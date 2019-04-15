package ChickenStore;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ChickenStore_Delivery implements ActionListener {

	JPanel deliveryPanel;

	JPanel delivery_num1;
	JPanel delivery_num2;
	JPanel delivery_num3;
	JPanel delivery_num4;
	JPanel delivery_num5;
	JPanel delivery_num6;
	// 테이블 + 토탈 값 넣을 패널 

	JButton delivery1_btn;
	JButton delivery2_btn;
	JButton delivery3_btn;
	JButton delivery4_btn;
	JButton delivery5_btn;
	JButton delivery6_btn;
	// 다이얼로그와 연결된 테이블 버튼

	JTextField delivery1_total;
	JTextField delivery2_total;
	JTextField delivery3_total;
	JTextField delivery4_total;
	JTextField delivery5_total;
	JTextField delivery6_total;
	// 테이블 총액 띄울 텍스트 필드 

	JTextField delivery_address1 = new JTextField(10);
	JTextField delivery_address2 = new JTextField(10);
	JTextField delivery_address3 = new JTextField(10);
	JTextField delivery_address4 = new JTextField(10);
	JTextField delivery_address5 = new JTextField(10);
	JTextField delivery_address6 = new JTextField(10);
	
	Frame delivery1;
	Frame delivery2;
	Frame delivery3;
	Frame delivery4;
	Frame delivery5;
	Frame delivery6;
	// 각 테이블과 연결된 다이얼로그 프레임 

	static JTable D1;
	static JTable D2;
	static JTable D3;
	static JTable D4;
	static JTable D5;
	static JTable D6;
	// 다이얼로그에 띄울 JTable

	DefaultTableModel model_D1;
	DefaultTableModel model_D2;
	DefaultTableModel model_D3;
	DefaultTableModel model_D4;
	DefaultTableModel model_D5;
	DefaultTableModel model_D6;
	// JTable에 연결할 DefaultTableModel

	JScrollPane scrollpane;
	JScrollPane scrollpane2;
	JScrollPane scrollpane3;
	JScrollPane scrollpane4;
	JScrollPane scrollpane5;
	JScrollPane scrollpane6;
	// JTable 받을 JScrollPane

	JComboBox add_order1;
	JComboBox add_order2;
	JComboBox add_order3;
	JComboBox add_order4;
	JComboBox add_order5;
	JComboBox add_order6;

	public ChickenStore_Delivery() {

		deliveryPanel = new JPanel();
		deliveryPanel.setLayout(new GridLayout(2,3));

		delivery_num1 = new JPanel();
		delivery_num1.setLayout(new BorderLayout());	
		delivery_num1.setBackground(Color.WHITE);
		delivery_num2 = new JPanel();
		delivery_num2.setLayout(new BorderLayout());	
		delivery_num2.setBackground(Color.WHITE);
		delivery_num3 = new JPanel();
		delivery_num3.setLayout(new BorderLayout());
		delivery_num3.setBackground(Color.WHITE);
		delivery_num4 = new JPanel();
		delivery_num4.setLayout(new BorderLayout());	
		delivery_num4.setBackground(Color.WHITE);
		delivery_num5 = new JPanel();
		delivery_num5.setLayout(new BorderLayout());	
		delivery_num5.setBackground(Color.WHITE);
		delivery_num6 = new JPanel();
		delivery_num6.setLayout(new BorderLayout());
		delivery_num6.setBackground(Color.WHITE);

		delivery1_btn = new JButton("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp배달 1<br />(배달료 +3000)</html>");
		delivery2_btn = new JButton("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp배달 2<br />(배달료 +3000)</html>");
		delivery3_btn = new JButton("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp배달 3<br />(배달료 +3000)</html>");
		delivery4_btn = new JButton("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp배달 4<br />(배달료 +3000)</html>");
		delivery5_btn = new JButton("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp배달 5<br />(배달료 +3000)</html>");
		delivery6_btn = new JButton("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp배달 6<br />(배달료 +3000)</html>");

		delivery1_total = new JTextField(10);
		delivery2_total = new JTextField(10);
		delivery3_total = new JTextField(10);
		delivery4_total = new JTextField(10);
		delivery5_total = new JTextField(10);
		delivery6_total = new JTextField(10);
		
		Vector<String> userColumn1 = new Vector<String> ();
		userColumn1.addElement("이름");
		userColumn1.addElement("가격");
		model_D1 = new DefaultTableModel(userColumn1, 0);
		D1 = new JTable(model_D1);
		scrollpane = new JScrollPane(D1);

		Vector<String> userColumn2 = new Vector<String> ();
		userColumn2.addElement("이름");
		userColumn2.addElement("가격");
		model_D2 = new DefaultTableModel(userColumn2, 0);
		D2 = new JTable(model_D2);
		scrollpane2 = new JScrollPane(D2);

		Vector<String> userColumn3 = new Vector<String> ();
		userColumn3.addElement("이름");
		userColumn3.addElement("가격");
		model_D3 = new DefaultTableModel(userColumn3, 0);
		D3 = new JTable(model_D3);
		scrollpane3 = new JScrollPane(D3);

		Vector<String> userColumn4 = new Vector<String> ();
		userColumn4.addElement("이름");
		userColumn4.addElement("가격");
		model_D4 = new DefaultTableModel(userColumn4, 0);
		D4 = new JTable(model_D4);
		scrollpane4 = new JScrollPane(D4);

		Vector<String> userColumn5 = new Vector<String> ();
		userColumn5.addElement("이름");
		userColumn5.addElement("가격");
		model_D5 = new DefaultTableModel(userColumn5, 0);
		D5 = new JTable(model_D5);
		scrollpane5 = new JScrollPane(D5);

		Vector<String> userColumn6 = new Vector<String> ();
		userColumn6.addElement("이름");
		userColumn6.addElement("가격");
		model_D6 = new DefaultTableModel(userColumn6, 0);
		D6 = new JTable(model_D6);
		scrollpane6 = new JScrollPane(D6);

		delivery_num1.add(delivery1_btn);
		delivery_num1.add(delivery1_total, BorderLayout.SOUTH);
		delivery_num2.add(delivery2_btn);
		delivery_num2.add(delivery2_total, BorderLayout.SOUTH);
		delivery_num3.add(delivery3_btn);
		delivery_num3.add(delivery3_total, BorderLayout.SOUTH);
		delivery_num4.add(delivery4_btn);
		delivery_num4.add(delivery4_total, BorderLayout.SOUTH);
		delivery_num5.add(delivery5_btn);
		delivery_num5.add(delivery5_total, BorderLayout.SOUTH);
		delivery_num6.add(delivery6_btn);
		delivery_num6.add(delivery6_total, BorderLayout.SOUTH);

		delivery1_btn.addActionListener(this);
		delivery2_btn.addActionListener(this);
		delivery3_btn.addActionListener(this);
		delivery4_btn.addActionListener(this);
		delivery5_btn.addActionListener(this);
		delivery6_btn.addActionListener(this);

		deliveryPanel.add(delivery_num1);
		deliveryPanel.add(delivery_num2);
		deliveryPanel.add(delivery_num3);
		deliveryPanel.add(delivery_num4);
		deliveryPanel.add(delivery_num5);
		deliveryPanel.add(delivery_num6);

	}

	void Frame_Open1() {
		JPanel BtnPanel = new JPanel(new GridLayout(2,2));
		delivery1 = new Frame("배달 1");
		delivery1.setSize(400, 400);
		delivery1.setLayout(new BorderLayout());
		delivery1.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				delivery_num1.setBackground(Color.LIGHT_GRAY);
				delivery1.setVisible(false);
			}
		});

		add_order1 = new JComboBox(getTableData(ChickenStore_Menu.menuTable));
		
		JPanel addressPanel = new JPanel(new BorderLayout());
		JLabel address = new JLabel("주소");
		address.setHorizontalAlignment(JLabel.CENTER);
		addressPanel.add(address, BorderLayout.PAGE_START);
		addressPanel.add(delivery_address1);

		JButton delivery1_add = new JButton("배달 1 추가");
		delivery1_add.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				int x = add_order1.getSelectedIndex();
				model_D1.addRow(new Object[] {ChickenStore_Menu.menuTable.getValueAt(x, 0), ChickenStore_Menu.unvisibleTable.getValueAt(x, 0)});

				int data = 0;
				String total;
				for(int i=0; i<model_D1.getRowCount();i++) {
					data += Integer.parseInt((String)model_D1.getValueAt(i, 1));
				}
				total = String.valueOf(data);
				delivery1_total.setText(total);
			}
		});
		JButton delivery1_pay = new JButton("배달 1 결제");
		delivery1_pay.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				delivery_num1.setBackground(Color.WHITE);
				delivery1.setVisible(false);
				int data = Integer.parseInt(delivery1_total.getText());
				ChickenStore_Final.today_money += data;
				ChickenStore_Final.today_money += 3000;
				delivery1_total.setText("");
				DefaultTableModel model = (DefaultTableModel) D1.getModel();
				model.setNumRows(0);
				String str = "오늘 매출 : " + ChickenStore_Final.today_money + "     전체 잔고 : " + ChickenStore_Final.total_money;
				ChickenStore_Final.priceLabel.setText(str);
				delivery_address1.setText("");
			}

		});
		
		BtnPanel.add(add_order1);
		BtnPanel.add(addressPanel);
		BtnPanel.add(delivery1_add);
		BtnPanel.add(delivery1_pay);
		delivery1.add(BtnPanel, BorderLayout.SOUTH);
		delivery1.add(scrollpane);
		delivery1.setVisible(true);
	}

	void Frame_Open2() {
		JPanel BtnPanel = new JPanel(new GridLayout(2,2));
		delivery2 = new Frame("배달 2");
		delivery2.setSize(400, 400);
		delivery2.setLayout(new BorderLayout());
		delivery2.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				delivery_num2.setBackground(Color.LIGHT_GRAY);
				delivery2.setVisible(false);
			}
		});

		add_order2 = new JComboBox(getTableData(ChickenStore_Menu.menuTable));

		JPanel addressPanel = new JPanel(new BorderLayout());
		JLabel address = new JLabel("주소");
		address.setHorizontalAlignment(JLabel.CENTER);
		addressPanel.add(address, BorderLayout.PAGE_START);
		addressPanel.add(delivery_address2);
		
		JButton delivery2_add = new JButton("배달 2 추가");
		delivery2_add.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				int x = add_order2.getSelectedIndex();
				model_D2.addRow(new Object[] {ChickenStore_Menu.menuTable.getValueAt(x, 0), ChickenStore_Menu.unvisibleTable.getValueAt(x, 0)});

				int data = 0;
				String total;
				for(int i=0; i<model_D2.getRowCount();i++) {
					data += Integer.parseInt((String)model_D2.getValueAt(i, 1));
				}
				total = String.valueOf(data);
				delivery2_total.setText(total);
			}

		});
		JButton delivery2_pay = new JButton("배달 2 결제");
		delivery2_pay.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				delivery_num2.setBackground(Color.WHITE);
				delivery2.setVisible(false);
				int data = Integer.parseInt(delivery2_total.getText());
				ChickenStore_Final.today_money += data;
				ChickenStore_Final.today_money += 3000;
				delivery2_total.setText("");
				DefaultTableModel model = (DefaultTableModel) D2.getModel();
				model.setNumRows(0);
				String str = "오늘 매출 : " + ChickenStore_Final.today_money + "     전체 잔고 : " + ChickenStore_Final.total_money;
				ChickenStore_Final.priceLabel.setText(str);
				delivery_address2.setText("");
			}

		});

		BtnPanel.add(add_order2);
		BtnPanel.add(addressPanel);
		BtnPanel.add(delivery2_add);
		BtnPanel.add(delivery2_pay);
		delivery2.add(BtnPanel, BorderLayout.SOUTH);
		delivery2.add(scrollpane2);
		delivery2.setVisible(true);
	}

	void Frame_Open3() {
		JPanel BtnPanel = new JPanel(new GridLayout(2,2));
		delivery3 = new Frame("배달 3");
		delivery3.setSize(400, 400);
		delivery3.setLayout(new BorderLayout());
		delivery3.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				delivery_num3.setBackground(Color.LIGHT_GRAY);
				delivery3.setVisible(false);
			}
		});

		add_order3 = new JComboBox(getTableData(ChickenStore_Menu.menuTable));

		JPanel addressPanel = new JPanel(new BorderLayout());
		JLabel address = new JLabel("주소");
		address.setHorizontalAlignment(JLabel.CENTER);
		addressPanel.add(address, BorderLayout.PAGE_START);
		addressPanel.add(delivery_address3);
		
		JButton delivery3_add = new JButton("배달 3 추가");
		delivery3_add.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				int x = add_order3.getSelectedIndex();
				model_D3.addRow(new Object[] {ChickenStore_Menu.menuTable.getValueAt(x, 0), ChickenStore_Menu.unvisibleTable.getValueAt(x, 0)});

				int data = 0;
				String total;
				for(int i=0; i<model_D3.getRowCount();i++) {
					data += Integer.parseInt((String)model_D3.getValueAt(i, 1));
				}
				total = String.valueOf(data);
				delivery3_total.setText(total);
			}

		});
		JButton delivery3_pay = new JButton("배달 3 결제");
		delivery3_pay.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				delivery_num3.setBackground(Color.WHITE);
				delivery3.setVisible(false);
				int data = Integer.parseInt(delivery3_total.getText());
				ChickenStore_Final.today_money += data;
				ChickenStore_Final.today_money += 3000;
				delivery3_total.setText("");
				DefaultTableModel model = (DefaultTableModel) D3.getModel();
				model.setNumRows(0);
				String str = "오늘 매출 : " + ChickenStore_Final.today_money + "     전체 잔고 : " + ChickenStore_Final.total_money;
				ChickenStore_Final.priceLabel.setText(str);
				delivery_address3.setText("");
			}

		});

		BtnPanel.add(add_order3);
		BtnPanel.add(addressPanel);
		BtnPanel.add(delivery3_add);
		BtnPanel.add(delivery3_pay);
		delivery3.add(BtnPanel, BorderLayout.SOUTH);
		delivery3.add(scrollpane3);
		delivery3.setVisible(true);
	}

	void Frame_Open4() {
		JPanel BtnPanel = new JPanel(new GridLayout(2,2));
		delivery4 = new Frame("배달 4");
		delivery4.setSize(400, 400);
		delivery4.setLayout(new BorderLayout());
		delivery4.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				delivery_num4.setBackground(Color.LIGHT_GRAY);
				delivery4.setVisible(false);
			}
		});

		add_order4 = new JComboBox(getTableData(ChickenStore_Menu.menuTable));

		JPanel addressPanel = new JPanel(new BorderLayout());
		JLabel address = new JLabel("주소");
		address.setHorizontalAlignment(JLabel.CENTER);
		addressPanel.add(address, BorderLayout.PAGE_START);
		addressPanel.add(delivery_address4);
		
		JButton delivery4_add = new JButton("배달 4 추가");
		delivery4_add.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				int x = add_order4.getSelectedIndex();
				model_D4.addRow(new Object[] {ChickenStore_Menu.menuTable.getValueAt(x, 0), ChickenStore_Menu.unvisibleTable.getValueAt(x, 0)});

				int data = 0;
				String total;
				for(int i=0; i<model_D4.getRowCount();i++) {
					data += Integer.parseInt((String)model_D4.getValueAt(i, 1));
				}
				total = String.valueOf(data);
				delivery4_total.setText(total);
			}

		});
		JButton delivery4_pay = new JButton("배달 4 결제");
		delivery4_pay.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				delivery_num4.setBackground(Color.WHITE);
				delivery4.setVisible(false);
				int data = Integer.parseInt(delivery4_total.getText());
				ChickenStore_Final.today_money += data;
				ChickenStore_Final.today_money += 3000;
				delivery4_total.setText("");
				DefaultTableModel model = (DefaultTableModel) D4.getModel();
				model.setNumRows(0);
				String str = "오늘 매출 : " + ChickenStore_Final.today_money + "     전체 잔고 : " + ChickenStore_Final.total_money;
				ChickenStore_Final.priceLabel.setText(str);
				delivery_address4.setText("");
			}

		});

		BtnPanel.add(add_order4);
		BtnPanel.add(addressPanel);
		BtnPanel.add(delivery4_add);
		BtnPanel.add(delivery4_pay);
		delivery4.add(BtnPanel, BorderLayout.SOUTH);
		delivery4.add(scrollpane4);
		delivery4.setVisible(true);
	}

	void Frame_Open5() {
		JPanel BtnPanel = new JPanel(new GridLayout(2,2));
		delivery5 = new Frame("배달 5");
		delivery5.setSize(400, 400);
		delivery5.setLayout(new BorderLayout());
		delivery5.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				delivery_num5.setBackground(Color.LIGHT_GRAY);
				delivery5.setVisible(false);
			}
		});

		add_order5 = new JComboBox(getTableData(ChickenStore_Menu.menuTable));

		JPanel addressPanel = new JPanel(new BorderLayout());
		JLabel address = new JLabel("주소");
		address.setHorizontalAlignment(JLabel.CENTER);
		addressPanel.add(address, BorderLayout.PAGE_START);
		addressPanel.add(delivery_address5);
		
		JButton delivery5_add = new JButton("배달 5 추가");
		delivery5_add.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				int x = add_order5.getSelectedIndex();
				model_D5.addRow(new Object[] {ChickenStore_Menu.menuTable.getValueAt(x, 0), ChickenStore_Menu.unvisibleTable.getValueAt(x, 0)});

				int data = 0;
				String total;
				for(int i=0; i<model_D5.getRowCount();i++) {
					data += Integer.parseInt((String)model_D5.getValueAt(i, 1));
				}
				total = String.valueOf(data);
				delivery5_total.setText(total);
			}

		});
		JButton delivery5_pay = new JButton("배달 5 결제");
		delivery5_pay.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				delivery_num5.setBackground(Color.WHITE);
				delivery5.setVisible(false);
				int data = Integer.parseInt(delivery5_total.getText());
				ChickenStore_Final.today_money += data;
				ChickenStore_Final.today_money += 3000;
				delivery5_total.setText("");
				DefaultTableModel model = (DefaultTableModel) D5.getModel();
				model.setNumRows(0);
				String str = "오늘 매출 : " + ChickenStore_Final.today_money + "     전체 잔고 : " + ChickenStore_Final.total_money;
				ChickenStore_Final.priceLabel.setText(str);
				delivery_address5.setText("");
			}

		});

		BtnPanel.add(add_order5);
		BtnPanel.add(addressPanel);
		BtnPanel.add(delivery5_add);
		BtnPanel.add(delivery5_pay);
		delivery5.add(BtnPanel, BorderLayout.SOUTH);
		delivery5.add(scrollpane5);
		delivery5.setVisible(true);
	}

	void Frame_Open6() {
		JPanel BtnPanel = new JPanel(new GridLayout(2,2));
		delivery6 = new Frame("배달 6");
		delivery6.setSize(400, 400);
		delivery6.setLayout(new BorderLayout());
		delivery6.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				delivery_num6.setBackground(Color.LIGHT_GRAY);
				delivery6.setVisible(false);
			}
		});

		add_order6 = new JComboBox(getTableData(ChickenStore_Menu.menuTable));

		JPanel addressPanel = new JPanel(new BorderLayout());
		JLabel address = new JLabel("주소");
		address.setHorizontalAlignment(JLabel.CENTER);
		addressPanel.add(address, BorderLayout.PAGE_START);
		addressPanel.add(delivery_address6);
		
		JButton delivery6_add = new JButton("배달 6 추가");
		delivery6_add.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				int x = add_order6.getSelectedIndex();
				model_D6.addRow(new Object[] {ChickenStore_Menu.menuTable.getValueAt(x, 0), ChickenStore_Menu.unvisibleTable.getValueAt(x, 0)});

				int data = 0;
				String total;
				for(int i=0; i<model_D6.getRowCount();i++) {
					data += Integer.parseInt((String)model_D6.getValueAt(i, 1));
				}
				total = String.valueOf(data);
				delivery6_total.setText(total);
			}

		});
		JButton delivery6_pay = new JButton("배달 6 결제");
		delivery6_pay.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				delivery_num6.setBackground(Color.WHITE);
				delivery6.setVisible(false);
				int data = Integer.parseInt(delivery6_total.getText());
				ChickenStore_Final.today_money += data;
				ChickenStore_Final.today_money += 3000;
				delivery6_total.setText("");
				DefaultTableModel model = (DefaultTableModel) D6.getModel();
				model.setNumRows(0);
				String str = "오늘 매출 : " + ChickenStore_Final.today_money + "     전체 잔고 : " + ChickenStore_Final.total_money;
				ChickenStore_Final.priceLabel.setText(str);
				delivery_address6.setText("");
			}

		});

		BtnPanel.add(add_order6);
		BtnPanel.add(addressPanel);
		BtnPanel.add(delivery6_add);
		BtnPanel.add(delivery6_pay);
		delivery6.add(BtnPanel, BorderLayout.SOUTH);
		delivery6.add(scrollpane6);
		delivery6.setVisible(true);
	}

	public Object[] getTableData(JTable table) { 

		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		int nRow = dtm.getRowCount();
		Object[] tableData = new Object[nRow];

		for (int i = 0; i < nRow; i++)
			tableData[i] = dtm.getValueAt(i,0);

		return tableData;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();

		if(actionCommand.equals("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp배달 1<br />(배달료 +3000)</html>")) {
			Frame_Open1();
			delivery_num1.setBackground(Color.DARK_GRAY);
		}

		else if(actionCommand.equals("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp배달 2<br />(배달료 +3000)</html>")) {
			Frame_Open2();
			delivery_num2.setBackground(Color.DARK_GRAY);
		}

		else if(actionCommand.equals("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp배달 3<br />(배달료 +3000)</html>")) {
			Frame_Open3();
			delivery_num3.setBackground(Color.DARK_GRAY);
		}

		else if(actionCommand.equals("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp배달 4<br />(배달료 +3000)</html>")) {
			Frame_Open4();
			delivery_num4.setBackground(Color.DARK_GRAY);
		}

		else if(actionCommand.equals("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp배달 5<br />(배달료 +3000)</html>")) {
			Frame_Open5();
			delivery_num5.setBackground(Color.DARK_GRAY);
		}

		else if(actionCommand.equals("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp배달 6<br />(배달료 +3000)</html>")) {
			Frame_Open6();
			delivery_num6.setBackground(Color.DARK_GRAY);
		}


	}
}