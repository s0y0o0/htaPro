package di_anno;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	Connection conn;
	
	String driver = "oracle.jdbc.driver.OracleDriver";  //각 db제조사에서 제공하는 상수
	//String url = "jdbc:oracleLthin:@서버주소:포트번호:SID"; 연결하려고 하는 db서버 주소
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "hr";
	String pwd = "hr";
	
	
	

	public DBConnect() {
		try{
			Class.forName(driver).newInstance();
		    //System.out.println("driver loading success");
			conn = DriverManager.getConnection(url, user, pwd);
			//System.out.println("connection success");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

	public Connection getConn() {
		return conn;
	}

	public static void main(String[] args) {
		DBConnect db = new DBConnect();

	}

}
