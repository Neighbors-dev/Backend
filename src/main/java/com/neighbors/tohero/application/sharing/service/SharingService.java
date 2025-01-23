package com.neighbors.tohero.application.sharing.service;

import com.neighbors.tohero.application.baseResponse.BaseResponse;
import com.neighbors.tohero.application.baseResponse.BaseResponseMessage;
import com.neighbors.tohero.application.baseResponse.BaseResponseStatus;
import com.neighbors.tohero.application.sharing.dto.GetRecommenderCodeResponse;
import com.neighbors.tohero.common.jwt.JwtProvider;
import com.neighbors.tohero.domain.domain.user.model.User;
import com.neighbors.tohero.domain.domain.user.service.GetUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SharingService {

    private final GetUser getUser;
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
