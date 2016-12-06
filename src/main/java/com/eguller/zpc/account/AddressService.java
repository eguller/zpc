package com.eguller.zpc.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

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

    @PostConstruct
    private void init() {
        Country germany = new Country();
        germany.setId(1L);
        germany.setIsoCode("DE");
        germany.setName("Deutschland");

        Country uk = new Country();
        uk.setId(2L);
        uk.setIsoCode("UK");
        uk.setName("United Kingdom");

        Country netherlands = new Country();
        netherlands.setId(3L);
        netherlands.setIsoCode("NL");
        netherlands.setName("Netherlands");
        countryRepository.save(germany);
        countryRepository.save(uk);
        countryRepository.save(netherlands);
    }
}
