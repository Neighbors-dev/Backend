package com.neighbors.tohero.application.sharing.service;

import com.neighbors.tohero.application.baseResponse.BaseResponse;
import com.neighbors.tohero.application.baseResponse.BaseResponseMessage;
import com.neighbors.tohero.application.baseResponse.BaseResponseStatus;
import com.neighbors.tohero.application.sharing.dto.GetRecommenderCodeResponse;
import com.neighbors.tohero.application.sharing.dto.GetSharingPageInfoResponse;
import com.neighbors.tohero.common.jwt.JwtProvider;
import com.neighbors.tohero.domain.domain.user.model.User;
import com.neighbors.tohero.domain.domain.user.service.GetUser;
import com.neighbors.tohero.domain.domain.user.service.UpdateUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SharingService {

    private final GetUser getUser;
    private final UpdateUser updateUser;
    private final JwtProvider jwtProvider;

    public BaseResponse<GetRecommenderCodeResponse> getRecommenderCode(String userEmail){
        User user = getUser.getUserByEmail(userEmail);

        String recommenderCode = createRecommenderCode(user);

        return new BaseResponse<>(
                BaseResponseStatus.OK,
                BaseResponseMessage.추천인_코드_생성이_정상실행_되었습니다.getMessage(),
                new GetRecommenderCodeResponse(recommenderCode)
        );
    }

    public BaseResponse<GetSharingPageInfoResponse> getSharingPageInfo(long userId){
        User user = getUser.getUserForSharing(userId);
        List<String> recommendedPeopleName = user.getNameOfRecommendedWriter();

        if(recommendedPeopleName.size() == 1 && Objects.equals(recommendedPeopleName.get(0), "")){
            recommendedPeopleName = new ArrayList<>();
        }

        return new BaseResponse<>(
                BaseResponseStatus.OK,
                BaseResponseMessage.공유하기_페이지_조회가_성공했습니다.getMessage(),
                new GetSharingPageInfoResponse(user.isFirstSharing(), recommendedPeopleName.size(), recommendedPeopleName)
        );
    }

    private String createRecommenderCode(User user){
        String recommenderEmailsDividedBySlash = user.getRecommenders();
        List<String> recommenderEmails = new java.util.ArrayList<>(List.of(recommenderEmailsDividedBySlash.split("/")));
        recommenderEmails.add(user.getEmail());

        if (recommenderEmails.size() > 5) {
            recommenderEmails.remove(0);
        }

        String recommenderEmailsWithUserEmailDividedBySlash =
                String.join("/", recommenderEmails)
                .replaceAll("^/+", "");

        return jwtProvider.createRecommenderCode(user.getEmail(), recommenderEmailsWithUserEmailDividedBySlash);
    }

}
