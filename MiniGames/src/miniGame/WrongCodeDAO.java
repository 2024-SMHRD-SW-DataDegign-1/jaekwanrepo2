package miniGame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WrongCodeDAO {
	// DAO : Data Access Object
	// DB에 관련된 기능들을 전부 가지고 있는 클래스
	Connection conn = null;
	PreparedStatement psmt = null;

	// 연결
	public void conn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@project-db-campus.smhrd.com:1524:xe";

			String user = "campus_24SW_DD_p1_3";

			String password = "smhrd3";

			conn = DriverManager.getConnection(url, user, password);

			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("조회 실패");
		}
	}

	// 닫기
	public void dbClose() {

		try {
			if (psmt != null) {
				psmt.close();
			}

			if (conn != null)
				conn.close();
			System.out.println("연결 종료");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("종료 실패");
		}

	}

	// 초기값 입력
	public int insertInit(String id) {

		String sql = "update rank set codescore = 2400 where id = ?";
		int row = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			row = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return row;
	}
	// 연봉 업데이트
	public int updateUser(String id, int salary) {
		int row = 0;
		try {
			String sql = "UPDATE rank SET codescore = ? WHERE id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, salary);
			psmt.setString(2, id);

			row = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return row;
	}

	//select 문 -- 유저 조회
	public int checkUser(String inputId) {
		String sql = "select codescore from rank where id = ?";
		ResultSet rs = null;
		int salary = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, inputId); // 물음표에 값을 넣음
			

			// executeQuery --> 쿼리문을 통해서 테이블에 있는 데이터에 영향을 끼치지 않을때 사용 되어진다!!
			rs = psmt.executeQuery();

			// rs.next() : 다음행으로 커서를 내리고 데이터가 있는지 확인하는 기능
			if (rs.next()) { // 다음행에서 데이터가 있으면 true 없으면 false
				// rs.getString(2) : 결과데이터중 2번째 컬럼에 있는 데이터를 문자열로 받아오겠습니다
				salary = rs.getInt(1);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return salary;
	}	
	//select 문 -- 정답 조회
	public String checkAnswer(int questionumber) {
		String sql = "select answer from answer where questionumber = ?";
		ResultSet rs = null;
		String answer = null;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, questionumber); // 물음표에 값을 넣음
			

			// executeQuery --> 쿼리문을 통해서 테이블에 있는 데이터에 영향을 끼치지 않을때 사용 되어진다!!
			rs = psmt.executeQuery();

			// rs.next() : 다음행으로 커서를 내리고 데이터가 있는지 확인하는 기능
			if (rs.next()) { // 다음행에서 데이터가 있으면 true 없으면 false
				// rs.getString(2) : 결과데이터중 2번째 컬럼에 있는 데이터를 문자열로 받아오겠습니다
				answer = rs.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return answer;
	}
	// 힌트 출력용
	public int checkRrow(int questionumber) {
		String sql = "select rrow from answer where questionumber = ?";
		ResultSet rs = null;
		int rrow = 0;
		try {
			conn();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, questionumber); // 물음표에 값을 넣음
			
			
			// executeQuery --> 쿼리문을 통해서 테이블에 있는 데이터에 영향을 끼치지 않을때 사용 되어진다!!
			rs = psmt.executeQuery();
			
			// rs.next() : 다음행으로 커서를 내리고 데이터가 있는지 확인하는 기능
			if (rs.next()) { // 다음행에서 데이터가 있으면 true 없으면 false
				// rs.getString(2) : 결과데이터중 2번째 컬럼에 있는 데이터를 문자열로 받아오겠습니다
				rrow = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rrow;
	}
		
	

}