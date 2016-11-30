package com.eguller.zpc.signup;

import com.eguller.zpc.account.Account;
import com.eguller.zpc.account.AccountService;
import com.eguller.zpc.web.Ajax;
import com.eguller.zpc.web.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * New user account creation.
 */
@Controller
class SignupController {

    private static final String SIGNUP_VIEW_NAME = "signup/signup";

    @Autowired
    private AccountService accountService;

    @GetMapping("signup")
    String signup(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
        model.addAttribute(new SignupForm());
        if (Ajax.isAjaxRequest(requestedWith)) {
            return SIGNUP_VIEW_NAME.concat(" :: signupForm");
        }
        return SIGNUP_VIEW_NAME;
    }

    @PostMapping("signup")
    String signup(@Valid @ModelAttribute SignupForm signupForm, Errors errors, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            return SIGNUP_VIEW_NAME;
        }
        Account account = accountService.save(signupForm.createAccount());
        accountService.signin(account);
        MessageHelper.addSuccessAttribute(ra, "signup.success");
        return "redirect:/";
    }
}
