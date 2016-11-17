package com.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @Description:װ���ࣺ����װ��request���� �ڹ��������滻ԭ�е��������
 * @author zhout
 * @date 2016��4��1�� ����11:27:31
*/
public class EncondingWrapper extends HttpServletRequestWrapper{

	//�����ṩ������췽�� ���ұ�����ø���Ĺ�����
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
