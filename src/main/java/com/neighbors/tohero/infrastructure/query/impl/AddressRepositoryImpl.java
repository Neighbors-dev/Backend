package com.neighbors.tohero.infrastructure.query.impl;

import com.neighbors.tohero.application.baseResponse.BaseResponseMessage;
import com.neighbors.tohero.application.baseResponse.BaseResponseStatus;
import com.neighbors.tohero.common.enums.TargetJob;
import com.neighbors.tohero.common.exception.address.AddressException;
import com.neighbors.tohero.domain.domain.address.model.Address;
import com.neighbors.tohero.domain.query.AddressRepository;
import com.neighbors.tohero.infrastructure.entity.AddressEntity;
import com.neighbors.tohero.infrastructure.mapper.AddressMapper;
import com.neighbors.tohero.infrastructure.repository.AddressEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AddressRepositoryImpl implements AddressRepository {

    private final AddressEntityRepository addressEntityRepository;
    private final AddressMapper addressMapper;

    @Override
    public List<Address> searchAddressByOfficeName(String queryPath, TargetJob targetJob) {
        List<AddressEntity> addressEntities = addressEntityRepository.findAllContainsOfficeName(queryPath,targetJob);

        return addressEntities.stream()
                .map(addressMapper::toDomain)
                .toList();
    }

    @Override
    public List<Address> searchAddressByRoadAddress(String queryRoadAddress, TargetJob targetJob) {
        List<AddressEntity> addressEntities = addressEntityRepository.findAllContainsRoadAddress(queryRoadAddress,targetJob);

        return addressEntities.stream()
                .map(addressMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existAddressById(long addressId) {
        return addressEntityRepository.existsById(addressId);
    }

    @Override
    public Address getAddressById(long addressId) {
        AddressEntity addressEntity = addressEntityRepository.findById(addressId)
                .orElseThrow(() -> new AddressException(
                        BaseResponseStatus.NO_RESULT,
                        BaseResponseMessage.일치하는_관할서_정보가_없습니다.getMessage()
                ));

        return addressMapper.toDomain(addressEntity);
    }
}
