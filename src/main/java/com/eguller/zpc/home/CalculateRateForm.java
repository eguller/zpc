package com.eguller.zpc.home;

import java.util.Date;

/**
 * (comment)
 *
 * @author eguller
 */
public class CalculateRateForm {
    Date date;
    String sourceCurrency;
    String targetCurrency;
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(String sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }
}
