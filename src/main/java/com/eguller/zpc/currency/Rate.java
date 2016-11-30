package com.eguller.zpc.currency;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

/**
 * (comment)
 *
 * @author eguller
 */
public class Rate {
    String sourceCcy;
    String targetCcy;
    BigDecimal rate;
    Date date;
    boolean historicLookup;
    public Rate(String sourceCcy, String targetCcy, BigDecimal rate){
        this(sourceCcy, targetCcy, rate, Calendar.getInstance().getTime());
        this.historicLookup = false;
    }

    public Rate(String sourceCcy, String targetCcy, BigDecimal rate, Date date){
        this.sourceCcy = sourceCcy;
        this.targetCcy = targetCcy;
        this.rate = rate;
        this.date = date;
        this.historicLookup = true;
    }

    public String getSourceCcy() {
        return sourceCcy;
    }

    public String getTargetCcy() {
        return targetCcy;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public Date getDate() {
        return date;
    }

    public boolean isHistoricLookup() {
        return historicLookup;
    }

    public void setHistoricLookup(boolean historicLookup) {
        this.historicLookup = historicLookup;
    }
}
