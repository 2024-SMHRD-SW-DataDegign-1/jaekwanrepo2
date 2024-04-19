package miniGame;

import miniGame.DBDTO;

public class WrongCodeMain extends WrongCodeRun{
	RankDAO rank = new RankDAO();
	
	

	public void wrongCodeMain(DBDTO user) {

		WrongCodeRun wcr = new WrongCodeRun();

		String intro = "당신은 이클립스의 소중함을 깨닫지 못하고 함부로 코딩을 해온 개발자였습니다.\r\n" + "\r\n"
				+ "자, 이제 당신은 콘솔창이라는 척박한 환경에서 개발을 해야하는 개발자입니다.\r\n" + "당신은 에러를 잡아내지 못하면 회사에서 짤립니다.\r\n" + "\r\n"
				+ "이클립스의 도움없이 에러를 잡아내십시오.";
		fx.slowPrint(intro, 50); // 인트로 한글자씩 천천히 출력
		String id = user.getId();
		System.out.print("유저 데이터 조회 중");
		fx.slowPrint("...", 300);
		WrongCodeDAO wcDAO = new WrongCodeDAO();
		wcDAO.conn();
		// 1. 접속한 아이디로 select salary from userdata where id 검색
		// 2. 연봉 확인 후 칭호 결정
		int checkedSalary = wcDAO.checkUser(id); // dao.checkUser(id)가 너무 많이 사용되서 db에 무리가 오므로 변수로 선언 필요
		if (checkedSalary < 3600) {
			title = "주니어 개발자";
		} else if (checkedSalary < 6000) {
			title = "시니어 개발자";
		} else {
			title = "사이버넷";
		}

		if (checkedSalary == 0) {
			fx.slowPrint("새로 오셨군요! " + id + "님 반갑습니다!", wordSpeed);
			// 3. 검색결과 rs.next() == 0이면 insert into userdata(id,salary) values (getId,2400)
			// --> 신규 등록
			// id 및 salary DB전송
			wcDAO.insertInit(id);

		} else {
			// 4. 접속 로그가 있으면
			fx.slowPrint("다시 오셨군요! " + title + " " + id + "님 기다리고 있었습니다.", wordSpeed);
		}
		// 게임 연봉 초기값을 db에 저장된 값 으로 초기화
		int salary = wcDAO.checkUser(id);
		// 난이도 선택
		while (true) {
			System.out.println("[1]EASY \t [2]NORMAL \t [3]HARD \t [4]게임 규칙 \t [5]뒤로가기");
			int sel = sc.nextInt();
			if(salary < 3600 && sel==2) { // normal 난이도 불가능
				fx.slowPrint("NORMAL 난이도는 시니어 개발자 이상 이용 가능합니다.", wordSpeed);
				continue;
			}
			if(salary < 6000 && sel==3) {
				fx.slowPrint("HARD 난이도는 사이버넷 이상 이용 가능합니다.", wordSpeed);
				continue;
			}
			if(sel==5) {
				break;
			}
			System.out.print("Loading");
			fx.slowPrint("...", 300);
			// 게임 시작
			if (sel >= 1 && sel <=3) {
				wcr.Run(id,salary,sel);
				salary = wcr.getSalary();
			}else if(sel == 4) {
				wcr.rule();
				continue;
			}
			System.out.println("게임이 종료되었습니다.");
			if (life != 0 && salary < 2000) {
				System.out.println("현재 연봉 : " + salary + "만원");
			} 
				
		}
		wcDAO.updateUser(id, salary);
		wcDAO.dbClose();
		rank.ranksys(2,id,salary);

	}

}
