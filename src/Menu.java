package SitDown;

import java.sql.Connection;
import java.sql.DriverManager;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

import java.awt.event.WindowAdapter;

public class Menu implements ActionListener {
	
	Connection con = null;
	
	String className = "org.gjt.mm.mysql.Driver";
	String url = "jdbc:mysql://localhost:3306/SitDown?useSSL=false&useUnicode=true&characterEncoding=euckr";
	String user = "root";
	String passwd = "123456";
	String sql = "INSERT INTO Menu(Fname, Fprice, Fbase) VALUES";
	Statement stmt = null;
	PreparedStatement pstmt = null;
	
	JPanel menuPanel;
	static JTable menuTable;
	
	JPanel menuTablePanel; // 테이블+버튼 담을 패널 
	JPanel menuInfoPanel; // 메뉴 정보 담을 패널 (그리드 3,4)
	JPanel menuTableBtnPanel;  // 텍스트 필드와 버튼 담을 패널 
	
	JLabel menuNameInfo2;
	JLabel menuPriceInfo2;
	JLabel menuOriginalPriceInfo2;
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
	
	JTextField menuName;
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
		unvisibleTable = new JTable();
		// 텍스트필드에 넣을 안보이는 테이블 
		
		try {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("MenuTable"));
			ObjectInputStream inputStream2 = new ObjectInputStream(new FileInputStream("unvisibleMenuTable"));
			
			Vector rowData = (Vector)inputStream.readObject();
			Vector columnNames = (Vector)inputStream.readObject();
			if(rowData.isEmpty()) {
				Vector<String> userColumn = new Vector<> ();
				userColumn.addElement("이름");
				menuTableModel = new DefaultTableModel(userColumn, 0) {
					@Override
					public boolean isCellEditable(int i, int c) {
						return false;
					}
				};
			}
			
			else {
				menuTableModel = new DefaultTableModel() {
					@Override
					public boolean isCellEditable(int i, int c) {
						return false;
					}
				};
				menuTableModel.setDataVector(rowData, columnNames);
			}
			
			Vector rowData2 = (Vector)inputStream2.readObject();
			Vector columnNames2 = (Vector)inputStream2.readObject();
			if(rowData2.isEmpty()) {
				Vector<String> userColumnUnvisible = new Vector<> ();
				userColumnUnvisible.addElement("가격");
				userColumnUnvisible.addElement("생산단가");
				userColumnUnvisible.addElement("재료");
				unvisibleTableModel = new DefaultTableModel(userColumnUnvisible, 0);
			}
			
			else {
				unvisibleTableModel = new DefaultTableModel();
				unvisibleTableModel.setDataVector(rowData2, columnNames2);
			}
			
