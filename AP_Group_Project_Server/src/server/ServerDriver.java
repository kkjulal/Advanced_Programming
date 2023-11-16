package server;

import view.ServerDashboard;

public class ServerDriver extends Thread {
	
	@Override
	public void run() {
		new ServerDashboard();
	}

	public static void main(String[] args) {
		ServerDriver server = new ServerDriver(); //Multi-threading		
		server.start();
	}

}
