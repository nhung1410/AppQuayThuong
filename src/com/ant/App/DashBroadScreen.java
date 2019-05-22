package com.ant.App;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DashBroadScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashBroadScreen frame = new DashBroadScreen();
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

	public DashBroadScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JMenu profiel = new JMenu();
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 125, 782, 410);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnStart = new JButton("Start");
		btnStart.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnStart.setBounds(318, 547, 111, 62);
		contentPane.add(btnStart);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 782, 26);
		contentPane.add(menuBar);
		
		JMenu mnEmployee = new JMenu("Employee");
		menuBar.add(mnEmployee);
		
		JMenu mnPrize = new JMenu("Prize");
		menuBar.add(mnPrize);
		
		JButton btnNewButton = new JButton("Select file.xsls");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				
			}
		});
		btnNewButton.setBounds(12, 54, 134, 25);
		contentPane.add(btnNewButton);
	}
}
