
//��һҳ����¼�
function nextPage(page){
	//if(page.parent().attr("class").indexOf("active")!=-1){
		var num = Number(page.html());
		//������һҳ����
		$.ajax({
			type:"post",
			url:"${ctx}/authentication",
			data:{pageNum:num},
			success:function(result){
				window.location.reload();
				/*
				var liDoms = page.parent().parent().children();
				$.each(liDoms,function(){
					if($(this).index()==page.parent().index()){
						$(this).removeClass("active");
						$(this).addClass("disabled");
					}
					if($(this).attr("class").indexOf("disabled")!=-1&&$(this).index()!=page.parent().index()){
						$(this).removeClass("disabled");
						$(this).addClass("active");
					}
				});
				*/
			}
		});	
	//}
};
 
//�����������������
function reset(){
	$("#username").val("");
	$("#originalPwd").val("");
	$("#origin_pwd_error").html("");
	$("#newPwd").val("");
	$("#confirm").val("");
	$("#confirm_error").html("");
};
//��������
function showDialog(){
	reset();
	var isValid = false;
	//��ȡ��ǰ�û���
	var user = '${user.username}';
	$('#username').val(user);
	//ԭʼ������֤
	$("#originalPwd").blur(function(){
		var originalPwd = $(this).val();
		$.ajax({
			type:"post",
			data:{originalPwd:originalPwd,user:user},
			dataType:"json",
			url:"${ctx}/confirmPwd",
			success:function(data){
				if(!data.msg){
					$('#origin_pwd_error').html("* ԭ��������");
					$('#originalPwd').val("");
					$('#originalPwd').focus();
				}else{
					$('#origin_pwd_error').html("");
				}
			}
		});
	});
	
	//������˶�
	$("#confirm").blur(function(){
		var newPwd = $("#newPwd").val();
		var confirmPwd = $(this).val();
		if(newPwd != confirmPwd){
			$(this).val("");
			$("#newPwd").val("");
			$("#newPwd").focus();
			//����ȷ����������ܵ��������ұ߿հײ��� �������´���ʧЧ
			$("#confirm_error").html("* ���벻һ��");
		}else{
			$("#confirm_error").html("");
			isValid = true;
		}
	});
	
	//ȡ����ť����¼�
	$("#cancel").click(function(){
		isValid = false;
		//reset();�ڵ���������Ϣ���ڣ��ٴδ�����ʧ
	});
	
	//���水ť����¼�
	$("#save").click(function(){
		if(isValid){
			var o_pwd = $("#originalPwd").val();
			var n_pwd = $("#newPwd").val();
			$.ajax({
				type:"post",
				data:{user:user,oldP:o_pwd,newP:n_pwd},
				dataType:"json",
				url:"${ctx}/updatePwd",
				success:function(data){
					if(!data.msg){
						alert("�����޸�ʧ��");
					}else{
						alert("�����޸ĳɹ�");
					}
				}
			});
			reset();
		}
	});
	
	//�����������ô���
    $('#updatePwd').modal('show');
};

//ע��ȷ��
function logoutConfirm(){
	 $('#logout_confirm').modal('show');
	 $("#quit").click(function(){
		 //$.post("${ctx}/logout");
		 //$.ajax({
         //type:"post",
         //url:"${ctx}/logout",
         //async:false
         //});
         //�������ַ�ʽִ����response��������ת����¼ ԭ����
         window.location.href="${ctx}/logout";
	 });
};

function showMsg(){alert(1)};

//ע��鿴���� ɾ���¼�
/* ע��ʧ�ܡ�����
function registerEvent(){
	alert(1);
	$("#seeDetail").click(function(){
		alert("�鿴����");
	});
	alert(2);
	$("#delete").click(function(){
		var status = $(this).parent().prev().children().html();
		alert(status);
	});
};
registerEvent();
*/
function delConfirm(obj){   
	var status = obj.parent().prev().children().html();
	if(status=="����ִ����"){
		$("#warn_msg").html("����ִ���У�����ɾ����");
		$('#warn').modal('show');
	}else{
		var id = obj.parent().parent().find("input[name='taskId']").val();
		$("#delId").val(id);
		$("#deleteConfirm").modal('show');	
	}
};

function deleteTask(){	
	var taskId = $("#delId").val();
	//alert(taskId);
	$.ajax({
		type:"post",
		url:"${ctx}/task/delete",
		data:{id:taskId},
		success:function(result){
			if(result.msg){
// 						var page = $("li[class='disabled']");
// 						if(page.length>1){
// 							page = page.eq(1).find("a").html();
// 						}else{
// 							page = page.eq(0).find("a").html();
// 						}
// 						$.ajax({
// 							type:"post",
// 							url:"${ctx}/authentication",
// 							data:{pageNum:page},
// 							success:function(result){
// 								window.location.reload();
// 							}
// 						});
				window.location.reload();
			}else{
				alert("����ɾ��ʧ��");
			}
		}
	});
};

function seeDetail(obj){
	var id = obj.parent().parent().find("input[name='taskId']").val();
	$.ajax({
		type:"post",
		dataType:"json",
		url:"${ctx}/task/seeDetail",
		data:{taskId:id},
		success:function(result){
			if(result.error==""){
			    // ����׼���õ�dom����ʼ��echartsͼ��
				var container = $("#data");
		        var myChart = echarts.init(container);	
		        $.each(option,function(key,value){
		        	if(key=="series"){
		        		value[0].data=result.dataArray;
		        		return false;
		        	}
		        });
		        myChart.setOption(option); 
				/*
				$("#totoal").val(result.packetNumbers).attr({"readonly":true});
    			$("#tcp").val(result.tcpNumbers).attr({"readonly":true});
    			$("#udp").val(result.tcpNumbers).attr({"readonly":true});
    			$("#icmp").val(result.tcpNumbers).attr({"readonly":true});
    			$("#arp").val(result.tcpNumbers).attr({"readonly":true});
    			$("#flow").val(result.tcpNumbers).attr({"readonly":true});
    			$("#size").val(result.tcpNumbers).attr({"readonly":true});
    			
    			*/
		       $("#taskStatus").modal('show'); 
			}else{
				alert("����ִ��ʧ�ܣ�BPF����:"+result.bpf);	
			}
		}
	});
};
