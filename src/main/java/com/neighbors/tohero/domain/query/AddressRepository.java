package com.neighbors.tohero.domain.query;

import com.neighbors.tohero.domain.domain.address.model.Address;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository {
    List<Address> searchAddressByPath(String queryPath);
}
