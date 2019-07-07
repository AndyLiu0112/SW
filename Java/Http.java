package com.czy.sw;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

/**
* @author Czy
* @time Jul 6, 2019
* @detail Http������
*/

public class Http {
	
	public static String httpRequest(String url,Map<String, String> param,String method,Map<String, String> headers) {
		String paramDipose = ParamHandle(param);
		if (method.equalsIgnoreCase("GET")) {
			return doGet(url + paramDipose, headers);
		}else {
			return doPost(url, paramDipose, headers);
		}
	}
	
	private static String ParamHandle(Map<String, String> params) {
		StringBuilder urlParam = new StringBuilder("?");;
		for (Entry<String,String> param : params.entrySet()) {
			urlParam.append(param.getKey() + "=" + param.getValue() + "&");
		}
		return urlParam.toString();
	}
	
	 private static String doGet(String httpurl,Map<String, String> headers) {
	        HttpURLConnection connection = null;
	        InputStream is = null;
	        BufferedReader br = null;
	        String result = null;// ���ؽ���ַ���
	        try {
	            // ����Զ��url���Ӷ���
	            URL url = new URL(httpurl);
	            // ͨ��Զ��url���Ӷ����һ�����ӣ�ǿת��httpURLConnection��
	            connection = (HttpURLConnection) url.openConnection();
	            // �������ӷ�ʽ��get
	            connection.setRequestMethod("GET");
	            // �������������������ĳ�ʱʱ�䣺15000����
	            connection.setConnectTimeout(15000);
	            // ���ö�ȡԶ�̷��ص�����ʱ�䣺60000����
	            connection.setReadTimeout(60000);
	            for (Entry<String,String> header : headers.entrySet()) {
					connection.setRequestProperty(header.getKey(), header.getValue());
				}
	            // ��������
	            connection.connect();
	            // ͨ��connection���ӣ���ȡ������
	            if (connection.getResponseCode() == 200) {
	                is = connection.getInputStream();
	                // ��װ������is����ָ���ַ���
	                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	                // �������
	                StringBuffer sbf = new StringBuffer();
	                String temp = null;
	                while ((temp = br.readLine()) != null) {
	                    sbf.append(temp);
	                    sbf.append("\r\n");
	                }
	                result = sbf.toString();
	            }
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            // �ر���Դ
	            if (null != br) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	            if (null != is) {
	                try {
	                    is.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	            connection.disconnect();// �ر�Զ������
	        }

	        return result;
	    }

	 	private static String doPost(String httpUrl, String param,Map<String, String> headers) {

	        HttpURLConnection connection = null;
	        InputStream is = null;
	        OutputStream os = null;
	        BufferedReader br = null;
	        String result = null;
	        try {
	            URL url = new URL(httpUrl);
	            connection = (HttpURLConnection) url.openConnection();
	            connection.setRequestMethod("POST");
	            connection.setConnectTimeout(15000);
	            connection.setReadTimeout(60000);
	            connection.setDoOutput(true);
	            connection.setDoInput(true);
	            for (Entry<String,String> header : headers.entrySet()) {
					connection.setRequestProperty(header.getKey(), header.getValue());
				}
	            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	            os = connection.getOutputStream();
	            os.write(param.getBytes());
	            if (connection.getResponseCode() == 200) {
	                is = connection.getInputStream();
	                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	                StringBuffer sbf = new StringBuffer();
	                String temp = null;
	                while ((temp = br.readLine()) != null) {
	                    sbf.append(temp);
	                    sbf.append("\r\n");
	                }
	                result = sbf.toString();
	            }
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (null != br) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	            if (null != os) {
	                try {
	                    os.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	            if (null != is) {
	                try {
	                    is.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	            connection.disconnect();
	        }
	        return result;
	    }
	    
}
