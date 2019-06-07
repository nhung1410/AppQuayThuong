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
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
import org.apache.xmlbeans.XmlCursor.TokenType;

import com.ant.Util.ButtonEditor;
import com.ant.Util.ButtonRender;
import com.ant.entities.Reward;
import com.ant.entities.User;

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
		ArrayList<Reward> list = new ArrayList<Reward>();
		File file = new File("Reward.txt");
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String data = br.readLine();

			while (data != null) {
				String token[] = data.split(",");


				Reward reward = new Reward();

				reward.setId(Integer.parseInt(token[0]));
				reward.setClazz(token[1]);
				reward.setTurns(Integer.parseInt(token[2]));
				reward.setPrize(token[3]);

				list.add(reward);

				System.out.println("asdsad" + list.size());
				data = br.readLine();

			}
			br.close();
			fr.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	private void writeFileReward(Reward reward) throws IOException {

		File f = new File("Reward.txt");
		FileWriter fw = new FileWriter(f, true);


		try {
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(reward.toString());
			bw.newLine();
			bw.flush();
			bw.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private void showFileInTable(ArrayList<Reward> list, DefaultTableModel model) {

		try {
			int i = 0;
			for (Reward reward : list) {
				i++;
				@SuppressWarnings("rawtypes")
				Vector<Object> row = new Vector<Object>();
				row.add(i);
				row.add(reward.getId());
				row.add(reward.getClazz());
				row.add(reward.getTurns());
				row.add(reward.getPrize());

				model.addRow(row);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Create the frame.
	 */
	public PrizeScreen() {
		ArrayList<Reward> readFileReward = readFileReward();
		System.out.println("bbbbb" + readFileReward.size());
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
			private static final long serialVersionUID = 1L;

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
//				String str = "";
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
