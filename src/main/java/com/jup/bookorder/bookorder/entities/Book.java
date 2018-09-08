package com.jup.bookorder.bookorder.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by wasan_kha on 9/8/2018 AD.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {

    private Long id;

    @JsonProperty("author_name")
    private String authorName;

    @JsonProperty("book_name")
    private String bookName;

    private BigDecimal price;
}
