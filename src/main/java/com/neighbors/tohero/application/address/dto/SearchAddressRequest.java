package com.neighbors.tohero.application.address.dto;

import com.neighbors.tohero.common.enums.TargetJob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record SearchAddressRequest (
        @Length(min = 1, max = 50)
        @NotBlank
        String searchAddress,

        @NotNull
        TargetJob targetJob
){
}
