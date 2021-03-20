package com.alchemy.api.controllers;

import com.alchemy.api.FetchData;
import com.alchemy.api.models.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;


@RestController
public class OurArticles {

    @Autowired
    FetchData fetchData;

    @RequestMapping(value = "articles", method = RequestMethod.GET)
    public ArrayList<Article> getArticle() {
        return fetchData.findAll();
    }

    @RequestMapping(value = "articles/{category}", method = RequestMethod.GET)
    public ArrayList<Article> findSportsNews(@PathVariable("category") String category){
        return fetchData.findArticleByCategory(category);
    }

}
