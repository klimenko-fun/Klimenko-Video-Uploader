package io.github.klimenko.videouploader.youtube.utils;

/**
 * A Enum for the different video visibility statues that Youtube have
 */
public enum VisibilityStatus {
    PUBLIC("public"),
    PRIVATE("private"),
    UNLISTED("unlisted");

    private final String status;

    VisibilityStatus(String status) {
        this.status = status;
    }

    public String getStatusName() {
        return status;
    }
}

