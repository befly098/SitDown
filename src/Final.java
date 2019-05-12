package SitDown;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class Final extends JFrame implements ActionListener {
	
	Date today = new Date();
	SimpleDateFormat date = new SimpleDateFormat("yyyy년 MM월 dd일");
	SimpleDateFormat day = new SimpleDateFormat("dd");
	
	JPanel datePanel;
	static JLabel dateLabel;
	JButton finishBtn; // 상단바에 들어갈 마감 버튼 
	// 날짜 설정하는 부분, 날짜 포맷 설정, datePanel 은 라벨과 버튼 붙이려고 선언
	
	JPanel moneyPanel;
	static JLabel priceLabel;
	JButton quitBtn; // 하단바에 들어갈 종료 버튼
	// 매출 설정하는 부분, moneyPanel 은 라벨과 버튼 붙이려고 선언 
	
	static JTabbedPane tabPanel; // 카테고리 들어갈 tab
	
	public static int today_money;
	public static int total_money;
	//  today : 오늘 매출, total : 전체 매출 
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	// 윈도우 크기에 이용할 변수 : 높이와 너비
	
	private Member member;
	private Menu menu;
	private Storage storage;
	private Table table;
	
	public static void main(String[] args) { // 메인함수 
		Final gui = new Final();
		gui.setVisible(true);
	}

	public Final() { // constructor
		super("SitDown");
		setSize(WIDTH, HEIGHT);
		setLayout(new BorderLayout());
		
		member = new Member();
		menu = new Menu();
		storage = new Storage();
		table = new Table();
		
		upper_bar();
		under_bar();
		
		tabPanel = new JTabbedPane();

		tabPanel.addTab("테이블", table.tablePanel);
		tabPanel.addTab("메뉴", menu.menuPanel);
		tabPanel.addTab("창고", storage.storagePanel);
		tabPanel.addTab("회원", member.memberPanel);
		
		add(tabPanel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	void upper_bar() {	// 여기까지 상단바 추가 과정 (날짜, 마감버튼) 
		
		datePanel = new JPanel();
		datePanel.setBackground(Color.WHITE);
		datePanel.setLayout(new BorderLayout());
		
		try {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("date_money"));
			Date day = (Date) inputStream.readObject();	
			today = day;
		} catch (IOException | ClassNotFoundException e) {
			today = new Date();
		}
		
		dateLabel = new JLabel(date.format(today));
		dateLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		datePanel.add(dateLabel, BorderLayout.WEST);
		
		finishBtn = new JButton("마감");
		finishBtn.addActionListener(this);
		datePanel.add(finishBtn, BorderLayout.EAST);
		add(datePanel, BorderLayout.NORTH);
		
	}
	
	void under_bar() {  // 여기까지 하단바 추가 과정 (매출, 종료버튼)
		
		moneyPanel = new JPanel();
		moneyPanel.setBackground(Color.WHITE);
		moneyPanel.setLayout(new BorderLayout());
		
		try {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("date_money"));
			Date day = (Date) inputStream.readObject();
			int money = inputStream.readInt();
			total_money = money;
		} catch (IOException | ClassNotFoundException e) {
			total_money = 0;
		}

		priceLabel = new JLabel("오늘 매출 : " + today_money + "     전체 잔고 : " + total_money);
		priceLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		moneyPanel.add(priceLabel, BorderLayout.WEST);
		
		quitBtn = new JButton("종료");
		quitBtn.addActionListener(this);
		moneyPanel.add(quitBtn, BorderLayout.EAST);	
		add(moneyPanel, BorderLayout.SOUTH);

	}
	
	private Vector<String> getColumnNames(DefaultTableModel myJTable) {
        Vector<String> columnNames = new Vector<String>();
		for (int i = 0; i < myJTable.getColumnCount(); i++)
            columnNames.add(myJTable.getColumnName(i) );
            return columnNames;
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand(); // 버튼 이름 받아오는 String
		
		if(actionCommand.compareTo("종료") == 0) { // 종료 버튼 
			int check = 0;
			for (int i = 0; i < Table.T.length; i++) {
				if (Table.T[i].getRowCount() <= 0)
					check++;
			}
			
			if(check != Table.T.length) {
				Frame errorBox = new Frame("Error");
				errorBox.setSize(300, 300);
				errorBox.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent windowEvent) {
						errorBox.setVisible(false);
					}
				});
				JLabel errorMessage = new JLabel("계산이 안된 테이블이 있습니다!");
				errorMessage.setHorizontalAlignment(JLabel.CENTER);
				errorBox.add(errorMessage);
				errorBox.setVisible(true);
				return;
			}
			
			if(today_money > 0) {
				total_money += today_money;
			}
			
			try {
				ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("MemberTable"));
				ObjectOutputStream outputStream2 = new ObjectOutputStream(new FileOutputStream("MenuTable"));
				ObjectOutputStream outputStream3 = new ObjectOutputStream(new FileOutputStream("unvisibleMenuTable"));
				ObjectOutputStream outputStream4 = new ObjectOutputStream(new FileOutputStream("StorageTable"));
				ObjectOutputStream outputStream5 = new ObjectOutputStream(new FileOutputStream("unvisibleTable"));
				ObjectOutputStream outputStream6 = new ObjectOutputStream(new FileOutputStream("WorkerTable"));
				ObjectOutputStream outputStream_DM = new ObjectOutputStream(new FileOutputStream("date_money"));
				
				outputStream.writeObject(Member.memberTableModel.getDataVector());
				outputStream.writeObject(getColumnNames(Member.memberTableModel));
				outputStream2.writeObject(Menu.menuTableModel.getDataVector());
				outputStream2.writeObject(getColumnNames(Menu.menuTableModel));
				outputStream3.writeObject(Menu.unvisibleTableModel.getDataVector());
				outputStream3.writeObject(getColumnNames(Menu.unvisibleTableModel));
				outputStream4.writeObject(Storage.storageTableModel.getDataVector());
				outputStream4.writeObject(getColumnNames(Storage.storageTableModel));
				outputStream5.writeObject(Storage.unvisibleTableModel.getDataVector());
				outputStream5.writeObject(getColumnNames(Storage.unvisibleTableModel));
				
				outputStream_DM.writeObject(today);
				outputStream_DM.writeInt(total_money);
				
				outputStream.close();
				outputStream2.close();
				outputStream3.close();
				outputStream4.close();
				outputStream5.close();
				outputStream6.close();
				outputStream_DM.close();
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			System.exit(0);
		}
		else if(actionCommand.equals("마감")) { // 마감 버튼 -> 날짜 바뀌기
			int check = 0;
			for (int i = 0; i < Table.T.length; i++) {
				if (Table.T[i].getRowCount() <= 0)
					check++;
			}
			
			if(check != Table.T.length) {
				Frame errorBox = new Frame("Error");
				errorBox.setSize(300, 300);
				errorBox.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent windowEvent) {
						errorBox.setVisible(false);
					}
				});
				JLabel errorMessage = new JLabel("계산이 안된 테이블이 있습니다!");
				errorMessage.setHorizontalAlignment(JLabel.CENTER);
				errorBox.add(errorMessage);
				errorBox.setVisible(true);
				return;
			}
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(today);
			cal.add(Calendar.DATE, 1);
			
			String strDay = day.format(cal.getTime());
			int get_day = Integer.parseInt(strDay);
			
			today = new Date(cal.getTimeInMillis());
			String strDate = date.format(cal.getTime());
			dateLabel.setText(strDate);
			
			total_money += today_money;
			today_money = 0;
			
			for(int i = 0; i<Storage.storageTable.getRowCount();i++){
				int data = 0;
				
				try {
					data = Integer.parseInt((String)Storage.storageTable.getValueAt(i, 2));
				}
				catch(ClassCastException e1) {
					data = (int)Storage.storageTable.getValueAt(i, 2);
				}
				
				if(data != 0) {
					int k = Integer.parseInt((String) Storage.storageTable.getValueAt(i, 1));
					int price = Integer.parseInt((String)Storage.storageTable.getValueAt(i, 3));
					price *= data;
					k += data;
					Storage.storageTable.setValueAt(0, i, 2);
					Storage.storageTable.setValueAt(Integer.toString(k), i, 1);
					total_money -= price;
				}
			
			}
			
			strDate = "오늘 매출 : " + today_money + "     전체 잔고 : " + total_money;
			priceLabel.setText(strDate);
			
			
		}
		
	}
}
