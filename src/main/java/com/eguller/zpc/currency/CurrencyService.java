package com.eguller.zpc.currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Service to define new currencies or load existing currencies in the system.
 *
 * @author eguller
 */

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;

    @PostConstruct
    protected void init(){
        save(new Currency("EUR", "Euro"));
        save(new Currency("USD", "US Dollar"));
        save(new Currency("GBP", "British Pound"));
        save(new Currency("TRY", "Turkish Lira"));
        save(new Currency("NZD", "New Zealand Dollar"));
        save(new Currency("AUD", "Australian Dollar"));
        save(new Currency("JPY", "Japanise Yen"));
        save(new Currency("HUF", "Hungarian Florin"));
    }

    @Transactional
    public void save(Currency currency){
        currencyRepository.save(currency);
    }

    /**
     * All currencies which can be used in the system.
     *
     * @return - all currencies
     */
    public Set<Currency> getAllCurrencies(){
        return Collections.unmodifiableSet(new HashSet<>(currencyRepository.findAll()));
    }
}
