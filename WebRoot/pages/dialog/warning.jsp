<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div id="message" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="false">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
	    <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">×</button>
	        <h3 style="width:100px;margin:0 auto;color:#b00;font:28px 600;text-align:center;">警&nbsp;告</h3>
	    </div>
	    <div class="modal-body">
	       <span style="color:rgb(153,27,27);font-size:18px;display:block;width:270px;margin:0 auto;text-align:center;" id="msg"></span>
	    </div>
	    <div class="modal-footer">
            <a href="#" class="btn btn-default" data-dismiss="modal" id="cancel">取消</a>
            <a href="#" class="btn btn-primary" data-dismiss="modal" id="save">确定</a>
        </div>
    </div>
  </div>
</div>