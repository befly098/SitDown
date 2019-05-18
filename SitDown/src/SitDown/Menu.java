package SitDown;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;

public class Menu implements ActionListener {
	
	JPanel menuPanel;
	static JTable menuTable;
	
	JPanel menuTablePanel; // 테이블+버튼 담을 패널 
	JPanel menuInfoPanel; // 메뉴 정보 담을 패널 (그리드 3,4)
	JPanel menuTableBtnPanel;  // 텍스트 필드와 버튼 담을 패널 
	
	JLabel menuNameInfo2;
	JLabel menuPriceInfo2;
	JLabel menuOriginalPriceinfo2;
	JLabel menuIngredientInfo2;
	// 메뉴 인포 테이블에 들어갈 라벨 
	
	static DefaultTableModel menuTableModel;
	
	// 여기서부터 
	static DefaultTableModel unvisibleTableModel;
	static JTable unvisibleTable;
	Frame addDialog;
	JTextField menuFrameName;
	JTextField menuFramePrice;
	JTextField menuFrameOriginalPrice;
	JTextArea menuFrameIngredients;
	JPanel dialogPanel;
	
	JLabel menuNameInfo;
	JLabel menuPriceInfo;
	JLabel menuOriginalPriceInfo;
	JLabel menuIngredientInfo;
	// 여기까지 메뉴 추가 다이얼로그에 사용한 변수 
	
	// 여기서부터 
	Frame editDialog;
	JTextField menuFrameName3;
	JTextField menuFramePrice3;
	JTextField menuFrameOriginalPrice3;
	JTextArea menuFrameIngredients3;
	JPanel editPanel;
	JLabel menuNameInfo3;
	JLabel menuPriceInfo3;
	JLabel menuOriginalPriceInfo3;
	JLabel menuIngredientInfo3;
	// 여기까지 메뉴 편집 다이얼로그에 사용한 변수 
	
	JTextField menu_name; //쓰는 곳이 없는듯
	// 메뉴 테이블에 들어갈 텍스트필드 
	
	JTextField menuInfoName;
	JTextField menuInfoPrice;
	JTextField menuInfoOriginalPrice;
	JTextArea menuInfoIngredients;
	// 메뉴 인포 테이블에 들어갈 텍스트 필드 
	
	JButton menuAdd;
	JButton menuEdit;
	JButton menuDelete;
	JButton menuInfo;
	JButton menuFrameAdd;
	JButton menuFrameEdit;
	// 메뉴 추가 삭제 버튼 
	
	private static final String NAME = "이름";
	private static final String PRICE = "가격";
	private static final String PRODUCTIONPRICE = "생산단가";
	private static final String MATERIAL = "재료";
	private static final String USED = "사용된 재료";
	
	public Menu() {
		
		menuPanel = new JPanel();
		menuPanel.setLayout(new BorderLayout());
		menuTablePanel = new JPanel();
		menuTablePanel.setLayout(new BorderLayout());
		menuTableBtnPanel = new JPanel();
		menuTableBtnPanel.setLayout(new GridLayout(1,2));
		menuInfoPanel = new JPanel();
		menuInfoPanel.setLayout(new GridLayout(5,2));
		
		//
		unvisibleTable = new JTable();	//스태틱
		// 텍스트필드에 넣을 안보이는 테이블 
		
		try {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("MenuTable"));
			ObjectInputStream inputStream2 = new ObjectInputStream(new FileInputStream("unvisibleMenuTable"));
			
			Vector rowData = (Vector)inputStream.readObject();
			Vector columnNames = (Vector)inputStream.readObject();
			if(rowData.isEmpty()) {
				Vector<String> userColumn = new Vector<> ();
				userColumn.addElement(NAME);
				menuTableModel = new DefaultTableModel(userColumn, 0);	//스태틱
			}
			
			else {
				menuTableModel = new DefaultTableModel();	//스태틱
				menuTableModel.setDataVector(rowData, columnNames);
			}
			
			Vector rowData2 = (Vector)inputStream2.readObject();
			Vector columnNames2 = (Vector)inputStream2.readObject();
			if(rowData2.isEmpty()) {
				Vector<String> userColumnUnvisible = new Vector<> ();
				userColumnUnvisible.addElement(PRICE);
				userColumnUnvisible.addElement(PRODUCTIONPRICE);
				userColumnUnvisible.addElement(MATERIAL);
				unvisibleTableModel = new DefaultTableModel(userColumnUnvisible, 0);	//스태틱
			}
			
			else {
				unvisibleTableModel = new DefaultTableModel();	//스태틱
				unvisibleTableModel.setDataVector(rowData2, columnNames2);
			}
			
			inputStream.close();
			inputStream2.close();
			
		} catch(/*FileNotFoundException | */ClassNotFoundException | IOException e) { //저거 포함되면 안됨...
			
			Vector<String> userColumn = new Vector<> ();
			userColumn.addElement(NAME);
			menuTableModel = new DefaultTableModel(userColumn, 0);	//스태틱
			
			Vector<String> userColumnUnvisible = new Vector<> ();
			userColumnUnvisible.addElement(PRICE);
			userColumnUnvisible.addElement(PRODUCTIONPRICE);
			userColumnUnvisible.addElement(MATERIAL);
			unvisibleTableModel = new DefaultTableModel(userColumnUnvisible, 0);	//스태틱

			e.printStackTrace();
		}
		
