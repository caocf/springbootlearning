
$(function() {
	$(".cancel").click(function() {
		$("form[name='fm']").attr("action", Fsp.vehiclePath+"/showBindVehicles").submit();
	});
	
	$(".submitbtn").click(function() {
		$(this).attr("disabled", !0);
		$.post(Fsp.vehiclePath+"/editVehicle", $("form[name=fm]").serialize(), function(data) {
			console.log(data);
			$(this).attr("disabled", !1);
			$(".submit_info p").removeClass("err").addClass("success").text("信息更新成功");
		});
	});
	
});