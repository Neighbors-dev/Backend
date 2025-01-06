package com.neighbors.tohero.application.address.dto;

import org.hibernate.validator.constraints.Length;

public record SearchAddressRequest (
        @Length(min = 1, max = 50)
        String searchAddress
){
}
