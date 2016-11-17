package com.index.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.index.model.IndexDao;
import com.index.model.Page;
import com.javacommon.util.ConnectionUtil;
import com.javacommon.util.Msg;
import com.javacommon.util.TaskUtil;
import com.task.model.Task;

/**
 * @Description:TODO
 * @author zhout
 * @date 2016年4月15日 下午5:04:26
*/
public class IndexService {

	private Connection connection;

	public IndexService() {
		this.connection = new ConnectionUtil().openConn();
	}

	
	/**
	 * @Description: 分页查询
	 * @param pageQuery
	 * @param user_id
	 * @param request   
	 * @author zhout 
	 * @date 2016年4月18日 下午3:06:35
	 */
	private void queryNextPage(Page pageQuery,int user_id,HttpServletRequest request){
		String str = "SELECT t.id id,t.task_name name,t.state state FROM tasks t WHERE ? = t.user_id LIMIT ?,?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = connection.prepareStatement(str);
			stmt.setInt(1,user_id);
			stmt.setInt(2,(pageQuery.getPageNum()-1)*pageQuery.getPageSize());
			stmt.setInt(3,pageQuery.getPageSize());
			rs = stmt.executeQuery();
			List<Task> task = new ArrayList<Task>();
			while(rs.next()){
				Task t = new Task();
				t.setId(rs.getInt("id"));
				t.setTaskName(rs.getString("name"));
				int state = rs.getInt("state");
				t.setState(state);
				if(state==1){
					t.setStateString("任务已完成");
				}else{
					t.setStateString("任务执行中");
				}
				task.add(t);
			}
			IndexDao indexDao = (IndexDao) request.getSession().getAttribute("indexDao");
			indexDao.setTasks(task);
			//System.out.println(JSON.toJSONString(indexDao));
			request.getSession().setAttribute("indexDao",indexDao);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(null != rs){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(null != stmt){
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(null != connection){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * @Description: 加载首页
	 * @param pageQuery
	 * @param user_id
	 * @param request   
	 * @author zhout 
	 * @date 2016年4月18日 下午3:06:49
	 */
	public void indexList(Page pageQuery,int user_id,HttpServletRequest request){
		if(null == pageQuery){
			IndexDao indexDao = null;
			String str = "SELECT  t.id id,t.task_name name,t.state state FROM tasks t WHERE ? = t.user_id";
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try{
				stmt = connection.prepareStatement(str);
				stmt.setInt(1,user_id);
				rs = stmt.executeQuery();
				List<Task> task = new ArrayList<Task>();
				int total = 0;
				int index = 0;
				int done = 0;
				int doing = 0;
				while(rs.next()){
					Task t = new Task();
					t.setId(rs.getInt("id"));
					t.setTaskName(rs.getString("name"));
					int state = rs.getInt("state");
					t.setState(state);
					if(state==1){
						t.setStateString("任务已完成");
						done++;
					}else{
						t.setStateString("任务执行中");
						doing++;
					}
					if(index<5){
						task.add(t);
						index++;
					}
					total++;
				}
				indexDao = new IndexDao();
				indexDao.setTasks(task);
				indexDao.setTotalTask(total);
				indexDao.setTask_done(done);
				indexDao.setTask_ing(doing);
				int pageNum = total%5==0?(total/5):(total/5+1);
				List<Integer> page = new ArrayList<Integer>();
				for(int i=1;i<=pageNum;i++){
					page.add(i);
				}
				indexDao.setPage(page);
				System.out.println(JSON.toJSONString(indexDao));
				request.getSession().setAttribute("indexDao", indexDao);
			} catch(SQLException sqle){
				sqle.printStackTrace();
			} finally{
				if(null != rs){
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(null != stmt){
					try {
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(null != connection){
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}else{
			queryNextPage(pageQuery,user_id,request);
		}
	}
}
