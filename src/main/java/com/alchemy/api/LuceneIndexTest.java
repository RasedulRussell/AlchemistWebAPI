package com.alchemy.api;

import com.alchemy.api.models.Article;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import java.util.*;
import java.util.stream.Collectors;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class LuceneIndexTest {

    @Test
    public List<Article> getSearchData(String key, EntityManager entityManager) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        //Create a Hibernate Search DSL query builder for the required entity
        org.hibernate.search.query.dsl.QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Article.class)
                .get();

        //Generate a Lucene query using the builder
        org.apache.lucene.search.Query query = queryBuilder
                .keyword()
                .onFields("details", "title").matching(key)
                .createQuery();

        org.hibernate.search.jpa.FullTextQuery fullTextQuery =
                fullTextEntityManager.createFullTextQuery(query, Article.class);
        var searchResult = fullTextQuery.getResultList();

        if(searchResult == null) return Collections.emptyList();

        var articles = (List<Article>) searchResult;
        var sorted = articles.stream()
                .sorted(Comparator.comparing(Article::getPublishTime).reversed())
                .collect(Collectors.toList());
        return sorted;
    }
}
