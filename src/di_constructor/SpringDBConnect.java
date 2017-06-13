package di_constructor;

import java.sql.Connection;
import java.sql.DriverManager;

public class SpringDBConnect {
	private String driver;
	private String url;
	private String username;
	private String password;
	
	
	private Connection conn;


	public Connection getConn() {
		
		try{
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url,username,password);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return conn;
	}


	public void setDriver(String driver) {
		this.driver = driver;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
