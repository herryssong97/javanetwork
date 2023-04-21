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
	public JTextField searchTxt; //////////// DAO�� ���� �Ķ���ͷ� �Ѱ�����ؼ�
	public JTextField buySet;	 //////////// public ���� ����� �Ѵ�.
	public JTextField whoBuy;  //////////// 

	public String cd; // ��� : ������ ���� �̸�
	public int sooBuy;	  // �ϴ� : ������ ���� ����
	public String orderWho;  // �ϴ� : � �����İ� ���� â
	
	public int ddefault; // ���� ���� ����
	
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
		resultTit.setFont(new Font("����", Font.PLAIN, 15));
		resultTit.setBounds(396, 213, 426, 23);
		contentPane.add(resultTit);
		
		//--------------------- TextArea ������
		resultArea = new JTextArea();
		
		resultArea.setLineWrap(true); // ����
		
		//--------------------- Scroll ������
		JScrollPane scrollPane = new JScrollPane(resultArea);
		scrollPane.setBounds(396, 246, 416, 169);
		contentPane.add(scrollPane);

		//--------------------- CD�� �Է�
		JLabel searchCd = new JLabel("CD \uBA85 \uC785\uB825 : ");
		searchCd.setFont(new Font("����", Font.PLAIN, 15));
		searchCd.setBounds(303, 134, 97, 35);
		contentPane.add(searchCd);
		
		//--------------------- �˻��Է�â
		searchTxt = new JTextField(cd);
		searchTxt.setBounds(402, 134, 286, 35);
		contentPane.add(searchTxt);
		searchTxt.setColumns(10);

		//--------------------- �˻� ��ư
		JButton searchBtn = new JButton("\uAC80\uC0C9");
		searchBtn.addMouseListener(new MouseAdapter() { //////////////////////////////////////////////////////
			@Override
			public void mouseClicked(MouseEvent e) {
				// �˻� ��ư ������ ���� textarea �ʱ�ȭ //////////////////////////////////////
				resultArea.selectAll();
				resultArea.replaceSelection("");
				
//				cd = "";
				//RecordInfoDAO reDao = new RecordInfoDAO();
				/*//////////////////////////////////////////////////////////////////////
				 * �˻���ư mouseClicked --> �ϴܿ� ���������� ��µǰ� �Ѵ�
				 *//////////////////////////////////////////////////////////////////////
				
				cd = searchTxt.getText(); //--------------------- cd���� �Է� �޴´�..........
				
				System.out.println("ȭ�鿡�� �Է¹��� : " + cd);
				// ����ó��
				if (cd.length() < 2) {
					JOptionPane.showMessageDialog(null,"2�� �̻� �Է����ּ���.", "Error", JOptionPane.ERROR_MESSAGE);
					System.out.println("2�� �̻� �Է����ּ���.");
				} 
				else {// ���Ű� �����Ҷ�

					try {
						// �Ӽ����� ����ִ� VO�� 'recordArr' ���� ����Ʈ�� ������ش�
						// Dao���� ��µǴ� ��� cd(������)���� recordArr�� ����
						// ���ʷ� ���� �ִٸ� ���.......
						// 
						ArrayList <RecordInfoVO> recordArr = reDao.getAllInfo(cd); 
						for(RecordInfoVO imsi : recordArr) {

							if (imsi.getCdSoo() >= 1) { // ���� ��� ������
								resultArea.append(imsi.getCdName()+"\t"+imsi.getCdOrder()+"\t"+
										imsi.getCdPrice()+"\t"+imsi.getCdDate()+"\t" + imsi.getCdSoo()+ "\r\n");	
							} 
							
							else { // ��� ������ --> �굵 ���� ������ �ɵ�
								resultArea.setText(imsi.getCdName()+"\t"+imsi.getCdOrder()+"\t"+
										imsi.getCdPrice()+"\t"+imsi.getCdDate()+"\t"+imsi.getCdSoo());		
								
								
//								if() {
//									
//								}
							}
//							�׽�Ʈ �� ���� �����
							///////////////////////////////////////////////////////////////////////////
							///////////////////////////////////////////////////////////////////////////
							System.out.println("ȭ�鿡 �Է��� �� : " + cd);
							// ȭ�� ��� Ȯ�� (�Է��� ���� db���� ������ ��)
							System.out.println( "ȭ�鿡 ��µǴ� �� : " + 
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
		searchBtn.setFont(new Font("����", Font.PLAIN, 18));
		searchBtn.setBounds(693, 133, 119, 37);
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(searchBtn);
		
		//--------------------- ��� ����
		JToolBar header = new JToolBar();
		header.setBounds(68, 98, 114, 91);
		contentPane.add(header);
		//--------------------- ��� ���Ƿΰ�
		JButton musicLogo = new JButton("");
		musicLogo.setIcon(new ImageIcon("D:\\hyeri_java0308\\workspace\\JavaNetwork\\src\\javanetwork\\headLogo.png"));
		header.add(musicLogo);
		
		JToolBar contents = new JToolBar();
		contents.setBounds(122, 265, 177, 150);
		contentPane.add(contents);

		//--------------------- ���� �̹���
		JButton cdLogo = new JButton("");
		contents.add(cdLogo);
		cdLogo.setIcon(new ImageIcon("D:\\hyeri_java0308\\workspace\\JavaNetwork\\src\\javanetwork\\cdLogo.png"));
		
		//--------------------- ���Ź�ư
		JButton buyBtn = new JButton("\uAD6C\uB9E4");
		buyBtn.addMouseListener(new MouseAdapter() {//////////////////////////////////////////////////////
			@Override
			public void mouseClicked(MouseEvent e) {

				/*//////////////////////////////////////////////////////////////////////
				 * ���Ź�ư mouseClicked 
				 *//////////////////////////////////////////////////////////////////////

				
				// ����ó��
				// InputMismatchException : �ڷ��� ����
				try {
					cd = searchTxt.getText();//--------------------- cd�̸��� �Է� �޴´�..........
					sooBuy = Integer.parseInt(buySet.getText()); //--------------------- ������ cd������ �Է� �޴´�..........
					orderWho = whoBuy.getText(); //--------------------- � ���� ��������� �Է� �޴´�..........
					System.out.println("�����Ҷ� ���� : " + cd + " �����ϴ� ���� : " + sooBuy + "�����̸� : " + orderWho);
					
					ArrayList <RecordInfoVO> recordArr = reDao.getAllInfo(cd); 
					for(RecordInfoVO imsi : recordArr) {


						// 0. 0���� ���� ���� �Է����� ��
						if (sooBuy < 1) {//sooBuy < 1		//1���� ū ���� �Է��� �ּ���.
							JOptionPane.showMessageDialog(null,"1���� ū ���� �Է��� �ּ���.", "Error", JOptionPane.ERROR_MESSAGE);	
						}
						// 1. ��� ���� �� sooBuy = ����°� // ?? = ��� --> ��� ���� �ɵ�..?
						else if(imsi.getCdSoo()<0) {
							JOptionPane.showMessageDialog(null,"��� �����ϴ�.", "Error", JOptionPane.ERROR_MESSAGE);
							break;
						}
						// 2. ���ż����� ����� ���� ��
						else if(sooBuy > imsi.getCdSoo()) {
							JOptionPane.showMessageDialog(null,"���� �������� ��� �����ϴ�.", "Error", JOptionPane.ERROR_MESSAGE);
							break;
						}
						// 3. ������ ����ġ 
						else if(orderWho != imsi.getCdOrder()) {
							JOptionPane.showMessageDialog(null,"�����̸��� �ٽ� �����ּ���.", "Error", JOptionPane.ERROR_MESSAGE);
						}
						// 4. ������ ��ġ == ���ſϷ�
						else if(imsi.getCdOrder().contains(orderWho)){
							boolean update = reDao.update_soo(sooBuy, cd, orderWho); // ���ż���, ������, ������
							if(update) {

								JOptionPane.showMessageDialog(null,"���� ����");
							}
						}
					}
				}
				//�ڷ��� ����
				catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null,"���ż����� �ùٸ� ��ġ�� �Է����ּ���.", "Error", JOptionPane.ERROR_MESSAGE);	
				}
			}
				
		});///////////////////////////////////////////////////////////////////////////////////////////////////
		buyBtn.setFont(new Font("����", Font.PLAIN, 15));
		buyBtn.setBounds(715, 433, 97, 35);
		contentPane.add(buyBtn);

		//--------------------- ���� �� �Է�â
		whoBuy = new JTextField();
		whoBuy.setText("\uAC00\uC218\uBA85 \uC785\uB825");
		whoBuy.setColumns(10);
		whoBuy.setBounds(503, 433, 97, 35);
		contentPane.add(whoBuy);

		//--------------------- ���ż����Է�â
		buySet = new JTextField();
		buySet.setText("\uAD6C\uB9E4\uC218\uB7C9 \uC785\uB825");
		buySet.setBounds(605, 433, 97, 35);
		contentPane.add(buySet);
		buySet.setColumns(10);

		//--------------------- �α׾ƿ� ��ư
		JButton logoutBtn = new JButton("\uB85C\uADF8\uC544\uC6C3");
		logoutBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "�α׾ƿ��մϴ�.");
				dispose();
				setVisible(false);
//				new clientsrcMain/* �α���ȭ�� */().setVisible(true);
			}
		});
		logoutBtn.setFont(new Font("����", Font.PLAIN, 12));
		logoutBtn.setBounds(715, 76, 97, 23);
		contentPane.add(logoutBtn);
		
	}
}
