package com.neighbors.tohero.infrastructure.repository;

import com.neighbors.tohero.infrastructure.entity.LetterEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LetterEntityRepository extends JpaRepository<LetterEntity, Long> {

    @Query("SELECT le FROM LetterEntity le WHERE le.isPublic = true")
    Optional<List<LetterEntity>> findPagedLetterEntity(Pageable pageable);

    @Query("SELECT COUNT(le) FROM LetterEntity le")
    long countAllLetter();

    @Query("SELECT le FROM LetterEntity le WHERE le.user.userId = :userId")
    Optional<List<LetterEntity>> findAllByUserId(@Param("userId") Long userId);

    @Query("SELECT le FROM LetterEntity le WHERE le.letterId = :letterId")
    Optional<LetterEntity> findById(@Param("letterId") long letterId);

    @Modifying
    @Query("UPDATE LetterEntity le SET le.isPublic = :isPublic WHERE le.user.userId = :userId AND le.letterId = :letterId")
    void updateLetterPublic(
            @Param("userId") long userId,
            @Param("letterId") long letterId,
            @Param("isPublic") boolean isPublic
    );

    @Modifying
    @Query("DELETE FROM LetterEntity le WHERE le.user.userId = :userId AND le.letterId = :letterId")
    void deleteByUserIdAndLetterId(
            @Param("userId") long userId,
            @Param("letterId") long letterId
    );
}
