package mvc;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
   Connection conn;
   String driver; 
   String url;
   String user;
   String pwd;

	public Connection getConn() {
		try{
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, user, pwd);
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return conn;
	}

	public static void main(String[] args) {
         DBConnect db = new DBConnect();
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}