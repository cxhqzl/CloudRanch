$(function () {
    var htmlWidth = parseFloat($('html').outerWidth());
    var banner = $('.banner');
    var sideBar = $('.side-bar');
    var imgNum = $('.banner>img').length;
    var curImgNum = 1;
    // 箭头动画
    $('.side-bar__buttons_angle>span:nth-child(1)').click(function () {
        if (curImgNum == 1) {
            banner.css('left', '-' + (imgNum - 1) * htmlWidth + 'px');
            curImgNum = imgNum;
            console.log(imgNum);
        } else {
            var left = parseFloat(banner.css('left'));
            banner.css('left', left + htmlWidth + 'px');
            curImgNum--;
        }
        setTimeout(function () {
            let src = $('.banner>img:nth-child(' + curImgNum + ')').attr('src');
            $('.side-bar__img > img').attr('src', src);
        }, 200);
    });
    $('.side-bar__buttons_angle>span:nth-child(2)').click(function () {
        if (curImgNum == imgNum && curImgNum != 1) {
            banner.css('left', 0);
            curImgNum = 1;
        } else {
            var left = parseFloat(banner.css('left'));
            banner.css('left', left - htmlWidth + 'px');
            curImgNum++;
        }
        setTimeout(function () {
            let src = $('.banner>img:nth-child(' + curImgNum + ')').attr('src');
            $('.side-bar__img > img').attr('src', src);
        }, 200);
    });
    // 侧边栏刷新
    $('.side-bar__buttons_redo').on('click', function () {
        $(this).children('.fa-redo').animate({
            transform: 'rotateZ(180deg)'
        }, 'slow');
    });
    // 侧边栏隐藏显示
    $('.side-bar__buttons_bars').click(function () {
        sideBar.toggleClass('hideRight');
    });
    $(window).resize(function () {
        htmlWidth = parseFloat($('html').outerWidth());
        $('.banner>img').css('width', htmlWidth);
    });
    // 搜索框自动提示
    $('.search__input_wrapper input').on('input', function (event) {
        event.stopPropagation();
        // // console.log($.trim($(this).val()));
        var res = showMates();
        let html = '';
        if(res == "@"){
        	$('.search__input_tips_title > span').html(0);
        	$('.search__input_tips > ul').html('');
        }else{
        	var names = res.split("@")[0];
        	var ids = res.split("@")[1];
        	var re = names.split(",");
        	var id = ids.split(",");
        	$('.search__input_tips_title > span').html(re.length-1);
        	for (let i = 0; i < re.length-1; i++) {
                html = html + "<li title='"+re[i]+"' onclick='siteDetail(\""+id[i]+"\")'>"+re[i]+"</li>";
            }
        	$('.search__input_tips > ul').html(html);
        }
        let tips = $('.search__input_tips');
        let keyword = $.trim($(this).val());
        tips.slideDown();
        if (keyword == '') {
            tips.slideUp();
        }
    }).on('click', function (event) {
        event.stopPropagation();
    });
    $('.search__input_tips').on('click', function (event) {
        event.stopPropagation();
    })
    $('.search__input_tips > ul').on('click', 'li', function (event) {
        event.stopPropagation();
        let keyword = $(this).html();
        $('.search__input_wrapper input').val(keyword);
        $('.search__input_tips').slideUp();
    });
    $('body').on('click', function () {
        $('.search__input_tips').slideUp();
    });
});
