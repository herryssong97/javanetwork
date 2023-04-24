package adminUpdateDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import adminUpdateVO.AdminMemUpdateVO;
import record101DBConn.Record101DBConn;

public class AdminMemUpdateDAO {

	private Connection con;
	PreparedStatement pstmt = null;
	Statement st = null;
	ResultSet rs = null;

	public AdminMemUpdateDAO() {
		try {
			con = new Record101DBConn().getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 전체 회원 조회
	public ArrayList<AdminMemUpdateVO> getAllInfo() throws SQLException, ClassNotFoundException {
		ArrayList<AdminMemUpdateVO> infoArray = new ArrayList<AdminMemUpdateVO>();

		String sql = "select * from GUEST_INFO";

		st = con.createStatement();
		rs = st.executeQuery(sql);

		while (rs.next()) {
			String guestName = rs.getString("guest_name");
			String guestId = rs.getString("guest_id");
			String guestPw = rs.getString("guest_pw");
			String phone = rs.getString("phone");

			AdminMemUpdateVO info = new AdminMemUpdateVO(guestName, guestId, guestPw, phone);
			infoArray.add(info);
		}
		return infoArray;
	}// getAllInfo-end

	// 특정 회원 검색
	public ArrayList<AdminMemUpdateVO> searchByName(String gstname) throws SQLException, ClassNotFoundException {
		ArrayList<AdminMemUpdateVO> nArray = new ArrayList<AdminMemUpdateVO>();

		String sql = "select * from GUEST_INFO" + " where lower(guest_name) like '%' ||?|| '%'";

		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, gstname);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			String guestName = rs.getString("guest_name");
			String guestId = rs.getString("guest_id");
			String guestPw = rs.getString("guest_pw");
			String phone = rs.getString("phone");

			AdminMemUpdateVO amvo = new AdminMemUpdateVO(guestName, guestId, guestPw, phone);
			nArray.add(amvo);
		}
		return nArray;
	}// searchByName-end

	// 회원이름 수정(비속어 등등의 경우..)
	public boolean guest_update(String uname, String bname) {

		String sql = "update GUEST_INFO set guest_name = ?" + " where guest_name = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, uname);
			pstmt.setString(2, bname);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("오류 발생");
			return false;
		}

		return true;
	}// guest_update-end

}
