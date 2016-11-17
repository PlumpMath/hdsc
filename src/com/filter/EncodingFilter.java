package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:�������봦�������-������������
 * @author zhout
 * @date 2016��4��1�� ����10:49:37
*/
public class EncodingFilter implements Filter{

	@Override
	public void destroy() {
		System.out.println("EncodingFilter destroy...");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		//������Ӧ��������
		httpServletResponse.setContentType("text/html;charset=UTF-8");
		//��������Ϊ:GET
		if(httpServletRequest.getMethod().equalsIgnoreCase("get")){
			 EncondingWrapper encondingWrapper = new EncondingWrapper(httpServletRequest);
			 chain.doFilter(encondingWrapper, httpServletResponse);
		}else{
			//����POST������������
			httpServletRequest.setCharacterEncoding("UTF-8");
			//ͨ������������ת����һ����������url
			chain.doFilter(httpServletRequest, httpServletResponse);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("EncodingFilter init...");
	}
}
