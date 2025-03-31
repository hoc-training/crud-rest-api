package com.hoc.training.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.hoc.training.helper.StringHelper;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SameValidator implements ConstraintValidator<SameWord, String> {

    @Autowired
    StringHelper stringHelper;

    @Override
    public boolean isValid(String arg0, ConstraintValidatorContext arg1) {

        return stringHelper.isSame(arg0.split(" ")[0].toLowerCase(), arg0.split(" ")[1].toLowerCase());
    }

}
