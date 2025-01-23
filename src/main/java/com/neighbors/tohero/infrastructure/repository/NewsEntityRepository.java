package com.neighbors.tohero.infrastructure.repository;

import com.neighbors.tohero.infrastructure.entity.NewsEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NewsEntityRepository extends JpaRepository<NewsEntity, Long> {
    @Query("SELECT ne FROM NewsEntity ne")
    Optional<List<NewsEntity>> findAllByPageable(Pageable pageable);
}
