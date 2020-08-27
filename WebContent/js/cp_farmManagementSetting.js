// 阻止浏览器中鼠标右键弹出下拉菜单
document.oncontextmenu = function (event) {
    event.preventDefault();
};

var mySwiper = new Swiper('.swiper-container', {
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
    },
    loop: true,
    effect: 'coverflow'
});

