// 阻止浏览器中鼠标右键弹出下拉菜单
document.oncontextmenu = function (event) {
    event.preventDefault();
};

var map;
var polyline = null;//当前操作的站点
var _polyline = null;
// 渲染已经存在的站点
function createExistentSite(plotArray) {
    // new google.maps.LatLng(-34, 151)
    for (let i = 0; i < plotArray.length; i++) {
        var latlngs = plotArray[i].latLng;
        let pathArray = new google.maps.MVCArray();
        for (let j = 0; j < latlngs.length; j++) {
            pathArray.push(new google.maps.LatLng(latlngs[j].lat, latlngs[j].lng));
        }
        let plot = createSite(pathArray);
        plot.target.setDraggable(false);
        plot.target.setEditable(false);
    }
}
// 返回经纬度字符串数组
function getSiteLatLngArray() {
    let latLngArray = '';
    polyline.target.getPath().forEach(function (item) {
    	latLngArray += item.lat() + ',' + item.lng() + "@";
        // console.log(item.lat() + ',' + item.lng());
    });
    return latLngArray.substring(0,latLngArray.length-1);
}
// 创建站点函数
function createSite(pathArray) {
    polyline = {
        target: new google.maps.Polyline({
            path: pathArray || new google.maps.MVCArray(),
            strokeColor: '#FF0000',
            strokeOpacity: 0.8,
            strokeWeight: 5,
            editable: true,
            draggable: true
        })
    }
    // 绑定地块双击事件
    polyline.target.addListener('dblclick', function (event) {
        event.stop();
        if (this.getEditable() === false) {
            this.setEditable(true);
            this.setDraggable(true);
            polyline = _polyline;
        }
    });
    polyline.target.setMap(map);
    return polyline;
}
// 初始化函数
function initMap(lat, lng) {
    // 初始化地图
    map = new google.maps.Map(document.getElementById('bgMap'), {
        center: {
            lat: lat,
            lng: lng
        },
        zoom: 17,
        disableDefaultUI: true,
        mapTypeId: 'satellite',
        gestureHandling: 'greedy',
        minZoom: 4
    });
    map.addListener('click', function (event) {
        if (_polyline === null && polyline === null) {
            polyline = createSite();
        } else if (_polyline !== null && polyline === null){
            alert('只可创建一个地块！')
        }
        polyline.target.getPath().push(event.latLng);
    });
    map.addListener('rightclick', function (event) {
        if (polyline !== null) {
            if (polyline.target.getPath().getLength() === 1) {
                polyline.target.getPath().clear();
                polyline = null;
            } else if (polyline.target.getPath().getAt(0) === polyline.target.getPath().getAt(polyline.target.getPath().getLength() - 1)) {
                $('.mg-pop-mod').removeClass('hide');
            } else {
                let start = polyline.target.getPath().getAt(0);
                polyline.target.getPath().push(start);
            }
        }
    });
    codeAddress('安徽省合肥市蜀山区长江西路130号');
}
// address=>latlng
function codeAddress(address) {
    geocoder = new google.maps.Geocoder();
    geocoder.geocode({ 'address': address }, function (results, status) {
        if (status == 'OK') {
            // map.setCenter(results[0].geometry.location);
            console.log(results[0].geometry.location.lat() + '-' + results[0].geometry.location.lng());
        } else {
            alert('地址解析错误');
        }
    });
}
// 确认修改
$('.alert-win .mg-pop-mod__body_yes').click(function () {
    $('.mg-plot-side').addClass('hide');
    $(this).parents('.alert-win').addClass('hide');
    polyline.target.setEditable(false);
    polyline.target.setDraggable(false);
    _polyline = polyline;
    polyline = null;
});
$('.alert-win .mg-pop-mod__body_cancel').click(function () {
    $(this).parents('.alert-win').addClass('hide');
});


