<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
    <title>领导回复</title>
    <link href="${pageContext.request.contextPath}/js/css/layui.css" type="text/css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/email.css" type="text/css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/layui.all.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/email.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/emailQuery.js" type="text/javascript"></script>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row">       
            <table class="layui-hide" id="emailQuery" lay-filter="emailQuery"></table>
    </div>
</div>
</body>
</html>
