package com.eguller.zpc.currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void save(Currency currency){
        currencyRepository.save(currency);
    }

    /**
     * All currencies which can be used in the system.
     *
     * @return - all currencies
     */
    @Cacheable("currencies")
    public Set<Currency> getAllCurrencies(){
        return Collections.unmodifiableSet(new HashSet<>(currencyRepository.findAll()));
    }
}
