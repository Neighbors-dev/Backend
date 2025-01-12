package com.neighbors.tohero.application.letter.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateLetterPublic(
    @NotNull
    @Max(Long.MAX_VALUE)
    @Min(0)
    long letterId,

    @NotNull
    boolean isPublic
) {
}
