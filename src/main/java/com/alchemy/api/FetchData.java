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

    ArrayList<Article> findArticleByCategory(String sports);

}
