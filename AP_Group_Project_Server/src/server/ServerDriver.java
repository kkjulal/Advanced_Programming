package server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerDriver extends Thread {
	
	private static final Logger logger = LogManager.getLogger(ServerDriver.class);

	public static void main(String[] args) {
		
		logger.trace("Server application started.");
		
		new Server();
	}

}
