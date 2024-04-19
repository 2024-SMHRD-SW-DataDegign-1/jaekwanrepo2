package miniGame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class NeonClass {
	Random ran = new Random();
	RankDAO rank = new RankDAO();
	
	public void imageGame(String id) {
		Scanner sc = new Scanner(System.in);

		NeonDAO dao = new NeonDAO();
		image neonIm = new image();/* 넌센스 퀴즈 */

		int sum = 0;

		String intro = "넌센스 그림퀴즈에 오신 걸 환영합니다.\n"
					  +"당신의 센스를 확인해보세요 문제의 정답을 맞힐 수 있는 기회는 딱 3번 주어집니다.\n"
					  +"한 문제당 만점은 30점이며 기회가 한번씩 깎일 때마다 힌트가 제공이 되며 점수가 -10점씩 차감됩니다.\n"
					  +"Let's play~  ";
		
		slowPrint(intro, 50);
		timeDelay(2);
		
		ArrayList<Integer> num = ranNum(16);
		System.out.println("\n\n-------------------------------------------\n\n");
		
		for (int i = 0; i < 3; i++) {
			int score = 30;
			System.out.println("문제 " + (i + 1)+"\t\t\t\t[0]뒤로가기");
			while (true) {
//				neonIm.neonfile(num.get(i) + 1);/*문제*/
				neonIm.printImage(dao.neonGame().get(num.get(i)).getQ());/* 문제 */
				System.out.print("정답을 입력하세요: ");
				String userAnswer = sc.nextLine();
				if(userAnswer.equals("0")) {
					return;
				}
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
						timeDelay(2);
						break;
					}
				}
				timeDelay(2);
			}
			System.out.println("획득점수" + score);
			sum += score;

		}

		System.out.println("게임 종료! 최종 점수는 " + sum + "점 입니다.");
		rank.ranksys(3, id, sum);

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
	public void timeDelay(int i) {
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	public void slowPrint(String text, int delay) {
        for (int i = 0; i < text.length(); i++) {
            System.out.print(text.charAt(i));
            try {
                Thread.sleep(delay); // 지정한 시간만큼 대기
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(); // 마지막에 개행 출력
    }
	

}
