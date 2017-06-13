package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.context.support.GenericXmlApplicationContext;

import di_step5.Assembler;

public class BoardDao {
	
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	public BoardDao(DBConnect db){
		this.conn = db.getConn();
	}
	
	public BoardVo view(BoardVo vo){
		BoardVo v = null;
		String sql = "";
		
		try{
			sql = "select * from board where serial = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vo.getSerial());
			rs = ps.executeQuery();
			

			if (rs.next()) {
				v = new BoardVo();
				v.setSerial(rs.getInt("serial"));
				v.setmDate(rs.getString("mDate"));
				v.setWorker(rs.getString("worker"));
				v.setSubject(rs.getString("subject"));
				v.setContent(rs.getString("content"));


			}
		}catch(Exception ex){
			v = null;
		}
		return v;
	}
}