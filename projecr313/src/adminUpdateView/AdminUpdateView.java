package adminUpdateView;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import adminUpdateDAO.AdminUpdateDAO;
import adminUpdateVO.AdminUpdateVO;
import javax.swing.JScrollPane;
import javax.swing.Icon;

public class AdminUpdateView extends JFrame {
	public JPanel jpanel = new JPanel();
	public JTextField searchField = new JTextField();	
	private JButton searchBtn = new JButton(
			new ImageIcon(AdminUpdateView.class.getResource("/images/search_icon.png")));
	private JTextArea resultArea = new JTextArea();
	private final JScrollPane scrollPane = new JScrollPane();
	private JButton insertBtn = new JButton("\uCD94\uAC00");
	private JButton updateBtn = new JButton("\uC218\uC815");
	private JButton deleteBtn = new JButton("\uC0AD\uC81C");
	private JButton selectBtn = new JButton("\uCD9C\uB825");
	private JButton enbanBtn = new JButton(new ImageIcon(AdminUpdateView.class.getResource("/images/recordicon.png"))); 

	private String cd; // 검색할 cd명
	

	public AdminUpdateView() {
		AdminUpdateGui();

	}

	public void AdminUpdateGui() {
		AdminUpdateDAO audao = new AdminUpdateDAO();

		setTitle("금반 말고 은반");
		setBounds(50, 50, 700, 700);
		setBackground(new Color(255, 255, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		jpanel.setLayout(null);
		getContentPane().add(jpanel);

		insertBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		insertBtn.setBounds(354, 157, 77, 30);
		jpanel.add(insertBtn);
		insertBtn.addActionListener(new ActionListener() {	// 삽입

			@Override
			public void actionPerformed(ActionEvent e) {
				String cdName = JOptionPane.showInputDialog("음반 이름");
				String cdOrder = JOptionPane.showInputDialog("가수명");
				int cdPrice = Integer.parseInt(JOptionPane.showInputDialog("가격"));
				String cdDate = JOptionPane.showInputDialog("발매일");
				int cdSoo = Integer.parseInt(JOptionPane.showInputDialog("수량"));
				boolean ins = audao.cd_insert(cdName, cdOrder, cdPrice, cdDate, cdSoo);

				if (ins) {
					JOptionPane.showMessageDialog(null, "추가완료");
					System.out.println("추가완료");
				} else
					System.out.println("에러 발생");

			}
		}); // insertBtn.addActionListener-end

		updateBtn.setBounds(432, 157, 77, 30);
		updateBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		jpanel.add(updateBtn);
		updateBtn.addActionListener(new ActionListener() {	// 수정

			@Override
			public void actionPerformed(ActionEvent e) {
				String cdName = JOptionPane.showInputDialog("음반 이름 : ");
				int updateSoo = Integer.parseInt(JOptionPane.showInputDialog("수량 수정 : "));
				boolean upd = audao.cd_update(updateSoo, cdName);

				if (upd) {
					JOptionPane.showMessageDialog(null, "수정완료");
					System.out.println("수정완료");
				} else
					System.out.println("에러 발생");

			}
		}); // updateBtn.addActionListener-end

		deleteBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		deleteBtn.setBounds(506, 157, 77, 30);
		jpanel.add(deleteBtn);
		deleteBtn.addActionListener(new ActionListener() {	// 삭제

			@Override
			public void actionPerformed(ActionEvent e) {
				String cdName = JOptionPane.showInputDialog("삭제할 음반 이름");
				boolean del = audao.cd_delete(cdName);

				if (del) {
					JOptionPane.showMessageDialog(null, "삭제완료");
					System.out.println("삭제완료");
				} else
					System.out.println("에러 발생");

			}
		}); // deleteBtn.addActionListener-end

		selectBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		selectBtn.setBounds(583, 157, 77, 30);
		jpanel.add(selectBtn);
		selectBtn.addActionListener(new ActionListener() {	// 전체 출력

			@Override
			public void actionPerformed(ActionEvent e) {
				resultArea.setText("");

				try {
					ArrayList<AdminUpdateVO> selArray = audao.getAllInfo();

					for (AdminUpdateVO imsi : selArray) {
						resultArea.append(imsi.getCdName() + "\t" + imsi.getCdOrder() + "\t" + imsi.getCdPrice() + "\t"
								+ imsi.getCdDate() + "\t" + imsi.getCdSoo() + "\n");
					}
				} catch (Exception ee) {

				}

			}
		}); // selectBtn.addActionListener-end
		
		scrollPane.setBounds(23, 197, 637, 418);
		
		jpanel.add(scrollPane);
		scrollPane.setViewportView(resultArea);
		resultArea.setFont(new Font("굴림", Font.BOLD, 15));

		searchField.setBounds(354, 114, 266, 30);
		searchField.setFont(new Font("굴림", Font.BOLD, 15));
		jpanel.add(searchField);

		searchBtn.setBounds(621, 114, 38, 30);
		searchBtn.setFont(new Font("굴림", Font.BOLD, 15));
		jpanel.add(searchBtn);
		
		searchBtn.addActionListener(new ActionListener() {	// 검색

			@Override
			public void actionPerformed(ActionEvent e) {
				resultArea.setText("");
				cd = searchField.getText();

				if (cd.isEmpty()) {
					JOptionPane.showMessageDialog(null, "검색 내용을 입력해주세요.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						ArrayList<AdminUpdateVO> nArray = audao.searchByName(cd);
						
						for (AdminUpdateVO imsi : nArray) {
							resultArea.append(imsi.getCdName() + "\t" + imsi.getCdOrder() + "\t" + imsi.getCdPrice()
									+ "\t" + imsi.getCdDate() + "\t" + imsi.getCdSoo() + "\n");
							}
						
					} catch (Exception ee) {
						
					}
						
				}
			}
		}); // searchBtn.addActionListener-end
		
		enbanBtn.setBounds(23, 10, 180, 169);
		enbanBtn.setBorderPainted(false); // 버튼 바깥 테두리x
		enbanBtn.setContentAreaFilled(false); // 이미지파일 투명 영역 색채우기 비활성
		enbanBtn.setFocusPainted(false); // 안쪽 테두리x
		jpanel.add(enbanBtn);
		enbanBtn.addActionListener(new ActionListener() {	// 종료
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "종료하시겠습니까?", "MESSAGE", JOptionPane.YES_NO_OPTION);
				
				if (result == JOptionPane.YES_OPTION)
					System.exit(0);
				
			}
		}); // enbanBtn.addActionListener-end
		
		
		

	}
}














//private String order; // 검색할 가수명
//order = searchField.getText();
//if (order.isEmpty()) {
//	JOptionPane.showMessageDialog(null, "검색 내용을 입력해주세요.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
//} else {
//	try {
//		ArrayList<AdminUpdateVO> oArray = audao.searchByOrder(order);
//		
//		for (AdminUpdateVO imsi : oArray) {
//			resultArea.append(imsi.getCdName() + "\t" + imsi.getCdOrder() + "\t" + imsi.getCdPrice()
//					+ "\t" + imsi.getCdDate() + "\t" + imsi.getCdSoo() + "\n");
//			}
//		
//	} catch (Exception ee) {
//		
//	}	
