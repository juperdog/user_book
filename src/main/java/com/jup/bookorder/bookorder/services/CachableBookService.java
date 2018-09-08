package com.jup.bookorder.bookorder.services;

import com.jup.bookorder.bookorder.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wasan_kha on 9/8/2018 AD.
 */
@Service
@CacheConfig(cacheNames = {"allBooks", "recommendedBooks"})
public class CachableBookService {

    @Value("${bookservice.listbook.url}")
    private String bookServiceUrl;

    @Value("${bookservice.recommendationbook.url}")
    private String bookRecomendationServiceUrl;

    private static ParameterizedTypeReference listBook =new ParameterizedTypeReference<List<Book>>(){};


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CacheManager cm;

    //this method will be cache until midnight of sunday (invalidate cache will be done by invaliateAllBooksCache)
    @Cacheable("allBooks")
    public List<Book> getAllBooks(){
        try {
            ResponseEntity<List<Book>> responses = restTemplate.exchange(bookServiceUrl, HttpMethod.GET, null, listBook);
            return responses.getBody();
        }catch(Exception e){
            throw new RuntimeException("Cannot connect to book-service");
        }
    }

    @Cacheable("recommendedBooks")
    public List<Book> getRecommendedBook(){
        try {
            ResponseEntity<List<Book>> responses = restTemplate.exchange(bookRecomendationServiceUrl, HttpMethod.GET, null, listBook);
            return responses.getBody();
        }catch(Exception e){
            throw new RuntimeException("Cannot connect to book-service");
        }
    }

    //this method will be cache until midnight of sunday (invalidate cache will be done by invaliateAllBooksCache)
    @Cacheable("allBooksMap")
    public Map<Long, Book> getAllBooksInMap(){
        List<Book> bookList = getAllBooks();
        Map<Long, Book> allBooksMap = new HashMap<>();
        for(Book book : bookList){
            allBooksMap.put(book.getId(), book);
        }
        return allBooksMap;
    }


    //invalidate cache[allBooks,allBooksMap] every SUN on 00:05
    @Scheduled(cron="0 5 0 * * SUN")
    public void invaliateAllBooksCache() {
        String cacheNameArray[] = new String[]{"allBooks", "allBooksMap"};
        for(String cacheName : cacheNameArray){
            Cache cache = cm.getCache(cacheName);
            cache.clear();
        }
    }
}
