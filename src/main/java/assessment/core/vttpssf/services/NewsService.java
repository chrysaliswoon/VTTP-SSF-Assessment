package assessment.core.vttpssf.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import assessment.core.vttpssf.models.NewsArticle;

@Service
public class NewsService {

    // ? API URL
    private static String apiUrl = "https://min-api.cryptocompare.com/data/v2/news/?lang=EN";

    // ? Inject value into fields
    @Value("${API_KEY}")
    private String apiKey;

    public List<NewsArticle> getArticles() {

        return null;

    }

}
