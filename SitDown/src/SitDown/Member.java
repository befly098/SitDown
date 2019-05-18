package SitDown;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Member implements ActionListener {
	
	JPanel memberPanel; // 멤버 패널
	JPanel memberBtnPanel; // 텍스트필드와 버튼 담을 패널 
	JPanel textfieldPanel; // 텍스트필드 패널 
	JPanel btnPanel; // 버튼 패널 
	static JTable memberTable; // 멤버 테이블
	static DefaultTableModel memberTableModel;
	
	JTextField memberNameTextField;
	JTextField memberNumberTextField;
	JTextField memberMileageTextField;
	JTextField memberPhoneTextField;
	// 테이블에 입력할 텍스트 담는 필드
	
	JLabel memberName;
	JLabel memberNumber;
	JLabel memberMileage;
	JLabel memberPhone;
	
	JButton addMember;
	JButton deleteMember;
	JButton editMember;
	// 테이블 관련 버튼 
	
	private static final String NUMBER = "번호";  
	private static final String NAME = "이름";  
	private static final String PHONENUMBER = "연락처";
	private static final String MILEAGE = "마일리지";
	private static final String LEVEL = "회원등급";  
	
	public Member() {
		memberPanel = new JPanel();
		memberPanel.setLayout(new BorderLayout());
		
		try {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("MemberTable"));
			
			Vector rowData = (Vector)inputStream.readObject();
			Vector columnNames = (Vector)inputStream.readObject();
			
			if(rowData.isEmpty()) {
				Vector<String> userColumn = new Vector<> ();
				userColumn.addElement(NUMBER);
				userColumn.addElement(NAME);
				userColumn.addElement(PHONENUMBER);
				userColumn.addElement(MILEAGE);
				userColumn.addElement(LEVEL);
				memberTableModel = new DefaultTableModel(userColumn, 0);
			}
			else {
				memberTableModel = new DefaultTableModel();
				memberTableModel.setDataVector(rowData, columnNames);
			}
			
			inputStream.close();
			
		} catch(FileNotFoundException e) {
			Vector<String> userColumn = new Vector<> ();
			userColumn.addElement(NUMBER);
			userColumn.addElement(NAME);
			userColumn.addElement(PHONENUMBER);
			userColumn.addElement(MILEAGE);
			userColumn.addElement(LEVEL);
			memberTableModel = new DefaultTableModel(userColumn, 0);
		} catch (ClassNotFoundException e) {
			Vector<String> userColumn = new Vector<> ();
			userColumn.addElement(NUMBER);
			userColumn.addElement(NAME);
			userColumn.addElement(PHONENUMBER);
			userColumn.addElement(MILEAGE);
			userColumn.addElement(LEVEL);
			memberTableModel = new DefaultTableModel(userColumn, 0);
			e.printStackTrace();
		} catch (IOException e) {
			Vector<String> userColumn = new Vector<> ();
			userColumn.addElement(NUMBER);
			userColumn.addElement(NAME);
			userColumn.addElement(PHONENUMBER);
			userColumn.addElement(MILEAGE);
			userColumn.addElement(LEVEL);
			memberTableModel = new DefaultTableModel(userColumn, 0);
			e.printStackTrace();
		}
		
		memberTable = new JTable(memberTableModel);
		JScrollPane scrollpane = new JScrollPane(memberTable);
		memberPanel.add(scrollpane, BorderLayout.CENTER);
		
		memberName = new JLabel(NAME);
		memberName.setHorizontalAlignment(JLabel.CENTER);

		memberNumber = new JLabel(NUMBER);
		memberNumber.setHorizontalAlignment(JLabel.CENTER);
		
		memberMileage = new JLabel(MILEAGE);
		memberMileage.setHorizontalAlignment(JLabel.CENTER);
		
		memberPhone = new JLabel(PHONENUMBER);
		memberPhone.setHorizontalAlignment(JLabel.CENTER);
		
		memberNameTextField = new JTextField(10);
		memberNumberTextField = new JTextField(10);
		memberMileageTextField = new JTextField(10);
		memberPhoneTextField = new JTextField(10);
		
		addMember = new JButton("추가");
		addMember.addActionListener(this);
		
		deleteMember = new JButton("삭제");
		deleteMember.addActionListener(this);
		
		editMember = new JButton("편집");
		editMember.addActionListener(this);
		// 텍스트필드와 버튼 생성 
		
		memberBtnPanel = new JPanel();
		memberBtnPanel.setLayout(new GridLayout(2,1));
		btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(1,3));
		textfieldPanel = new JPanel();
		textfieldPanel.setLayout(new GridLayout(2,4));
		
		textfieldPanel.add(memberNumber);
		textfieldPanel.add(memberName);
		textfieldPanel.add(memberPhone);
		textfieldPanel.add(memberMileage);
		textfieldPanel.add(memberNumberTextField);
		textfieldPanel.add(memberNameTextField);
		textfieldPanel.add(memberPhoneTextField);
		textfieldPanel.add(memberMileageTextField);
		
		btnPanel.add(addMember);
		btnPanel.add(deleteMember);
		btnPanel.add(editMember);
		
		memberBtnPanel.add(textfieldPanel);
		memberBtnPanel.add(btnPanel);
		// 패널에 텍스트필드와 버튼 추가 
		
		memberPanel.add(memberBtnPanel, BorderLayout.SOUTH);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		
		if(actionCommand.equals("추가")) {
			if(Integer.parseInt(memberMileageTextField.getText()) <= 500) {
				memberTableModel.addRow(new Object[] {memberNumberTextField.getText(), memberNameTextField.getText(), memberPhoneTextField.getText(), memberMileageTextField.getText(), "일반"});
			}
			
			else if(Integer.parseInt(memberMileageTextField.getText()) > 500 && Integer.parseInt(memberMileageTextField.getText()) <= 1000) {
				memberTableModel.addRow(new Object[] {memberNumberTextField.getText(), memberNameTextField.getText(), memberPhoneTextField.getText(), memberMileageTextField.getText(), "골드"});
			}
			
			else {
				memberTableModel.addRow(new Object[] {memberNumberTextField.getText(), memberNameTextField.getText(), memberPhoneTextField.getText(), memberMileageTextField.getText(), "플래티넘"});
			}
			
			memberNumberTextField.setText("");
			memberNameTextField.setText("");
			memberPhoneTextField.setText("");
			memberMileageTextField.setText("");
		}
		
		else if(actionCommand.equals("삭제")) {
			memberTableModel.removeRow(memberTable.getSelectedRow());
		}
		
		else if(actionCommand.equals("편집")) {
			int row = memberTable.getSelectedRow();
			memberTable.setValueAt(memberNumberTextField.getText(), row, 0);
			memberTable.setValueAt(memberNameTextField.getText(), row, 1);
			memberTable.setValueAt(memberPhoneTextField.getText(), row, 2);
			memberTable.setValueAt(memberMileageTextField.getText(), row, 3);
			
			if(Integer.parseInt(memberMileageTextField.getText()) <= 500) {
				memberTable.setValueAt("일반", row, 4);
			}
			
			else if(Integer.parseInt(memberMileageTextField.getText()) > 500 && Integer.parseInt(memberMileageTextField.getText()) <= 1000) {
				memberTable.setValueAt("골드", row, 4);
			}
			
			else {
				memberTable.setValueAt("플래티넘", row, 4);
			}
			
			memberNumberTextField.setText("");
			memberNameTextField.setText("");
			memberPhoneTextField.setText("");
			memberMileageTextField.setText("");
		}
		
	}

}
