<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="./common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>用户登录</title>
    <%@ include file="./common/meta.jsp" %> 
    <style type="text/css">
        .login_label {display: inline-block;text-align: right;width: 76px;font-size: 14px;}
        body{width:100%; height:100%; margin:0; padding:0;}
    </style>
     
</head>
<body>
    <div id="login_div" align="center" style="margin-top: 100px;">
        <div class="easyui-panel" title="用户登录" align="center" style="width:400px;padding:20px 30px 20px 30px;)">
            <form id="loginForm" method="post" novalidate>
                <div style="padding: 5px;">
                    <label class="login_label">用户名：</label>
                    <input id="loginName" name="loginName" class="easyui-textbox" 
                    		style="width: 210px;height:40px;padding:12px"
                           value=""
                           data-options="prompt:'请输入登录名...',iconCls:'easyui-icon-man',iconWidth:38,required:true,validType:'minLength[1]',missingMessage:'请输入用户名!'"/>
                </div>
                <div style="padding: 5px;">
                    <label class="login_label">密&nbsp;&nbsp;码 ：</label>
                    <input id="password" name="password" class="easyui-textbox" type="password"
                           value=""
                           onkeydown="javascript:if(event.keyCode==13){login();}" style="width:210px;height:40px;padding:12px"
                           data-options="prompt:'请输入密码...',iconCls:'easyui-icon-lock',iconWidth:38,required:true,validType:'minLength[1]',missingMessage:'请输入密码!'"/>

                </div>
                <%--<div style="padding: 5px;">--%>
                    <%--<label class="login_label"> 验证码：</label>--%>
                    <%--<input id="validateCode" name="validateCode" type="text" onkeydown="if(event.keyCode==13)login()"--%>
                           <%--class="easyui-validatebox textbox eu-input" style="width: 100px" required="true"--%>
                           <%--data-options="tipPosition:'left',validType:'alphanum',missingMessage:'请输入验证码!'"/>--%>
                    <%--<img id="validateCode_img" align="middle" onclick="refreshCheckCode();"/>--%>
                    <%--<a href="javascript:void(0)" onclick="refreshCheckCode();"> 看不清,换一个</a>--%>

                <%--</div>--%>
                <div style="padding: 5px;">
                    <label for="rememberPassword" style="margin-left: 76px;"><input id="rememberPassword" type="checkbox"/>记住密码</label>
                    <label for="autoLogin"><input id="autoLogin" type="checkbox"/>自动登录</label>
                </div>
                <div>
                    <a id="login_linkbutton" href="#" class="easyui-linkbutton" onclick="login()"
                       style="margin-left: 76px;width: 210px;height: 42px;">登 录</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
