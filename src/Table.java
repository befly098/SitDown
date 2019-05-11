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

public class Table implements ActionListener {

	JPanel tablePanel;

	JPanel table_num1;
	JPanel table_num2;
	JPanel table_num3;
	JPanel table_num4;
	JPanel table_num5;
	JPanel table_num6;
	// 테이블 + 토탈 값 넣을 패널 

	JButton table1_btn;
	JButton table2_btn;
	JButton table3_btn;
	JButton table4_btn;
	JButton table5_btn;
	JButton table6_btn;
	// 다이얼로그와 연결된 테이블 버튼

	JTextField table1_total;
	JTextField table2_total;
	JTextField table3_total;
	JTextField table4_total;
	JTextField table5_total;
	JTextField table6_total;
	// 테이블 총액 띄울 텍스트 필드 

	Frame table1;
	Frame table2;
	Frame table3;
	Frame table4;
	Frame table5;
	Frame table6;
	// 각 테이블과 연결된 다이얼로그 프레임 
	Frame cash;

	static JTable T1;
	static JTable T2;
	static JTable T3;
	static JTable T4;
	static JTable T5;
	static JTable T6;
	// 다이얼로그에 띄울 JTable
	static JTable MEM;

	DefaultTableModel model_T1;
	DefaultTableModel model_T2;
	DefaultTableModel model_T3;
	DefaultTableModel model_T4;
	DefaultTableModel model_T5;
	DefaultTableModel model_T6;
	// JTable에 연결할 DefaultTableModel
	DefaultTableModel model_mem;

	JScrollPane scrollpane;
	JScrollPane scrollpane2;
	JScrollPane scrollpane3;
	JScrollPane scrollpane4;
	JScrollPane scrollpane5;
	JScrollPane scrollpane6;
	// JTable 받을 JScrollPane
	JScrollPane scrollpane_mem;

	JComboBox add_order1;
	JComboBox add_order2;
	JComboBox add_order3;
	JComboBox add_order4;
	JComboBox add_order5;
	JComboBox add_order6;
	// 메뉴 선택 박스
	
	int [] count1 = new int [100];
	int [] count2 = new int [100];
	int [] count3 = new int [100];
	int [] count4 = new int [100];
	int [] count5 = new int [100];
	int [] count6 = new int [100];
	
