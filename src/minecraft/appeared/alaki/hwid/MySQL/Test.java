package appeared.alaki.hwid.MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Test {
	
	/*private static final String host = "sql8.freemysqlhosting.net";
	private static final String port = "3306";
	private static final String database = "sql8502948";
	private static final String username = "sql8502948";
	private static final String password = "r2jau35rNQ";
	
	private static Connection con;
	
	public static boolean isConnected() {
		return (con == null ? false : true);
	}
	
	public static void connect() throws ClassNotFoundException{
		
		if(!isConnected()) {
			
			try {
				
			Class.forName("com.mysql.cj.jdbc.Driver");			
			
				con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
				System.out.println("[Auth] Connected");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public static void disconnect() {
		if(!isConnected()) {
			try {
				con.close();
				System.out.println("[Auth] Disconnected");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public static void update(String qry) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(qry);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/

}
