package com.neighbors.tohero.application.mainPage.dto;

import java.util.List;

public record GetMainPageLetterInfoResponse (
        List<OpenedLetter> openedLetters
){
}
