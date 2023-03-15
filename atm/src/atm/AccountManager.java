package atm;

import java.util.ArrayList;
import java.util.Random;
public class AccountManager {
	private static ArrayList<Account> accountList = new ArrayList<>();
	
	public Account createAccount(Account account) {
		String accountNumber = accNumGenerator();
		account.setAccountNumber(accountNumber);
		this.accountList.add(account);
		return account;
	}
	
	
	public Account getAccount(int index) {
		Account account = this.accountList.get(index);
		
		Account requestAcc = new Account(account.getUserId(), account.getAccountNumber(), account.getMoney());
		return requestAcc;
	}
	
	
	public Account getAccountByNumber(String accountNumber) {
		Account account = null;
		for(Account object : accountList) {
			if(object.getAccountNumber().equals(accountNumber))
				account = object;
		}
		return account;
	}
	
	public void setAccount(int index, Account account) {
		this.accountList.set(index, account);
	}
	
	
	public void deleteAccount(int index) {
		this.accountList.remove(index);
	}
	
	private String accNumGenerator() {
		Random ran = new Random();
		String num = "";
		
		while(true) {
			int first = ran.nextInt(8999)+1000;
			int second = ran.nextInt(8999)+1000;
			
			num = first + "-" + second;
			Account account = getAccountByNumber(num);
			
			if(account == null)
				break;
		}
		return num;
	}
	
	public int indexOfByAccountId(String id) {
		int index = -1;
		for(Account account : accountList) {
			if(account.getUserId().equals(id))
				index = accountList.indexOf(account);
		}
		return index;
	}
}
