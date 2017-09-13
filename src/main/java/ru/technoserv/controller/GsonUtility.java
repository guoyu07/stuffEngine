package ru.technoserv.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtility {

    private static Gson gson = new GsonBuilder().serializeNulls().create();

    public static <T> String toJson(T object){
        return  gson.toJson(object);
    }
}
