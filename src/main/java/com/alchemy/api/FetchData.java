package com.alchemy.api;

import com.alchemy.api.models.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface FetchData extends PagingAndSortingRepository<Article, String> {
    @Override
    ArrayList<Article> findAll();

    ArrayList<Article> findArticleByCategory(String sports);

    ArrayList<Article> findArticleByCategory(String category, Pageable pageable);
}
