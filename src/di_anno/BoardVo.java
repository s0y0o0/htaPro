package di_anno;

import java.util.List;

public class BoardVo {

	static final long serialVersionUID = 1l;

	int serial;
	String mDate;
	String worker;
	String subject;
	String content;
	int hit;
	int grp;
	String deep;
	List<String> attfile; // 서버에 저장된 파일명
	List<String> oriAttfile;// 사용자가 선택한 원본 파일명
	String[] deleteFile;

	String findStr = "";
	int nowPage;

	public BoardVo() {
	}

	public BoardVo(int s, String md, String wk, String sub, String cont, int h) {
		this.serial = s;
		this.mDate = md;
		this.worker = wk;
		this.subject = sub;
		this.content = cont;
		this.hit = h;
	}

	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	public String getmDate() {
		return mDate;
	}

	public void setmDate(String mDate) {
		this.mDate = mDate;
	}

	public String getWorker() {
		return worker;
	}

	public void setWorker(String worker) {
		this.worker = worker;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getFindStr() {
		return findStr;
	}

	public void setFindStr(String findStr) {
		this.findStr = findStr;
	}

	public int getGrp() {
		return grp;
	}

	public void setGrp(int grp) {
		this.grp = grp;
	}

	public String getDeep() {
		return deep;
	}

	public void setDeep(String deep) {
		this.deep = deep;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public List<String> getAttfile() {
		return attfile;
	}

	public void setAttfile(List<String> attfile) {
		this.attfile = attfile;
	}

	public List<String> getOriAttfile() {
		return oriAttfile;
	}

	public void setOriAttfile(List<String> oriAttfile) {
		this.oriAttfile = oriAttfile;
	}

	public String[] getDeleteFile() {
		return deleteFile;
	}

	public void setDeleteFile(String[] deleteFile) {
		this.deleteFile = deleteFile;
	}

}
