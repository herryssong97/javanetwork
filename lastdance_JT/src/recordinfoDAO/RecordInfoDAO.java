package recordinfoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import record101DBConn.Record101DBConn;
//import recordinfoDBConn.RecordInfoDBConn;
import recordinfoVO.RecordInfoVO;

public class RecordInfoDAO {
	
	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs1 = null;
	//-----------------------
	
	public RecordInfoDAO() throws ClassNotFoundException, SQLException {
		con = new Record101DBConn().getConnection();
	} // DAO-생성자-end
	

	//전체 출력-----------------------
	// db에 있는 것을 꺼내 모두 ArrayList에 넣을 것
	public ArrayList <RecordInfoVO> getAllInfo(String word) throws SQLException, ClassNotFoundException {
		// RecordSearch에서 cd = searchTxt.getText(); 입력받은 음원의 이름은 파라미터로~
		//  getAllInfo(String word) 인자로 받는다
		
		// RecordSearch.java > getAllInfo(cd)
		// --------> RecordInfoDAO.java > getAllInfo(String word)
		
		ArrayList<RecordInfoVO> recordArr = new ArrayList<RecordInfoVO>();
		// recordArr 객체에 RecordInfoVO의 모든 속성을 담는다
		
		String sql = "select * from RECORD101"
				+ " where lower(cd_name) like '%' ||?|| '%'";

		////////////////////////////////////////////////////////////////////////
		
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, word); // word == 음원의 이름
		rs1 = pstmt.executeQuery(); // 출력만 executeQuery ******** 1) 전체 음원을 DB안에 넣는다
		
		System.out.println("화면에서 입력받아 DAO 에서 넘겨주는 값 : "+ word);
		

		while (rs1.next()) { // 2) rs1에 값이 있는 동안~ DB안에서 음원 하나씩 꺼내서 
			
			String cdName = rs1.getString("cd_name");
			String cdOrder = rs1.getString("cd_order");
			String cdPrice = rs1.getString("cd_price");
			String cdDate = rs1.getString("cd_date");
			int cdSoo = rs1.getInt("cd_soo");
			
			// db에 잘 들어가는지 확인
			System.out.println( "DB에 들어가는 값 : " + 
					cdName + "\t" + cdOrder + "\t" + cdPrice  + "\t" + cdDate  + "\t" + cdSoo);
			
			RecordInfoVO record = new RecordInfoVO(cdName, cdOrder, cdPrice, cdDate, cdSoo); 
			// 3) record객체 안에 5가지를 초기치로 넣어 RecordInfoVO의 인자가 있는 생성자로 간다
			
			recordArr.add(record); // 4) record객체(1음원 정보)를 recordArr 리스트안에 넣는다
							 // 5) DB안의 음원이 다 할때 까지 이 작업을 반복한다
							 //		ex) 100명이면 100번을 작업해 리스트에는 100명의 정보가 들어가게 된다
		}

		return recordArr; // recordArr에 넘겨준다
		
	}	//전체 출력-end -----------------------

	
	//-- update : 수정 --------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------
	
	public boolean update_soo (int soo, String word, String singer) {
		// 											잔여 수량 - 구매할 수량 = 업데이트!
		String sql = "update RECORD101 set cd_soo = cd_soo - ?"
				+ " where lower(cd_name) like '%' ||lower(?)|| '%'"
				+ " and lower(cd_order) like '%' ||lower(?)|| '%'";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, soo);
			pstmt.setString(2, word);
			pstmt.setString(3, singer);
			
			pstmt.executeUpdate(); // DB에 수정 명령
			
		} catch (SQLException e) {
			System.out.println("update error");
			return false;
		}		
		
		// error가 안나면 true를 넘김 : boolen 으로 메서드를 만들어주면 return true/false 필수
		return true;
	} // update-end
	

	//-- 찾아라  --------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------

	public boolean find_order (String singer) {
		// 											
		String sql = "select cd_order, count(*)"
				+ " from RECORD101"
				+ " where  lower(cd_order) like '%' ||lower(?)|| '%' group by cd_order";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, singer); // cd == 음원의 이름

			System.out.println( "DB에 들어가는 값 : " + 
					singer + "\t" );
			String no = "0";
			// 가수명 cnt 갯수가 0이면 false 로 가야하는데..
			if(sql==no) {
				return false;
			}
			
			
			rs1 = pstmt.executeQuery(); // 출력만 executeQuery
			
			
			
			

			
			// 
		} catch (SQLException e) {
			System.out.println("find error");
			return false;
		}		
		
		// error가 안나면 true를 넘김 : boolen 으로 메서드를 만들어주면 return true/false 필수
		return true;
	} // update-end
	
	
	
	
	
	
	
	

} // RecordInfoDAO-end
