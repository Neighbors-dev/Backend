package com.neighbors.tohero.application.letter.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neighbors.tohero.common.enums.TargetJob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CreateLetterRequest (
    @NotBlank
    @Length(min =1, max = 500)
    String content,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    TargetJob targetJob,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    Long addressId,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Length(min = 1, max = 100)
    String heroName,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    Boolean readingAlarm,

    @NotNull
    boolean isPublic,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String recommenderCode
){
}
