<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<style type="text/css">
	.error{color:#b50000;}
</style>
<div class="modal fade" id="originalRule" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="false">
        <div class="modal-dialog">
            <div class="modal-content" style="width:610px;">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" >x</button>
                    <h3>系统默认BPF规则</h3>
                </div>
                <div class="modal-body">
					 <form class="form-horizontal" role="form">
<!-- 					  <div class="form-group"> -->
<!-- 					    <label for="username" class="col-sm-3 control-label">规则名称 :</label> -->
<!-- 					    <div class="col-sm-9"> -->
<!-- 					      <input type="text" class="form-control" id="old_bpf_name"> -->
<!-- 					    </div> -->
<!-- 					  </div> -->
					  <div class="form-group">
					    <label for="originalPwd" class="col-sm-3 control-label">规则详情 :</label>
					    <div class="col-sm-9">
					      <input type="text" class="form-control" id="old_bpf">
					    </div>
					  </div>
					</form>
                </div>
            </div>
        </div>
</div>