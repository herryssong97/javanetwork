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

	private String cd; // �˻��� cd��
	

	public AdminUpdateView() {
		AdminUpdateGui();

	}

	public void AdminUpdateGui() {
		AdminUpdateDAO audao = new AdminUpdateDAO();

		setTitle("�ݹ� ���� ����");
		setBounds(50, 50, 700, 700);
		setBackground(new Color(255, 255, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		jpanel.setLayout(null);
		getContentPane().add(jpanel);

		insertBtn.setFont(new Font("����", Font.PLAIN, 12));
		insertBtn.setBounds(354, 157, 77, 30);
		jpanel.add(insertBtn);
		insertBtn.addActionListener(new ActionListener() {	// ����

			@Override
			public void actionPerformed(ActionEvent e) {
				String cdName = JOptionPane.showInputDialog("���� �̸�");
				String cdOrder = JOptionPane.showInputDialog("������");
				int cdPrice = Integer.parseInt(JOptionPane.showInputDialog("����"));
				String cdDate = JOptionPane.showInputDialog("�߸���");
				int cdSoo = Integer.parseInt(JOptionPane.showInputDialog("����"));
				boolean ins = audao.cd_insert(cdName, cdOrder, cdPrice, cdDate, cdSoo);

				if (ins) {
					JOptionPane.showMessageDialog(null, "�߰��Ϸ�");
					System.out.println("�߰��Ϸ�");
				} else
					System.out.println("���� �߻�");

			}
		}); // insertBtn.addActionListener-end

		updateBtn.setBounds(432, 157, 77, 30);
		updateBtn.setFont(new Font("����", Font.PLAIN, 12));
		jpanel.add(updateBtn);
		updateBtn.addActionListener(new ActionListener() {	// ����

			@Override
			public void actionPerformed(ActionEvent e) {
				String cdName = JOptionPane.showInputDialog("���� �̸� : ");
				int updateSoo = Integer.parseInt(JOptionPane.showInputDialog("���� ���� : "));
				boolean upd = audao.cd_update(updateSoo, cdName);

				if (upd) {
					JOptionPane.showMessageDialog(null, "�����Ϸ�");
					System.out.println("�����Ϸ�");
				} else
					System.out.println("���� �߻�");

			}
		}); // updateBtn.addActionListener-end

		deleteBtn.setFont(new Font("����", Font.PLAIN, 12));
		deleteBtn.setBounds(506, 157, 77, 30);
		jpanel.add(deleteBtn);
		deleteBtn.addActionListener(new ActionListener() {	// ����

			@Override
			public void actionPerformed(ActionEvent e) {
				String cdName = JOptionPane.showInputDialog("������ ���� �̸�");
				boolean del = audao.cd_delete(cdName);

				if (del) {
					JOptionPane.showMessageDialog(null, "�����Ϸ�");
					System.out.println("�����Ϸ�");
				} else
					System.out.println("���� �߻�");

			}
		}); // deleteBtn.addActionListener-end

		selectBtn.setFont(new Font("����", Font.PLAIN, 12));
		selectBtn.setBounds(583, 157, 77, 30);
		jpanel.add(selectBtn);
		selectBtn.addActionListener(new ActionListener() {	// ��ü ���

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
		resultArea.setFont(new Font("����", Font.BOLD, 15));

		searchField.setBounds(354, 114, 266, 30);
		searchField.setFont(new Font("����", Font.BOLD, 15));
		jpanel.add(searchField);

		searchBtn.setBounds(621, 114, 38, 30);
		searchBtn.setFont(new Font("����", Font.BOLD, 15));
		jpanel.add(searchBtn);
		
		searchBtn.addActionListener(new ActionListener() {	// �˻�

			@Override
			public void actionPerformed(ActionEvent e) {
				resultArea.setText("");
				cd = searchField.getText();

				if (cd.isEmpty()) {
					JOptionPane.showMessageDialog(null, "�˻� ������ �Է����ּ���.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
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
		enbanBtn.setBorderPainted(false); // ��ư �ٱ� �׵θ�x
		enbanBtn.setContentAreaFilled(false); // �̹������� ���� ���� ��ä��� ��Ȱ��
		enbanBtn.setFocusPainted(false); // ���� �׵θ�x
		jpanel.add(enbanBtn);
		enbanBtn.addActionListener(new ActionListener() {	// ����
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ�?", "MESSAGE", JOptionPane.YES_NO_OPTION);
				
				if (result == JOptionPane.YES_OPTION)
					System.exit(0);
				
			}
		}); // enbanBtn.addActionListener-end
		
		
		

	}
}














//private String order; // �˻��� ������
//order = searchField.getText();
//if (order.isEmpty()) {
//	JOptionPane.showMessageDialog(null, "�˻� ������ �Է����ּ���.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
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
