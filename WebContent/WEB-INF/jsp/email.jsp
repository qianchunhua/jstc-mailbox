<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
    <title>留言和查询回复</title>
    <link href="${pageContext.request.contextPath}/js/css/layui.css" type="text/css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/email.css" type="text/css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/layui.all.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/email.js" type="text/javascript"></script>
    <style type="text/css">
    	.textareaW100 {
    		margin-top: 0px;
    		margin-bottom: 0px;
    		height:100px;
    		width:340px;
    	}
    </style>
</head>
<body>
<div class="layui-container">
    <div class="layui-collapse" lay-accordion>
        <div class="layui-colla-item">
            <h2 class="layui-colla-title">留言</h2>

            <div class="layui-colla-content ${collapseState!='emailQuery'?'layui-show':''}">
                <form class="layui-form" action="${pageContext.request.contextPath}/emailSave" method="post">
                    <fieldset class="layui-elem-field layui-field-title layui_fieldset_padding">
                        <legend>留言信息</legend>

                        <div class="layui-form-item">
                            <label class="layui-form-label">标题：</label>

                            <div class="layui-input-block">
                                <input type="text" name="title" required lay-verify="required" placeholder="请输入标题" autocomplete="off"
                                       class="layui-input" value="${message.title}">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">留言类别：</label>

                            <div class="layui-input-inline">
                                <select name="type" lay-verify="required">
                                    <option value="建议">建议</option>
                                    <option value="咨询">咨询</option>
                                    <option value="投诉">投诉</option>
                                    <option value="表扬">表扬</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">留言内容：</label>

                            <div class="layui-input-block">
                                <textarea name="content" placeholder="留言字数请不要超过300个字" class="textareaW100" lay-verify="required">${message.content}</textarea>(必填)
                            </div>
                        </div>
                    </fieldset>

                    <fieldset class="layui-elem-field layui-field-title layui_fieldset_padding">
                        <legend>个人信息</legend>
                        <div class="layui-form-item">
                            <label class="layui-form-label">网名：</label>

                            <div class="layui-input-inline">
                                <input type="text" name="nickname" required lay-verify="required" placeholder="请输入网名" autocomplete="off"
                                       class="layui-input" value="${message.nickname}">
                            </div>
                            <label class="layui-form-label">手机：</label>

                            <div class="layui-input-inline">
                                <input type="text" name="mobilephone" placeholder="请输入手机" autocomplete="off" class="layui-input"
                                       value="${message.mobilephone}">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">邮箱：</label>

                            <div class="layui-input-inline">
                                <input type="text" name="email" required lay-verify="required|email" placeholder="请输入邮箱" autocomplete="off"
                                       class="layui-input" value="${message.email}">
                            </div>
                            <label class="layui-form-label">验证码：</label>

                            <div class="layui-input-inline">
                                <input type="text" name="checkcode" required lay-verify="required" placeholder="请输入验证码" autocomplete="off" class="layui-input">
                            </div>
                            <div class="layui-input-inline">
                                <img src="${pageContext.request.contextPath}/getCaptcha" id="captchaImg" class="captchaImg">
                            </div>
                        </div>
                        <div>
                            ${errorMsg}
                        </div>
                    </fieldset>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="layui-btn" lay-submit lay-filter="emailNew">提交</button>
                            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                            <button type="back" class="layui-btn layui-btn-normal" onclick="javascript:history.back(-1);">返回</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="layui-colla-item">
            <h2 class="layui-colla-title">查询回复</h2>

            <div class="layui-colla-content ${collapseState=='emailQuery'?'layui-show':''}">
                <form class="layui-form" action="${pageContext.request.contextPath}/emailQuery" method="post">
                    <div class="layui-form-item">
                        <label class="layui-form-label">邮箱：</label>

                        <div class="layui-input-block">
                            <input type="text" name="email" required lay-verify="required|email" placeholder="请输入邮箱" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">密钥：</label>

                        <div class="layui-input-block">
                            <input type="text" name="querykey" required lay-verify="required" placeholder="请输入密钥" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="layui-btn" lay-submit lay-filter="emailReply">提交</button>
                            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                            <button type="back" class="layui-btn layui-btn-normal" onclick="javascript:history.back(-1);">返回</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
