package com.neighbors.tohero.application.address.service;

import com.neighbors.tohero.application.address.dto.SearchAddressRequest;
import com.neighbors.tohero.application.address.dto.SearchAddressResponse;
import com.neighbors.tohero.application.baseResponse.BaseResponse;
import com.neighbors.tohero.application.baseResponse.BaseResponseMessage;
import com.neighbors.tohero.application.baseResponse.BaseResponseStatus;
import com.neighbors.tohero.common.enums.TargetJob;
import com.neighbors.tohero.common.exception.address.AddressException;
import com.neighbors.tohero.domain.domain.address.model.Address;
import com.neighbors.tohero.domain.domain.address.service.GetAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final GetAddress getAddress;

    public BaseResponse<SearchAddressResponse> searchAddress(SearchAddressRequest searchAddressRequest) {

        List<Address> searchedAddresses = getAddressByQuery(searchAddressRequest.searchAddress().trim(), searchAddressRequest.targetJob());
        if(searchedAddresses.isEmpty()) {
            throw new AddressException(BaseResponseStatus.NO_RESULT, BaseResponseMessage.일치하는_관할서_정보가_없습니다.getMessage());
        }

        return new BaseResponse(
                BaseResponseStatus.OK,
                BaseResponseMessage.주소_검색이_성공적으로_응답되었습니다.getMessage(),
                SearchAddressResponse.from(searchedAddresses)
        );
    }

    private List<Address> getAddressByQuery(String officeName, TargetJob targetJob) {
        List<Address> searchedAddressesByOfficeName = getAddress.searchAddressByOfficeName(officeName, targetJob);
        List<Address> searchedAddressesByRoadAddress = getAddress.searchAddressByRoadAddress(officeName, targetJob);

        return Stream.concat(searchedAddressesByOfficeName.stream(), searchedAddressesByRoadAddress.stream())
                .collect(Collectors.toMap(
                        Address::getAddressId,
                        address -> address,
                        (existing, replacement) -> existing
                ))
                .values()
                .stream()
                .toList();
    }
}
