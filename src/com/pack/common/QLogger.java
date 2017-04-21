package com.pack.common;

import java.text.MessageFormat;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class QLogger {

    private Logger logger;
    private String logEntryTemplate;
    private String className;
    private FileHandler fileH = null;;

    /**
     * Cosntructor initializes the logger
     * @param pkgName
     * @param fileName
     * @param logLevel
     */
    public QLogger(String pkgName, String fileName, String logLevel) {
        try {
            logger = Logger.getLogger(pkgName);
            fileH = new FileHandler(fileName);
            SimpleFormatter simpleF = new SimpleFormatter();
            fileH.setFormatter(simpleF);
            logger.addHandler(fileH);
            if ((logLevel != null) && !logLevel.equals("")) {
                logger.setLevel(Level.parse(logLevel));
            } else {
                logger.setLevel(Level.FINE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (fileH != null)
                fileH.close();
        }
    }

    /**
     * This method sets the the className for the logger
     * @param componentName
     */
    public void setComponent(String componentName) {
        logEntryTemplate = componentName + ".{0}: {1}";
        className = componentName;
    }

    /**
     * Writes a log entry for entering a test method.
     */
    public void entering(String methodName, Object[] params) {
        if (params != null) {
            logger.entering(className, methodName, params);
        } else {
            logger.entering(className, methodName);
        }
    }

    /**
     * Writes a log entry for exiting a test method.
     */
    public void exiting(String methodName) {
        logger.exiting(className, methodName);
    }

    /**
     * Writes a log entry.
     */
    public void log(Level level, String methodName, Object message) {
        Object[] args = {methodName, message};
        logger.log(level, MessageFormat.format(logEntryTemplate, args));
    }

    /**
     * Writes a log entry.
     */
    public void log(
            Level level,
            String methodName,
            String message,
            Object[] params
            ) {
        Object[] args = {methodName, message};
        logger.log(level,
                        MessageFormat.format(logEntryTemplate, args), params);
    }

    /**
     * This method closes the log handle
     */
    public void closeLogFile() {
        fileH.close();
        fileH = null;
    }
}
