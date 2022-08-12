package assessment.core.vttpssf.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import assessment.core.vttpssf.models.NewsArticle;
import assessment.core.vttpssf.services.NewsService;

@Controller
@RequestMapping
public class NewsController {

    @Autowired
    private NewsService newsSvc;
    
    //? GET Routes
    @GetMapping(path={"/"})
    public String getHomePage(Model model) {
        List<NewsArticle> articleResults = newsSvc.getArticles();

        model.addAttribute("results", articleResults);
        
        return "index";
    }

    @GetMapping(path={"/articles"})
    public String getSavedArticles(Model model) {

        return "articles";
    }

    //? POST Routes
    @PostMapping(path = "/articles")
    public String postArticles(Model model, @RequestBody MultiValueMap<String, String> form) {

        String articleId = form.getFirst("checkbox");
        System.out.println("Form data: " + articleId);

        return "articles";
    } 
}
