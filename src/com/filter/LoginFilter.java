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

import com.login.model.User;

/**
 * @Description:��ֹδ��¼ֱ�Ӹ���·������ҳ��
 * @author zhout
 * @date 2016��4��2�� ����9:51:47
*/
public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		System.out.println("LoginFilter destroy...");
	}

	 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		User user = (User)httpServletRequest.getSession().getAttribute("user");
		if(null == user){
			httpServletResponse.sendRedirect("../login.jsp");
			System.out.println("��¼��֤ʧ��");
		}else{//�û��ѵ�¼
			chain.doFilter(httpServletRequest, httpServletResponse);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("LoginFilter init...");
	}
	
}
