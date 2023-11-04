package client.helper.logging;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LoggingHelper {
    private static final Logger logger = Logger.getLogger(LoggingHelper.class.getName());

    static class CustomFormatter extends Formatter {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

        @Override
        public String format(LogRecord record) {
            return format.format(new Date(record.getMillis())) + " " + record.getLevel() + ": " + record.getMessage() + "\n";
        }
    }

    static {
        try {
            // for console output
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new CustomFormatter());
            logger.addHandler(consoleHandler);

            // for log file output
            FileHandler fileHandler = new FileHandler("Logging_EasyPass.txt", true); // append to file if it exists
            fileHandler.setFormatter(new CustomFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error creating log file", e);
        }
    }

    public static void logToConsole(String message) {
        logger.log(Level.INFO, message);
    }
    public static void logToConsoleVerbose(String message) {
        logger.log(Level.FINEST, message);
    }

    public static void logToFile(String message) {
        logger.log(Level.INFO, message);
    }
}