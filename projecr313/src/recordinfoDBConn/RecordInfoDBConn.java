package recordinfoDBConn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RecordInfoDBConn {

	private Connection con; // 立加按眉 con
	
	public Connection getConnection() {
		return con;	
	}
	
	// default积己磊
	public RecordInfoDBConn() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
											// 荐沥且 何盒
	}
}
