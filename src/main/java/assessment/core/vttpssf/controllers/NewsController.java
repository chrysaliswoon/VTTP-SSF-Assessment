package assessment.core.vttpssf.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import assessment.core.vttpssf.models.NewsArticle;
import assessment.core.vttpssf.services.NewsService;

@Controller
public class NewsController {

    @Autowired
    private NewsService newsSvc;
    
    //? GET Routes
    @GetMapping(path={"/", "index.html"})
    public String Homepage(Model model) {
        List<NewsArticle> articleResults = newsSvc.getArticles();

        model.addAttribute("results", articleResults);
        
        return "index.html";
    }
}
