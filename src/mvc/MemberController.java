package mvc;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.lookup.InferenceContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {
	MemberDao dao;

	public MemberController() {

	}

	public MemberController(MemberDao dao) {
		this.dao = dao;
	}
	
	@RequestMapping(value="member/view.do", method={RequestMethod.GET, RequestMethod.POST})
	public String view(MemberVo vo) {
		MemberVo v = dao.view(vo);
		vo.setUserpwd(v.getUserpwd());
		return "view"; // member 폴더 안의 view.jsp에 결과가 표시된다
	}
	
	@RequestMapping(value="member/list.do",method=RequestMethod.POST)
		public Object list(MemberVo vo){
		List<MemberVo> list = new ArrayList<MemberVo>();
		list = dao.list(vo);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("list",list);
		mv.setViewName("list");
		
		return mv;
		
	}
	
	@RequestMapping(value="member/delete.do",method={RequestMethod.GET, RequestMethod.POST})
	public Object delete(MemberVo vo){
		ModelAndView mv = new ModelAndView();
		int r = dao.delete(vo);
		
		String msg = "";
		if(r>0) msg = "정상적으로 삭제되었습니다.";
		else msg = "삭제 중 오류가 발생하였습니다.";
		
		mv.addObject("msg",msg);
		mv.setViewName("delete_result");
		
		return mv;
	}
	@RequestMapping(value="member/modify.do",method={RequestMethod.GET, RequestMethod.POST})
	public Object modify(MemberVo vo){
		ModelAndView mv = new ModelAndView();
		int r = dao.modify(vo);
		
		String msg = "";
		if(r>0) msg = "정상적으로 수정되었습니다.";
		else msg = "수정 중 오류가 발생하였습니다.";
		
		mv.addObject("msg",msg);
		mv.setViewName("modify_result");
		
		return mv;
		
	}
	
	@RequestMapping(value="member/input.do", method=RequestMethod.POST)
	public Object input(MemberVo vo){
		ModelAndView mv = new ModelAndView();
			int r = dao.input(vo);
			
			String msg = "";
			if(r>0) msg = "회원 가입이 정상적으로 처리 되었습니다.";
			else msg = "회원 가입 중 오류가 발생하였습니다.";
			
			mv.addObject("msg",msg);
			mv.setViewName("input_result");
			
		return mv;
	}

}