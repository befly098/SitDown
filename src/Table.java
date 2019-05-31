package SitDown;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;

public class Table implements ActionListener {

	JPanel tablePanel;

	JPanel orderPanel;
	JPanel BtnPanel;
	JTable order;
	JButton menu_add, menu_pay;

	JPanel tableTotalPanel;
	JPanel tableListPanel;
	JPanel tableEditPanel;
	JButton table_add, table_delete;

	JPanel[] table_num = new JPanel[50];
	// 테이블 + 토탈 값 넣을 패널
	JPanel[] table = new JPanel[50];

	JToggleButton[] table_btn = new JToggleButton[50];
	ButtonGroup table_btn_group = new ButtonGroup();
	// 다이얼로그와 연결된 테이블 버튼

	JTextField[] table_total = new JTextField[50];
	// 테이블 총액 띄울 텍스트 필드

	Frame cash;

	static JTable[] T = new JTable[50];
	static JTable MEM;

	DefaultTableModel[] model_T = new DefaultTableModel[50];
	DefaultTableModel model_order;
	DefaultTableModel model_mem;

	JScrollPane[] scrollpane = new JScrollPane[50];
	JScrollPane scrollpane_order;
	// JTable 받을 JScrollPane
	JScrollPane scrollpane_mem;

	JComboBox menu_list;
	JTextField menu_quantity;
	// 메뉴 선택 박스

	int tables = 6;
	int[][] count = new int[50][100];

	public Table() {
		tablePanel = new JPanel();
		tablePanel.setLayout(new GridLayout(1, 2));

		tableListPanel = new JPanel();
		tableListPanel.setLayout(new GridLayout(5, 2));

		for (int i = 0; i < table_num.length; i++) {
			table_num[i] = new JPanel();
			table_num[i].setLayout(new BorderLayout());
			table_num[i].setBackground(Color.WHITE);
		}

		String name = new String("테이블 1");
		for (int i = 0; i < table_btn.length; i++) {
			table_btn[i] = new JToggleButton(name);
			table_btn_group.add(table_btn[i]);
			name = name.replace(Integer.toString(i + 1), Integer.toString(i + 2));
		}

		for (int i = 0; i < table_total.length; i++)
			table_total[i] = new JTextField(10);
		String table_column[] = { "이름", "가격", "수량" };
		for (int i = 0; i < T.length; i++) {
			Vector[] userColumn = new Vector[50];
			userColumn[i] = new Vector<String>();
			userColumn[i].addElement("이름");
			userColumn[i].addElement("가격");
			userColumn[i].addElement("수량");
			model_T[i] = new DefaultTableModel(userColumn[i], 0);
			T[i] = new JTable(model_T[i]);
			scrollpane[i] = new JScrollPane(T[i]);
		}

		Vector<String> userColumn_mem = new Vector<String>();
		userColumn_mem.addElement("이름");
		userColumn_mem.addElement("연락처");
		userColumn_mem.addElement("마일리지");
		userColumn_mem.addElement("등급");
		model_mem = new DefaultTableModel(userColumn_mem, 0);
		MEM = new JTable(model_mem);
		scrollpane_mem = new JScrollPane(MEM);

		for (int i = 0; i < table_num.length; i++) {
			table_num[i].add(table_btn[i]);
			table_num[i].add(table_total[i], BorderLayout.SOUTH);
		}

		for (int i = 0; i < table_btn.length; i++)
			table_btn[i].addActionListener(this);

		for (int i = 0; i < tables; i++)
			tableListPanel.add(table_num[i]);

		tableTotalPanel = new JPanel(new BorderLayout());
		tableEditPanel = new JPanel(new GridLayout(1, 2));
		table_add = new JButton("테이블 추가");
		table_add.addActionListener(this);
		table_delete = new JButton("테이블 삭제");
		table_delete.addActionListener(this);

		tableEditPanel.add(table_add);
		tableEditPanel.add(table_delete);
		tableTotalPanel.add(tableListPanel);
		tableTotalPanel.add(tableEditPanel, BorderLayout.SOUTH);

		/////////// 아래부터 주문 정보를 보여주는 패널 디자인 ////////////////
		orderPanel = new JPanel();
		orderPanel.setLayout(new BorderLayout());
		BtnPanel = new JPanel(new GridLayout(1, 3));

		Vector<String> userColumn_order = new Vector<String>();
		userColumn_order.addElement("이름");
		userColumn_order.addElement("가격");
		userColumn_order.addElement("수량");
		model_order = new DefaultTableModel(userColumn_order, 0);
		order = new JTable(model_order);
		scrollpane_order = new JScrollPane(order);

		menu_list = new JComboBox(getTableData(Menu.menuTable));
		menu_list.addActionListener(this);
		menu_quantity=new JTextField(100);
		menu_quantity.setText("1");
		menu_add = new JButton("추가");
		menu_add.addActionListener(this);
		menu_pay = new JButton("결제");
		menu_pay.addActionListener(this);

		BtnPanel.add(menu_list);
		BtnPanel.add(menu_quantity);
		BtnPanel.add(menu_add);
		BtnPanel.add(menu_pay);

		/*
		 * //add here for (int i = 0; i < table.length; i++) {
		 * 
		 * JLabel jl = new JLabel("Hello!!!!");
		 * 
		 * table[i] = new JPanel(new BorderLayout()); table[i].add(jl);
		 * table[i].add(scrollpane[i]); table[i].add(BtnPanel, BorderLayout.SOUTH); }
		 */

		orderPanel.add(scrollpane_order);
		orderPanel.add(BtnPanel, BorderLayout.SOUTH);

		tablePanel.add(tableTotalPanel, BorderLayout.CENTER);
		tablePanel.add(orderPanel, BorderLayout.EAST);
	}

