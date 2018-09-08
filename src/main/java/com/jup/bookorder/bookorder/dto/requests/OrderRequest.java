package com.jup.bookorder.bookorder.dto.requests;

import lombok.Data;

import java.util.List;

/**
 * Created by wasan_kha on 9/8/2018 AD.
 */
@Data
public class OrderRequest {
    List<Long> orders;
}
