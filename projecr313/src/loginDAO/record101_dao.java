package loginDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import loginConn.record101_conn;

public class record101_dao {

	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public record101_dao() throws ClassNotFoundException, SQLException {
		con = new record101_conn().getConnection();

	}

	public boolean insert_cli(String cli_name, String cli_id, String cli_pw, String cli_phone) {
		String sql = "insert into client101 values(?,?,?,?)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cli_name);
			pstmt.setString(2, cli_id);
			pstmt.setString(3, cli_pw);
			pstmt.setString(4, cli_phone);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block

			return false;
		}
		return true;

	}

	public boolean login(String cli_id, String cli_pw) throws SQLException {
		boolean b = false;
		String sql = "SELECT * FROM client101 WHERE cli_id=? and cli_pw=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, cli_id);
		pstmt.setString(2, cli_pw);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			b = true;
		}
		return b;

	}

	public boolean check(String cli_id) {

		String sql = "SELECT count(cli_id) FROM CLIENT101 WHERE cli_id=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cli_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int cnt = rs.getInt("count(cli_id)");
				if (cnt > 0) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
}
