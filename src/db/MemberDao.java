package db;

import java.sql.Connection;
import java.util.List;

public class MemberDao {
   Connection conn;
   String sql = null;
   
   public MemberDao(DBConnect db){
      this.conn = db.getConn();
      if(conn != null){
         System.out.println("DB가 정상적으로 연결됨");
      }
   }
   
   public void insert(MemberVo vo){
      System.out.println("insert");
      sql = "insert into ~";
      
   }
   public void update(MemberVo vo){
      System.out.println("update");
      sql = "update to ~";
   }
   public void delete(MemberVo vo){
      System.out.println("delete");
      sql = "delete to ~";
   }
   public MemberVo view(MemberVo vo){
      MemberVo v = null;
      System.out.println("view");
      sql = "view ~";
      return v;
   }
   
   public List<MemberVo>  select(MemberVo vo){
      List<MemberVo> list = null;
      System.out.println("select");
      sql = "select * ~";
      
      return list;
   }

   public String getSql() {
      return sql;
   }
   
   
   
}