package com.example.restservice.external.numberfinder.entity;

public enum Code {
    OK("00.Result.OK", 0),
    NOT_FOUND("01.Result.NotFound", 1),
    ERROR("02.Result.Error", 2);
    private final String type;
    private final int id;

    Code(String type, int id) {
        this.type = type;
        this.id = id;
    }

    public static Code valueOf(final Integer position) {
        return position > 0 ? OK : position < 0 ? NOT_FOUND : ERROR;
    }

    public String asString() {
        return this.type;
    }

    public int asInt() {
        return this.id;
    }
}
