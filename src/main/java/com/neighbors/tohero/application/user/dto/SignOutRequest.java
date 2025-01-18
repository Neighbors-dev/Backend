package com.neighbors.tohero.application.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record SignOutRequest(
        @NotBlank
        @Length(min = 1, max = 30)
        String reasonCategory,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @Length(min = 1, max = 500)
        String opinionForService
) {
}
