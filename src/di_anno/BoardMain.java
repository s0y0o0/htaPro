package di_anno;

import org.springframework.context.support.GenericXmlApplicationContext;

public class BoardMain {

	public BoardMain() {}
	
	public static void main(String[] args){
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("di_anno/anno_context.xml");
		BoardDao dao = ctx.getBean("dao",BoardDao.class);
		
		
		BoardVo v = new BoardVo();
		v.setSerial(82);
		BoardVo vo = dao.view(v);
		
		if(vo!=null){
			System.out.println("Serial : " + vo.getSerial());
			System.out.println(vo.subject + ", " + vo.getWorker());
		}else{
			System.out.println("data not found");
		}
		
		//qualifer test
		AutoWiredTest au = ctx.getBean("autowire", AutoWiredTest.class);
		System.out.println(au.getUser().getIrum());
		
	}

}
