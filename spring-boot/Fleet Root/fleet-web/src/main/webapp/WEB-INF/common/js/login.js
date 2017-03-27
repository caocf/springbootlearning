(function(f,$,w){
	function addEnterEvent(){
		$("form").bind("keydown",function(e){
			e=e||window.event;
			var key = e.which||e.keycode;
	            if (key == 13) {
	            		$(this).submit();
	            }
		});
	}
	function addLoginBtnEvent(){
		$(".loginbtn").click(function(e){
			$("form[name=fm]").submit();
			return !1;
		});
	}
	
	function addCaptchaBtnEvent(){
		$(".refresh,.vcode").each(function(){
			$(this).click(function(e){
				$(".vcode").attr("src",Fsp.contextPath+"/captcha?t="+new Date().getTime());
				return !1;
			});
		});
	}
	
	function addSendSecCodeEvent() {
		$("#sendSecurityCode").click(function() {
			$("#sendSecurityCode").unbind('click').removeAttr("onclick");
			if($("input[name=username]").val() == "") {
				$("input[name=username]").next().html(Fsp.messages.username.required);
				addSendSecCodeEvent();
				return;
			}
			btnTimer();
			var data = $("form[name=fm]").serialize();
			$.post(Fsp.contextPath+"/sendSecurityCode", data, function(res) {
				if(res.success) {
					$(".secode").html(res.data);
				}else {
					$(this).next().next().html(Fsp.messages.securityCode.getFailed);
				}
			});
		});
	}
	
	function btnTimer() {
		var time = 60;
		var timer = setInterval(function() {
			time--;
			if(time == 0) {
				clearInterval(timer);
				addSendSecCodeEvent();
				$("#sendSecurityCode").html(Fsp.messages.securityCode.get);
			}else {
				$("#sendSecurityCode").html(time+Fsp.messages.securityCode.getting);
			}
		},1000);
	}
	
	function addValidator(){
		var rules = {
				username:{
					required:!0
				},
				password:{
					required:!0
				},
				securityCode:{
					required:!0
				}
			};
		if($(".refresh").length>0){
			rules.captcha={
				required:!0
			};
		}
		$("form[name=fm]").validate({
//			onkeyup:!1,
			rules:rules,
			messages:Fsp.messages,
			 errorPlacement: function(error, element) {  
				 if($(element).attr("name") == "securityCode") {
					 element.next().next().html(error.html());
				 }else {
					 element.next().html(error.html());
				 }
			 },
			 onfocusout: function( element) {
					$(element).valid();
			},
			success: function(label) {  
				if($(label).attr("for") == "securityCode") {
					$("[name="+$(label).attr("for")+"]").next().next().html("");
				}else {
					$("[name="+$(label).attr("for")+"]").next().html("");
				}
		     }
		});
	}
	function _initForgetPwd(){
		$('#forgetPwd').hover(function() {
			$(this).prev().show();
		}, function() {
			$(this).prev().hide();
		})
	}
	function _init(){
		addEnterEvent();
		addLoginBtnEvent();
//		addCaptchaBtnEvent();
		addSendSecCodeEvent();
		addValidator();
		_initForgetPwd();
	}
	_init();
})(window.Fsp,jQuery,window);