package com.neighbors.tohero.infrastructure.entity;

import com.neighbors.tohero.common.enums.TargetJob;
import com.neighbors.tohero.infrastructure.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "`Address`")
@Getter
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

    @Column(name = "target_job")
    @Enumerated(value = EnumType.ORDINAL)
    private TargetJob targetJob;
}
