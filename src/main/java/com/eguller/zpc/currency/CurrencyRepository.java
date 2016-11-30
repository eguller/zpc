package com.eguller.zpc.currency;

import com.eguller.zpc.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * (comment)
 *
 * @author eguller
 */
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