		menuTable = new JTable(menuTableModel);	//스태틱
		JScrollPane scrollpane = new JScrollPane(menuTable);
		menuTablePanel.add(scrollpane, BorderLayout.CENTER);
		unvisibleTable = new JTable(unvisibleTableModel);	//스태틱
		
		menuNameInfo = new JLabel(NAME);
		menuNameInfo.setHorizontalAlignment(JLabel.CENTER);
		menuPriceInfo = new JLabel(PRICE);
		menuPriceInfo.setHorizontalAlignment(JLabel.CENTER);
		menuOriginalPriceInfo = new JLabel(PRODUCTIONPRICE);
		menuOriginalPriceInfo.setHorizontalAlignment(JLabel.CENTER);
		menuIngredientInfo = new JLabel(USED);
		menuIngredientInfo.setHorizontalAlignment(JLabel.CENTER);
		
		menuNameInfo2 = new JLabel(NAME);
		menuNameInfo2.setHorizontalAlignment(JLabel.CENTER);
		menuPriceInfo2 = new JLabel(PRICE);
		menuPriceInfo2.setHorizontalAlignment(JLabel.CENTER);
		menuOriginalPriceinfo2 = new JLabel(PRODUCTIONPRICE);
		menuOriginalPriceinfo2.setHorizontalAlignment(JLabel.CENTER);
		menuIngredientInfo2 = new JLabel(USED);
		menuIngredientInfo2.setHorizontalAlignment(JLabel.CENTER);
		
		menuNameInfo3 = new JLabel(NAME);
		menuNameInfo3.setHorizontalAlignment(JLabel.CENTER);
		menuPriceInfo3 = new JLabel(PRICE);
		menuPriceInfo3.setHorizontalAlignment(JLabel.CENTER);
		menuOriginalPriceInfo3 = new JLabel(PRODUCTIONPRICE);
		menuOriginalPriceInfo3.setHorizontalAlignment(JLabel.CENTER);
		menuIngredientInfo3 = new JLabel(USED);
		menuIngredientInfo3.setHorizontalAlignment(JLabel.CENTER);
		
		menuAdd = new JButton("추가");
		menuAdd.addActionListener(this);
		menuEdit = new JButton("편집");
		menuEdit.addActionListener(this);
		menuDelete = new JButton("삭제");
		menuDelete.addActionListener(this);
		menuInfo = new JButton("상세정보");
		menuInfo.addActionListener(this);
		
		menuTableBtnPanel.add(menuAdd);
		menuTableBtnPanel.add(menuInfo);
		menuTablePanel.add(menuTableBtnPanel, BorderLayout.SOUTH);
		
		menuInfoName = new JTextField(10);
		menuInfoPrice = new JTextField(10);
		menuInfoOriginalPrice = new JTextField(10);
		menuInfoIngredients = new JTextArea(20,10);
		JScrollPane menuInfoscrollPane=new JScrollPane(menuInfoIngredients);
		
		
		menuInfoPanel.add(menuNameInfo2);
		menuInfoPanel.add(menuInfoName);
		menuInfoPanel.add(menuPriceInfo2);
		menuInfoPanel.add(menuInfoPrice);
		menuInfoPanel.add(menuOriginalPriceinfo2);
		menuInfoPanel.add(menuInfoOriginalPrice);
		menuInfoPanel.add(menuIngredientInfo2);
		menuInfoPanel.add(menuInfoscrollPane);
		menuInfoPanel.add(menuEdit);
		menuInfoPanel.add(menuDelete);
		
