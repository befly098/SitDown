package ChickenStore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ChickenStore_Storage implements ActionListener{

	JPanel storagePanel;
	
	static JTable storageTable;
	static JTable unvisibleTable;
	
	JPanel storageBtnPanel;
	JPanel storageInfoPanel;
	JPanel storageTablePanel;
	JPanel dialogPanel;
	
	JTextField storage_name;
	JTextField storage_price;
	JTextField storage_selling;
	JTextField storage_phone;
	JTextField storage_quantity;
	JTextField storage_orderquantity;
	
	JTextField storage_Tablename;
	JTextField storage_Tableprice;
	JTextField storage_Tableselling;
	JTextField storage_Tablephone;
	JTextField storage_Tablequantity;
	JTextField storage_Tableorderquantity;
	
	JButton storage_add;
	JButton storage_delete;
	JButton storage_order;
	JButton storage_cancel;
	JButton storage_info;
	JButton storageFrame_add;
	
	JLabel name_storage;
	JLabel name_price;
	JLabel name_selling;
	JLabel name_phone;
	JLabel name_quantity;
	JLabel name_orderquantity;
	
	JLabel frame_storage;
	JLabel frame_price;
	JLabel frame_selling;
	JLabel frame_phone;
	JLabel frame_quantity;
	JLabel frame_orderquantity;
	
	int orderquantity; // 주문량 담을 변수 
	Frame order_Dialog; // 주문량 적을 다이얼로그  
	
	static DefaultTableModel storageTableModel;
	static DefaultTableModel unvisibleTableModel;
	
	Frame add_Dialog;
	
	public ChickenStore_Storage() {
		
		storagePanel = new JPanel();
		storagePanel.setLayout(new BorderLayout());
		storageTablePanel = new JPanel();
		storageTablePanel.setLayout(new BorderLayout());
		storageBtnPanel = new JPanel();
		storageBtnPanel.setLayout(new GridLayout(1,3));
		storageInfoPanel = new JPanel();
		storageInfoPanel.setLayout(new GridLayout(7,2));
		
		
		// 데이터 다 입력받아서 출력 테이블에 보여주고 싶지 않은 부분은 안 보이는 테이블에 따로 저장 
		
		try {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("StorageTable"));
			ObjectInputStream inputStream2 = new ObjectInputStream(new FileInputStream("unvisibleTable"));
			
			Vector rowData = (Vector)inputStream.readObject();
			Vector columnNames = (Vector)inputStream.readObject();
			
			if(rowData.isEmpty()) {
				Vector<String> userColumn = new Vector<String> ();
				userColumn.addElement("이름");
				userColumn.addElement("재고");
				userColumn.addElement("주문");
				userColumn.addElement("가격");
				storageTableModel = new DefaultTableModel(userColumn, 0);
			}
			else {
				storageTableModel = new DefaultTableModel();
				storageTableModel.setDataVector(rowData, columnNames);
			}
			
			Vector rowData2 = (Vector)inputStream2.readObject();
			Vector columnNames2 = (Vector)inputStream2.readObject();
			
			if(rowData2.isEmpty()) {
				Vector<String> unvisible_userColumn = new Vector<String> ();
				unvisible_userColumn.addElement("판매처");
				unvisible_userColumn.addElement("연락처");
				unvisibleTableModel = new DefaultTableModel(unvisible_userColumn, 0);
			}
			else {
				unvisibleTableModel = new DefaultTableModel();
				unvisibleTableModel.setDataVector(rowData2, columnNames2);
			}
			
			inputStream.close();
			inputStream2.close();
			
		} catch(FileNotFoundException e) {
			Vector<String> userColumn = new 
					Vector<String> ();
			userColumn.addElement("이름");
			userColumn.addElement("재고");
			userColumn.addElement("주문");
			userColumn.addElement("가격");
			storageTableModel = new DefaultTableModel(userColumn, 0);
			
			Vector<String> unvisible_userColumn = new Vector<String> ();
			unvisible_userColumn.addElement("판매처");
			unvisible_userColumn.addElement("연락처");
			unvisibleTableModel = new DefaultTableModel(unvisible_userColumn, 0);
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			Vector<String> userColumn = new 
					Vector<String> ();
			userColumn.addElement("이름");
			userColumn.addElement("재고");
			userColumn.addElement("주문");
			userColumn.addElement("가격");
			storageTableModel = new DefaultTableModel(userColumn, 0);
			
			Vector<String> unvisible_userColumn = new Vector<String> ();
			unvisible_userColumn.addElement("판매처");
			unvisible_userColumn.addElement("연락처");
			unvisibleTableModel = new DefaultTableModel(unvisible_userColumn, 0);
			
			e.printStackTrace();
		} catch (IOException e) {
			Vector<String> userColumn = new 
					Vector<String> ();
			userColumn.addElement("이름");
			userColumn.addElement("재고");
			userColumn.addElement("주문");
			userColumn.addElement("가격");
			storageTableModel = new DefaultTableModel(userColumn, 0);
			
			Vector<String> unvisible_userColumn = new Vector<String> ();
			unvisible_userColumn.addElement("판매처");
			unvisible_userColumn.addElement("연락처");
			unvisibleTableModel = new DefaultTableModel(unvisible_userColumn, 0);
			
			e.printStackTrace();
		}
		
		storageTable = new JTable(storageTableModel);
		JScrollPane scrollpane = new JScrollPane(storageTable);
		storageTablePanel.add(scrollpane, BorderLayout.CENTER);
		unvisibleTable = new JTable(unvisibleTableModel);
		
		storage_name = new JTextField(10);
		storage_price = new JTextField(10);
		storage_selling = new JTextField(10);
		storage_phone = new JTextField(10);
		storage_quantity = new JTextField(10);
		storage_orderquantity = new JTextField(10);
		
		name_storage = new JLabel("이름");
		name_storage.setHorizontalAlignment(JLabel.CENTER);
		name_price = new JLabel("가격");
		name_price.setHorizontalAlignment(JLabel.CENTER);
		name_selling = new JLabel("판매처");
		name_selling.setHorizontalAlignment(JLabel.CENTER);
		name_phone = new JLabel("연락처");
		name_phone.setHorizontalAlignment(JLabel.CENTER);
		name_quantity = new JLabel("수량");
		name_quantity.setHorizontalAlignment(JLabel.CENTER);
		name_orderquantity = new JLabel("주문량");
		name_orderquantity.setHorizontalAlignment(JLabel.CENTER);
		
		storage_add = new JButton("추가");
		storage_add.addActionListener(this);
		storage_delete = new JButton("삭제");
		storage_delete.addActionListener(this);
		storage_order = new JButton("주문");
		storage_order.addActionListener(this);
		storage_cancel = new JButton("주문 취소");
		storage_cancel.addActionListener(this);
		storage_info = new JButton("상세정보");
		storage_info.addActionListener(this);
		
		storageBtnPanel.add(storage_add);
		storageBtnPanel.add(storage_delete);
		storageBtnPanel.add(storage_info);
		
		storageTablePanel.add(storageBtnPanel, BorderLayout.SOUTH);
		
		
		storageInfoPanel.add(name_storage);
		storageInfoPanel.add(storage_name);
		
		storageInfoPanel.add(name_price);
		storageInfoPanel.add(storage_price);
		
		storageInfoPanel.add(name_selling);
		storageInfoPanel.add(storage_selling);
		
		storageInfoPanel.add(name_phone);
		storageInfoPanel.add(storage_phone);
		
		storageInfoPanel.add(name_quantity);
		storageInfoPanel.add(storage_quantity);
		
		storageInfoPanel.add(name_orderquantity);
		storageInfoPanel.add(storage_orderquantity);
		
		storageInfoPanel.add(storage_order);
		storageInfoPanel.add(storage_cancel);
	
		storagePanel.add(storageInfoPanel, BorderLayout.EAST);
		storagePanel.add(storageTablePanel);
	}
	
	void Frame_OrderBox() {
		order_Dialog = new Frame("주문 수량 입력");
		order_Dialog.setSize(300, 300);
		order_Dialog.setLayout(new GridLayout(3,1));
		order_Dialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				order_Dialog.setVisible(false);
			}
		});
		
		JTextField order_input = new JTextField(15); // 주문량 적을 텍스트 필드 
		JLabel order = new JLabel("주문할 수량을 입력하세요."); // 설명 넣을 라벨
		order.setHorizontalAlignment(JLabel.CENTER);
		
		JButton orderFrame_add = new JButton("주문 완료");
		orderFrame_add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int x = storageTable.getSelectedRow();
				storageTable.setValueAt(Integer.parseInt(order_input.getText()), x, 2);
				storage_orderquantity.setText(order_input.getText());
				order_Dialog.setVisible(false);
				order_input.setText("");
			}
			
		});
		
		order_Dialog.add(order);
		order_Dialog.add(order_input);
		order_Dialog.add(orderFrame_add);
		order_Dialog.setVisible(true);
	}
	void Frame_Open() { // 추가 버튼 누를때 작동할 입력하는 다이얼로그 프레임 오픈 
		
		add_Dialog = new Frame("재료 정보 입력");
		add_Dialog.setSize(300, 300);
		add_Dialog.setLayout(new BorderLayout());
		add_Dialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				add_Dialog.setVisible(false);
			}
		});
		
		storageFrame_add = new JButton("내용 추가");
		storageFrame_add.addActionListener(this);
		
		storage_Tablename = new JTextField(10);
		storage_Tableprice = new JTextField(10);
		//storage_Tablequantity = new JTextField(10);
		storage_Tableorderquantity = new JTextField(10);
		storage_Tableselling = new JTextField(10);
		storage_Tablephone = new JTextField(10);
		
		frame_storage = new JLabel("이름");
		frame_storage.setHorizontalAlignment(JLabel.CENTER);
		frame_orderquantity = new JLabel("재고");
		frame_orderquantity.setHorizontalAlignment(JLabel.CENTER);
