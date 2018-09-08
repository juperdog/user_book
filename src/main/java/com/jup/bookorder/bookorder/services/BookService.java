package com.jup.bookorder.bookorder.services;

import com.jup.bookorder.bookorder.dto.BookWithRecommendation;
import com.jup.bookorder.bookorder.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * Created by wasan_kha on 9/8/2018 AD.
 */
@Service
public class BookService {

    @Autowired
    private CachableBookService cachableBookService;

    public Set<BookWithRecommendation> getBookWithRecommendation(){
        List<Book> bookList = cachableBookService.getAllBooks();
        List<Book> recommendedBookList = cachableBookService.getRecommendedBook();

        //List all recommendedBook
        Set<Long> recommendedBookIdList = new HashSet<>();
        for(Book recommendedBook : recommendedBookList){
            recommendedBookIdList.add(recommendedBook.getId());
        }

        SortedSet<BookWithRecommendation> out = new TreeSet<>();
        for(Book book : bookList){
            out.add(new BookWithRecommendation(book, recommendedBookIdList.contains(book.getId())));
        }
        return out;
    }

    public Book getBookById(Long bookId){
        return cachableBookService.getAllBooksInMap().get(bookId);
    }

}
