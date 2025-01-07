package com.neighbors.tohero.domain.domain.address.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Address {
    private long addressId;
    private String officeName;
    private String roadAddress;
    private String phoneNumber;
    private String queryPath;

    public static Address of(long addressId, String officeName, String roadAddress, String phoneNumber, String queryPath){
        return new Address(addressId, officeName, roadAddress, phoneNumber, queryPath);
    }
}
