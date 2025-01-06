package com.neighbors.tohero.domain.domain.address.service;

import com.neighbors.tohero.common.annotaion.DomainService;
import com.neighbors.tohero.domain.domain.address.model.Address;
import com.neighbors.tohero.domain.query.AddressRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainService
@RequiredArgsConstructor
public class GetAddress {

    private final AddressRepository addressRepository;

    public List<Address> searchAddressByPath(String queryPath){
        return addressRepository.searchAddressByPath(queryPath);
    }
}
