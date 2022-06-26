package com.company.resources;

import java.util.Locale;
import java.util.ResourceBundle;

public class Internationalization {


    public static void locale(String page){
        Locale locale = Utils.getUserLocale();

        switch (locale.getLanguage()){
            case "en":
                ResourceBundle r = ResourceBundle.getBundle("com/company/resources/header/header_en", locale);

                break;
        }

    }
}
