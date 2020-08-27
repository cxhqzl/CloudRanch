let module = angular.module('farmerManagementSideApp', ['ng', 'ngRoute']);
module.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider
            .when('/essentialInformation', {
                templateUrl: '../cloudranch/farmerManagement/cp_essentialInformation.html'
            })
            .when('/plotManagement', {
                templateUrl: '../cloudranch/farmerManagement/cp_plotManagement.html'
            })
            .when('/plotManagement-sub', {
                templateUrl: '../cloudranch/farmerManagement/cp_plotManagement-sub.html'
            })
            .when('/customerInformation', {
                templateUrl: '../cloudranch/farmerManagement/cp_customerInformation.html'
            })
            .when('/informationentry-plant', {
                templateUrl: '../cloudranch/farmerManagement/cp_informationentry-plant.html'
            })
            .when('/informationentry-fertilize', {
                templateUrl: '../cloudranch/farmerManagement/cp_informationentry-fertilize.html'
            })
            .when('/informationentry-harvest', {
                templateUrl: '../cloudranch/farmerManagement/cp_informationentry-harvest.html'
            })
            .when('/informationentry-logistics', {
                templateUrl: '../cloudranch/farmerManagement/cp_informationentry-logistics.html'
            })
            .when('/informationentry-register', {
                templateUrl: '../cloudranch/farmerManagement/cp_informationentry-register.html'
            })
            .when('/informationentry-breed', {
                templateUrl: '../cloudranch/farmerManagement/cp_informationentry-breed.html'
            })
            .when('/informationentry-butcher', {
                templateUrl: '../cloudranch/farmerManagement/cp_informationentry-butcher.html'
            })
            .when('/informationentry-animallogistics', {
                templateUrl: '../cloudranch/farmerManagement/cp_informationentry-animallogistics.html'
            })
            .when('/farmerManagementSetting', {
                templateUrl: '../cloudranch/farmerManagement/cp_farmerManagementSetting.html'
            })
            .when('/plotManagementAnimal', {
                templateUrl: '../cloudranch/farmerManagement/cp_plotManagementAnimal.html'
            })
            .otherwise({
                redirectTo: '/essentialInformation'
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
    var pageChoose;
    if (url.lastIndexOf('-') === -1) {
        pageChoose = url.slice(url.lastIndexOf('/') + 1);
    } else {
        pageChoose = url.slice(url.lastIndexOf('/') + 1, url.lastIndexOf('-'));
    }
    if (pageChoose == 'informationentry') {
        $('a[href="#!/' + url.slice(url.lastIndexOf('/') + 1) + '"]').parent('li').addClass('choose');
    } else {
        $('a[href="#!/' + pageChoose + '"]').parent('li').addClass('choose');
    }
    // 侧边栏动画
    $('.side-lists > li').click(function () {
        if (!$(this).hasClass('slideDown-li')) {
            $(this).addClass('choose').siblings().removeClass('choose');
            $(this).siblings('.slideDown-li').find('li').removeClass('choose');
        }
    });
    $('.slideDown-li li').click(function () {
        $('.side-lists').find('li').removeClass('choose');
        $(this).addClass('choose');
    });
    // 解决鼠标快速进入进出时文字显示的问题
    $('.side-lists').hover(function () {
        if (slideFlag == true) {
            side.css('width', '200px');
            showinfo.css('left', '200px');
            setTimeout(function () {
                if (stopShow == true) {
                    stopShow = false;
                } else {
                    sideText.removeClass('hide');
                    textHideFlag = false;
                }
            }, 200);
        }
    }, function () {
        if (slideFlag == true) {
            side.css('width', '56px');
            showinfo.css('left', '56px');
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
            showinfo.css('left', '56px');
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
            showinfo.css('left', '200px');
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
    // 取消按钮
    $('.delete .mg-pop-mod__body_cancel').click(function () {
        $(this).parents('.delete').addClass('hide');
    });
    // 侧边栏下拉
    $('.slideDown-li').hover(function () {
        $(this).find('.slideDown-items').stop().slideDown('fast');
        $(this).find('.title .glyphicon').addClass('slideup');
    }, function () {
        $(this).find('.slideDown-items').stop().slideUp('fast');
        $(this).find('.title .glyphicon').removeClass('slideup');
    });
});
