package miniGame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class MusicGameDAO {

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

	// 노래 정보 불러오기
	public ArrayList<MusicGameDTO> songData() {
		String sql = "select * from song";
		ResultSet rs = null; 
		MusicGameDTO dto = null;
		ArrayList<MusicGameDTO> list = new ArrayList<MusicGameDTO>();

		try {
			conn();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				String path = rs.getString(1);
				String title = rs.getString(2);
				String singer = rs.getString(3);
				list.add(new MusicGameDTO(path, title, singer));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbClose();
		}

		return list;
	}

}
