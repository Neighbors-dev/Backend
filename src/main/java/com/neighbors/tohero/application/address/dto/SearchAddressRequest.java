package com.neighbors.tohero.application.address.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record SearchAddressRequest (
        @Length(min = 1, max = 50)
        @NotBlank
        String searchAddress
){
}
