var $menu_tree;
var $menu_tree_data;
$(function() {
	//初始化导航菜单数据
//	bindMenuJson();
    //初始化导航菜单
//		initMenu();
    initMenuTree();
});

/*  初始化导航菜单 */
function initMenu(){
//    $.post(ctxAdmin+"/index/navTree", function(data) {
        $.each($menu_tree_data, function(i, n) {
            var menulist = "<div class='easyui-panel' data-options='fit:true,border:false' style='overflow-y:auto;overflow-X: hidden;'><ul>";
            $.each(n.children, function(j, o) {//依赖于center界面选项卡$layout_center_tabs对象
                menulist += "<li><div><strong><a onClick='javascript:eu.addTab($layout_center_tabs,\""
                    + o.text+"\",\"" + ctxAdmin + o.attributes.url+ "\",true,\""+o.iconCls+"\")' style='font-size:14px;' > <span class='tree-icon tree-file "+o.iconCls+"'></span>" + o.text + "</a></strong></div></li> ";
            });
            menulist += '</ul></div>';

            $(".easyui-accordion").accordion('add', {
                title : n.text,
                content : menulist,
                iconCls : n.iconCls
            });

        });
        $('.easyui-accordion div li div strong a').click(function(){
            $('.easyui-accordion li div').removeClass("selected");
            $(this).parent().parent().addClass("selected");
        }).hover(function(){
            $(this).parent().parent().addClass("hover");
        },function(){
            $(this).parent().parent().removeClass("hover");
        });

//    },"json");
}

function initMenuTree(){
    //组织机构树
    $menu_tree = $("#menu_tree").tree({
        url : ctxAdmin+"/index/navTree",
        method:'get',
        animate:true,
        lines:true,
        onClick:function(node){
            var url = node.attributes.url;
            if(url){

                if(url.substring(0,4) == "http"){

                }else if(url.substring(0,1) == "/" ){
                    url = ctx + url;
                }else{
                    url = ctxAdmin+'/' + url;
                }
                eu.addTab($layout_center_tabs,node.text,url,true,node.iconCls);
            }
        }
    });
}

