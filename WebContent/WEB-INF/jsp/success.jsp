<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>留言成功</title>
    <link href="${pageContext.request.contextPath}/js/css/layui.css" type="text/css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/layui.all.js" type="text/javascript"></script>
</head>
<body>
<div class="layui-container">
    <div class="layui-row">
        <div class="layui-col-md4 layui-col-md-offset4">
            <p> 提交成功！</p>

            <p>
                请记下下面的密钥，用于查询本次留言回复，需要妥善保存丢失无法找回：
            </p>

            <p>
                ${querykey}
            </p>
        </div>
    </div>
</div>
</body>
</html>
