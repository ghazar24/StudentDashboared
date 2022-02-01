
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class sqlitConnection {

	
	public static Connection dbConnecter() {
		
		try {
			
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:StudentDB.db");
			//JOptionPane.showMessageDialog(null, "Connected");
			return conn;
			
		}
		catch(Exception e) {
			
			JOptionPane.showMessageDialog(null, e);
			return null;
			
		}
		
	}
}
