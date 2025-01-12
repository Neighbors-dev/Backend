package com.neighbors.tohero.infrastructure.repository;

import com.neighbors.tohero.infrastructure.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsEntityRepository extends JpaRepository<NewsEntity, Long> {
}
