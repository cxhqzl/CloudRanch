var map;
function initMap() {
    // 初始化地图
    map = new google.maps.Map(document.getElementById('bgMap'), {
        center: {
            lat: 31.864,
            lng: 117.253
        },
        zoom: 18,
        disableDefaultUI: true,
        mapTypeId: 'satellite',
        gestureHandling: 'greedy',
        minZoom: 4
    });

    var points = [];
    var arrLength = 0;
    // 绘制折线
    var polyline = new google.maps.Polyline({
        paths: points,
        geodesic: true,
        strokeColor: '#FF0000',
        strokeOpacity: 1.0,
        strokeWeight: 2,
        editable: true
    });
    polyline.setMap(map);
    map.addListener('click', function (event) {
        let path = polyline.getPath();
        let length = path.getLength();
        path.push(event.latLng);
        // arrLength = path.getLength();
        console.log(path);
    });
    map.addListener('rightclick', function (event) {
        polyline.setEditable(false);
    });
    // 绘制多边形

    // var polygon = new google.maps.Polygon({
    //     path: points,
    //     strokeColor: '#FF0000',
    //     strokeOpacity: 0.8,
    //     strokeWeight: 2,
    //     fillColor: '#FF0000',
    //     fillOpacity: 0.35
    // });
    // polygon.setMap(map);
    // 绑定事件
    // map.addListener('click', function (event) {
    //     console.log(event.latLng.lat() + '-' + event.latLng.lng());
    //     let pathArr = polygon.getPath();
    //     pathArr.push({
    //         lat: event.latLng.lat(),
    //         lng: event.latLng.lng()
    //     });
    //     console.log(pathArr);
    //     arrLength = pathArr.getLength();
    //     console.log(arrLength);
    //     polygon.setMap(map);
    // });
    // map.addListener('rightclick', function (event) {
    //     if (arrLength > 0) {
    //         points.pop();
    //         arrLength = 0;
    //         polyline.setPath(points);
    //         polyline.setMap(map);
    //         console.log(points);
    //     }
    // });
    // map.addListener('mousemove', function (event) {
    //     if (arrLength > 0) {
    //         polyline.setMap(null);
    //         points[arrLength] = {
    //             lat: event.latLng.lat(),
    //             lng: event.latLng.lng()
    //         }
    //         polyline.setPath(points);
    //         polyline.setMap(map);
    //         // console.log(arrLength);
    //     }
    // });
}
