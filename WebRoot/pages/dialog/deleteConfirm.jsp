<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div id="deleteConfirm" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="false">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
	    <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">×</button>
	        <h3 style="color:#C10D14;">您 确 认 要 删 除 吗？</h3>
	    </div>
	    <div class="modal-footer">
            <a href="#" class="btn btn-default" data-dismiss="modal">取消</a>
			<!--
			    <a href="../logout" class="btn btn-primary" data-dismiss="modal">确定</a> 
			    以上方式路径正确，不能跳转    
			-->
            <a href="#" onclick="deleteTask()" id="delete" class="btn btn-primary" data-dismiss="modal">确定</a>
        </div>
    </div>
  </div>
</div>