function bindMenuJson(){
	$menu_tree_data =[ {
		  "id" : "1",
		  "pId" : null,
		  "text" : "系统管理",
		  "iconCls" : "eu-icon-application",
		  "checked" : false,
		  "attributes" : {
		    "markUrl" : "",
		    "code" : "",
		    "type" : 0,
		    "url" : ""
		  },
		  "children" : [ {
		    "id" : "2",
		    "pId" : null,
		    "text" : "资源管理",
		    "iconCls" : "eu-icon-folder",
		    "checked" : false,
		    "attributes" : {
		      "markUrl" : "",
		      "code" : "",
		      "type" : 0,
		      "url" : "sys/resource"
		    },
		    "children" : [ ],
		    "state" : "open"
		  }, {
		    "id" : "3",
		    "pId" : null,
		    "text" : "角色管理",
		    "iconCls" : "eu-icon-group",
		    "checked" : false,
		    "attributes" : {
		      "markUrl" : "",
		      "code" : "",
		      "type" : 0,
		      "url" : "sys/role"
		    },
		    "children" : [ ],
		    "state" : "open"
		  }, {
		    "id" : "4",
		    "pId" : null,
		    "text" : "机构管理",
		    "iconCls" : "eu-icon-group",
		    "checked" : false,
		    "attributes" : {
		      "markUrl" : "",
		      "code" : "",
		      "type" : 0,
		      "url" : "sys/organ"
		    },
		    "children" : [ ],
		    "state" : "open"
		  }, {
		    "id" : "26",
		    "pId" : null,
		    "text" : "岗位管理",
		    "iconCls" : "eu-icon-vcard",
		    "checked" : false,
		    "attributes" : {
		      "markUrl" : "",
		      "code" : "",
		      "type" : 0,
		      "url" : "sys/post"
		    },
		    "children" : [ ],
		    "state" : "open"
		  }, {
		    "id" : "5",
		    "pId" : null,
		    "text" : "用户管理",
		    "iconCls" : "eu-icon-user",
		    "checked" : false,
		    "attributes" : {
		      "markUrl" : "",
		      "code" : "",
		      "type" : 0,
		      "url" : "sys/user"
		    },
		    "children" : [ ],
		    "state" : "open"
		  }, {
		    "id" : "7",
		    "pId" : null,
		    "text" : "字典类型",
		    "iconCls" : "eu-icon-book",
		    "checked" : false,
		    "attributes" : {
		      "markUrl" : "",
		      "code" : "",
		      "type" : 0,
		      "url" : "sys/dictionary-type"
		    },
		    "children" : [ ],
		    "state" : "open"
		  }, {
		    "id" : "8",
		    "pId" : null,
		    "text" : "数据字典",
		    "iconCls" : "eu-icon-email",
		    "checked" : false,
		    "attributes" : {
		      "markUrl" : "",
		      "code" : "",
		      "type" : 0,
		      "url" : "sys/dictionary"
		    },
		    "children" : [ ],
		    "state" : "open"
		  }, {
		    "id" : "9",
		    "pId" : null,
		    "text" : "内容管理",
		    "iconCls" : "eu-icon-bug",
		    "checked" : false,
		    "attributes" : {
		      "markUrl" : "",
		      "code" : "",
		      "type" : 0,
		      "url" : "sys/bug"
		    },
		    "children" : [ ],
		    "state" : "open"
		  }, {
		    "id" : "21",
		    "pId" : null,
		    "text" : "日志管理",
		    "iconCls" : "eu-icon-monitor",
		    "checked" : false,
		    "attributes" : {
		      "markUrl" : "",
		      "code" : "",
		      "type" : 0,
		      "url" : "sys/log"
		    },
		    "children" : [ ],
		    "state" : "open"
		  }, {
		    "id" : "29",
		    "pId" : null,
		    "text" : "配置管理",
		    "iconCls" : "",
		    "checked" : false,
		    "attributes" : {
		      "markUrl" : "",
		      "code" : "",
		      "type" : 0,
		      "url" : "sys/config"
		    },
		    "children" : [ ],
		    "state" : "open"
		  }, {
		    "id" : "30",
		    "pId" : null,
		    "text" : "云盘管理",
		    "iconCls" : "",
		    "checked" : false,
		    "attributes" : {
		      "markUrl" : "",
		      "code" : "",
		      "type" : 0,
		      "url" : "disk"
		    },
		    "children" : [ ],
		    "state" : "open"
		  }, {
		    "id" : "33",
		    "pId" : null,
		    "text" : "流程设置",
		    "iconCls" : "eu-icon-application",
		    "checked" : false,
		    "attributes" : {
		      "markUrl" : "",
		      "code" : "",
		      "type" : 0,
		      "url" : ""
		    },
		    "children" : [ {
		      "id" : "34",
		      "pId" : null,
		      "text" : "流程分类",
		      "iconCls" : "",
		      "checked" : false,
		      "attributes" : {
		        "markUrl" : "",
		        "code" : "",
		        "type" : 0,
		        "url" : "act/actprocesstype"
		      },
		      "children" : [ ],
		      "state" : "open"
		    }, {
		      "id" : "35",
		      "pId" : null,
		      "text" : "流程部署",
		      "iconCls" : "",
		      "checked" : false,
		      "attributes" : {
		        "markUrl" : "",
		        "code" : "",
		        "type" : 0,
		        "url" : "act/actprocessdeploy"
		      },
		      "children" : [ ],
		      "state" : "open"
		    }, {
		      "id" : "36",
		      "pId" : null,
		      "text" : "流程定义",
		      "iconCls" : "",
		      "checked" : false,
		      "attributes" : {
		        "markUrl" : "",
		        "code" : "",
		        "type" : 0,
		        "url" : "act/actprocessdef"
		      },
		      "children" : [ ],
		      "state" : "open"
		    }, {
		      "id" : "37",
		      "pId" : null,
		      "text" : "流程表单",
		      "iconCls" : "",
		      "checked" : false,
		      "attributes" : {
		        "markUrl" : "",
		        "code" : "",
		        "type" : 0,
		        "url" : "act/actprocessform"
		      },
		      "children" : [ ],
		      "state" : "open"
		    } ],
		    "state" : "open"
		  } ],
		  "state" : "open"
		}, {
		  "id" : "31",
		  "pId" : null,
		  "text" : "我的工作",
		  "iconCls" : "eu-icon-application",
		  "checked" : false,
		  "attributes" : {
		    "markUrl" : "",
		    "code" : "",
		    "type" : 0,
		    "url" : ""
		  },
		  "children" : [ {
		    "id" : "32",
		    "pId" : null,
		    "text" : "我的通知",
		    "iconCls" : "",
		    "checked" : false,
		    "attributes" : {
		      "markUrl" : "",
		      "code" : "",
		      "type" : 0,
		      "url" : "notice/notice"
		    },
		    "children" : [ ],
		    "state" : "open"
		  } ],
		  "state" : "open"
		} ];	
}