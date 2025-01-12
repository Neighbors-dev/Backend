package com.neighbors.tohero.infrastructure.entity;

import com.neighbors.tohero.common.enums.TargetJob;
import com.neighbors.tohero.infrastructure.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`Address`")
@Getter
@AllArgsConstructor
@NoArgsConstructor
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

    public static AddressEntity of(long addressId, String branchOffice, String roadAddress, String phoneNumber, String queryPath, TargetJob targetJob) {
        return new AddressEntity(addressId, branchOffice, roadAddress, phoneNumber, queryPath, targetJob);
    }
}
