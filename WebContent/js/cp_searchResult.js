$(function () {
    $('.results__item_tip .icon,.results__item_tip ul').mouseenter(function () {

        $('.results__item_tip ul').stop().slideDown('fast');
    });
    $('.results__item_tip .icon,.results__item_tip ul').mouseleave(function () {
        $('.results__item_tip ul').stop().slideUp('fast');
    });
});

