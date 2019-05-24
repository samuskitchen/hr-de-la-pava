package com.kometsales.sales.util;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValidator {

    private static final Logger LOGGER = LogManager.getLogger(DateValidator.class.getName());

    public static boolean isThisDateValid(String dateToValidate, String dateFormat){

        if(dateToValidate == null){
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);

        try {
            //if not valid, it will throw ParseException
            Date date = sdf.parse(dateToValidate);
            LOGGER.info("Date Valid {}", date);

        } catch (ParseException e) {
            LOGGER.error("Date Invalid!");
            return false;
        }

        return true;
    }
}
