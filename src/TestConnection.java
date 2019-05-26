package SitDown;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con = null;
		
		String className = "org.gjt.mm.mysql.Driver";
		String url = "jdbc:mysql://localhost:3306/sitDown?useSSL=false&useUnicode=true&characterEncoding=euckr";
		String user = "root";
		String passwd = "123456";
		
		System.out.println("testing\n");
		
		try {
			Class.forName(className);
			con = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connect Success!");
		} catch(Exception e) {
			System.out.println("Connect Failed!");
			e.printStackTrace();
		}finally {
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
