<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户列表</title>
    <link href="${pageContext.request.contextPath}/js/css/layui.css" type="text/css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/email.css" type="text/css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/layui.all.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/user.js" type="text/javascript"></script>
</head>
<body>
<div class="layui-container">
    <div class="layui-row">
        <div class="layui-btn-group">
            <button class="layui-btn layui-btn-normal layui-btn-sm" id="userForwardSubmit">提交给转发人</button>
        </div>
        <div class="layui-btn-group">
			<a class="layui-btn layui-btn-normal layui-btn-sm" href="${pageContext.request.contextPath}/index" target="_blank">注销</a>
        </div>
		<div class="layui-btn-group">
            <button class="layui-btn layui-btn-normal layui-btn-sm" id="userBack" onclick="javascript:history.back(-1);">返回</button>
        </div>
        <table class="layui-hide" id="userList" lay-filter="userList"></table>
    </div>
</div>
</body>
</html>
