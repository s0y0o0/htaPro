package mvc;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.EnableLoadTimeWeaving;

public class MemberDao {
	Connection conn;
	
	
	public MemberDao(DBConnect db){
		this.conn = db.getConn();
	}
	
	public int input(MemberVo vo){
		int r = 0;
		PreparedStatement ps = null;
		
		try{
			String sql = " insert into member(userid, userpwd) values(?,?) ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getUserid());
			ps.setString(2, vo.getUserpwd());
			r= ps.executeUpdate();
		}catch(Exception ex){
			r=-1;
			ex.printStackTrace();
		}
		
		return r;
	}
	public int delete(MemberVo vo){
		int r= 0;
		PreparedStatement ps = null;
		String id = vo.getUserid();
		String pwd = vo.getUserpwd();
		
		try{
			String sql = " delete from member where userid = ? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeQuery();
			r= 1;
		}catch(Exception ex){
			r= -1;
			ex.printStackTrace();
		}
		
		return r;
	}
	
	public int modify(MemberVo vo){
		int r= 0;
		PreparedStatement ps = null;
		String id = vo.getUserid();
		String pwd = vo.getUserpwd();
	
		try{
			String sql =  " update member set userpwd=? where userid=? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, pwd);
			ps.setString(2, id);
			r = ps.executeUpdate();
		}catch(Exception ex){
			ex.printStackTrace();
			r=-1;
		}
		return r;
	}
	
	public MemberVo view(MemberVo vo) {
		MemberVo v = new MemberVo();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from member where userid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getUserid());
			rs = ps.executeQuery();
			if (rs.next()) {
				v.setUserpwd(rs.getString("userpwd"));
			} else {
				v.setUserpwd("!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		v.setUserid(vo.getUserid());
		return v;
	}
	
	public List<MemberVo> list(MemberVo vo){
		
		List<MemberVo> list = new ArrayList<MemberVo>();
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		
		try{
			sql = "select * from member where userid like ? order by userid asc ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + vo.getUserid() + "%");
			rs = ps.executeQuery();
			
			while(rs.next()){
				MemberVo v = new MemberVo();
				v.setUserid(rs.getString("userid"));
				v.setUserpwd(rs.getString("userpwd"));
				
				list.add(v);
			}
		
		}catch(Exception ex){
			ex.printStackTrace();
			
		}
		
		return list;
	}
	
}