package com.alchemy.api.controllers;

import com.alchemy.api.FetchData;
import com.alchemy.api.LuceneIndexTest;
import com.alchemy.api.models.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.ArrayList;


@RestController
public class OurArticles {

    private final LuceneIndexTest luceneIndexTest = new LuceneIndexTest();
    @Autowired
    EntityManager entityManager;
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

    @RequestMapping(value="search/{key}", method = RequestMethod.GET)
    public ArrayList<Article> findSearchNews(@PathVariable("key") String key) {
        return luceneIndexTest.getSearchData(key, entityManager);
    }
}
