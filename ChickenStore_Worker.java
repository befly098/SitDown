package ChickenStore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

public class ChickenStore_Worker implements ActionListener {
	
//	Date today = new Date();
//	SimpleDateFormat date = new SimpleDateFormat("yyyy년 MM월 dd일");
	
	JPanel workerPanel; // 멤버 패널
	JPanel workerBtnPanel; // 텍스트필드와 버튼 담을 패널 
	JPanel textfieldPanel; // 텍스트필드 패널
	JPanel btnPanel; // 버튼 패널 
	static JTable workerTable; // 멤버 테이블 
	static DefaultTableModel workerTableModel;
	
	JTextField worker_name;
	JTextField worker_number;
	JTextField worker_rating;
	JTextField worker_salary;
	JTextField worker_phone;
	// 테이블에 입력할 텍스트 담는 필드
	
	JLabel workerName;
	JLabel workerNumber;
	JLabel workerRating;
	JLabel workerSalary;
	JLabel workerPhone;
	
	JButton worker_add;
	JButton worker_delete;
	JButton worker_edit;
	// 테이블 관련 버튼 
	
	public ChickenStore_Worker() {
		workerPanel = new JPanel();
		workerPanel.setLayout(new BorderLayout());
		
		try {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("WorkerTable"));
			
			Vector rowData = (Vector)inputStream.readObject();
			Vector columnNames = (Vector)inputStream.readObject();
			if(rowData.isEmpty()) {
				Vector<String> userColumn = new Vector<String> ();
				userColumn.addElement("번호");
				userColumn.addElement("이름");
				userColumn.addElement("직급");
				userColumn.addElement("월급");
				userColumn.addElement("연락처");
				userColumn.addElement("입사일");
				workerTableModel = new DefaultTableModel(userColumn, 0);
			}
			else {
				workerTableModel = new DefaultTableModel();
				workerTableModel.setDataVector(rowData, columnNames);
			}
		
			inputStream.close();
			
		} catch(FileNotFoundException e) {
			Vector<String> userColumn = new Vector<String> ();
			userColumn.addElement("번호");
			userColumn.addElement("이름");
			userColumn.addElement("직급");
			userColumn.addElement("월급");
			userColumn.addElement("연락처");
			userColumn.addElement("입사일");
			workerTableModel = new DefaultTableModel(userColumn, 0);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			Vector<String> userColumn = new Vector<String> ();
			userColumn.addElement("번호");
			userColumn.addElement("이름");
			userColumn.addElement("직급");
			userColumn.addElement("월급");
			userColumn.addElement("연락처");
			userColumn.addElement("입사일");
			workerTableModel = new DefaultTableModel(userColumn, 0);
			e.printStackTrace();
		} catch (IOException e) {
			Vector<String> userColumn = new Vector<String> ();
			userColumn.addElement("번호");
			userColumn.addElement("이름");
			userColumn.addElement("직급");
			userColumn.addElement("월급");
			userColumn.addElement("연락처");
			userColumn.addElement("입사일");
			workerTableModel = new DefaultTableModel(userColumn, 0);
			e.printStackTrace();
		}
		
		workerTable = new JTable(workerTableModel);
		JScrollPane scrollpane = new JScrollPane(workerTable);
		workerPanel.add(scrollpane, BorderLayout.CENTER);
		
		worker_name = new JTextField(10);
		worker_number = new JTextField(10);
		worker_rating = new JTextField(10);
		worker_salary = new JTextField(10);
		worker_phone = new JTextField(10);
		
		workerNumber = new JLabel("번호");
		workerNumber.setHorizontalAlignment(JLabel.CENTER);
		workerName = new JLabel("이름");
		workerName.setHorizontalAlignment(JLabel.CENTER);
		workerRating = new JLabel("직급");
		workerRating.setHorizontalAlignment(JLabel.CENTER);
		workerSalary = new JLabel("월급");
		workerSalary.setHorizontalAlignment(JLabel.CENTER);
		workerPhone = new JLabel("연락처");
		workerPhone.setHorizontalAlignment(JLabel.CENTER);
		
		worker_add = new JButton("추가");
		worker_add.addActionListener(this);
		
		worker_delete = new JButton("삭제");
		worker_delete.addActionListener(this);
		
		worker_edit = new JButton("편집");
		worker_edit.addActionListener(this);
		// 텍스트필드와 버튼 생성 
		
		workerBtnPanel = new JPanel();
		workerBtnPanel.setLayout(new GridLayout(2,1));
		
		textfieldPanel = new JPanel();
		textfieldPanel.setLayout(new GridLayout(2,5));
		
		btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(1,3));
		
		textfieldPanel.add(workerNumber);
		textfieldPanel.add(workerName);
		textfieldPanel.add(workerRating);
		textfieldPanel.add(workerSalary);
		textfieldPanel.add(workerPhone);
		
		textfieldPanel.add(worker_number);
		textfieldPanel.add(worker_name);
		textfieldPanel.add(worker_rating);
		textfieldPanel.add(worker_salary);
		textfieldPanel.add(worker_phone);
		
		btnPanel.add(worker_add);
		btnPanel.add(worker_delete);
		btnPanel.add(worker_edit);
		// 패널에 텍스트필드와 버튼 추가 
		
		workerBtnPanel.add(textfieldPanel);
		workerBtnPanel.add(btnPanel);
		
		workerPanel.add(workerBtnPanel, BorderLayout.SOUTH);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		
		if(actionCommand.equals("추가")) {
			workerTableModel.addRow(new Object[] {worker_number.getText(), worker_name.getText(), worker_rating.getText(), worker_salary.getText(), worker_phone.getText(), ChickenStore_Final.dateLabel.getText()});
			worker_number.setText("");
			worker_name.setText("");
			worker_salary.setText("");
			worker_rating.setText("");
		}
		
		else if(actionCommand.equals("삭제")) {
			workerTableModel.removeRow(workerTable.getSelectedRow());
		}
		
		else if(actionCommand.equals("편집")) {
			int row = workerTable.getSelectedRow();
			workerTable.setValueAt(worker_number.getText(), row, 0);
			workerTable.setValueAt(worker_name.getText(), row, 1);
			workerTable.setValueAt(worker_rating.getText(), row, 2);
			workerTable.setValueAt(worker_salary.getText(), row, 3);
			workerTable.setValueAt(worker_phone.getText(), row, 4);
		}
		
	}

}

