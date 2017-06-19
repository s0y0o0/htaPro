package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;
import kr.or.kobis.kobisopenapi.consumer.rest.exception.OpenAPIFault;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.HTTP;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sun.xml.internal.ws.resources.HttpserverMessages;

import bean.WaddaDao;
import bean.WaddaDao;
import bean.BoardVo;
import bean.DetailPageVo;
import bean.GenreCountVo;
import bean.MemberVo;
import bean.NationCountVo;
import bean.OutputVo;
import bean.PageVo;
import bean.ReplyVo;
import bean.WaddaDao;

public class WaddaServlet extends HttpServlet {
	WaddaDao dao = new WaddaDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String url = req.getRequestURI();
		RequestDispatcher dispatcher = null;
		String msg = "";
		
		
		
		if (url.lastIndexOf("login.do") >= 0) {
			login(req);	// 占싸깍옙占쏙옙 占쏙옙占쏙옙
			rank(req);	// 占쌘쏙옙占쏙옙占실쏙옙
			lastviewmoive(req);	// 占쌍깍옙 占쏙옙 占쏙옙화
			
			//board
			list(req); //ddddddddddd이게 문제임
			newMovie(req);	//占쏙옙占쌜소곤옙
			
			dispatcher = req.getRequestDispatcher("index.jsp");
			dispatcher.forward(req, resp);

		}else if (url.lastIndexOf("main.do") >= 0) {
			//login(req);
			rank(req);	// 占쌘쏙옙占쏙옙占실쏙옙
			lastviewmoive(req);	// 占쌍깍옙 占쏙옙 占쏙옙화
			
			//board
			list(req); //ddddddddddd이게 문제임
			newMovie(req);	//占쏙옙占쌜소곤옙
			
			dispatcher = req.getRequestDispatcher("index.jsp");
			dispatcher.forward(req, resp);

		} else if (url.lastIndexOf("mypage.do") >= 0) {
	         System.out.println("111");
	         mypage(req);
	         System.out.println("333");
	         dispatcher = req.getRequestDispatcher("index.jsp?inc=../wadda_page/mypage.jsp");   
	         dispatcher.forward(req, resp);
	         System.out.println("444");
	      }
		
		
		else if (url.lastIndexOf("detail_page.do") >= 0) {
			detail_page(req);
			dispatcher = req.getRequestDispatcher("index.jsp?inc=../wadda_page/detail_page.jsp");
			dispatcher.forward(req, resp);
		}else if (url.lastIndexOf("detail_page2.do") >= 0) {
			detail_page(req);
			dispatcher = req.getRequestDispatcher("index.jsp?inc=../wadda_page/detail_page.jsp");
			dispatcher.forward(req, resp);
		} else if (url.lastIndexOf("genre.do") >= 0) {
			genre(req);
			dispatcher = req.getRequestDispatcher("index.jsp?inc=../wadda_page/genre_result.jsp");	// 페이지 필요
			dispatcher.forward(req, resp);
		}
		/*else if (url.lastIndexOf("search.wa") >= 0) {
			List<OutputVo> list = new ArrayList<OutputVo>();
			OutputVo ovo = new OutputVo();

			list = search(req);

			req.setAttribute("list", list);
			req.setAttribute("ovo", ovo);

			dispatcher = req.getRequestDispatcher("searchMovieResult.jsp");
			dispatcher.forward(req, resp);
		 
		}*/
		//蹂대뱶
		else if (url.lastIndexOf("insert.do") >= 0) {
	         insert(req);
	         dispatcher = req.getRequestDispatcher("index.jsp?inc=../board/board_input_result.jsp");
	         dispatcher.forward(req, resp);

	      } else if (url.lastIndexOf("list.do") >= 0) {
			list(req);
			dispatcher = req.getRequestDispatcher("index.jsp?inc=../board/board_list.jsp");
			dispatcher.forward(req, resp);

		} else if (url.lastIndexOf("view.do") >= 0) {
			view(req);
			dispatcher = req.getRequestDispatcher("index.jsp?inc=../board/board_view.jsp");
			dispatcher.forward(req, resp);

		} else if (url.lastIndexOf("delete.do") >= 0) {
			delete(req);
			dispatcher = req.getRequestDispatcher("index.jsp?inc=../board/board_delete.jsp");
			dispatcher.forward(req, resp);

		} else if (url.lastIndexOf("selectOne.do") >= 0) {
			selectOne(req);
			dispatcher = req.getRequestDispatcher("index.jsp?inc=../board/board_modify.jsp");
			dispatcher.forward(req, resp);

		} else if (url.lastIndexOf("modify.do") >= 0) {
			modify(req);
			dispatcher = req.getRequestDispatcher("index.jsp?inc=../board/board_modify_result.jsp");
			dispatcher.forward(req, resp);

		} else if (url.lastIndexOf("reply.do") >=0 ){
			replyinput(req);
			dispatcher = req.getRequestDispatcher("view.do");
			dispatcher.forward(req, resp);
		} else if (url.lastIndexOf("replydel.do") >=0 ){
			replydel(req);
			dispatcher = req.getRequestDispatcher("view.do");
			dispatcher.forward(req, resp);
		}
		// header button ///////////
		else if (url.lastIndexOf("recommend.do") >=0 ){
			HttpSession session = req.getSession(false);
			session = req.getSession();
			recommend(req);
			dispatcher = req.getRequestDispatcher("index.jsp?inc=../wadda_page/recommend_page.jsp");
			dispatcher.forward(req, resp);
		}
		
		
		// detail_page ///////////
		else if(url.lastIndexOf("insert_detail_page_comment.do") >= 0){
			String movieNm= (String)req.getParameter("movieNm");
			insert_detail_page_comment(req);
			req.setAttribute("movieNm", movieNm);
			dispatcher = req.getRequestDispatcher("detail_page2.do");
			dispatcher.forward(req, resp);
		}
		
		
	 else if (url.lastIndexOf("search.do") >= 0) {
		search(req);
		dispatcher = req.getRequestDispatcher("index.jsp?inc=./search.jsp");
		dispatcher.forward(req, resp);

	}else if (url.lastIndexOf("detail_comment_del.do") >=0 ){
        detail_comment_del(req);
        dispatcher = req.getRequestDispatcher("detail_page.do");
        dispatcher.forward(req, resp);
     }
	}
	
	public void detail_comment_del(HttpServletRequest req){
	      DetailPageVo dVo = new DetailPageVo();
	      List<DetailPageVo> list = new ArrayList<DetailPageVo>();
	      
	      dVo.setDetail_cnum(req.getParameter("ddd"));
	      dVo.setMovieNm(req.getParameter("movieNm"));
	      
	      list = dao.detail_comment_del(req);
	      
	      
	   }

	public void login(HttpServletRequest req) {
		MemberVo vo = new MemberVo();
		vo = dao.memberCheck(req);
		
		HttpSession session = req.getSession(false);
		
		MemberVo obj = (MemberVo)session.getAttribute("id");
		
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String gender = req.getParameter("gender");
		if(obj == null){
			session.setAttribute("loginVo", id);
			session.setAttribute("name", name);
			session.setAttribute("gender", gender);
		}
		
		req.setAttribute("vo", vo);
		req.setAttribute("id", req.getParameter("id"));
//		req.setAttribute("dailyResult", req.getParameter("dailyResult"));
		
		
	}
	
	public void rank(HttpServletRequest req) {
//		HashMap<String, Object> dailyResult = new HashMap<String, Object>();
		List<OutputVo> r_list = new ArrayList<OutputVo>();
		r_list = dao.rank(req);
		req.setAttribute("r_list", r_list);
	}
	
	public void lastviewmoive(HttpServletRequest req) {
		List<OutputVo> c_list = new ArrayList<OutputVo>();
		c_list = dao.lastviewmoive(req);
		req.setAttribute("c_list", c_list);
		
	}
	
	public void newMovie(HttpServletRequest req) {
		List<OutputVo> n_list = new ArrayList<OutputVo>();
		n_list = dao.newMovie(req);
		req.setAttribute("n_list", n_list);
		
	}
	
	public void detail_page(HttpServletRequest req){
		OutputVo ovo = new OutputVo();
		
		ovo = dao.movie_selectOne((String)req.getParameter("movieNm"));
		double avgStar = dao.avgStar((String)req.getParameter("movieNm"));
		req.setAttribute("avgStar", avgStar);
		req.setAttribute("ovo", ovo);
		list_detail_page_comment(req);
		
	}
	
	// detail_page 肄붾찘�듃
	public void insert_detail_page_comment(HttpServletRequest req){
		HttpSession session = req.getSession(false);
		int rs =0;
		String id = (String)session.getAttribute("loginVo");
		if(id != null){
			rs = dao.insert_detail_page_comment(req,id);
		}
		
		req.setAttribute("rs", rs);
		
	}

	public void list_detail_page_comment(HttpServletRequest req) {
	      try {
	         HttpSession session = req.getSession(false);
	         String logId = (String)session.getAttribute("loginVo");
	         String movieNm = (String)req.getParameter("movieNm");
	         List<DetailPageVo> list = dao.list_detail_page_comment(movieNm);
	         req.setAttribute("list", list);
	         req.setAttribute("logId", logId);
	      } catch (Exception ex) {
	         ex.printStackTrace();
	      }
	   }

