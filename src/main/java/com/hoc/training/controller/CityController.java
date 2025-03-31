package com.hoc.training.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoc.training.dto.CityDTO;
import com.hoc.training.service.CreateCityValidation;
import com.hoc.training.service.UpdateCityValidation;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cities")
@Validated
public class CityController {

    @PostMapping
    public String createCity(@RequestBody @Valid CityDTO cityDTO) {

        return "Success";
    }

    @PostMapping("/add")
    public String addNewCity(@RequestBody @Validated(CreateCityValidation.class) CityDTO cityDTO) {

        return "Success";
    }

    @PutMapping
    public String updateNewCity(@RequestBody @Validated(UpdateCityValidation.class) CityDTO cityDTO) {

        return "Success";
    }
}
