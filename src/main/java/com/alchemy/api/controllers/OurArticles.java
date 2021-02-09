package com.alchemy.api.controllers;

import com.alchemy.api.models.Article;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OurArticles {


    @RequestMapping(value = "articles", method = RequestMethod.GET)
    public List<Article> getArticles(){

        var article =  new Article();
        article.setId(1);
        article.setDetail("Hello detail");
        article.setTitle("title");

        var article1 =  new Article();
        article1.setId(2);
        article1.setDetail("Hello detail");
        article1.setTitle("title");

        var list = new ArrayList<Article>();
        list.add(article);
        list.add(article1);

        return list;
    }


    @RequestMapping(value = "articles/{id}", method = RequestMethod.GET)
    public Article getArticle(@PathVariable("id") int id){
        var article =  new Article();
        article.setId(id);
        article.setDetail("Hello detail");
        article.setTitle("title");

        return article;
    }
}
