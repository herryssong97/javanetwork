package record101DBConn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Record101DBConn {
	
	private Connection con;
	
	public Connection getConnection() {
		return con;
	}
	
	public Record101DBConn() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
	}
	
}
