package com.eguller.zpc.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Utility method for all date parsing, formatting and calculations.
 *
 * @author eguller
 */
public class DateHelper {
    public static String format(Date date){
        return DateTimeFormatter.ISO_DATE.format(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
    }
}
