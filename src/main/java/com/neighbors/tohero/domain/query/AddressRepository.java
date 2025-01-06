package com.neighbors.tohero.domain.query;

import com.neighbors.tohero.domain.domain.address.model.Address;

import java.util.List;

public interface AddressRepository {
    List<Address> searchAddressByPath(String queryPath);
    List<Address> searchAddressByRoadAddress(String queryRoadAddress);
}
