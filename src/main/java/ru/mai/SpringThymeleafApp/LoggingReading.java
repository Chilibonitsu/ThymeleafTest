package ru.mai.SpringThymeleafApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoggingReading {
    public static final Pattern MATCH = Pattern.compile(
            "^(.+)\\s+(\\d+)$");
    public static Logger LOGGER;

    public static void logReader() {
        try (FileInputStream ins = new FileInputStream("log.config")){
            LogManager.getLogManager().readConfiguration(ins);
            LOGGER = Logger.getLogger(LoggingReading.class.getName());
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
        }
    }
    public static HashMap<String, Integer> Reader() {
        HashMap<String, Integer> map = new HashMap<>();
        try {
            Scanner sc = new Scanner(new File("input.txt"),
                    "UTF-8");
            String str;
            int counter = 0;
            Matcher matcher;
            while (sc.hasNextLine()) {
                str = sc.nextLine();
                matcher = MATCH.matcher(str);
                if (matcher.find()) {
                    map.put(matcher.group(1),
                            Integer.parseInt(matcher.group(2)));
                } else {
                    LOGGER.log(Level.WARNING,
                            "Строка" + counter + " не была прочтена");
                }
                counter++;

            }
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
        return map;
    }

}
