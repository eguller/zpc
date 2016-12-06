package com.eguller.zpc.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * (comment)
 *
 * @author eguller
 */
@Repository
public interface AddressRepository extends JpaRepository<Account, Long> {
    //public void
}