//		frame_quantity = new JLabel("주문");
//		frame_quantity.setHorizontalAlignment(JLabel.CENTER);
		frame_price = new JLabel("가격");
		frame_price.setHorizontalAlignment(JLabel.CENTER);
		frame_selling = new JLabel("판매처");
		frame_selling.setHorizontalAlignment(JLabel.CENTER);
		frame_phone = new JLabel("연락처");
		frame_phone.setHorizontalAlignment(JLabel.CENTER);
		
		dialogPanel = new JPanel();
		dialogPanel.setLayout(new GridLayout(5,2));
		
		dialogPanel.add(frame_storage);
		dialogPanel.add(storage_Tablename);
		//dialogPanel.add(frame_quantity);
		//dialogPanel.add(storage_Tablequantity);
		dialogPanel.add(frame_orderquantity );
		dialogPanel.add(storage_Tableorderquantity);
		dialogPanel.add(frame_price);
		dialogPanel.add(storage_Tableprice);
		dialogPanel.add(frame_selling);
		dialogPanel.add(storage_Tableselling);
		dialogPanel.add(frame_phone);
		dialogPanel.add(storage_Tablephone);
		
		add_Dialog.add(storageFrame_add, BorderLayout.SOUTH);
		add_Dialog.add(dialogPanel);
		add_Dialog.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		
		if(actionCommand.equals("추가")) {
			Frame_Open();
		}
		
		else if(actionCommand.equals("내용 추가")) { // 다이얼로그에 정보 입력 하고 테이블에 저장
			storageTableModel.addRow(new Object[] {storage_Tablename.getText(), storage_Tableorderquantity.getText(), 0, storage_Tableprice.getText()});
			unvisibleTableModel.addRow(new Object[] {storage_Tableselling.getText(), storage_Tablephone.getText()});
			add_Dialog.setVisible(false);
		}
		
		else if(actionCommand.equals("상세정보")) {  // 상세정보 눌렀을 때 텍스트필드에 선택한 행의 정보 가져와서 띄운다 
			int row = storageTable.getSelectedRow();
			String data = (String) storageTable.getValueAt(row, 0);
			storage_name.setText(data);
			data = (String) storageTable.getValueAt(row, 1);
			storage_quantity.setText(data);
			data = String.valueOf(storageTable.getValueAt(row, 2));
			storage_orderquantity.setText(data);
			data = (String) storageTable.getValueAt(row, 3);
			storage_price.setText(data);
			data = (String) unvisibleTable.getValueAt(row, 0);
			storage_selling.setText(data);
			data = (String) unvisibleTable.getValueAt(row, 1);
			storage_phone.setText(data);
		}
		
		else if(actionCommand.equals("삭제")) {
			int row = storageTable.getSelectedRow();
			storageTableModel.removeRow(row);
			unvisibleTableModel.removeRow(row);
			storage_name.setText("");
			storage_quantity.setText("");
			storage_orderquantity.setText("");
			storage_price.setText("");
			storage_selling.setText("");
			storage_phone.setText("");
		}
		
		else if(actionCommand.equals("주문")) {
			Frame_OrderBox();
		}
		
		else if(actionCommand.equals("주문 취소")) {
			//orderquantity = 0;
			int x = storageTable.getSelectedRow();
			storageTable.setValueAt(0, x, 2);
			storage_orderquantity.setText("0");
		}
	}
}
