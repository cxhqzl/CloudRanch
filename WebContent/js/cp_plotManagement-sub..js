$('.tabs .tab').click(function () {
    let num = $(this).data('num');
    $(this)
        .parent()
        .siblings('.tab-page-container')
        .children('.tab-page-' + num)
        .removeClass('hide')
        .siblings()
        .addClass('hide');
    $(this).siblings('.bar').css('left', (82 * num) + 'px');;
});