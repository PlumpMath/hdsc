package com.admin.init;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InitTaskService {

	private static ExecutorService taskPool = Executors.newFixedThreadPool(1);
	
	public void triggerTaskListener(){
		//taskPool.execute(new TriggerTaskThread());
	}
	
	class TriggerTaskThread implements Runnable{
		@Override
		public void run() {//调用Linux脚本触发监听器
			Process process = null;  
	        List<String> processList = new ArrayList<String>();  
	        try {  
	        	String cmd = new String("chmod 777 /usr/local/test.sh");
	            process = Runtime.getRuntime().exec(cmd);  
	            int exitValue = process.waitFor();  
	            if(0 != exitValue) {  
	                System.out.println("call shell failed. error code is :" + exitValue);  
	            }else{ 
	            	BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));  
		            String line = "";  
		            while ((line = input.readLine()) != null) {  
		                processList.add(line);  
		            }  
		            if(null != input){
		            	input.close();
		            }
		            for (String list : processList) {  
			            System.out.println(list);  
			        }  
	            }
	        } catch (Throwable e) {  
	            e.printStackTrace();
	        }  
		}
	}
}
