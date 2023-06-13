package com.example.pariwisata;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ValueData<T> {
    @SerializedName("success")
    private int success;

    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private T data;

    public int getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
