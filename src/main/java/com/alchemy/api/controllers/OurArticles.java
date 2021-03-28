package com.alchemy.api.controllers;

import com.alchemy.api.FetchData;
import com.alchemy.api.models.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
public class OurArticles {

    @Autowired
    FetchData fetchData;

    @RequestMapping(value = "articles", method = RequestMethod.GET)
    public ArrayList<Article> getArticle() {
        return fetchData.findAll();
    }

    @RequestMapping(value = "articles/{category}/{pageNo}", method = RequestMethod.GET)
    public ArrayList<Article> findSportsNews(@PathVariable("category") String category, @PathVariable("pageNo") int pageNo){
        Pageable pageable = PageRequest.of(pageNo, 12, Sort.by("publishTime").descending());
        return fetchData.findArticleByCategory(category, pageable);
    }

    @RequestMapping(value = "articles/{category}", method = RequestMethod.GET)
    public ArrayList<Article> findNewsByCategory(@PathVariable("category") String category) {
        return fetchData.findArticleByCategory(category);
    }

}
