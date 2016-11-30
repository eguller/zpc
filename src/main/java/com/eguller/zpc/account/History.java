package com.eguller.zpc.account;

import com.eguller.zpc.currency.Rate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * Historical record of currency conversion requests.
 * Records older than latest 10 are deleted periodically.
 *
 * @author eguller
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue
    private Long id;
    private String sourceCurrency;
    private String targetCurrency;
    private Date rateDate;
    @Column(precision = 19, scale = 4)
    private BigDecimal rate;
    @Column(name = "requestTime", updatable = false)
    private Date requestTime;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    private boolean historicLookup;


    protected History() {
    }

    public History(Rate rate, Account account) {
        this.sourceCurrency = rate.getSourceCcy();
        this.targetCurrency = rate.getTargetCcy();
        this.rateDate = rate.getDate();
        this.rate = rate.getRate();
        this.account = account;
        this.historicLookup = rate.isHistoricLookup();
    }

    @PrePersist
    protected void onCreate(){
        requestTime = Calendar.getInstance().getTime();
    }

    public Long getId() {
        return id;
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

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Date getRateDate() {
        return rateDate;
    }

    public void setRateDate(Date rateDate) {
        this.rateDate = rateDate;
    }

    public boolean isHistoricLookup() {
        return historicLookup;
    }

    public void setHistoricLookup(boolean historicLookup) {
        this.historicLookup = historicLookup;
    }
}
