package javanetwork;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import recordinfoDAO.RecordInfoDAO;
import recordinfoVO.RecordInfoVO;

public class RecordSearch extends JFrame {

	
	private JPanel contentPane;
	
	public JTextArea resultArea; //////////// 
	public JTextField searchTxt; //////////// DAO에 값을 파라미터로 넘겨줘야해서
	public JTextField buySet;	 //////////// public 으로 해줘야 한다.
	public JTextField whoBuy;  //////////// 

	public String cd; // 상단 : 구매할 음원 이름
	public int sooBuy;	  // 하단 : 구매할 음원 수량
	public String orderWho;  // 하단 : 어떤 가수냐고 묻는 창
	
	public int ddefault; // 기존 음원 수량
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					RecordSearch frame = new RecordSearch();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public RecordSearch() throws ClassNotFoundException, SQLException {
		
		RecordInfoDAO reDao = new RecordInfoDAO();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 935, 617);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		//--------------------- TextArea title
		JLabel resultTit = new JLabel("\uC74C\uC6D0 \uBA85          \uAC00\uC218           \uAC00\uACA9           \uCD9C\uC2DC\uC77C        \uC794\uC5EC\uC218\uB7C9");
		resultTit.setFont(new Font("굴림", Font.PLAIN, 15));
		resultTit.setBounds(396, 213, 426, 23);
		contentPane.add(resultTit);
		
		//--------------------- TextArea 결과출력
		resultArea = new JTextArea();
		
		resultArea.setLineWrap(true); // 개행
		
		//--------------------- Scroll 결과출력
		JScrollPane scrollPane = new JScrollPane(resultArea);
		scrollPane.setBounds(396, 246, 416, 169);
		contentPane.add(scrollPane);

		//--------------------- CD명 입력
		JLabel searchCd = new JLabel("CD \uBA85 \uC785\uB825 : ");
		searchCd.setFont(new Font("굴림", Font.PLAIN, 15));
		searchCd.setBounds(303, 134, 97, 35);
		contentPane.add(searchCd);
		
		//--------------------- 검색입력창
		searchTxt = new JTextField(cd);
		searchTxt.setBounds(402, 134, 286, 35);
		contentPane.add(searchTxt);
		searchTxt.setColumns(10);

		//--------------------- 검색 버튼
		JButton searchBtn = new JButton("\uAC80\uC0C9");
		searchBtn.addMouseListener(new MouseAdapter() { //////////////////////////////////////////////////////
			@Override
			public void mouseClicked(MouseEvent e) {
				// 검색 버튼 누를때 마다 textarea 초기화 //////////////////////////////////////
				resultArea.selectAll();
				resultArea.replaceSelection("");
				
//				cd = "";
				//RecordInfoDAO reDao = new RecordInfoDAO();
				/*//////////////////////////////////////////////////////////////////////
				 * 검색버튼 mouseClicked --> 하단에 음원정보가 출력되게 한다
				 *//////////////////////////////////////////////////////////////////////
				
				cd = searchTxt.getText(); //--------------------- cd명을 입력 받는다..........
				
				System.out.println("화면에서 입력받음 : " + cd);
				// 예외처리
				if (cd.length() < 2) {
					JOptionPane.showMessageDialog(null,"2자 이상 입력해주세요.", "Error", JOptionPane.ERROR_MESSAGE);
					System.out.println("2자 이상 입력해주세요.");
				} 
				else {// 구매가 가능할때

					try {
						// 속성들이 들어있는 VO를 'recordArr' 명인 리스트로 만들어준다
						// Dao에서 출력되는 모든 cd(음원명)들은 recordArr에 담긴다
						// 차례로 값이 있다면 출력.......
						// 
						ArrayList <RecordInfoVO> recordArr = reDao.getAllInfo(cd); 
						for(RecordInfoVO imsi : recordArr) {

							if (imsi.getCdSoo() >= 1) { // 만일 재고가 있으면
								resultArea.append(imsi.getCdName()+"\t"+imsi.getCdOrder()+"\t"+
										imsi.getCdPrice()+"\t"+imsi.getCdDate()+"\t" + imsi.getCdSoo()+ "\r\n");	
							} 
							
							else { // 재고가 없으면 --> 얘도 보고 지워도 될듯
								resultArea.setText(imsi.getCdName()+"\t"+imsi.getCdOrder()+"\t"+
										imsi.getCdPrice()+"\t"+imsi.getCdDate()+"\t"+imsi.getCdSoo());		
								
								
//								if() {
//									
//								}
							}
//							테스트 후 밑은 지우기
							///////////////////////////////////////////////////////////////////////////
							///////////////////////////////////////////////////////////////////////////
							System.out.println("화면에 입력한 값 : " + cd);
							// 화면 출력 확인 (입력한 값이 db에서 나오는 중)
							System.out.println( "화면에 출력되는 값 : " + 
									imsi.getCdName() + "\t" + imsi.getCdOrder() + "\t" 
							+ imsi.getCdPrice()  + "\t" + imsi.getCdDate()  + "\t" + imsi.getCdSoo());
//							
						} 
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
					}
					
				}
				
				//---------------------------------------------------------------
			}
		});///////////////////////////////////////////////////////////////////////////////////////////////////
		searchBtn.setFont(new Font("굴림", Font.PLAIN, 18));
		searchBtn.setBounds(693, 133, 119, 37);
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(searchBtn);
		
