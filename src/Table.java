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
	JButton menuAdd;
	JButton menuPay;

	JPanel tableTotalPanel;
	JPanel tableListPanel;
	JPanel tableEditPanel;
	JButton tableAdd;
	JButton tableDelete;

	JPanel[] tableNum = new JPanel[50];
	// 테이블 + 토탈 값 넣을 패널
	JPanel[] table = new JPanel[50];

	JToggleButton[] tableBtn = new JToggleButton[50];
	ButtonGroup tableBtnGroup = new ButtonGroup();
	// 다이얼로그와 연결된 테이블 버튼

	JTextField[] tableTotal = new JTextField[50];
	// 테이블 총액 띄울 텍스트 필드

	Frame cash;

	static JTable[] T = new JTable[50];
	static JTable MEM;

	DefaultTableModel[] modelT = new DefaultTableModel[50];
	DefaultTableModel modelOrder;
	DefaultTableModel modelMem;

	JScrollPane[] scrollpane = new JScrollPane[50];
	JScrollPane scrollpaneOrder;
	// JTable 받을 JScrollPane
	JScrollPane scrollpaneMem;

	JComboBox menuList;
	JTextField menuQuantity;
	// 메뉴 선택 박스

	int tables = 6;
	int[][] count = new int[50][100];

	public Table() {
		tablePanel = new JPanel();
		tablePanel.setLayout(new GridLayout(1, 2));

		tableListPanel = new JPanel();
		tableListPanel.setLayout(new GridLayout(5, 2));

		for (int i = 0; i < tableNum.length; i++) {
			tableNum[i] = new JPanel();
			tableNum[i].setLayout(new BorderLayout());
			tableNum[i].setBackground(Color.WHITE);
		}

		String name = new String("테이블 1");
		for (int i = 0; i < tableBtn.length; i++) {
			tableBtn[i] = new JToggleButton(name);
			tableBtnGroup.add(tableBtn[i]);
			name = name.replace(Integer.toString(i + 1), Integer.toString(i + 2));
		}

		for (int i = 0; i < tableTotal.length; i++)
			tableTotal[i] = new JTextField(10);
		String tableColumn[] = { "이름", "가격", "수량" };
		for (int i = 0; i < T.length; i++) {
			Vector[] userColumn = new Vector[50];
			userColumn[i] = new Vector<String>();
			userColumn[i].addElement("이름");
			userColumn[i].addElement("가격");
			userColumn[i].addElement("수량");
			modelT[i] = new DefaultTableModel(userColumn[i], 0);
			T[i] = new JTable(modelT[i]);
			scrollpane[i] = new JScrollPane(T[i]);
		}

		Vector<String> userColumnMem = new Vector<>();
		userColumnMem.addElement("이름");
		userColumnMem.addElement("연락처");
		userColumnMem.addElement("마일리지");
		userColumnMem.addElement("등급");
		modelMem = new DefaultTableModel(userColumnMem, 0);
		MEM = new JTable(modelMem);
		scrollpaneMem = new JScrollPane(MEM);

		for (int i = 0; i < tableNum.length; i++) {
			tableNum[i].add(tableBtn[i]);
			tableNum[i].add(tableTotal[i], BorderLayout.SOUTH);
		}

		for (int i = 0; i < tableBtn.length; i++)
			tableBtn[i].addActionListener(this);

		for (int i = 0; i < tables; i++)
			tableListPanel.add(tableNum[i]);

		tableTotalPanel = new JPanel(new BorderLayout());
		tableEditPanel = new JPanel(new GridLayout(1, 2));
		tableAdd = new JButton("테이블 추가");
		tableAdd.addActionListener(this);
		tableDelete = new JButton("테이블 삭제");
		tableDelete.addActionListener(this);

		tableEditPanel.add(tableAdd);
		tableEditPanel.add(tableDelete);
		tableTotalPanel.add(tableListPanel);
		tableTotalPanel.add(tableEditPanel, BorderLayout.SOUTH);

		/////////// 아래부터 주문 정보를 보여주는 패널 디자인 ////////////////
		orderPanel = new JPanel();
		orderPanel.setLayout(new BorderLayout());
		BtnPanel = new JPanel(new GridLayout(1, 3));

		Vector<String> userColumnOrder = new Vector<>();
		userColumnOrder.addElement("이름");
		userColumnOrder.addElement("가격");
		userColumnOrder.addElement("수량");
		modelOrder = new DefaultTableModel(userColumnOrder, 0);
		order = new JTable(modelOrder);
		scrollpaneOrder = new JScrollPane(order);

		menuList = new JComboBox(getTableData(Menu.menuTable));
		menuList.addActionListener(this);
		menuQuantity=new JTextField(100);
		menuQuantity.setText("1");
		menuAdd = new JButton("추가");
		menuAdd.addActionListener(this);
		menuPay = new JButton("결제");
		menuPay.addActionListener(this);

		BtnPanel.add(menuList);
		BtnPanel.add(menuQuantity);
		BtnPanel.add(menuAdd);
		BtnPanel.add(menuPay);

		orderPanel.add(scrollpaneOrder);
		orderPanel.add(BtnPanel, BorderLayout.SOUTH);

		tablePanel.add(tableTotalPanel, BorderLayout.CENTER);
		tablePanel.add(orderPanel, BorderLayout.EAST);
	}

	void Payment(int data) {
		cash = new Frame("결제창");
		cash.setSize(450, 500);
		cash.setLayout(new BorderLayout());
		cash.addWindowListener(new WindowAdapter() {
			@Override
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
						modelMem.addRow(
								new Object[] { Member.memberTable.getValueAt(i, 1), Member.memberTable.getValueAt(i, 2),
										Member.memberTable.getValueAt(i, 3), Member.memberTable.getValueAt(i, 4) });
					}
				}
			}
		});
		p.add(scrollpaneMem, BorderLayout.CENTER);

		JPanel paypanel = new JPanel();
		JButton cashBtn = new JButton("현금결제");
		paypanel.add(cashBtn);
		cashBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (MEM.getSelectedRow() == -1)
					Final.todayMoney += data;
				else {
					Object name = new Object();
					name = modelMem.getValueAt(MEM.getSelectedRow(), 0);
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
								Final.todayMoney += data * 0.98;
							} else if (rating.compareTo("골드") == 0) {
								int mileage = 0;
								try {
									mileage = (int) Member.memberTable.getValueAt(i, 3);
								} catch (ClassCastException e1) {
									mileage = Integer.parseInt((String) Member.memberTable.getValueAt(i, 3));
								}
								mileage += data * 0.02;
								Member.memberTable.setValueAt(mileage, i, 3);
								Final.todayMoney += data * 0.95;
							} else if (rating.equals("플래티넘")) {
								int mileage = 0;
								try {
									mileage = (int) Member.memberTable.getValueAt(i, 3);
								} catch (ClassCastException e1) {
									mileage = Integer.parseInt((String) Member.memberTable.getValueAt(i, 3));
								}
								mileage += data * 0.02;
								Member.memberTable.setValueAt(mileage, i, 3);
								Final.todayMoney += data * 0.9;
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
				String str = "오늘 매출 : " + Final.todayMoney + "     전체 잔고 : " + Final.totalMoney;
				Final.priceLabel.setText(str);

				for (int i = 0; i < modelMem.getRowCount(); i++) {
					modelMem.removeRow(0);
				}

				cash.setVisible(false);

				tableTotal[clickedTableBtn].setText("");

				for (int i = 0; i <= modelT[clickedTableBtn].getRowCount(); i++) {
					modelT[clickedTableBtn].removeRow(0);
				}
				for (int i = 0; i < 100; i++) {
					count[clickedTableBtn][i] = 0;
				}
			}
		});

		JButton cardBtn = new JButton("카드결제");
		paypanel.add(cardBtn);
		cardBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (MEM.getSelectedRow() == -1)
					Final.todayMoney += data;
				else {
					Object name = new Object();
					name = modelMem.getValueAt(MEM.getSelectedRow(), 0);
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
								Final.todayMoney += data * 0.98;
							} else if (rating.compareTo("골드") == 0) {
								int mileage = 0;
								try {
									mileage = (int) Member.memberTable.getValueAt(i, 3);
								} catch (ClassCastException e1) {
									mileage = Integer.parseInt((String) Member.memberTable.getValueAt(i, 3));
								}
								mileage += data * 0.02;
								Member.memberTable.setValueAt(mileage, i, 3);
								Final.todayMoney += data * 0.95;
							} else if (rating.equals("플래티넘")) {
								int mileage = 0;
								try {
									mileage = (int) Member.memberTable.getValueAt(i, 3);
								} catch (ClassCastException e1) {
									mileage = Integer.parseInt((String) Member.memberTable.getValueAt(i, 3));
								}
								mileage += data * 0.02;
								Member.memberTable.setValueAt(mileage, i, 3);
								Final.todayMoney += data * 0.9;
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
				String str = "오늘 매출 : " + Final.todayMoney + "     전체 잔고 : " + Final.totalMoney;
				Final.priceLabel.setText(str);

				for (int i = 0; i < modelMem.getRowCount(); i++) {
					modelMem.removeRow(0);
				}

				cash.setVisible(false);

				tableTotal[clickedTableBtn].setText("");

				for (int i = 0; i <= modelT[clickedTableBtn].getRowCount(); i++) {
					modelT[clickedTableBtn].removeRow(0);
				}
				for (int i = 0; i < 100; i++) {
					count[clickedTableBtn][i] = 0;
				}
			}
		});

		cash.add(p, BorderLayout.NORTH);
		cash.add(paypanel, BorderLayout.SOUTH);
		cash.setVisible(true);
	}

	int clickedTableBtn = -1;
	int mQuantity=-1;

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
				order.setModel(modelT[i]);
				menuList.removeAllItems();
				menuList.setModel(new DefaultComboBoxModel(getTableData(Menu.menuTable)));
				menuList.repaint();
				clickedTableBtn = i;
				tableNum[i].setBackground(Color.DARK_GRAY);
				break;

			}
		}

		if (actionCommand.compareTo("테이블 추가") == 0) {
			tableNum[tables].add(tableBtn[tables]);
			tableNum[tables].add(tableTotal[tables], BorderLayout.SOUTH);

			tableListPanel.add(tableNum[tables]);
			tables++;
		} else if (actionCommand.compareTo("테이블 삭제") == 0) {
			if (tables > 1) {
				if (tableTotal[tables - 1].getText().equals("")) {
					tableListPanel.remove(tableNum[tables - 1]);
					tableTotal[tables - 1].setText("");
					
					for (int i = 0; i < 100; i++)
						count[tables - 1][i] = 0;

					tableListPanel.setVisible(true);
					tables--;
					clickedTableBtn = -1;
				}

				else if (!tableTotal[tables - 1].getText().equals("")) {
					String notificationStr = "테이블" + String.valueOf(tables) + "을 먼저 결제해주세요";
					JOptionPane.showMessageDialog(null, notificationStr, "미결제 테이블", JOptionPane.INFORMATION_MESSAGE);
				}
			}

		}

		tableListPanel.revalidate();
		tableListPanel.repaint();

		if (actionCommand.compareTo("추가") == 0) {
			int selectedMenu = menuList.getSelectedIndex();
			int cannotOrderFlag = 0;
			String emptyIngredient = "";
			String ingredientArr[] = String.valueOf(Menu.unvisibleTable.getValueAt(selectedMenu, 2)).split("[\n]+");
			String ingredientNameArr[]=new String[ingredientArr.length];
			String ingredientQuantityArr[]=new String[ingredientArr.length];
			int ingredientQauntity;
			
			for(int i=0;i<ingredientArr.length;i++)
			{
				if(ingredientArr[i].split("[ ]+").length<2)
				{
					JOptionPane.showMessageDialog(null, "재료 입력을 (재료이름) (수량)으로 바꿔주세요.", "재료 입력 오류", JOptionPane.INFORMATION_MESSAGE);
			        return; 
				}
				else if(ingredientArr[i].split("[ ]+").length>2)
				{
					JOptionPane.showMessageDialog(null, "재료이름에는 공백이 들어갈 수 없습니다", "재료 입력 오류", JOptionPane.INFORMATION_MESSAGE);
			        return; 
				}
				ingredientNameArr[i]=ingredientArr[i].split("[ ]+")[0];
				ingredientQuantityArr[i]=ingredientArr[i].split("[ ]+")[1];
				//System.out.println(ingredientQuantityArr[i]);
			}
			
			String storageIngredient[] = new String[Storage.storageTable.getRowCount()];

			if(clickedTableBtn!=-1) {
				
				//수량 입력문이 정수인지 확인
				try {
					Integer.parseInt(menuQuantity.getText());
				} catch(NumberFormatException error) { 
					JOptionPane.showMessageDialog(null, "수량은 정수로만 입력할 수 있습니다!", "입력 오류", JOptionPane.INFORMATION_MESSAGE);
			        return; 
			    } catch(NullPointerException error) {
			    	JOptionPane.showMessageDialog(null, "수량을 입력해주세요!", "입력 오류", JOptionPane.INFORMATION_MESSAGE);
			        return;
			    }
				
				//메뉴 수량 quantity 변수에 저장
				mQuantity=Integer.parseInt(menuQuantity.getText());
				if(mQuantity<1) {
					mQuantity=-1;
					JOptionPane.showMessageDialog(null, "1 이상의 수를 입력해주세요!", "입력 오류", JOptionPane.INFORMATION_MESSAGE);
			        return;
				}
				
				menuQuantity.setText("1");
				
			for (int i = 0; i < Storage.storageTable.getRowCount(); i++) {
				storageIngredient[i] = String.valueOf(Storage.storageTable.getValueAt(i, 0));
			}
			// 모든 재료가 있는지 확인
			for (int sIndex = 0; sIndex < ingredientArr.length; sIndex++) {
				//System.out.println(ingredientArr[sIndex].split("[ ]+")[0]);
				boolean ingredientContains = Arrays.stream(storageIngredient)
						.anyMatch(ingredientArr[sIndex].split("[ ]+")[0]::equals);
				if (Boolean.FALSE.equals(ingredientContains)) {
					cannotOrderFlag = 1;
					emptyIngredient = ingredientArr[sIndex].split("[ ]+")[0];
					break;
				}
			}


			// 재료 개수가 0개 이상인지 확인
			if (cannotOrderFlag != 1) {
				for (int sIndex = 0; sIndex < Storage.storageTable.getRowCount(); sIndex++) {
					boolean ingredientContains = Arrays.stream(ingredientNameArr)
							.anyMatch(storageIngredient[sIndex]::equals);

					if (Boolean.TRUE.equals(ingredientContains)) {
						ingredientQauntity=Integer.parseInt((String)ingredientQuantityArr[Arrays.asList(ingredientNameArr).indexOf(storageIngredient[sIndex])]);
						if (Integer.valueOf((String) Storage.storageTable.getValueAt(sIndex, 1)) < mQuantity*ingredientQauntity) {
							cannotOrderFlag = 1;
							emptyIngredient = String.valueOf(Storage.storageTable.getValueAt(sIndex, 0));
							break;
						}
					}
				}
			}
			
			// 만약에 모든 재료가 존재한다면
			if (cannotOrderFlag == 0) {
				count[clickedTableBtn][selectedMenu] += mQuantity;
				
				boolean inTable=false;
				
				for(int j = 0; j < modelT[clickedTableBtn].getRowCount(); j++) 
				{
					if ((modelT[clickedTableBtn].getValueAt(j, 0))
							.equals(Menu.menuTable.getValueAt(selectedMenu, 0))) {
						inTable=true;
						break;
					}
				}

				if (!(inTable)) {

					modelT[clickedTableBtn].addRow(new Object[] { Menu.menuTable.getValueAt(selectedMenu, 0),
							Menu.unvisibleTable.getValueAt(selectedMenu, 0),
							count[clickedTableBtn][selectedMenu] });

					for (int sIndex = 0; sIndex < Storage.storageTable.getRowCount(); sIndex++) {
						boolean ingredientContains = Arrays.stream(ingredientNameArr)
								.anyMatch(storageIngredient[sIndex]::equals);
						if (Boolean.TRUE.equals(ingredientContains)) {
							int quantity = Integer.parseInt((String) Storage.storageTable.getValueAt(sIndex, 1));
							ingredientQauntity=Integer.parseInt((String)ingredientQuantityArr[Arrays.asList(ingredientNameArr).indexOf(storageIngredient[sIndex])]);
							System.out.printf("quan: %d\n",quantity);
							System.out.printf("quan*menu: %d\n",mQuantity*ingredientQauntity);
							quantity-=mQuantity*ingredientQauntity;
							Storage.storageTable.setValueAt(Integer.toString(quantity), sIndex, 1);
						}
					}

				}

				else {
					for (int j = 0; j < modelT[clickedTableBtn].getRowCount(); j++) {
						if ((modelT[clickedTableBtn].getValueAt(j, 0))
								.equals(Menu.menuTable.getValueAt(selectedMenu, 0))) {
							modelT[clickedTableBtn].setValueAt(count[clickedTableBtn][selectedMenu], j, 2);
							break;
						}
					}

					for (int sIndex = 0; sIndex < Storage.storageTable.getRowCount(); sIndex++) {
						boolean ingredientContains = Arrays.stream(ingredientNameArr)
								.anyMatch(storageIngredient[sIndex]::equals);
						if (Boolean.TRUE.equals(ingredientContains)) {
							ingredientQauntity=Integer.parseInt((String)ingredientQuantityArr[Arrays.asList(ingredientNameArr).indexOf(storageIngredient[sIndex])]);
							int quantity = Integer.parseInt((String) Storage.storageTable.getValueAt(sIndex, 1));
							quantity-=mQuantity*ingredientQauntity;
							Storage.storageTable.setValueAt(Integer.toString(quantity), sIndex, 1);
						}
					}

				}

				int data = 0;
				String total;
				for (int i = 0; i < modelT[clickedTableBtn].getRowCount(); i++) {
					data += (Integer.parseInt((String) modelT[clickedTableBtn].getValueAt(i, 1))
							* Integer.parseInt(modelT[clickedTableBtn].getValueAt(i, 2).toString()));
				}
				total = String.valueOf(data);
				tableTotal[clickedTableBtn].setText(total);
				modelT[clickedTableBtn].fireTableDataChanged();
				order.setModel(modelT[clickedTableBtn]);//
				mQuantity=-1;
			}
			// 모든 재료가 존재하지 않는다면

			else if (cannotOrderFlag == 1) {
				String notificationStr = String.valueOf(Menu.menuTable.getValueAt(selectedMenu, 0)) + "의 '"
						+ emptyIngredient + "'이/가 부족합니다!";
				JOptionPane.showMessageDialog(null, notificationStr, "재료 소진", JOptionPane.INFORMATION_MESSAGE);
			}

		}
			else {
				JOptionPane.showMessageDialog(null, "메뉴를 주문할 테이블을 선택해주세요!", "테이블 미선택", JOptionPane.INFORMATION_MESSAGE);
			}
		}

		if (actionCommand.compareTo("결제") == 0) {
			if (clickedTableBtn != -1) {
				tableNum[clickedTableBtn].setBackground(Color.WHITE);
				int data = Integer.parseInt(tableTotal[clickedTableBtn].getText());
				Payment(data);
			}
			
			else
				JOptionPane.showMessageDialog(null, "결제하실 테이블을 선택해주세요!", "테이블 미선택", JOptionPane.INFORMATION_MESSAGE);
		}

	}
}