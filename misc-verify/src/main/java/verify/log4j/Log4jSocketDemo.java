package verify.log4j;


import org.apache.log4j.Logger;

public class Log4jSocketDemo {
	
	public static void main(String[] args) {
		Logger logger =  Logger.getLogger(Log4jSocketDemo.class);
		logger.info("Test");
		
		org.apache.log4j.LogManager.shutdown();
	}

}
