package miniGame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NeonDAO {


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

	}
	//DB문제 넌센스이미지 불러오기
	public ArrayList<NeonDTO> neonGame() {
//		String q= null;
		String sql = "select * from imagegame ";
		ResultSet rs = null; 
		MusicGameDTO dto = null;
		ArrayList<NeonDTO> Quizlist = new ArrayList<NeonDTO>();

		try {
			conn();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				NeonDTO q = new NeonDTO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
//				String a = rs.getString(2);
//				String hint1 = rs.getString(3);
//				String hint2 = rs.getString(4);
				Quizlist.add(q);
				

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbClose();
		}

		return Quizlist;
	}
}

	
	

