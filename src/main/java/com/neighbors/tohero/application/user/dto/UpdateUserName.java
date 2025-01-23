package com.neighbors.tohero.application.user.dto;

import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record UpdateUserName(
        @Length(min = 1, max = 5)
        @Pattern(regexp = "^[^,]*$")
        String nickname
) {
}
