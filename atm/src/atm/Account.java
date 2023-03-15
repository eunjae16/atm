package atm;

public class Account {
	public static int LIMIT = 3;
	private String userId;
	private String accountNumber;
	private int money;
	
	public Account(String userId) {
		this.userId = userId;
	}
	
	public Account(String userId, String accountNumber, int money) {
		this.userId = userId;
		this.accountNumber = accountNumber;
		this.money = money;
	}
	
	public String getUserId() {
		return this.userId;
	}
	
	public String getAccountNumber() {
		return this.accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public int getMoney() {
		return this.money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
}