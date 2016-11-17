<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<style type="text/css">
	.error{color:#b50000;}
</style>
<div class="modal fade" id="updatePwd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="false">
        <div class="modal-dialog">
            <div class="modal-content" style="width:610px;">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" ></button>
                    <h3>密码重置</h3>
                </div>
                <div class="modal-body">
					 <form class="form-horizontal" role="form">
					  <div class="form-group">
					    <label for="username" class="col-sm-3 control-label">用户名:</label>
					    <div class="col-sm-6">
					      <input type="text" class="form-control" id="username">
					    </div>
					    <label class="col-sm-3 control-label error"></label>
					  </div>
					  <div class="form-group">
					    <label for="originalPwd" class="col-sm-3 control-label">旧密码:</label>
					    <div class="col-sm-6">
					      <input type="password" class="form-control" id="originalPwd">
					    </div>
					    <label id="origin_pwd_error" class="col-sm-3 control-label error"></label>
					  </div>
					  <div class="form-group">
					    <label for="newPwd" class="col-sm-3 control-label">新密码:</label>
					    <div class="col-sm-6">
					      <input type="password" class="form-control" id="newPwd">
					    </div>
					    <label class="col-sm-3 control-label error"></label>
					  </div>
					  <div class="form-group">
					    <label for="confirm" class="col-sm-3 control-label">确认新密码:</label>
					    <div class="col-sm-6">
					      <input type="password" class="form-control" id="confirm">
					    </div>
					    <label id="confirm_error" class="col-sm-3 control-label error"></label>
					  </div>
					</form>
                </div>
                <div class="modal-footer">
                    <a href="#" class="btn btn-primary" data-dismiss="modal" id="cancel" style="margin-right:30px;">取消</a>
                    <a href="#" class="btn btn-primary" data-dismiss="modal" id="savePWD">保存</a>
                </div>
            </div>
        </div>
</div>