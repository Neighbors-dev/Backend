package com.neighbors.tohero.application.user.dto;

import org.hibernate.validator.constraints.Length;

public record UpdateUserName(
        @Length(min = 1, max = 5)
        String nickname
) {
}