		//--------------------- 상단 툴바
		JToolBar header = new JToolBar();
		header.setBounds(68, 98, 114, 91);
		contentPane.add(header);
		//--------------------- 상단 음악로고
		JButton musicLogo = new JButton("");
		musicLogo.setIcon(new ImageIcon("D:\\hyeri_java0308\\workspace\\JavaNetwork\\src\\javanetwork\\headLogo.png"));
		header.add(musicLogo);
		
		JToolBar contents = new JToolBar();
		contents.setBounds(122, 265, 177, 150);
		contentPane.add(contents);

		//--------------------- 음원 이미지
		JButton cdLogo = new JButton("");
		contents.add(cdLogo);
		cdLogo.setIcon(new ImageIcon("D:\\hyeri_java0308\\workspace\\JavaNetwork\\src\\javanetwork\\cdLogo.png"));
		
		//--------------------- 구매버튼
		JButton buyBtn = new JButton("\uAD6C\uB9E4");
		buyBtn.addMouseListener(new MouseAdapter() {//////////////////////////////////////////////////////
			@Override
			public void mouseClicked(MouseEvent e) {

				/*//////////////////////////////////////////////////////////////////////
				 * 구매버튼 mouseClicked 
				 *//////////////////////////////////////////////////////////////////////

				
				// 예외처리
				// InputMismatchException : 자료형 오류
				try {
					cd = searchTxt.getText();//--------------------- cd이름을 입력 받는다..........
					sooBuy = Integer.parseInt(buySet.getText()); //--------------------- 구매할 cd갯수를 입력 받는다..........
					orderWho = whoBuy.getText(); //--------------------- 어떤 가수 음원살건지 입력 받는다..........
					System.out.println("구매할때 음원 : " + cd + " 구매하는 수량 : " + sooBuy + "가수이름 : " + orderWho);
					
					ArrayList <RecordInfoVO> recordArr = reDao.getAllInfo(cd); 
					for(RecordInfoVO imsi : recordArr) {


						// 0. 0보다 작은 수를 입력했을 때
						if (sooBuy < 1) {//sooBuy < 1		//1보다 큰 수를 입력해 주세요.
							JOptionPane.showMessageDialog(null,"1보다 큰 수를 입력해 주세요.", "Error", JOptionPane.ERROR_MESSAGE);	
						}
						// 1. 재고가 없을 때 sooBuy = 사려는거 // ?? = 재고 --> 사실 빼도 될듯..?
						else if(imsi.getCdSoo()<0) {
							JOptionPane.showMessageDialog(null,"재고가 없습니다.", "Error", JOptionPane.ERROR_MESSAGE);
							break;
						}
						// 2. 구매수량이 재고보다 높을 때
						else if(sooBuy > imsi.getCdSoo()) {
							JOptionPane.showMessageDialog(null,"구매 수량보다 재고가 적습니다.", "Error", JOptionPane.ERROR_MESSAGE);
							break;
						}
						// 3. 가수명 불일치 
						else if(orderWho != imsi.getCdOrder()) {
							JOptionPane.showMessageDialog(null,"가수이름을 다시 적어주세요.", "Error", JOptionPane.ERROR_MESSAGE);
						}
						// 4. 가수명 일치 == 구매완료
						else if(imsi.getCdOrder().contains(orderWho)){
							boolean update = reDao.update_soo(sooBuy, cd, orderWho); // 구매수량, 음원명, 가수명
							if(update) {

								JOptionPane.showMessageDialog(null,"구매 성공");
							}
						}
					}
				}
				//자료형 오류
				catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null,"구매수량에 올바른 수치를 입력해주세요.", "Error", JOptionPane.ERROR_MESSAGE);	
				}
			}
				
		});///////////////////////////////////////////////////////////////////////////////////////////////////
		buyBtn.setFont(new Font("굴림", Font.PLAIN, 15));
		buyBtn.setBounds(715, 433, 97, 35);
		contentPane.add(buyBtn);

		//--------------------- 가수 명 입력창
		whoBuy = new JTextField();
		whoBuy.setText("\uAC00\uC218\uBA85 \uC785\uB825");
		whoBuy.setColumns(10);
		whoBuy.setBounds(503, 433, 97, 35);
		contentPane.add(whoBuy);

		//--------------------- 구매수량입력창
		buySet = new JTextField();
		buySet.setText("\uAD6C\uB9E4\uC218\uB7C9 \uC785\uB825");
		buySet.setBounds(605, 433, 97, 35);
		contentPane.add(buySet);
		buySet.setColumns(10);

		//--------------------- 로그아웃 버튼
		JButton logoutBtn = new JButton("\uB85C\uADF8\uC544\uC6C3");
		logoutBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "로그아웃합니다.");
				dispose();
				setVisible(false);
//				new clientsrcMain/* 로그인화면 */().setVisible(true);
			}
		});
		logoutBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		logoutBtn.setBounds(715, 76, 97, 23);
		contentPane.add(logoutBtn);
		
	}
}