/*	public List<OutputVo> search(HttpServletRequest req) {

		List<OutputVo> list = new ArrayList<OutputVo>();
		String qq = req.getParameter("qq");
		list = dao.search(qq);

		return list;

	}*/
	
	
	
	
	
	
	//蹂대뱶
	public void replydel(HttpServletRequest req){
		ReplyVo rvo = new ReplyVo();
		BoardVo vo = new BoardVo();
		
		List<ReplyVo> list = new ArrayList<ReplyVo>();
		rvo.setComment_serial(req.getParameter("comment_serial"));
		
		list = dao.replydel(req);
		
		/*vo = dao.selectOne(vo);
		
		req.setAttribute("obj", vo);*/
	}
	
	
	public void replyinput(HttpServletRequest req){
		BoardVo vo = new BoardVo();
		ReplyVo rvo = new ReplyVo();
		
		
		List<ReplyVo> list = new ArrayList<ReplyVo>();
		vo.setSerial(Integer.parseInt(req.getParameter("serial")));
		
		list = dao.replyinput(req);
		
	/*	BoardVo v = dao.view(vo);
		
		req.setAttribute("obj", v);*/
		req.setAttribute("list", list);
		
	}
	
	public void replyList(HttpServletRequest req){
		ReplyVo rvo = new ReplyVo();
		List <ReplyVo> relist = dao.replyList(req);
		req.setAttribute("relist", relist);
	}
	
	
	public void view(HttpServletRequest req) {
		BoardVo vo = new BoardVo();
		
		vo.setSerial(Integer.parseInt(req.getParameter("serial")));	
		vo.setFindStr(req.getParameter("finStr"));
		/*vo.setNowPage(Integer.parseInt(req.getParameter("nowPage")));*/
	
		WaddaDao dao = new WaddaDao();
		
		
		BoardVo v = dao.view(vo);
		
		if(req.getParameter("findStr") == null) {
			v.setFindStr("");
			v.setNowPage(1);
		}else {
			v.setFindStr(req.getParameter("findStr"));
			v.setNowPage(Integer.parseInt(req.getParameter("nowPage")));
		}
		
		
		v.setSerial(Integer.parseInt(req.getParameter("serial")));
		
		
		req.setAttribute("obj", v);
		
		List<ReplyVo> relist = dao.replyList(req);
		req.setAttribute("relist", relist);
		
	}

	public void insert(HttpServletRequest req) {
		int rs = dao.insert(req);
		String msg = "";
		if (rs > 0) {
			msg = "정상적으로 내용이 입력되었습니다.";
		} else {
			msg = "내용작성에 오류가 발생하였스니다.";
		}
		req.setAttribute("msg", msg);
	}

	public void list(HttpServletRequest req) {
		try {
			BoardVo v = new BoardVo();
			String findStr = "";
			int nowPage = 1;

			if (req.getParameter("findStr") != null) {
				findStr = req.getParameter("findStr");
			}
			if (req.getParameter("nowPage") != null) {
				nowPage = Integer.parseInt(req.getParameter("nowPage"));
			}

			v.setFindStr(findStr);
			v.setNowPage(nowPage);
			List<BoardVo> b_list = dao.list(v);
			PageVo pVo = dao.getpVo();

			req.setAttribute("b_list", b_list);
			req.setAttribute("obj", v);
			req.setAttribute("page", pVo);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	public void delete(HttpServletRequest req) {
		BoardVo vo = new BoardVo();
		
		vo.setSerial(Integer.parseInt(req.getParameter("serial")));
		
		int np = 1;
		String msg = "";

		if (req.getParameter("nowPage").equals(""))
			np = 1;
		else
			np = Integer.parseInt(req.getParameter("nowPage"));

		WaddaDao dao = new WaddaDao();
		vo.setFindStr(req.getParameter("findStr"));
		vo.setNowPage(np);

		int r = dao.delete(vo);
		if (r > 0) {
			msg = "占쎌젟占쎄맒占쎌읅占쎌몵嚥∽옙 占쎄텣占쎌젫占쎈┷占쎈�占쎈뮸占쎈빍占쎈뼄.";
		} else {
			msg = "占쎄텣占쎌젫餓ο옙 占쎌궎�몴占� 獄쏆뮇源�";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("obj", vo);

	}

	public void selectOne(HttpServletRequest req) {
		BoardVo vo = new BoardVo();
		vo.setSerial(Integer.parseInt(req.getParameter("serial")));
		int np = 1;
		if (req.getParameter("nowPage").equals(""))
			np = 1;
		else
			np = Integer.parseInt(req.getParameter("nowPage"));

		WaddaDao dao = new WaddaDao();
		BoardVo v = dao.selectOne(vo);
		v.setFindStr(req.getParameter("findStr"));
		v.setNowPage(np);

		req.setAttribute("obj", v);

	}

	public void modify(HttpServletRequest req) {
		BoardVo vo = null;
		int np = 1;
		String msg = "";

		vo = dao.update(req);
		
		if (vo != null) {
			msg = "占쎌젟占쎄맒占쎌읅占쎌몵嚥∽옙 占쎈땾占쎌젟占쎈┷占쎈�占쎈뮸占쎈빍占쎈뼄.";
		} else {
			msg = "占쎈땾占쎌젟餓ο옙 占쎌궎�몴占� 獄쏆뮇源�";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("obj", vo);
	}
	
	public void genre(HttpServletRequest req) {
		System.out.println("2번이에여");
		List<OutputVo> g_list = new ArrayList<OutputVo>();
		g_list = dao.genreList(req);
		req.setAttribute("g_list", g_list);
	}
	
	public void recommend(HttpServletRequest req){
		HttpSession session = req.getSession(false);
		String id = (String)session.getAttribute("loginVo");
		
		List<OutputVo> reco_list = new ArrayList<OutputVo>();
		
		//reco_list = dao.recococo(id);
		
		// 스타를 검색하고, 평점순으로 최상단 세개 추려서, 
		//이런영화어떠세요? 감독,배우,장르로 검색된 영화들 중에 가장 평균 평점이 높은 5개 
		
		//List<OutputVo> my_list = new ArrayList<OutputVo>();
		//List<OutputVo> reco_director_list = new ArrayList<OutputVo>(); //추천 영화 1~5등 감독,
		//List<OutputVo> reco_actor_list = new ArrayList<OutputVo>(); //추천 영화 배우, 
		List<OutputVo> reco_genre_list = new ArrayList<OutputVo>(); //추천 영화 장르
		
		//my_list = dao.recommend(id);
		//reco_director_list = dao.reco_director_list(id);
		//reco_actor_list = dao.reco_actor_list(id);
		reco_genre_list = dao.reco_genre_list(id);
		
		//req.setAttribute("director_list", reco_director_list);
		//req.setAttribute("actor_list", reco_actor_list);
		req.setAttribute("genre_list", reco_genre_list);
	}
	
	
	// main search

		public void search(HttpServletRequest req) {
			String findStr = (String) req.getParameter("search");
				
			List<OutputVo> main_list = dao.mainSearch(findStr);
			req.setAttribute("main_list", main_list);

		}
		
		public void mypage(HttpServletRequest req) {
	         System.out.println("222");
	         GenreCountVo gcvo = new GenreCountVo();   //장르 카운트
	         gcvo = dao.genreCount(req);
	         
	         NationCountVo ncvo = new NationCountVo();
	         ncvo = dao.nationCount(req);
	         
	         req.setAttribute("gcvo", gcvo);
	         req.setAttribute("ncvo", ncvo);
	      }
}


