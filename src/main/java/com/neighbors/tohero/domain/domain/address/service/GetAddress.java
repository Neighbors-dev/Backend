package com.neighbors.tohero.domain.domain.address.service;

import com.neighbors.tohero.common.annotaion.DomainService;
import com.neighbors.tohero.common.enums.TargetJob;
import com.neighbors.tohero.domain.domain.address.model.Address;
import com.neighbors.tohero.domain.query.AddressRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainService
@RequiredArgsConstructor
public class GetAddress {

    private final AddressRepository addressRepository;

    public List<Address> searchAddressByOfficeName(String queryPath, TargetJob targetJob){
        return addressRepository.searchAddressByOfficeName(queryPath, targetJob);
    }

    public List<Address> searchAddressByRoadAddress(String queryRoadAddress, TargetJob targetJob){
        return addressRepository.searchAddressByRoadAddress(queryRoadAddress, targetJob);
    }
}
