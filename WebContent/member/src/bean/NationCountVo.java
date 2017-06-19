package bean;

public class NationCountVo {
   
   int korCnt = 0;   // 한국
   int ameCnt = 0;   // 미국
   int engCnt = 0;   // 영국
   int jpnCnt = 0;   // 일본
   int frcCnt = 0;   // 프랑스
   int cndCnt = 0;   // 캐나다
   int chiCnt = 0;   // 칠레
   int ausCnt = 0; // 오스트레일리아
   int chnCnt = 0;   // 중국
   int newCnt = 0; // 뉴질랜드
   
   public NationCountVo () {}
   
   public NationCountVo(int korCnt, int ameCnt, int engCnt, int jpnCnt, int frcCnt, int cndCnt, int chiCnt, int ausCnt,
         int chnCnt, int newCnt) {
      super();
      this.korCnt = korCnt;
      this.ameCnt = ameCnt;
      this.engCnt = engCnt;
      this.jpnCnt = jpnCnt;
      this.frcCnt = frcCnt;
      this.cndCnt = cndCnt;
      this.chiCnt = chiCnt;
      this.ausCnt = ausCnt;
      this.chnCnt = chnCnt;
      this.newCnt = newCnt;
   }

   public int getKorCnt() {
      return korCnt;
   }

   public void setKorCnt(int korCnt) {
      this.korCnt = korCnt;
   }

   public int getAmeCnt() {
      return ameCnt;
   }

   public void setAmeCnt(int ameCnt) {
      this.ameCnt = ameCnt;
   }

   public int getEngCnt() {
      return engCnt;
   }

   public void setEngCnt(int engCnt) {
      this.engCnt = engCnt;
   }

   public int getJpnCnt() {
      return jpnCnt;
   }

   public void setJpnCnt(int jpnCnt) {
      this.jpnCnt = jpnCnt;
   }

   public int getFrcCnt() {
      return frcCnt;
   }

   public void setFrcCnt(int frcCnt) {
      this.frcCnt = frcCnt;
   }

   public int getCndCnt() {
      return cndCnt;
   }

   public void setCndCnt(int cndCnt) {
      this.cndCnt = cndCnt;
   }

   public int getChiCnt() {
      return chiCnt;
   }

   public void setChiCnt(int chiCnt) {
      this.chiCnt = chiCnt;
   }

   public int getAusCnt() {
      return ausCnt;
   }

   public void setAusCnt(int ausCnt) {
      this.ausCnt = ausCnt;
   }

   public int getChnCnt() {
      return chnCnt;
   }

   public void setChnCnt(int chnCnt) {
      this.chnCnt = chnCnt;
   }

   public int getNewCnt() {
      return newCnt;
   }

   public void setNewCnt(int newCnt) {
      this.newCnt = newCnt;
   }
   
}