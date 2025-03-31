package com.hoc.training.dto;

import org.springframework.validation.annotation.Validated;

import com.hoc.training.service.CreateCityValidation;
import com.hoc.training.service.UpdateCityValidation;
import com.hoc.training.validation.Palindrome;
import com.hoc.training.validation.SameWord;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class CityDTO {

    @NotBlank(message = "Code is required")
    private String code;

    @NotBlank(message = "Name is rquired")
    private String name;

    @Palindrome
    private String reverseName;

    @SameWord
    private String sameWord;

    @NotBlank(message = "New name is required", groups = CreateCityValidation.class)
    @Size(min = 3, message = "New name can not less than 3 characters", groups = UpdateCityValidation.class)
    private String newName;
}
