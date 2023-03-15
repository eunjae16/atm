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
		System.out.println("1. 회원 가입");
		System.out.println("2. 회원 탈퇴");
		System.out.println("3. 로그인");
		System.out.println("4. 로그아웃");
		System.out.println("0. 종료");
		System.out.println("-------------------------");
	}
	
	public int inputNumber() {
		System.out.print("선택 : ");
		int select = this.scanner.nextInt();
		
		return select;
	}
	
	public void addUser() {
		System.out.print("ID : ");
		String id = this.scanner.next();
		System.out.print("PW : ");
		String password = this.scanner.next();
		System.out.print("이름 : ");
		String name = this.scanner.next();
		
		User user = new User(id, password, name);
		if(this.userManager.addUser(user) != null) {
			System.out.println("회원가입 완료");
		}
		else
			System.out.println("중복된 아이디가 존재합니다.");
	}
	
	public void removeUser() {
		System.out.print("탈퇴할 ID : ");
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
				System.out.println("비밀번호가 일치하지 않습니다.");
		}
		else
			System.out.println("아이디를 다시 확인하세요.");
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
					System.out.println("계좌는 계정 당 3개까지 신청할 수 있습니다.");
			}
			else
				System.out.println("비밀번호가 일치하지 않습니다.");
		}
		else
			System.out.println("회원정보를 다시 확인하세요.");
	}
	
	public void removeAccount() {
		if(logInCheck()) {
			System.out.print("철회할 계좌명 : ");
			String id = this.scanner.next();
			int index = accountManager.indexOfByAccountId(id);
			
			if(index != -1) {
				System.out.print("계좌번호(- 포함해서 쓸 것) : ");
				String accountNumber = this.scanner.next();
				Account targetAccount = accountManager.getAccount(index);
				
				if(targetAccount.getMoney() == 0) {
					if(accountNumber.equals(targetAccount.getAccountNumber()))
						accountManager.deleteAccount(index);
					else
						System.out.println("계좌번호가 일치하지 않습니다.");
				}
				else
					System.out.println("철회 전 계좌에 남은 잔액을 출금하세요.");
			}
			else
				System.out.println("계좌명을 다시 확인해주세요..");
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
					System.out.println("로그인되었습니다.");
				}
				else
					System.out.println("비밀번호가 일치하지 않습니다.");
			}
			else
				System.out.println("아이디를 다시 확인해주세요.");
		}
		else
			System.out.println("이미 로그인 되어있습니다.");
	}
	
	public boolean logInCheck() {
		if(log != -1)
			return true;
		return false;
	}
	
	public int logOut() {
		if(logInCheck()) {
			System.out.println("로그아웃 완료");
			return this.log = -1;
		}
		else {
			System.out.println("로그인되어있지 않습니다.");
			return this.log;
		}
	}
	public void run() {
		while(true) {
			ArrayList<User> list = userManager.getList();
			for(int i=0; i<list.size(); i++)
			System.out.println("ID : " + list.get(i).getId() + ", PW : " + list.get(i).getPassword() + ", 이름 : " + list.get(i).getName());
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
		System.out.println("=== 회원 메뉴 ===");
		System.out.println("1. 입금");
		System.out.println("2. 출금");
		System.out.println("3. 조회");
		System.out.println("4. 이체");
		System.out.println("5. 계좌생성");
		System.out.println("6. 계좌철회");
		System.out.println("0. 로그아웃");
		System.out.println("==============");
	}
	public void customRun() {
		while(this.log != -1) {
			printCustomMenuInfo();
			int select = inputNumber();
		}
	}
}
