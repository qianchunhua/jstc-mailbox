<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>回复留言页面</title>
    <link href="${pageContext.request.contextPath}/js/css/layui.css" type="text/css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/email.css" type="text/css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/layui.all.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/reply.js" type="text/javascript"></script>
    
    <style type="text/css">
    	.textareaW100 {
    		margin-top: 0px;
    		margin-bottom: 0px;
    		height:130px;
    		width:540px;
    	}
    </style>
</head>
<body>
<div class="layui-container">
    <div class="layui-row">
        <form class="layui-form" action="${pageContext.request.contextPath}/replyEdit" method="post">
            <fieldset class="layui-elem-field layui-field-title layui_fieldset_padding">
                <legend>留言信息</legend>

                <div class="layui-form-item">
                    <label class="layui-form-label">标题：</label>

                    <div class="layui-input-block">
                        <input type="text" name="title" autocomplete="off" class="layui-input layui-disabled" value="${title}" readonly>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">留言类别：</label>

                    <div class="layui-input-inline">
                        <input type="text" name="type" autocomplete="off" class="layui-input layui-disabled" value="${type}" readonly>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">留言内容：</label>
                    <div class="layui-input-block">
                        <input type="text" name="content" autocomplete="off" class="layui-input layui-disabled" value="${content}" readonly>
                    </div>
                </div>               
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">发言内容：</label>
					
                    <div class="layui-input-block">
                        <textarea name="reply" placeholder="发言字数请不要超过300个字 " cols="45" rows="5" class="textareaW100" required lay-verify="required">${reply}</textarea>(必填)
                    </div>
                </div>
            </fieldset>

            <fieldset class="layui-elem-field layui-field-title layui_fieldset_padding">
                <legend>个人信息</legend>
                <div class="layui-form-item">
                    <label class="layui-form-label">网名：</label>

                    <div class="layui-input-inline">
                        <input type="text" name="nickname" autocomplete="off" class="layui-input layui-disabled" value="${nickname}" readonly>
                    </div>
                    <label class="layui-form-label">手机：</label>

                    <div class="layui-input-inline">
                        <input type="text" name="mobilephone" autocomplete="off" class="layui-input layui-disabled" value="${mobilephone}" readonly>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">邮箱：</label>

                    <div class="layui-input-inline">
                        <input type="text" name="email" autocomplete="off" class="layui-input layui-disabled" value="${email}" readonly>
                    </div>
                </div>
                <input type="hidden" name="querykey" value="${querykey}">
            </fieldset>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="replyEdit">提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    <button type="back" class="layui-btn layui-btn-normal" onclick="javascript:history.back(-1);">返回</button>
                </div>
            </div>
        </form>

    </div>
</div>
</body>
</html>
