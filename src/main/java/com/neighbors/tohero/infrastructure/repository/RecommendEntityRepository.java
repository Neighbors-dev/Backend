package com.neighbors.tohero.infrastructure.repository;

import com.neighbors.tohero.infrastructure.entity.RecommendEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendEntityRepository extends JpaRepository<RecommendEntity, Long> {
    @Query("SELECT re FROM RecommendEntity re WHERE re.userEntity.email IN :emails")
    List<RecommendEntity> findAllByUserEmailIn(@Param("emails") List<String> emails);
}
