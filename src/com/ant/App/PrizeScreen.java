package com.ant.App;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.io.FileUtils;
import com.ant.Util.ButtonEditor;
import com.ant.Util.ButtonRender;
import com.ant.Util.SqliteConnection;
import com.ant.entities.Reward;
import java.sql.*;
import java.sql.Connection;

public class PrizeScreen extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtTurns;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrizeScreen frame = new PrizeScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private ArrayList<Reward> readFileReward() {
		File file = new File("Reward.txt");;
		ArrayList<Reward> list = new ArrayList<Reward>();
		BufferedReader br = null;
		try {
			if(file.exists() == true) {
			 br = new BufferedReader(new FileReader(file));
			String data = br.readLine();

			while (data != null) {
				String token[] = data.split(",");
				Reward reward = new Reward();
				
				reward.setId(Integer.parseInt(token[0]));
				reward.setClazz(token[1]);
				reward.setTurns(Integer.parseInt(token[2]));
				reward.setPrize(token[3]);

				list.add(reward);
				data = br.readLine();

			}

			}
			else {
				
			}
				
			
		} catch (IOException e) {
			// TODO: handle exception
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	
		return list;
	}

	private void writeFileReward(Reward reward) throws IOException {

		File f = new File("Reward.txt");
		FileWriter fw = new FileWriter(f, true);
		BufferedWriter bw = null;
		try {
			 bw = new BufferedWriter(fw);
			bw.write(reward.toString());
			bw.newLine();
			bw.flush();
			bw.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				bw.close();
			} catch ( IOException e2) {
				e2.printStackTrace();
			}
		}

	}
	
	private void copyFile() throws IOException {

        File source = new File("Reward.txt");
        File dest = new File("TurnReward.txt");
 
        InputStream is = null;
        OutputStream os = null;
 
        try {
            if(dest.exists() == false) {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
             
            byte[] buffer = new byte[1024];
            int length;
             
            while ((length = is.read(buffer)) > 0) {
                 
                os.write(buffer, 0, length);
            }
            }
            else {
				
			}
             
        }catch (Exception e) {
			// TODO: handle exception
		} finally {
 
            if (is != null) {
                is.close();
            }
 
            if (os != null) {
                os.close();
            }
        }
    }


	
	

	private void showFileInTable(ArrayList<Reward> list, DefaultTableModel model) {

		try {
			int i = 0;
			if (list != null) {
				for (Reward reward : list) {
					i++;
				
					Vector<Object> row = new Vector<Object>();
					row.add(i);
					row.add(reward.getId());
					row.add(reward.getClazz());
					row.add(reward.getTurns());
					row.add(reward.getPrize());
					row.add("Delete");
					table.getColumn("Action").setCellRenderer(new ButtonRender());
					table.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox(), table));
					model.addRow(row);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Add prize");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	Connection conn = null;
	

	/**
	 * Create the frame.
	 * 
	 */
	public PrizeScreen()  {
		conn = SqliteConnection.dbConnector();
		try {
			
			copyFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ArrayList<Reward> readFileReward = readFileReward();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 700);
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
		mnPrize.setBackground(SystemColor.activeCaption);
		menuBar.add(mnPrize);

		JMenuItem mnDb = new JMenuItem("Dashbroad");
		menuBar.add(mnDb);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(48, 72, 694, 267);
		contentPane.add(scrollPane);

		table = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};

		scrollPane.setViewportView(table);

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("NO");
		model.addColumn("ID");
		model.addColumn("Class");
		model.addColumn("Turns");
		model.addColumn("Prize");
		model.addColumn("Action");
		table.setModel(model);

		JPanel editPanel = new JPanel();
		editPanel.setBounds(48, 367, 694, 250);
		contentPane.add(editPanel);
		editPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Class");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel.setBounds(12, 12, 55, 30);
		editPanel.add(lblNewLabel);

		JLabel lblTurns = new JLabel("Turns");
		lblTurns.setFont(new Font("Dialog", Font.BOLD, 13));
		lblTurns.setBounds(12, 53, 55, 30);
		editPanel.add(lblTurns);

		JLabel lblPrize = new JLabel("Prize");
		lblPrize.setFont(new Font("Dialog", Font.BOLD, 13));
		lblPrize.setBounds(12, 107, 55, 30);
		editPanel.add(lblPrize);

		String[] item = { "Giải nhất", "Giải nhì", "Giải ba" };

		JComboBox cbbClass = new JComboBox(item);
		cbbClass.setBounds(85, 15, 190, 30);
		editPanel.add(cbbClass);

		txtTurns = new JTextField();
		txtTurns.setBounds(85, 58, 190, 30);
		editPanel.add(txtTurns);
		txtTurns.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBounds(85, 119, 293, 119);
		editPanel.add(panel);
		panel.setLayout(null);

		JEditorPane edtPrize = new JEditorPane();
		edtPrize.setBounds(0, 0, 293, 119);
		panel.add(edtPrize);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				int selectedRow = table.getSelectedRow();
				int selectedColumn = table.getSelectedColumn();
				int columnCount = model.getColumnCount();
				for (int column = 0; column < columnCount; column++) {
					if ("Class".equals(table.getColumnName(column))) {
						cbbClass.setSelectedItem(table.getValueAt(selectedRow, column).toString());

					} else if ("Turns".equals(table.getColumnName(column))) {
						txtTurns.setText(table.getValueAt(selectedRow, column).toString());
					} else if ("Prize".equals(table.getColumnName(column))) {
						edtPrize.setText(table.getValueAt(selectedRow, column).toString());

					} else {

					}

				}
//				table.getValueAt(selectedRow, 1);

			}
		});

		showFileInTable(readFileReward, model);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					Reward reward = new Reward(cbbClass.getItemAt(cbbClass.getSelectedIndex()).toString(),
							Integer.parseInt(txtTurns.getText()), edtPrize.getText());

					Vector row = new Vector();
					row.add(model.getRowCount() + 1);
					row.add(reward.getId());
					row.add(reward.getClazz());
					row.add(reward.getTurns());
					row.add(reward.getPrize());
					row.add("Delete");
					table.getColumn("Action").setCellRenderer(new ButtonRender());
					table.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox(), table));

					model.addRow(row);

					writeFileReward(reward);

					table.setModel(model);
					table.setVisible(true);
					contentPane.updateUI();
					txtTurns.setText("");
					edtPrize.setText("");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		btnAdd.setBounds(513, 19, 98, 30);
		editPanel.add(btnAdd);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String id = "";
				int selectedRow = table.getSelectedRow();
				int columnCount = model.getColumnCount();
				for (int column = 0; column < columnCount; column++) {
					if ("Class".equals(table.getColumnName(column))) {
						table.setValueAt(cbbClass.getSelectedItem(), selectedRow, column);

					} else if ("Turns".equals(table.getColumnName(column))) {
						table.setValueAt(txtTurns.getText(), selectedRow, column);
					} else if ("Prize".equals(table.getColumnName(column))) {
						table.setValueAt(edtPrize.getText(), selectedRow, column);
					} else if ("ID".equals(table.getColumnName(column))) {
						id = table.getValueAt(selectedRow, column).toString();
					} else {

					}
				}

				File f = new File("Reward.txt");
				ArrayList<String> list = new ArrayList<String>();

				try {
//					Scanner r = new Scanner(f);

					List<String> lines = FileUtils.readLines(f, "UTF-8");
					

					for (String st : lines) {
						String[] token = st.split(",");

						if (token[0].equals(id)) {
							list.add(token[0] + "," + cbbClass.getSelectedItem() + "," + txtTurns.getText() + ","
									+ edtPrize.getText());
						} else {
							list.add(st);
						}
					}

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				try (PrintWriter pw = new PrintWriter(f)) {
					for (String s : list) {
						pw.println(s);
					}

				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});

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
		btnUpdate.setBounds(513, 89, 98, 30);
		editPanel.add(btnUpdate);
		mnEmployee.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ListEmployeeScreen employeeScreen = new ListEmployeeScreen();
				employeeScreen.setVisible(true);

				setVisible(false);
			}
		});

		mnDb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DashbroadScreen dashbroadScreen = new DashbroadScreen();
				dashbroadScreen.setVisible(true);
				setVisible(false);
			}
		});
	}
}
