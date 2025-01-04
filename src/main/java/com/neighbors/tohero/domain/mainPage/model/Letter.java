package com.neighbors.tohero.domain.mainPage.model;

import lombok.Getter;

@Getter
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
}
