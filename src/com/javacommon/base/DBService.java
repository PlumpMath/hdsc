package com.javacommon.base;

import java.util.List;

/**
 * @Description:数据库操作
 * @author zhout
 * @date 2016年3月27日 上午9:30:07
*/
public interface DBService {

	public List<Object> list();
	
	public int add();
	
	public int update();
	
	public int delete();
}
