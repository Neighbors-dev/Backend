package com.neighbors.tohero.application.sharing.dto;

import java.util.List;

public record GetSharingPageInfoResponse(
        boolean isFirst,
        int numberOfWriter,
        List<String> nameOfWriters
) {
}
