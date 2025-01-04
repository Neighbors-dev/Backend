package com.neighbors.tohero.application.mainPage.dto;

import com.neighbors.tohero.domain.domain.mainPage.model.Letter;

public record OpenedLetter(
        String to,
        String from,
        String content
){
    public static OpenedLetter from(Letter letter){
        return new OpenedLetter(letter.getTargetName(), letter.getFromUserName(), letter.getLetterContent());
    }
}
