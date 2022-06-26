package com.company.resources;

import java.util.Locale;

public class Utils {
    private static String lang;
    private static String country;

    public void setUserLocale(String lang, String country){
        this.lang = lang;
        this.country = country;
    }


    public static Locale getUserLocale(){
        Locale locale;
        if (lang.equals("En") && lang.equals("US")){
            locale = new Locale(lang, country);
            return locale;
        }
        return null;
    }
}
