package com.alchemy.api;

import com.alchemy.api.models.Article;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.service.spi.InjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class LuceneIndexTest {

    @Test
    public ArrayList<Article> getSearchData(String key, EntityManager entityManager) {

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

        ArrayList<Article> articles = (ArrayList<Article>) fullTextQuery.getResultList();
        return articles;
    }
}
