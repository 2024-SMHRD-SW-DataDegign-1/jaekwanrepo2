package miniGame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;



public class GameMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random ran = new Random();
		DBDAO dao = new DBDAO();
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

		System.out.println("[1]노래맞추기 [2]틀린문법찾기 [3]그림맞추기 [4]초성퀴즈");
		int num = sc.nextInt();

		if (num == 1) {
			MusicGameCon mp3 = new MusicGameCon();
			mp3.playTest(user.getId());

		}else if(num == 2) {
			WrongCodeMain wcm = new WrongCodeMain();
			wcm.wrongCodeMain(user);
		}else if(num == 3) {/*아현 : 넌센스 그림 맞추기 게임*/
			
		}else if(num == 4) {
			
		}else if(num == 5) {
			
		}




	}

}
