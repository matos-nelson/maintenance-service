package org.rent.circle.maintenance.api.enums;

public enum Status {
    IN_PROGRESS("IN_PROGRESS"),
    REJECTED("REJECTED"),
    COMPLETED("COMPLETED");

    public final String value;

    Status(String value) {
        this.value = value;
    }
}
