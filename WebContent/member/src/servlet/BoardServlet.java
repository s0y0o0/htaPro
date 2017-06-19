/*package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import bean.BoardDao;
import bean.BoardVo;
import bean.PageVo;
import bean.ReplyVo;

public class BoardServlet extends HttpServlet {
	BoardDao dao = new BoardDao();

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

		if (url.lastIndexOf("insert.board") >= 0) {
			insert(req);
			dispatcher = req.getRequestDispatcher("tri_index.jsp?inc=../board/board_input_result.jsp");
			dispatcher.forward(req, resp);

		} else if (url.lastIndexOf("list.board") >= 0) {
			list(req);
			dispatcher = req.getRequestDispatcher("tri_index.jsp?inc=../board/board_list.jsp");
			dispatcher.forward(req, resp);

		} else if (url.lastIndexOf("view.board") >= 0) {
			view(req);
			dispatcher = req.getRequestDispatcher("tri_index.jsp?inc=../board/board_view.jsp");
			dispatcher.forward(req, resp);

		} else if (url.lastIndexOf("delete.board") >= 0) {
			delete(req);
			dispatcher = req.getRequestDispatcher("tri_index.jsp?inc=../board/board_delete.jsp");
			dispatcher.forward(req, resp);

		} else if (url.lastIndexOf("selectOne.board") >= 0) {
			selectOne(req);
			dispatcher = req.getRequestDispatcher("tri_index.jsp?inc=../board/board_modify.jsp");
			dispatcher.forward(req, resp);

		} else if (url.lastIndexOf("modify.board") >= 0) {
			modify(req);
			dispatcher = req.getRequestDispatcher("tri_index.jsp?inc=../board/board_modify_result.jsp");
			dispatcher.forward(req, resp);

		} else if (url.lastIndexOf("reply.board") >=0 ){
			replyinput(req);
			dispatcher = req.getRequestDispatcher("view.board");
			dispatcher.forward(req, resp);
		} else if (url.lastIndexOf("replydel.board") >=0 ){
			replydel(req);
			dispatcher = req.getRequestDispatcher("view.board");
			dispatcher.forward(req, resp);
		}
	}
	
	public void replydel(HttpServletRequest req){
		ReplyVo rvo = new ReplyVo();
		BoardVo vo = new BoardVo();
		
		List<ReplyVo> list = new ArrayList<ReplyVo>();
		rvo.setComment_serial(req.getParameter("comment_serial"));
		
		list = dao.replydel(req);
		
		vo = dao.selectOne(vo);
		
		req.setAttribute("obj", vo);
	}
	
	
	public void replyinput(HttpServletRequest req){
		BoardVo vo = new BoardVo();
		ReplyVo rvo = new ReplyVo();
		
		
		List<ReplyVo> list = new ArrayList<ReplyVo>();
		vo.setSerial(Integer.parseInt(req.getParameter("serial")));
		
		list = dao.replyinput(req);
		
		BoardVo v = dao.view(vo);
		
		req.setAttribute("obj", v);
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
		vo.setNowPage(Integer.parseInt(req.getParameter("nowPage")));
	
		BoardDao dao = new BoardDao();
		
		
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
			msg = "�젙�긽�쟻�쑝濡� ���옣�릺�뿀�뒿�땲�떎.";
		} else {
			msg = "���옣以� �삤瑜� 諛쒖깮";
		}
		req.setAttribute("msg", msg);
	}

	public void list(HttpServletRequest req) {
		try {
			BoardVo v = new BoardVo();
			String findStr = "";
			int nowPage = 1;

			// 泥섏쓬 留곹겕濡� �떎�뻾�릺�뿀�쓣 �븣 ----------------------------------------
			if (req.getParameter("findStr") != null) {
				findStr = req.getParameter("findStr");
			}

			if (req.getParameter("nowPage") != null) {
				nowPage = Integer.parseInt(req.getParameter("nowPage"));
			}
			// ---------------------------------------------------

			v.setFindStr(findStr);
			v.setNowPage(nowPage);
			List<BoardVo> list = dao.list(v);
			PageVo pVo = dao.getpVo();

			req.setAttribute("list", list);
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

		BoardDao dao = new BoardDao();
		vo.setFindStr(req.getParameter("findStr"));
		vo.setNowPage(np);

		int r = dao.delete(vo);
		if (r > 0) {
			msg = "�젙�긽�쟻�쑝濡� �궘�젣�릺�뿀�뒿�땲�떎.";
		} else {
			msg = "�궘�젣以� �삤瑜� 諛쒖깮";
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

		BoardDao dao = new BoardDao();
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
			msg = "�젙�긽�쟻�쑝濡� �닔�젙�릺�뿀�뒿�땲�떎.";
		} else {
			msg = "�닔�젙以� �삤瑜� 諛쒖깮";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("obj", vo);
	}

	
}
*/