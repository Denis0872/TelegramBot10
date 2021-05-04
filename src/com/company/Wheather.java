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
    public static  String getWeather(String message, Model model) throws IOException {


        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+message+"&units=metric&APPID=6a6d12380407ba0a506a3fa4567947f5");
        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext()) {
            result += in.nextLine();
        }
        JSONObject object=new JSONObject(result);
        model.setName(object.getString("name"));
        JSONObject main = object.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));
        JSONArray getarray =object.getJSONArray("weather");
        for (int i=0;i<getarray.length(); i++){
        JSONObject object1 =getarray.getJSONObject(i);
        model.setIcon((String) object1.get("icon"));
        model.setMain((String)object1.get("main"));

        }

        return "Город"+ model.getName()+ "\n"+
        "Температура "+ model.getTemp()+ "\n"+
        "Влажность %"+ model.getHumidity()+ "\n"+
                "Погода "+ model.getMain()+"\n"+
        "https://openweathermap.org/img/w/"+ model.getIcon()+ ".png"+"\n";

    }
}
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String inputline;
//            StringBuffer response = new StringBuffer();
//            while ((inputline = in.readLine()) != null) {
//                response.append(inputline);
//                in.close();
//                return response.toString();
//            }
//        } catch (ProtocolException e) {
//            e.printStackTrace();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
////
////        catch(IOException e){
////                e.printStackTrace();
//        //
//        //     InputStreamReader reader = new InputStreamReader(urlObject.openStream());
//        //     Gson todayWeatherGson = new Gson();
////
//        //     HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
//////
//        //      int responseCode = connection.getResponseCode();
//        //BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//        //      String inputLine;
//////    StringBuffer response = new StringBuffer();
//        //      return wt.toString();
//
  //    return null;
//    }
