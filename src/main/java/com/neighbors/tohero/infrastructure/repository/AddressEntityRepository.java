package com.neighbors.tohero.infrastructure.repository;

import com.neighbors.tohero.common.enums.TargetJob;
import com.neighbors.tohero.infrastructure.entity.AddressEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressEntityRepository extends JpaRepository<AddressEntity, Long> {

    @Query("SELECT a FROM AddressEntity a WHERE a.queryPath LIKE %:queryPath% AND a.targetJob = :targetJob")
    List<AddressEntity> findAllContainsOfficeName(
            @Param("queryPath") String queryPath,
            @Param("targetJob") TargetJob targetJob
    );

    @Query("SELECT a FROM AddressEntity a WHERE a.roadAddress LIKE %:queryRoadAddress% AND a.targetJob = :targetJob")
    List<AddressEntity> findAllContainsRoadAddress(
            @Param("queryRoadAddress") String queryRoadAddress,
            @Param("targetJob") TargetJob targetJob
    );
}
