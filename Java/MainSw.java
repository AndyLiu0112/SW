package com.czy.sw;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.czy.sw.Http;

/**
* @author Czy
* @time Jul 6, 2019
* @detail *
*/

public class MainSw {

	/**
	 * ǿ�ǽ���ϵͳ
	 */
	////////////////////////////////////////////////////////
	private String account = "";
	private String password = "";
	private String url = "http://jwgl.sdust.edu.cn/app.do";
	////////////////////////////////////////////////////////
	
	/**
	 * ע�⣺���ڴ���Json��Ҫ����Json����������ʱ������Json���ݣ����赱ǰѧ����2018-2019-2����ǰ�ܴ���18��
	 * ����Json�������ֱ�ӵ���GetCurrentTime()�����õ��ַ�����ת��ΪJson��ȡ����
	 * ��ʵѧ���뵱ǰ�ܴ��ǿ������м���ģ������Լ��ٶ�ǿ�Ƿ�����������������Կ�һ��SW/Web/app/auxiliary/Conf.php��
	 */
	////////////////////////////////////////////////////////
	private String curWeek = "18";
	private String curTerm = "2018-2019-2";
	////////////////////////////////////////////////////////
	
	private Map<String, String> headers = new HashMap<String, String>();
	
	private Map<String, String> GetHashMap() {
		return new HashMap<String, String>();
	}
	
	public MainSw() {
		Map<String, String> param = GetHashMap();
		param.put("method", "authUser");
		param.put("xh", this.account);
		param.put("pwd", this.password);
		String reqResult = Http.httpRequest(this.url, param, "GET", this.headers);
		System.out.println(reqResult);
		String[] reqResultArr  = reqResult.split(",");
		if(reqResultArr[0].charAt(9) == '0') {
			System.out.println("��¼ʧ��");
			System.exit(0);
		}else {
			this.headers.put("token", reqResultArr[2].substring(9, reqResultArr[2].length()-1));
		}
	}
	
	private String GetHandle(Map<String, String> param) {
		return Http.httpRequest(this.url, param, "GET", this.headers);
	}
	
	public String GetStudentIdInfo() {
		Map<String, String> param = GetHashMap();
		param.put("method", "getStudentIdInfo");
		param.put("xh", this.account);
		String req = this.GetHandle(param);
		System.out.println(req);
		return req;
	}
	
	public String GetCurrentTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, String> param = GetHashMap();
		param.put("method", "getCurrentTime");
		param.put("currDate", df.format(new Date()));
		String req = this.GetHandle(param);
		System.out.println(req);
		return req;
	}
	
	public String GetTable() {
		Map<String, String> param = GetHashMap();
		param.put("method", "getKbcxAzc");
		param.put("xh", this.account);
		param.put("xnxqid", this.curTerm);
		param.put("zc", this.curWeek);
		String req = this.GetHandle(param);
		System.out.println(req);
		return req;
	}
	
	public String GetTable(String week) {
		Map<String, String> param = GetHashMap();
		param.put("method", "getKbcxAzc");
		param.put("xh", this.account);
		param.put("xnxqid", this.curTerm);
		param.put("zc", week);
		String req = this.GetHandle(param);
		System.out.println(req);
		return req;
	}
	
	public String GetGrade() {
		Map<String, String> param = GetHashMap();
		param.put("method", "getCjcx");
		param.put("xh", this.account);
		param.put("xnxqid", "");
		String req = this.GetHandle(param);
		System.out.println(req);
		return req;
	}
	
	public String GetGrade(String term) {
		Map<String, String> param = GetHashMap();
		param.put("method", "getCjcx");
		param.put("xh", this.account);
		param.put("xnxqid", term);
		String req = this.GetHandle(param);
		System.out.println(req);
		return req;
	}
	
	public String GetClassroom(String idleTime) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, String> param = GetHashMap();
		param.put("method", "getKxJscx");
		param.put("time", df.format(new Date()));
		param.put("idleTime", idleTime);
		String req = this.GetHandle(param);
		System.out.println(req);
		return req;
	}

	public String GetExam() {
		Map<String, String> param = GetHashMap();
		param.put("method", "getKscx");
		param.put("xh", this.account);
		String req = this.GetHandle(param);
		System.out.println(req);
		return req;
	}
	
	/**
	 * ��ں���
	 * @param args
	 */
	public static void main(String[] args) {
		MainSw Q = new MainSw();
//		Q.GetStudentIdInfo(); //��ȡѧ����Ϣ
//		Q.GetCurrentTime(); //��ȡѧ����Ϣ
//		Q.GetTable(); //��ǰ�ܴοα�
//		Q.GetTable("3"); //ָ���ܴοα�
//		Q.GetGrade(); //��ѯȫ���ɼ�
//		Q.GetGrade("2018-2019-2"); //ָ��ѧ�ڳɼ���ѯ
//		Q.GetClassroom("0102"); //�ս��Ҳ�ѯ "allday"��ȫ�� "am"������ "pm"������ "night"������ "0102":1.2�ڿս��� "0304":3.4�ڿս���
//		Q.GetExam(); //��ȡ������Ϣ
	}

}
