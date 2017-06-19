package bean;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpRequest;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sun.javafx.print.PrinterImpl;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;
import kr.or.kobis.kobisopenapi.consumer.rest.exception.OpenAPIFault;

public class WaddaDao {

	// 占쌘쏙옙占쏙옙占실쏙옙 占쏙옙회占쏙옙占쏙옙 : 占쏙옙占쏙옙占쏙옙 占싯삼옙占싫듸옙, 占쏙옙占쏙옙~占쏙옙占신뤄옙
	// 占싯삼옙占쏙옙占쏙옙,占썩본占쏙옙占쏙옙 占쏙옙占쏙옙
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	int yesday = Integer.parseInt(sdf.format(date)) - 1;
	String td = Integer.toString(yesday);

	// board
	int size = 1024 * 1024 * 10;
	String uploadPath = "C:/workspace/test0520/WebContent/upload/";
	String encoding = "utf-8";
	MultipartRequest multi = null;
	PageVo pVo;
	/////////////////////////

	InputVo ivo; // 占쏙옙화api(占쏙옙화占쏙옙占쏙옙,daum)占쏙옙 占싯삼옙占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쌔억옙 占쏙옙
					// 占쏙옙占쏙옙占쏙옙
	OutputVo ovo; // 占쏙옙화api(占쏙옙화占쏙옙占쏙옙,daum)占쏙옙占쏙옙 占쌨아울옙占쏙옙 占쏙옙占쏙옙占쏙옙
	MemberVo mvo; // facebook占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 회占쏙옙占쏙옙占쏙옙
	Connection conn;
	List<OutputVo> r_list; // 占쌘쏙옙占쏙옙占실쏙옙占쏙옙 占쏙옙占쏙옙占� 占쏙옙화占쏙옙占쏙옙트
	List<OutputVo> c_list; // 占쌍근븝옙占쏙옙화占쏙옙 占쏙옙占쏙옙占� 占쏙옙화占쏙옙占쏙옙트
	List<OutputVo> n_list; // 占쏙옙占쌜소게울옙 占쏙옙占쏙옙占� 占쏙옙화占쏙옙占쏙옙트

	String filename = "";

	public WaddaDao() {
		conn = new DBConnect().getConn();
	}

	public MemberVo memberCheck(HttpServletRequest req) {
		MemberVo vo = new MemberVo();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = null;
		try {
			sql = "select * from w_member where id = ? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, req.getParameter("id"));
			rs = ps.executeQuery();

			if (!rs.next()) {
				sql = "insert into w_member(id, name, email, gender, age_range, join_date) "
						+ " values(?, ?, ?, ?, 0, sysdate)";

				ps = conn.prepareStatement(sql);
				ps.setString(1, req.getParameter("id"));
				ps.setString(2, req.getParameter("name"));
				ps.setString(3, req.getParameter("email"));
				ps.setString(4, req.getParameter("gender"));

				ps.executeUpdate();
			}
			vo.setId(req.getParameter("id"));
			vo.setName(req.getParameter("name"));

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try{rs.close(); } catch(Exception res) {}
			try{ps.close(); } catch(Exception pse) {}
		}
		return vo;
	}

