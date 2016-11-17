package com.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @Description:装饰类：用来装饰request对象 在过滤器中替换原有的请求对象
 * @author zhout
 * @date 2016年4月1日 下午11:27:31
*/
public class EncondingWrapper extends HttpServletRequestWrapper{

	//必须提供这个构造方法 而且必须调用父类的构造器
	public EncondingWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);
		if(null == value){
			return null;
		}else{
			byte[] c = null;
			try {
				c = value.getBytes("ISO-8859-1");
				value = new String(c,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return value;
	}
}
