package com.hoc.training.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoc.training.entity.Country;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    @PostMapping
    public String createCoutnry() {

        Country country = new Country();
        country.setCode("ID");
        country.setId(1L);
        country.setName("Indonesia");

        return "Country " + country.getName() + " successfully created!";
    }
}
