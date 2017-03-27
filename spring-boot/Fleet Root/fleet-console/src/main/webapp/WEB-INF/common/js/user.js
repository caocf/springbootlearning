
(function(f,$,w){
	function initBindEvent() {
		$(".savebtn").click(function() {
			if($("form[name=fm]").valid()) {
				$(this).attr("disabled", !0);
				$(".savebtn").unbind('click').removeAttr("onclick");
				$(".savebtn").css({
					"cursor":"text"
				});
				var url = Fsp.fuPath+"/addUser";
				var message = Fsp.messages.createUser.success;
				if($("input[name='id']").val() != "") {
					url = Fsp.fuPath+"/updateUser";
					message = Fsp.messages.updateUser.success;
				}
				$.post(url, $("form[name=fm]").serialize(), function(data) {
					console?console.log(data):0;
//					clearForm();
//					$(this).attr("disabled", !1);
//					alert(message);
					if($("input[name='id']").val() != "") {
						location.href = Fsp.fuPath+"/showUsers";
					}else {
						forwardTimer();
					}
				});
			}
		});
		
		$(".restpwdbtn").click(function() {
			$("#resetpwd").show();
		});
		
		$("#resetpwd .cancel,#resetpwd .close").click(function() {
			$("#resetpwd").hide();
		});
		
		$("#resetpwd .confirm").click(function() {
			$.post(Fsp.fuPath+"/resetPwd", $("form[name=fm]").serialize(), function(data) {
				$("#resetpwd").hide();
				if(data) {
					$(".submit_info p").removeClass("err").addClass("success").html(Fsp.messages.resetPwd.success);
				}else {
					$(".submit_info p").removeClass("success").addClass("err").html(Fsp.messages.resetPwd.fail);
				}
			});
		});
		
		$(".backbtn").click(function() {
			location.href = Fsp.fuPath+"/showUsers";
		});
	}
	
	function forwardTimer() {
		var time = 3;
		$(".submit_info p").removeClass("err").addClass("success").text("新用户创建成功！且"+time+"秒后跳转到账户管理页面");
		var timer = setInterval(function() {
			time = time-1;
			if(time == 0) {
				clearInterval(timer);
				location.href = Fsp.fuPath+"/showUsers";
			}else {
				$(".submit_info p").removeClass("err").addClass("success").text("新用户创建成功！且"+time+"秒后跳转到账户管理页面");
			}
		},1000);
	}
	
	function clearForm() {
		if($("input[name='id']").val() == "") {
			$("form[name=fm] input").each(function() {
				$(this).val($(this).attr("defaultValue"));
			});
			$("form[name='fm'] .err").html("");
		}
	}
	
	function addValidator(){
		var id = $("input[name='id']").val();
		var rules = {
				"fleetDomain.name":{
					required:!0
				},
				orgId:{
					required:!0
				},
				userName:{
					required:!0,
					userNameRegex:""
				},
				email:{
					required:!0,
					emailRegex:""
				},
				mobile:{
					required:!0,
					mobileRegex:""
				}
			};
		
		if(id == "") {
			var data = {};
			data[Fsp._csrfName]= Fsp._csrfToken
			rules.userName.remote =  {
					type: "post",
					url: Fsp.fuPath+"/hasUser",
					data: data,
					dataType: "json",
					dataFilter: function(data, type) {
						if (data == "true")
							return false;
						else
							return true;
					}
			};
		}
		
		$.validator.addMethod("userNameRegex", function(value, element) {
			var userName = /^([a-zA-Z0-9]{5,25})$/;
			return this.optional(element) || (userName.test(value));
		}, Fsp.messages.userName.regex);
		
		$.validator.addMethod("mobileRegex", function(value, element) {
			var mobile = /^[0-9]{11}$/
			return this.optional(element) || (mobile.test(value)); 
		}, Fsp.messages.mobile.regex);
		
		$.validator.addMethod("emailRegex", function(value, element) {
			var email = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
			return this.optional(element) || (email.test(value));
		}, Fsp.messages.email.regex);

		$("form[name=fm]").validate({
			rules:rules,
			messages:Fsp.messages,
			 errorPlacement: function(error, element) {  
					element.next().html(error.html());
			 },
			 onfocusout: function( element) {
					$(element).valid();
			},
			success: function(label) {  
				$("[name='"+$(label).attr("for")+"']").next().html("");
		     }
		});
	}
	
	initBindEvent();
	addValidator();
})(window.Fsp,jQuery,window);