package com.behere.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.behere.common.exception.ParameterException;

public class RequestUtils {
	private static final Logger logger = LoggerFactory.getLogger(RequestUtils.class);
	
	
	public static void loggerParams(HttpServletRequest request){
		StringBuffer sbuf = new StringBuffer();
        Enumeration<String> en = request.getParameterNames();
		boolean b=true;
		while(en.hasMoreElements()){
			String t = b?"?":"&";
			String name=en.nextElement();
			String value=request.getParameter(name);
			sbuf.append(t+name+"="+value);
			if(b) {
				b=false;
			}
		}
		logger.info(sbuf.toString());
	}
	
	public static Map<String, String> parseParameterMap(String m) throws UnsupportedEncodingException {
		Map<String, String> parameterMap = null;
		if (!StringUtils.isEmpty(m)) {
			String str = Des3.decode(m);
			logger.debug(str);
			try {
				parameterMap = Function.parseParameter(str);
			} catch (ParameterException e) {
				e.printStackTrace();
			}
		}
		return parameterMap;
	}
	
	public static PageData parseParameterPageData(String m) throws UnsupportedEncodingException {
		PageData pageData = new PageData();
		if (!StringUtils.isEmpty(m)) {
			String str = Des3.decode(m);
			logger.debug(str);
			try {
				pageData = Function.parseParameterPageData(str);
			} catch (ParameterException e) {
				e.printStackTrace();
			}
		}
		return pageData;
	}
	
	
	@SuppressWarnings("unchecked")
	public static <T extends Param> T  parseParameter(String m,Class<T> clazz) throws UnsupportedEncodingException {
		T param = null;
		Map<String, String> parameterMap = parseParameterPageData(m);
		if (parameterMap != null) {
			BeanRequestPicker<T> brp = new BeanRequestPicker<T>(clazz, parameterMap);
			param = brp.handleMap(clazz);
		}
		return param;
	}
	
	public static String getQueryString(HttpServletRequest request,boolean encode){
		StringBuffer sbuf = new StringBuffer();
        Enumeration<String> en = request.getParameterNames();
		boolean b=true;
		while(en.hasMoreElements()){
			String t = b?"?":"&";
			String name=en.nextElement();
			String value=request.getParameter(name);
			try {
				sbuf.append(t+name+"="+(encode?URLEncoder.encode(value,"utf-8"):value));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if(b) {
				b = false;
			}
		}
		return sbuf.toString();
		
	}
	
	public static String getQueryString(HttpServletRequest request){
		return getQueryString(request, false);
		
	}
	
	public static String requestStr(HttpServletRequest request){
		StringBuffer sbuf = new StringBuffer();
		Enumeration<String> en = request.getParameterNames();
		boolean b=true;
		while(en.hasMoreElements()){
			String t = b?"":"&";
			String name=en.nextElement();
			String value=request.getParameter(name);
			if(!"url".equals(name)){
				sbuf.append(t+name+"="+value);
				if(b) {
					b=false;
				}
			}
			
		}
		return sbuf.toString();
	}
	
//	public static void main(String[] args) {
//		Users user = new Users();
//		String m = Des3.encode("userName=Behere&password=123456");
//		try {
//			user = parseParameter(m, Users.class);
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		System.out.println(user.getUserName());
//	}
}