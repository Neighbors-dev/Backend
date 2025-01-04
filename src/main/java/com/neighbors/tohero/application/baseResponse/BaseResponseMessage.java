package com.neighbors.tohero.application.baseResponse;

import lombok.Getter;

@Getter
public enum BaseResponseMessage {

    //oauth success
    로그인_성공했습니다("로그인 성공했습니다"),

    //notice success
    공지_조회_성공했습니댜("공지 조회 성공했습니댜"),

    //user
    이미_존재하는_유저입니다("이미 존재하는 유저입니다"),
    유저_이름_변경이_완료되었습니다("유저 이름 변경이 완료되었습니다"),
    존재하지_않는_유저입니다("존재하지 않는 유저입니다"),
    유저_이름의_길이는_1부터_5까지만_가능합니다("유저 이름의 길이는 1부터 5까지만 가능합니다"),

    //jwt error message
    JWT_토큰_오류입니다("JWT 토큰 오류입니다"),
    지원하지_않는_토큰_입니다("지원하지 않는 토큰 입니다"),
    토큰이_올바르지_못한_형식입니다("토큰이 올바르지 못한 형식입니다"),
    유효하지_않은_토큰_입니다("유효하지 않은 토큰입니다"),

    //main page
    메인페이지_조회가_정상적으로_실행되었습니다("메인페이지 조회가 정상적으로 실행되었습니다");


    private final String message;

    private BaseResponseMessage(String message) {
        this.message = message;
    }
}
