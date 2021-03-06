package com.eguller.zpc.signup;

import com.eguller.zpc.account.AccountRepository;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validates whether given email adress already exists in the system or not.
 * @author eguller
 */
@Component
class EmailExistsValidator implements ConstraintValidator<EmailExists, String> {

    private final AccountRepository accountRepository;

    public EmailExistsValidator(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public void initialize(EmailExists constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !accountRepository.exists(value);
    }
}
