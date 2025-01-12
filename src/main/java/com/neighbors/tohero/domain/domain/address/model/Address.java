package com.neighbors.tohero.domain.domain.address.model;

import com.neighbors.tohero.common.enums.TargetJob;
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
    private TargetJob targetJob;

    public static Address of(long addressId, String officeName, String roadAddress, String phoneNumber, String queryPath, TargetJob targetJob){
        return new Address(addressId, officeName, roadAddress, phoneNumber, queryPath, targetJob);
    }
}
