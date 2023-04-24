package adminUpdateMain;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import adminUpdateView.AdminMemUpdateView;
import adminUpdateView.AdminUpdateView;
import runmain.RunAdminMemUpdate;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Record_select extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Record_select frame = new Record_select();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Record_select() {
		setTitle("\uAD00\uB9AC\uC790 \uBA54\uB274 \uC120\uD0DD");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnJaego = new JButton("\uC7AC\uACE0\uAD00\uB9AC");
		btnJaego.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				setVisible(false);
				new AdminUpdateView().setVisible(true);
			}
		});
		btnJaego.setBounds(150, 35, 139, 101);
		contentPane.add(btnJaego);
		
		JButton btnClient = new JButton("\uD68C\uC6D0\uAD00\uB9AC");
		btnClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				setVisible(false);
				new AdminMemUpdateView().setVisible(true);
				
			}
		});
		btnClient.setBounds(150, 152, 139, 101);
		contentPane.add(btnClient);
	}
}
