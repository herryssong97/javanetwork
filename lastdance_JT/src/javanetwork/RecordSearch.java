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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import loginMain.record101_login;
import recordinfoDAO.RecordInfoDAO;
import recordinfoVO.RecordInfoVO;

public class RecordSearch extends JFrame {

	
	private JPanel contentPane;
	public JTextField searchTxt; //////////// DAO에 값을 파라미터로 넘겨줘야해서
	public JTextField buySet;	 //////////// public 으로 해줘야 한다.
	public JTextField whoBuy;  //////////// 

	public String cd; // 상단 : 구매할 음원 이름
	public int sooBuy;	  // 하단 : 구매할 음원 수량
	public String orderWho;  // 하단 : 어떤 가수냐고 묻는 창
	
	public int ddefault; // 기존 음원 수량
	private JTable table;
	
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
		
		//--------------------- Scroll 결과출력
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(353, 246, 459, 169);
		contentPane.add(scrollPane);
		
		//--------------------- table
		Object record[] = new Object[10]; //----- 10개까지만 출력되게 해라는 뜻 (음원명이 10건 이상 겹치진 않을거라 괜찮을듯)
		String [] field = {"음원명", "가수", "가격", "출시일", "재고"}; //------ 상단 가로행 첫 줄 필드
		DefaultTableModel tableModel = new DefaultTableModel(field, 0);
		table = new JTable(tableModel);
		  
		table.addMouseListener(new MouseAdapter() {
			@Override
				/*////////////////////////////////////////////////////////
				 * table 행 클릭시 실행
				 */////////////////////////////////////////////////////////
				public void mouseClicked(MouseEvent e) {

				  int row = table.getSelectedRow();
				  int col = table.getSelectedColumn();
//				table.selectAll();
				System.out.println();
				TableModel model = table.getModel();
				((DefaultTableModel) model).setNumRows(0);
				// 누를때마다 초기화
				System.out.println("선택된 row ::: " + row); // 총 행 갯수 2 출력

					  ArrayList<RecordInfoVO> recordArr = null;
					try {
						recordArr = reDao.getAllInfo(cd);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
//						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
//						e1.printStackTrace();
					} 
					  for (int i = 0; i < recordArr.size(); i++) {
					  
							RecordInfoVO vo = recordArr.get(i);
							// 0자리에 음원명, 1자리에 가수명, 2자리에 가격
							// 3자리에 출시일, 4자리에 수량을 넣어라
							record[0] = vo.getCdName();
							record[1] = vo.getCdOrder();
							record[2] = vo.getCdPrice();
							record[3] = vo.getCdDate();
							record[4] = vo.getCdSoo();
							tableModel.addRow(record);
							  
							System.out.println("$$$$$$$$$  "+vo.getCdOrder()); // 콘솔 확인용
								
						}
					  String str = (String) table.getValueAt(row, 1);
					  whoBuy.setText(str); // --> 하단 가수명에 이름 들어감 !!!!!!!
				}
			});/////////////////////////////////////////////////////////
		scrollPane.setViewportView(table);

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
//				table.selectAll();
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				model.setNumRows(0);
				// 누를때 마다 초기화
				
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				// 한 행만 선택 가능하게 

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
//							RecordInfoVO vo = recordArr.get(imsi);
							
