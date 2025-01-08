package com.neighbors.tohero.presentation.controller;

import com.neighbors.tohero.application.baseResponse.BaseResponse;
import com.neighbors.tohero.application.user.dto.UpdateUserName;
import com.neighbors.tohero.application.user.service.UserService;
import com.neighbors.tohero.common.jwt.JwtUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class UserController {

    private final UserService userService;

    @Operation(summary = "유저 API", description = "유저 이름 변경 API입니다.")
    @PutMapping("/user/name")
    public ResponseEntity<BaseResponse> updateUserName(
            @Parameter(hidden=true) @AuthenticationPrincipal JwtUserDetails jwtUserDetail,
            @ParameterObject @Validated  UpdateUserName updateUserName
    ){
        return ResponseEntity.ok()
                .body(userService.updateUserName(jwtUserDetail.getUserId(), updateUserName.nickname()));
    }
}