		menuPanel.add(menuInfoPanel, BorderLayout.EAST);
		menuPanel.add(menuTablePanel);
	}

	void FrameOpen() {
		
		addDialog = new Frame("메뉴 정보 입력");
		addDialog.setSize(300, 300);
		addDialog.setLayout(new BorderLayout());
		addDialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				addDialog.setVisible(false);
			}
		});
		
		menuFrameAdd = new JButton("내용 추가");
		menuFrameAdd.addActionListener(this);
		
		menuFrameName = new JTextField(10);
		menuFramePrice = new JTextField(10);
		menuFrameOriginalPrice = new JTextField(10);
		menuFrameIngredients = new JTextArea(20,10); //added
		JScrollPane scrollPane=new JScrollPane(menuFrameIngredients);
		
		dialogPanel = new JPanel();
		dialogPanel.setLayout(new GridLayout(4,2));
		
		dialogPanel.add(menuNameInfo);
		dialogPanel.add(menuFrameName);
		dialogPanel.add(menuPriceInfo);
		dialogPanel.add(menuFramePrice);
		dialogPanel.add(menuOriginalPriceInfo);
		dialogPanel.add(menuFrameOriginalPrice);
		dialogPanel.add(menuIngredientInfo);
		dialogPanel.add(scrollPane);
		
		addDialog.add(menuFrameAdd, BorderLayout.SOUTH);
		addDialog.add(dialogPanel);
		addDialog.setVisible(true);
	}
	
	void FrameEdit() {
		
		editDialog = new Frame("메뉴 정보 입력");
		editDialog.setSize(300, 300);
		editDialog.setLayout(new BorderLayout());
		editDialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				editDialog.setVisible(false);
			}
		});
		
		menuFrameEdit = new JButton("편집 완료");
		menuFrameEdit.addActionListener(this);
		
		menuFrameName3 = new JTextField(10);
		menuFramePrice3 = new JTextField(10);
		menuFrameOriginalPrice3 = new JTextField(10);
		menuFrameIngredients3 = new JTextArea(20,10); //added
		JScrollPane scrollPane3=new JScrollPane(menuFrameIngredients3);
		
		editPanel = new JPanel();
		editPanel.setLayout(new GridLayout(4,2));
		
		editPanel.add(menuNameInfo3);
		editPanel.add(menuFrameName3);
		editPanel.add(menuPriceInfo3);
		editPanel.add(menuFramePrice3);
		editPanel.add(menuOriginalPriceInfo3);
		editPanel.add(menuFrameOriginalPrice3);
		editPanel.add(menuIngredientInfo3);
		editPanel.add(scrollPane3);
		
		int row = menuTable.getSelectedRow();
		String data = (String) menuTable.getValueAt(row, 0);
		menuFrameName3.setText(data);
		data = (String) unvisibleTable.getValueAt(row, 0);
		menuFramePrice3.setText(data);
		data = (String) unvisibleTable.getValueAt(row, 1);
		menuFrameOriginalPrice3.setText(data);
		data = (String) unvisibleTable.getValueAt(row, 2);
		menuFrameIngredients3.setText(data);
		
		editDialog.add(menuFrameEdit, BorderLayout.SOUTH);
		editDialog.add(editPanel);
		editDialog.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		
		if(actionCommand.equals("추가")) {
			FrameOpen();
		}
		
		else if(actionCommand.equals("상세정보")) {
			int row = menuTable.getSelectedRow();
			String data = (String) menuTable.getValueAt(row, 0);
			menuInfoName.setText(data);
			data = (String) unvisibleTable.getValueAt(row, 0);
			menuInfoPrice.setText(data);
			data = (String) unvisibleTable.getValueAt(row, 1);
			menuInfoOriginalPrice.setText(data);
			data = (String) unvisibleTable.getValueAt(row, 2);
			menuInfoIngredients.setText(data);
		}
		
		else if(actionCommand.equals("내용 추가")) {
			menuTableModel.addRow(new Object[] {menuFrameName.getText()});
			unvisibleTableModel.addRow(new Object[] {menuFramePrice.getText(), menuFrameOriginalPrice.getText(), menuFrameIngredients.getText()});
			addDialog.setVisible(false);
		}
		
		else if(actionCommand.equals("편집")) {
			FrameEdit();
		}
		
		else if(actionCommand.equals("삭제")) {
			int row = menuTable.getSelectedRow();
			menuTableModel.removeRow(row);
			unvisibleTableModel.removeRow(row);
			menuInfoName.setText("");
			menuInfoPrice.setText("");
			menuInfoOriginalPrice.setText("");
			menuInfoIngredients.setText("");
		}
		
		else if(actionCommand.equals("편집 완료")) {
			int row = menuTable.getSelectedRow();
			menuTable.setValueAt(menuFrameName3.getText(), row, 0);
			unvisibleTable.setValueAt(menuFramePrice3.getText(), row, 0);
			unvisibleTable.setValueAt(menuFrameOriginalPrice3.getText(), row, 1);
			unvisibleTable.setValueAt(menuFrameIngredients3.getText(), row, 2);
			
			String data = (String) menuTable.getValueAt(row, 0);
			menuInfoName.setText(data);
			data = (String) unvisibleTable.getValueAt(row, 0);
			menuInfoPrice.setText(data);
			data = (String) unvisibleTable.getValueAt(row, 1);
			menuInfoOriginalPrice.setText(data);
			data = (String) unvisibleTable.getValueAt(row, 2);
			System.out.println(data);
			menuInfoIngredients.setText(data);
			
			editDialog.setVisible(false);
		}
		
	}

}