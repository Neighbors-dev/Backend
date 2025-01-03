package com.neighbors.tohero.infrastructure.repository;

import com.neighbors.tohero.infrastructure.entity.NoticeEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeEntityRepository extends JpaRepository<NoticeEntity, Long> {

    @Query("SELECT n FROM NoticeEntity n")
    Slice<NoticeEntity> findPagedNoticeEntity(Pageable pageable);
}
