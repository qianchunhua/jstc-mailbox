jQuery(document).ready(function () {
    initTable();
});

function initTable() {
    var tableConfig = {};
    //定义表格的表头，field表示数据中对应的字段名称，title表示显示用的表头名称
    tableConfig["cols"] = [[
        //{type: 'checkbox'},
        {field: 'title', title: '标题'},
        {field: 'content', title: '留言内容'},
        {field: 'reply', title: '领导回复内容'},
        {field: 'nickname', title: '网名'},
        {field: 'email', title: '邮箱地址'},
        {field: 'submitdatestr', title: '留言提交时间'},
        {field: 'replydatestr', title: '回复时间'}       
    ]];
    //定义表格ID
    tableConfig["elem"] = "#emailQuery";
    
    //获取路径
    var pathName=window.document.location.pathname;
    //截取，得到项目名称
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    
    //分页数据请求URL
    tableConfig["url"] = projectName+'/emailQueryShow';
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
    var $table = layui.table;
    $table.render(tableConfig);
}