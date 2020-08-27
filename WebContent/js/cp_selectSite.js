// 阻止浏览器中鼠标右键弹出下拉菜单
document.oncontextmenu = function (event) {
    event.preventDefault();
};

// 初始化地图
function initMap() {
    var map = new T.Map('bgMap', {
        minZoom: 4
    });
    return map;
}
//设置定位
function setLocation(lng,lat){
	map.centerAndZoom(new T.LngLat(lng, lat), 6);
    map.disableDoubleClickZoom();
    map.setMapType(TMAP_HYBRID_MAP);
}
//绘图
function drawSite_select(lnglats, map) {
    // 将经纬度字符串转化成数组：'...,...@...,...@...' => [[...,...],[...,...],...]
    let tmp = lnglats.split('@');
    let points = [];
    for (let i = 0; i < tmp.length; i++) {
        let lnglat = tmp[i].split(',');
        points.push(new T.LngLat(lnglat[0], lnglat[1]));
    }
    // 生成站点
    let polygon = new T.Polygon(points, {
        color: "#000000", weight: 3, opacity: 0.4, fillColor: "#FFFFFF", fillOpacity: 0.5
    });

    // 为站点多边形绑定鼠标事件
    let bound = polygon.getBounds();
    let centerpoint = bound.getCenter();
    polygon.centerpoint = centerpoint;
    bindMouseHoverEventToSite(polygon);

    // 添加图片标注
    polygon.marker = addMarker(centerpoint, map);
    // 设置文本框函数
    polygon.addLabelWindow = function (obj) {
        let html = ``;
        for (let key in obj) {
            html += `<p>${key}：${obj[key]}</p>`
        }
        let label = new T.Label({
            text: html,
            position: this.centerpoint,
            offset: new T.Point(-80, -90)
        });
        this.marker.addEventListener('mouseover', () => {
            map.addOverLay(label);
        }).addEventListener('mouseout', () => {
            map.removeOverLay(label);
        });
    }
    // 设置点击地块跳转事件
    polygon.bindLink = function (siteId) {
        this.marker.addEventListener('click', () => {
            showTheSite(siteId);
        });
    }
    map.addOverLay(polygon);
    return polygon;
}
// 为站点绑定鼠标选悬停事件
function bindMouseHoverEventToSite(polygon) {
    polygon.addEventListener('mouseover', (e) => {
        polygon.setWeight(5);
        polygon.setFillOpacity(0.6);
    }).addEventListener('mouseout', (e) => {
        polygon.setWeight(3);
        polygon.setFillOpacity(0.4);
    });
}
// 计算经纬度中间位置
function getCenterPoint(lnglats) {
    let tmp = lnglats.split('@');
    let lng = 0, lat = 0;
    for (var i = 0; i < tmp.length; i++) {
        let lnglat = tmp[i].split(',');
        lng += parseFloat(lnglat[0]);
        lat += parseFloat(lnglat[1]);
    }
    return {
        lng: lng / i,
        lat: lat / i
    }
}
//添加标注点
function addMarker(lnglat, map) {
    //创建图片对象
    let icon = new T.Icon({
        iconUrl: getURL()+"/img/marker.png",
        iconSize: new T.Point(20, 20),
        iconAnchor: new T.Point(20, 20)
    });
    //向地图上添加自定义标注
    let marker = new T.Marker(lnglat, { icon: icon });
    map.addOverLay(marker);
    return marker;
}