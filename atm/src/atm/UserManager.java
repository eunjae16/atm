package atm;

import java.util.ArrayList;

public class UserManager {
	private static ArrayList<User> list = new ArrayList<>();
	// User에 대한
	
	// Create
	public void addUser(User user) {
		this.list.add(user);
	}
	// Read
	public User getUser(int index) {
		User user = this.list.get(index);
		
		//사본 제공
		User requestObject = new User();
		return requestObject;
	}
	
	public User getUserById(String id) {
		int index = -1; // ?
		return getUser(index);
	}
	
	// Update
	public void setUser(int index, User user) {
		this.list.set(index, user);
	}
	
	// Delete
	public void deleteUserById(int index) {
		this.list.remove(index);
	}
}
