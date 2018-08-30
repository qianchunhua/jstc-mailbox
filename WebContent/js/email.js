jQuery(function () {
    var $form = layui.form;
    $form.render();

    var $element = layui.element;
    $element.render();

    jQuery("#captchaImg").bind("click", function () {
        var $self = jQuery(this);
        var src = $self.attr("src");
        var index = src.indexOf("?");
        if (index != -1) {
            src = src.substring(0, index);
        }
        var date = new Date();
        src += "?t=" + date.getTime();
        $self.attr("src", src);
    });
});