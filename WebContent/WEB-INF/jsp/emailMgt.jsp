<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>后台转发页面</title>
    <link href="${pageContext.request.contextPath}/js/css/layui.css" type="text/css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/layui.all.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/emailMgt.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/DataTableExtend.js" type="text/javascript"></script>
    <script type="text/html" id="newestUserForwardTpl">
        {{#  if(d.forwardorder > 0){ }}
        <span>{{d.fromdisplayname}}({{d.fromusername}}) 转发到 {{d.todisplayname}}({{d.tousername}})</span>
        {{#  } else { }}
        无转发
        {{#  } }}
    </script>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row">
        <div>            
        	
        </div>
        <div class="layui-btn-group">
            <!--<button class="layui-btn layui-btn-normal layui-btn-sm" id="emailReply">回复留言</button>-->
            <button class="layui-btn layui-btn-normal layui-btn-sm" id="emailForward">转发</button>
            <button class="layui-btn layui-btn-normal layui-btn-sm" id="userBack" onclick="window.history.back(-1);">返回</button>
        </div>
        <table class="layui-hide" id="emailList" lay-filter="emailList"></table>
    </div>
</div>
</body>
</html>