	public Table() {
		tablePanel = new JPanel();
		tablePanel.setLayout(new GridLayout(2,3));

		table_num1 = new JPanel();
		table_num1.setLayout(new BorderLayout());	
		table_num1.setBackground(Color.WHITE);
		table_num2 = new JPanel();
		table_num2.setLayout(new BorderLayout());	
		table_num2.setBackground(Color.WHITE);
		table_num3 = new JPanel();
		table_num3.setLayout(new BorderLayout());
		table_num3.setBackground(Color.WHITE);
		table_num4 = new JPanel();
		table_num4.setLayout(new BorderLayout());	
		table_num4.setBackground(Color.WHITE);
		table_num5 = new JPanel();
		table_num5.setLayout(new BorderLayout());	
		table_num5.setBackground(Color.WHITE);
		table_num6 = new JPanel();
		table_num6.setLayout(new BorderLayout());
		table_num6.setBackground(Color.WHITE);

		table1_btn = new JButton("테이블 1");
		table2_btn = new JButton("테이블 2");
		table3_btn = new JButton("테이블 3");
		table4_btn = new JButton("테이블 4");
		table5_btn = new JButton("테이블 5");
		table6_btn = new JButton("테이블 6");

		table1_total = new JTextField(10);
		table2_total = new JTextField(10);
		table3_total = new JTextField(10);
		table4_total = new JTextField(10);
		table5_total = new JTextField(10);
		table6_total = new JTextField(10);

		Vector<String> userColumn1 = new Vector<String> ();
		userColumn1.addElement("이름");
		userColumn1.addElement("가격");
		userColumn1.addElement("수량");
		model_T1 = new DefaultTableModel(userColumn1, 0);
		T1 = new JTable(model_T1);
		scrollpane = new JScrollPane(T1);

		Vector<String> userColumn2 = new Vector<String> ();
		userColumn2.addElement("이름");
		userColumn2.addElement("가격");
		userColumn2.addElement("수량");
		model_T2 = new DefaultTableModel(userColumn2, 0);
		T2 = new JTable(model_T2);
		scrollpane2 = new JScrollPane(T2);

		Vector<String> userColumn3 = new Vector<String> ();
		userColumn3.addElement("이름");
		userColumn3.addElement("가격");
		userColumn3.addElement("수량");
		model_T3 = new DefaultTableModel(userColumn3, 0);
		T3 = new JTable(model_T3);
		scrollpane3 = new JScrollPane(T3);

		Vector<String> userColumn4 = new Vector<String> ();
		userColumn4.addElement("이름");
		userColumn4.addElement("가격");
		userColumn4.addElement("수량");
		model_T4 = new DefaultTableModel(userColumn4, 0);
		T4 = new JTable(model_T4);
		scrollpane4 = new JScrollPane(T4);

		Vector<String> userColumn5 = new Vector<String> ();
		userColumn5.addElement("이름");
		userColumn5.addElement("가격");
		userColumn5.addElement("수량");
		model_T5 = new DefaultTableModel(userColumn5, 0);
		T5 = new JTable(model_T5);
		scrollpane5 = new JScrollPane(T5);

		Vector<String> userColumn6 = new Vector<String> ();
		userColumn6.addElement("이름");
		userColumn6.addElement("가격");
		userColumn6.addElement("수량");
		model_T6 = new DefaultTableModel(userColumn6, 0);
		T6 = new JTable(model_T6);
		scrollpane6 = new JScrollPane(T6);
		
		Vector<String> userColumn_mem = new Vector<String> ();
		userColumn_mem.addElement("이름");
		userColumn_mem.addElement("연락처");
		userColumn_mem.addElement("마일리지");
		userColumn_mem.addElement("등급");
		model_mem = new DefaultTableModel(userColumn_mem, 0);
		MEM = new JTable(model_mem);
		scrollpane_mem = new JScrollPane(MEM);

		table_num1.add(table1_btn);
		table_num1.add(table1_total, BorderLayout.SOUTH);
		table_num2.add(table2_btn);
		table_num2.add(table2_total, BorderLayout.SOUTH);
		table_num3.add(table3_btn);
		table_num3.add(table3_total, BorderLayout.SOUTH);
		table_num4.add(table4_btn);
		table_num4.add(table4_total, BorderLayout.SOUTH);
		table_num5.add(table5_btn);
		table_num5.add(table5_total, BorderLayout.SOUTH);
		table_num6.add(table6_btn);
		table_num6.add(table6_total, BorderLayout.SOUTH);

		table1_btn.addActionListener(this);
		table2_btn.addActionListener(this);
		table3_btn.addActionListener(this);
		table4_btn.addActionListener(this);
		table5_btn.addActionListener(this);
		table6_btn.addActionListener(this);

		tablePanel.add(table_num1);
		tablePanel.add(table_num2);
		tablePanel.add(table_num3);
		tablePanel.add(table_num4);
		tablePanel.add(table_num5);
		tablePanel.add(table_num6);
		
	}
	
