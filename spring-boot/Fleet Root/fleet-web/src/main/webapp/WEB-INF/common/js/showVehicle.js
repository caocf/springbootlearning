(function(f,$,w){
	function addEnterEvent(){
		$("form").bind("keydown",function(e){
			e=e||window.event;
			var key = e.which||e.keycode;
	            if (key == 13) {
	            		$("input[name=page]").val(0);
	            		$(this).submit();
	            }
		});
	}
	function addLoginBtnEvent(){
		$(".searchbtn").click(function(e){
			$("input[name=page]").val(0);
			$("form[name=fm]").submit();
			return !1;
		});
	}
	
	function addLocationEvent(){
		$("a.underline").each(function(){
			$(this).click(function(){
				var vin = $(this).attr("vin");
				$("form[name=mlForm] input[name=vin]").val(vin);
				$("form[name=mlForm]")[0].submit();
//				location=f.locationPath+"/monitorLocation?vin="+vin;
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
	function addOrderEvent(){
		var co = f.currentOrder;
		var coe = $("th[order="+co.property.replace(".",'\\.')+"]");
		if(co&&coe.length>0){
			coe.find("i.ico").eq(0).append("<span class='filter "+(co.direction=='DESC'?'':"current")+"'></span>");
		}
		$("th[order]").each(function(){
			$(this).click(function(){
				var property,direction='DESC';
				if(co.property==$(this).attr("order")){
					direction = co.direction=='DESC'?'ASC':'DESC';
				}
				property = $(this).attr("order");
				$("form[name=fm]")[0].reset();
				$("input[name=orders\\[0\\]\\.property").val(property);
				$("input[name=orders\\[0\\]\\.direction").val(direction);
				$("form[name=fm]").submit();
			});
		});
	}
	function _init(){
		addEnterEvent();
		addLoginBtnEvent();
		addLocationEvent();
		registerSetPage();
		addOrderEvent();
	}
	_init();
})(window.Fsp,jQuery,window);