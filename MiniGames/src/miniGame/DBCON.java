package miniGame;

import java.util.Scanner;

public class DBCON {

	public DBDTO loginMenu(int menu){
		DBDTO user = null;
		DBDAO dao= new DBDAO();
		Scanner sc = new Scanner(System.in);
		
		if (menu == 1) {
			System.out.print("로그인할 ID 입력 : ");
			String id = sc.next();
			System.out.print("로그인할 PW 입력 : ");
			String pw = sc.next();

			user = dao.loginUser(id, pw);

			
		}
		else if (menu == 2) {
			System.out.print("ID 입력 : ");
			String id = sc.next();
			System.out.print("PW 입력 : ");
			String pw = sc.next();
			System.out.print("이름 입력 : ");
			String name = sc.next();

			int row = dao.insertUser(id, pw, name);
			if (row > 0) {
				System.out.println("회원가입 성공! :)");
			} else {
				System.out.println("회원가입 실패!ㅠㅠ");
			}

		}
		else if (menu == 3) {
			System.exit(0);
		}
		
		
		
		
		
		return user;
	}
	
	
	
	
	
	
	
}
