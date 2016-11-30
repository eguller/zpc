package com.eguller.zpc.currency;

import com.eguller.zpc.client.RestClient;
import com.eguller.zpc.util.DateHelper;
import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Open Exchange rates implementation as rate provide.
 *
 * @author eguller
 */

@PropertySource("classpath:application.properties")
public class OpenExchangeRates implements RateProvider {
    private static final String DEFAULT_BASE_CURRENCY = "USD";

    @Value("${openexchangerates.appid}")
    String appId;

    @Autowired
    RestClient restClient;

    @Override
    public Set<Rate> getHistoricalRate(String sourceCurrency, Set<String> targetCurrencies, Date date) {
        Response response = restClient.get("https://openexchangerates.org/api/historical/{date}.json?app_id={appId}", Response.class, DateHelper.format(date), appId);
        return doConversion(sourceCurrency, targetCurrencies, response, date);
    }

    @Override
    public Rate getHistoricalRate(String sourceCurrency, String targetCurrency, Date date) {
        Set<String> targetCurrencies = new HashSet<>();
        targetCurrencies.add(targetCurrency);
        Optional<Rate> rate = getHistoricalRate(sourceCurrency, targetCurrencies, date).stream().findFirst();
        if (rate.isPresent()) {
            return rate.get();
        } else {
            throw new ContextedRuntimeException("Rate cannot be calculated.")
                    .addContextValue("sourceCurreny", sourceCurrency)
                    .addContextValue("targetCurrency", targetCurrency)
                    .addContextValue("date", date);
        }
    }

    @Override
    public Set<Rate> getCurrentRate(String sourceCurrency, Set<String> targetCurrencies) {
        Response response = restClient.get("https://openexchangerates.org/api/latest.json?app_id={appId}", Response.class, appId);
        return doConversion(sourceCurrency, targetCurrencies, response);
    }

    @Override
    public Rate getCurrentRate(String sourceCurrency, String targetCurrency) {
        Set<String> targetCurrencies = new HashSet<>();
        targetCurrencies.add(targetCurrency);
        Optional<Rate> rate = getCurrentRate(sourceCurrency, targetCurrencies).stream().findFirst();
        if (rate.isPresent()) {
            return rate.get();
        } else {
            throw new ContextedRuntimeException("Rate cannot be calculated")
                    .addContextValue("sourceCurrency", sourceCurrency)
                    .addContextValue("targetCurrency", targetCurrency);
        }
    }

    private Set<Rate> doConversion(String sourceCurrency, Set<String> targetCurrencies, Response response,Date date){
        Map<String, Rate> rates = responseToRates(response, date);
        return doConversion(sourceCurrency, targetCurrencies, rates);
    }

    private Set<Rate> doConversion(String sourceCurrency, Set<String> targetCurrencies, Response response){
        Map<String, Rate> rates = responseToRates(response);
        return doConversion(sourceCurrency, targetCurrencies, rates);
    }

    private Map<String, Rate> responseToRates(Response response, Date date) {
        Map<String, Rate> rates = new HashMap<>();
        response.getRates().entrySet().forEach(entry -> rates.put(entry.getKey(), new Rate(response.getBase(), entry.getKey(), entry.getValue(), date)));
        return rates;
    }

    private Map<String, Rate> responseToRates(Response response) {
        Map<String, Rate> rates = new HashMap<>();
        response.getRates().entrySet().forEach(entry -> rates.put(entry.getKey(), new Rate(response.getBase(), entry.getKey(), entry.getValue())));
        return rates;
    }

    private Set<Rate> doConversion(String sourceCurrency, Set<String> targetCurrencies, Map<String, Rate> usdToAllOther) {
        Set<Rate> result = new HashSet<>();
        for (String targetCurrency : targetCurrencies) {
            if (DEFAULT_BASE_CURRENCY.equals(sourceCurrency)) {
                Rate targetRate = usdToAllOther.get(targetCurrency);
                result.add(targetRate);
            } else {
                Rate sourceRate = usdToAllOther.get(sourceCurrency);
                Rate targetRate = usdToAllOther.get(targetCurrency);
                if (sourceRate != null && targetRate != null) {
                    Rate calculatedRate = new Rate(sourceCurrency, targetCurrency, targetRate.getRate().divide(sourceRate.getRate(), RoundingMode.HALF_UP), sourceRate.getDate());
                    result.add(calculatedRate);
                }
            }
        }
        return result;
    }

    private static class Response {
        String base;
        Map<String, BigDecimal> rates;

        public String getBase() {
            return base;
        }

        public void setBase(String base) {
            this.base = base;
        }

        public Map<String, BigDecimal> getRates() {
            return rates;
        }

        public void setRates(Map<String, BigDecimal> rates) {
            this.rates = rates;
        }
    }
}
