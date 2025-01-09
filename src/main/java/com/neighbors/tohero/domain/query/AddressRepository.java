package com.neighbors.tohero.domain.query;

import com.neighbors.tohero.common.enums.TargetJob;
import com.neighbors.tohero.domain.domain.address.model.Address;

import java.util.List;

public interface AddressRepository {
    List<Address> searchAddressByOfficeName(String queryPath, TargetJob targetJob);
    List<Address> searchAddressByRoadAddress(String queryRoadAddress, TargetJob targetJob);
    boolean existAddressById(long addressId);
}
