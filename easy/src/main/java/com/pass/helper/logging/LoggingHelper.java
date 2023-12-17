package com.pass.helper.logging;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LoggingHelper {
    private static final Logger logger = Logger.getLogger(LoggingHelper.class.getName());


    static class CustomFormatter extends Formatter {
        private static final String format = "[%1$s] [%2$-7s] %3$s %n";
        private final SimpleDateFormat sdf;

        public CustomFormatter() {
            sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone("CET"));
        }

        @Override
        public String format(LogRecord record) {
            String message = record.getMessage();
            message = message.replaceAll("\u001B\\[[;\\d]*m", ""); // remove ANSI color codes
            return String.format(format, sdf.format(new Date(record.getMillis())),
                    record.getLevel().getLocalizedName(), message);
        }
    }

    static {
        try {
            logger.setUseParentHandlers(false);
            // for console output
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new CustomFormatter());
            logger.addHandler(consoleHandler);

            // for log file output
            FileHandler fileHandler = new FileHandler("Logging_EasyPass.log", true); // append to
                                                                                     // file if it
                                                                                     // exists
            fileHandler.setFormatter(new CustomFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error creating log file", e);
        }
    }

    public static void logToConsole(String message) {
        logger.log(Level.WARNING, message);
    }

    public static void logToConsoleVerbose(String message) {
        logger.log(Level.FINEST, message);
    }

    public static void logToFile(String message, Object... args) {
        message = message.replaceAll("\u001B\\[[;\\d]*m", "");
        logger.log(Level.SEVERE, String.format(message, args));

    }
}
