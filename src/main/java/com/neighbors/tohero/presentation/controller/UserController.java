package com.neighbors.tohero.presentation.controller;

import com.neighbors.tohero.application.baseResponse.BaseResponse;
import com.neighbors.tohero.application.user.dto.AuthenticateUserRequest;
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
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "유저 API", description = "사용자 인증 API입니다.")
    @PostMapping("/user/auth")
    public ResponseEntity<BaseResponse> authenticateUser(@RequestBody @Validated AuthenticateUserRequest authenticateUserRequest){
        return ResponseEntity.ok()
                .body(userService.authenticateUser(authenticateUserRequest));
    }
}
