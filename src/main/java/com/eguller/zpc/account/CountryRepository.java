package com.eguller.zpc.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (comment)
 *
 * @author eguller
 */

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    List<Country> findAll();
}
