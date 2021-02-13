package com.alchemy.api.controllers;

import com.alchemy.api.FetchData;
import com.alchemy.api.models.Article;
import com.alchemy.api.models.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class OurArticles {

    @Autowired
    FetchData fetchData;

    @RequestMapping(value = "article", method = RequestMethod.GET)
    public ArrayList<Article> getArticle() {
        return fetchData.findAll();
    }

    @RequestMapping(value = "id", method = RequestMethod.GET)
    public int getId() {
        return 16007;
    }
}
