package com.smart.commom;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Properties;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang3.StringUtils;

public class HttpClientUtil {

	public static final String CHARACTER_ENCODING = "UTF-8";

	/**
	 * post 请求，返回String的数据包
	 * 
	 * @param url
	 * @param requestData
	 * @return
	 * @throws Exception
	 */
	public static String httpRequest(String url, String requestData) throws Exception {
		if (StringUtils.isEmpty(requestData)) {
			return httpRequest(url, null, null);
		}
		return httpRequest(url, requestData.getBytes(CHARACTER_ENCODING), null);
	}

	/**
	 * HTTPS 请求，返回String的数据包
	 * 
	 * @param url
	 * @param requestData
	 * @return
	 * @throws Exception
	 */
	public static String httpsRequest(String url, String requestData) throws Exception {
		if (StringUtils.isEmpty(requestData)) {
			return httpsRequest(url, null, null);
		} else {
			return httpsRequest(url, requestData.getBytes(CHARACTER_ENCODING), null);
		}

	}

	/**
	 * post请求并返回数据包
	 * 
	 * @param url
	 *            请求url
	 * @param requestData
	 *            请求数据
	 * @param requestProperties
	 *            请求包体
	 * @return byte[] 数据包
	 * @throws Exception
	 */
	public static String httpRequest(String url, byte[] requestData, Properties requestProperties) throws Exception {
		HttpURLConnection httpConn = null;
		StringBuffer sBuffer = new StringBuffer("");
		try {
			httpConn = (HttpURLConnection) new URL(url).openConnection();
			// 封住包体
			if (requestProperties != null) {

			}
			String length = "0";
			if (requestData != null) {
				length = Integer.toString(requestData.length);
			}
			httpConn.setConnectTimeout(15000);
			httpConn.setRequestMethod("POST");
			httpConn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");

			httpConn.setRequestProperty("Connection", "close");
			httpConn.setRequestProperty("Content-Length", length);
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);
			OutputStream outStream = httpConn.getOutputStream();
			outStream.write(requestData);

			outStream.flush();
			outStream.close();

			BufferedReader in = null;
			String inputLine = null;

			in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), CHARACTER_ENCODING));
			while ((inputLine = in.readLine()) != null) {
				sBuffer.append(inputLine);
			}

			in.close();

		} catch (Exception e) {
			throw e;
		} finally {
			if (httpConn != null) {
				httpConn.disconnect();
				httpConn = null;
			}
		}
		return sBuffer.toString();
	}

	private static class TrustAnyTrustManager implements X509TrustManager {

		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}

	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	/**
	 * https请求(post和get都可以用)
	 * 
	 * @param url
	 * @param requestData
	 * @param requestProperties
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public static String httpsRequest(String url, byte[] requestData, Properties requestProperties) throws Exception {

		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
		HttpsURLConnection httpsConn = null;
		StringBuffer sBuffer = new StringBuffer("");
		try {
			httpsConn = (HttpsURLConnection) new URL(url).openConnection();
			// 封住包体
			if (requestProperties != null) {

			}
			String length = "0";
			if (requestData != null) {
				length = Integer.toString(requestData.length);
			}
			httpsConn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			httpsConn.setSSLSocketFactory(sc.getSocketFactory());
			httpsConn.setConnectTimeout(15000);
			httpsConn.setDoInput(true);
			httpsConn.setDoOutput(true);
			// OutputStream outStream = httpsConn.getOutputStream();
			BufferedOutputStream hurlBufOus = new BufferedOutputStream(httpsConn.getOutputStream());
			hurlBufOus.write(requestData);

			hurlBufOus.flush();
			hurlBufOus.close();
			httpsConn.connect();
			System.out.println(httpsConn.getResponseCode());
			BufferedReader in = null;
			String inputLine = null;

			in = new BufferedReader(new InputStreamReader(httpsConn.getInputStream(), CHARACTER_ENCODING));
			while ((inputLine = in.readLine()) != null) {
				sBuffer.append(inputLine);
			}

			in.close();

		} catch (Exception e) {
			throw e;
		} finally {
			if (httpsConn != null) {
				httpsConn.disconnect();
				httpsConn = null;
			}
		}
		System.out.println(sBuffer.toString());
		return sBuffer.toString();
	}

	/**
	 * post请求
	 * 
	 * @param urlstring
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public static String doPost(String urlstring, Map<String, String> parameters) throws Exception {
		URL url = new URL(urlstring);// 提交地址
		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		httpURLConnection.setDoOutput(true);// 打开写入属性
		httpURLConnection.setDoInput(true);// 打开读取属性
		httpURLConnection.setRequestMethod("POST");// 设置提交方法
		httpURLConnection.setConnectTimeout(50000);// 连接超时时间
		httpURLConnection.setReadTimeout(50000);
		httpURLConnection.connect();
		StringBuilder s = new StringBuilder();
		String[] array = parameters.keySet().toArray(new String[0]);
		for (int i = 0; i < array.length; i++) {
			s.append(array[i] + "=" + parameters.get(array[i]));
			if (i != array.length - 1) {
				s.append("&");
			}
		}
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(), "utf-8"));
		out.write(s.toString());
		out.flush();
		out.close();

		// 读取post之后的返回值
		BufferedReader in = new BufferedReader(new InputStreamReader((InputStream) httpURLConnection.getInputStream(), "utf-8"));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = in.readLine()) != null) {
			sb.append(line);
		}
		in.close();
		httpURLConnection.disconnect();// 断开连接
		return sb.toString();
	}

	public static String sendGetRequest(String path) {
		String content = "";
		try {
			URL url = new URL(path);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setConnectTimeout(5000);
			httpURLConnection.setRequestMethod("GET");
			if (httpURLConnection.getResponseCode() == 200) {
				InputStream is = httpURLConnection.getInputStream();
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				byte[] buf = new byte[1024];
				int len = 0;
				while ((len = is.read(buf)) != -1) {
					bos.write(buf, 0, len);
				}
				byte[] data = bos.toByteArray();
				bos.flush();
				bos.close();
				is.close();
				content = new String(data);
				if (content.contains(CHARACTER_ENCODING)) {
					content = new String(data, CHARACTER_ENCODING);
				}
			}
			httpURLConnection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

	/**
	 * 
	 * (post json请求). <br/>
	 *
	 * @author gaowenming
	 * @param postUrl
	 * @param json
	 * @since JDK 1.8
	 */
	public static void postJson(String postUrl, String json) {

		try {
			// 创建连接
			URL url = new URL(postUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type", "application/json");

			connection.connect();

			// POST请求
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.writeBytes(json);
			out.flush();
			out.close();

			// 读取响应
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String lines;
			StringBuffer sb = new StringBuffer("");
			while ((lines = reader.readLine()) != null) {
				lines = new String(lines.getBytes(), "utf-8");
				sb.append(lines);
			}
			System.out.println(sb);
			reader.close();
			// 断开连接
			connection.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}