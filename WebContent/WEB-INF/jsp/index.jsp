<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page language="java"%> 
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<!DOCTYPE html>
<html>
<head>
    <title>欢迎使用！</title>
    <link href="${pageContext.request.contextPath}/js/css/layui.css" type="text/css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/layui.all.js" type="text/javascript"></script>
	<script type="text/javascript">
    	function closeWin() {
    		if (confirm('您确定要退出吗？')) {
    			window.close();
    		} else {
    			return false;
    		}
    	}
    </script>
    <style type="text/css">
   	.w1000 {
   		padding-top: 16px;
		height: 76px;
		line-height: 26px;
		text-align: center;
		font-size: 12px;
		margin-top:400px;
   	}
    </style>
</head>
<body>
<div class="layui-container">	
	<img src="./images/top.jpg"><br>
	<p style="margin-left:775px; font-size:18px;">
		<%
	    	java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy年MM月dd日");  
	   		java.util.Date currentTime = new java.util.Date();  
	   		String time = simpleDateFormat.format(currentTime).toString();
	   		out.println("访客您好！" + time);
	     %>
     </p>	 
	<div class="layui-row" style="margin-top:30px;">
    	<a href="${pageContext.request.contextPath}/emailNew" class="layui-btn layui-btn-lg layui-btn-normal">撰写信件</a><br><br>
        <a href="${pageContext.request.contextPath}/emailReply" class="layui-btn layui-btn-lg layui-btn-normal">查看回信</a><br><br>     
        <a href="${pageContext.request.contextPath}/leadershipLoginPage" class="layui-btn layui-btn-lg layui-btn-normal">院长登录</a><br><br>
        <a href="" class="layui-btn layui-btn-lg layui-btn-normal" onclick="closeWin()">安全退出</a>
    </div>
	<table width="700" cellspacing="0" border="0" align="center" style="margin-top:-210px;">
   		<tbody>
	   		<tr>
	   			<td style="width:300px; text-align:center;">
	   				<p><img src="./images/12.jpg" width="125" height="170"> </p>
	   				<p>院长：周春光</p>
	   			</td>	   			
	   			<td>
	   				<p style="text-align:center; font-size:24px;">感&nbsp;&nbsp;谢</p>
	   				<p style="text-align:left; font-size:16px; line-height:1.5;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	   				          真诚地欢迎您为江苏旅游职业学院建设与发展提出意见与建议，对我们工作中的不足提出批评。
	   				</p>
	   				<p style="text-align:left; font-size:16px; line-height:1.5;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	   					对于您的每一封来信，我们将尽快给予答复。
	   				</p>
	   			</td>
	   		</tr>
    	</tbody>    	
    </table>
    
    <div class="footer">
    	<div id="copyright" class="w1000">    		
    		<p>版权所有 © 江苏旅游职业学院 <a href="http://www.miit.gov.cn/" target="_blank">苏ICP备11034729号-1 </a> 学院官网：<a href="http://www.jstc.edu.cn/" target="_blank">www.jstc.edu.cn</a></p>
    		<p>地址：江苏省扬州市邗江中路98号    邮编：225127    电话：0514-87431881  传真：0514-87431883</p>  		
    	</div>
    </div>
</div>
</body>
</html>
