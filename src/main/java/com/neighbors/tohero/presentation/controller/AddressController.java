package com.neighbors.tohero.presentation.controller;

import com.neighbors.tohero.application.address.dto.SearchAddressRequest;
import com.neighbors.tohero.application.address.service.AddressService;
import com.neighbors.tohero.application.baseResponse.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "주소 API", description = "주소 조회를 위해 사용되는 API입니다. 경찰서 기준으로 검색하고 싶으면 TargetJob 부분에 POLICE_OFFICER, 소방서 기준으로 검색하고 싶으면 FIRE_FIGHTER 로 입력해주시면 됩니다.")
    @GetMapping("/address")
    public ResponseEntity<BaseResponse> searchAddress(@ParameterObject @Validated SearchAddressRequest searchAddressRequest) {
        return ResponseEntity.ok()
                .body(addressService.searchAddress(searchAddressRequest));
    }
}
