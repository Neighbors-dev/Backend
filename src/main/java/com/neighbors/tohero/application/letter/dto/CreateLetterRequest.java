package com.neighbors.tohero.application.letter.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neighbors.tohero.common.enums.TargetJob;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CreateLetterRequest (
    @NotBlank
    @Length(min =1, max = 1000)
    String content,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    TargetJob targetJob,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    long addressId,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Length(min = 1, max = 100)
    String heroName
){
}
