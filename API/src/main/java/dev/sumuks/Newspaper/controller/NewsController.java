package dev.sumuks.Newspaper.controller;

import dev.sumuks.Newspaper.config.TenantContext;
import dev.sumuks.Newspaper.entity.News;
import dev.sumuks.Newspaper.service.NewspaperSchedulerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
public class NewsController {

    @Autowired
    NewspaperSchedulerService newsScheduler;

    @GetMapping(value = "/helloWorld")
    public String helloWorld(){
        return ("Hello " + TenantContext.getCurrentTenant());
    }

    @GetMapping("/newsapplication")
    public ResponseEntity<?> webpage(@RequestHeader String tenantID, @RequestParam(name = "category") String category){
        try {
            List<News> newsList = newsScheduler.returnNews(category.toLowerCase());
            return new ResponseEntity<>(newsList, HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            log.info(e.getMessage());
            return new ResponseEntity<>("ERROR : " + e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
