package com.neighbors.tohero.application.address.service;

import com.neighbors.tohero.application.address.dto.SearchAddressRequest;
import com.neighbors.tohero.application.address.dto.SearchAddressResponse;
import com.neighbors.tohero.application.baseResponse.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    public BaseResponse<SearchAddressResponse> searchAddress(SearchAddressRequest searchAddressRequest) {
        return null;
    }
}
