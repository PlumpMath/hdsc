<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="modal fade" id="Dialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="false">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3 id="msg">Settings</h3>
                </div>
                <div class="modal-body">
					<table class="table table-bordered table-striped table-condensed">
                        <thead>
                        <tr>
                            <th>任务名</th>
                            <th>所属用户</th>
                            <th>结束时间</th>
                            <th>执行状态</th>
                            <th>更多操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>测试-1</td>
                            <td class="center">admin</td>
                            <td class="center">2016-03-06 12:00</td>
                            <td class="center">
                            	<span class="label-success label label-default">执行中</span>
                            </td>
                            <td class="center">
                               <a class="btn btn-info" href="#" style="width:60px;height:25px;padding:0;">
                					<i class="glyphicon glyphicon-edit icon-white"></i>&nbsp;编辑
            					</a>
                                <a class="btn btn-danger" href="#" style="width:60px;height:25px;padding:0;">
                                    <i class="glyphicon glyphicon-trash icon-white"></i>&nbsp;删除
                                </a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <a href="#" class="btn btn-primary" data-dismiss="modal">取消</a>
                    <a href="#" class="btn btn-primary" data-dismiss="modal">保存</a>
                </div>
            </div>
        </div>
</div>