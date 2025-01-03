package com.neighbors.santa.application.baseResponse;

import lombok.Getter;

@Getter
public enum BaseResponseMessage {

    //oauth success
    로그인_성공했습니다("로그인 성공했습니다"),

    //jwt error message
    JWT_토큰_오류입니다("JWT 토큰 오류입니다"),
    지원하지_않는_토큰_입니다("지원하지 않는 토큰 입니다"),
    토큰이_올바르지_못한_형식입니다("토큰이 올바르지 못한 형식입니다"),
    유효하지_않은_토큰_입니다("유효하지 않은 토큰입니다");

    private final String message;

    private BaseResponseMessage(String message) {
        this.message = message;
    }
}
