package miniGame;

import java.util.Scanner;

public class WrongCodeRun {

	Scanner sc = new Scanner(System.in);
	Function fx = new Function();
	int wordSpeed = 1;
	int hint = 2;
	int streak = 0;
	int life = 3;
	int salary = 0;
	String title = null;

	String code = null;
	int inputRow = 0;
	WrongCodeDAO wrongCodeDAO = new WrongCodeDAO();
	int plus;
	int minus;
	int indexS;
	int indexE;

	public int getSalary() {
		return salary;
	}

	public void Run(String id, int salary, int sel) {
		if (sel == 1) {
			indexS = 1;
			indexE = 5;
			plus = 400;
			minus = 200;
		} else if (sel == 2) {
			indexS = 6;
			indexE = 9;
			plus = 600;
			minus = 300;
		} else if (sel == 3) {
			indexS = 10;
			indexE = 12;
			plus = 1000;
			minus = 500;
		}
		for (int i = indexS; i <= indexE; i++) {
			String filePath = ".\\txt\\" + i + ".txt";
			System.out.println("===== " + i + "번 문제 =====");
			fx.loadQuestionTxt(filePath); // 문제 출력
			int rrow = wrongCodeDAO.checkRrow(i); // 정답행 반환
			System.out.println("\n[0][힌트보기] : " + hint + "회 남음\t[현재 목숨] : " + life + "\t[현재 연봉] : " + salary + "만원"
					+ "\t[-1]뒤로가기");
			System.out.print("\n잘못된 행 입력 : ");
			inputRow = sc.nextInt();
			if (inputRow == 0) {
				if (hint > 0) {
					System.out.println("힌트 : " + rrow + "행");
					hint--;
				} else {
					fx.slowPrint("사용 가능한 힌트가 없습니다.", wordSpeed);
				}
			}
			if (inputRow == -1) {
				// 뒤로가기 버튼
				System.out.println("게임 종료");
				break;
			}
			System.out.print("올바른 코드 입력 : ");
			code = sc.nextLine(); // nextLine 씹힘 방지
			code = sc.nextLine();

			// 정답을 db에서 가져오기
			if (code.equals(wrongCodeDAO.checkAnswer(i))) {
				fx.slowPrint("성공적으로 처리했습니다! \n[연봉] +" + plus + "만원!!", wordSpeed);
				salary += plus;
				// 맞춘 문제는 userdata에 기록함 update userdata set questionumber = i , ox = o)
				if (++streak == 2) {
					++hint;
					streak = 0;
					fx.slowPrint(id + "! 일처리 아주 잘하는구만! 2연 성공 보너스! [연봉] + " + (plus / 2) + "만원!!", wordSpeed);
					salary += plus / 2;
				}

			} else {
				fx.slowPrint("정답이 아닙니다. [연봉] -" + minus + "만원" + "\t 현재 목숨 : " + --life, wordSpeed);
				salary -= minus;
				streak = 0;
			}
			if (life == 0 || salary < 0) {
				fx.slowPrint("해고되셨습니다. 다시 스마트인재개발원으로 돌아갑니다.", wordSpeed); // 해고.mp3
				fx.slowPrint("예진쌤 : 자, 이게 클릭이야", 150); // 무한도전.mp3
				// 게임오버 시 연봉초기화
				salary = 2400;
				life = 3;
				hint = 2;
				break;
			}

		}
		this.salary = salary; // 게임 종료 시 main에 salary값 전달을 위함
	}

	public void rule() {
		fx.slowPrint("========= 게임 규칙 ========\n", wordSpeed);
		fx.slowPrint("1. 제시되는 코드들은 어딘가 에러가 발생하는 코드들입니다.\n 이 게임의 목표는 이 코드의 오류난 행을 찾아 올바르게 고치는 것입니다.\n", wordSpeed);
		fx.slowPrint("2. 한 문제당 하나의 에러가 있습니다.\n", wordSpeed);
		fx.slowPrint("3. 정답을 입력할 때에는 에러가 발생한 행과 수정된 코드를 입력하시면 됩니다.\n", wordSpeed);
		fx.slowPrint("4. 단, 모든 코드를 입력할 때에는 불필요한 띄어쓰기를 허용하지 않습니다. ", wordSpeed);
		System.out.println("========================\n\n");
	}
}
