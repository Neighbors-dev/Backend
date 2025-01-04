package com.neighbors.tohero.infrastructure.repository;

import com.neighbors.tohero.infrastructure.entity.LetterEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LetterEntityRepository extends JpaRepository<LetterEntity, Long> {

    @Query("SELECT n FROM LetterEntity n JOIN FETCH n.user WHERE n.isPublic = true")
    Slice<LetterEntity> findPagedLetterEntity(Pageable pageable);
}
