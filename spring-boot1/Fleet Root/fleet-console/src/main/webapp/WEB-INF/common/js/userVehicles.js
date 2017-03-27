var properties = ["vin","vehicleDomain.model","vehicleDomain.plateId","vehicleDomain.driver",
                  "vehicleDomain.telephone","status","bindingDate"];

$(function() {
//	$("iframe").load(function(a) {
//		console.log($("script").eq(0).attr("id")+">...........");
//	});
	
	$(".tableform table th[sort!='false']").click(function() {
		sort(this);
	});
	
	$(".searchbtn").click(function() {
		$("input[name='page']").val(0);
		$("form[name='fm']").submit();
	});
	
	$("#bangding .files").change(function() {
		$("#bangding input[type='text']").val($(this).val());
	});
	
	$(".bangding").click(function() {
		$("#bangding").show();
	});
	
	$("#bangding .close").unbind('click').removeAttr("onclick").click(function() {
		closeBangding(false);
	});
	
	$("#bangding .btn1").click(function() {
		if(!$("#bangding .files").val() || $("#bangding .files").val() == "") {
			return;
		}
		$("#bangding .loading").show();
//		$("#bangding .files").attr("disabled", !0);
		$("#bangding .btn1").attr("disabled", !0);
		$("#bangding .submit_info p").removeClass("success").removeClass("err").text("");
		$("#bangding form").submit();
//		setTimeout(doCheckTimer(), 1000);
	});
	
	$("#jiebang .close,#jiebang .cancel").click(function() {
		$("#jiebang").hide();
	});
	
	registerSetPage();
	initSort();
});

function closeBangding(temp) {
	$("#bangding").hide();
	$("#bangding .loading").hide();
	$("#bangding input[type='text']").val("");
	$("#bangding input[type='file']").val("");
	$("#bangding .btn1").attr("disabled", !1);
	$("#bangding .submit_info p").removeClass("success").removeClass("err").text("");
	if(temp == true) {
		$(".searchbtn").click();
	}
}

function doCheckTimer() {
	var time = 1500;
	Fsp.timer = setInterval(function() {
		
		var ajaxTimeoutTest = $.ajax({
			url: Fsp.vehiclePath+"/isSuccResponse",
			timeout: time,
			type: 'get',
			data:{},
			dataType:'json',
			success: function(data){
				console.log(data);
				if(data+"" != "" && data != null) {
					clearInterval(Fsp.timer);
					if(data) {
						$("#bangding .btn1").attr("disabled", !1);
						$("#bangding .loading").hide();
						$("#bangding .submit_info p").removeClass("err").addClass("success").text("上传成功，详情请查看下载的excel文件");
					}
				}
			},
			complete: function(XMLHttpRequest,status){
				if(status=='timeout'){//超时,status还有success,error等值的情况
					ajaxTimeoutTest.abort();
				}
			}
		});
		
	},time);
}

function registerSetPage(){
	Fsp.Pages.registerDefaultPage(function(pageNow,pageConfig,page){
		$("input[name=page]").val(pageNow);
		$("form[name=fm]").submit();
	});
}

function initSort() {
	var property = $("input[name='orders[0].property']").val();
	var index = -1;
	for(var i=0;i<properties.length;i++){
		if(properties[i]==property){
			index = i;
			break;
		}
	}
	
	if(index < 0) {
		return;
	}
	
	var current = $(".tableform table th[sort!='false']").eq(i);
	if($("i", current).length != 0) {
		current = $("i", current).eq(0);
	}
	var htm = current.html();
	var direction = $("input[name='orders[0].direction']").val();
	htm += (direction == "DESC" ? "<span class='filter'></span>" : "<span class='filter current'></span>");
	current.html(htm);
}

function sort(ele) {
	var index  = $(".tableform table th[sort!='false']").index(ele);
	var direction = $("input[name='orders[0].direction']").val().toLowerCase()=='desc'?'ASC':"DESC";
	if($("input[name='orders[0].property']").val() != properties[index]) {
		direction = "DESC"
	}
	$("input[name='orders[0].property']").val(properties[index]);
	$("input[name='orders[0].direction']").val(direction);
	$("form[name='fm']").submit();
}

function toEditVehiclePage(id) {
	$("form[name='fm'] input[name='id']").val(id);
	$("form[name='fm']").attr("action", Fsp.vehiclePath+"/toEditVehiclePage").submit();
}

function releaseBind(id) {
	$("#jiebang").show();
	$("#jiebang .confirm").unbind('click').click(function() {
		var data = {id:id};
		$.get(Fsp.vehiclePath+"/releaseBind", data, function(msg) {
			if(msg == "SUCCESS") {
				$("#jiebang").hide();
				$("form[name='fm']").submit();
			}else {
				alert(msg);
			}
		});
	});
}

function bindVehicleCallback(status, msg) {
	$("#bangding .btn1").attr("disabled", !1);
	$("#bangding .loading").hide();
	if(status == "Success") {
//		$("#bangding").hide();
//		$("form[name='fm'] input[name='msg']").val(msg);
		$("#bangding .close").unbind('click').removeAttr("onclick").click(function() {
			closeBangding(true);
		});
		$("#bangding .submit_info p").removeClass("err").addClass("success").html("文件上传成功，请<font style='font-size:14px;'><a href='javascript:;' style='text-decoration:underline;' onclick='downloadBind();'>下载</a></font>文件查看详情");
	}else {
		$("#bangding .submit_info p").removeClass("success").addClass("err").text(Fsp.messages[msg].required);
	}
}

function downloadBind() {
	$("iframe[name='frameFile']").attr("src", window.Fsp.vehiclePath+"/downloadBindResults");
}