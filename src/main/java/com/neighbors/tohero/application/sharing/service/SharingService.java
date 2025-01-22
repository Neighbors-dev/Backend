package com.neighbors.tohero.application.sharing.service;

import com.neighbors.tohero.application.baseResponse.BaseResponse;
import com.neighbors.tohero.application.sharing.dto.GetRecommenderCodeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SharingService {

    public BaseResponse<GetRecommenderCodeResponse> getRecommenderCode(long userId){
        return null;
    }

}
