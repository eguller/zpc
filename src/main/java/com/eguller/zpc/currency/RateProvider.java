package com.eguller.zpc.currency;

import java.util.Date;
import java.util.Set;

/**
 * Created by eguller on 11/20/16.
 */
public interface RateProvider {
    Set<Rate> getHistoricalRate(String sourceCurrency, Set<String> targetCurrency, Date date);

    Rate getHistoricalRate(String sourceCurrency, String targetCurrency, Date date);

    Set<Rate> getCurrentRate(String sourceCurrency, Set<String> targetCurrency);

    Rate getCurrentRate(String sourceCurrency, String targetCurrency);

}
