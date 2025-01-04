package com.neighbors.tohero.domain.domain.mainPage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Letter {
    private long letterId;
    private String letterContent;
    private boolean isOpened;
    private String fromUserName;
    private String targetName;

    public String getTargetName(){
        if(targetName == null) {
            return "익명의";
        }
        return targetName;
    }

    public static Letter of(long letterId, String letterContent, boolean isOpened, String fromUserName, String targetName) {
        return new Letter(letterId, letterContent, isOpened, fromUserName, targetName);
    }
}
