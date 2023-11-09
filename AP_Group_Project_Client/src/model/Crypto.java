package domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Crypto implements Serializable {
	
	private String password;
	
	public Crypto() {
		password = "";
	}
	
	public Crypto(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Password: " + password + "";
	}
}
