package bean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.*;

import com.oreilly.servlet.MultipartRequest;

public class MappingDao {
   ApiInputVo invo = null; // �����ִ°�
   ApiOutputVo outvo = null; // �޴� ��
   Connection conn;
   int size = 1024 * 1024 * 10;
   String uploadPath = "C:/workspace/test0520/WebContent/upload/";

   String encoding = "utf-8";
   MultipartRequest multi = null;

   PageVo pVo;

   public MappingDao() {
      conn = new DBConnect().getConn();
   }

   public String mapSearch(HttpServletRequest req) {
//      try {
//         req.setCharacterEncoding("UTF-8");
//      } catch (UnsupportedEncodingException e1) {
//         // TODO Auto-generated catch block
//         e1.printStackTrace();
//      }
      String findStr = req.getParameter("findStr");


      String clientId = "xGQwJnJ3jWXJNrNgZ7Ip";// ���ø����̼� Ŭ���̾�Ʈ ���̵�";
      String clientSecret = "pvqMexieFB";// ���ø����̼� Ŭ���̾�Ʈ ��ũ����";
      StringBuffer res = new StringBuffer();
      JSONObject data = null;
      JSONArray array = new JSONArray();

      List<ApiOutputVo> list = new ArrayList<ApiOutputVo>();
      try {
         String text = URLEncoder.encode(findStr, "UTF-8");
         String apiURL = "https://openapi.naver.com/v1/search/local?query=" + text;
         URL url = new URL(apiURL);
         HttpURLConnection con = (HttpURLConnection) url.openConnection();
         con.setRequestMethod("GET");
         con.setRequestProperty("X-Naver-Client-Id", clientId);
         con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
         int responseCode = con.getResponseCode();
         BufferedReader br;
         if (responseCode == 200) { // ���� ȣ��
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
         } else { // ���� �߻�
            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
         }
         String inputLine;

         while ((inputLine = br.readLine()) != null) {
            res.append(inputLine);
         }
         br.close();
         JSONObject jsonObject = null;
         // String json = null;
         // String line = null;

         jsonObject = new JSONObject(res.toString());

         array = jsonObject.getJSONArray("items");

         for (int i = 0; i < 2; i++) {
            data = new JSONObject();
            data = (JSONObject) array.get(i);

            /* if(title.contains(city)||title.contains(brand)){ */

            outvo = new ApiOutputVo();
//            outvo.setTitle(data.getString("title"));
//            outvo.setLink(data.getString("link"));
//            outvo.setTelephone(data.getString("telephone"));
//            outvo.setAddress(data.getString("address"));
//            outvo.setRoadAddress(data.getString("roadAddress"));
//            
            URLEncoder.encode(findStr, "UTF-8");
            
            outvo.setTitle(data.getString("title"));
            outvo.setLink(data.getString("link"));
            outvo.setTelephone(data.getString("telephone"));
            outvo.setAddress(data.getString("address"));
            outvo.setRoadAddress(data.getString("roadAddress"));
            
            
            
            list.add(outvo);

            /* } */
         }

      } catch (Exception e) {
         e.printStackTrace();
      }
      req.setAttribute("list", list);
      System.out.println("ddddddddddd : " + list.toString());
      String result = "";
      
      return result;

   }

}