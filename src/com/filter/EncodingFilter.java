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
 * @Description:中文乱码处理过滤器-处理所有请求
 * @author zhout
 * @date 2016年4月1日 下午10:49:37
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
		//处理响应乱码问题
		httpServletResponse.setContentType("text/html;charset=UTF-8");
		//请求类型为:GET
		if(httpServletRequest.getMethod().equalsIgnoreCase("get")){
			 EncondingWrapper encondingWrapper = new EncondingWrapper(httpServletRequest);
			 chain.doFilter(encondingWrapper, httpServletResponse);
		}else{
			//处理POST请求乱码问题
			httpServletRequest.setCharacterEncoding("UTF-8");
			//通过过滤器链跳转到下一个过滤器或url
			chain.doFilter(httpServletRequest, httpServletResponse);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("EncodingFilter init...");
	}
}
