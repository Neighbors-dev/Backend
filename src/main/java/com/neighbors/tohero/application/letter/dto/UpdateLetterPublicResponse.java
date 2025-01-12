package com.neighbors.tohero.application.letter.dto;

public record UpdateLetterPublicResponse (
        boolean before,
        boolean after
){

    public static UpdateLetterPublicResponse of(boolean after) {
        return new UpdateLetterPublicResponse(!after, after);
    }
}
