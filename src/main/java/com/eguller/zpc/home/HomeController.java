package com.eguller.zpc.home;

import com.eguller.zpc.account.Account;
import com.eguller.zpc.account.AccountService;
import com.eguller.zpc.account.History;
import com.eguller.zpc.currency.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Controller
class HomeController {
    private static final String DEFAULT_SOURCE_CURRENCY = "USD";
    private static final String DEFAULT_TARGET_CURRENCY = "EUR";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy");
    @Autowired
    CurrencyService currencyService;
    @Autowired
    ConverterService converterService;

    @Autowired
    AccountService accountService;

    @ModelAttribute("module")
    String module() {
        return "home";
    }


    @GetMapping("/")
    String index(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("sourceCurrency", DEFAULT_SOURCE_CURRENCY);
            model.addAttribute("targetCurrency", DEFAULT_TARGET_CURRENCY);
            model.addAttribute("currencies", currencyService.getAllCurrencies());

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Account account = accountService.loadAccountByUserName(authentication.getName());
            model.addAttribute("history", converterService.loadHistoryFor(account));
            return "home/homeSignedIn";
        } else {
            return "home/homeNotSignedIn";
        }
    }

    @GetMapping("rate")
    String rate(@RequestParam("sourceCurrency") String sourceCurrency,
                @RequestParam("targetCurrency") String targetCurrency,
                @RequestParam(value = "rateDate", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Optional<Date> rateDate, Model model) {
        model.addAttribute("sourceCurrency", sourceCurrency);
        model.addAttribute("targetCurrency", targetCurrency);
        model.addAttribute("currencies", currencyService.getAllCurrencies());
        model.addAttribute("rateDate", rateDate);

        Rate rate;
        if(rateDate.isPresent()){
            rate = converterService.getHistoricalRate(sourceCurrency, targetCurrency, rateDate.get());
            model.addAttribute("rateDate", formatter.format(rateDate.get().toInstant()));
        } else {
            rate = converterService.getCurrentRate(sourceCurrency, targetCurrency);
        }
        model.addAttribute("rate", rate);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountService.loadAccountByUserName(authentication.getName());
        List<History> history = converterService.loadHistoryFor(account);
        model.addAttribute("history", history);
        converterService.saveHistory(rate, account);
        return "home/homeSignedIn";
    }
}
