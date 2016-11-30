package com.eguller.zpc.currency;

import java.util.Date;
import java.util.Set;

/**
 * Currency Layer implementation as rate provider.
 *
 * @author eguller
 */
public class CurrencyLayer implements RateProvider {
    @Override
    public Set<Rate> getHistoricalRate(String sourceCcy, Set<String> targetCurrencies, Date date) {
        return null;
    }

    @Override
    public Rate getHistoricalRate(String sourceCurrency, String targetCurrency, Date date) {
        return null;
    }

    @Override
    public Set<Rate> getCurrentRate(String sourceCurrency, Set<String> targetCurrency) {
        return null;
    }

    @Override
    public Rate getCurrentRate(String sourceCurrency, String targetCurrency) {
        return null;
    }
}
