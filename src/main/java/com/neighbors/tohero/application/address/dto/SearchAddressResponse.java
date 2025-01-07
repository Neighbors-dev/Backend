package com.neighbors.tohero.application.address.dto;

import com.neighbors.tohero.domain.domain.address.model.Address;

import java.util.List;

public record SearchAddressResponse (
        List<AddressInfo> addressInfos
){
    public record AddressInfo(
            long addressId,
            String officeName,
            String roadAddress
    ) {
        public static AddressInfo from(Address address) {
            return new AddressInfo(address.getAddressId(), address.getOfficeName(), address.getRoadAddress());
        }
    }

    public static SearchAddressResponse from(List<Address> searchedAddresses) {

        List<AddressInfo> addressInfos = searchedAddresses.stream()
                .map(AddressInfo::from)
                .toList();

        return new SearchAddressResponse(addressInfos);
    }
}
