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
		$(".searchbtn").click(function(e){
			$("form[name=fm]").submit();
			return !1;
		});
	}
	
	function addLocationEvent(){
		$("a.underline").each(function(){
			$(this).click(function(){
				var vin = $(this).attr("vin");
				location=f.locationPath+"/monitorLocation?vin="+vin;
			});
		});
	}
	function registerSetPage(){
		f.Pages.registerDefaultPage(function(pageNow,pageConfig,page){
			$("form[name=fm]")[0].reset();
			$("input[name=page]").val(pageNow);
			$("form[name=fm]").submit();
		});
	}
	function _init(){
		addEnterEvent();
		addLoginBtnEvent();
		addLocationEvent();
		registerSetPage();
	}
	_init();
})(window.Fsp,jQuery,window);