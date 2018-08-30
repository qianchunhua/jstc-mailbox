jQuery(function () {
    initTable();
    initButton();
});

function initTable() {
    var tableConfig = {};
    //定义表格的表头，field表示数据中对应的字段名称，title表示显示用的表头名称
    tableConfig["cols"] = [[
        {type: 'checkbox'},
        {field: 'username', title: '用户名'},
        {field: 'displayname', title: '姓名'}
    ]];
    //定义表格ID
    tableConfig["elem"] = "#userList";
    
    //获取路径
    var pathName=window.document.location.pathname;
    //截取，得到项目名称
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    
    //分页数据请求URL
    tableConfig["url"] = projectName+'/userList';
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

function initButton() {
    jQuery("#userForwardSubmit").bind("click", function () {
        var $table = layui.table;
        var checkStatus = $table.checkStatus('userList');
        if (checkStatus.data.length != 1) {
            layer.msg("只能选择一个用户进行操作！", {time: 2000});
        } else {
            var username = checkStatus.data[0].username;
            var email = getQueryString("email");
            var querykey = getQueryString("querykey");
            //获取路径
            var pathName=window.document.location.pathname;
            //截取，得到项目名称
            var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
            
            jQuery.ajax({
                url: projectName+"/userForward",
                type: "post",
                data: "tousername=" + username + "&email=" + email + "&querykey=" + querykey,
                complete: function (msg) {
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                }
            });
        }
    });
}

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return (r[2]);
    return null;
}