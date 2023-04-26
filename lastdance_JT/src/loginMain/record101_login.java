package loginMain;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import adminUpdateMain.Record_select;
import javanetwork.RecordSearch;
import loginDAO.record101_dao;
import loginJoin.record101join;

public class record101_login extends JFrame {

	private JPanel contentPane;
	private JTextField log_id;
	private JPasswordField log_pw;
	public String username;
	public String pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					record101_login frame = new record101_login();
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
	public record101_login() throws ClassNotFoundException, SQLException {

		record101_dao dao = new record101_dao();
		setTitle("RECORD101");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 354, 288);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("\uB85C\uADF8\uC778");
		lblNewLabel.setBounds(116, 10, 168, 55);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(46, 78, 57, 15);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("비밀번호");
		lblNewLabel_1_1.setBounds(46, 111, 57, 15);
		contentPane.add(lblNewLabel_1_1);

		log_id = new JTextField();
		log_id.setBounds(126, 75, 116, 21);
		contentPane.add(log_id);
		log_id.setColumns(10);

		log_pw = new JPasswordField();
		log_pw.setBounds(126, 108, 116, 21);
		contentPane.add(log_pw);

		JButton btnNewButton = new JButton("\uB85C\uADF8\uC778");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				username = log_id.getText();
				pass = log_pw.getText();

				boolean l1 = false;
				boolean l2 = false;
				try {
					l1 = dao.login(username, pass);
					l2 = dao.adm_login(username, pass);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if (l1) {
					JOptionPane.showMessageDialog(null, "로그인 완료");
					dispose();
					setVisible(false);
					try {

						new RecordSearch().setVisible(true);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}else if(l2) { // admin101테이블로
					JOptionPane.showMessageDialog(null, "관리자로 로그인합니다");
					dispose();
					setVisible(false);
					new Record_select().setVisible(true);
				}

				// ----------------------------------------

				else {
					JOptionPane.showMessageDialog(null, "로그인실패");
				}

			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(126, 159, 97, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("\uD68C\uC6D0\uAC00\uC785");

		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				setVisible(false);
				try {
					new record101join().setVisible(true);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(126, 193, 97, 23);
		contentPane.add(btnNewButton_1);
	}

}
