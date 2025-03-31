package com.hoc.training.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoc.training.entity.Province;

@RestController
@RequestMapping("/api/provinces")
public class ProvinceController {

    @PostMapping
    public String createProvince() {

        Province data = new Province(1L, "IKN", "Nusantara");

        return "Province " + data.getName() + " successfully created!";
    }
}
