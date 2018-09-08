package com.jup.bookorder.bookorder.services;

import com.jup.bookorder.bookorder.entities.Book;
import com.jup.bookorder.bookorder.entities.Order;
import com.jup.bookorder.bookorder.entities.User;
import com.jup.bookorder.bookorder.exception.BadRequestException;
import com.jup.bookorder.bookorder.repositories.OrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wasan_kha on 9/8/2018 AD.
 */
@Service
public class OrderService {

    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired
    private BookService bookService;

    @Transactional
    public BigDecimal order(User user, List<Long> bookIdList){

        BigDecimal sum = BigDecimal.ZERO;
        List<Order> orderList = new ArrayList<>(bookIdList.size());
        List<Long> bookIdNotFound = new ArrayList<>(bookIdList.size());

        //validation data
        for(Long bookId : bookIdList){
            Book book = bookService.getBookById(bookId);
            if(book != null){
                orderList.add(new Order(user.getId(), bookId));
                sum = sum.add(book.getPrice());
            }else{
                bookIdNotFound.add(bookId);
            }
        }

        //hasError?
        if(bookIdNotFound.size() > 0){
            throw new BadRequestException("book_id "+ Arrays.toString(bookIdNotFound.toArray())+" not found ");
        }

        for(Order order : orderList){
            ordersRepo.save(order);
        }
        return sum;
    }

    public List<Order> getOrderByUser(User user){
        return ordersRepo.findByUserId(user.getId());
    }

    public void deleteOrderByUser(User user){
        List<Order> orderList = ordersRepo.findByUserId(user.getId());
        ordersRepo.delete(orderList);
    }
}
