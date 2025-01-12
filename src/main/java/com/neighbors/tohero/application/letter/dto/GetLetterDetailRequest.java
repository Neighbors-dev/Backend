package com.neighbors.tohero.application.letter.dto;

import jakarta.validation.constraints.NotNull;

public record GetLetterDetailRequest(
        @NotNull
        long letterId
) {
}
