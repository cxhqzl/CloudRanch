$('.sa-buttons .add').click(function () {
    if ($('.sensor .add').hasClass('hide')) {
        $('.sensor .add').removeClass('hide');
        $('.sa-buttons .save').removeClass('hide');
        $(this).html('取消');
    } else {
        $('.sensor .add').addClass('hide');
        $('.sa-buttons .save').addClass('hide');
        $(this).html('添加传感器');
    }
});
