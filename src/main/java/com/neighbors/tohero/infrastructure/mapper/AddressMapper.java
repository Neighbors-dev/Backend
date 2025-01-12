package com.neighbors.tohero.infrastructure.mapper;

import com.neighbors.tohero.domain.domain.address.model.Address;
import com.neighbors.tohero.infrastructure.entity.AddressEntity;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    public Address toDomain(AddressEntity addressEntity) {
        return Address.of(
                addressEntity.getAddressId(),
                addressEntity.getBranchOffice(),
                addressEntity.getRoadAddress(),
                addressEntity.getPhoneNumber(),
                addressEntity.getQueryPath(),
                addressEntity.getTargetJob()
        );
    }

    public AddressEntity toEntity(Address address) {
        if(address == null) {
            return null;
        }
        return AddressEntity.of(
                address.getAddressId(),
                address.getOfficeName(),
                address.getRoadAddress(),
                address.getPhoneNumber(),
                address.getQueryPath(),
                address.getTargetJob()
        );
    }
}
