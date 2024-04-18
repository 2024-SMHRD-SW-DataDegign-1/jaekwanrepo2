package miniGame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random ran = new Random();
//		image im = new image();
		DBDAO dao = new DBDAO();
//		PathDAO pathdao = new PathDAO();
		DBDTO user = null;

		while (true) {
			System.out.println("1.로그인 2.회원가입 3.프로그램종료");
			int menu = sc.nextInt();
			if (menu == 1) {
				System.out.print("로그인할 ID 입력 : ");
				String id = sc.next();
				System.out.print("로그인할 PW 입력 : ");
				String pw = sc.next();

				user = dao.loginUser(id, pw);
				System.out.println(user.getName());

				if (user.getName().equals("")) {
					System.out.println("로그인 실패");
				} else {
					System.out.println("로그인 성공");
					System.out.println(user.getName() + "님 환영합니다.");
					break;
				}
			}
			if (menu == 2) {
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
			if (menu == 3) {
				System.exit(0);
			}
		}

//		String comPath = ".\\player\\";

		System.out.println("[1]MusicGame");
		int num = sc.nextInt();

		if (num == 1) {
			MusicGameCon mp3 = new MusicGameCon();
			mp3.playTest();

		}

	}

}
