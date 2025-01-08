package com.neighbors.tohero.application.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neighbors.tohero.common.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record AuthenticateUserRequest (
        @NotNull
        Role role,

        @NotBlank
        @Length(min = 1, max = 5)
        String nickname,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @Email
        String email
){
}
