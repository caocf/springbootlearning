$(function() {
	
	var rules = {
			oldPassword:{
				required:!0
			},
			password:{
				required:!0,
				passwordRegex:""
			},
			confirmPassword:{
				required:!0,
				equalTo:"#password"
			}
	};
	
	$.validator.addMethod("passwordRegex", function(value, element) {
		var password = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,20}$/;
		return this.optional(element) || (password.test(value));
	}, Fsp.messages.password.regex);
	
	$("form[name='fm']").validate({
		rules:rules,
		messages:Fsp.messages,
		 errorPlacement: function(error, element) {  
				element.next().html(error.html());
		 },
		 onfocusout: function(element) {
				$(element).valid();
		},
		success: function(label) {  
			$("[name='"+$(label).attr("for")+"']").next().html("");
	     }
	});
	
	$(".modifypwdbtn").click(function() {
		if($("form[name='fm']").valid()) {
			$.post(Fsp.userPath+"/modifyAuth", $("form[name=fm]").serialize(), function(data) {
				console.log(data);
				$(this).attr("disabled", !1);
				if(data == "SUCCESS") {
					clearForm();
					$(".submit_info p").removeClass("err").addClass("success").html(Fsp.messages.modifyAuth.success);
				} else if(data == "VALIDATE_FAILED") {
					$(".submit_info p").removeClass("success").addClass("err").html(Fsp.messages.modifyAuth.validate_failed);
				} else {
					$(".submit_info p").removeClass("success").addClass("err").html(Fsp.messages.modifyAuth.failed);
				}
			});
		}
	});
	
	$(".firstloginbtn").click(function() {
		if($("form[name='fm']").valid()) {
			$.post(Fsp.userPath+"/firstLogin", $("form[name=fm]").serialize(), function(data) {
				console.log(data);
				$(this).attr("disabled", !1);
				if(data == "SUCCESS") {
					forwardTimer();
				}else {
					$(".submit_info p").removeClass("success").addClass("err").html("密码修改失败");
				}
			});
		}
	});
	
	$(".cancel").click(function() {
		clearForm();
	});
	
	function clearForm() {
		$("form[name=fm] input").each(function() {
			$(this).val($(this).attr("defaultValue"));
		});
		$("form[name='fm'] .err").html("");
		$(".submit_info p").removeClass("err").removeClass("success").text("");
	}
	
	function forwardTimer() {
		var time = 3;
		$(".submit_info p").removeClass("err").addClass("success").text("密码修改成功！且"+time+"秒后跳转到车辆位置监控页面");
		var timer = setInterval(function() {
			time = time-1;
			if(time == 0) {
				clearInterval(timer);
				location.href = Fsp.contextPath;
			}else {
				$(".submit_info p").removeClass("err").addClass("success").text("密码修改成功！且"+time+"秒后跳转到车辆位置监控页面");
			}
		},1000);
	}
});