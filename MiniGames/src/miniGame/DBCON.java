package miniGame;

import java.util.Scanner;

public class DBCON {

	public DBDTO loginMenu(int menu) {
		DBDTO user = null;
		DBDAO dao = new DBDAO();
		Scanner sc = new Scanner(System.in);
		String id = null;
		String pw = null;
		String name = null;

		if (menu == 1) {
			System.out.print("로그인할 ID 입력 : ");
			id = sc.next();
			System.out.print("로그인할 PW 입력 : ");
			pw = sc.next();

			user = dao.loginUser(id, pw);

		} else if (menu == 2) {
			while (true) {
				System.out.print("ID 입력 : ");
				id = sc.next();
				if (id.length() > 10 || id.length() < 4) {
					System.out.println("아이디는 4~10글자 사이로 입력해주세요.");
					continue;
				}
				break;
			}
			while (true) {
				System.out.print("PW 입력 : ");
				pw = sc.next();
				if (pw.length() > 12 || pw.length() < 4) {
					System.out.println("비밀번호는 4~12글자 사이로 입력해주세요.");
					continue;
				}
				break;
			}
			while (true) {
				System.out.print("이름 입력 : ");
				name = sc.next();
				if (name.length() < 2 || name.length() > 5) {
					System.out.println("이름은 2글자 이상 5글자 이하로 입력해주세요.");
					continue;
				}
				break;
			}

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
