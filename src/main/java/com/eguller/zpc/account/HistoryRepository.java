package com.eguller.zpc.account;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * (comment)
 *
 * @author eguller
 */
public interface HistoryRepository extends JpaRepository<History, Long> {
    @Query("select h from History h where h.account =:account order by h.requestTime desc")
    List<History> loadAccountHistory(@Param("account") Account account, Pageable pageable);
}
