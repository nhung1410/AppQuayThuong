package com.ant.Util;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import javafx.scene.control.Cell;

public class ButtonRender implements TableCellRenderer {
	JButton button = new JButton("Delete");

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (isSelected ) {
			if (hasFocus) {

				Connection conn = null;
				PreparedStatement pst = null;
				try {
					conn = SqliteConnection.dbConnector();
					pst = conn.prepareStatement("DELETE FROM reward WHERE  id = ?");
					int _id = Integer.parseInt((table.getValueAt(row, 0)).toString());
					pst.setInt(1, _id);
					pst.execute();
					((DefaultTableModel) table.getModel()).removeRow(row);

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						pst.close();
						conn.close();
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
			else {
				
			}
		}

		return button;
	}
}
