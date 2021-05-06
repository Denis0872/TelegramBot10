package com.company;

import org.checkerframework.checker.units.qual.A;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    public String chatId;
    public String uName;
    public String mytime;
    public String message;
    public String uSecondName;
    public String uNick;
    private double lat;
    private double lon;


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
                    this.lat = 1.1;
                    this.lon = 1.2;
                    this.chatId = "1";//update.getMessage().getChatId().toString();
                    //User user = update.getMessage().getFrom();
                    this.uName = "Вася";//user.getFirstName();
                    this.uSecondName = "Петров" ;// user.getLastName();
                    this.uNick = "волк";//user.getUserName();
                    update.getMessage().hasLocation();
                    sendMessage(message, "Ваша локация:"+lat+" "+lon );
                    break;
                case "/start":
                    sendMessage(message, "Привет!!! Это Телеграм Бот" );
                    break;
                case "/subscribe":
                     final String url = "jdbc:mysql://localhost:3306/";
                     final String usr = "root";
                     final String password = "12345";
                     Connection con = null;
                    Statement stmt = null;
                    boolean rs = true;
                    try {
                        con = DriverManager.getConnection(url, usr, password);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    try {
                        stmt = con.createStatement();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                    String insert2= "CREATE TABLE IF NOT EXISTS `telegram`.`bot` (\n" +
                            "  `chatId` INT NOT NULL,\n" +
                            "  `Name` VARCHAR(45) NULL,\n" +
                            "  `SurName` VARCHAR(45) NULL,\n" +
                            "  `Nick` VARCHAR(45) NULL,\n" +
                            "  `lan` DOUBLE NULL,\n" +
                            "  `lon` DOUBLE NULL,\n" +
                            "  PRIMARY KEY (`chatId`));";

                    String insert3 ="INSERT INTO `telegram`.`bot` (`chatId`, `Name`, `SurName`, `Nick`,`lan`,`lon`) VALUES ('1', 'Вася', 'Петров', 'Васька','1','2');";

                    try {
                        rs = stmt.execute(insert3);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } finally {
                        //close connection ,stmt and resultset here
                        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
                        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
                       //try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
                    }
                    if (rs==false){
                    sendMessage(message, "Вы подписаны" );}
                    else{
                        sendMessage(message, "Вы уже подписаны" );}

                break;
                case "/unsubscribe":
                    final String url1 = "jdbc:mysql://localhost:3306/";
                    final String usr1 = "root";
                    final String password1 = "12345";
                    Connection con1 = null;
                    Statement stmt1 = null;
                    boolean rs1 = true;
                    try {
                        con1 = DriverManager.getConnection(url1, usr1, password1);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    try {
                        stmt1 = con1.createStatement();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    String delete1 ="DELETE FROM `telegram`.`bot` WHERE chatId='1';";
                    try {
                        rs1 = stmt1.execute(delete1);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } finally {
                        try { con1.close(); } catch(SQLException se) { /*can't do anything */ }
                        try { stmt1.close(); } catch(SQLException se) { /*can't do anything */ }
                        //try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
                    }
                    sendMessage(message, "Вы отписаны" );
                   break;
                case "/nextDay":
                    try{
                        sendMessage(message, Wheather.getWeather1(message.getText(), model));
                    }
                    catch (IOException e){
                        sendMessage(message, "Такого города нет");
                    }
                    break;

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
        KeyboardRow keyboardRow3 = new KeyboardRow();

        List<KeyboardButton> keyboardButtons=new ArrayList<>();
        KeyboardButton keyboardButtons1=new KeyboardButton();
        KeyboardButton keyboardButtons2=new KeyboardButton();
        KeyboardButton keyboardButtons3=new KeyboardButton();
        KeyboardButton keyboardButtons4=new KeyboardButton();
        KeyboardButton keyboardButtons5=new KeyboardButton();
        keyboardButtons.add(keyboardButtons1);
        keyboardButtons.add(keyboardButtons2);
        keyboardButtons.add(keyboardButtons3);
        keyboardButtons.add(keyboardButtons4);
        keyboardButtons.add(keyboardButtons5);
        keyboardButtons1.setText("/start");
        keyboardButtons2.setText("/location");
        keyboardButtons3.setText("/subscribe");
        keyboardButtons4.setText("/unsubscribe");
        keyboardButtons5.setText("/nextDay");
        keyboardButtons2.getRequestLocation();
        keyboardButtons2.setRequestLocation(true);
        keyboardRow1.add(keyboardButtons1);
        keyboardRow2.add(keyboardButtons2);
        keyboardRow2.add(keyboardButtons5);
        keyboardRow3.add(keyboardButtons3);
        keyboardRow3.add(keyboardButtons4);
        keyboardRowList.add(keyboardRow1);
        keyboardRowList.add(keyboardRow2);
        keyboardRowList.add(keyboardRow3);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);

    }
}
