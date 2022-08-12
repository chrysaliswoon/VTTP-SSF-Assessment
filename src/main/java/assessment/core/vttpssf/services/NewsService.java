package assessment.core.vttpssf.services;

import java.io.Reader;
import java.io.StringReader;
import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import assessment.core.vttpssf.models.NewsArticle;
import assessment.core.vttpssf.repositories.NewsRespository;

@Service
public class NewsService {

    @Autowired
    private NewsRespository newsRepo;

    // ? API URL
    private static String apiUrl = "https://min-api.cryptocompare.com/data/v2/news/?lang=EN";

    // ? Inject value into fields
    @Value("${API_KEY}")
    private String apiKey;

    public List<NewsArticle> getArticles() {

        // ? Create endpoint URL with query string
        String url = UriComponentsBuilder.fromUriString(apiUrl)
                .queryParam("api_key", apiKey)
                .toUriString();

        // System.out.println("Endpoint URL: " + url);

        // ? Create GET Request
        RequestEntity<Void> req = RequestEntity.get(url).build();

        // ? Make call to Cryptocurrency API
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> res;

        try {
            res = template.exchange(req, String.class);
        } catch (Exception ex) {
            System.err.printf("Error: ", ex.getMessage());
            return Collections.emptyList();
        }

        // ? Get the body with the payload
        String payload = res.getBody();
        // System.out.println("Payload: " + payload);

        // System.out.printf("Cache: " + id);

        // //? Convert payload to JSON object
        Reader strReader = new StringReader(payload);
        // System.out.println("JSON Object: " + strReader);

        // //? Create JSONReader from Reader
        JsonReader jsonReader = Json.createReader(strReader);

        // //? Reads payload as JSON object
        JsonObject cryptoResult = jsonReader.readObject();
        // System.out.println("Crypto Result: " + cryptoResult);

        // //? Get array within the object
        JsonArray cryptoData = cryptoResult.getJsonArray("Data");
        // System.out.println("Crypto Data: " + cryptoData);

        // List<Object> objList = new ArrayList<>();
        ArrayList<NewsArticle> list = new ArrayList<>();

        // //? Loop through the array
        for (int i = 0; i < cryptoData.size(); i++) {
            JsonObject object = cryptoData.getJsonObject(i);

            String articleId = object.getString("id");

            Integer date = object.getInt("published_on");
            String publishDate = Integer.toString(date);

            String articleTitle = object.getString("title");
            String articleUrl = object.getString("url");
            String imgUrl = object.getString("imageurl");
            String articleBody = object.getString("body");
            String articleTag = object.getString("tags");
            String articleCategory = object.getString("categories");

            list.add(NewsArticle.createArticle(articleId, articleTitle, articleTag, publishDate, articleBody,
                    articleCategory, articleUrl, imgUrl));

        }
        return list;

    }

    public List<NewsArticle> saveArticles() {

        return null;
    }

    public Optional<NewsArticle> getNewsById(String id) {
        String result = newsRepo.get(id);
        //System.out.printf("Result: " + result);

        if (result == null)
            return Optional.empty();

        return Optional.of(NewsArticle.create(result));
    }

}
