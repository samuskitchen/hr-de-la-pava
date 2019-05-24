package com.kometsales.sales.util;

import com.kometsales.sales.model.Sales;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CSVUtils {

    private static final Logger LOGGER = LogManager.getLogger(DateValidator.class.getName());

    private final static String SEPARATOR = ",";
    private final static String FORMAT_DATE = "dd-MM-yyyy HH:mm:ss";
    private static final Pattern INPUT_REGEX = Pattern.compile(".*[`~!@#$%^&*()\\-_=+\\\\|\\[{\\]};:'\",<.>/?].*");

    public static List<Sales> readFileCsv(File fileCsv) {
        List<Sales> inputList = new ArrayList<>();

        try {
            InputStream inputFS = new FileInputStream(fileCsv);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));

            // skip the header of the csv
            inputList = br.lines()
                    .map(mapToItem)
                    .collect(Collectors.toList());

            br.close();
            fileCsv.deleteOnExit();
        } catch (IOException e) {
            LOGGER.error("Error reading the file ", e);
        }

        return inputList;
    }

    private static final Function<String, Sales> mapToItem = (line) -> {
        String[] p = line.split(SEPARATOR);// a CSV has comma separated lines
        Sales item = new Sales();

        //Validate Column Date
        if (DateValidator.isThisDateValid(p[0], FORMAT_DATE)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE);
            LocalDateTime dateTime = LocalDateTime.parse(p[0], formatter);
            item.setDate(dateTime);
        }

        //Validate Column id seller
        if (p[1] != null && p[1].trim().length() > 0 && specialsCharacter(p[1])) {
            item.setSeller(Long.parseLong(p[1]));
        }

        //Validate Column product
        if (p[2] != null && p[2].trim().length() > 0 && specialsCharacter(p[2])) {
            item.setProduct(Integer.parseInt(p[2]));
        }

        //Validate Column rode
        if (p[3] != null && p[3].trim().length() > 0 && specialsCharacter(p[3])) {
            item.setRode(new BigDecimal(p[3]));
        }

        return item;
    };


    private static boolean specialsCharacter(String input) {
        boolean inputMatches = true;

        if (input == null || input.trim().isEmpty()) {
            return false;
        }

        if (INPUT_REGEX.matcher(input).matches()) {
            inputMatches = false;
        }

        return inputMatches;
    }

}