	public List<OutputVo> rank(HttpServletRequest req) {

		String targetDt = td;
		String itemPerPage = "10";
		String multiMovieYn = "";
		String repNationCd = "";
		String wideAreaCd = "";

		// 占쌩깍옙키
		String key = "a3c892ccc795551de48b778e2bf1fdc2";
		// KOBIS 占쏙옙占쏙옙 API Rest Client占쏙옙 占쏙옙占쏙옙 호占쏙옙
		KobisOpenAPIRestService service = new KobisOpenAPIRestService(key);

		HashMap<String, Object> dailyResult = new HashMap<String, Object>();

		try {
			String dailyResponse;

			dailyResponse = service.getDailyBoxOffice(true, targetDt, itemPerPage, multiMovieYn, repNationCd,
					wideAreaCd);
			ObjectMapper mapper = new ObjectMapper();
			dailyResult = mapper.readValue(dailyResponse, HashMap.class);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("test", dailyResult);

			JSONParser jsonParser = new JSONParser();
			JSONObject json1 = new JSONObject();
			json1.putAll((Map) jsonObject.get("test"));
			json1.putAll((Map) json1.get("boxOfficeResult"));

			String str = json1.toString();
			JSONObject json2 = (JSONObject) jsonParser.parse(str);
			JSONArray jsonArr = (JSONArray) json2.get("dailyBoxOfficeList");

			r_list = new ArrayList<OutputVo>();

			for (int i = 0; i < jsonArr.size(); i++) {
				JSONObject obj = (JSONObject) jsonArr.get(i);

				String movieNm = (String) obj.get("movieNm");

				OutputVo rovo = new OutputVo();

				rovo = movie_selectOne(movieNm);

				rovo.setR_boxofficeType((String) obj.get("boxofficeType"));
				rovo.setR_rnum((String) obj.get("rnum"));
				rovo.setR_rank((String) obj.get("rank"));
				rovo.setR_rankInten((String) obj.get("rankInten"));
				rovo.setR_rankOldAndNew((String) obj.get("rankOldAndNew"));
				rovo.setR_movieCd((String) obj.get("movieCd"));
				rovo.setR_movieNm((String) obj.get("movieNm"));
				rovo.setR_openDt((String) obj.get("openDt"));
				rovo.setR_salesAmt((String) obj.get("salesAmt"));
				rovo.setR_salesShare((String) obj.get("salesShare"));
				rovo.setR_salesInten((String) obj.get("salesInten"));
				rovo.setR_audiChange((String) obj.get("salesChange"));
				rovo.setR_salesAcc((String) obj.get("salesAcc"));
				rovo.setR_audiCnt((String) obj.get("audiCnt"));
				rovo.setR_audiInten((String) obj.get("audiInten"));
				rovo.setR_audiChange((String) obj.get("audiChange"));
				rovo.setR_audiAcc((String) obj.get("audiAcc"));
				rovo.setR_scrnCnt((String) obj.get("scrnCnt"));
				rovo.setR_showCnt((String) obj.get("showCnt"));

				System.out.println("3");
				System.out.println(rovo.getPoster());
				System.out.println(rovo.getGenre());
				r_list.add(rovo);
			}

			// KOBIS 占쏙옙占쏙옙 API Rest Client占쏙옙 占쏙옙占쏙옙 占쌘듸옙 占쏙옙占쏙옙 호占쏙옙(boolean
			// isJson, Stirng
			// comCode)
			String codeResponse = service.getComCodeList(true, "0105000000");
			HashMap<String, Object> codeRsult = mapper.readValue(codeResponse, HashMap.class);
			req.setAttribute("codeResult", codeRsult);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return r_list;
	}

	public OutputVo movie_selectOne(String movieNm) {
		ovo = new OutputVo();

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = null;

		try {
			sql = "select * from movie where movie_name = ?";

			ps = conn.prepareStatement(sql);
			ps.setString(1, movieNm);

			rs = ps.executeQuery();

			if (rs.next()) {
				
				ovo.setR_movieNm(movieNm);
				ovo.setSerial(rs.getInt("serial"));
				ovo.setGenre(rs.getString("genre"));
				ovo.setMovie_name(rs.getString("movie_name"));
				ovo.setOpen_year(rs.getString("open_year"));
				ovo.setWatch_age(rs.getString("watch_age"));
				ovo.setPlay_time(rs.getString("play_time"));
				ovo.setNation(rs.getString("nation"));
				ovo.setDirector(rs.getString("director"));
				ovo.setDirector_photo(rs.getString("director_photo"));
				ovo.setActor(rs.getString("actor"));
				ovo.setActor_part(rs.getString("actor_part"));
				ovo.setActor_photo(rs.getString("actor_photo"));
				ovo.setActor2(rs.getString("actor2"));
				ovo.setActor2_part(rs.getString("actor2_part"));
				ovo.setActor2_photo(rs.getString("actor2_photo"));
				ovo.setSurpporter(rs.getString("surpporter"));
				ovo.setSurpporter_part(rs.getString("surpporter_part"));
				ovo.setSurpporter_photo(rs.getString("surpporter_photo"));
				ovo.setSurpporter2(rs.getString("surpporter2"));
				ovo.setSurpporter2_part(rs.getString("surpporter2_part"));
				ovo.setSurpporter2_photo(rs.getString("surpporter2_photo"));
				ovo.setSurpporter3(rs.getString("surpporter3"));
				ovo.setSurpporter3_part(rs.getString("surpporter3_part"));
				ovo.setSurpporter3_photo(rs.getString("surpporter3_photo"));
				ovo.setPoster(rs.getString("poster"));
				ovo.setSlide_photo(rs.getString("slide_photo"));
				ovo.setSlide_photo2(rs.getString("slide_photo2"));
				ovo.setSlide_photo3(rs.getString("slide_photo3"));
				ovo.setPreview_url(rs.getString("preview_url"));
				ovo.setSimilar_movie_photo(rs.getString("similar_movie_photo"));
				ovo.setStory(rs.getString("story"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{rs.close(); } catch(Exception res) {}
			try{ps.close(); } catch(Exception pse) {}
		}
		return ovo;
	}
	
	 public List<DetailPageVo> detail_comment_del(HttpServletRequest req){
	      List<DetailPageVo> list = new ArrayList<DetailPageVo>();
	      DetailPageVo dVo = new DetailPageVo();
	      PreparedStatement ps = null;
	      int r = 1;
	      String sql = "";
	      String detail_cnum = req.getParameter("detail_cnum");
	      
	      try{
	         sql = " delete from star where detail_cnum = ? ";
	         ps = conn.prepareStatement(sql);
	         ps.setString(1, detail_cnum);
	         ps.executeQuery();
	         
	      }catch (Exception ex) {
	         r=-1;
	         ex.printStackTrace();
	      }
	      dVo.setDetail_cnum(detail_cnum);
	      return list;
	   }
	

	
	
//	public OutputVo detail_page(String select_movileNm) {
//		String targetDt = td;
//		String itemPerPage = "10";
//		String multiMovieYn = "";
//		String repNationCd = "";
//		String wideAreaCd = "";
//
//		String key = "ebda7a1f95e36ddc5942aa63aeeeb18c";
//		KobisOpenAPIRestService service = new KobisOpenAPIRestService(key);
//
//		HashMap<String, Object> dailyResult = new HashMap<String, Object>();
//
//		try {
//			String dailyResponse;
//
//			dailyResponse = service.getDailyBoxOffice(true, targetDt, itemPerPage, multiMovieYn, repNationCd,
//					wideAreaCd);
//			ObjectMapper mapper = new ObjectMapper();
//			dailyResult = mapper.readValue(dailyResponse, HashMap.class);
//
//			JSONObject jsonObject = new JSONObject();
//			jsonObject.put("test", dailyResult);
//
//			JSONParser jsonParser = new JSONParser();
//			JSONObject json1 = new JSONObject();
//			json1.putAll((Map) jsonObject.get("test"));
//			json1.putAll((Map) json1.get("boxOfficeResult"));
//
//			String str = json1.toString();
//			JSONObject json2 = (JSONObject) jsonParser.parse(str);
//			JSONArray jsonArr = (JSONArray) json2.get("dailyBoxOfficeList");
//
//			r_list = new ArrayList<OutputVo>();
//
//			for (int i = 0; i < jsonArr.size(); i++) {
//
//				JSONObject obj = (JSONObject) jsonArr.get(i);
//				if (select_movileNm.equals((String) obj.get("movieNm"))) {
//					ovo = movie_selectOne((String) obj.get("movieNm"));
//					
//					ovo.setR_boxofficeType((String) obj.get("boxofficeType"));
//					ovo.setR_rnum((String) obj.get("rnum"));
//					ovo.setR_rank((String) obj.get("rank"));
//					ovo.setR_rankInten((String) obj.get("rankInten"));
//					ovo.setR_rankOldAndNew((String) obj.get("rankOldAndNew"));
//					ovo.setR_movieCd((String) obj.get("movieCd"));
//					ovo.setR_movieNm((String) obj.get("movieNm"));
//					ovo.setR_openDt((String) obj.get("openDt"));
//					ovo.setR_salesAmt((String) obj.get("salesAmt"));
//					ovo.setR_salesShare((String) obj.get("salesShare"));
//					ovo.setR_salesInten((String) obj.get("salesInten"));
//					ovo.setR_audiChange((String) obj.get("salesChange"));
//					ovo.setR_salesAcc((String) obj.get("salesAcc"));
//					ovo.setR_audiCnt((String) obj.get("audiCnt"));
//					ovo.setR_audiInten((String) obj.get("audiInten"));
//					ovo.setR_audiChange((String) obj.get("audiChange"));
//					ovo.setR_audiAcc((String) obj.get("audiAcc"));
//					ovo.setR_scrnCnt((String) obj.get("scrnCnt"));
//					ovo.setR_showCnt((String) obj.get("showCnt"));
//				}
//			}
//			String codeResponse = service.getComCodeList(true, "0105000000");
//			HashMap<String, Object> codeRsult = mapper.readValue(codeResponse, HashMap.class);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//		}
//		return ovo;
//	}

	public List<OutputVo> lastviewmoive(HttpServletRequest req) {
		c_list = new ArrayList<OutputVo>();
		ovo = new OutputVo();
		String id = (String)req.getSession(false).getAttribute("loginVo");

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = null;

		try {
			sql = "select * from star where id = ? order by star_date desc";

			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			
			rs = ps.executeQuery();

			while (rs.next()) {
				ovo = movie_selectOne((String)rs.getString("movienm"));
				c_list.add(ovo);
				System.out.println(ovo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{rs.close(); } catch(Exception res) {}
			try{ps.close(); } catch(Exception pse) {}
		}
		return c_list;
	}

	public List<OutputVo> newMovie(HttpServletRequest req) {
		
		n_list = rank(req);

//		for (int i = 0; i < n_list.size() - 1; i++) {
//			for (int j = 1; j < n_list.size() - i; j++) {
//				
//				
//				int aa = intparse(n_list.get(j - 1).getOpen_year());
//				int bb = intparse(n_list.get(j).getOpen_year());
//
//				OutputVo temp = new OutputVo();
//				if (aa<bb) {
//
//					temp = n_list.get(j);
//					n_list.set(j, n_list.get(j + 1));
//					n_list.set(j + 1, temp);
//				}
//			}

//		}
		return n_list;
	}
	
	public int intparse(String date) {
		int result = 0;
		
		String[] date1 = date.split(".");
		
		String r = "";
		
		for(String v : date1) {
			r += v;
			
		}
		System.out.println("~~~~~~~~");
		System.out.println(r);
//		result = Integer.parseInt(r);
//		System.out.println(result);
		
		return result;
		
	}

	// board
	public List<ReplyVo> replydel(HttpServletRequest req) {
		List<ReplyVo> list = new ArrayList<ReplyVo>();
		ReplyVo rvo = new ReplyVo();

		PreparedStatement ps = null;
		int r = 1;
		String sql = "";
		String comment_serial = req.getParameter("comment_serial");

		try {
			sql = " delete from board_comment where comment_serial = ? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, comment_serial);
			ps.executeQuery();

		} catch (Exception ex) {
			r = -1;
			ex.printStackTrace();
		}

		rvo.setComment_serial(comment_serial);

		list = replyList(req);

		return list;
	}

	public ReplyVo selectReply(ReplyVo rvo) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		ReplyVo rv = new ReplyVo();

		try {
			sql = " select * from board_comment where comment_serial = ? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, rvo.getComment_serial());
			rs = ps.executeQuery();

			if (rs.next()) {
				rv.setComment_content(rs.getString("comment_content"));
				rv.setComment_date(rs.getString("comment_date"));
				rv.setComment_id(rs.getString("comment_id"));
				rv.setComment_num(rs.getInt("comment_num"));
				rv.setComment_parent(rs.getInt("comment_parent"));
				rv.setComment_pwd(rs.getString("comment_pwd"));
				rv.setComment_serial(rs.getString("comment_serial"));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try{rs.close(); } catch(Exception res) {}
			try{ps.close(); } catch(Exception pse) {}
		}

		return rv;

	}

	public List<ReplyVo> replyinput(HttpServletRequest req) {
		List<ReplyVo> list = new ArrayList<ReplyVo>();
		ReplyVo rvo = new ReplyVo();

		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		int rs = -1;

		
		String comment_id = (String)req.getSession(false).getAttribute("loginVo");
		
	
		String comment_pwd = req.getParameter("commentParentPassword"); // 占쎈솭占쎈뮞占쎌뜖占쎈굡
		/*String comment_id = req.getParameter("commentParentName"); // 占쎈툡占쎌뵠占쎈탵
*/		int comment_parent = Integer.parseInt(req.getParameter("serial")); // 疫뀐옙
																			// 甕곕뜇�깈
		String comment_content = req.getParameter("commentParentText"); // 疫뀐옙占쎄땀占쎌뒠
		/*System.out.println(comment_content);*/

		// 占쎈솊疫뀐옙 占쏙옙占쎌삢
		sql = "insert into board_comment(comment_num, comment_pwd, comment_id, comment_date, comment_parent,comment_content,comment_serial )"
				+ " values(seq_comment_num.nextval, ?, ?, sysdate, ?, ?,?|| '-' || seq_comment_num.currval ) ";

		try {

			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, "1111");
			ps.setString(2, comment_id);
			ps.setInt(3, comment_parent);
			ps.setString(4, comment_content);
			ps.setInt(5, comment_parent);

			rs = ps.executeUpdate();

			list = replyList(req);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try{ps.close(); } catch(Exception pse) {}
		}
		return list;
	}

	public int insert(HttpServletRequest req) {
		List<String> attFiles = new ArrayList<String>();
		List<String> oriAttFiles = new ArrayList<String>();

		int rs = 0; // 占쎌젟占쎄쉐占쎌읅占쎌몵嚥∽옙 占쏙옙占쎌삢占쎈쭆 野껋럩�뒭 0癰귣��뼄 占쎄쿃揶쏉옙.
		PreparedStatement ps = null;
		String sql = null;
		String filename = "";
		String name = (String)req.getSession(false).getAttribute("name");
	
		try {
			// 占쎈솁占쎌뵬 占쎈씜嚥≪뮆諭� 筌ｌ꼶�봺
			multi = new MultipartRequest(req, uploadPath, size, encoding, new DefaultFileRenamePolicy());

			Enumeration<String> files = multi.getFileNames();
			while (files.hasMoreElements()) {
				String file1 = files.nextElement();
				attFiles.add(multi.getFilesystemName(file1));
				oriAttFiles.add(multi.getOriginalFileName(file1));
				filename = multi.getFilesystemName(file1);
			}

			// board 占쎈�믭옙�뵠�뇡遺용퓠 占쎄땀占쎌뒠 占쏙옙占쎌삢
			sql = "insert into board(serial, mdate, worker, subject, content, hit, grp, deep) "
					+ " values(seq_board_serial.nextval, sysdate, ?, ?, ?, 0 , "
					+ " seq_board_serial.currval, seq_board_serial.currval) ";

			ps = conn.prepareStatement(sql);
			ps.setString(1,name);
			ps.setString(2, multi.getParameter("subject"));
			ps.setString(3, multi.getParameter("content"));

			rs = ps.executeUpdate();

			// board_att 占쎈�믭옙�뵠�뇡遺용퓠 筌ｂ뫀占� 占쎈솁占쎌뵬 占쏙옙占쎌삢
			for (int i = 0; i < attFiles.size(); i++) {
				if (attFiles.get(i) == null)
					continue;
				sql = "insert into board_att(serial, pserial, attfile, oriattfile)"
						+ " values(seq_board_att_serial.nextval, seq_board_serial.currval, ?,?)";

				ps = conn.prepareStatement(sql);
				ps.setString(1, attFiles.get(i));
				ps.setString(2, oriAttFiles.get(i));

				rs = ps.executeUpdate();
			}

			// 占쎄퐶占쎄퐬占쎌뵬 筌띾슢諭얏묾占�
			makeThumb(attFiles);

		} catch (Exception ex) {
			rs = -1;
			ex.printStackTrace();
		} finally {
			try{ps.close(); } catch(Exception pse) {}
		}
		return rs;
	}

	public void makeThumb(List<String> list) {

		File file = null;
		ParameterBlock pb = null;
		BufferedImage thumb = null;
		RenderedOp rop = null;
		Graphics2D g = null;
		BufferedImage buff = null;

		for (String f : list) {
			if (f == null)
				continue;

			pb = new ParameterBlock();
			pb.add(uploadPath + f);
			rop = JAI.create("fileload", pb);

			buff = rop.getAsBufferedImage();
			thumb = new BufferedImage(80, 60, BufferedImage.TYPE_INT_BGR);

			g = thumb.createGraphics();
			g.drawImage(buff, 0, 0, 80, 60, null);

			file = new File(uploadPath + "sm_" + f);
			try {
				ImageIO.write(thumb, "jpg", file);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
			}
		}

	}

	public void pagaCompute(BoardVo v) {
		pVo = new PageVo();

		int totList = 0; // �뵳�딅뮞占쎈뱜 占쎌읈筌ｏ옙 揶쏉옙占쎈땾
		int totPage = 0; // 占쎌읈筌ｏ옙 占쎈읂占쎌뵠筌욑옙占쎈땾
		int totBlock = 0; // 占쎌읈筌ｏ옙 �뇡遺얠쑏占쎈땾

		int nowBlock = 1; // 占쎌겱占쎌삺 �뇡遺얠쑏

		int startNo = 0; // �뵳�딅뮞占쎈뱜 筌뤴뫖以됵옙�벥 占쎈뻻占쎌삂占쎌맄燁삼옙
		int endNo = 0; // �뵳�딅뮞占쎈뱜 筌뤴뫖以됵옙�벥 筌띾뜆占쏙쭕占� 占쎌맄燁삼옙

		int startPage = 0; // 占쎈립�뇡遺얠쑏占쎈퓠 占쎈ご占쎈뻻占쎈막 占쎈뻻占쎌삂 占쎈읂占쎌뵠筌욑옙 甕곕뜇�깈
		int endPage = 0; // 占쎈립�뇡遺얠쑏占쎈퓠 占쎈ご占쎈뻻占쎈막 筌띾뜆占쏙쭕占� 占쎈읂占쎌뵠筌욑옙 甕곕뜇�깈

		int nowPage = v.getNowPage(); // 占쎌겱占쎌삺 占쎈읂占쎌뵠筌욑옙

		PreparedStatement ps = null;
		ResultSet rs = null;
		String findStr = v.getFindStr();

		String sql = "select count(*) cnt from board where worker like ? or subject like ? or content like ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + findStr + "%");
			ps.setString(2, "%" + findStr + "%");
			ps.setString(3, "%" + findStr + "%");
			rs = ps.executeQuery();
			rs.next();

			totList = rs.getInt("cnt");

			totPage = (int) Math.ceil(totList / (pVo.getListSize() * 1.0));
			totBlock = (int) Math.ceil(totPage / (pVo.getBlockSize() * 1.0));

			nowBlock = (int) Math.ceil(nowPage / (pVo.getBlockSize() * 1.0));

			endPage = nowBlock * pVo.getBlockSize();
			startPage = endPage - pVo.getBlockSize() + 1;

			endNo = nowPage * pVo.getListSize();
			startNo = endNo - pVo.getListSize() + 1;

			if (endPage > totPage)
				endPage = totPage;
			if (endNo > totList)
				endNo = totList;

			pVo.setTotList(totList);
			pVo.setTotBlock(totBlock);
			pVo.setEndNo(endNo);
			pVo.setEndPage(endPage);
			pVo.setNowBlock(nowBlock);
			pVo.setStartNo(startNo);
			pVo.setStartPage(startPage);
			pVo.setTotPage(totPage);
			pVo.setNowPage(nowPage);

			ps.close();
			rs.close();

		} catch (Exception ex) {
		} finally {
			try{rs.close(); } catch(Exception res) {}
			try{ps.close(); } catch(Exception pse) {}
		}

	}

	public List<BoardVo> list(BoardVo v) {
		pagaCompute(v);

		PreparedStatement ps = null;
		ResultSet rs = null;
		String findStr = v.getFindStr();
		String replStr = "";

		String sql = "select * from(select rownum no, brd.* from(select * from board where worker like ? or subject like ? or content like ? "
	            + "order by grp desc, deep asc)brd ) where no between ? and ? ";

		ArrayList<BoardVo> list = new ArrayList<BoardVo>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + findStr + "%");
			ps.setString(2, "%" + findStr + "%");
			ps.setString(3, "%" + findStr + "%");
			ps.setInt(4, pVo.getStartNo());
			ps.setInt(5, pVo.getEndNo());

			rs = ps.executeQuery();

			while (rs.next()) {
				replStr = "";
				int deep = rs.getString("deep").split("-").length;
				if (deep > 1) {
					for (int i = 3; i <= deep; i++) {
						replStr += "&nbsp;&nbsp;";
					}
					replStr += "";
				}
				BoardVo vo = new BoardVo();
				vo.setSerial(rs.getInt("serial"));
				vo.setmDate(rs.getString("mdate"));
				vo.setWorker(rs.getString("worker"));
				vo.setContent(rs.getString("content"));
				vo.setSubject(replStr + rs.getString("subject"));
				vo.setHit(rs.getInt("hit"));

				list.add(vo);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try{rs.close(); } catch(Exception res) {}
			try{ps.close(); } catch(Exception pse) {}
		}
			return list;
		}

	public int delete(BoardVo v) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		BoardVo temp = selectOne(v);
		File deleteFile = null;
		String sql = "";

		int r = 1;
		try {
			// 占쎌뜚癰귣㈇占� 占쎄텣占쎌젫
			sql = "delete from board where serial =?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, v.getSerial());
			ps.executeUpdate();

			// 筌ｂ뫀占쏙옙�솁占쎌뵬占쎈굶 �겫�뜄�쑎占쎌궎疫뀐옙
			sql = "select * from board_att where pserial=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, v.getSerial());
			rs = ps.executeQuery();

			// 筌ｂ뫀占쏙옙�솁占쎌뵬 占쎄텣占쎌젫
			while (rs.next()) {
				deleteFile = new File(uploadPath + rs.getString("attfile"));
				if (deleteFile.exists()) {
					deleteFile.delete();
					deleteFile = null;
				}
				Thread.sleep(2000);

				deleteFile = new File(uploadPath + "sm_" + rs.getString("attfile"));

				if (deleteFile.exists()) {
					deleteFile.delete();
					deleteFile = null;
				}
				Thread.sleep(2000);
			}
			// 筌ｂ뫀占� 占쎈�믭옙�뵠�뇡占� 占쎄텣占쎌젫
			sql = "delete from board_att where pserial=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, v.getSerial());
			ps.executeUpdate();

		} catch (Exception ex) {
			r = -1;
			ex.printStackTrace();
		} finally {
			try{rs.close(); } catch(Exception res) {}
			try{ps.close(); } catch(Exception pse) {}
		}
		return r;
	}

	public BoardVo view(BoardVo vo) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		BoardVo v = null;

		try {
			// hit占쎈땾 筌앹빓占�
			sql = "update board set hit = hit+1 where serial=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vo.getSerial());
			ps.executeUpdate();

			v = selectOne(vo);

			v.setSerial(vo.getSerial());
			v.setFindStr(vo.getFindStr());
			v.setNowPage(vo.getNowPage());

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try{rs.close(); } catch(Exception res) {}
			try{ps.close(); } catch(Exception pse) {}
		}
		return v;
	}

	public BoardVo selectOne(BoardVo v) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		BoardVo vo = new BoardVo();
		vo.setFindStr(v.getFindStr());
		vo.setNowPage(v.getNowPage());
		vo.setSerial(v.getSerial());

		List<String> attFile = new ArrayList<String>();
		List<String> oriFile = new ArrayList<String>();

		try {
			// 占쎌맊癰귣㈇占�
			sql = "select * from board where serial = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, v.getSerial());
			rs = ps.executeQuery();

			if (rs.next()) {
				vo.setSerial(rs.getInt("serial"));
				vo.setmDate(rs.getString("mDate"));
				vo.setWorker(rs.getString("worker"));
				vo.setSubject(rs.getString("subject"));
				vo.setContent(rs.getString("content"));
				vo.setHit(rs.getInt("hit"));
				vo.setGrp(rs.getInt("grp"));
				vo.setDeep(rs.getString("deep"));

				// 筌ｂ뫀占쏙옙�솁占쎌뵬
				sql = "select attfile, oriattfile from board_att where pserial=?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, vo.getSerial());

				ResultSet rs2 = ps.executeQuery();
				while (rs2.next()) {
					attFile.add(rs2.getString("attfile"));
					oriFile.add(rs2.getString("oriattfile"));
				}
				vo.setAttfile(attFile);
				vo.setOriAttfile(oriFile);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try{rs.close(); } catch(Exception res) {}
			try{ps.close(); } catch(Exception pse) {}
		}
		return vo;
	}

	public BoardVo update(HttpServletRequest req) {
		List<String> attFiles = new ArrayList<String>();
		List<String> oriAttFiles = new ArrayList<String>();

		PreparedStatement ps = null;
		int r = 0;
		String sql = "";
		MultipartRequest multi = null;
		BoardVo vo = null;
		BoardVo tempVo = null;
		String tempFile = null;
		String file1 = null;
		String file2 = null;
		String oriFile1 = null;
		String oriFile2 = null;
		String[] delFile = null;

		try {
			// 筌ｂ뫀占쏙옙�솁占쎌뵬
			multi = new MultipartRequest(req, uploadPath, size, encoding, new DefaultFileRenamePolicy());

			Enumeration<String> files = multi.getFileNames();

			while (files.hasMoreElements()) {
				tempFile = files.nextElement();
				attFiles.add(multi.getFilesystemName(tempFile));
				oriAttFiles.add(multi.getOriginalFileName(tempFile));
			}

			// 占쎌뜚癰귨옙 占쎈땾占쎌젟
			sql = "update board set subject=? , content=? where serial=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, multi.getParameter("subject"));
			ps.setString(2, multi.getParameter("content"));
			ps.setString(3, multi.getParameter("serial"));
			r = ps.executeUpdate();

			// board_att 占쎈�믭옙�뵠�뇡遺용퓠 筌ｂ뫀占� 占쎈솁占쎌뵬 占쏙옙占쎌삢
			for (int i = 0; i < attFiles.size(); i++) {
				if (attFiles.get(i) == null)
					continue;
				sql = "insert into board_att(serial, pserial, attfile, oriattfile)"
						+ " values(seq_board_att_serial.nextval, ?, ?,?)";

				ps = conn.prepareStatement(sql);
				ps.setString(1, multi.getParameter("serial"));
				ps.setString(2, attFiles.get(i));
				ps.setString(3, oriAttFiles.get(i));

				r = ps.executeUpdate();
			}

			// 占쎄텣占쎌젫占쎈쭆 筌ｂ뫀占쏙옙�솁占쎌뵬 筌ｌ꼶�봺
			if (multi.getParameterValues("deleteFile") != null) {
				delFile = multi.getParameterValues("deleteFile");
				for (int i = 0; i < delFile.length; i++) {
					sql = "delete from board_att where attfile=? ";
					ps = conn.prepareStatement(sql);
					ps.setString(1, delFile[i]);
					ps.executeUpdate();

					File f = new File("../../upload/" + delFile[i]);
					if (f.exists())
						f.delete();
					Thread.sleep(500);

					f = new File("../../upload/sm_" + delFile[i]);
					if (f.exists())
						f.delete();
				}
			}

			vo = new BoardVo();
			vo.setSerial(Integer.parseInt(multi.getParameter("serial")));
			vo.setFindStr(multi.getParameter("findStr"));
			vo.setNowPage(Integer.parseInt(multi.getParameter("nowPage")));
		} catch (Exception ex) {
			vo = null;
			ex.printStackTrace();
		} finally {
			try{ps.close(); } catch(Exception pse) {}
		}
		return vo;
	}

	public BoardVo repl(HttpServletRequest req) {
		List<String> attFiles = new ArrayList<String>();
		List<String> oriAttFiles = new ArrayList<String>();

		PreparedStatement ps = null;
		String sql = null;

		BoardVo vo = new BoardVo();
		int np = 1;
		String msg = "";

		MultipartRequest multi = null;

		try {
			multi = new MultipartRequest(req, uploadPath, size, encoding, new DefaultFileRenamePolicy());

			// 筌ｂ뫀占쏙옙�솁占쎌뵬
			Enumeration<String> files = multi.getFileNames();

			while (files.hasMoreElements()) {
				String file1 = files.nextElement();
				attFiles.add(multi.getFilesystemName(file1));
				oriAttFiles.add(multi.getOriginalFileName(file1));
			}

			// 占쎄땀占쎌뒠 占쏙옙占쎌삢
			sql = "insert into board(serial, mdate, worker, subject, " + " content, hit, grp, deep)"
					+ " values(seq_board_serial.nextval, sysdate, ?, ?, ?, 0, ?, "
					+ " ? || '-' || seq_board_serial.currval)";

			ps = conn.prepareStatement(sql);
			ps.setString(1, multi.getParameter("worker"));
			ps.setString(2, multi.getParameter("subject"));
			ps.setString(3, multi.getParameter("content"));
			ps.setString(4, multi.getParameter("grp"));
			ps.setString(5, multi.getParameter("deep"));

			ps.executeUpdate();

			// board_att 占쎈�믭옙�뵠�뇡遺용퓠 筌ｂ뫀占� 占쎈솁占쎌뵬 占쏙옙占쎌삢
			for (int i = 0; i < attFiles.size(); i++) {
				if (attFiles.get(i) == null)
					continue;
				sql = "insert into board_att(serial, pserial, attfile, oriattfile)"
						+ " values(seq_board_att_serial.nextval, seq_board_serial.currval, ?,?)";

				ps = conn.prepareStatement(sql);
				ps.setString(1, attFiles.get(i));
				ps.setString(2, oriAttFiles.get(i));

				ps.executeUpdate();

			}
			vo.setFindStr(multi.getParameter("findStr"));
			vo.setNowPage(Integer.parseInt(multi.getParameter("nowPage")));

		} catch (Exception ex) {
			vo = null;
			ex.printStackTrace();
		} finally {
			try{ps.close(); } catch(Exception pse) {}
		}
		return vo;
	}

	public List<ReplyVo> replyList(HttpServletRequest req) {
		List<ReplyVo> list = new ArrayList<ReplyVo>();

		ResultSet rs;

		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;

		int comment_parent = Integer.parseInt(req.getParameter("serial"));
		sql = " select * from board_comment where comment_parent = ? ORDER by comment_num asc ";

		try {
			conn = new DBConnect().getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, comment_parent);
			rs = ps.executeQuery();

			while (rs.next()) {

				ReplyVo rvo = new ReplyVo();
				rvo.setComment_content(rs.getString("comment_content"));
				rvo.setComment_date(rs.getString("comment_date"));
				rvo.setComment_id(rs.getString("comment_id"));
				rvo.setComment_num(rs.getInt("comment_num"));
				rvo.setComment_parent(rs.getInt("comment_parent"));
				rvo.setComment_pwd(rs.getString("comment_pwd"));
				rvo.setComment_serial(rs.getString("comment_serial"));
				list.add(rvo);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{ps.close(); } catch(Exception pse) {}
		}

		return list;
	}

	public PageVo getpVo() {
		return pVo;
	}

	public int insert_detail_page_comment(HttpServletRequest req, String id) {
	      String name = (String)req.getSession(false).getAttribute("name");
	      String gender = (String)req.getSession(false).getAttribute("gender");
	      String gimg = "";

	      int rs = 0; // 占쎌젟占쎄쉐占쎌읅占쎌몵嚥∽옙 占쏙옙占쎌삢占쎈쭆 野껋럩�뒭 0癰귣��뼄 占쎄쿃揶쏉옙.
	      PreparedStatement ps = null;
	      String sql = null;

	      String movieNm = (String) req.getParameter("movieNm");
	      int star_input = 5 * Integer.parseInt(req.getParameter("star_input"));
	      String content = (String) req.getParameter("content");
	      
	      if(gender.equals("male")) gimg = "../img/user_m.png";
	      else gimg = "../img/user_w.png";
	      
	      
	      try {

	         
	         
	         sql = "insert into star(id, movieNm, star_point, reply, star_date,comment_name, detail_cnum, gender ) " + " values(?, ?, ?, ?, sysdate,?,?||'-'||?, ?) ";

	         ps = conn.prepareStatement(sql);
	         ps.setString(1, id);
	         ps.setString(2, movieNm);
	         ps.setInt(3, star_input);
	         ps.setString(4, content);
	         ps.setString(5, name);
	         ps.setString(6, id);
	         ps.setString(7, movieNm);
	         ps.setString(8, gimg);

	         ps.executeUpdate();
	         
	         
	         

	      } catch (Exception ex) {
	         rs = -1;
	         ex.printStackTrace();
	      } finally {
	         try{ps.close(); } catch(Exception pse) {}
	      }
	      
	      return rs;
	   }

	public List<DetailPageVo> list_detail_page_comment(String movieNm) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "select * from star where movieNm = ? order by star_date desc ";

		ArrayList<DetailPageVo> list = new ArrayList<DetailPageVo>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, movieNm);

