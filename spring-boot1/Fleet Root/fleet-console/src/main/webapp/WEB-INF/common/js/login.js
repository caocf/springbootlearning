(function(f,$,w){
	function addEnterEvent(){
		$("form").bind("keydown",function(e){
			e=e||window.event;
			var key = e.which||e.keycode;
	            if (key == 13) {
	            	this.submit();
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
	
	function addValidator(){
		var rules = {
				username:{
					required:!0
				},
				password:{
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
					element.next().html(error.html());
			 },
			 onfocusout: function( element) {
					$(element).valid();
			},
			success: function(label) {  
				$("[name="+$(label).attr("for")+"]").next().html("");
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
		addCaptchaBtnEvent();
		addValidator();
		_initForgetPwd();
	}
	_init();
})(window.Fsp,jQuery,window);