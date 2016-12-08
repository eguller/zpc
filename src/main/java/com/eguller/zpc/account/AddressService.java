package com.eguller.zpc.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (comment)
 *
 * @author eguller
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AddressService {
    @Autowired
    CountryRepository countryRepository;

    List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
}
