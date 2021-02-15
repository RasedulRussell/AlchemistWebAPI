package com.alchemy.api;

import com.alchemy.api.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface FetchData extends JpaRepository<Article, String> {
    @Override
    ArrayList<Article> findAll();

    @Query("select article.details from Article article")
    ArrayList<String> getDetails();

    @Query("select article.url from Article article")
    ArrayList<String> getUrl();

}
