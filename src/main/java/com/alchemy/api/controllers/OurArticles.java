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
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class OurArticles {

    private final LuceneIndexTest luceneIndexTest = new LuceneIndexTest();
    @Autowired
    EntityManager entityManager;
    @Autowired
    FetchData fetchData;

    @RequestMapping(value = "articles", method = RequestMethod.GET)
    public List<Article> getArticle() {
        List<Article> articles = fetchData.findAll();
        return articles.stream()
                .sorted(Comparator.comparing(Article::getPublishTime).reversed())
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "articles/{category}/{pageNo}", method = RequestMethod.GET)
    public ArrayList<Article> findSportsNews(@PathVariable("category") String category, @PathVariable("pageNo") int pageNo){
        Pageable pageable = PageRequest.of(pageNo, 12, Sort.by("publishTime").descending());
        return fetchData.findArticleByCategory(category, pageable);
    }

    @RequestMapping(value = "articles/{category}", method = RequestMethod.GET)
    public List<Article> findNewsByCategory(@PathVariable("category") String category) {
        List<Article> articles =  fetchData.findArticleByCategory(category);
        return articles.stream()
                .sorted(Comparator.comparing(Article::getPublishTime).reversed())
                .collect(Collectors.toList());
    }

    @RequestMapping(value="/news/search/{key}", method = RequestMethod.GET)
    public List<Article> findSearchNews(@PathVariable("key") String key) {
        return luceneIndexTest.getSearchData(key, entityManager);
    }
    @RequestMapping(value = "/marque", method = RequestMethod.GET)
    public List<Article> findMarque() {
        List<Article> articles = fetchData.findAll();
        articles = articles.stream()
                .sorted(Comparator.comparing(Article::getPublishTime).reversed())
                .collect(Collectors.toList());

        return articles.stream().limit(30).collect(Collectors.toList());
    }
}
