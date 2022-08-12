package assessment.core.vttpssf.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import assessment.core.vttpssf.models.NewsArticle;
import assessment.core.vttpssf.services.NewsService;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@RequestMapping(path = "/news", produces = MediaType.APPLICATION_JSON_VALUE)
public class NewsRESTController {

    @Autowired
    private NewsService newsSvc;

    @GetMapping(path = "{id}")
    public ResponseEntity<String> getNewsArticle(@PathVariable String id) {

        Optional<NewsArticle> opt = newsSvc.getNewsById(id);

        if (opt.isEmpty()) {
            JsonObject err = Json.createObjectBuilder()
            .add("error", "Cannot find news article %s".formatted(id))
            .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(err.toString());
        }

        NewsArticle newsArticle = opt.get();
        return ResponseEntity.ok(newsArticle.toJson().toString());
    }
    
}
