package atm;

import java.util.ArrayList;

public class User {
	private String id;
	private String password;
	private String name;
	private ArrayList<Account> accs;
	
	public User(String id, String password, String name){
		this.id = id;
		this.password = password;
		this.name = name;
	}
	public User() {
		this.id = id;
		this.password = password;
		this.name = name;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<Account> getAccs() {
		return this.accs;
	}
	public void setAccs(ArrayList<Account> accs) {
		this.accs = accs;
	}
}
