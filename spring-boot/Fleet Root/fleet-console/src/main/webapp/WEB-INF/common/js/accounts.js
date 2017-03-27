
var properties = ["fleetDomain.name","orgId","userName","email","mobile","address"];

$(function () {
	
	$(".searchbtn").click(function() {
		$("input[name='page']").val(0);
		$("form[name='fm']").attr("action", Fsp.fuPath+"/showUsers").submit();
	});
	
	$(".bangding").click(function() {
		$("form[name='fm']").attr("action", Fsp.fuPath+"/toAddUser").submit();
	});
	
	$(".tableform table th[sort!='false']").click(function() {
		sort(this);
	});
	
	registerSetPage();
	initSort();
});

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
	$("form[name='fm']").attr("action", Fsp.fuPath+"/showUsers").submit();
}

function toUpdateUser(userId) {
	$("input[name='userId']").val(userId);
	$("form[name='fm']").attr("action", Fsp.fuPath+"/toUpdateUser").submit();
}

function toUserVehicles(userId, companyName) {
	$("input[name='userId']").val(userId);
	$("input[name='companyName']").val(companyName);
	$("input[name='page']").val(0);
	$("input[name='orders[0].property']").remove();
	$("input[name='orders[0].direction']").remove();
	$("form input[name='key']").val("");
	$("form[name='fm']").attr("action", Fsp.vehiclePath+"/showBindVehicles").submit();
}
