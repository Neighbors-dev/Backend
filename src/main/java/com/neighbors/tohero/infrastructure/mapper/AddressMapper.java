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
                addressEntity.getQueryPath()
        );
    }
}
