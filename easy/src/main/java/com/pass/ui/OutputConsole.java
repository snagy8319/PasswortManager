package com.pass.ui;

import com.pass.helper.logging.LoggingHelper;
import com.pass.helper.ui.PrintedColor;

public class OutputConsole {

    public static void writeConsole(boolean valid, String process) {

        if (valid) {
            switch (process) {
                case "registering":
                    LoggingHelper.logToConsole(PrintedColor.successMessage
                            + "User has been registired" + PrintedColor.resetColor);
                    break;
                case "logging":
                    LoggingHelper.logToConsole(PrintedColor.successMessage
                            + "User has been loggedin" + PrintedColor.resetColor);
                    break;
                case "logout":
                    LoggingHelper.logToConsole(PrintedColor.successMessage
                            + "User  has been loggedout" + PrintedColor.resetColor);
                    break;
                default:
                    break;
            }
        } else {
            switch (process) {
                case "registering":
                    LoggingHelper.logToConsole("User has not been registerd");
                    break;
                case "logging":
                    LoggingHelper.logToConsole("User has not been loggedin");
                    break;
                case "logout":
                    LoggingHelper.logToConsole("User has not been loggedout");
                    break;
                default:
                    break;
            }
        }

    }

}
