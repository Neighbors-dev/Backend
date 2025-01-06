package com.neighbors.tohero.infrastructure.repository;

import com.neighbors.tohero.infrastructure.entity.AddressEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressEntityRepository extends JpaRepository<AddressEntity, Long> {

    @Query("SELECT a FROM AddressEntity a WHERE a.queryPath LIKE %:queryPath%")
    List<AddressEntity> findAllContainsPath(@Param("queryPath") String queryPath);

    @Query("SELECT a FROM AddressEntity a WHERE a.roadAddress LIKE %:queryRoadAddress%")
    List<AddressEntity> findAllContainsRoadAddress(@Param("queryRoadAddress") String queryRoadAddress);
}
