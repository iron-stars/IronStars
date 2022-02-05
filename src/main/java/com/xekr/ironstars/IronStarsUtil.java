package com.xekr.ironstars;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Random;

public class IronStarsUtil {
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static final Random RANDOM = new Random();
}
