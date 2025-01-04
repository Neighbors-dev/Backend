package com.neighbors.tohero.domain.query;

import com.neighbors.tohero.domain.domain.notice.model.Notice;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoticeRepository {
    List<Notice> getPagedNotice(Pageable pageable);
}
