// 添加记录
$('.addrecord').click(function () {
    let saverecord = $(this).siblings('.saverecord');
    let add = $(this).parents('h3').siblings('table').find('.add');
    if (add.hasClass('hide')) {
        let date = new Date();
        let year = date.getFullYear();
        let month = date.getMonth() + 1;
        let day = date.getDate();
        month = month < 10 ? '0' + month : month;
        day = day < 10 ? '0' + day : day;
        let dateStr = `${year}-${month}-${day}`;
        add.find('.date').val(dateStr);
        add.removeClass('hide');
        saverecord.removeClass('hide');
        $(this).html('取消添加');
    } else {
        add.addClass('hide');
        saverecord.addClass('hide');
        $(this).html('添加记录');
    }
});
$('.addrecord-t').click(function () {
    let saverecord = $(this).siblings('.saverecord-t');
    let add = $(this).parents('h3').siblings('.add');
    if (add.hasClass('hide')) {
        let date = new Date();
        let year = date.getFullYear();
        let month = date.getMonth() + 1;
        let day = date.getDate();
        month = month < 10 ? '0' + month : month;
        day = day < 10 ? '0' + day : day;
        let dateStr = `${year}-${month}-${day}`;
        add.find('.date').val(dateStr);
        add.removeClass('hide');
        saverecord.removeClass('hide');
        $(this).html('取消添加');
    } else {
        add.addClass('hide');
        saverecord.addClass('hide');
        $(this).html('添加记录');
    }
});
// 获取bendi 路径
function getFullPath(obj) {
    if (obj) {
        //Internet Explorer 
        if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
            obj.select();
            return document.selection.createRange().text;
        }
        //Firefox
        if (window.navigator.userAgent.indexOf("Firefox") >= 1) {
            if (obj.files) {
                return obj.files.item(0).getAsDataURL();
            }
            return obj.value;
        }

        //兼容chrome、火狐等，HTML5获取路径       
        if (typeof FileReader != "undefined") {
            var reader = new FileReader();
            reader.onload = function (e) {
                document.getElementById("pic").src = e.target.result + "";
            }
            reader.readAsDataURL(obj.files[0]);
        } else if (browserVersion.indexOf("SAFARI") > -1) {
            alert("暂时不支持Safari浏览器!");
        }
    }
}

function showPic(obj) {
    var fullPath = getFullPath(obj);
    if (fullPath) {
        document.getElementById("pic").src = fullPath + "";
    }
}