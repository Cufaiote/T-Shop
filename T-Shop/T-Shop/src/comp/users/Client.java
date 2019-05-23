package comp.users;

import java.io.Serializable;

public class Client extends User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Client(int userId, String username, String password) {
		this.setUserId(userId);
		this.setUsername(username);
		this.setPassword(password);
	}

	public Client(String username, String password, String status) {
		this.setUsername(username);
		this.setPassword(password);
		this.setStatus(status);
	}

	public Client() {
		super();
	}

	public Client(String username, String password) {
		// TODO Auto-generated constructor stub
		this.setUsername(username);
		this.setPassword(password);
	}

}
