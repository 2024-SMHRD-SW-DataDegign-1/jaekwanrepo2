package miniGame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RankDAO {

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

	// 랭크 저장
	public void rank(int gameNum, String id, int preScore) {
		String sql = "";
		String sql2 = "";
		if(gameNum==1) {
		sql = "select songscore from rank where id = ?";
		sql2 = "UPDATE rank SET songscore = ? WHERE ID = ?";
		}
		else if(gameNum==2) {
			sql = "select codescore from rank where id = ?";
			sql2 = "UPDATE rank SET codescore = ? WHERE ID = ?";
		}
		else if(gameNum==3) {
			sql = "select imagecore from rank where id = ?";
			sql2 = "UPDATE rank SET imagecore = ? WHERE ID = ?";
		}
		else if(gameNum==4) {
			sql = "select wordscore from rank where id = ?";
			sql2 = "UPDATE rank SET wordscore = ? WHERE ID = ?";
		}else {
			System.out.println("게임종류를 찾을수 없다");
			System.exit(0);
		}
		ResultSet rs = null;
		int score = 0;
		int row = 0;

		try {
			conn();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);

			rs = psmt.executeQuery();
			if (rs.next()) {
				score = rs.getInt(1);
			}
			if (score < preScore) {
				psmt = conn.prepareStatement(sql2);
				psmt.setInt(1, preScore);
				psmt.setString(2, id);
				row = psmt.executeUpdate();

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}

	}

	// 랭크조회
	public ArrayList<RankDTO> searchRank(int gameNum) {
		ArrayList<RankDTO> rank = new ArrayList<RankDTO>();
		String sql = "";
		if(gameNum == 1) {
			sql = "select * from rank order by songscore desc";
		}
		else if(gameNum == 2) {
			sql = "select * from rank order by codescore desc";
		}
		else if(gameNum == 3) {
			sql = "select * from rank order by imagecore desc";
		}
		else if(gameNum == 4) {
			sql = "select * from rank order by wordscore desc";
		}
		ResultSet rs = null;

		try {
			conn();
			psmt = conn.prepareStatement(sql);
			//psmt.setString(1, gameName);
			//psmt.setString(2, gameName);
			rs = psmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString(1);
				int musicScore = rs.getInt(2);
				int codeScore = rs.getInt(3);
				int imageScore = rs.getInt(4);
				int wordScore = rs.getInt(5);
				rank.add(new RankDTO(id,musicScore,codeScore,imageScore,wordScore));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}

		return rank;
	}
	
	
	public void ranksys(int num,String id,int sum) {
		//랭크 입력
		rank(num, id, sum);
		
		//랭크 출력
		ArrayList<RankDTO> result = searchRank(num);
		System.out.println("--------------------------------------------------");
		if (num == 1) {
			for (RankDTO i : result) {
				System.out.println("\t"+i.getId() + "\t" + i.getMusicScore());
			}
		}
		else if (num == 2) {
			for (RankDTO i : result) {
				System.out.println("\t"+i.getId() + "\t" + i.getCodeScore());
			}
		}
		else if (num == 3) {
			for (RankDTO i : result) {
				System.out.println("\t"+i.getId() + "\t" + i.getImageScore());
			}
		}
		else if (num == 4) {
			for (RankDTO i : result) {
				System.out.println("\t"+i.getId() + "\t" + i.getWordScore());
			}
		}
		
		
	}
	
	public void ranksys(int num) {
		ArrayList<RankDTO> result = searchRank(num);
		System.out.println("--------------------------------------------------");
		if (num == 1) {
			for (RankDTO i : result) {
				System.out.println("\t\t"+i.getId() + "\t" + i.getMusicScore());
			}
		}
		else if (num == 2) {
			for (RankDTO i : result) {
				System.out.println("\t\t"+i.getId() + "\t" + i.getCodeScore());
			}
		}
		else if (num == 3) {
			for (RankDTO i : result) {
				System.out.println("\t\t"+i.getId() + "\t" + i.getImageScore());
			}
		}
		else if (num == 4) {
			for (RankDTO i : result) {
				System.out.println("\t\t"+i.getId() + "\t" + i.getWordScore());
			}
		}
	}
	

}