			rs = ps.executeQuery();

			while (rs.next()) {
				DetailPageVo vo = new DetailPageVo();
				vo.setId(rs.getString("id"));
				vo.setMovieNm(rs.getString("movieNm"));
				vo.setStar_point(rs.getInt("star_point"));
				vo.setReply(rs.getString("reply"));
				vo.setStar_date(rs.getString("star_date"));
				vo.setComment_name(rs.getString("comment_name"));
				vo.setDetail_cnum(rs.getString("detail_cnum"));
				vo.setGender(rs.getString("gender"));
				list.add(vo);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try{rs.close(); } catch(Exception res) {}
			try{ps.close(); } catch(Exception pse) {}
		}
		return list;
	}

	public double avgStar(String movieNm) {
		double avgStar = 0;
		int sumStar = 0;
		int cnt = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "select star_point from star where movieNm = ?";

		ArrayList<DetailPageVo> list = new ArrayList<DetailPageVo>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, movieNm);
			rs = ps.executeQuery();

			while (rs.next()) {
				sumStar += (rs.getInt("star_point"));
				cnt++;
				System.out.println("sumStar : " + sumStar);
			}
			avgStar = (double) sumStar / (cnt);
			System.out.println("avgStar : " + avgStar);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try{rs.close(); } catch(Exception res) {}
			try{ps.close(); } catch(Exception pse) {}
		}
		return avgStar;

	}
	
	public List<OutputVo> genreList(HttpServletRequest req) { 
		List<OutputVo> g_list = new ArrayList<OutputVo>();
		
		String gg = (String) req.getParameter("genre");
		System.out.println(gg);
		String genre = "%" + gg + "%";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		System.out.println("3번이에여");

		String sql = "select * from movie where genre like ?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, genre);
			rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println("4번이에여");
				OutputVo govo = new OutputVo();
				
				String movie_name = (String) rs.getString("movie_name");
				govo = movie_selectOne(movie_name);
				double avgStar = avgStar(movie_name); 
				govo.setAvgStar(avgStar);
				
				g_list.add(govo);
			}

			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try{rs.close(); } catch(Exception res) {}
			try{ps.close(); } catch(Exception pse) {}
		}
		
		
		return g_list;
	}

	//////////////////////// 영화추천 recommend_page////////////////////////
