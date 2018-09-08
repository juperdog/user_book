package com.jup.bookorder.bookorder.dto.responses;

import com.jup.bookorder.bookorder.dto.BookWithRecommendation;
import lombok.Data;

import java.util.Set;

/**
 * Created by wasan_kha on 9/8/2018 AD.
 */
@Data
public class BookResponse {
    private Set<BookWithRecommendation> books;


    public BookResponse(Set<BookWithRecommendation> bookSet){
        this.books = bookSet;
    }
}
