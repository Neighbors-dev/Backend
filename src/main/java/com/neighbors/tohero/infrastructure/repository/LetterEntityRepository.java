package com.neighbors.tohero.infrastructure.repository;

import com.neighbors.tohero.infrastructure.entity.LetterEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LetterEntityRepository extends JpaRepository<LetterEntity, Long> {

    @Query("SELECT le FROM LetterEntity le WHERE le.isPublic = true")
    Slice<LetterEntity> findPagedLetterEntity(Pageable pageable);

    @Query("SELECT COUNT(le) FROM LetterEntity le WHERE le.isPublic = true")
    long countPublicLetter();

    @Query("SELECT le FROM LetterEntity le WHERE le.user.userId = :userId")
    List<LetterEntity> findAllByUserId(@Param("userId") Long userId);

    @Query("SELECT le FROM LetterEntity le WHERE le.letterId = :letterId AND le.isPublic = true")
    Optional<LetterEntity> findByIdAndPublic(@Param("letterId") long letterId);
}
