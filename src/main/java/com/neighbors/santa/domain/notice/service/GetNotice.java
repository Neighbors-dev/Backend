package com.neighbors.santa.domain.notice.service;

import com.neighbors.santa.common.annotaion.DomainService;
import com.neighbors.santa.domain.notice.model.Notice;
import com.neighbors.santa.domain.notice.query.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@DomainService
@RequiredArgsConstructor
public class GetNotice {

    private final NoticeRepository noticeRepository;

    public List<Notice> getPagedNotice(Pageable pageable) {
        return noticeRepository.getPagedNotice(pageable);
    }
}
