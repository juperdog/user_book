package com.jup.bookorder.bookorder.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jup.bookorder.bookorder.entities.Book;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wasan_kha on 9/8/2018 AD.
 */
@Data
public class BookWithRecommendation implements Comparable<BookWithRecommendation>{

    private Long id;

    private String name;

    private String author;

    private BigDecimal price;

    @JsonProperty("is_recommended")
    private boolean isRecommended = false;

    public BookWithRecommendation(Book book, boolean isRecommended){
        this.id = book.getId();
        this.name = book.getBookName();
        this.author = book.getAuthorName();
        this.price = book.getPrice();
        this.isRecommended = isRecommended;
    }

    @Override
    public int compareTo(BookWithRecommendation o) {
        if(this.isRecommended() && !o.isRecommended())
            return -1;
        return this.getName().compareTo(o.getName());
    }
}
