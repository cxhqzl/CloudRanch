// 注册
$('#register').on('click', function () {
    layer.open({
        type: 2,
        title: false,
        closeBtn: 0,
        area: ['500px', '450px'],
        shadeClose: true, //点击遮罩关闭
        content: './cloudranch/publicHtml/popWinRegister.html',
        scrollbar: false,
        resize: false
    });
});
// 登录
$('#login').on('click', function () {
    layer.open({
        type: 2,
        title: false,
        closeBtn: 0,
        area: ['330px', '400px'],
        shadeClose: true, //点击遮罩关闭
        content: './cloudranch/publicHtml/popWinLogin.html',
        scrollbar: false,
        resize: false
    });
});

function openLogin() {
    layer.open({
        type: 2,
        title: false,
        closeBtn: 0,
        area: ['330px', '400px'],
        shadeClose: true, //点击遮罩关闭
        content: './publicHtml/popWinLogin.html',
        scrollbar: false,
        resize: false
    });
}