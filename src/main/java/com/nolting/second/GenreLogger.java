package com.nolting.second;

import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class GenreLogger {
    public static Logger logger = null;

    public synchronized static Logger getLogger() throws Exception{
        if(logger == null) {
            FileHandler fileHandler = new FileHandler("Logs/Genrelog.log", true);
            logger = Logger.getLogger("Glog");
            logger.addHandler(fileHandler);
        }

        return logger;
    }
}
