package com.johnny.bankworker.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
    final static String DEFAULT_PATTERN = "MM-dd-yyyy HH:mm:ss";

    public static String getCurrentDateTime(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static Date convert(String value) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.parse(value);
    }

    public static String getRecentMonthDateTime(int recentMonth){
        GregorianCalendar gc=new GregorianCalendar();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        gc.add(2,-recentMonth);

        return simpleDateFormat.format(gc.getTime());
    }
}
