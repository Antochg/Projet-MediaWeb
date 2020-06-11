package persistantdata;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Accès à la base de données wamp server
 *
 */
public class AccesBD {

	private static String url_jdbc = "jdbc:mysql://localhost/projetj2ee?useUnicode=yes&characterEncoding=UTF-8";
	private static String login = "root";
	private static String pwd = "";

	
	public static Connection connexionBD() throws Exception {
		
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conn = DriverManager.getConnection(url_jdbc, login, pwd);
		
		return conn;
	}
}
