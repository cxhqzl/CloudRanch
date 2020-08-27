$(function () {
    // 下拉菜单动画
    let dropdownMenuMod = $('.dropdown-menu-mod');
    let item = $('.dropdown-menu-mod__oths>li');
    let iconHtml = `<span class="glyphicon glyphicon-chevron-down rotateZ180"
    aria-hidden="true"></span>`;
    dropdownMenuMod.mouseenter(function () {
        $(this).find('.glyphicon-chevron-down').addClass('rotateZ180');
        $(this).children('.dropdown-menu-mod__oths').removeClass('hide');
    }).mouseleave(function () {
        $(this).find('.glyphicon-chevron-down').removeClass('rotateZ180');
        $(this).children('.dropdown-menu-mod__oths').addClass('hide');
    });
    item.click(function () {
        let text = $(this).text();
        let curr = $(this).parent().siblings('span');
        if (curr.text() !== text) {
            curr.html(text + iconHtml);
            $(this).parent('.dropdown-menu-mod__oths').addClass('hide');
        }
    });
    // 设置表单动画
    $('.mg-panel .glyphicon-pencil').on('click', function (event) {
        event.preventDefault();
        $(this).parents('.setting').siblings().find('.glyphicon-saved').trigger('click');
        $(this).parentsUntil('').children();
        let ele = $(this).siblings('p');
        $(this).addClass('hide');
        $(this).siblings('.glyphicon-saved').removeClass('hide');
        ele.children('input')
            .removeAttr('readonly')
            .css('border', '1px solid #999');
        let height = ele.children('.farmer-introduction').height() + 'px';
        ele.children('.farmer-introduction')
            .addClass('hide')
            .siblings('textarea')
            .removeClass('hide')
            .css('border', '1px solid #999')
            .height(height);
    });
    $('.mg-panel .glyphicon-saved').on('click', function (event) {
        event.preventDefault();
        let ele = $(this).siblings('p');
        $(this).addClass('hide');
        $(this).siblings('.glyphicon-pencil').removeClass('hide');
        ele.children('input')
            .css('border', '1px solid transparent')
            .attr('readonly', true);
        ele.children('.farmer-introduction')
            .removeClass('hide')
            .siblings('textarea')
            .addClass('hide')
            .css('border', '1px solid transparent');

    });
    $('.mg-panel .glyphicon-trash').on('click', function (event) {
        event.preventDefault();

        // $('.delete').removeClass('hide');
    });
    // 地块管理设置
    let href = '';
    $('.manage-area-main>.row').on('click', '.glyphicon-pencil', function (event) {
        event.preventDefault();
        $(this).parents('.setting').siblings().find('.glyphicon-saved').trigger('click');
        $(this).parentsUntil('').children();
        let ele = $(this).siblings('p');
        $(this).addClass('hide');
        $(this).siblings('.glyphicon-saved').removeClass('hide');
        ele.children('input')
            .removeAttr('readonly')
            .css('border', '1px solid #999');

        $(this).parents('.plotSetting').siblings().find('.glyphicon-saved').trigger('click');
        let elem = $(this).parents('.plotSetting').children('a');
        href = elem.attr('href');
        elem.attr('href', 'javaScript:void(0);');
        $(this).siblings('p').find('input').css({
            cursor: 'text',
            textAlign: 'left'
        });
    });
    $('.manage-area-main>.row').on('click', '.glyphicon-saved', function (event) {
        event.preventDefault();
        let ele = $(this).siblings('p');
        $(this).addClass('hide');
        $(this).siblings('.glyphicon-pencil').removeClass('hide');
        ele.children('input')
            .css('border', '1px solid transparent')
            .attr('readonly', true);

        let elem = $(this).parents('.plotSetting').children('a');
        elem.attr('href', href);
        $(this).siblings('p').find('input').css({
            cursor: 'pointer',
            textAlign: 'right'
        });
    });
});