package com.tacocloud.tacos;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class HomeController {


//    @ModelAttribute(name = "host")
//    public String getHost(@RequestHeader(HttpHeaders.HOST) String host) {
//        return host;
//    }
    


    @GetMapping("/home")
    public String home() {
        return "home" ;
    }

    
}
