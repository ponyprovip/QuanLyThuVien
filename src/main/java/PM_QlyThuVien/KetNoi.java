package PM_QlyThuVien;

import java.sql.Connection;
import java.sql.DriverManager;

public class KetNoi {
    public Connection  getConnection() throws ClassNotFoundException{
       Connection con = null;
       try {
           Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           String url = "jdbc:sqlserver://localhost\\HP:1433;databaseName=qlytvien;encrypt=true;trustServerCertificate=true;user=sa;password=572311";
           con = DriverManager.getConnection(url);
       } catch (Exception e) {
       }
       return con;
   }
}
