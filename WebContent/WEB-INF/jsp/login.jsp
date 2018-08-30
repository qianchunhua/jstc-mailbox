<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
    <title>领导登录</title>
    <link href="${pageContext.request.contextPath}/js/css/layui.css" type="text/css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/layui.all.js" type="text/javascript"></script>
</head>
<body>
<div class="layui-container">
    <div>
        ${errorMsg}
    </div>
    <form class="layui-form" action="${pageContext.request.contextPath}/leadershipLogin" method="GET">
        <div class="layui-form-item">
            <label class="layui-form-label">工号：</label>

            <div class="layui-input-block">
                <input type="text" name="username" required lay-verify="required" placeholder="请输入工号" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码：</label>

            <div class="layui-input-block">
                <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="login">提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                <button type="back" class="layui-btn layui-btn-normal" onclick="javascript:history.back(-1);">返回</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
