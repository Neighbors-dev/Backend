package com.neighbors.tohero.infrastructure.repository;

import com.neighbors.tohero.infrastructure.entity.UserOpinionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOpinionEntityRepository extends JpaRepository<UserOpinionEntity, Long> {
}
