package com.jup.bookorder.utils;

import com.jup.bookorder.bookorder.exception.BadRequestException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wasan_kha on 9/8/2018 AD.
 */
public class Utils {

    private  static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    public static Date stringToDate(String dateStr) {
        try{
            return df.parse(dateStr);
        }catch (ParseException ex){
            throw new BadRequestException("parse date error [dd/MM/yyyy]");
        }
    }

    public static String dateToString(Date date){
        return df.format(date);
    }
}
