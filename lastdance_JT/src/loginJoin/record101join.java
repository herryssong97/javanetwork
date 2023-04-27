package loginJoin;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import loginDAO.record101_dao;
import loginMain.record101_login;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JFormattedTextField;

public class record101join extends JFrame {

	private JPanel contentPane;
	private JTextField text_name;
	private JTextField text_id;
	private JTextField text_phone;
	private JPasswordField text_pw;
	private JPasswordField text_pwre;

	public String name;
	public String id;
	public String pw;
	public String pwre;
	public String phone;

	public String id1;
	public boolean b2;

	/**
	 * Launch the application.
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					record101join frame = new record101join();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public record101join() throws ClassNotFoundException, SQLException {
		record101_dao dao = new record101_dao();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 468, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("\uD68C\uC6D0\uAC00\uC785");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		lblNewLabel.setBounds(143, 10, 131, 63);
		contentPane.add(lblNewLabel);

		JLabel lblNewL = new JLabel("\uC774\uB984");
		lblNewL.setBounds(47, 100, 57, 15);
		contentPane.add(lblNewL);

		JLabel lblNewLabel_2 = new JLabel("\uC544\uC774\uB514");
		lblNewLabel_2.setBounds(47, 150, 57, 15);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("\uBE44\uBC00\uBC88\uD638");
		lblNewLabel_3.setBounds(47, 200, 57, 15);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("\uC804\uD654\uBC88\uD638");
		lblNewLabel_4.setBounds(47, 300, 57, 15);
		contentPane.add(lblNewLabel_4);

		text_name = new JTextField();
		text_name.setBounds(147, 100, 116, 21);
		contentPane.add(text_name);
		text_name.setColumns(10);

		text_id = new JTextField();
		text_id.setColumns(10);
		text_id.setBounds(147, 150, 116, 21);
		contentPane.add(text_id);

		text_phone = new JTextField();
		text_phone.setColumns(10);
		text_phone.setBounds(147, 300, 116, 21);
		contentPane.add(text_phone);
		text_pw = new JPasswordField();
		text_pw.setBounds(147, 200, 116, 21);
		contentPane.add(text_pw);

		JButton btnNewButton = new JButton("\uAC00\uC785\uC644\uB8CC");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				name = text_name.getText();
				id = text_id.getText();
				pw = text_pw.getText();
				pwre = text_pwre.getText();
				phone = text_phone.getText();

				boolean b1 = null != null;
				while (true) {

					if (id.length() < 5) {
						JOptionPane.showMessageDialog(null, "아이디는 5자 이상가능!!");
						break;
					}
					if (!pw.equals(pwre)) {
						JOptionPane.showMessageDialog(null, "비밀번호확인!!");
						break;
					}

					if (text_name.getText().contains("관리자")) {
						if (b1 = dao.insert_adm("관리자", id, pw, phone)) {
							JOptionPane.showMessageDialog(null, "관리자 가입 완료");
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
							break;
						}
					}

					if (b1 = dao.insert_cli(name, id, pw, phone)) {
						JOptionPane.showMessageDialog(null, "회원 가입 완료!");
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
						break;
					}
				}
			}
		});

		btnNewButton.setBounds(297, 269, 97, 77);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel_3_1 = new JLabel("\uBE44\uBC00\uBC88\uD638\uD655\uC778");
		lblNewLabel_3_1.setBounds(47, 250, 88, 15);
		contentPane.add(lblNewLabel_3_1);

		text_pwre = new JPasswordField();
		text_pwre.setBounds(147, 250, 116, 21);
		contentPane.add(text_pwre);

		JButton btnId = new JButton("\uC911\uBCF5 \uD655\uC778");
		btnId.setBounds(297, 146, 91, 23);
		contentPane.add(btnId);
		btnId.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id1 = text_id.getText();
				b2 = false;
				if (b2 = dao.check(id1)) {
					JOptionPane.showMessageDialog(null, "아이디 중복");
					text_id.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "아이디 사용가능");

				}

			}

		});

	}
}