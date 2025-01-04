package com.neighbors.tohero.infrastructure.repository;

import com.neighbors.tohero.infrastructure.entity.NoticeEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeEntityRepository extends JpaRepository<NoticeEntity, Long> {

    @Query("SELECT n FROM NoticeEntity n")
    Slice<NoticeEntity> findPagedNoticeEntity(Pageable pageable);

    @Query("SELECT n FROM NoticeEntity n ORDER BY n.created_at DESC LIMIT 5")
    List<NoticeEntity> findTopNoticeEntity(int exposedNoticeNumber);
}
