package SitDown;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JOptionPane;

import java.awt.event.WindowAdapter;

public class Menu implements ActionListener {
	
	Connection con = null;
	
	String className = "org.gjt.mm.mysql.Driver";
	String url = "jdbc:mysql://localhost:3306/SitDown?useSSL=false&useUnicode=true&characterEncoding=euckr";
	String user = "root";
	String passwd = "123456";
	String sql = "INSERT INTO Storage(Iname, Iprice, Iseller, Icontact, Iquant, Iorder) VALUES";
	Statement stmt = null;
	PreparedStatement pstmt = null;
	
	JPanel menuPanel;
	static JTable menuTable;
	
	JPanel menuTablePanel; // 테이블+버튼 담을 패널 
	JPanel menuInfoPanel; // 메뉴 정보 담을 패널 (그리드 3,4)
	JPanel menuTableBtnPanel;  // 텍스트 필드와 버튼 담을 패널 
	
	JLabel menu_nameinfo2;
	JLabel menu_priceinfo2;
	JLabel menu_originalPriceinfo2;
	JLabel menu_ingredientinfo2;
	// 메뉴 인포 테이블에 들어갈 라벨 
	
	static DefaultTableModel menuTableModel;
	
	// 여기서부터 
	static DefaultTableModel unvisibleTableModel;
	static JTable unvisibleTable;
	Frame add_Dialog;
	JTextField menuFrame_name;
	JTextField menuFrame_price;
	JTextField menuFrame_originalPrice;
	JTextArea menuFrame_ingredients;
	JPanel dialogPanel;
	
	JLabel menu_nameinfo;
	JLabel menu_priceinfo;
	JLabel menu_originalPriceinfo;
	JLabel menu_ingredientinfo;
	// 여기까지 메뉴 추가 다이얼로그에 사용한 변수 
	
	// 여기서부터 
	Frame edit_Dialog;
	JTextField menuFrame_name3;
	JTextField menuFrame_price3;
	JTextField menuFrame_originalPrice3;
	JTextArea menuFrame_ingredients3;
	JPanel editPanel;
	JLabel menu_nameinfo3;
	JLabel menu_priceinfo3;
	JLabel menu_originalPriceinfo3;
	JLabel menu_ingredientinfo3;
	// 여기까지 메뉴 편집 다이얼로그에 사용한 변수 
	
	JTextField menu_name;
	// 메뉴 테이블에 들어갈 텍스트필드 
	
	JTextField menuinfo_name;
	JTextField menuinfo_price;
	JTextField menuinfo_originalPrice;
	JTextArea menuinfo_ingredients;
	// 메뉴 인포 테이블에 들어갈 텍스트 필드 
	
