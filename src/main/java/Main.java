import java.sql.*;

public class Main {
    public static void main(String... args){
        try {
            String url = "jdbc:db2://localhost:50000/SAMPLE";
            String user = "User";
            String pass = "Password";

            Class.forName("com.ibm.db2.jcc.DB2Driver");

            Connection con = DriverManager.getConnection(url, user, pass);

            // テーブルの作成
            Statement stmt = con.createStatement();
            stmt.execute("CREATE TABLE test(id int, name varchar(100))");

            // 登録
            String sql = "INSERT INTO test(id, name) VALUES (?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, 100);
            pstmt.setString(2, "登録テスト");
            pstmt.execute();

            // 検索
            sql = "SELECT * FROM test WHERE (id = ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, 100);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                System.out.println(rs.getInt("id"));
                System.out.println(rs.getString("name"));
            }

            // テーブル削除
            stmt.execute("DROP TABLE test");

            con.close();

            System.out.println("接続成功");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
