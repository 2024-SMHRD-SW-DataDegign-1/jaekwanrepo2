package miniGame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WordGameDAO {

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

    // 단어정보 불러오기
    public ArrayList<WordGameDTO> wordData() {
        String sql = "select * from words";
        ResultSet rs = null;
        ArrayList<WordGameDTO> list = new ArrayList<WordGameDTO>();

        try {
            conn();
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();

            while (rs.next()) {
                String right = rs.getString(1);
                String firstWord = rs.getString(2);
                String hint1 = rs.getString(3);
                String hint2 = rs.getString(4);
                list.add(new WordGameDTO(right, firstWord, hint1, hint2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbClose();
        }

        return list;
    }
}
