package com.javacommon.base;

import java.util.List;

/**
 * @Description:���ݿ����
 * @author zhout
 * @date 2016��3��27�� ����9:30:07
*/
public interface DBService {

	public List<Object> list();
	
	public int add();
	
	public int update();
	
	public int delete();
}
