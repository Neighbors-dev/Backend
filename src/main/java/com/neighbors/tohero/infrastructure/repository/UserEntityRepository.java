package com.neighbors.tohero.infrastructure.repository;

import com.neighbors.tohero.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUserId(long userId);

    @Query("SELECT ue FROM UserEntity ue JOIN FETCH ue.recommendEntity WHERE ue.userId=:userId")
    Optional<UserEntity> findNameOfWritersByUserId(long userId);
}
