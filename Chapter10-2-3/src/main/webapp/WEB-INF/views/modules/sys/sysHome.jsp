<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>首页</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		     WinMove();
		});
	</script>
</head>
<body class="gray-bg">
   <div class="row  border-bottom white-bg dashboard-header">
        <div class="col-sm-12">
            <blockquote class="text-info" style="font-size:14px">JeePlus是一款基于代码生成器的智能快速开发平台，引领新开发模式(智能开发\在线开发\插件开发)， 可以帮助解决Java项目80%的重复工作，让开发更多关注业务逻辑。既能快速提高开发效率，帮助公司节省人力成本，同时又不失灵活性。 
    JeePlus快速开发宗旨是：简单功能由代码生成器生成使用; 复杂业务采用表单自定义，业务流程使用工作流来实现、扩展出任务接口，供开发编写业务逻辑。 实现了流程任务节点和任务接口的灵活配置，既保证了公司流程的保密性，又减少了开发人员的工作量
    			<br/>
               <br>JeePlus采用了目前极为流行的扁平化响应式的设计风格，可以完美的兼容电脑，pad，手机等多个平台。前端UI采用了<a target="_blank" href="https://wrapbootstrap.com/theme/inspinia-responsive-admin-theme-WB0R5L90S">INSPINIA</a>(点击访问）为原型开发，JeePlus v2.0发布时作者将bootstrap升级到了最新的3.3.4。
                <br>…………
            </blockquote>

            <hr>
        </div>
    </div>
      
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-4">

                <div class="ibox float-e-margins">
                     <div class="ibox-title">
                        <h5>JeePlus 技术特点</h5> 
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="index.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="index.html#">选项1</a>
                                </li>
                                <li><a href="index.html#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <p> JeePlus开发平台采用 SpringMVC + MyBatis + BootStrap + Apache Shiro + Ehcache 开发组件 的基础架构,采用面向声明的开发模式， 基于泛型编写极少代码即可实现复杂的数据展示、数据编辑、
表单处理等功能，再配合代码生成器的使用,将J2EE的开发效率提高5倍以上，可以将代码减少50%以上。

                        <ol>
						<li>代码生成器，支持多种数据模型,根据表生成对应的Entity,Service,Dao,Action,JSP等,增删改查/排序/导出导入Excel/权限控制/功能生成直接使用</li>
						<li>基础用户权限，强大数据权限，操作权限控制精密细致，对所有管理链接都进行权限验证，可控制到按钮，对指定数据集权限进行过滤</li>
						<li>简易Excel导入导出，支持单表导出和一对多表模式导出，生成的代码自带导入导出Excel功能</li>
						<li>查询过滤器，查询功能自动生成，后台动态拼SQL追加查询条件；支持多种匹配方式（全匹配/模糊查询/包含查询/不匹配查询） </li>
						<li>UI标签库，针对WEB UI进行标准封装，页面统一采用UI标签实现功能：封装了bootstrap风格的table，检索，部门选择 ，人员选择等常用控件。</li>
						<li>国际化，支持多语言，多语言系统切换（测试中，即将发布)</li>
						<li>集成百度Echarts，实现曲线图，柱状图，数据等报表</li>
						<li>系统日志监控，详细记录操作日志，可支持追查表修改日志</li>
						<li>WebSocket集成：集成在线聊天系统。</li>
						<li>提供常用工具类封装，日志、缓存、验证、字典、组织机构等，常用标签（taglib），获取当前组织机构、字典等数据。</li>
						<li>工作流组件（开发中）</li>
						<li>连接池监视：监视当期系统数据库连接池状态，可进行分析SQL找出系统性能瓶颈。</li>
						<li>提供APP接口，可以快速和移动APP整合。</li>
						
						<li>快速体验</li>
						<li>支持多种浏览器: IE, 火狐, Google 等浏览器访问速度都很快</li>
						<li>支持数据库: Mysql,Oracle数据库的支持，但不限于数据库，平台留有其它数据库支持接口等</li>
						
						<li>要求JDK1.6+</li>
                        </ol>
                    </div>
                </div>
              
            </div>
            <div class="col-sm-4">
                <div class="ibox float-e-margins">
                     <div class="ibox-title">
                        <h5>升级日志</h5> <span class="label label-primary">K+</span>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="index.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="index.html#">选项1</a>
                                </li>
                                <li><a href="index.html#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="panel-body">
                            <div class="panel-group" id="version">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h5 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#version" href="#v2.0">v2.0</a><code class="pull-right">2016.01.15更新</code>
                                            </h5>
                                    </div>
                                    <div id="v2.0" class="panel-collapse collapse in">
                                        <div class="panel-body">
                                            <ol>
                                                <li>修复IE不兼容，首页出现滚动条</li>
                                                <li>修复validate插件bug</li>
                                                <li>提供基于WebSocket的即时聊天系统</li>
                                                <li>增加注册忘记密码功能</li>
                                                <li>优化了主题的显示效果</li>
                                            </ol>
                                        </div>
                                    </div>
                                </div>
                                   <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h5 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#version" href="#v1.0">v1.9</a><code class="pull-right"></code>
                                            </h5>
                                    </div>
                                </div>
                                   <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h5 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#version" href="#v1.0">v1.8</a><code class="pull-right"></code>
                                            </h5>
                                    </div>
                                </div>
                                   <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h5 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#version" href="#v1.0">v1.7</a><code class="pull-right"></code>
                                            </h5>
                                    </div>
                                </div>
                                   <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h5 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#version" href="#v1.0">v1.6</a><code class="pull-right"></code>
                                            </h5>
                                    </div>
                                </div>
                                   <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h5 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#version" href="#v1.0">v1.5</a><code class="pull-right"></code>
                                            </h5>
                                    </div>
                                </div>
                                   <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h5 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#version" href="#v1.0">v1.4</a><code class="pull-right"></code>
                                            </h5>
                                    </div>
                                </div>
                                   <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h5 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#version" href="#v1.0">v1.3</a><code class="pull-right"></code>
                                            </h5>
                                    </div>
                                </div>
                                   <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h5 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#version" href="#v1.0">v1.2</a><code class="pull-right"></code>
                                            </h5>
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h5 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#version" href="#v1.1">v1.1</a><code class="pull-right"></code>
                                            </h5>
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h5 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#version" href="#v1.0">v1.0</a><code class="pull-right"></code>
                                            </h5>
                                    </div>
                                </div>
                               
                </div>
            </div>
            </div>
            </div>
            </div>
            <div class="col-sm-4">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>商业授权 </h5>
                    </div>
                    <div class="ibox-content">
                        <p>JeePlus是一款收费开源的快速开发平台，购买后您将获得以下内容</p>
                        <ol>
                            <li>源码(带注释)；</li>
                            <li>代码生成器；</li>
                            <li>开发者文档；</li>
                            <li>永久的免费升级服务；</li>
                            <li>授权许可；</li>
                            <li>付费二次开发服务；</li>
                            <li>……</li>
                        </ol>
                        <hr>
                    

                    </div>
                </div>
                  <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>联系信息</h5>

                    </div>
                    <div class="ibox-content">
                        <p><i class="fa fa-send-o"></i> 网址：<a href="http://www.jeeplus.org" target="_blank">http://www.jeeplus.org</a>
                        </p>
                        <p><i class="fa fa-qq"></i> QQ：<a href="http://wpa.qq.com/msgrd?v=3&uin=117575171&site=qq&menu=yes" target="_blank">117575171</a>
                        </p>
                        <p><i class="fa fa-weixin"></i> 微信：<a href="javascript:;">117575171</a>
                        </p>
                        <p><i class="fa fa-credit-card"></i> 支付宝：<a href="javascript:;" class="支付宝信息">117575171@qq.com</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>