$(function () {
    // 个人信息下拉菜单 
    $('.header__img').mouseenter(function () {
        $(this).children('.userinfo-mod').stop().slideDown('fast');
    }).mouseleave(function () {
        let mod = $(this).children('.userinfo-mod');
        mod.stop().slideUp('fast');
    });
    // 导航动画
    $('.page-settings__nav>li').click(function () {
        let num = $(this).data('num');
        $('.page-settings__' + $(this).data('name')).removeClass('hide').siblings().addClass('hide');
        $('.bottom-bar').animate({ left: num * 64 + (2 * num + 1) * 10 + 'px' }, 'fast');
        $('.page-settings__' + $(this).data('name') + ' .page-settings__buttons_cancel').click();
    });
    // 控制密码是否可视
    let isPsdShow = false;
    $('.page-settings__account-security_psd img').click(function () {
        if (isPsdShow) {
            $(this).attr('src', '../img/闭眼.png');
            $(this).parent().siblings('input').attr('type', 'password');
            isPsdShow = false;
        } else {
            $(this).attr('src', '../img/睁眼.png');
            $(this).parent().siblings('input').attr('type', 'text');
            isPsdShow = true;
        }
    });
    // 表单动画
    $('.page-settings__myinfo .page-settings__edit > span').click(function () {
        let element = $(this).parent().siblings();
        $(this).parent().addClass('hide');
        $(this).parent().siblings('.page-settings__buttons').removeClass('hide');
        element.find('input').removeAttr('readonly').css('border', '1px solid #999');
        element.find('.page-settings__myinfo_gender').removeClass('hide').siblings('div').addClass('hide');
        element.find('select').removeClass('hide').siblings().addClass('hide');
        element.find('.page-settings__myinfo_img > div').removeClass('hide');
    });
    $('.page-settings__myinfo .page-settings__buttons_cancel').click(function () {
        let element = $(this).parent().siblings();
        $(this).parent().addClass('hide');
        $(this).parent().siblings('.page-settings__edit').removeClass('hide');
        element.find('input').attr('readonly', true);
        element.find('input').css('border', '0');
        element.find('.page-settings__myinfo_gender').addClass('hide').siblings('div').removeClass('hide');
        element.find('select').addClass('hide').siblings().removeClass('hide');
        element.find('.page-settings__myinfo_img > div').addClass('hide');
    });
    $('.page-settings__account-security .page-settings__edit > span').click(function () {
        let element = $(this).parent().siblings();
        $(this).parent().addClass('hide');
        $(this).parent().siblings('.page-settings__buttons').removeClass('hide');
        element.find('input').removeAttr('readonly').css('border', '1px solid #999');
    });
    $('.page-settings__account-security .page-settings__buttons_cancel').click(function () {
        let element = $(this).parent().siblings();
        $(this).parent().addClass('hide');
        $(this).parent().siblings('.page-settings__edit').removeClass('hide');
        element.find('input').attr('readonly', true);
        element.find('input').css('border', '0');
    });
});