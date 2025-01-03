package com.neighbors.santa.infrastructure.entity;

import com.neighbors.santa.infrastructure.entity.base.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "`Address`")
public class AddressEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private long addressId;

    @Column(name = "branch_office")
    private String branchOffice;

    @Column(name = "road_address")
    private String roadAddress;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "query_path")
    private String queryPath;
}
