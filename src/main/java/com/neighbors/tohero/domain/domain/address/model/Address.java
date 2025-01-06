package com.neighbors.tohero.domain.domain.address.model;

import lombok.Getter;

@Getter
public class Address {
    private long addressId;
    private String officeName;
    private String roadAddress;
    private String phoneNumber;
    private String queryPath;
}
