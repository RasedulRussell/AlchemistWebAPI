package com.alchemy.api;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

import javax.persistence.EntityManagerFactory;

public class LuceneIndexServiceBean {

    private FullTextEntityManager fullTextEntityManager ;

    public LuceneIndexServiceBean(EntityManagerFactory entityManagerFactory) {
        fullTextEntityManager = Search.getFullTextEntityManager(entityManagerFactory.createEntityManager());
    }

    public void triggerIndexing() {
        try{
            fullTextEntityManager.createIndexer().startAndWait();
        }catch(InterruptedException e){
            System.out.println("here");
            throw new RuntimeException(e);
        }
    }
}
