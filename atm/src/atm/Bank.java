package atm;

import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
	Scanner scanner;
	private UserManager userManager;
	private AccountManager accountManager;
	
	int log;
	
	private String name;
	public Bank(String name) {
		this.scanner = new Scanner(System.in);
		
		userManager = new UserManager();
		accountManager = new AccountManager();
		
		this.name = name;
		this.log = -1;
		
	}
	
	public void printInfo() {
		System.out.println("--- " + this.name + " ---");
		System.out.println("1. ȸ�� ����");
		System.out.println("2. ȸ�� Ż��");
		System.out.println("3. �α���");
		System.out.println("4. �α׾ƿ�");
		System.out.println("0. ����");
		System.out.println("-------------------------");
	}
	
	public int inputNumber() {
		System.out.print("���� : ");
		int select = this.scanner.nextInt();
		
		return select;
	}
	
	public void addUser() {
		System.out.print("ID : ");
		String id = this.scanner.next();
		System.out.print("PW : ");
		String password = this.scanner.next();
		System.out.print("�̸� : ");
		String name = this.scanner.next();
		
		User user = new User(id, password, name);
		if(this.userManager.addUser(user) != null) {
			System.out.println("ȸ������ �Ϸ�");
		}
		else
			System.out.println("�ߺ��� ���̵� �����մϴ�.");
	}
	
	public void removeUser() {
		System.out.print("Ż���� ID : ");
		String id = this.scanner.next();
		System.out.print("PW : ");
		String password = this.scanner.next();
		
		int index = -1;
		index = this.userManager.indexOfById(id);
		if(index != -1) {
			String targetPassword = this.userManager.getList().get(index).getPassword();
			if(targetPassword.equals(password))
				this.userManager.deleteUserById(index);
			else
				System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
		else
			System.out.println("���̵� �ٽ� Ȯ���ϼ���.");
	}
	
	public void addAccount() {
		System.out.print("ID : ");
		String id = this.scanner.next();
		System.out.print("PW : ");
		String password = this.scanner.next();
		
		User user = this.userManager.getUserById(id);
		
		if(user != null) {
			if(user.getPassword().equals(password)) {
				if(user.getAccountSize() < Account.LIMIT) {
					Account account = this.accountManager.createAccount(new Account(id));
					this.userManager.setUser(user, account);
				}
				else
					System.out.println("���´� ���� �� 3������ ��û�� �� �ֽ��ϴ�.");
			}
			else
				System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
		else
			System.out.println("ȸ�������� �ٽ� Ȯ���ϼ���.");
	}
	
	public void removeAccount() {
		if(logInCheck()) {
			System.out.print("öȸ�� ���¸� : ");
			String id = this.scanner.next();
			int index = accountManager.indexOfByAccountId(id);
			
			if(index != -1) {
				System.out.print("���¹�ȣ(- �����ؼ� �� ��) : ");
				String accountNumber = this.scanner.next();
				Account targetAccount = accountManager.getAccount(index);
				
				if(targetAccount.getMoney() == 0) {
					if(accountNumber.equals(targetAccount.getAccountNumber()))
						accountManager.deleteAccount(index);
					else
						System.out.println("���¹�ȣ�� ��ġ���� �ʽ��ϴ�.");
				}
				else
					System.out.println("öȸ �� ���¿� ���� �ܾ��� ����ϼ���.");
			}
			else
				System.out.println("���¸��� �ٽ� Ȯ�����ּ���..");
		}
	}
	
	public void logIn() {
		if(!logInCheck()) {
			int index = -1;
			System.out.print("ID : ");
			String id = this.scanner.next();
			for(int i=0; i<userManager.getList().size(); i++) {
				User targetUser = UserManager.getList().get(i);
				if(id.equals(targetUser.getId()))
					index = i;
			}
			
			if(index != -1) {
				System.out.print("PW : ");
				String password = this.scanner.next();
				String targetUserPassword = UserManager.getList().get(index).getPassword();
				if(targetUserPassword.equals(password)) {
					this.log = index;
					System.out.println("�α��εǾ����ϴ�.");
				}
				else
					System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			}
			else
				System.out.println("���̵� �ٽ� Ȯ�����ּ���.");
		}
		else
			System.out.println("�̹� �α��� �Ǿ��ֽ��ϴ�.");
	}
	
	public boolean logInCheck() {
		if(log != -1)
			return true;
		return false;
	}
	
	public int logOut() {
		if(logInCheck()) {
			System.out.println("�α׾ƿ� �Ϸ�");
			return this.log = -1;
		}
		else {
			System.out.println("�α��εǾ����� �ʽ��ϴ�.");
			return this.log;
		}
	}
	public void run() {
		while(true) {
			ArrayList<User> list = userManager.getList();
			for(int i=0; i<list.size(); i++)
			System.out.println("ID : " + list.get(i).getId() + ", PW : " + list.get(i).getPassword() + ", �̸� : " + list.get(i).getName());
			printInfo();
			int select = inputNumber();
			
			if(select == 1) addUser();
			else if(select == 2) removeUser();
			else if(select == 3) {
				logIn();
				if(this.log != -1)
					customRun();
			}
			else if(select == 4) logOut();
			else if(select == 0) break;
		}
	}
	
	
	public void printCustomMenuInfo() {
		System.out.println("=== ȸ�� �޴� ===");
		System.out.println("1. �Ա�");
		System.out.println("2. ���");
		System.out.println("3. ��ȸ");
		System.out.println("4. ��ü");
		System.out.println("5. ���»���");
		System.out.println("6. ����öȸ");
		System.out.println("0. �α׾ƿ�");
		System.out.println("==============");
	}
	public void customRun() {
		while(this.log != -1) {
			printCustomMenuInfo();
			int select = inputNumber();
		}
	}
}
