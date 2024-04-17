package miniGame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random ran = new Random();
		image im = new image();
		MusicGameCon mp3 = new MusicGameCon();
		DBDAO dao = new DBDAO();
		PathDAO pathdao = new PathDAO();

		while (true) {
			System.out.println("1.로그인 2.회원가입 3.프로그램종료");
			int menu = sc.nextInt();
			if (menu == 1) {
				System.out.print("로그인할 ID 입력 : ");
				String id = sc.next();
				System.out.print("로그인할 PW 입력 : ");
				String pw = sc.next();
				
				String name = dao.loginUser(id, pw);
				// name == "" 로그인 실패
				// name != "" 로그인 성공
				if (name.equals("")) {
					System.out.println("로그인 실패");
				} else {
					System.out.println("로그인 성공");
					System.out.println(name + "님 환영합니다.");
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
			ArrayList<Integer> song = mp3.ranNum(pathdao.songData().size());
			for (int j = 0; j < 5; j++) {
				for (int i = 1; i <= 3; i++) {
					im.printImage();
					mp3.playTest(pathdao.songData().get(song.get(j)).getPath(), i);
					System.out.print("정답(한글로작성)" + (5 - (i - 1) * 2) + "점 : ");
					if (pathdao.songData().get(song.get(j)).getName().equals(sc.next())) {
						System.out.println("정답입니다.");
						mp3.timeDelay(3);
						break;
					} else {
						System.out.println("오답입니다.");
						mp3.timeDelay(3);
					}

				}

			}
		}

	}

}
