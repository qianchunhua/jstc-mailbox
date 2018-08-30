jQuery(document).ready(function () {
    initTable();
    initToolbar();
    emailReply();
});
function initTable() {
    var tableConfig = {};
    //定义表格的表头，field表示数据中对应的字段名称，title表示显示用的表头名称
    tableConfig["cols"] = [[
        {type: 'checkbox'},
        {field: 'title', title: '标题', event: 'reply'},
        {field: 'content', title: '留言内容', event: 'reply'},
        {field: 'nickname', title: '网名', event: 'reply'},
        {field: 'email', title: '邮箱地址', event: 'reply'},
        {field: 'submitdatestr', title: '留言提交时间', event: 'reply'},
        {field: 'replydatestr', title: '回复时间', event: 'reply'},
        {field: 'state', title: '状态', width: 80, event: 'reply'},
        {field: 'forwardorder', title: '已转发次数', width: 100, event: 'reply'},
        {field: 'newestuserforward', title: '最新转发', templet: '#newestUserForwardTpl', event: 'reply'},
    ]];
    //定义表格ID
    tableConfig["elem"] = "#emailList";
    
    //获取路径
    var pathName=window.document.location.pathname;
    //截取，得到项目名称
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    
    //分页数据请求URL
    tableConfig["url"] = projectName+'/emailList';
    //定义向服务器请求数据时，URL中页码字段的名称和每页显示条数字段的名称
    tableConfig["request"] = {
        pageName: 'page',
        limitName: 'pageSize'
    };
    //分页参数
    tableConfig["page"] = {
        groups: 5,
        prev: "上一页",
        next: "下一页"
    };    
    /*
    var $ = layui.jquery;
 
    tableConfig["done"] = function (res, curr, count) {   
    	LayUIDataTable.SetJqueryObj($);
    	var currentRowDataList = LayUIDataTable.ParseDataTable(function (index, currentData, rowData) {    
    	    var email = res.data[index].email;
            var querykey = res.data[index].querykey;
    		var index = layer.open({
                type: 2,
                title: "回复留言",
                content: projectName+"/replyPage?email=" + encodeURIComponent(email) + "&querykey=" + querykey,
                end: function () {
                    $table.reload("emailList");
                }
            });
            layer.full(index);
    	})
    }  
    */
    var $table = layui.table;
    $table.render(tableConfig);
}
function initToolbar() {
    //获取路径
    var pathName=window.document.location.pathname;
    //截取，得到项目名称
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    jQuery("#emailForward").bind("click", function () {
        var $table = layui.table;
        var checkStatus = $table.checkStatus('emailList');
        if (checkStatus.data.length != 1) {
            layer.msg("只能选择一个留言进行回复操作！", {time: 2000});
        } else {
            var email = checkStatus.data[0].email;
            var querykey = checkStatus.data[0].querykey;
            var index = layer.open({
                type: 2,
                title: "选择转发用户",
                content: projectName+"/userPage?email=" + encodeURIComponent(email) + "&querykey=" + querykey,
                end: function () {
                    $table.reload("emailList");
                }
            });
            layer.full(index);
        }
    });
}
function emailReply(){
	layui.use('table', function(){
	  var table = layui.table; 
	  //监听单元格事件
	  table.on('tool(emailList)', function(obj){
	    var data = obj.data;	    
	    if(obj.event === 'reply'){
	        //获取路径
	        var pathName=window.document.location.pathname;
	        //截取，得到项目名称
	        var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	    	var email = data.email;
            var querykey = data.querykey;
    		var index = layer.open({
                type: 2,
                title: "回复留言",
                content: projectName+"/replyPage?email=" + encodeURIComponent(email) + "&querykey=" + querykey,
                end: function () {
                    $table.reload("emailList");
                }
            });
            layer.full(index);       
	    }
	  });
	});
}