	void Payment(int data)
	{
		cash = new Frame("결제창");
		cash.setSize(450, 500);
		cash.setLayout(new BorderLayout());
		cash.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				cash.setBackground(Color.LIGHT_GRAY);
				cash.setVisible(false);
			}
		});
		
		JPanel p = new JPanel();
		p.add(new JLabel("번호 뒷자리"));
		JTextField textfield = new JTextField("4자리", 10);
		p.add(textfield);
		JButton search = new JButton("검색");
		p.add(search);
		search.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				String num = new String();
				num = textfield.getText();
				int row = Member.memberTable.getRowCount();
				String[] val = new String[row];
				for (int i = 0; i < val.length; i++)
				{
					val[i] = (String)Member.memberTable.getValueAt(i,2);
					val[i] = val[i].substring(val[i].length()-4, val[i].length());
					if(num.equals(val[i]))
					{
						model_mem.addRow(new Object[] {Member.memberTable.getValueAt(i, 1), Member.memberTable.getValueAt(i, 2), Member.memberTable.getValueAt(i, 3), Member.memberTable.getValueAt(i, 4)});
					}
				}
			}
		});
		p.add(scrollpane_mem, BorderLayout.CENTER);
		
		JPanel paypanel = new JPanel();
		JButton cash_btn = new JButton("현금결제");
		paypanel.add(cash_btn);
		cash_btn.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(MEM.getSelectedRow() == -1)
					Final.today_money += data;
				else
				{
					Object name = new Object();
					name = model_mem.getValueAt(MEM.getSelectedRow(),0);
					int row = Member.memberTable.getRowCount();
					for (int i = 0; i < row; i++)
					{
						if(name.equals(Member.memberTable.getValueAt(i, 1)))
						{
							String rating = (String)Member.memberTable.getValueAt(i, 4);

							if (rating.equals("일반")) {
								int mileage = 0;
								try {
									mileage = (int)Member.memberTable.getValueAt(i, 3);
								}
								catch(ClassCastException e1) {
									mileage = Integer.parseInt((String)Member.memberTable.getValueAt(i, 3));
								}
								mileage += data*0.02;
								Member.memberTable.setValueAt(mileage, i, 3);
								Final.today_money += data * 0.98;
							}
							else if(rating.compareTo("골드") == 0) {
								int mileage = 0;
								try {
									mileage = (int)Member.memberTable.getValueAt(i, 3);
								}
								catch(ClassCastException e1) {
									mileage = Integer.parseInt((String)Member.memberTable.getValueAt(i, 3));
								}
								mileage += data*0.02;
								Member.memberTable.setValueAt(mileage, i, 3);
								Final.today_money += data * 0.95;
							}
							else if(rating.equals("플래티넘")){
								int mileage = 0;
								try {
									mileage = (int)Member.memberTable.getValueAt(i, 3);
								}
								catch(ClassCastException e1) {
									mileage = Integer.parseInt((String)Member.memberTable.getValueAt(i, 3));
								}
								mileage += data*0.02;
								Member.memberTable.setValueAt(mileage, i, 3);
								Final.today_money += data * 0.9;
							}
							
							int mileage = (int) Member.memberTable.getValueAt(i, 3);
							if (mileage >= 500 && mileage < 1000)
								Member.memberTable.setValueAt("골드", i, 4);
							else if(mileage > 1000) {
								Member.memberTable.setValueAt("플래티넘",i, 4);
							}
							else {
								Member.memberTable.setValueAt("일반", i, 4);
							}
							break;
						}
					}
				}
				String str = "오늘 매출 : " + Final.today_money + "     전체 잔고 : " + Final.total_money;
				Final.priceLabel.setText(str);
				
				for(int i=0; i<model_mem.getRowCount();i++) {
					model_mem.removeRow(0);
				}
				
				cash.setVisible(false);
			}
		});
		
		JButton card_btn = new JButton("카드결제");
		paypanel.add(card_btn);
		card_btn.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(MEM.getSelectedRow() == -1)
					Final.today_money += data;
				else
				{
					Object name = new Object();
					name = model_mem.getValueAt(MEM.getSelectedRow(),0);
					int row = Member.memberTable.getRowCount();
					for (int i = 0; i < row; i++)
					{
						if(name.equals(Member.memberTable.getValueAt(i, 1)))
						{
							String rating = (String)Member.memberTable.getValueAt(i, 4);

							if (rating.equals("일반")) {
								int mileage = 0;
								try {
									mileage = (int)Member.memberTable.getValueAt(i, 3);
								}
								catch(ClassCastException e1) {
									mileage = Integer.parseInt((String)Member.memberTable.getValueAt(i, 3));
								}
								mileage += data*0.02;
								Member.memberTable.setValueAt(mileage, i, 3);
								Final.today_money += data * 0.98;
							}
							else if(rating.compareTo("골드") == 0) {
								int mileage = 0;
								try {
									mileage = (int)Member.memberTable.getValueAt(i, 3);
								}
								catch(ClassCastException e1) {
									mileage = Integer.parseInt((String)Member.memberTable.getValueAt(i, 3));
								}
								mileage += data*0.02;
								Member.memberTable.setValueAt(mileage, i, 3);
								Final.today_money += data * 0.95;
							}
							else if(rating.equals("플래티넘")){
								int mileage = 0;
								try {
									mileage = (int)Member.memberTable.getValueAt(i, 3);
								}
								catch(ClassCastException e1) {
									mileage = Integer.parseInt((String)Member.memberTable.getValueAt(i, 3));
								}
								mileage += data*0.02;
								Member.memberTable.setValueAt(mileage, i, 3);
								Final.today_money += data * 0.9;
							}
							
							int mileage = (int) Member.memberTable.getValueAt(i, 3);
							if (mileage >= 500 && mileage < 1000)
								Member.memberTable.setValueAt("골드", i, 4);
							else if(mileage > 1000) {
								Member.memberTable.setValueAt("플래티넘",i, 4);
							}
							else {
								Member.memberTable.setValueAt("일반", i, 4);
							}
							break;
						}
					}
				}
				String str = "오늘 매출 : " + Final.today_money + "     전체 잔고 : " + Final.total_money;
				Final.priceLabel.setText(str);
				
				for(int i=0; i<model_mem.getRowCount();i++) {
					model_mem.removeRow(0);
				}
				
				cash.setVisible(false);
			}
		});
		
		cash.add(p, BorderLayout.NORTH);
		cash.add(paypanel,BorderLayout.SOUTH);
		cash.setVisible(true);
	}

	void Frame_Open1() {
		
		JPanel BtnPanel = new JPanel(new GridLayout(1,3));
		table1 = new Frame("테이블 1");
		table1.setSize(400, 400);
		table1.setLayout(new BorderLayout());
		table1.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				table_num1.setBackground(Color.LIGHT_GRAY);
				table1.setVisible(false);
			}
		});

		add_order1 = new JComboBox(getTableData(Menu.menuTable));

		JButton table1_add = new JButton("테이블 1 추가");
		table1_add.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				int x = add_order1.getSelectedIndex();
				count1[x] += 1;
				if(count1[x] == 1)
				model_T1.addRow(new Object[] {Menu.menuTable.getValueAt(x, 0), Menu.unvisibleTable.getValueAt(x, 0), count1[x]});
				else
				{
					for(int j=0;j<model_T1.getRowCount();j++)
					{
						if((model_T1.getValueAt(j,0)).equals(Menu.menuTable.getValueAt(x, 0)))
								model_T1.setValueAt(count1[x],j,2);
					}
				}
				int data = 0;
				String total;
				for(int i=0; i<model_T1.getRowCount();i++) {
					data += (Integer.parseInt((String)model_T1.getValueAt(i, 1)) * Integer.parseInt(model_T1.getValueAt(i,2).toString()));
				}
				total = String.valueOf(data);
				table1_total.setText(total);
			}
		});
		
		JButton table1_pay = new JButton("테이블 1 결제");
		table1_pay.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				table_num1.setBackground(Color.WHITE);
				table1.setVisible(false);
				int data = Integer.parseInt(table1_total.getText());
				Payment(data);
				table1_total.setText("");
				
				for(int i=0; i<=model_T1.getRowCount();i++) {
					model_T1.removeRow(0);
				}
				for(int i=0; i<100;i++) {
					count1[i]=0;
				}
			}

		});

		BtnPanel.add(add_order1);
		BtnPanel.add(table1_add);
		BtnPanel.add(table1_pay);
		table1.add(BtnPanel, BorderLayout.SOUTH);
		table1.add(scrollpane);
		table1.setVisible(true);
	}

	void Frame_Open2() {
		JPanel BtnPanel = new JPanel(new GridLayout(1,3));
		table2 = new Frame("테이블 2");
		table2.setSize(400, 400);
		table2.setLayout(new BorderLayout());
		table2.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				table_num2.setBackground(Color.LIGHT_GRAY);
				table2.setVisible(false);
			}
		});

		add_order2 = new JComboBox(getTableData(Menu.menuTable));

		JButton table2_add = new JButton("테이블 2 추가");
		table2_add.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				int x = add_order2.getSelectedIndex();
				count2[x] += 1;
				if(count2[x] == 1)
				model_T2.addRow(new Object[] {Menu.menuTable.getValueAt(x, 0), Menu.unvisibleTable.getValueAt(x, 0), count2[x]});
				else 
				{
					for(int j=0;j<model_T2.getRowCount();j++)
					{
						if((model_T2.getValueAt(j,0)).equals(Menu.menuTable.getValueAt(x, 0)))
								model_T2.setValueAt(count2[x],j,2);
					}
				}
				int data = 0;
				String total;
				for(int i=0; i<model_T2.getRowCount();i++) {
					data += (Integer.parseInt((String)model_T2.getValueAt(i, 1)) * Integer.parseInt(model_T2.getValueAt(i,2).toString()));
				}
				total = String.valueOf(data);
				table2_total.setText(total);
			}

		});
		JButton table2_pay = new JButton("테이블 2 결제");
		table2_pay.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				table_num2.setBackground(Color.WHITE);
				table2.setVisible(false);
				int data = Integer.parseInt(table2_total.getText());
				Payment(data);	
				table2_total.setText("");
				
				for(int i=0; i<=model_T2.getRowCount();i++) {
					model_T2.removeRow(0);
				}
				for(int i=0; i<100;i++) {
					count2[i]=0;
				}
			}

		});

		BtnPanel.add(add_order2);
		BtnPanel.add(table2_add);
		BtnPanel.add(table2_pay);
		table2.add(BtnPanel, BorderLayout.SOUTH);
		table2.add(scrollpane2);
		table2.setVisible(true);
	}

	void Frame_Open3() {
		JPanel BtnPanel = new JPanel(new GridLayout(1,3));
		table3 = new Frame("테이블 3");
		table3.setSize(400, 400);
		table3.setLayout(new BorderLayout());
		table3.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				table_num3.setBackground(Color.LIGHT_GRAY);
				table3.setVisible(false);
			}
		});

		add_order3 = new JComboBox(getTableData(Menu.menuTable));

		JButton table3_add = new JButton("테이블 3 추가");
		table3_add.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				int x = add_order3.getSelectedIndex();
				count3[x] += 1;
				if(count3[x] == 1)
				model_T3.addRow(new Object[] {Menu.menuTable.getValueAt(x, 0), Menu.unvisibleTable.getValueAt(x, 0), count3[x]});
				else 
				{
					for(int j=0;j<model_T3.getRowCount();j++)
					{
						if((model_T3.getValueAt(j,0)).equals(Menu.menuTable.getValueAt(x, 0)))
								model_T3.setValueAt(count3[x],j,2);
					}
				}
				int data = 0;
				String total;
				for(int i=0; i<model_T3.getRowCount();i++) {
					data += (Integer.parseInt((String)model_T3.getValueAt(i, 1)) * Integer.parseInt(model_T3.getValueAt(i,2).toString()));
				}
				total = String.valueOf(data);
				table3_total.setText(total);
			}

		});
		JButton table3_pay = new JButton("테이블 3 결제");
		table3_pay.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				table_num3.setBackground(Color.WHITE);
				table3.setVisible(false);
				int data = Integer.parseInt(table3_total.getText());
				Payment(data);	
				table3_total.setText("");
				
				for(int i=0; i<=model_T3.getRowCount();i++) {
					model_T3.removeRow(0);
				}
				for(int i=0; i<100;i++) {
					count3[i]=0;
				}
			}

		});

		BtnPanel.add(add_order3);
		BtnPanel.add(table3_add);
		BtnPanel.add(table3_pay);
		table3.add(BtnPanel, BorderLayout.SOUTH);
		table3.add(scrollpane3);
		table3.setVisible(true);
	}

	void Frame_Open4() {
		JPanel BtnPanel = new JPanel(new GridLayout(1,3));
		table4 = new Frame("테이블 4");
		table4.setSize(400, 400);
		table4.setLayout(new BorderLayout());
		table4.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				table_num4.setBackground(Color.LIGHT_GRAY);
				table4.setVisible(false);
			}
		});

		add_order4 = new JComboBox(getTableData(Menu.menuTable));

		JButton table4_add = new JButton("테이블 4 추가");
		table4_add.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				int x = add_order4.getSelectedIndex();
				count4[x] += 1;
				if(count4[x] == 1)
				model_T4.addRow(new Object[] {Menu.menuTable.getValueAt(x, 0), Menu.unvisibleTable.getValueAt(x, 0), count4[x]});
				else 
				{
					for(int j=0;j<model_T4.getRowCount();j++)
					{
						if((model_T4.getValueAt(j,0)).equals(Menu.menuTable.getValueAt(x, 0)))
								model_T4.setValueAt(count4[x],j,2);
					}
				}
				int data = 0;
				String total;
				for(int i=0; i<model_T4.getRowCount();i++) {
					data += (Integer.parseInt((String)model_T4.getValueAt(i, 1)) * Integer.parseInt(model_T4.getValueAt(i,2).toString()));
				}
				total = String.valueOf(data);
				table4_total.setText(total);
			}

		});
		JButton table4_pay = new JButton("테이블 4 결제");
		table4_pay.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				table_num4.setBackground(Color.WHITE);
				table4.setVisible(false);
				int data = Integer.parseInt(table4_total.getText());
				Payment(data);	
				table4_total.setText("");
				
				for(int i=0; i<=model_T4.getRowCount();i++) {
					model_T4.removeRow(0);
				}
				for(int i=0; i<100;i++) {
					count4[i]=0;
				}
			}

		});

		BtnPanel.add(add_order4);
		BtnPanel.add(table4_add);
		BtnPanel.add(table4_pay);
		table4.add(BtnPanel, BorderLayout.SOUTH);
		table4.add(scrollpane4);
		table4.setVisible(true);
	}

	void Frame_Open5() {
		JPanel BtnPanel = new JPanel(new GridLayout(1,3));
		table5 = new Frame("테이블 5");
		table5.setSize(400, 400);
		table5.setLayout(new BorderLayout());
		table5.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				table_num5.setBackground(Color.LIGHT_GRAY);
				table5.setVisible(false);
			}
		});

		add_order5 = new JComboBox(getTableData(Menu.menuTable));

		JButton table5_add = new JButton("테이블 5 추가");
		table5_add.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				int x = add_order5.getSelectedIndex();
				count5[x] += 1;
				if(count5[x] == 1)
				model_T5.addRow(new Object[] {Menu.menuTable.getValueAt(x, 0), Menu.unvisibleTable.getValueAt(x, 0), count5[x]});
				else 
				{
					for(int j=0;j<model_T5.getRowCount();j++)
					{
						if((model_T5.getValueAt(j,0)).equals(Menu.menuTable.getValueAt(x, 0)))
								model_T5.setValueAt(count5[x],j,2);
					}
				}
				int data = 0;
				String total;
				for(int i=0; i<model_T5.getRowCount();i++) {
					data += (Integer.parseInt((String)model_T5.getValueAt(i, 1)) * Integer.parseInt(model_T5.getValueAt(i,2).toString()));
				}
				total = String.valueOf(data);
				table5_total.setText(total);
			}

		});
		JButton table5_pay = new JButton("테이블 5 결제");
		table5_pay.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				table_num5.setBackground(Color.WHITE);
				table5.setVisible(false);
				int data = Integer.parseInt(table5_total.getText());
				Payment(data);	
				table5_total.setText("");
				
				for(int i=0; i<=model_T5.getRowCount();i++) {
					model_T5.removeRow(0);
				}
				for(int i=0; i<100;i++) {
					count5[i]=0;
				}
			}

		});

		BtnPanel.add(add_order5);
		BtnPanel.add(table5_add);
		BtnPanel.add(table5_pay);
		table5.add(BtnPanel, BorderLayout.SOUTH);
		table5.add(scrollpane5);
		table5.setVisible(true);
	}

	void Frame_Open6() {
		JPanel BtnPanel = new JPanel(new GridLayout(1,3));
		table6 = new Frame("테이블 6");
		table6.setSize(400, 400);
		table6.setLayout(new BorderLayout());
		table6.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				table_num6.setBackground(Color.LIGHT_GRAY);
				table6.setVisible(false);
			}
		});

		add_order6 = new JComboBox(getTableData(Menu.menuTable));

		JButton table6_add = new JButton("테이블 6 추가");
		table6_add.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				int x = add_order6.getSelectedIndex();
				count6[x] += 1;
				if(count6[x] == 1)
				model_T6.addRow(new Object[] {Menu.menuTable.getValueAt(x, 0), Menu.unvisibleTable.getValueAt(x, 0), count6[x]});
				else 
				{
					for(int j=0;j<model_T6.getRowCount();j++)
					{
						if((model_T6.getValueAt(j,0)).equals(Menu.menuTable.getValueAt(x, 0)))
								model_T6.setValueAt(count6[x],j,2);
					}
				}
				int data = 0;
				String total;
				for(int i=0; i<model_T6.getRowCount();i++) {
					data += (Integer.parseInt((String)model_T6.getValueAt(i, 1)) * Integer.parseInt(model_T6.getValueAt(i,2).toString()));
				}
				total = String.valueOf(data);
				table6_total.setText(total);
			}

		});

		JButton table6_pay = new JButton("테이블 6 결제");
		table6_pay.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				table_num6.setBackground(Color.WHITE);
				table6.setVisible(false);
				int data = Integer.parseInt(table6_total.getText());
				Payment(data);	
				table6_total.setText("");
				
				for(int i=0; i<=model_T6.getRowCount();i++) {
					model_T6.removeRow(0);
				}
				for(int i=0; i<100;i++) {
					count6[i]=0;
				}
			}

		});

		BtnPanel.add(add_order6);
		BtnPanel.add(table6_add);
		BtnPanel.add(table6_pay);
		table6.add(BtnPanel, BorderLayout.SOUTH);
		table6.add(scrollpane6);
		table6.setVisible(true);
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

		if(actionCommand.equals("테이블 1")) {
			Frame_Open1();
			table_num1.setBackground(Color.DARK_GRAY);
		}

		else if(actionCommand.equals("테이블 2")) {
			Frame_Open2();
			table_num2.setBackground(Color.DARK_GRAY);
		}

		else if(actionCommand.equals("테이블 3")) {
			Frame_Open3();
			table_num3.setBackground(Color.DARK_GRAY);
		}

		else if(actionCommand.equals("테이블 4")) {
			Frame_Open4();
			table_num4.setBackground(Color.DARK_GRAY);
		}

		else if(actionCommand.equals("테이블 5")) {
			Frame_Open5();
			table_num5.setBackground(Color.DARK_GRAY);
		}

		else if(actionCommand.equals("테이블 6")) {
			Frame_Open6();
			table_num6.setBackground(Color.DARK_GRAY);
		}

	}
}
