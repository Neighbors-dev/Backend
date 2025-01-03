package com.neighbors.santa.domain.notice.query;

import com.neighbors.santa.domain.notice.model.Notice;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoticeRepository {
    List<Notice> getPagedNotice(Pageable pageable);
}
