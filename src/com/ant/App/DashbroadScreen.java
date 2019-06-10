package src.com.ant.App;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.stream.IntStream;

import com.ant.App.ListEmployeeScreen;
import com.ant.App.LoginScreen;
import com.ant.App.PrizeScreen;
import com.ant.entities.DetailReward;
import com.ant.entities.Reward;
import com.ant.entities.User;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.commons.compress.utils.Lists;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Appender;
import org.apache.xmlbeans.XmlCursor.TokenType;

import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DashbroadScreen extends JFrame {

	private JPanel contentPane;
	private JTextField txtM;
	private JTextField txtN;
	private JTextField txtV;
	private JTextField txt1;
	private JTextField txt2;
	private JTextField txt3;
	private JTextField txt4;
	private JTextField txtClazz;
	int rewardId;
	int turn;

	ArrayList<Reward> reList = new ArrayList<Reward>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashbroadScreen frame = new DashbroadScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void RanIdEmployee() {

		ArrayList<String> array = new ArrayList<String>();
		double _min = 0;
		String[] array2;
		Vector<Object> listEmp = new Vector<>();
		listEmp = ListEmployeeScreen.getData();
		File file = new File("Login.txt");

		try {
			if (file.exists() == true) {
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				String data = br.readLine();
				while (data != null) {
					String token[] = data.split(",");
					data = br.readLine();
					array.add(token[0]);

				}
				br.close();
				fr.close();
			} else {

			}
			Random ran = new Random();
			if (listEmp != null) {
				for (int i = 0; i < listEmp.size(); i++) {
					array2 = listEmp.get(i).toString().split(",");
					array.add(array2[1]);
				}
			} else {

			}
			if(array.size()>0) {
				_min = Double.parseDouble(array.get(0));
				for (int i = 0; i < array.size(); i++) {
					double temp = Double.parseDouble(array.get(i));
					if (temp < _min) {
						_min = temp;
					} else {
						
					}
			}
				int result = (int) _min + ran.nextInt(array.size());

				String t1 = String.valueOf(result / 1000);
				String t2 = String.valueOf((result / 100) % 10);
				String t3 = String.valueOf((result % 100) / 10);
				String t4 = String.valueOf(result % 10);
				txt1.setText(t1);
				txt2.setText(t2);
				txt3.setText(t3);
				txt4.setText(t4);
			
			}else {
				JOptionPane.showMessageDialog(null,
					    "Chọn thêm nhân viên để quay thưởng !");
			}

			

//			for(int  i =min ;i<(min +countLine);i++) {
//				if(result == arr[i] ) {
//					System.out.println(arr[i]);
//					
//					break;
//				}
//				else {
//					
//				}
//
//			}

			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private JTextField txtTurn;
	private JTable table;

	private void readFileReward() {
		File file = new File("Reward.txt");
		try {
			if(file.exists() == true) {
			List<String> lines = FileUtils.readLines(file, "UTF-8");
			for (String st : lines) {
				String token[] = st.split(",");
				txtClazz.setText(token[1]);

				rewardId = Integer.parseInt(token[0]);
				turn = Integer.parseInt(token[2]);

			}}
			else {
				
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void updateFileReward() {

	}

	private void writeFileDetailReward(DetailReward dere) {
		File file = new File("RewardDetail.txt");

		try {
			FileWriter fw = new FileWriter(file, true);
			FileReader fr = new FileReader(file);
			int i = 0;
			try {
				BufferedWriter bw = new BufferedWriter(fw);
				BufferedReader br = new BufferedReader(fr);

				while (br.readLine() != null) {
					i++;
				}
				dere.setId(i + 1);
				bw.write(dere.toString());

				bw.newLine();
				bw.flush();
				bw.close();

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {

		}
	}

	/**
	 * Create the frame.
	 */
	public DashbroadScreen() {
		ArrayList<Reward> reList = new ArrayList<Reward>();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 300, 30);
		contentPane.add(menuBar);

		JMenuItem mnEmployee = new JMenuItem("Employee");
		menuBar.add(mnEmployee);

		JMenuItem mnPrize = new JMenuItem("Prize");
		menuBar.add(mnPrize);

		JMenuItem mnDb = new JMenuItem("Dashbroad");
		mnDb.setBackground(SystemColor.activeCaption);
		menuBar.add(mnDb);

		JPanel panel = new JPanel();
		panel.setBounds(12, 57, 958, 567);
		contentPane.add(panel);
		panel.setLayout(null);
		txtClazz = new JTextField();
		txtClazz.setEditable(false);
		txtClazz.setBounds(244, 275, 100, 30);
		panel.add(txtClazz);
		txtClazz.setColumns(10);

		txtTurn = new JTextField();
		txtTurn.setEditable(false);
		txtTurn.setColumns(10);
		txtTurn.setBounds(244, 321, 100, 30);
		panel.add(txtTurn);

		readFileReward();

		txtM = new JTextField();
		txtM.setHorizontalAlignment(SwingConstants.CENTER);
		txtM.setText("M");
		txtM.setFont(new Font("Dialog", Font.BOLD, 20));
		txtM.setEditable(false);
		txtM.setBounds(65, 115, 50, 60);
		panel.add(txtM);
		txtM.setColumns(10);

		txtN = new JTextField();
		txtN.setFont(new Font("Dialog", Font.BOLD, 20));
		txtN.setText("N");
		txtN.setHorizontalAlignment(SwingConstants.CENTER);
		txtN.setEditable(false);
		txtN.setColumns(10);
		txtN.setBounds(127, 115, 50, 60);
		panel.add(txtN);

		txtV = new JTextField();
		txtV.setFont(new Font("Dialog", Font.BOLD, 20));
		txtV.setHorizontalAlignment(SwingConstants.CENTER);
		txtV.setText("V");
		txtV.setEditable(false);
		txtV.setColumns(10);
		txtV.setBounds(189, 115, 50, 60);
		panel.add(txtV);

		txt1 = new JTextField();
		txt1.setHorizontalAlignment(SwingConstants.CENTER);
		txt1.setFont(new Font("Dialog", Font.BOLD, 20));
		txt1.setEditable(false);
		txt1.setColumns(10);
		txt1.setBounds(251, 115, 50, 60);
		panel.add(txt1);

		txt2 = new JTextField();
		txt2.setFont(new Font("Dialog", Font.BOLD, 20));
		txt2.setHorizontalAlignment(SwingConstants.CENTER);
		txt2.setEditable(false);
		txt2.setColumns(10);
		txt2.setBounds(313, 115, 50, 60);
		panel.add(txt2);

		txt3 = new JTextField();
		txt3.setHorizontalAlignment(SwingConstants.CENTER);
		txt3.setFont(new Font("Dialog", Font.BOLD, 20));
		txt3.setEditable(false);
		txt3.setColumns(10);
		txt3.setBounds(374, 115, 50, 60);
		panel.add(txt3);

		txt4 = new JTextField();
		txt4.setHorizontalAlignment(SwingConstants.CENTER);
		txt4.setFont(new Font("Dialog", Font.BOLD, 20));
		txt4.setEditable(false);
		txt4.setColumns(10);
		txt4.setBounds(436, 115, 50, 60);
		panel.add(txt4);
		txtTurn.setText(String.valueOf(turn));
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					RanIdEmployee();

					DetailReward dere = new DetailReward();
					String employeeId = txt1.getText() + txt2.getText() + txt3.getText() + txt4.getText();
					dere.setEmployeeId(Integer.parseInt(employeeId));

					dere.setRewardId(rewardId);

					writeFileDetailReward(dere);

					turn--;
//					System.out.println(turn);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		btnStart.setFont(new Font("Dialog", Font.BOLD, 20));
		btnStart.setBounds(324, 462, 100, 50);
		panel.add(btnStart);

		JLabel lblNewLabel = new JLabel("Giải");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel.setBounds(92, 275, 112, 29);
		panel.add(lblNewLabel);

		JLabel lblSLnQuay = new JLabel("Số lần quay còn lại");
		lblSLnQuay.setFont(new Font("Dialog", Font.BOLD, 14));
		lblSLnQuay.setBounds(92, 316, 130, 29);
		panel.add(lblSLnQuay);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(584, 40, 362, 515);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JButton btnLogout = new JButton("Log out");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					JDialog.setDefaultLookAndFeelDecorated(true);
					int response = JOptionPane.showConfirmDialog(null, "Do you want to log out?", "Confirm",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (response == JOptionPane.YES_OPTION) {
						setVisible(false);
						LoginScreen loginScreen = new LoginScreen();
						loginScreen.setVisible(true);

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnLogout.setFont(new Font("Dialog", Font.BOLD, 13));
		btnLogout.setBounds(884, 0, 98, 26);
		contentPane.add(btnLogout);
		mnEmployee.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ListEmployeeScreen employeeScreen = new ListEmployeeScreen();
				employeeScreen.setVisible(true);
				setVisible(false);
			}
		});
		mnPrize.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PrizeScreen prizeScreen = new PrizeScreen();
				prizeScreen.setVisible(true);
				setVisible(false);

			}
		});
	}
}
