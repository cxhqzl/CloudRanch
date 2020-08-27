var createExistentSite = function(plotArray) {
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
var createSite = function(pathArray) {
    let polyline = {
        target: new google.maps.Polyline({
            path: pathArray || new google.maps.MVCArray(),
            strokeColor: '#FF0000',
            strokeOpacity: 0.8,
            strokeWeight: 5,
            editable: true,
            draggable: true
        })
    }
    polyline.target.setMap(map);
    return polyline;
}