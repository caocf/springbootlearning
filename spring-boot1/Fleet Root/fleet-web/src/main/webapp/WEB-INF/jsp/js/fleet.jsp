<%@page import="com.sos.fleet.common.util.UrlPathUtil"%>
<%@page import="com.cus.common.settings.CommonJsSettings"%>
<%@page import="com.cus.common.settings.EnvironmentSettings"%>
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
		this.formatOfDate = function(){
			Date.prototype.format = function(fmt){
			  var o = {   
			    "M+" : this.getMonth()+1,                 //月份   
			    "d+" : this.getDate(),                    //日   
			    "H+" : this.getHours(),                   //小时   
			    "m+" : this.getMinutes(),                 //分   
			    "s+" : this.getSeconds(),                 //秒   
			    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
			    "S"  : this.getMilliseconds()             //毫秒   
			  };   
			  if(/(y+)/.test(fmt))   
			    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
			  for(var k in o)   
			    if(new RegExp("("+ k +")").test(fmt))   
			  		fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
			  return fmt;   
			}  
		}
		this.keysOfObject=function(){
			var DONT_ENUM =  "propertyIsEnumerable,isPrototypeOf,hasOwnProperty,toLocaleString,toString,valueOf,constructor".split(","),
			    hasOwn = ({}).hasOwnProperty;
			    for (var i in {
			        toString: 1
			    }){
			        DONT_ENUM = false;
			    }
			
			Object.keys = Object.keys || function(obj){
	            var result = [];
	            for(var key in obj ) 
		            if(hasOwn.call(obj,key)){
		                result.push(key) ;
		            }
	            if(DONT_ENUM && obj){
	                for(var i = 0 ;key = DONT_ENUM[i++]; ){
	                    if(hasOwn.call(obj,key)){
	                        result.push(key);
	                    }
	                }
	            }
	            return result;
	        };
		}
		function _init(){
			c.trimOfString();
			c.formatOfDate();
			c.keysOfObject();
		}
		_init();
	}
	new _compat();
	
	var Fsp_ =function(){
		var f = this;
		var IS_READ_ONLY_ENV = <%=EnvironmentSettings.ENABLE_DREAD_ONLY %>;
		this.contextPath = "${contextPath}";
		this.commonPath = "${commonPath}";
		this.jsPath="${jsPath}";
		this.cssPath="${cssPath}";
		this.jqote2Path = "${jqote2Path}";
		this.vehiclePath="${vehiclePath}";
		this.locationPath="${locationPath}";
		this.userPath="${userPath}";
		this.imgPath="${imgPath}";
		
		var Csrf_ = function(){
			var csrf = this;
			function _init(){
				var csrfEle = $("input[name=_csrf],input[csrf]");
				csrf.parameterName = csrfEle.eq(0).attr("name");
				csrf.token = csrfEle.eq(0).val();
			}
			
			$(document).ready(function(){
				_init();
			});
		}
		
		this.Csrf = new Csrf_();
				
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
		
		var _Jqote2 = function(){
			var jqote =  this;
			var jqotes = [];
			function _init(){
				jqote.supportJqote2  = $.jqote?!0:!1;
			}
			
			$(document).ready(function(){
				_init();
			});
			
			this.get=function(pagePath,callback){
				var rs = null;
				if(this[pagePath]){
					rs = this[pagePath];
					if(callback){
						callback(this[pagePath].tmpl,this[pagePath],jqote);
					}
					return rs;
				}
				var settings = {
					url:f.jqote2Path+"/"+pagePath+".tpl?t="+new Date().getTime(),
					async:callback?!0:!1,
					success:function(data){
						var tmpl = jqote.create(pagePath,data);
						rs = tmpl;
						jqote[pagePath] = rs;
						if(callback){
							callback(data,tmpl,jqote);
						}
					}
				}
				$.ajax(settings);
				return rs;
			}
			this.create = function(pagePath,tmpl){
				var jqote = new _JqoteTmpl(tmpl)
				jqotes.push(jqote);
				jqote[pagePath] = jqote;
				return jqote;
			}
		}
		
		var _JqoteTmpl = function(tmpl){
			this.tmpl = tmpl;
			this.html = function(obj){
				return $.jqote(this.tmpl,obj);
			}
		}
		
		f.Jqote2=new _Jqote2();
		
		var Pages_ = function(){
			var defaultPageId;
			var p = this;
			var pages = [];
			var defaultTmpl;
			function resoveTmpl(data,tmpl,jqote){
				defaultTmpl = tmpl;
			}
			
			function _init(){
			
				f.Jqote2.supportJqote2?f.Jqote2.get("page",resoveTmpl):!1;
				
				var pagesEle = $(".pages");
				if(pagesEle.length==1){
					defaultPageId = pagesEle.attr("id");
				}
				pagesEle.each(function(){
						if(p[$(this).attr("id")]){
							alert($(this).attr("id")+" exists in Pages");
							return;
						}
						p.addPage($(this).find(".scriptJson").html().trim(),$(this).attr("id"));
					});
			}
			this.getDefaultTmpl=function(){
				return defaultTmpl;
			}
			this.registerDefaultPage = function(fn){
				$(document).ready(function(){
					p.registerPage(fn);
				});
			}
			this.registerPage = function(fn,pageId){
				pageId = pageId||defaultPageId;
				if(p[pageId]&&!p[pageId].getPageCallback()){
					p[pageId].setPageCallback(fn);
				}
			}
			$(document).ready(function(){
				_init();
			});
			this.addPage = function(scriptJson,pageId){
				if(p[pageId]){
					alert(pageId+"is exist!");
					retrun;
				}
				p[pageId] = new Page_(scriptJson,pageId);
				p[pageId].index=pages.length;
				pages.push(p[pageId]);
				return p[pageId];
			}
			this.getPage=function(pageId){
				if(typeof("pageId")=="string"){
					return p[pageId];
				}else{
					return pages[pageId];
				}
			}
			this.getOrCreate=function(scriptJson,pageId){
				if(this.hasPage(pageId)){
					return this.getPage(pageId);
				}else{
					return this.addPage(scriptJson,pageId);
				}
			}
			this.hasPage=function(pageId){
				if(typeof("pageId")=="string"){
					return p[pageId]?!0:!1;
				}else{
					return pageId >= 0&&pageId < pages.length;
				}
			}
			this.getSize=function(){
				return pages.length;
			}
			this.removePage=function(pageId){
				var page = this.getPage(pageId);
				var index = page.index;
				pages.splice(index,1);
				delete p[page.pageId];
			}
		}
		this.Pages = new Pages_();
		
		
		var Page_ = function(scriptJson,pageId){
			this.binding=function(pageParam){
				this.page = typeof(pageParam)=='string'?eval("("+pageParam+")"):pageParam;
				this.page.pageId = this.pageId =pageId;
			}
			this.binding(scriptJson);
			var fnPageCallback;
			var p = this;
			var _init = function(){
				if($("#"+pageId).length<=0){
					return;
				}
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
			this.getPageCallback=function(){
				return fnPageCallback ;
			}
			this.setPage=function(pageNow){
				fnPageCallback(pageNow,this.page,this);
			}
			this.html=function(tmpl){
				tmpl = tmpl||f.Pages.getDefaultTmpl();
				return tmpl.html(this.page);
			}
			this.referesh=function(){
				_init();
			}
			_init();
		}
		
		var _Util = function(){
			this.castPage = function(searchPageImpl){
				return {
					visible:searchPageImpl.visiblePages,
					number:searchPageImpl.pageNumber,
					currentPage:searchPageImpl.pageNumber+1,
					totalPages:searchPageImpl.pageResult.totalPages,
					more:searchPageImpl.pageNumber + searchPageImpl.more < searchPageImpl.pageResult.totalPages-1
						? searchPageImpl.pageNumber + searchPageImpl.more:(searchPageImpl.pageResult.totalPages-1),
					totalElements:searchPageImpl.pageResult.totalElements,
					beginPage:searchPageImpl.pageResult.totalPages > searchPageImpl.visiblePages
						?searchPageImpl.pageNumber+1-searchPageImpl.visiblePages <1
							?1:searchPageImpl.pageNumber+1-searchPageImpl.visiblePages
						:1,
					endPage:searchPageImpl.pageResult.totalPages > searchPageImpl.visiblePages
						?searchPageImpl.pageNumber+1+searchPageImpl.visiblePages>searchPageImpl.pageResult.totalPages
							?searchPageImpl.pageResult.totalPages:searchPageImpl.pageNumber+1+searchPageImpl.visiblePages
						:searchPageImpl.pageResult.totalPages,
					pageResult:searchPageImpl.pageResult,
					offset:searchPageImpl.offset
				};
			}
		}
		this.Util = new _Util();
	}
	w.Fsp = $.Fsp = new Fsp_();
})(window,jQuery);
