package com.ant.Util;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.apache.poi.ss.usermodel.Row;

public class ButtonEditor extends DefaultCellEditor{
	protected JButton button;
	  private String    label;
	  private boolean   isPushed;
	  
	  public ButtonEditor(JCheckBox checkBox, JTable table) {
	    super(checkBox);
	    button = new JButton();
	    button.setOpaque(true);
	    button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
//				fireEditingStopped();
				int selectRow = table.getSelectedRow();
				int countCol = table.getColumnCount();
				for (int i = 0; i < countCol; i++) {
					table.remove(selectRow);
				}
			}
		});

	  }
	 
	  


	public Component getTableCellEditorComponent(JTable table, Object value,
	                   boolean isSelected, int row, int column) {
	    if (isSelected) {
	      button.setForeground(table.getSelectionForeground());
	      button.setBackground(table.getSelectionBackground());
	    } else{
	      button.setForeground(table.getForeground());
	      button.setBackground(table.getBackground());
	    }
	    label = (value ==null) ? "" : value.toString();
	    button.setText( label );
	    isPushed = true;
	    return button;
	  }
	  
	  public Object getCellEditorValue() {
	    if (isPushed)  {
	      
	      JOptionPane.showMessageDialog(button ,label + ": Ouch!");
	     
	    }
	    isPushed = false;
	    return new String( label ) ;
	  }
	    
	  public boolean stopCellEditing() {
	    isPushed = false;
	    return super.stopCellEditing();
	  }
	  
	  protected void fireEditingStopped() {
	    super.fireEditingStopped();
	  }
}
