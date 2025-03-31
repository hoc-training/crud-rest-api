package com.hoc.training.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.hoc.training.helper.StringHelper;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PalindromeValidator implements ConstraintValidator<Palindrome, String> {

    @Autowired
    private StringHelper stringHelper;

    @Override
    public boolean isValid(String arg0, ConstraintValidatorContext arg1) {

        return stringHelper.isPalindrome(arg0);
    }
}
