package com.buutcamp.main;

import com.buutcamp.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    private static String unnamedPoster;
    private static int usernameMaxSize;
    private static int messageMaxSize;

    public static String getDateStr(){
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(new Date());
        return date;
    }

    public static boolean stringIsNullOrEmpty(String s){
        if(s != null){
            return s.trim().isEmpty();
        }
        return true;
    }

    public static void p(String s){
        System.out.println("UTILS PRINT: " + s);
    }

    public static Integer parseIntSafe(String s){
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static String getProperty(String name){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        Environment env = ctx.getEnvironment();
        String property = env.getProperty(name);
        ctx.close();
        return property;
    }

    public static String getUnnamedPoster(){
        if(stringIsNullOrEmpty(unnamedPoster)){
            unnamedPoster = getProperty("unnamed_poster");
        }
        return unnamedPoster;
    }

    public static int getUsernameMaxSize(){
        if(usernameMaxSize == 0){
            usernameMaxSize = Integer.parseInt(getProperty("username_max_size"));
        }
        return usernameMaxSize;
    }

    public static int getMessageMaxSize(){
        if(messageMaxSize == 0){
            messageMaxSize = Integer.parseInt(getProperty("message_max_size"));
        }
        return messageMaxSize;
    }
}
