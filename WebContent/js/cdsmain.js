$('.c-panorama-management .panoramas .inner ').hover(function () {
    $(this).find('.glyphicon-trash').removeClass('hide');
}, function () {
    $(this).find('.glyphicon-trash').addClass('hide');
});