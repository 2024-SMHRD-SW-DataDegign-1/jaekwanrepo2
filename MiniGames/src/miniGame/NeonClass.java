package miniGame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class NeonClass {
	Random ran = new Random();

	public void imageGame() {
		Scanner sc = new Scanner(System.in);

		NeonDAO dao = new NeonDAO();
//		NeonQuestion neon = new NeonQuestion();
//		NeonImage neonIm = new NeonImage();/* 넌센스 퀴즈 */

		int sum = 0;

		System.out.println("사진 퀴즈에 오신 것을 환영합니다!");

		ArrayList<Integer> num = ranNum(16);
		
		
		
		for (int i = 0; i < 16; i++) {
			int score = 30;
			System.out.println("문제 " + (i + 1));
			while (true) {
//				neonIm.neonfile(num.get(i) + 1);/*문제*/
//				neonIm.neonfile(dao.neonGame().get(num.get(i)).getQ());/* 문제 */
				System.out.print("정답을 입력하세요: ");
				String userAnswer = sc.nextLine();
				System.out.println();

				if (userAnswer.equalsIgnoreCase(dao.neonGame().get(num.get(i)).getA())) {
					System.out.println("정답입니다.");
					break;

				} else {
					System.err.println("오답입니다.");
					score -= 10;
					if (score == 20) {
						System.out.println(dao.neonGame().get(num.get(i)).getHint1());
						System.out.println("기회2번남음");
					} else if (score == 10) {
						System.out.println(dao.neonGame().get(num.get(i)).getHint2());
						System.out.println("기회1번남음");
					} else {
						System.out.println("죄송합니다. 기회를 모두 사용하셨습니다. 정답은: " + dao.neonGame().get(num.get(i)).getA());
						break;
					}
				}

			}
			System.out.println("획득점수" + score);
			sum += score;

		}

		System.out.println("게임 종료! 최종 점수는 " + sum + "점 입니다.");

	}

	public ArrayList<Integer> ranNum(int max) {

		ArrayList<Integer> result = new ArrayList<Integer>();

		for (int i = 0; i < max; i++) {
			int randomNumber = ran.nextInt(max);

			while (result.contains(randomNumber)) {
				randomNumber = ran.nextInt(max);
			}
			result.add(randomNumber);
		}

		return result;
	}

}
