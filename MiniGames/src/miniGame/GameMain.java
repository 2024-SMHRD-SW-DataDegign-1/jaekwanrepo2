package miniGame;

import java.util.Scanner;

public class GameMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DBDTO user = null;
		DBCON con = new DBCON();
		image im = new image();

		im.printImage(".\\image\\오프닝.txt");

		while (true) {
			while (true) {
				System.out.println("--------------------------------------------------------------------------");
				System.out.println("[1]로그인\t\t\t[2]회원가입\t\t[3]프로그램종료");
				System.out.println("--------------------------------------------------------------------------");
				int menu = sc.nextInt();
				user = con.loginMenu(menu);
 
				if (menu == 1) {
					if (user == null) {
						System.err.println("로그인 실패");
					} else {
						System.out.println("로그인 성공");
						System.out.println();
						System.out.println(user.getName() + "님 환영합니다.");
						break;
					}

				}
			}

//		String comPath = ".\\player\\";
			while (true) {
				System.out.println("--------------------------------------------------------------------------");
				System.out.println("[1]노래맞추기 [2]틀린코드찾기 [3]넌센스그림퀴즈 [4]초성퀴즈 [5]랭킹보기 [6]로그아웃 [0]게임종료");
				System.out.println("--------------------------------------------------------------------------");
				int num = sc.nextInt();

				if (num == 1) {
					MusicGameCon mp3 = new MusicGameCon();
					mp3.playTest(user.getId());

				} else if (num == 2) {
					WrongCodeMain wcm = new WrongCodeMain();
					wcm.wrongCodeMain(user);
				} else if (num == 3) {/* 아현 : 넌센스 그림 맞추기 게임 */
					NeonClass neon = new NeonClass();
					neon.imageGame(user.getId());

				} else if (num == 4) {
					WordGame wordGame = new WordGame();
					wordGame.startGame(user.getId());
				} else if (num == 5) {
					RankDAO rank = new RankDAO();
					System.out.println("--------------------------------------------------------------------------");
					System.out.println("[1]노래맞추기 [2]틀린문법찾기 [3]넌센스그림퀴즈 [4]초성퀴즈 [0]뒤로가기");
					System.out.println("--------------------------------------------------------------------------");
					int numR = sc.nextInt();
					if (numR == 0) {
						continue;
					} else {
						rank.ranksys(numR);
					}

				} else if (num == 6) {
					user =null;
					break;
				} else if (num == 0) {
					System.out.println("게임을 종료합니다.");
					System.exit(0);
				}

			}
		}

	}

}
