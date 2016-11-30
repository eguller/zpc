package com.eguller.zpc.currency;

import com.eguller.zpc.account.Account;
import com.eguller.zpc.account.History;
import com.eguller.zpc.account.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * (comment)
 *
 * @author eguller
 */

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ConverterService {
    @Autowired
    RateProvider rateProvider;
    @Autowired
    HistoryRepository historyRepository;

    public Rate getHistoricalRate(String sourceCurrency, String targetCurrency, Date rateDate){
        Rate rate = rateProvider.getHistoricalRate(sourceCurrency, targetCurrency, rateDate);
        return rate;
    }

    public Rate getCurrentRate(String sourceCurrency, String targetCurrency){
        Rate rate = rateProvider.getCurrentRate(sourceCurrency, targetCurrency);
        return rate;
    }

    public List<History> loadHistoryFor(Account account){
        return historyRepository.loadAccountHistory(account, new PageRequest(0,10));
    }

    /**
     * Saves an history entry for currency conversion request.
     * @param rate - Result of a rate request
     * @param account - Requesting account
     */
    public void saveHistory(Rate rate, Account account){
        History history = new History(rate, account);
        historyRepository.save(history);
    }
}
