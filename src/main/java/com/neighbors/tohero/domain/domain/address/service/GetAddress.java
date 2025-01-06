package com.neighbors.tohero.domain.domain.address.service;

import com.neighbors.tohero.common.annotaion.DomainService;
import com.neighbors.tohero.domain.domain.address.model.Address;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainService
@RequiredArgsConstructor
public class GetAddress {
    public List<Address> searchAddressByPath(String queryPath){
        return null;
    }
}
