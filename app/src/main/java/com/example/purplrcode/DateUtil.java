package com.example.purplrcode;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    @SuppressLint("SimpleDateFormat")
    public static String getNowDateTime() {
        SimpleDateFormat s_format = new SimpleDateFormat("MM月dd日");
        return s_format.format(new Date());
    }

    @SuppressLint("SimpleDateFormat")
    public static String getNowTime() {
        SimpleDateFormat s_format = new SimpleDateFormat("HH:mm:ss");
        return s_format.format(new Date());
    }
}