	JButton menu_add;
	JButton menu_edit;
	JButton menu_delete;
	JButton menu_info;
	JButton menuFrame_add;
	JButton menuFrame_edit;
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
				Vector<String> userColumn = new Vector<String> ();
				userColumn.addElement("이름");
				menuTableModel = new DefaultTableModel(userColumn, 0) {
					public boolean isCellEditable(int i, int c) {
						return false;
					}
				};
			}
			
			else {
				menuTableModel = new DefaultTableModel() {
					public boolean isCellEditable(int i, int c) {
						return false;
					}
				};
				menuTableModel.setDataVector(rowData, columnNames);
			}
			
			Vector rowData2 = (Vector)inputStream2.readObject();
			Vector columnNames2 = (Vector)inputStream2.readObject();
			if(rowData2.isEmpty()) {
				Vector<String> userColumn_unvisible = new Vector<String> ();
				userColumn_unvisible.addElement("가격");
				userColumn_unvisible.addElement("생산단가");
				userColumn_unvisible.addElement("재료");
				unvisibleTableModel = new DefaultTableModel(userColumn_unvisible, 0);
			}
			
			else {
				unvisibleTableModel = new DefaultTableModel();
				unvisibleTableModel.setDataVector(rowData2, columnNames2);
			}
			
			inputStream.close();
			inputStream2.close();
			
		} catch(FileNotFoundException e) {
			
			Vector<String> userColumn = new Vector<String> ();
			userColumn.addElement("이름");
			menuTableModel = new DefaultTableModel(userColumn, 0);
			
			Vector<String> userColumn_unvisible = new Vector<String> ();
			userColumn_unvisible.addElement("가격");
			userColumn_unvisible.addElement("생산단가");
			userColumn_unvisible.addElement("재료");
			unvisibleTableModel = new DefaultTableModel(userColumn_unvisible, 0);

			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			Vector<String> userColumn = new Vector<String> ();
			userColumn.addElement("이름");
			menuTableModel = new DefaultTableModel(userColumn, 0);
			
			Vector<String> userColumn_unvisible = new Vector<String> ();
			userColumn_unvisible.addElement("가격");
			userColumn_unvisible.addElement("생산단가");
			userColumn_unvisible.addElement("재료");
			unvisibleTableModel = new DefaultTableModel(userColumn_unvisible, 0);
			e.printStackTrace();
		} catch (IOException e) {
			Vector<String> userColumn = new Vector<String> ();
			userColumn.addElement("이름");
			menuTableModel = new DefaultTableModel(userColumn, 0);
			
			Vector<String> userColumn_unvisible = new Vector<String> ();
			userColumn_unvisible.addElement("가격");
			userColumn_unvisible.addElement("생산단가");
			userColumn_unvisible.addElement("재료");
			unvisibleTableModel = new DefaultTableModel(userColumn_unvisible, 0);
			e.printStackTrace();
		}

		menuTable = new JTable(menuTableModel);
		JScrollPane scrollpane = new JScrollPane(menuTable);
		menuTablePanel.add(scrollpane, BorderLayout.CENTER);
		unvisibleTable = new JTable(unvisibleTableModel);
		
		menuTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		menuTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = menuTable.getSelectedRow();
					String data = (String)menuTable.getValueAt(row, 0);
					menuinfo_name.setText(data);
					data = String.valueOf(unvisibleTable.getValueAt(row,  0));
					menuinfo_price.setText(data);
					data = String.valueOf(unvisibleTable.getValueAt(row,  1));
					menuinfo_originalPrice.setText(data);
					data = String.valueOf(unvisibleTable.getValueAt(row, 2));
					menuinfo_ingredients.setText(data);
					
				}
			}
		});
		
		menu_nameinfo = new JLabel("이름");
		menu_nameinfo.setHorizontalAlignment(JLabel.CENTER);
		menu_priceinfo = new JLabel("가격");
		menu_priceinfo.setHorizontalAlignment(JLabel.CENTER);
		menu_originalPriceinfo = new JLabel("생산단가");
		menu_originalPriceinfo.setHorizontalAlignment(JLabel.CENTER);
		menu_ingredientinfo = new JLabel("사용된 재료");
		menu_ingredientinfo.setHorizontalAlignment(JLabel.CENTER);
		
		menu_nameinfo2 = new JLabel("이름");
		menu_nameinfo2.setHorizontalAlignment(JLabel.CENTER);
		menu_priceinfo2 = new JLabel("가격");
		menu_priceinfo2.setHorizontalAlignment(JLabel.CENTER);
		menu_originalPriceinfo2 = new JLabel("생산단가");
		menu_originalPriceinfo2.setHorizontalAlignment(JLabel.CENTER);
		menu_ingredientinfo2 = new JLabel("사용된 재료");
		menu_ingredientinfo2.setHorizontalAlignment(JLabel.CENTER);
		
		menu_nameinfo3 = new JLabel("이름");
		menu_nameinfo3.setHorizontalAlignment(JLabel.CENTER);
		menu_priceinfo3 = new JLabel("가격");
		menu_priceinfo3.setHorizontalAlignment(JLabel.CENTER);
		menu_originalPriceinfo3 = new JLabel("생산단가");
		menu_originalPriceinfo3.setHorizontalAlignment(JLabel.CENTER);
		menu_ingredientinfo3 = new JLabel("사용된 재료");
		menu_ingredientinfo3.setHorizontalAlignment(JLabel.CENTER);
		
		menu_add = new JButton("추가");
		menu_add.addActionListener(this);
		menu_edit = new JButton("편집");
		menu_edit.addActionListener(this);
		menu_delete = new JButton("삭제");
		menu_delete.addActionListener(this);
		
		menuTableBtnPanel.add(menu_add);
		menuTablePanel.add(menuTableBtnPanel, BorderLayout.SOUTH);
		
		menuinfo_name = new JTextField(10);
		menuinfo_price = new JTextField(10);
		menuinfo_originalPrice = new JTextField(10);
		menuinfo_ingredients = new JTextArea(20,10);
		JScrollPane menuinfo_scrollPane=new JScrollPane(menuinfo_ingredients);
		
		
		menuInfoPanel.add(menu_nameinfo2);
		menuInfoPanel.add(menuinfo_name);
		menuInfoPanel.add(menu_priceinfo2);
		menuInfoPanel.add(menuinfo_price);
		menuInfoPanel.add(menu_originalPriceinfo2);
		menuInfoPanel.add(menuinfo_originalPrice);
		menuInfoPanel.add(menu_ingredientinfo2);
		menuInfoPanel.add(menuinfo_scrollPane);
		menuInfoPanel.add(menu_edit);
		menuInfoPanel.add(menu_delete);
		
		menuPanel.add(menuInfoPanel, BorderLayout.EAST);
		menuPanel.add(menuTablePanel);
	}

	void Frame_Open() {
		
		add_Dialog = new Frame("메뉴 정보 입력");
		add_Dialog.setSize(300, 300);
		add_Dialog.setLayout(new BorderLayout());
		add_Dialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				add_Dialog.setVisible(false);
			}
		});
		
		menuFrame_add = new JButton("내용 추가");
		menuFrame_add.addActionListener(this);
		
		menuFrame_name = new JTextField(10);
		menuFrame_price = new JTextField(10);
		menuFrame_originalPrice = new JTextField(10);
		menuFrame_ingredients = new JTextArea(20,10); //added
		JScrollPane scrollPane=new JScrollPane(menuFrame_ingredients);
		
		dialogPanel = new JPanel();
		dialogPanel.setLayout(new GridLayout(4,2));
		
		dialogPanel.add(menu_nameinfo);
		dialogPanel.add(menuFrame_name);
		dialogPanel.add(menu_priceinfo);
		dialogPanel.add(menuFrame_price);
		dialogPanel.add(menu_originalPriceinfo);
		dialogPanel.add(menuFrame_originalPrice);
		dialogPanel.add(menu_ingredientinfo);
		dialogPanel.add(scrollPane);
		
		add_Dialog.add(menuFrame_add, BorderLayout.SOUTH);
		add_Dialog.add(dialogPanel);
		add_Dialog.setVisible(true);
	}
	
	void Frame_Edit() {
		
		edit_Dialog = new Frame("메뉴 정보 입력");
		edit_Dialog.setSize(300, 300);
		edit_Dialog.setLayout(new BorderLayout());
		edit_Dialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				edit_Dialog.setVisible(false);
			}
		});
		
		menuFrame_edit = new JButton("편집 완료");
		menuFrame_edit.addActionListener(this);
		
		menuFrame_name3 = new JTextField(10);
		menuFrame_price3 = new JTextField(10);
		menuFrame_originalPrice3 = new JTextField(10);
		menuFrame_ingredients3 = new JTextArea(20,10); //added
		JScrollPane scrollPane3=new JScrollPane(menuFrame_ingredients3);
		
		editPanel = new JPanel();
		editPanel.setLayout(new GridLayout(4,2));
		
		editPanel.add(menu_nameinfo3);
		editPanel.add(menuFrame_name3);
		editPanel.add(menu_priceinfo3);
		editPanel.add(menuFrame_price3);
		editPanel.add(menu_originalPriceinfo3);
		editPanel.add(menuFrame_originalPrice3);
		editPanel.add(menu_ingredientinfo3);
		editPanel.add(scrollPane3);
		
		int row = menuTable.getSelectedRow();
		String data = (String) menuTable.getValueAt(row, 0);
		menuFrame_name3.setText(data);
		data = (String) unvisibleTable.getValueAt(row, 0);
		menuFrame_price3.setText(data);
		data = (String) unvisibleTable.getValueAt(row, 1);
		menuFrame_originalPrice3.setText(data);
		data = (String) unvisibleTable.getValueAt(row, 2);
		menuFrame_ingredients3.setText(data);
		
		edit_Dialog.add(menuFrame_edit, BorderLayout.SOUTH);
		edit_Dialog.add(editPanel);
		edit_Dialog.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		
		if(actionCommand.equals("추가")) {
			//menuTableModel.addRow(new Object[] {menu_name.getText()});
			Frame_Open();
		}
		
		else if(actionCommand.equals("내용 추가")) {
			menuTableModel.addRow(new Object[] {menuFrame_name.getText()});
			unvisibleTableModel.addRow(new Object[] {menuFrame_price.getText(), menuFrame_originalPrice.getText(), menuFrame_ingredients.getText()});
			
			sql = "INSERT INTO Menu(Fname, Fprice, Fbase) VALUES";
			sql += "(\"" + menuFrame_name.getText() + "\"," + menuFrame_price.getText() + "," + menuFrame_originalPrice.getText()
			+ ");";
			
			try {
				Class.forName(className);
				con = DriverManager.getConnection(url, user, passwd); 
				stmt = (Statement) con.createStatement();
				stmt.executeUpdate(sql);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			String ingredients = (String) menuFrame_ingredients.getText();
			String[] change_ingredients = ingredients.split("\\n");
			
			for(int i=0; i<change_ingredients.length; i++) {
				String[] change_ingredients2 = change_ingredients[i].split(" ");
				
				sql = "INSERT INTO Recipe(Food, Ing, Needs) VALUES";
				sql += "(\"" + menuFrame_name.getText() + "\"," + "\"" + change_ingredients2[0] + "\"" + "," + change_ingredients2[1] + ");";
			
				try {
					Class.forName(className);
					con = DriverManager.getConnection(url, user, passwd); 
					stmt = (Statement) con.createStatement();
					stmt.executeUpdate(sql);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			add_Dialog.setVisible(false);
		}
		
		else if(actionCommand.equals("편집")) {
			Frame_Edit();
		}
		
		else if(actionCommand.equals("삭제")) {
			int row = menuTable.getSelectedRow();
			
			String Fname = String.valueOf(menuTable.getValueAt(row, 0));
			sql = "DELETE FROM Recipe WHERE Food=\"" + Fname + "\";";
			try {
				Class.forName(className);
				con = DriverManager.getConnection(url, user, passwd);
				stmt = (Statement) con.createStatement();
				stmt.executeUpdate(sql);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			sql = "DELETE FROM Menu WHERE Fname=\"" + Fname + "\";";
			try {
				Class.forName(className);
				con = DriverManager.getConnection(url, user, passwd);
				stmt = (Statement) con.createStatement();
				stmt.executeUpdate(sql);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			menuTableModel.removeRow(row);
			unvisibleTableModel.removeRow(row);
			menuinfo_name.setText("");
			menuinfo_price.setText("");
			menuinfo_originalPrice.setText("");
			menuinfo_ingredients.setText("");
		}
		
		else if(actionCommand.equals("편집 완료")) {
			int row = menuTable.getSelectedRow();
			menuTable.setValueAt(menuFrame_name3.getText(), row, 0);
			unvisibleTable.setValueAt(menuFrame_price3.getText(), row, 0);
			unvisibleTable.setValueAt(menuFrame_originalPrice3.getText(), row, 1);
			unvisibleTable.setValueAt(menuFrame_ingredients3.getText(), row, 2);
			
			String data = (String) menuTable.getValueAt(row, 0);
			menuinfo_name.setText(data);
			data = (String) unvisibleTable.getValueAt(row, 0);
			menuinfo_price.setText(data);
			data = (String) unvisibleTable.getValueAt(row, 1);
			menuinfo_originalPrice.setText(data);
			data = (String) unvisibleTable.getValueAt(row, 2);
			System.out.println(data);
			menuinfo_ingredients.setText(data);
			
			edit_Dialog.setVisible(false);
		}
		
	}

}
