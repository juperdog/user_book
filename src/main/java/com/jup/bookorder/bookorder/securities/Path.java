package com.jup.bookorder.bookorder.securities;

import lombok.Data;

/**
 * Created by wasan_kha on 9/4/2018 AD.
 */
@Data
public class Path {

    private String method;
    private String uri;

    public Path(String method, String uri){
        this.method = method;
        this.uri = uri;
    }
}
