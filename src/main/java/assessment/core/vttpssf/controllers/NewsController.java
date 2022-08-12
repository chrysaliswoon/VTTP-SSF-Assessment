package assessment.core.vttpssf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewsController {
    
    //? GET Routes
    @GetMapping(path={"/", "index.html"})
    public String Homepage(Model model) {
        return "index.html";
    }
}
