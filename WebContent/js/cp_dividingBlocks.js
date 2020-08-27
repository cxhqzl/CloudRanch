// 阻止浏览器中鼠标右键弹出下拉菜单
document.oncontextmenu = function (event) {
    event.preventDefault();
};

var map;
var plots = [];//存储目前站点已有的地块
var currPlot = null;//当前操作的地块
function createExistentPlot(plotArray) {
    // new google.maps.LatLng(-34, 151)
    for (let i = 0; i < plotArray.length; i++) {
        var latlngs = plotArray[i].latLng;
        let pathArray = new google.maps.MVCArray();
        for (let j = 0; j < latlngs.length; j++) {
            pathArray.push(new google.maps.LatLng(latlngs[j].lat, latlngs[j].lng));
        }
        let plot = createPlot(pathArray, plotArray[i]);
        plot.target.setDraggable(false);
        plot.target.setEditable(false);
    }
}
//获取经纬度数组
function getLatLngArray() {
    var latLngArray = "";
    currPlot.target.getPath().forEach(function (item) {
    	latLngArray += item.lng() + ',' + item.lat() + "@";
        //latLngArray.push(item.lng() + ',' + item.lat());
    });
    
    return latLngArray.substring(0,latLngArray.length-1);
}
// 创建地块函数
function createPlot(pathArray, plot) {
    let polygon = {
        target: new google.maps.Polygon({
            path: pathArray || new google.maps.MVCArray(),
            strokeColor: '#FF0000',
            strokeOpacity: 0.8,
            strokeWeight: 2,
            fillColor: '#FF0000',
            fillOpacity: 0.35,
            editable: true,
            draggable: true
        }),
        name: plot ? plot.name : undefined,
        croptype: plot ? plot.croptype : undefined,
		square: plot ? plot.square : undefined,
        videourl: plot ? plot.videourl : undefined

    }
    // 绑定地块双击事件
    polygon.target.addListener('dblclick', function (event) {
        event.stop();
        if (this.getEditable() === false) {
            this.setEditable(true);
            this.setDraggable(true);
            // 找到当前地块
            for (let i = 0; i < plots.length; i++) {
                if (this === plots[i].target) {
                    currPlot = plots[i];
                } else {
                    plots[i].target.setDraggable(false);
                    plots[i].target.setEditable(false);
                }
            }
            $('#side-name').val(currPlot.name);
            $('#side-croptype').val(currPlot.croptype);
            $('#side-strokecolor').val(currPlot.target.strokeColor);
            $('#side-strokeopacity').val(currPlot.target.strokeOpacity * 100);
            $('#side-fillcolor').val(currPlot.target.fillColor);
            $('#side-fillopacity').val(currPlot.target.fillOpacity * 100);

            $('.mg-plot-side').removeClass('hide');
        }
    });
    plots.push(polygon);
    polygon.target.setMap(map);
    return polygon;
}

// 初始化函数
function initMap(lat,lng) {
    // 初始化地图
    map = new google.maps.Map(document.getElementById('bgMap'), {
        center: {
            lat: lat,
            lng: lng
        },
        zoom: 18,
        disableDefaultUI: true,
        mapTypeId: 'satellite',
        gestureHandling: 'greedy',
        minZoom: 4
    });
    map.addListener('click', function (event) {
        if (currPlot === null) {
            currPlot = createPlot();
        }
        currPlot.target.getPath().push(event.latLng);
        // console.log(currPlot.target);
        // console.log(path);
    });
    map.addListener('rightclick', function (event) {
        if (currPlot !== null) {
            if (currPlot.target.getPath().getLength() === 1) {
                currPlot.target.getPath().clear();
                plots.pop();
                currPlot = null;
            } else {
                if ($('.mg-plot-side').hasClass('hide')) {
                    $('.plot-form').removeClass('hide');
                } else {
                    $('.alert-win').removeClass('hide');
                    // $('.mg-plot-side').addClass('hide')
                }
            }
        }
    });
}

// 地块信息弹出框
$('.plot-form .mg-pop-mod__body_yes').click(function () {
    if (currPlot !== null) {
        currPlot.target.setEditable(false);
        currPlot.target.setDraggable(false);
        currPlot.name = $.trim($('#name').val());
        currPlot.croptype = $.trim($('#croptype').val());
        currPlot.square = $.trim($('#square').val());
        currPlot.videoUrl = $.trim($('#videoUrl').val());
        $(this).parents('.plot-form').addClass('hide');
        currPlot = null;
    }
});
$('.plot-form .mg-pop-mod__body_cancel').click(function () {
    $(this).parents('.plot-form').addClass('hide');
    currPlot.target.setEditable(true);
    currPlot.target.setDraggable(true);
});
// 确认修改
$('.alert-win .mg-pop-mod__body_yes').click(function () {
    $('.mg-plot-side').addClass('hide');
    $(this).parents('.alert-win').addClass('hide');
    currPlot.target.setEditable(false);
    currPlot.target.setDraggable(false);
    currPlot = null;
});
$('.alert-win .mg-pop-mod__body_cancel').click(function () {
    $(this).parents('.alert-win').addClass('hide');
});
// 侧边栏编辑地块覆盖层的颜色属性
// 边框颜色
$('#side-strokecolor').change(function () {
    currPlot.target.setOptions({
        strokeColor: $(this).val()
    });
});
// 边框不透明度
$("#side-strokeopacity").bind('input propertychange', function () {
    currPlot.target.setOptions({
        strokeOpacity: $(this).val() / 100
    });
});
// 地块填充颜色
$('#side-fillcolor').change(function () {
    currPlot.target.setOptions({
        fillColor: $(this).val()
    });
});
// 地块填充不透明度
$("#side-fillopacity").bind('input propertychange', function () {
    currPlot.target.setOptions({
        fillOpacity: $(this).val() / 100
    });
});

