package atm;

import java.util.ArrayList;

public class UserManager {
	private static ArrayList<User> list = new ArrayList<>();
	
	public static ArrayList<User> getList() {
		return list;
	}
	
	public void addUser(User user) {
		this.list.add(user);
	}
	
	public User getUser(int index) {
		User user = this.list.get(index);
		
		User requestObject = new User();
		return requestObject;
	}
	
	public User getUserById(String id) {
		int index = -1;
		return getUser(index);
	}
	
	
	public void setUser(int index, User user) {
		this.list.set(index, user);
	}
	
	
	public void deleteUserById(int index) {
		this.list.remove(index);
	}
}
