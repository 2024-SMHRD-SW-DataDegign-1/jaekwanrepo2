package miniGame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBDAO {

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
		}
		if(conn!=null) {
			System.out.println("연결성공");
		}else {
			System.out.println("연결성공");
		}
		

	}

	// 닫기
	public void dbClose() {
		try {
			if (psmt != null)
				psmt.close();

			if (conn != null)
				conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(psmt==null) {
			System.out.println("반환성공");
		}else{
			System.out.println("반환실패");
		}

	}

	// 회원가입
	public int insertUser(String id, String pw, String name) {
		int row = 0;
		String sql = "INSERT INTO DATAMEMBER VALUES(?,?,?)";

		try {
			conn();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			psmt.setString(3, name);

			row = psmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbClose();
		}

		return row;

	}

	// 로그인
	public String loginUser(String id, String pw) {

		String sql = "select name from datamember where id = ? and pw = ?";
		ResultSet rs = null;

		String name = "";

		try {
			conn();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);

			rs = psmt.executeQuery();

			if (rs.next()) {
				name = rs.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}

		return name;

	}

	// 회원 이름 수정
	public int updateUser(String id, String name) {
		int row = 0;
		String sql = "UPDATE DATAMEMBER SET NAME = ? WHERE ID = ?";

		try {
			conn();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, name);
			psmt.setString(2, id);

			row = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}

		return row;

	}

}
