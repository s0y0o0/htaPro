/*package bean;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardDao {
	Connection conn;
	int size = 1024 * 1024 * 10;
	String uploadPath = "C:/workspace/test0520/WebContent/upload/";
	String encoding = "utf-8";
	MultipartRequest multi = null;

	PageVo pVo;

	public BoardDao() {
		conn = new DBConnect().getConn();
	}
	
	public List<ReplyVo> replydel(HttpServletRequest req){
		List<ReplyVo> list = new ArrayList<ReplyVo>();
		ReplyVo rvo = new ReplyVo();
		
		 
		 PreparedStatement ps = null;
	     int r = 1;
	     String sql = "";
	     String comment_serial = req.getParameter("comment_serial");
	     System.out.println(comment_serial);

	     
	     try{
	    	 sql = " delete from board_comment where comment_serial = ? ";
	    	 ps = conn.prepareStatement(sql);
	    	 ps.setString(1, comment_serial);
	    	 ps.executeQuery();
	    	 
	     }catch (Exception ex) {
	    	 r = -1;
			ex.printStackTrace();
	     }
	     
	    rvo.setComment_serial(comment_serial);
	    
	    list = replyList(req);
	        
		
		return list;
	}
	
	public ReplyVo selectReply(ReplyVo rvo){
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		ReplyVo rv = new ReplyVo();
		
		try{
			sql = " select * from board_comment where comment_serial = ? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, rvo.getComment_serial());
			rs = ps.executeQuery();
			
			if(rs.next()){
				rv.setComment_content(rs.getString("comment_content"));
				rv.setComment_date(rs.getString("comment_date"));
				rv.setComment_id(rs.getString("comment_id"));
				rv.setComment_num(rs.getInt("comment_num"));
				rv.setComment_parent(rs.getInt("comment_parent"));
				rv.setComment_pwd(rs.getString("comment_pwd"));
				rv.setComment_serial(rs.getString("comment_serial"));
			}
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return rv;
		
	}

	public List<ReplyVo> replyinput(HttpServletRequest req){
		List<ReplyVo> list = new ArrayList<ReplyVo>();
		ReplyVo rvo = new ReplyVo();
		
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		int rs=-1;
		
		
		String comment_pwd = req.getParameter("commentParentPassword");	 //�뙣�뒪�썙�뱶
		String comment_id = req.getParameter("commentParentName"); // �븘�씠�뵒
		int comment_parent = Integer.parseInt(req.getParameter("serial")); //湲� 踰덊샇
		String comment_content = req.getParameter("commentParentText"); //湲��궡�슜
		

		//�뙎湲� ���옣
		sql = "insert into board_comment(comment_num, comment_pwd, comment_id, comment_date, comment_parent,comment_content,comment_serial )"
				+ " values(seq_comment_num.nextval, ?, ?, sysdate, ?, ?,?|| '-' || seq_comment_num.currval ) ";
		
		
		
		try {
			
		conn = new DBConnect().getConn();
		ps = conn.prepareStatement(sql);
		ps.setString(1,comment_pwd);
		ps.setString(2,comment_id);
		ps.setInt(3,comment_parent);
		ps.setString(4,comment_content);
		ps.setInt(5,comment_parent);
		
		rs=ps.executeUpdate();
		
		list = replyList(req);
		
		
	
		
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return list;
	}	
	
	
	
	public int insert(HttpServletRequest req) {
		List<String> attFiles = new ArrayList<String>();
		List<String> oriAttFiles = new ArrayList<String>();

		int rs = 0; // �젙�꽦�쟻�쑝濡� ���옣�맂 寃쎌슦 0蹂대떎 �겙媛�.
		PreparedStatement ps = null;
		String sql = null;
		String filename = "";
		try {
			// �뙆�씪 �뾽濡쒕뱶 泥섎━
			multi = new MultipartRequest(req, uploadPath, size, encoding, new DefaultFileRenamePolicy());

			Enumeration<String> files = multi.getFileNames();
			while (files.hasMoreElements()) {
				String file1 = files.nextElement();
				attFiles.add(multi.getFilesystemName(file1));
				oriAttFiles.add(multi.getOriginalFileName(file1));
				filename = multi.getFilesystemName(file1);
			}

			// board �뀒�씠釉붿뿉 �궡�슜 ���옣
			sql = "insert into board(serial, mdate, worker, subject, content, hit, grp, deep) "
					+ " values(seq_board_serial.nextval, sysdate, ?, ?, ?, 0 , "
					+ " seq_board_serial.currval, seq_board_serial.currval) ";

			ps = conn.prepareStatement(sql);
			ps.setString(1, multi.getParameter("worker"));
			ps.setString(2, multi.getParameter("subject"));
			ps.setString(3, multi.getParameter("content"));

			rs = ps.executeUpdate();

			// board_att �뀒�씠釉붿뿉 泥⑤� �뙆�씪 ���옣
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

			// �꽟�꽕�씪 留뚮뱾湲�
			makeThumb(attFiles);

		} catch (Exception ex) {
			rs = -1;
			ex.printStackTrace();
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
			}
		}

	}

	public void pagaCompute(BoardVo v) {
		pVo = new PageVo();

		int totList = 0; // 由ъ뒪�듃 �쟾泥� 媛��닔
		int totPage = 0; // �쟾泥� �럹�씠吏��닔
		int totBlock = 0; // �쟾泥� 釉붾윮�닔

		int nowBlock = 1; // �쁽�옱 釉붾윮

		int startNo = 0; // 由ъ뒪�듃 紐⑸줉�쓽 �떆�옉�쐞移�
		int endNo = 0; // 由ъ뒪�듃 紐⑸줉�쓽 留덉�留� �쐞移�

		int startPage = 0; // �븳釉붾윮�뿉 �몴�떆�븷 �떆�옉 �럹�씠吏� 踰덊샇
		int endPage = 0; // �븳釉붾윮�뿉 �몴�떆�븷 留덉�留� �럹�씠吏� 踰덊샇

		int nowPage = v.getNowPage(); // �쁽�옱 �럹�씠吏�

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
		}

	}

	public List<BoardVo> list(BoardVo v) {
		pagaCompute(v);

		PreparedStatement ps = null;
		ResultSet rs = null;
		String findStr = v.getFindStr();
		String replStr = "";

		String sql = "select * from(" + " select rownum no, brd.* from("
				+ " select * from board where worker like ? or subject like ? or content like ? "
				+ " order by grp desc, deep asc" + " )brd " + ") where no between ? and ? ";

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
				vo.setSubject(replStr + rs.getString("subject"));
				vo.setHit(rs.getInt("hit"));

				list.add(vo);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return list;
		}
	}

	public int delete(BoardVo v) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        BoardVo temp = selectOne(v);
        File deleteFile = null;
        String sql = "";
        
        int r = 1;
        try {
           // �썝蹂멸� �궘�젣
           sql = "delete from board where serial =?";
           ps = conn.prepareStatement(sql);
           ps.setInt(1, v.getSerial());
           ps.executeUpdate();

           // 泥⑤��뙆�씪�뱾 遺덈윭�삤湲�
           sql = "select * from board_att where pserial=?";
           ps = conn.prepareStatement(sql);
           ps.setInt(1, v.getSerial());
           rs = ps.executeQuery();

           // 泥⑤��뙆�씪 �궘�젣
           while (rs.next()) {
              deleteFile = new File(uploadPath + rs.getString("attfile"));
              if (deleteFile.exists()) {
                 deleteFile.delete();
                 deleteFile=null;
              }
              Thread.sleep(2000);
              
              deleteFile = new File(uploadPath +"sm_" + rs.getString("attfile"));
              
              if (deleteFile.exists()) {
                 deleteFile.delete();
                 deleteFile=null;
              }
              Thread.sleep(2000);
           }
           // 泥⑤� �뀒�씠釉� �궘�젣
           sql = "delete from board_att where pserial=?";
           ps = conn.prepareStatement(sql);
           ps.setInt(1, v.getSerial());
           ps.executeUpdate();

        } catch (Exception ex) {
           r = -1;
           ex.printStackTrace();
        } finally {
           return r;
        }
     }

	public BoardVo view(BoardVo vo) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		BoardVo v = null;
		
		try {
			// hit�닔 利앷�
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
			return v;
		}
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
			// �쐢蹂멸�
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

				// 泥⑤��뙆�씪
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
			return vo;
		}
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
			// 泥⑤��뙆�씪
			multi = new MultipartRequest(req, uploadPath, size, encoding, new DefaultFileRenamePolicy());

			Enumeration<String> files = multi.getFileNames();

			while (files.hasMoreElements()) {
				tempFile = files.nextElement();
				attFiles.add(multi.getFilesystemName(tempFile));
				oriAttFiles.add(multi.getOriginalFileName(tempFile));
			}

			// �썝蹂� �닔�젙
			sql = "update board set subject=? , content=? where serial=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, multi.getParameter("subject"));
			ps.setString(2, multi.getParameter("content"));
			ps.setString(3, multi.getParameter("serial"));
			r = ps.executeUpdate();

			// board_att �뀒�씠釉붿뿉 泥⑤� �뙆�씪 ���옣
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

			// �궘�젣�맂 泥⑤��뙆�씪 泥섎━
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
			return vo;
		}
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

			// 泥⑤��뙆�씪
			Enumeration<String> files = multi.getFileNames();

			while (files.hasMoreElements()) {
				String file1 = files.nextElement();
				attFiles.add(multi.getFilesystemName(file1));
				oriAttFiles.add(multi.getOriginalFileName(file1));
			}

			// �궡�슜 ���옣
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

			// board_att �뀒�씠釉붿뿉 泥⑤� �뙆�씪 ���옣
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
			return vo;
		}
	}
	
	public List<ReplyVo> replyList(HttpServletRequest req) {
		List<ReplyVo> list = new ArrayList<ReplyVo>();
		
		ResultSet rs;
		
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
	
		int comment_parent = Integer.parseInt(req.getParameter("serial"));
		sql = " select * from board_comment where comment_parent = ? ORDER by comment_num asc ";
	
		try{			
		conn = new DBConnect().getConn();
		ps = conn.prepareStatement(sql);
		ps.setInt(1, comment_parent);
		rs = ps.executeQuery();
		
		while(rs.next()){
			
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
		

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	

	public PageVo getpVo() {
		return pVo;
	}
}*/