//全景图显示与隐藏
$('.panorama .icon').click(function() {
    $(this).parent('.panorama').toggleClass('parnorama-hide');
    $(this).toggleClass('icon-up');
});
//全景图左右滑动
let id ;
$('.fa-angle-double-left').hover(function() {
    let dom = $('.pics');
    let p;
    id = setInterval(function() {
        p = dom.scrollLeft()
        dom.scrollLeft(p-1);
    },1);
}, function() {
    clearInterval(id);
});
$('.fa-angle-double-right').hover(function() {
    let dom = $('.pics');
    let p;
    id = setInterval(function() {
        p = dom.scrollLeft()
        dom.scrollLeft(p+1);
    },1);
}, function() {
    clearInterval(id);
});
