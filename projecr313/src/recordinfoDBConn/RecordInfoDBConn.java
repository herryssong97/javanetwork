package recordinfoDBConn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RecordInfoDBConn {

	private Connection con; // ���Ӱ�ü con
	
	public Connection getConnection() {
		return con;	
	}
	
	// default������
	public RecordInfoDBConn() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
											// ������ �κ�
	}
}
