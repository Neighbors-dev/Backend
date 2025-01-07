package com.neighbors.tohero.presentation.controller;

import com.neighbors.tohero.application.address.dto.SearchAddressRequest;
import com.neighbors.tohero.application.address.service.AddressService;
import com.neighbors.tohero.application.baseResponse.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/address")
    public ResponseEntity<BaseResponse> searchAddress(@ParameterObject @Validated SearchAddressRequest searchAddressRequest) {
        return ResponseEntity.ok()
                .body(addressService.searchAddress(searchAddressRequest));
    }
}