/*	public List<OutputVo> recococo(String id){
		List<OutputVo> reco_list = new ArrayList<OutputVo>();
		
		
		//20대여성 추천영화

		셀렉트 *
		프롬 무비
		웨어 영화제목=(
		셀렉트 멤버.영화제목
		프롬 스타
		조인 멤버
		온 스타.아이디 = 멤버.아이디
		웨어 멤버.나이 =이십, 멤버.성별 여자 스타
		오더바이 스타포인트 desc)
		
		
		return reco_list;
	}*/
	public List<OutputVo> recommend(String id){
		List<OutputVo> reco_list = new ArrayList<OutputVo>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from star where id = ? and rownum <=3  order by star_point desc";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				OutputVo ovo = new OutputVo();
				ovo= movie_selectOne(rs.getString("movie_name"));
				reco_list.add(ovo);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try{rs.close(); } catch(Exception res) {}
			try{ps.close(); } catch(Exception pse) {}
		}
		return reco_list;
}
	//감독
	public List<OutputVo> reco_director_list(String id){
		
		
			List<OutputVo> reco_director_list = new ArrayList<OutputVo>();
			List<OutputVo> reco_list = new ArrayList<OutputVo>();
			reco_list = recommend(id);
			for(int i =0; i<3; i++){
				PreparedStatement ps = null;
				ResultSet rs = null;
				
				String sql = "select * from movie where director = ? and rownum <="+(3-i);
				
				try {
						ps = conn.prepareStatement(sql);
						ps.setString(1, reco_list.get(i).getDirector());
						rs = ps.executeQuery();
	
					while (rs.next()) {
						OutputVo ovo = new OutputVo();
						ovo= movie_selectOne(rs.getString("movieNm"));
						reco_director_list.add(ovo);
					}
	
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					try{rs.close(); } catch(Exception res) {}
					try{ps.close(); } catch(Exception pse) {}
				}
			}
			
			return reco_director_list;
	}
	//배우
	public List<OutputVo> reco_actor_list(String id){
		
		
		List<OutputVo> reco_actor_list = new ArrayList<OutputVo>();
		List<OutputVo> reco_list = new ArrayList<OutputVo>();
		reco_list = recommend(id);
		for(int i =0; i<3; i++){
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			String sql = "select * from movie where actor = ? or actor = ? or actor2 = ? or actor2 = ? and rownum <="+(3-i);
			
			try {
					ps = conn.prepareStatement(sql);
					ps.setString(1, reco_list.get(i).getActor());
					ps.setString(2, reco_list.get(i).getActor2());
					ps.setString(3, reco_list.get(i).getActor());
					ps.setString(4, reco_list.get(i).getActor2());
					rs = ps.executeQuery();

				while (rs.next()) {
					OutputVo ovo = new OutputVo();
					ovo= movie_selectOne(rs.getString("movieNm"));
					reco_actor_list.add(ovo);
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				try{rs.close(); } catch(Exception res) {}
				try{ps.close(); } catch(Exception pse) {}
			}
		}
		
		return reco_actor_list;
	}
	//장르
	public List<OutputVo> reco_genre_list(String id){
		
		
		List<OutputVo> reco_genre_list = new ArrayList<OutputVo>();
		List<OutputVo> reco_list = new ArrayList<OutputVo>();
		reco_list = recommend(id);
		System.out.println("ffffff" + reco_list.size());
		System.out.println(reco_list.get(0).getGenre());
		//for(int i =0; i<reco_list.size(); i++){
			
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			String sql = "select * from movie where genre like ? and rownum <=3";
			System.out.println();
			try {
					ps = conn.prepareStatement(sql);
					ps.setString(1, "%" + "드라마" + "%");
					rs = ps.executeQuery();

				while (rs.next()) {
					OutputVo ovo = new OutputVo();
					System.out.println("----------------" + rs.getString("movie_name"));
					ovo = movie_selectOne(rs.getString("movie_name"));
					reco_genre_list.add(ovo);
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				try{rs.close(); } catch(Exception res) {}
				try{ps.close(); } catch(Exception pse) {}
			}
		//}
		
		return reco_genre_list;
	}
	
	

	// mainSearch

	public List<OutputVo> mainSearch(String findStr) {
		List<OutputVo> list = new ArrayList<OutputVo>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from movie where movie_name like ? or actor like ? or actor2 like ? or surpporter like ? or surpporter2 like ? or surpporter3 like ? or genre like ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,"%"+ findStr + "%");
			ps.setString(2,"%"+ findStr + "%");
			ps.setString(3,"%"+ findStr + "%");
			ps.setString(4,"%"+ findStr + "%");
			ps.setString(5,"%"+ findStr + "%");
			ps.setString(6,"%"+ findStr + "%");
			ps.setString(7,"%"+ findStr + "%");
			rs = ps.executeQuery();
			
			while(rs.next()){
			
				
				OutputVo vo = new OutputVo();
				vo.setSerial(rs.getInt("serial"));
				vo.setGenre(rs.getString("genre"));
				vo.setMovie_name(rs.getString("movie_name"));
				vo.setOpen_year(rs.getString("open_year"));
				vo.setWatch_age(rs.getString("watch_age"));
				vo.setPlay_time(rs.getString("play_time"));
				vo.setNation(rs.getString("nation"));
				vo.setDirector(rs.getString("director"));
				vo.setDirector_photo(rs.getString("director_photo"));
				vo.setActor(rs.getString("actor"));
				vo.setActor_part(rs.getString("actor_part"));
				vo.setActor_photo(rs.getString("actor_photo"));
				vo.setActor2(rs.getString("actor2"));
				vo.setActor2_part(rs.getString("actor2_part"));
				vo.setActor2_photo(rs.getString("actor2_photo"));
				vo.setSurpporter(rs.getString("surpporter"));
				vo.setSurpporter_part(rs.getString("surpporter_part"));
				vo.setSurpporter_photo(rs.getString("surpporter_photo"));
				vo.setSurpporter2(rs.getString("surpporter2"));
				vo.setSurpporter2_part(rs.getString("surpporter2_part"));
				vo.setSurpporter2_photo(rs.getString("surpporter2_photo"));
				vo.setSurpporter3(rs.getString("surpporter3"));
				vo.setSurpporter3_part(rs.getString("surpporter3_part"));
				vo.setSurpporter3_photo(rs.getString("surpporter3_photo"));
				vo.setPoster(rs.getString("poster"));
				vo.setSlide_photo(rs.getString("slide_photo"));
				vo.setSlide_photo2(rs.getString("slide_photo2"));
				vo.setSlide_photo3(rs.getString("slide_photo3"));
				vo.setPreview_url(rs.getString("preview_url"));
				vo.setSimilar_movie_photo(rs.getString("similar_movie_photo"));
				vo.setStory(rs.getString("story"));
				
				
				list.add(vo);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return list;
	}
	
	public GenreCountVo genreCount(HttpServletRequest req) {
	      GenreCountVo gcvo = new GenreCountVo();
	      List<OutputVo> temp = new ArrayList<OutputVo>();
	      String id = req.getParameter("id");
	      
	      temp = myMovieList(id);
	      
	      int dramaCnt = 0;   //드라마
	      int comedyCnt = 0;   //코미디
	      int aniCnt = 0;      //애니메이션
	      int thrillCnt = 0;   //스릴러
	      int crimeCnt = 0;   //범죄
	      int sfCnt = 0;      //sf
	      int dacuCnt = 0;   //다큐멘터리
	      int actionCnt = 0;   //액션
	      int horrorCnt = 0;   // 공포
	      int rocoCnt = 0;   //로맨틱코미디
	      int romanceCnt = 0;   //로맨스
	      int fantasyCnt = 0;      //판타지
	      int familyCnt = 0;   // 가족
	      int advenCnt = 0;   //모험
	      int suspenseCnt = 0;   // 서스펜스
	      int meloCnt = 0;   //멜로
	      int musicCnt = 0;   //뮤지컬
	      
	      for(OutputVo vo : temp) {
	         if(vo.getGenre().contains("드라마")) {
	            dramaCnt++;
	            gcvo.setDramaCnt(dramaCnt);
	         }  if (vo.getGenre().contains("코미디")) {
	            comedyCnt++;
	            gcvo.setComedyCnt(comedyCnt);
	         }  if (vo.getGenre().contains("애니메이션")) {
	            aniCnt++;
	            gcvo.setAniCnt(aniCnt);
	         }  if (vo.getGenre().contains("스릴러")) {
	            thrillCnt++;
	            gcvo.setThrillCnt(thrillCnt);
	         }  if (vo.getGenre().contains("범죄")) {
	            gcvo.setCrimeCnt(crimeCnt);
	         }  if (vo.getGenre().contains("SF")) {
	            gcvo.setSfCnt(sfCnt);
	         }  if (vo.getGenre().contains("다큐멘터리")) {
	            gcvo.setDacuCnt(dacuCnt);
	         }  if (vo.getGenre().contains("액션")) {
	            actionCnt = actionCnt + 1;
	            gcvo.setActionCnt(actionCnt);
	         }  if (vo.getGenre().contains("공포")) {
	            horrorCnt++;
	            gcvo.setHorrorCnt(horrorCnt);
	         }  if (vo.getGenre().contains("로맨틱코미디")) {
	            rocoCnt++;
	            gcvo.setRocoCnt(rocoCnt);
	         }  if (vo.getGenre().contains("로맨스")) {
	            romanceCnt++;
	            gcvo.setRomanceCnt(romanceCnt);
	         }  if (vo.getGenre().contains("판타지")) {
	            fantasyCnt++;
	            gcvo.setFantasyCnt(fantasyCnt);
	         }  if (vo.getGenre().contains("가족")) {
	            familyCnt++;
	            gcvo.setFamilyCnt(familyCnt);
	         }  if (vo.getGenre().contains("모험")) {
	            advenCnt++;
	            gcvo.setAdvenCnt(advenCnt);
	         }  if (vo.getGenre().contains("서스펜스")) {
	            suspenseCnt++;
	            gcvo.setSuspenseCnt(suspenseCnt);
	         }  if (vo.getGenre().contains("멜로")) {
	            meloCnt++;
	            gcvo.setMeloCnt(meloCnt);
	         }  if (vo.getGenre().contains("뮤지컬")) {
	            musicCnt++;
	            gcvo.setMusicCnt(musicCnt);
	         }
	      }
	      
	      return gcvo;
	   }
	   
	   public List<OutputVo> myMovieList(String id) {
	      List<OutputVo> my_list = new ArrayList<OutputVo>();
	      OutputVo myvo = new OutputVo();

	      PreparedStatement ps = null;
	      ResultSet rs = null;

	      String sql = null;

	      try {
	         sql = "select * from star where id = ? order by star_date desc";

	         ps = conn.prepareStatement(sql);
	         ps.setString(1, id);
	         
	         rs = ps.executeQuery();

	         while (rs.next()) {
	            myvo = movie_selectOne((String)rs.getString("movienm"));
	            my_list.add(myvo);
	         }

	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         try{rs.close(); } catch(Exception res) {}
	         try{ps.close(); } catch(Exception pse) {}
	      }
	      return my_list;
	   }
	   
	   public NationCountVo nationCount(HttpServletRequest req) {
	      
	      NationCountVo ncvo = new NationCountVo();
	      List<OutputVo> temp = new ArrayList<OutputVo>();
	      String id = req.getParameter("id");
	      
	      temp = myMovieList(id);
	      
	      int korCnt = 0;   // 한국
	      int ameCnt = 0;   // 미국
	      int engCnt = 0;   // 영국
	      int jpnCnt = 0;   // 일본
	      int frcCnt = 0;   // 프랑스
	      int cndCnt = 0;   // 캐나다
	      int chiCnt = 0;   // 칠레
	      int ausCnt = 0; // 호주
	      int chnCnt = 0;   // 중국
	      int newCnt = 0; // 뉴질랜드
	      
	      
	      for(OutputVo vo : temp) {
	         if(vo.getNation().contains("한국")) {
	            korCnt++;
	            ncvo.setKorCnt(korCnt);
	         }
	         if(vo.getNation().contains("미국")) {
	            ameCnt++;
	            ncvo.setAmeCnt(ameCnt);
	         }
	         if(vo.getNation().contains("영국")) {
	            engCnt++;
	            ncvo.setEngCnt(engCnt);
	         }
	         if(vo.getNation().contains("일본")) {
	            jpnCnt++;
	            ncvo.setJpnCnt(jpnCnt);
	         }
	         if(vo.getNation().contains("프랑스")) {
	            frcCnt++;
	            ncvo.setFrcCnt(frcCnt);
	         }
	         if(vo.getNation().contains("캐나다")) {
	            cndCnt++;
	            ncvo.setCndCnt(cndCnt);
	         }
	         if(vo.getNation().contains("칠레")) {
	            chiCnt++;
	            ncvo.setChiCnt(chiCnt);
	         }
	         if(vo.getNation().contains("오스트레일리아")) {
	            ausCnt++;
	            ncvo.setAusCnt(ausCnt);
	         }
	         if(vo.getNation().contains("중국")) {
	            chnCnt++;
	            ncvo.setChnCnt(chnCnt);
	         }
	         if(vo.getNation().contains("뉴질랜드")) {
	            newCnt++;
	            ncvo.setNewCnt(newCnt);
	         }
	         
	      }
	      
	      return ncvo;
	   }
	
	
}
