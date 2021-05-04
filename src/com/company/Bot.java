package com.company;

import org.checkerframework.checker.units.qual.A;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    private String chatId;

    public void sendMessage(Message mes, String text) {
        SendMessage smg = new SendMessage();
        smg.enableMarkdown(false);
        smg.setChatId(mes.getChatId().toString());
        smg.setReplyToMessageId(mes.getMessageId());
        smg.setText(text);
       try {
            setButtons(smg);
           sendMessage(smg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "Wheather_Petergof_bot";
    }

    @Override
    public String getBotToken() {
        return "1679751960:AAFQb-XP8Sy8LDfUrB1wZgwy2-uw8M3mGik";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Location loc =new Location();
        Model model= new Model();
        Message message = update.getMessage();
        message.getLocation();

        if (message != null && message.hasText()) {
          //  if(message.hasLocation())

            switch (message.getText()) {
//                case "/help":
//                    sendMessage(message, "Чем могу помочь?");
//                    break;
                case "/location":
                    double lat = 0;
                    double lon = 0;
                    lat = update.getMessage().getLocation().getLatitude();
                    lon = update.getMessage().getLocation().getLongitude();
                    if (update.getMessage().hasLocation()){
                        SendMessage msg = new SendMessage();
                        String lat1 = Double.toString(lat);
                        String lon1 = Double.toString(lon);
                        sendMessage(message, lat1+""+lon1);
                        try {

                            execute(msg);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case "/start":
                    sendMessage(message, "Приветсвую в чатботе!" );
                    break;
//                case "/city":
//                        sendMessage(message, "Какой город ищем?");
//                break;
                default:
                    try{
                        sendMessage(message, Wheather.getWeather(message.getText(), model));
                    }
                    catch (IOException e){
                     sendMessage(message, "Такого города нет");
                    }
                    break;
            }
        }
    }
   // @Deprecated
    public  Message sendMessage(SendMessage sendMessage) throws TelegramApiException{
            if (sendMessage==null)
            {throw new TelegramApiException("rrr"); }
        else
            return sendApiMethod(sendMessage);
        }

    public void setButtons(SendMessage sndmes) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sndmes.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardRow1 = new KeyboardRow();
        KeyboardRow keyboardRow2 = new KeyboardRow();

        List<KeyboardButton> keyboardButtons=new ArrayList<>();
        KeyboardButton keyboardButtons1=new KeyboardButton();
        KeyboardButton keyboardButtons2=new KeyboardButton();
        keyboardButtons.add(keyboardButtons1);
        keyboardButtons.add(keyboardButtons2);
        keyboardButtons1.setText("/start");
        keyboardButtons2.setText("/location");
        keyboardButtons2.getRequestLocation();
        keyboardButtons2.setRequestLocation(true);
        keyboardRow1.add(keyboardButtons1);
        keyboardRow2.add(keyboardButtons2);
//        keyboardButtons1.add("/start");
//        keyboardButtons1.add("/help");
      // keyboardButtons2.setText("/subscribe");
//        keyboardButtons2.getRequestLocation();
//        keyboardButtons2.setRequestLocation(true);
//        keyboardButtons2.add("/city");
        keyboardRowList.add(keyboardRow1);
        keyboardRowList.add(keyboardRow2);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);

    }
    public static Object inputloc(String chatId,double lat, double lon)
    {
        String lat1 = Double.toString(lat);
        String lon2 = Double.toString(lon);
        return "Ваши координаты: " + lat1 + " " + lon2;

    }


}
