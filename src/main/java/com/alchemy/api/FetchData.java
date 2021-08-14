package com.alchemy.api;

import com.alchemy.api.models.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface FetchData extends PagingAndSortingRepository<Article, String> {
    @Override
    List<Article> findAll();

    List<Article> findArticleByCategory(String sports);

    ArrayList<Article> findArticleByCategory(String category, Pageable pageable);
}
