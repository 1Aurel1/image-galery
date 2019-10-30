package tk.aurelmarishta.imagegallery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    public AppController() {
    }

    @GetMapping("")
    public String home() {


        return "home";
    }

}
