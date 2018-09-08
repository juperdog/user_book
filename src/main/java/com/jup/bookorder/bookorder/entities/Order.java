package com.jup.bookorder.bookorder.entities;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by wasan_kha on 9/8/2018 AD.
 */
@Data
@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "book_id", nullable = false)
    private Long bookId;

    public Order(){}

    public Order(Long userId, Long bookId){
        this.userId = userId;
        this.bookId = bookId;
    }

}