							record[0] = imsi.getCdName();
							record[1] = imsi.getCdOrder();
							record[2] = imsi.getCdPrice();
							record[3] = imsi.getCdDate();
							record[4] = imsi.getCdSoo();
							tableModel.addRow(record);
							
							}
						} 
						
					 catch (SQLException e1) {
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
		musicLogo.setIcon(new ImageIcon("D:\\hyeri_java0308\\workspace\\lastdance_JT\\src\\javanetwork\\music.png"));
		header.add(musicLogo);
		
		JToolBar contents = new JToolBar();
		contents.setBounds(122, 265, 177, 150);
		contentPane.add(contents);

		//--------------------- 음원 이미지
		JButton cdLogo = new JButton("");
		contents.add(cdLogo);
		cdLogo.setIcon(new ImageIcon("D:\\hyeri_java0308\\workspace\\lastdance_JT\\src\\javanetwork\\cdLogo.png"));
		
		//--------------------- 구매버튼
		
		JButton buyBtn = new JButton("\uAD6C\uB9E4");
		buyBtn.addMouseListener(new MouseAdapter() {//////////////////////////////////////////////////////
			@Override
			public void mouseClicked(MouseEvent e) {

				/*//////////////////////////////////////////////////////////////////////
				 * 구매버튼 mouseClicked 
				 *//////////////////////////////////////////////////////////////////////

				  int row = table.getSelectedRow();
				  int col = table.getSelectedColumn();

				// 예외처리
				// InputMismatchException : 자료형 오류
				try {
					cd = searchTxt.getText();//--------------------- cd이름을 입력 받는다..........
					sooBuy = Integer.parseInt(buySet.getText()); //--------------------- 구매할 cd갯수를 입력 받는다..........
					orderWho = whoBuy.getText(); //--------------------- 어떤 가수 음원살건지 입력 받는다..........

				    int soooo = (int) table.getValueAt(row, 4); // db에서 가져오는 잔여 음반 수량
				    
					System.out.println("구매할때 음원 : " + cd + " 구매하는 수량 : " + sooBuy + " 가수이름 : " + orderWho + " ::: "+soooo);
					boolean update=false;
					ArrayList <RecordInfoVO> recordArr = reDao.getAllInfo(cd); 
/////////////////////////////////////////////
/////////////////////////////////////////////
					for(RecordInfoVO imsi : recordArr) {
						
						 int firstIndex = 0;
					     int lastIndex = recordArr.size(); // recordArr 담긴 총 갯수

					     
					     
						// 0. 0보다 작은 수를 입력했을 때
						if (sooBuy < 1) {//sooBuy < 1		//1보다 큰 수를 입력해 주세요.
							JOptionPane.showMessageDialog(null,"1보다 큰 수를 입력해 주세요.", "Error", JOptionPane.ERROR_MESSAGE);	
							break;
						}
						// 1. 재고가 없을 때 sooBuy = 사려는거 // ?? = 재고 --> 사실 빼도 될듯..?
						else if(soooo <= 0) {
							JOptionPane.showMessageDialog(null,"재고가 없습니다.", "Error", JOptionPane.ERROR_MESSAGE);
							break;
						}
						// 2. 구매수량이 재고보다 높을 때
						else if(sooBuy > soooo) {
							JOptionPane.showMessageDialog(null,"구매 수량보다 재고가 적습니다.", "Error", JOptionPane.ERROR_MESSAGE);
							break;
						}
						// 4. 가수명 일치 == 구매완료
						else if(table.getValueAt(row, 1).equals(orderWho)){
							
							update = reDao.update_soo(sooBuy, cd, orderWho); // 구매수량, 음원명, 가수명
							if(update) {
								JOptionPane.showMessageDialog(null,"구매 성공");
							}
							break;
						}
//						// 4. 가수명 불일치
						else if(!table.getValueAt(row, 1).equals(orderWho)){
							JOptionPane.showMessageDialog(null,"가수명 불일치", "Error", JOptionPane.ERROR_MESSAGE);
							break;
						}
							
/////////////////////////////////////////////
//						System.out.println("첫번째는~  "+firstIndex);
//
//						System.out.println("마지막째는~  "+lastIndex);
/////////////////////////////////////////////
					}
				}
				//자료형 오류
				catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null,"올바른 수치를 입력해주세요!", "Error", JOptionPane.ERROR_MESSAGE);	
				}
			}
		});
		
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
				try {
					new record101_login().setVisible(true);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				new clientsrcMain/* 로그인화면 */().setVisible(true);
			}
		});
		logoutBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		logoutBtn.setBounds(715, 76, 97, 23);
		contentPane.add(logoutBtn);
		
	}
}
