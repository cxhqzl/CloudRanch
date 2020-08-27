let module = angular.module('manageSideApp', ['ng', 'ngRoute']);
 
module.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider
            .when('/farmManagement', {
                templateUrl: '../cloudranch/management/cp_merchantManagement.html'
            })
            .when('/userManagement', {
                templateUrl: '../cloudranch/management/cp_userManagement.html'
            })
            .when('/farmManagement-sub', {
                templateUrl: '../cloudranch/management/cp_farmManagement-sub.html'
            })
            .when('/farmManagement-plot', {
                templateUrl: '../cloudranch/management/cp_farmManagement-plot.html'
            })
            .otherwise({
                redirectTo: '/farmManagement'
            });
    }]);

$(function () {
    // 侧边栏动画
    let sideText = $('.side-lists > li > a > span:nth-child(2)');
    let side = $('.side');
    let showinfo = $('.manage-area');
    let slideFlag = false;
    let textHideFlag = false;//表示侧边栏是否收缩
    let stopShow = false;//表示是否组织文字显示
    // 网页刷新侧边栏高亮问题
    let url = window.location.href;
    if (url.lastIndexOf('-') === -1) {
        var pageChoose = url.slice(url.lastIndexOf('/') + 1);
    } else {
        var pageChoose = url.slice(url.lastIndexOf('/') + 1, url.lastIndexOf('-'));
    }
    $('a[href="#!/' + pageChoose + '"]').parents('li').addClass('choose');
    // 侧边栏动画
    $('.side-lists > li').click(function () {
        $(this).addClass('choose').siblings().removeClass('choose');
    });
    // 解决鼠标快速进入进出时文字显示的问题
    $('.side-lists').mouseenter(function () {
        if (slideFlag == true) {
            side.css('width', '200px');
            showinfo.css('left', '201px');
            setTimeout(function () {
                if (stopShow == true) {
                    stopShow = false;
                } else {
                    sideText.removeClass('hide');
                    textHideFlag = false;
                }
            }, 200);
        }
    }).mouseleave(function () {
        if (slideFlag == true) {
            side.css('width', '56px');
            showinfo.css('left', '57px');
            if (textHideFlag == true) {
                stopShow = true;
            } else {
                sideText.addClass('hide');
            }
            textHideFlag = true;
        }
    });
    $('.menu-fold > span').click(function () {
        if (!$(this).hasClass('rotateZ180')) {
            $(this).addClass('rotateZ180');
            side.css('width', '56px');
            showinfo.css('left', '57px');
            slideFlag = true;
            if (textHideFlag == true) {
                stopShow = true;
            } else {
                sideText.addClass('hide');
            }
            textHideFlag = true;
        } else {
            $(this).removeClass('rotateZ180');
            side.css('width', '200px');
            showinfo.css('left', '201px');
            slideFlag = false;
            setTimeout(function () {
                if (stopShow == true) {
                    stopShow = false;
                } else {
                    sideText.removeClass('hide');
                    textHideFlag = false;
                }
            }, 200);
        }
    });
});