package org.dell.kube.pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/")
public class HomeController {
    Logger loger = LoggerFactory.getLogger(HomeController.class);
    private String pageContent;

    public HomeController(@Value("${page.content}") String pageContent){
        this.pageContent=pageContent;

    }
    @GetMapping
    public String getPage(){
        loger.error("Welcome Page Accessed");
        loger.debug("Welcome Page Accessed");
        loger.info("Welcome Page Accessed");
        loger.trace("Welcome Page Accessed");
        loger.warn("Welcome Page Accessed");
        return "Hello from page : "+pageContent+" ";
    }


}