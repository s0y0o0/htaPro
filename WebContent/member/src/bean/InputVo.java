package bean;

public class InputVo {
   
   String q;         //�˻��� ���ϴ� ���Ǿ�
   String pageno;      //�˻� ����� ��������ȣ �⺻:1, �ּ�:1, �ִ�:500
   int result;         //�� �������� ��µ� ����� �⺻:10, �ּ�:1, �ִ�:20
   String output;      //���� json or xml
   
   String r_key;   //���ڿ�(�ʼ�)   �߱޹���Ű ���� �Է��մϴ�.
   String r_targetDt;   //���ڿ�(�ʼ�)   ��ȸ�ϰ��� �ϴ� ��¥�� yyyymmdd �������� �Է��մϴ�.
   String r_itemPerPage;   //���ڿ�   ��� ROW �� ������ �����մϴ�.(default : ��10��, �ִ� : ��10��)
   String r_multiMovieYn;   //���ڿ�   �پ缺 ��ȭ/�����ȭ�� �������� ��ȸ�� �� �ֽ��ϴ�. ��Y�� : �پ缺 ��ȭ ��N�� : �����ȭ (default : ��ü)
   String r_repNationCd;   //���ڿ�   �ѱ�/�ܱ� ��ȭ���� ��ȸ�� �� �ֽ��ϴ�.  ��K: : �ѱ���ȭ ��F�� : �ܱ���ȭ (default : ��ü)
   String r_wideAreaCd;   //���ڿ�   ���������� ��ȸ�� �� ������, �����ڵ�� �����ڵ� ��ȸ ���񽺿��� ��0105000000�� �μ� ��ȸ�� �����ڵ��Դϴ�. (default : ��ü)
   
   public InputVo(){}
   
   
   
}