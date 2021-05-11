package com.company;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javassist.bytecode.stackmap.BasicBlock;
import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.telegrambots.meta.TelegramBotsApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;


public class Wheather {
    public static String getWeather(String message, Model model) throws IOException {


        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&APPID=6a6d12380407ba0a506a3fa4567947f5");
        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext()) {
            result += in.nextLine();
        }
        JSONObject object = new JSONObject(result);
        model.setName(object.getString("name"));
        JSONObject main = object.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));
        JSONArray getarray = object.getJSONArray("weather");
        for (int i = 0; i < getarray.length(); i++) {
            JSONObject object1 = getarray.getJSONObject(i);
            model.setIcon((String) object1.get("icon"));
            model.setMain((String) object1.get("main"));

        }

        return "Город" + model.getName() + "\n" +
                "Температура " + model.getTemp() + "\n" +
                "Влажность %" + model.getHumidity() + "\n" +
                "Погода " + model.getMain() + "\n" +
                "https://openweathermap.org/img/w/" + model.getIcon() + ".png" + "\n";

    }

    public static String getWeather1(String message, Model model) throws IOException {
        URL url1 = new URL("http://api.openweathermap.org/data/2.5/weather?" + message + "&units=metric&APPID=6a6d12380407ba0a506a3fa4567947f5");
        //api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}
        Scanner in1 = new Scanner((InputStream) url1.getContent());
        String result1 = "";
        while (in1.hasNext()) {
            result1 += in1.nextLine();
        }
        JSONObject object1 = new JSONObject(result1);
        model.setName(object1.getString("name"));
        JSONObject main = object1.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));
        JSONArray getarray = object1.getJSONArray("weather");
        for (int i = 0; i < getarray.length(); i++) {
            JSONObject object2 = getarray.getJSONObject(i);
            model.setIcon((String) object2.get("icon"));
            model.setMain((String) object2.get("main"));
        }
        return "Город" + model.getName() + "\n" +
                "Температура " + model.getTemp() + "\n" +
                "Влажность %" + model.getHumidity() + "\n" +
                "Погода " + model.getMain() + "\n" +
                "https://openweathermap.org/img/w/" + model.getIcon() + ".png" + "\n";

    }
}

