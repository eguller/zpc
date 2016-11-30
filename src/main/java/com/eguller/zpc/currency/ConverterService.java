package com.eguller.zpc.currency;

import com.eguller.zpc.account.Account;
import com.eguller.zpc.account.History;
import com.eguller.zpc.account.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Convert currency rates by using data which is provided from external services.
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

    /**
     * Find a rate for a give previous date.
     *
     * @param sourceCurrency - from currency
     * @param targetCurrency - to currency
     * @param rateDate       - date for historical data
     * @return - Currency rate for given currencies and given date.
     */
    public Rate getHistoricalRate(String sourceCurrency, String targetCurrency, Date rateDate){
        return rateProvider.getHistoricalRate(sourceCurrency, targetCurrency, rateDate);
    }

    /**
     * Find current currency rate for given currencies.
     *
     * @param sourceCurrency - from currency
     * @param targetCurrency - to currency
     * @return - Current currency rate
     */
    public Rate getCurrentRate(String sourceCurrency, String targetCurrency) {
        return rateProvider.getCurrentRate(sourceCurrency, targetCurrency);
    }

    /**
     * Loads currency conversion request history for given account
     * @param account - account to load historical data
     * @return - last 10 request
     */
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
