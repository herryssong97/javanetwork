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

import adminUpdateDAO.AdminMemUpdateDAO;
import adminUpdateMain.Record_select;
import adminUpdateVO.AdminMemUpdateVO;
import adminUpdateVO.AdminUpdateVO;
import javax.swing.JScrollPane;

public class AdminMemUpdateView extends JFrame {
	JPanel jpanel = new JPanel();
	public JTextField searchField = new JTextField();
	private JButton searchBtn = new JButton(
			new ImageIcon(AdminUpdateView.class.getResource("/images/search_icon.png")));
	private JTextArea resultArea = new JTextArea();
	private JButton updateBtn = new JButton("\uC218\uC815");
	private JButton selectBtn = new JButton("\uCD9C\uB825");
	private JButton enbanBtn = new JButton(new ImageIcon(AdminUpdateView.class.getResource("/images/recordicon.png")));

	private String cname; // 검색할 고객명
	private final JScrollPane scrollPane = new JScrollPane();

	public AdminMemUpdateView() {
		AdminMemUpdateGui();
	}

	public void AdminMemUpdateGui() {
		AdminMemUpdateDAO amdao = new AdminMemUpdateDAO();

		setTitle("금반 말고 은반");
		setBounds(50, 50, 700, 700);
		setBackground(new Color(255, 255, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		jpanel.setLayout(null);
		getContentPane().add(jpanel);

		updateBtn.setBounds(506, 157, 77, 30);
		updateBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		jpanel.add(updateBtn);
		updateBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String bname = JOptionPane.showInputDialog("고객명 : ");
				String uname = JOptionPane.showInputDialog("고객명 수정 : ");
				boolean upd = amdao.guest_update(uname, bname);

				if (upd) {
					JOptionPane.showMessageDialog(null, "수정완료");
					System.out.println("수정완료");
				} else
					System.out.println("에러 발생");

			}
		}); // updateBtn.addActionListener-end

		selectBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		selectBtn.setBounds(583, 157, 77, 30);
		jpanel.add(selectBtn);
		selectBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				resultArea.setText("");

				try {
					ArrayList<AdminMemUpdateVO> selArray = amdao.getAllInfo();

					for (AdminMemUpdateVO imsi : selArray) {
						resultArea.append(imsi.getGuestName() + "\t" + imsi.getGuestId() + "\t" + imsi.getGuestPw()
								+ "\t" + imsi.getPhone() + "\n");
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
		searchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				resultArea.setText("");
				cname = searchField.getText();

				if (cname.length() <= 1) { // 검색창에 한 글자 이하 입력시 오류 메시지
					JOptionPane.showMessageDialog(null, "두 글자 이상 입력해주세요.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						ArrayList<AdminMemUpdateVO> nArray = amdao.searchByName(cname);

						for (AdminMemUpdateVO imsi : nArray) {
							resultArea.append(imsi.getGuestName() + "\t" + imsi.getGuestId() + "\t" + imsi.getGuestPw()
									+ "\t" + imsi.getPhone() + "\t" + "\n");
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
		enbanBtn.addActionListener(new ActionListener() { // 종료

			@Override
			public void actionPerformed(ActionEvent e) {
				int res1 = JOptionPane.showConfirmDialog(null, "홈으로 돌아가시겠습니까?", "MESSAGE", JOptionPane.YES_NO_OPTION);

				if (res1 == JOptionPane.YES_OPTION) {
					dispose();
					setVisible(false);

					new Record_select().setVisible(true);
				} else {
					int res2 = JOptionPane.showConfirmDialog(null, "종료하시겠습니까?", "MESSAGE", JOptionPane.YES_NO_OPTION);

					if (res2 == JOptionPane.YES_OPTION) {
						System.exit(0);
					}

				}

			}
		}); // enbanBtn.addActionListener-end

	}

}
