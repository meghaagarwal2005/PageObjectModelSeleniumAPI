package Common;


import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;


public class LoggerManager {

	private static final String LOG_ROOT_DIRECTORY = "src/test/Logs";
	private static final String LOG_ROOT_ARCHIVE_DIRECTORY = "src/test/Logs/Archive";
	private static Map<Long, Logger> loggerMap = new HashMap<Long, Logger>();

	public LoggerManager(String logFolder, String logFileName) {
		System.setProperty("log.name", logFileName);
		System.setProperty("log.dir", logFolder);
		System.setProperty("log.root.dir", LOG_ROOT_DIRECTORY);
		System.setProperty("log.archive.dir", LOG_ROOT_ARCHIVE_DIRECTORY);
		
		try {
			Logger logger = LogManager.getLogger(logFileName);
			loggerMap.put(Thread.currentThread().getId(),logger);
		} catch (Exception e) {
			System.out.println("Logger Failed to Initialize" + ExceptionUtils.getMessage(e));
			throw new RuntimeException("Logger Failed to Initialize");
		}

	}


	public static Logger getLogger() {
		Logger logger = loggerMap.get(Thread.currentThread().getId());
		if (null != logger) {
			return logger;
		} else {
			throw new RuntimeException("Logger Not Initialized. Initialize the logger");
				
		}
	}
	

	
	
}
