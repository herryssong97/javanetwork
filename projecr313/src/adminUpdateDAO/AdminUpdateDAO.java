package adminUpdateDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import adminUpdateVO.AdminUpdateVO;
import record101DBConn.Record101DBConn;

public class AdminUpdateDAO {

	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Statement st = null;

	public AdminUpdateDAO() {
		try {
			con = new Record101DBConn().getConnection();
		} catch (Exception e) {
		}
	}

	public boolean cd_insert(String cdName, String cdOrder, int cdPrice, String cdDate, int cdSoo) {
																	// 삽입
		String sql = "insert into RECORD101 values(?, ?, ?, ?, ?)";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, cdName);
			pstmt.setString(2, cdOrder);
			pstmt.setInt(3, cdPrice);
			pstmt.setString(4, cdDate);
			pstmt.setInt(5, cdSoo);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("insertion error");
			return false;
		}

		return true;
	}// cd_insert-end

	public boolean cd_update(int updateSoo, String cdName) { // 이름 검색으로 가격 수정

		String sql = "update RECORD101 set cd_soo = ?" + " where lower(cd_name) = lower(?)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, updateSoo);
			pstmt.setString(2, cdName);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("오류 발생");
			return false;
		}
		return true;

	}// cd_update-end

	public boolean cd_delete(String cdName) {	// 삭제
		String sql = "delete from RECORD101 where lower(cd_name) = lower(?)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cdName);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("delete error");
			return false;
		}

		return true;
	}// cd_delete-end

	

	// 제목 검색으로 결과 띄우기
	public ArrayList <AdminUpdateVO> searchByName(String cdname) throws SQLException, ClassNotFoundException {
		ArrayList <AdminUpdateVO> nArray = new ArrayList<AdminUpdateVO>();
		
		String sql = "select * from RECORD101"
				+ " where lower(cd_name) like '%' ||lower(?)|| '%'";
		
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, cdname);
		rs = pstmt.executeQuery();
		
		//System.out.println("화면에서 입력받아 dao에서 넘겨주는 값 : "+cdname);
		
		while(rs.next()) {
			String cdName = rs.getString("cd_name");
			String cdOrder = rs.getString("cd_order");
			int cdPrice = rs.getInt("cd_price");
			String cdDate = rs.getString("cd_date");
			int cdSoo = rs.getInt("cd_soo");
			
//			System.out.println( "DB에 들어가는 값 : " + 
//					cdName + "\t" + cdOrder + "\t" + cdPrice  + "\t" + cdDate  + "\t" + cdSoo);
			
			AdminUpdateVO auvo = new AdminUpdateVO(cdName, cdOrder, cdPrice, cdDate, cdSoo);
			nArray.add(auvo);
		}
		return nArray;
		
	}
	
	// 가수 검색으로 결과 띄우기
		public ArrayList <AdminUpdateVO> searchByOrder(String cdorder) throws SQLException, ClassNotFoundException {
			ArrayList <AdminUpdateVO> oArray = new ArrayList<AdminUpdateVO>();
			
			String sql = "select * from RECORD101"
					+ " where lower(cd_order) like '%' ||lower(?)|| '%'";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cdorder);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String cdName = rs.getString("cd_name");
				String cdOrder = rs.getString("cd_order");
				int cdPrice = rs.getInt("cd_price");
				String cdDate = rs.getString("cd_date");
				int cdSoo = rs.getInt("cd_soo");
				
				AdminUpdateVO order = new AdminUpdateVO(cdName, cdOrder, cdPrice, cdDate, cdSoo);
				oArray.add(order);
			}
			return oArray;
			
		}
	
	public ArrayList <AdminUpdateVO> getAllInfo() throws SQLException, ClassNotFoundException {
		ArrayList <AdminUpdateVO> infoArray = new ArrayList<AdminUpdateVO>();
		
		String sql = "select * from RECORD101";
		
		st = con.createStatement();
		rs = st.executeQuery(sql);
		
		while(rs.next()) {
			String cdName = rs.getString("cd_name");
			String cdOrder = rs.getString("cd_order");
			int cdPrice = rs.getInt("cd_price");
			String cdDate = rs.getString("cd_date");
			int cdSoo = rs.getInt("cd_soo");
			
			AdminUpdateVO info = new AdminUpdateVO(cdName, cdOrder, cdPrice, cdDate, cdSoo);
			infoArray.add(info);
		}
		
		return infoArray;
	}
	
	


}
