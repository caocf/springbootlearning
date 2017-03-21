(function(f,$,w){
	var map,SosMap,page,currentOrder,zoom=18;
	function _initMap(){
		map = f.amap = new AMap.Map("mapContainer",{resizeEnable: !0,level:11,zoom: 11});   
		//地图中添加地图操作ToolBar插件
		map.plugin(['AMap.ToolBar'], function() {
	        //设置地位标记为自定义标记
	        var toolBar = new AMap.ToolBar();
	        map.addControl(toolBar);
	        // 加载比例尺插件
	        map.plugin(["AMap.Scale"], function () {
	         var scale = new AMap.Scale();
	          map.addControl(scale);
	        });
	    });
	}
	var _SosMap = function(){
		var marker;
		var clearMap = this.clearMap=function(){
			marker?marker.setMap(null):!1;
		}
		this.setVehicleLocation=function(location){
			clearMap();
			marker = new AMap.Marker({
				position: [location.lon, location.lat],
				icon: new AMap.Icon({
					size: new AMap.Size(25, 25),
					image: f.imgPath+"/current_location.png" 
//					imageOffset: new AMap.Pixel(-153, -138)
				})
			});
			marker.setMap(map);
			marker.vehicleLoaction = location;
			map.setFitView();
			map.setZoom(zoom);
		}
	}
	SosMap=f.SosMap = new _SosMap();
	
	var _Timer = function(vin){
		var time = 120000,timer,vin_ = vin,ele_,t = timer,scheduler=this;
		this.setVin=function(vin){
			vin_=vin;
		}
		this.setEle=function(ele){
			ele_=ele;
		}
		function run(){
			f.currentVin = vin_;
			if(!f.currentVin){
				t.stop();
				return;
			}
			$.get(f.locationPath+"/getLocationByVin?vin="+vin_+"&t="+new Date().getTime(),function(data){
				var mes;
				if(data&&(data.code=='E0000'||data.code=='0000')){
					if(data.data&&data.data.length>0){
						if(parseInt(data.data[0].lat)!=515||parseInt(data.data[0].lon)!=515){
							SosMap.setVehicleLocation(data.data[0]);
						}else{
							SosMap.clearMap();
						}
						showVehicleDetails(data.data[0]);
						if(ele_){
							$(ele_).parent().parent().addClass('current').siblings().removeClass('current');
						}
						$('#tingzhu').show();
						return;
					}else{
						//TODO none location
					}
				}else if(data&&(data.code=='1001'||data.code=='E1001')){
					//TODO error code
					console?console.log("VIN is null or empty."):0;
				}else if(data&&(data.code=='0001'||data.code=='E0001')){
					console?console.log("Scenario interface is not in scope."):0;
				}else if(data&&(data.code=='0014'||data.code=='E0014')){
					console?console.log("Did not find item"):0;
					mes = "车辆未注册，请联系管理员。";
				}
				scheduler.stop();
				mes = mes||"很抱歉，服务异常请稍后再试，或者联系系统管理员。";
				SosMap.clearMap();
				alert(mes);
			});
		}
		this.start=function(){
			if(timer){
				this.stop();
			}
			run();
			timer = w.setInterval(run, time);
		}
		this.stop=function(){
			if(timer){
				w.clearInterval(timer);
				timer=null;
			}
		}
	} 
	var timer = f.monitorTimer = new _Timer();
	
	function _initAutoComplete(){
		var searchbox = $( "#searchbox" );
		var val = searchbox.val().trim();
		searchbox.autocomplete({
			source: function(request,response){
				if(request.term.length<1){
					return;
				}
				var reqBody = {keyword:request.term};
				reqBody[f.Csrf.parameterName] = f.Csrf.token;
				$.post(f.vehiclePath+"/autoComplete?t="+new Date().getTime(),reqBody,function(data){
					response(data);
				});
			}
		});
	}
	
	function showSignOffLocation(vin){
//		window.open(f.locationPath+"/getLocationHistoryByVin?vin="+vin);
		f.popup.open(f.locationPath+"/getLocationHistoryByVin?vin="+vin+"&t="+new Date().getTime());
	}
	
	function addVehicleDetailsEvent(){
		$("#tingzhu [vin]").each(function(){
			$(this).click(function(){
				showSignOffLocation($(this).attr("vin"));
				return !1;
			});
		});
	}
	function showVehicleDetails(location){
		f.Jqote2.get("vehicleDetails",function(tpl,tmpl,jqote){
				var rsHtml = tmpl.html(location);
				$("#tingzhu").html(rsHtml);
				addVehicleDetailsEvent();
			});
	}
	
	function getLocationByVin(vin,ele){
		timer.setVin(vin);
		timer.setEle(ele);
		timer.start();
	}
	function _initVehicleLocation(){
		var vin = f.currentVin;
		if(vin){
			getLocationByVin(vin);
		}
	}
	
	function addEnterEvent(){
		$("form").bind("keydown",function(e){
			e=e||window.event;
			var key = e.which||e.keycode;
	            if (key == 13) {
	            		$(this).submit();
	            }
		});
		
		function searchVehicleCallback(tmpl,obj,pageHtml){
			var rsHtml = tmpl.html(obj.page);
			var body = rsHtml+pageHtml;
			$(".cont").children().not(".company").remove();
			$(".cont").append(body);
			if(!$(".arrow").hasClass('current')){
				$(".arrow").click();
			}
		}
		
		function searchVehicle(data){
			var pageJson = f.Util.castPage(data);
			pageJson.type = "simple";
			if(!f.Pages.hasPage("defaultPage")){
				page = f.Pages.addPage(pageJson,"defaultPage");
				registerSetPage();
			}else{
				page = f.Pages.getPage("defaultPage");
				page.binding(pageJson);
			}
			var pageHtml = page.html();
			f.Jqote2.get("showVehicle",function(tpl,tmpl,jqote){
				searchVehicleCallback(tmpl,page,pageHtml);
				page.referesh();
				addResultEvent();
				addOrderEvent();
			});
		}
		$("form").submit(function(){
			var reqBody =$(this).serialize();
			$.post(this.action+"?t="+new Date().getTime(),reqBody,function(data){
				searchVehicle(data);
			});
			return !1;
		});
	}
	function addLoginBtnEvent(){
		$(".btn3").click(function(e){
			$("input[name=page]").val(0);
			$("form[name=fm]").submit();
			return !1;
		});
		$(document).ready(function(){
			$(".btn3").click();
		});
	}
	function resetForm(){
		if(page){
			page.condition&&page.condition.keyword?$("input[name=keyword]").val(page.condition.keyword):!1;
			if(page.orders&&page.orders.length>0){
				$("input[name=orders\\[0\\]\\.property").val(page.orders[0].property);
				$("input[name=orders\\[0\\]\\.direction").val(page.orders[0].direction);
			}
		}
	}
	function registerSetPage(){
		f.Pages.registerPage(function(pageNow,pageConfig,p){
			resetForm();
			$("input[name=page]").val(pageNow);
			$("form[name=fm]").submit();
		},"defaultPage");
	}
	
	function addResultEvent(){
		$(".vin").click(function(){
			var vin = $(this).attr("vin");
			getLocationByVin(vin,this);
			return !1;
		});
		$(".signOff").click(function(){
			var vin = $(this).attr("vin");
			showSignOffLocation(vin);
			$(this).parent().parent().addClass('current').siblings().removeClass('current');
			return !1;
		});
	}
	function addOrderEvent(){
		var co = currentOrder;
		var coe = co?$("th[order="+co.property.replace(".",'\\.')+"]"):!1;
		if(co&&coe.length>0){
			coe.find("span.ico").eq(0).append("<span class='filter "+(co.direction=='DESC'?'':"current")+"'></span>");
		}
		$("th[order]").each(function(){
			$(this).css("cursor","pointer");
			$(this).click(function(){
				var property,direction='DESC';
				if(co&&co.property==$(this).attr("order")){
					direction = co.direction=='DESC'?'ASC':'DESC';
				}
				property = $(this).attr("order");
				currentOrder={property:property,direction:direction};
				resetForm();
				$("input[name=orders\\[0\\]\\.property").val(property);
				$("input[name=orders\\[0\\]\\.direction").val(direction);
				$("form[name=fm]").submit();
			});
		});
	}
	function addArrowEvent(){
		$(".arrow").click(function(){
			$(this).prev().toggle();
			$(this).toggleClass('current');
			return !1;
		});
	}
	
	function _PopUp(){
		var pp = this;
		function createWindow(src){
			if($("#windowBackground").length>0){
				pp.close();
			}
			$(document.body).append("<div class='windowBackground' id='windowBackground'><iframe id='iframeWindow' frameborder='0' scrolling='no' marginheight='0' marginwidth='0' src='"+src+"' ></div>");
			pp.resize();
		}
		this.open=function(src){
			createWindow(src);
			/*$("#iframeWindow").load(function(){
				$("#windowBackground").show();
			});*/
		};
		this.close=function(){
			$(".windowBackground").remove();
		};
		this.resize=function(){
			$("#iframeWindow").height($(document.body).height()).width($(document.body).width());
		};
	}
	
	f.popup=new _PopUp();
	
	function _init(){
		_initMap();
		_initAutoComplete();
		_initVehicleLocation();
		addEnterEvent();
		addLoginBtnEvent();
		addArrowEvent();
	}
	_init();
})(window.Fsp,jQuery,window);