package db;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
@Aspect
public class MemberAdvice {
   
   @Around("execution (* db.MemberDao.*(..))")
   public Object logCheck(ProceedingJoinPoint jp)throws Throwable{
      Object obj = null;
      String file = "log.txt";
      FileOutputStream fos = null;
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
      
      try{
         fos = new FileOutputStream(file, true);
         
         obj = jp.proceed();
         
         MemberDao d = (MemberDao) jp.getTarget();
         String sql = d.getSql();
         
         String now = sdf.format(new Date());
         String cName = jp.getTarget().getClass().getName();
         String mName = jp.getSignature().getName();
         String temp = now + "\t" + cName + "\t" + mName + "\t" + sql + "\t";
         System.out.println(temp);
         
         fos.write(temp.getBytes(), 0, temp.getBytes().length);
         fos.close();
      }catch(Exception e){
         e.printStackTrace();
      }
      
      
      return obj;
   }
}