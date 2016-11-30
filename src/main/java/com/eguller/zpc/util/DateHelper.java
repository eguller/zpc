package com.eguller.zpc.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * (comment)
 *
 * @author eguller
 */
public class DateHelper {
    public static String format(Date date){
        return DateTimeFormatter.ISO_DATE.format(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
    }
}
