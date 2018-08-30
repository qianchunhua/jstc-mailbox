jQuery(function () {
    var $form = layui.form;
    $form.on('submit(replyEdit)', function (data) {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    });
    $form.render();
});