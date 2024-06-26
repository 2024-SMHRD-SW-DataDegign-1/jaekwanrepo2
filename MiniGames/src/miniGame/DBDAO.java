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

   // 회원가입
   public int insertUser(String id, String pw, String name) {
      int row = 0;
      ResultSet rs = null; //
      String checkID = null;
      String sql = "INSERT INTO DATAMEMBER VALUES(?,?,?)";
      String sql2 = "INSERT INTO RANK VALUES(?,0,0,0,0)";
      String sql3 = "select id from Datamember where id = ?"; //
      try {
         conn();
         psmt = conn.prepareStatement(sql3);
         psmt.setString(1, id); // 물음표에 값을 넣음
         rs = psmt.executeQuery();
         if (rs.next()) {
            checkID = rs.getString(1);
         }

         if (checkID == null) {
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, id);
            psmt.setString(2, pw);
            psmt.setString(3, name);
            row = psmt.executeUpdate();

            psmt = conn.prepareStatement(sql2);
            psmt.setString(1, id);
            row = psmt.executeUpdate();
         }else {
            System.out.println("아이디가 중복됩니다.");
         }

      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         dbClose();
      }

      return row;

   }

   // 로그인
   public DBDTO loginUser(String id, String pw) {

      String sql = "select * from datamember where id = ? and pw = ?";
      ResultSet rs = null;
      DBDTO user = null;

      // String name = "";

      try {
         conn();
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, id);
         psmt.setString(2, pw);

         rs = psmt.executeQuery();
         if (rs.next()) {
            user = new DBDTO(id, pw, rs.getString(3));
         }

      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         dbClose();
      }

      return user;

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
