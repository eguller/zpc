package com.eguller.zpc.currency;

import java.util.Date;
import java.util.Set;

/**
 * Rate provider connects to an external system to read either historical or
 * current currency rates.
 */
public interface RateProvider {
    /**
     * Return historical currency rate for given base and target currencies.
     *
     * @param sourceCurrency   - base currency
     * @param targetCurrencies - target currencies
     * @param date             - previous date for historical rate.
     * @return - Historical rates for given date.
     */
    Set<Rate> getHistoricalRate(String sourceCurrency, Set<String> targetCurrencies, Date date);

    /**
     * @param sourceCurrency - base currency
     * @param targetCurrency - target currency
     * @param date           - previous date for historical date.
     * @return - Historical rates for given date.
     */
    Rate getHistoricalRate(String sourceCurrency, String targetCurrency, Date date);

    /**
     * Find current currenct rates for given base currency and target currencies.
     * @param sourceCurrency - base currency
     * @param targetCurrency - target currencies
     * @return - rates for given base and target currencies
     */
    Set<Rate> getCurrentRate(String sourceCurrency, Set<String> targetCurrency);

    /**
     * Current currency rate for give base and target currency.
     * @param sourceCurrency - base currency.
     * @param targetCurrency - target currency.
     * @return - current rate for given base and target currency.
     */
    Rate getCurrentRate(String sourceCurrency, String targetCurrency);

}
