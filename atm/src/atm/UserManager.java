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
		
		User requestObject = new User(user.getId(), user.getPassword(), user.getName(), user.getAccountList());
		return requestObject;
	}
	
	public User getUserById(String id) {
		User user = null;
		
		int index = indexOfById(id);
		if(index != -1)
			user = getUser(index);
		
		return user;
	}
	
	public int indexOfById(String id) {
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
	public void setUser(User user, Account account) {
		int index = indexOfById(user.getId());
		
		list.get(index).addAccount(account);
	}
	
	public void deleteUserById(int index) {
		this.list.remove(index);
	}
}