	void Payment(int data) {
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
		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String num = new String();
				num = textfield.getText();
				int row = Member.memberTable.getRowCount();
				String[] val = new String[row];
				for (int i = 0; i < val.length; i++) {
					val[i] = (String) Member.memberTable.getValueAt(i, 2);
					val[i] = val[i].substring(val[i].length() - 4, val[i].length());
					if (num.equals(val[i])) {
						model_mem.addRow(
								new Object[] { Member.memberTable.getValueAt(i, 1), Member.memberTable.getValueAt(i, 2),
										Member.memberTable.getValueAt(i, 3), Member.memberTable.getValueAt(i, 4) });
					}
				}
			}
		});
		p.add(scrollpane_mem, BorderLayout.CENTER);

		JPanel paypanel = new JPanel();
		JButton cash_btn = new JButton("현금결제");
		paypanel.add(cash_btn);
		cash_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (MEM.getSelectedRow() == -1)
					Final.today_money += data;
				else {
					Object name = new Object();
					name = model_mem.getValueAt(MEM.getSelectedRow(), 0);
					int row = Member.memberTable.getRowCount();
					for (int i = 0; i < row; i++) {
						if (name.equals(Member.memberTable.getValueAt(i, 1))) {
							String rating = (String) Member.memberTable.getValueAt(i, 4);

							if (rating.equals("일반")) {
								int mileage = 0;
								try {
									mileage = (int) Member.memberTable.getValueAt(i, 3);
								} catch (ClassCastException e1) {
									mileage = Integer.parseInt((String) Member.memberTable.getValueAt(i, 3));
								}
								mileage += data * 0.02;
								Member.memberTable.setValueAt(mileage, i, 3);
								Final.today_money += data * 0.98;
							} else if (rating.compareTo("골드") == 0) {
								int mileage = 0;
								try {
									mileage = (int) Member.memberTable.getValueAt(i, 3);
								} catch (ClassCastException e1) {
									mileage = Integer.parseInt((String) Member.memberTable.getValueAt(i, 3));
								}
								mileage += data * 0.02;
								Member.memberTable.setValueAt(mileage, i, 3);
								Final.today_money += data * 0.95;
							} else if (rating.equals("플래티넘")) {
								int mileage = 0;
								try {
									mileage = (int) Member.memberTable.getValueAt(i, 3);
								} catch (ClassCastException e1) {
									mileage = Integer.parseInt((String) Member.memberTable.getValueAt(i, 3));
								}
								mileage += data * 0.02;
								Member.memberTable.setValueAt(mileage, i, 3);
								Final.today_money += data * 0.9;
							}

							int mileage = (int) Member.memberTable.getValueAt(i, 3);
							if (mileage >= 500 && mileage < 1000)
								Member.memberTable.setValueAt("골드", i, 4);
							else if (mileage > 1000) {
								Member.memberTable.setValueAt("플래티넘", i, 4);
							} else {
								Member.memberTable.setValueAt("일반", i, 4);
							}
							break;
						}
					}
				}
				String str = "오늘 매출 : " + Final.today_money + "     전체 잔고 : " + Final.total_money;
				Final.priceLabel.setText(str);

				for (int i = 0; i < model_mem.getRowCount(); i++) {
					model_mem.removeRow(0);
				}

				cash.setVisible(false);

				table_total[clicked_table_btn].setText("");

				for (int i = 0; i <= model_T[clicked_table_btn].getRowCount(); i++) {
					model_T[clicked_table_btn].removeRow(0);
				}
				for (int i = 0; i < 100; i++) {
					count[clicked_table_btn][i] = 0;
				}
			}
		});

		JButton card_btn = new JButton("카드결제");
		paypanel.add(card_btn);
		card_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (MEM.getSelectedRow() == -1)
					Final.today_money += data;
				else {
					Object name = new Object();
					name = model_mem.getValueAt(MEM.getSelectedRow(), 0);
					int row = Member.memberTable.getRowCount();
					for (int i = 0; i < row; i++) {
						if (name.equals(Member.memberTable.getValueAt(i, 1))) {
							String rating = (String) Member.memberTable.getValueAt(i, 4);

							if (rating.equals("일반")) {
								int mileage = 0;
								try {
									mileage = (int) Member.memberTable.getValueAt(i, 3);
								} catch (ClassCastException e1) {
									mileage = Integer.parseInt((String) Member.memberTable.getValueAt(i, 3));
								}
								mileage += data * 0.02;
								Member.memberTable.setValueAt(mileage, i, 3);
								Final.today_money += data * 0.98;
							} else if (rating.compareTo("골드") == 0) {
								int mileage = 0;
								try {
									mileage = (int) Member.memberTable.getValueAt(i, 3);
								} catch (ClassCastException e1) {
									mileage = Integer.parseInt((String) Member.memberTable.getValueAt(i, 3));
								}
								mileage += data * 0.02;
								Member.memberTable.setValueAt(mileage, i, 3);
								Final.today_money += data * 0.95;
							} else if (rating.equals("플래티넘")) {
								int mileage = 0;
								try {
									mileage = (int) Member.memberTable.getValueAt(i, 3);
								} catch (ClassCastException e1) {
									mileage = Integer.parseInt((String) Member.memberTable.getValueAt(i, 3));
								}
								mileage += data * 0.02;
								Member.memberTable.setValueAt(mileage, i, 3);
								Final.today_money += data * 0.9;
							}

							int mileage = (int) Member.memberTable.getValueAt(i, 3);
							if (mileage >= 500 && mileage < 1000)
								Member.memberTable.setValueAt("골드", i, 4);
							else if (mileage > 1000) {
								Member.memberTable.setValueAt("플래티넘", i, 4);
							} else {
								Member.memberTable.setValueAt("일반", i, 4);
							}
							break;
						}
					}
				}
				String str = "오늘 매출 : " + Final.today_money + "     전체 잔고 : " + Final.total_money;
				Final.priceLabel.setText(str);

				for (int i = 0; i < model_mem.getRowCount(); i++) {
					model_mem.removeRow(0);
				}

				cash.setVisible(false);

				table_total[clicked_table_btn].setText("");

				for (int i = 0; i <= model_T[clicked_table_btn].getRowCount(); i++) {
					model_T[clicked_table_btn].removeRow(0);
				}
				for (int i = 0; i < 100; i++) {
					count[clicked_table_btn][i] = 0;
				}
			}
		});

		cash.add(p, BorderLayout.NORTH);
		cash.add(paypanel, BorderLayout.SOUTH);
		cash.setVisible(true);
	}

	int clicked_table_btn = -1;
	int m_quantity;

	public Object[] getTableData(JTable table) {

		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		int nRow = dtm.getRowCount();
		Object[] tableData = new Object[nRow];

		for (int i = 0; i < nRow; i++) {
			tableData[i] = dtm.getValueAt(i, 0);
		}

		return tableData;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		String[] name = new String[50];

		for (int i = 0; i < 50; i++) {
			name[i] = new String("테이블 ");
			name[i] = name[i].concat(Integer.toString(i + 1));
		}

		for (int i = 0; i < tables; i++) {
			if (actionCommand.equals(name[i])) {
				// Table_Open(i, e);
				order.setModel(model_T[i]);
				menu_list.removeAllItems();
				menu_list.setModel(new DefaultComboBoxModel(getTableData(Menu.menuTable)));
				menu_list.repaint();
				clicked_table_btn = i;
				table_num[i].setBackground(Color.DARK_GRAY);
				break;

			}
		}

		if (actionCommand.compareTo("테이블 추가") == 0) {
			table_num[tables].add(table_btn[tables]);
			table_num[tables].add(table_total[tables], BorderLayout.SOUTH);

			tableListPanel.add(table_num[tables]);

			// tableListPanel.setVisible(true);

			tables++;
		} else if (actionCommand.compareTo("테이블 삭제") == 0) {
			if (tables > 1) {
				if (table_total[tables - 1].getText().equals("")) {
					tableListPanel.remove(table_num[tables - 1]);
					table_total[tables - 1].setText("");

					/*
					 * for (int i = 0; i <= model_T[tables-1].getRowCount(); i++)
					 * model_T[tables-1].removeRow(0);
					 */

					for (int i = 0; i < 100; i++)
						count[tables - 1][i] = 0;

					tableListPanel.setVisible(true);
					tables--;
					clicked_table_btn = -1;
				}

				else if (!table_total[tables - 1].getText().equals("")) {
					String notification_str = "테이블" + String.valueOf(tables) + "을 먼저 결제해주세요";
					JOptionPane.showMessageDialog(null, notification_str, "미결제 테이블", JOptionPane.INFORMATION_MESSAGE);
				}
			}

		}

		tableListPanel.revalidate();
		tableListPanel.repaint();

		if (actionCommand.compareTo("추가") == 0) {
			int selected_menu = menu_list.getSelectedIndex();
			int cannot_order_flag = 0;
			String empty_ingredient = "";
			String ingredient_arr[] = String.valueOf(Menu.unvisibleTable.getValueAt(selected_menu, 2)).split("[\n, ]+");
			for(int i=0;i<ingredient_arr.length;i++)
				System.out.println(ingredient_arr[i]);
			String storage_ingredient[] = new String[Storage.storageTable.getRowCount()];

			if(clicked_table_btn!=-1) {
				
				//수량 입력문이 정수인지 확인
				try {
					Integer.parseInt(menu_quantity.getText());
				} catch(NumberFormatException error) { 
					JOptionPane.showMessageDialog(null, "수량은 정수로만 입력할 수 있습니다!", "입력 오류", JOptionPane.INFORMATION_MESSAGE);
			        return; 
			    } catch(NullPointerException error) {
			    	JOptionPane.showMessageDialog(null, "수량을 입력해주세요!", "입력 오류", JOptionPane.INFORMATION_MESSAGE);
			        return;
			    }
				
				//메뉴 수량 quantity 변수에 저장
				m_quantity=Integer.parseInt(menu_quantity.getText());
				if(m_quantity<1) {
					JOptionPane.showMessageDialog(null, "1 이상의 수를 입력해주세요!", "입력 오류", JOptionPane.INFORMATION_MESSAGE);
			        return;
				}
				
				menu_quantity.setText("1");
				
			for (int i = 0; i < Storage.storageTable.getRowCount(); i++) {
				storage_ingredient[i] = String.valueOf(Storage.storageTable.getValueAt(i, 0));
			}
			// 모든 재료가 있는지 확인
			for (int s_index = 0; s_index < ingredient_arr.length; s_index++) {
				boolean ingredient_contains = Arrays.stream(storage_ingredient)
						.anyMatch(ingredient_arr[s_index]::equals);
				if (Boolean.FALSE.equals(ingredient_contains)) {
					cannot_order_flag = 1;
					empty_ingredient = ingredient_arr[s_index];
					break;
				}
			}

			// 재료 개수가 0개 이상인지 확인
			if (cannot_order_flag != 1) {
				for (int s_index = 0; s_index < Storage.storageTable.getRowCount(); s_index++) {
					boolean ingredient_contains = Arrays.stream(ingredient_arr)
							.anyMatch(storage_ingredient[s_index]::equals);

					if (Boolean.TRUE.equals(ingredient_contains)) {
						if (Integer.valueOf((String) Storage.storageTable.getValueAt(s_index, 1)) < m_quantity) {
							cannot_order_flag = 1;
							empty_ingredient = String.valueOf(Storage.storageTable.getValueAt(s_index, 0));
							break;
						}
					}
				}
			}
			
			// 만약에 모든 재료가 존재한다면
			if (cannot_order_flag == 0) {
				count[clicked_table_btn][selected_menu] += m_quantity;
				
				boolean in_table=false;
				
				for(int j = 0; j < model_T[clicked_table_btn].getRowCount(); j++) 
				{
					if ((model_T[clicked_table_btn].getValueAt(j, 0))
							.equals(Menu.menuTable.getValueAt(selected_menu, 0))) {
						in_table=true;
						break;
					}
				}

				if (!(in_table)) {

					model_T[clicked_table_btn].addRow(new Object[] { Menu.menuTable.getValueAt(selected_menu, 0),
							Menu.unvisibleTable.getValueAt(selected_menu, 0),
							count[clicked_table_btn][selected_menu] });

					for (int s_index = 0; s_index < Storage.storageTable.getRowCount(); s_index++) {
						boolean ingredient_contains = Arrays.stream(ingredient_arr)
								.anyMatch(storage_ingredient[s_index]::equals);
						if (Boolean.TRUE.equals(ingredient_contains)) {
							int quantity = Integer.valueOf((String) Storage.storageTable.getValueAt(s_index, 1));
							quantity-=m_quantity;
							Storage.storageTable.setValueAt(Integer.toString(quantity), s_index, 1);
						}
					}

				}

				else {
					for (int j = 0; j < model_T[clicked_table_btn].getRowCount(); j++) {
						if ((model_T[clicked_table_btn].getValueAt(j, 0))
								.equals(Menu.menuTable.getValueAt(selected_menu, 0))) {
							model_T[clicked_table_btn].setValueAt(count[clicked_table_btn][selected_menu], j, 2);
							break;
						}
					}

					for (int s_index = 0; s_index < Storage.storageTable.getRowCount(); s_index++) {
						boolean ingredient_contains = Arrays.stream(ingredient_arr)
								.anyMatch(storage_ingredient[s_index]::equals);
						if (Boolean.TRUE.equals(ingredient_contains)) {
							int quantity = Integer.valueOf((String) Storage.storageTable.getValueAt(s_index, 1));
							quantity-=m_quantity;
							Storage.storageTable.setValueAt(Integer.toString(quantity), s_index, 1);
						}
					}

				}

				int data = 0;
				String total;
				for (int i = 0; i < model_T[clicked_table_btn].getRowCount(); i++) {
					data += (Integer.parseInt((String) model_T[clicked_table_btn].getValueAt(i, 1))
							* Integer.parseInt(model_T[clicked_table_btn].getValueAt(i, 2).toString()));
				}
				total = String.valueOf(data);
				table_total[clicked_table_btn].setText(total);
				model_T[clicked_table_btn].fireTableDataChanged();
				order.setModel(model_T[clicked_table_btn]);//
			}
			// 모든 재료가 존재하지 않는다면

			else if (cannot_order_flag == 1) {
				String notification_str = String.valueOf(Menu.menuTable.getValueAt(selected_menu, 0)) + "의 '"
						+ empty_ingredient + "'이/가 부족합니다!";
				JOptionPane.showMessageDialog(null, notification_str, "재료 소진", JOptionPane.INFORMATION_MESSAGE);
			}

		}
			else {
				JOptionPane.showMessageDialog(null, "메뉴를 주문할 테이블을 선택해주세요!", "테이블 미선택", JOptionPane.INFORMATION_MESSAGE);
			}
		}

		if (actionCommand.compareTo("결제") == 0) {
			if (clicked_table_btn != -1) {
				table_num[clicked_table_btn].setBackground(Color.WHITE);
				int data = Integer.parseInt(table_total[clicked_table_btn].getText());
				Payment(data);
			}
			
			else
				JOptionPane.showMessageDialog(null, "결제하실 테이블을 선택해주세요!", "테이블 미선택", JOptionPane.INFORMATION_MESSAGE);
		}

	}
}