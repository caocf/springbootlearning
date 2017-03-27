<%@page import="com.sos.fleet.common.util.UrlPathUtil"%>
<%@page import="com.sos.fleet.common.settings.CommonJsSettings"%>
<%@page import="com.sos.fleet.common.settings.EnvironmentSettings"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/javascript; charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ include file="../common/init.jsp"%>
(function(w,$){
	function _compat(){
		var c = this;
		this.trimOfString = function(){
			String.prototype.trim=function(){
		　		return this.replace(/(^\s*)|(\s*$)/g, "");
			}
		}
		function _init(){
			c.trimOfString();
		}
		_init();
	}
	new _compat();
	
	var Fsp_ =function(){
		var f = this;
		var IS_READ_ONLY_ENV = <%=EnvironmentSettings.ENABLE_DREAD_ONLY %>;
		this.contextPath = "${contextPath}";
		this.jsPath="${jsPath}";
		this.cssPath="${cssPath}";
		this.imgPath="${imgPath}";
		this.fuPath="${fuPath}";
		this.vehiclePath="${vehiclePath}";
		var Env_  =  function(name){
			var modifierCss = ".env_read_only";
			var containerCss = ".env_read_only_mes";
			var containerText = "<spring:message code="env.read.only.message"></spring:message>";
			var e_ = this;
			this.name = name;
			this.isReadOnlyEnv = function(){
				return IS_READ_ONLY_ENV;
			}
			this.activeReadOnly = function(){
				if(IS_READ_ONLY_ENV){
					$(modifierCss).css({"visibility":"hidden"});
					if($(containerCss).length<=0){
						_createContainer();
					}
					$(containerCss).text(containerText).show();
				}
			}
			var _createContainer = function(){
				$(".main").append("<div class='"+ containerCss.substring(1)+"'></div>");
			}
			var _init = function(){
				e_.activeReadOnly();
			}
			
			$(document).ready(function(){
				_init();
			});
		}
		this.Env = new Env_("<%=EnvironmentSettings.instance().getName()%>");
		
		var Pages_ = function(){
			var defaultPageId;
			var pages = this;
			var pageCount = 0;
			function _init(){
				var pagesEle = $(".pages");
				if(pagesEle.length==1){
					defaultPageId = pagesEle.attr("id");
				}
				pagesEle.each(function(){
						if(pages[$(this).attr("id")]){
							alert($(this).attr("id")+" exists in Pages");
							return;
						}
						pageCount++;
						pages[$(this).attr("id")] = new Page_($(this).find(".scriptJson").html().trim(),$(this).attr("id"));
					});
			}
			this.registerDefaultPage = function(fn){
				this.registerPage(fn);
			}
			this.registerPage = function(fn,pageId){
				$(document).ready(function(){
					pageId = pageId||defaultPageId;
					if(pageCount==1&&pages[pageId]){
						pages[pageId].setPageCallback(fn);
					}
				});
			}
			$(document).ready(function(){
				_init();
			});
		}
		this.Pages = new Pages_();
		
		
		var Page_ = function(scriptJson,pageId){
			this.page = eval("("+scriptJson+")");
			this.pageId = pageId;
			var fnPageCallback;
			var p = this;
			var _init = function(){
				$("#"+pageId+" a.first").click(function(){
					p.setPage(0);
				});
				$("#"+pageId+" a.previous").click(function(){
					p.setPage(p.page.number-1);
				});
				$("#"+pageId+" a.nums").not(".current").each(function(){
					$(this).click(function(){
						p.setPage($(this).attr("pageNum"));
					});
				});
				$("#"+pageId+" a.more").click(function(){
					p.setPage(p.page.more);
				});
				$("#"+pageId+" a.next").click(function(){
					p.setPage(p.page.number+1);
				});
				$("#"+pageId+" a.endNum").click(function(){
					p.setPage(p.page.totalPages-1);
				});
				$("#"+pageId+" a.end").click(function(){
					p.setPage(p.page.totalPages-1);
				});
				
				$("#"+pageId+" input.input").bind("keydown",function(e){
					e=e||window.event;
					var key = e.which||e.keycode;
		            if (key != 13) {
		            	return;
		            }
					var val = $(this).val().trim();
					if(!/^\d+$/.test(val)){
						$(this).val(p.page.number+1);
						return;
					}
					var pageNow = parseInt(val);
					if(pageNow>p.page.totalPages){
						pageNow = p.page.totalPages;
					}
					if(pageNow<1){
						pageNow = 1;
					}
					$(this).val(pageNow);
					p.setPage(pageNow-1);
				});
			}
			
			this.setPageCallback=function(callback){
				fnPageCallback = callback;
			}
			this.setPage=function(pageNow){
				fnPageCallback(pageNow,this.page,this);
			}
			_init();
		}
	}
	w.Fsp = $.Fsp = new Fsp_();
})(window,jQuery);
