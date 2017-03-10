(function(f,$,w){
	var map,SosMap,currentOrder,zoom=18,rangeEle;
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
		var markers=[],windowsArr=[];
		this.clearMap=function(){
			map.clearMap();
			map.clearInfoWindow();
			windowsArr.length=markers.length=0;
		}
		this.addVehicleLocation=function(location){
			var marker = new AMap.Marker({
				map:map,
				position: [location.lon, location.lat],
				icon: "https://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
				topWhenMouseOver:!0
			});
			marker.vehicleLoaction = location;
			location.marker = marker;
			markers.push(marker);
			this.addInfoWindow(location);
		}
		this.addInfoWindow=function(location){
			var infoWindow = new AMap.InfoWindow({
				content: "<p>"+new Date(location.locatedTime).format("yyyy-MM-dd HH:mm")+"</p>",
				size:new AMap.Size(120, 0),
				autoMove:!0, 
				offset:new AMap.Pixel(0,-20)
			});
			location.infoWindow=infoWindow;
			windowsArr.push(infoWindow);
			var aa = function (e) {infoWindow.open(map, location.marker.getPosition());};
			AMap.event.addListener(location.marker, "mouseover", aa);
		}
	}
	SosMap=f.SosMap = new _SosMap();
	function changeMap(pageRequest){
		var content = pageRequest.pageResult.content;
		SosMap.clearMap();
	
		$.each(content,function(i,location){
			var metaData={index:i,lat:location.latitude,lon:location.longitude,locatedTime:location.locatedTime};
			SosMap.addVehicleLocation(metaData);
			if(i==content.length-1){
				map.setFitView();
			}
			$(".table2 tbody tr").eq(i).click(function(){
				metaData.infoWindow.open(map, metaData.marker.getPosition());
			});
		});
		
	}
	function changeList(pageRequest){
		var pageJson = f.Util.castPage(pageRequest);
		pageJson.type = "simple";
		var page;
		if(!f.Pages.hasPage("pages")){
			page = f.Pages.addPage(pageJson,"pages");
			registerSetPage("pages");
		}else{
			page = f.Pages.getPage("pages");
			page.binding(pageJson);
		}
		/*var page = f.Pages.getOrCreate(pageJson,"pages");
		page.binding(pageJson);*/
		var pageHtml = page.html();
		f.Jqote2.get("locationHistory",function(tpl,tmpl,jqote){
			var rsHtml = tmpl.html(pageRequest);
			$(".cont tbody").html(rsHtml);
			$("#pageDiv").html(pageHtml);
			if(!$(".arrow").hasClass('current')){
				$(".arrow").click();
			}
			page.referesh();
			changeMap(pageRequest);
		});
	}
	
	function getLocationHistory(range,page){
		var data = {
				vin:f.currentVin,
				recent:parseInt(range[0]),
				far:parseInt(range[1])
		};
		data[f.Csrf.parameterName]=f.Csrf.token;
		if(page){
			data.page=page;
		}
		$.post(f.locationPath+"/getRestLocationHistoryByVin?t="+new Date().getTime(),data,function(page){
			changeList(page);
		});
	}
	
	function _initSlider(){
		/*rangeEle = document.getElementById("range");
		noUiSlider.create(rangeEle,{
			range: {min:0, max:7},
			start: [0, 1],
			step: 1,
			margin:1,
			connect: !0,
			behaviour: 'tap-drag',
			exchange:1
		});
		rangeEle.noUiSlider.on("slide",function(){
			var val = rangeEle.noUiSlider.get();
			getLocationHistory(val);
		});*/
		var opt  = {
				range: {min:0, max:7},
				start: [0, 1],
				step: 1,
				margin:1,
				connect: !0/*,
				slide: function() {
					var val =$(this).val()
					getLocationHistory(val);
				}*/
			};
			rangeEle = $('#range').noUiSlider(opt);
		rangeEle.on("slide",function(){
			var val = $(this).val();
			getLocationHistory(val);
			showTimeTip(val);
		});
		showTimeTip(opt.start);
	}
	function showTimeTip(val){
		var recent = parseInt(val[0]),far=parseInt(val[1]);
		var recentDate,farDate;
		if(recent==0){
			recentDate = new Date(f.nowTime);
		}else{
			recentDate = new Date(f.truncateTime-(recent-1)*86400000);
		}
		farDate = new Date(f.truncateTime-(far-1)*86400000);
		$(".timeTip").css("text-align","center").html(farDate.format("yyyy/MM/dd HH:mm:ss")+" - "+recentDate.format("yyyy/MM/dd HH:mm:ss"));
	}
	function addArrowEvent(){
		$(".arrow").click(function(){
			$(this).prev().toggle();
			$(this).toggleClass('current');
			return !1;
		});
	}
	function registerSetPage(pageId){
		var callback =function(pageNow,pageConfig,p){
			//setpage
			//			getLocationHistory(rangeEle.noUiSlider.get(),pageNow);
						getLocationHistory(rangeEle.val(),pageNow);
						console?console.log(pageNow):!0;
		};
		if(!pageId){
			f.Pages.registerDefaultPage(callback);
		}else{
			f.Pages.registerPage(callback,pageId);
		}
	}
	function _initLocations(){
		var locations = [];
		$("[locationMetaData]").each(function(){
			var location = eval("("+$(this).attr("locationMetaData")+")");
			locations.push(location);
			location.element=this;
			$(this).parent().click(function(){
				location.infoWindow.open(map, location.marker.getPosition());
			});
		});
		$.each(locations,function(i,obj){
			SosMap.addVehicleLocation(obj);
			if(i==locations.length-1){
				map.setFitView();
			}
		});
	}
	function _initMonitorLocation(){
		$(".btn2[vin]").click(function(){
//			location=f.locationPath+"/monitorLocation?vin="+$(this).attr("vin");
			window.parent.Fsp.popup.close();
		});
	}
	function _init(){
		_initSlider();
		 addArrowEvent();
		 registerSetPage();
		 _initMap();
		 _initLocations();
		 _initMonitorLocation();
	}
	_init();
})(window.Fsp,jQuery,window);