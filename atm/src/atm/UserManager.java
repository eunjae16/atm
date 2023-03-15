package atm;

import java.util.ArrayList;

public class UserManager {
	private static ArrayList<User> list = new ArrayList<>();
	
	public static ArrayList<User> getList() {
		return list;
	}
	
	public User addUser(User user) {
		User result = getUserById(user.getId());
		if(result == null) {
			this.list.add(user);
			return user;
		}
		return null;
	}
	
	public User getUser(int index) {
		User user = this.list.get(index);
		
		User requestObject = new User(user.getId(), user.getPassword());
		return requestObject;
	}
	
	public User getUserById(String id) {
		User user = null;
		
		int index = indexOfById(id);
		if(index != -1)
			user = getUser(index);
		
		return user;
	}
	
	
	private int indexOfById(String id) {
		int index = -1;
		for(User user : list) {
			if(user.getId().equals(id))
				index = list.indexOf(user);
		}
		return index;
	}
	
	public void setUser(int index, User user) {
		this.list.set(index, user);
	}
	
	
	public void deleteUserById(int index) {
		this.list.remove(index);
	}
}
