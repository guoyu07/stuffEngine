package ru.technoserv.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtility {

    private GsonUtility(){}

    private static Gson gson = new GsonBuilder().serializeNulls().setDateFormat("dd.MM.yyyy").create();

    public static <T> String toJson(T object){
        return  gson.toJson(object);
    }

    public static <T> T toObject(String s, T t){
        return (T)gson.fromJson(s, t.getClass());
    }
}