			inputStream.close();
			inputStream2.close();
			
		} catch(ClassNotFoundException | IOException e) {
			
			Vector<String> userColumn = new Vector<> ();
			userColumn.addElement("이름");
			menuTableModel = new DefaultTableModel(userColumn, 0);
			
			Vector<String> userColumnUnvisible = new Vector<> ();
			userColumnUnvisible.addElement("가격");
			userColumnUnvisible.addElement("생산단가");
			userColumnUnvisible.addElement("재료");
			unvisibleTableModel = new DefaultTableModel(userColumnUnvisible, 0);

			e.printStackTrace();
		}

		menuTable = new JTable(menuTableModel);
		JScrollPane scrollpane = new JScrollPane(menuTable);
		menuTablePanel.add(scrollpane, BorderLayout.CENTER);
		unvisibleTable = new JTable(unvisibleTableModel);
		
		menuTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		menuTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = menuTable.getSelectedRow();
					String data = (String)menuTable.getValueAt(row, 0);
					menuInfoName.setText(data);
					data = String.valueOf(unvisibleTable.getValueAt(row,  0));
					menuInfoPrice.setText(data);
					data = String.valueOf(unvisibleTable.getValueAt(row,  1));
					menuInfoOriginalPrice.setText(data);
					data = String.valueOf(unvisibleTable.getValueAt(row, 2));
					menuInfoIngredients.setText(data);
					
				}
			}
		});
		
		menuNameInfo = new JLabel("이름");
		menuNameInfo.setHorizontalAlignment(JLabel.CENTER);
		menuPriceInfo = new JLabel("가격");
		menuPriceInfo.setHorizontalAlignment(JLabel.CENTER);
		menuOriginalPriceInfo = new JLabel("생산단가");
		menuOriginalPriceInfo.setHorizontalAlignment(JLabel.CENTER);
		menuIngredientInfo = new JLabel("사용된 재료");
		menuIngredientInfo.setHorizontalAlignment(JLabel.CENTER);
		
		menuNameInfo2 = new JLabel("이름");
		menuNameInfo2.setHorizontalAlignment(JLabel.CENTER);
		menuPriceInfo2 = new JLabel("가격");
		menuPriceInfo2.setHorizontalAlignment(JLabel.CENTER);
		menuOriginalPriceInfo2 = new JLabel("생산단가");
		menuOriginalPriceInfo2.setHorizontalAlignment(JLabel.CENTER);
		menuIngredientInfo2 = new JLabel("사용된 재료");
		menuIngredientInfo2.setHorizontalAlignment(JLabel.CENTER);
		
		menuNameInfo3 = new JLabel("이름");
		menuNameInfo3.setHorizontalAlignment(JLabel.CENTER);
		menuPriceInfo3 = new JLabel("가격");
		menuPriceInfo3.setHorizontalAlignment(JLabel.CENTER);
		menuOriginalPriceInfo3 = new JLabel("생산단가");
		menuOriginalPriceInfo3.setHorizontalAlignment(JLabel.CENTER);
		menuIngredientInfo3 = new JLabel("사용된 재료");
		menuIngredientInfo3.setHorizontalAlignment(JLabel.CENTER);
		
		menuAdd = new JButton("추가");
		menuAdd.addActionListener(this);
		menuEdit = new JButton("편집");
		menuEdit.addActionListener(this);
		menuDelete = new JButton("삭제");
		menuDelete.addActionListener(this);
		
		menuTableBtnPanel.add(menuAdd);
		menuTablePanel.add(menuTableBtnPanel, BorderLayout.SOUTH);
		
		menuInfoName = new JTextField(10);
		menuInfoPrice = new JTextField(10);
		menuInfoOriginalPrice = new JTextField(10);
		menuInfoIngredients = new JTextArea(20,10);
		JScrollPane menuinfoScrollPane=new JScrollPane(menuInfoIngredients);
		
		
		menuInfoPanel.add(menuNameInfo2);
		menuInfoPanel.add(menuInfoName);
		menuInfoPanel.add(menuPriceInfo2);
		menuInfoPanel.add(menuInfoPrice);
		menuInfoPanel.add(menuOriginalPriceInfo2);
		menuInfoPanel.add(menuInfoOriginalPrice);
		menuInfoPanel.add(menuIngredientInfo2);
		menuInfoPanel.add(menuinfoScrollPane);
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
		
		else if(actionCommand.equals("내용 추가")) {
			menuTableModel.addRow(new Object[] {menuFrameName.getText()});
			unvisibleTableModel.addRow(new Object[] {menuFramePrice.getText(), menuFrameOriginalPrice.getText(), menuFrameIngredients.getText()});
			
			sql = "INSERT INTO Menu(Fname, Fprice, Fbase) VALUES";
			sql += "(\"" + menuFrameName.getText() + "\"," + menuFramePrice.getText() + "," + menuFrameOriginalPrice.getText()
			+ ");";
			
			try {
				Class.forName(className);
				con = DriverManager.getConnection(url, user, passwd); 
				stmt = (Statement) con.createStatement();
				stmt.executeUpdate(sql);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			String ingredients = menuFrameIngredients.getText();
			String[] changeIngredients = ingredients.split("\\n");
			
			for(int i=0; i<changeIngredients.length; i++) {
				String[] changeIngredients2 = changeIngredients[i].split(" ");
				
				sql = "INSERT INTO Recipe(Food, Ing, Needs) VALUES";
				sql += "(\"" + menuFrameName.getText() + "\"," + "\"" + changeIngredients2[0] + "\"" + "," + changeIngredients2[1] + ");";
			
				try {
					Class.forName(className);
					con = DriverManager.getConnection(url, user, passwd); 
					stmt = (Statement) con.createStatement();
					stmt.executeUpdate(sql);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
			addDialog.setVisible(false);
		}
		
		else if(actionCommand.equals("편집")) {
			FrameEdit();
		}
		
		else if(actionCommand.equals("삭제")) {
			int row = menuTable.getSelectedRow();
			
			String DBName = String.valueOf(menuTable.getValueAt(row, 0));
			sql = "DELETE FROM Recipe WHERE Food=\"" + DBName + "\";";
			try {
				Class.forName(className);
				con = DriverManager.getConnection(url, user, passwd);
				stmt = (Statement) con.createStatement();
				stmt.executeUpdate(sql);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			sql = "DELETE FROM Menu WHERE Fname=\"" + DBName + "\";";
			try {
				Class.forName(className);
				con = DriverManager.getConnection(url, user, passwd);
				stmt = (Statement) con.createStatement();
				stmt.executeUpdate(sql);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			
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