package SitDown;

import java.sql.Connection;
import java.sql.DriverManager;

import java.io.FileInputStream;
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Statement;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class Final extends JFrame implements ActionListener{
   
   Connection connect = null;
   
   String className = "org.gjt.mm.mysql.Driver";
   String url = "jdbc:mysql://localhost:3306/sitDown?useSSL=false&useUnicode=true&characterEncoding=euckr";
   AES128 aes = new AES128();
   String key = "0123456789abcdef";
   String user = "root";
   String encode_user = aes.encrypt(user, key);
   String passwd = "123456";
   String encode_passwd = aes.encrypt(passwd, key);

   String sql;
   Statement statement = null;
   
   Date today = new Date();
   SimpleDateFormat date = new SimpleDateFormat("yyyy년 MM월 dd일");
   SimpleDateFormat day = new SimpleDateFormat("dd");
   
   JPanel datePanel;
   JLabel dateLabel;
   JButton finishBtn; // 상단바에 들어갈 마감 버튼 
   // 날짜 설정하는 부분, 날짜 포맷 설정, datePanel 은 라벨과 버튼 붙이려고 선언
   
   JPanel moneyPanel;
   static JLabel priceLabel;
   JButton quitBtn; // 하단바에 들어갈 종료 버튼
   // 매출 설정하는 부분, moneyPanel 은 라벨과 버튼 붙이려고 선언 
   
   JTabbedPane tabPanel; // 카테고리 들어갈 tab
   
   static int todayMoney = 0;
   static int totalMoney = 0;
   //  today : 오늘 매출, total : 전체 매출 
   
   public static final int WIDTH = 800;
   public static final int HEIGHT = 600;
   // 윈도우 크기에 이용할 변수 : 높이와 너비
   
   private Member member;
   private Menu menu;
   private Storage storage;
   private Table table;
   
   public static final String dateMoney = "date_money";
   
   public static void main(String[] args) throws IOException { // 메인함수 
      Final gui = new Final();
      gui.setVisible(true);
   }

   public Final() throws IOException { // constructor
      super("SitDown");
      setSize(WIDTH, HEIGHT);
      setLayout(new BorderLayout());
      
      member = new Member();
      menu = new Menu();
      storage = new Storage();
      table = new Table();
      
      upperBar();
      underBar();
      
      tabPanel = new JTabbedPane();

      tabPanel.addTab("테이블", table.tablePanel);
      tabPanel.addTab("메뉴", menu.menuPanel);
      tabPanel.addTab("창고", storage.storagePanel);
      tabPanel.addTab("회원", member.memberPanel);
      
      add(tabPanel);
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
   }

   void upperBar() throws IOException {   // 여기까지 상단바 추가 과정 (날짜, 마감버튼) 
      
      datePanel = new JPanel();
      datePanel.setBackground(Color.WHITE);
      datePanel.setLayout(new BorderLayout());
      
      ObjectInputStream inputStream = null;
      try {
         inputStream = new ObjectInputStream(new FileInputStream(dateMoney));
         Date todayDate = (Date) inputStream.readObject();   
         today = todayDate;
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
   
   void underBar() throws IOException {  // 여기까지 하단바 추가 과정 (매출, 종료버튼)
      
      moneyPanel = new JPanel();
      moneyPanel.setBackground(Color.WHITE);
      moneyPanel.setLayout(new BorderLayout());
      
      ObjectInputStream inputStream = null;
      try {
         inputStream = new ObjectInputStream(new FileInputStream(dateMoney));
         Date day = (Date) inputStream.readObject();
         int money = inputStream.readInt();
         totalMoney = money;
      } catch (IOException | ClassNotFoundException e) {
         totalMoney = 0;
      }

      priceLabel = new JLabel("오늘 매출 : " + todayMoney + "     전체 잔고 : " + totalMoney);
      priceLabel.setFont(new Font("Arial", Font.PLAIN, 20));
      moneyPanel.add(priceLabel, BorderLayout.WEST);
      
      quitBtn = new JButton("종료");
      quitBtn.addActionListener(this);
      moneyPanel.add(quitBtn, BorderLayout.EAST);   
      add(moneyPanel, BorderLayout.SOUTH);

   }
   
   private Vector<String> getColumnNames(DefaultTableModel myJTable) {
        Vector<String> columnNames = new Vector<>();
      for (int i = 0; i < myJTable.getColumnCount(); i++) {
            columnNames.add(myJTable.getColumnName(i) );
      }
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
               @Override
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
         
         if(todayMoney > 0) {
            totalMoney += todayMoney;
         }
         
         ObjectOutputStream outputStream = null;
         ObjectOutputStream outputStream2 = null;
         ObjectOutputStream outputStream3= null;
         ObjectOutputStream outputStream4 = null;
         ObjectOutputStream outputStream5= null;
         ObjectOutputStream outputStreamDM = null;
         
         try {
             outputStream = new ObjectOutputStream(new FileOutputStream("MemberTable"));
             outputStream2 = new ObjectOutputStream(new FileOutputStream("MenuTable"));
             outputStream3 = new ObjectOutputStream(new FileOutputStream("unvisibleMenuTable"));
             outputStream4 = new ObjectOutputStream(new FileOutputStream("StorageTable"));
             outputStream5 = new ObjectOutputStream(new FileOutputStream("unvisibleTable"));
             outputStreamDM = new ObjectOutputStream(new FileOutputStream(dateMoney));
            
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
            
            outputStreamDM.writeObject(today);
            outputStreamDM.writeInt(totalMoney);
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
               @Override
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
         
         today = new Date(cal.getTimeInMillis());
         String strDate = date.format(cal.getTime());
         dateLabel.setText(strDate);
         
         totalMoney += todayMoney;
         todayMoney = 0;
         
         for(int i = 0; i<Storage.storageTable.getRowCount();i++){
            int data = 0;
            
            try {
               data = Integer.parseInt((String)Storage.storageTable.getValueAt(i, 2));
            }
            catch(ClassCastException e1) {
               data = (int)Storage.storageTable.getValueAt(i, 2);
            }
            
            if(data != 0) {
               int k = Integer.parseInt(String.valueOf(Storage.storageTable.getValueAt(i, 1)));
               String s1 = (String)Storage.storageTable.getValueAt(i, 0);
               int price = Integer.parseInt((String)Storage.storageTable.getValueAt(i, 3));
               price *= data;
               k += data;
               Storage.storageTable.setValueAt(0, i, 2);
               Storage.storageTable.setValueAt(Integer.toString(k), i, 1);
               
               sql = "UPDATE Storage SET Iquant =" + k + ",Iorder = 0 WHERE Iname=" + s1 +";";
               System.out.println(sql);
               try {
                  Class.forName(className);
                  connect = DriverManager.getConnection(url, aes.decrypt(encode_user, key), aes.decrypt(encode_passwd, key));
                  System.out.println("encode--->" + encode_user);
                  System.out.println("encode--->" + encode_passwd);
                  
                  System.out.println("decode--->" + aes.decrypt(encode_user, key));
                  System.out.println("decode--->" + aes.decrypt(encode_passwd, key));
                  statement = (Statement) connect.createStatement();
                  statement.executeUpdate(sql);
               } catch (Exception e1) {
                  e1.printStackTrace();
               }
               totalMoney -= price;
            }
         
         }
         
         strDate = "오늘 매출 : " + todayMoney + "     전체 잔고 : " + totalMoney;
         priceLabel.setText(strDate);
         
         
      }
      
   }
}
