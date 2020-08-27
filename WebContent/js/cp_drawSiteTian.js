// 阻止浏览器中鼠标右键弹出下拉菜单
document.oncontextmenu = function (event) {
    event.preventDefault();
};
var marker;
var lngLats = [];//存储经纬度的数组
var polygonTool = null;//存储当前操作的地块的经纬度
var currentPolygon = null;

var map = new T.Map('bgMap', {
    minZoom: 4
});
function setLngLat(lng,lat){
	map.centerAndZoom(new T.LngLat(lng, lat), 16);
	marker = addMarker([lng,lat], map);
}
map.disableDoubleClickZoom();
map.setMapType(TMAP_HYBRID_MAP);


// 添加多边形工具
polygonTool = new T.PolygonTool(map, {
    color: "#009966", weight: 3, opacity: 1, fillColor: "#009966", fillOpacity: 0.5
});


// 绑定地图单击事件
map.addEventListener('contextmenu', function () {
    if (currentPolygon !== null) {
        if ($('.mg-plot-side').css('display') == 'block') {
            $('.alert-win').removeClass('hide');
        } else {
            $('.plot-form').removeClass('hide');
        }
    }
});
// 每个多边形绘制结束后触发
polygonTool.addEventListener("draw", function () {
    let lastPolygonIndex = polygonTool.getPolygons().length - 1;
    currentPolygon = polygonTool.getPolygons()[lastPolygonIndex];
    currentPolygon.enableEdit();
    currentPolygon.addEventListener('dblclick', function () {
        if (currentPolygon === null) {
            currentPolygon = this;
            if (!this.isEditable()) {
                currentPolygon.enableEdit();
                $('.buttons .addBlock').addClass('hide');
                $('#side-name').val(currentPolygon.name);
                $('#side-croptype').val(currentPolygon.croptype);
                $('#side-strokecolor').val(currentPolygon.getColor());
                $('#side-strokeopacity').val(currentPolygon.getOpacity() * 100);
                $('#side-fillcolor').val(currentPolygon.getFillColor());
                $('#side-fillopacity').val(currentPolygon.getFillOpacity() * 100);
                $('.mg-plot-side').slideDown('fast');
            }
        }
    });
});
//侧边栏按钮点击事件
$('.modify-plot').click(function() {
    if (currentPolygon !== null) {
        if ($('.mg-plot-side').css('display') == 'block') {
            $('.alert-win').removeClass('hide');
        } else {
            $('.plot-form').removeClass('hide');
        }
    }
});
$('.delete-plot').click(function() {
	 var placeId = currentPolygon.placeId;
	 delPlace(placeId);
});
$('.delete-plot-alert .mg-pop-mod__body_cancel').click(function() {
    $(this).parents('.delete-plot-alert').addClass('hide');
});
$('.mg-plot-side h3 span').click(function() {
    $('.mg-plot-side').slideUp('fast');
    currentPolygon.disableEdit();
    $('.buttons .addBlock').removeClass('hide');
    currentPolygon = null;
});

$('.buttons .addBlock').click(function () {
    polygonTool.open();
    $(this).addClass('hide');
    $(this).siblings('.cancel').removeClass('hide');
});
$('.buttons .cancel').click(function () {
    if (currentPolygon === null) {
        polygonTool.close();
    } else {
        map.removeOverLay(currentPolygon);
    }
    currentPolygon = null;
    $(this).addClass('hide');
    $(this).siblings('.addBlock').removeClass('hide');
});


// 提交地块信息按钮
 function bindSubmit(fun) {
	$('.plot-form .mg-pop-mod__body_yes').click(function () {
	    $(this).parents('.plot-form').addClass('hide');
	    fun();
	    currentPolygon.name = $('#name').val();
	    currentPolygon.croptype = $('#croptype').val();
	    currentPolygon.disableEdit();
	    $('.buttons .addBlock').removeClass('hide');
	    currentPolygon = null;
	});
 }
 //提交站点经纬度信息
 function submitLngLats(){
	var href = window.location.href;
	var siteId  = href.split("siteId=")[1];
	var str = getCurrentLngLats();
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"addSiteLocations",
		data : {
			siteId : siteId,
			lngLats : str
		},
		success : function(result) {
			if(result){
				alert("操作成功");
				skipPage('cloudranch/cp_manageSide.html#!/farmManagement');
			}else{
				alert("操作失败");
			}
		},
		error : function(e){
			
		}
	});
 }
 //提交地块经纬度信息
 function submitPlaceLngLats(){
	 var fillColor = currentPolygon.getFillColor();
	 var fillOpacity = currentPolygon.getFillOpacity();
	 var strokeColor = currentPolygon.getColor();
	 var strokeOpacity = currentPolygon.getOpacity();
	 var str = getCurrentLngLats();
		$.ajax({
			type : "POST",//方法类型
			async : true,
			dataType : "json",//预期服务器返回的数据类型
			url : getURL()+"addPlace",
			data : {
				placeName : $("#name").val(),
				square : $("#square").val(),
				siteId : sessionStorage.getItem("siteId"),
				vidoUrl : $("#videoUrl").val(),
				vidoName : $("#videoName").val(),
				crop : $("#croptype").val(),
				type : 'plant',
				account : '1',
				strokeColor : strokeColor,
				fillColor : fillColor,
				strokeOpacity : strokeOpacity,
				fillOpacity : fillOpacity,
				lngLats : str
			},
			success : function(result) {
				if(result.flag){
					$.ajax({
						type : "POST",//方法类型
						async : true,
						dataType : "json",//预期服务器返回的数据类型
						url : getURL()+"addPlaceLocations",
						data : {
							lngLats : str,
							placeId : result.placeId
						},
						success : function(result) {
							if(result){
								alert("添加成功");
								refreshPage();
							}else{
								alert("添加失败");
							}
						},
						error : function(e){
							
						}
					});
				}else{
					alert("添加失败");
				}
			},
			error : function(e){
				
			}
		});
	 }
//修改地块样式
 function modifiPlaceStyle(){
	 var placeId = currentPolygon.placeId;
	 if(placeId == -1){
		 alert("站点修改未开放！");
		 return;
	 }
 	 var fillColor = currentPolygon.getFillColor();
 	 var fillOpacity = currentPolygon.getFillOpacity();
 	 var strokeColor = currentPolygon.getColor();
 	 var strokeOpacity = currentPolygon.getOpacity();
 	$.ajax({
 		type : "POST",//方法类型
 		async : true,
 		dataType : "json",//预期服务器返回的数据类型
 		url : getURL()+"modifiPlaceStyle",
 		data : {
 			placeId : placeId,
 			strokeColor : strokeColor,
 			fillColor : fillColor,
 			strokeOpacity : strokeOpacity,
 			fillOpacity : fillOpacity
 		},
 		success : function(result) {
 			if(result){
 				alert("修改成功");
 			}else{
 				alert("修改失败");
 			}
 			refreshPage();
 		},
 		error : function(e){
 			
 		}
 	});
 }
 bindSubmit(submitLngLats);
$('.plot-form .mg-pop-mod__body_cancel').click(function () {
    $(this).parents('.plot-form').addClass('hide');
    currentPolygon.enableEdit();
});
// 确认修改
// function bindOk(fun) {
$('.alert-win .mg-pop-mod__body_yes').click(function () {
    $('.mg-plot-side').slideUp('fast');
    $(this).parents('.alert-win').addClass('hide');
    // fun();
    currentPolygon.disableEdit();
    $('.buttons .addBlock').removeClass('hide');
    currentPolygon = null;
});
// }

$('.alert-win .mg-pop-mod__body_cancel').click(function () {
    $(this).parents('.alert-win').addClass('hide');
});

// 侧边栏编辑地块覆盖层的颜色属性
// 边框颜色
$('#side-strokecolor').change(function () {
    currentPolygon.setColor($(this).val());
});
// 边框不透明度
$("#side-strokeopacity").bind('input propertychange', function () {
    currentPolygon.setOpacity($(this).val() / 100);
});
// 地块填充颜色
$('#side-fillcolor').change(function () {
    currentPolygon.setFillColor($(this).val());
});
// 地块填充不透明度
$("#side-fillopacity").bind('input propertychange', function () {
    currentPolygon.setFillOpacity($(this).val() / 100);
});
//画站点边界
function drawSiteBorder(){
	$.ajax({
		type : "POST",//方法类型
		async : false,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"querySiteLocations",
		data : {
			siteId : sessionStorage.getItem("siteId")
		},
		success : function(result) {
			var lngLats = result.lngLats;
			getBlockInf(lngLats, map,null);
			drawPlaceBorder();
		},
		error : function(e){
			
		}
	});
}
//画地块边界
function drawPlaceBorder(){
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"queryPlaces",
		data : {
			siteId : sessionStorage.getItem("siteId"),
			placeId : -1,
			pageNumber : -1,
			limit : -1,
			type : ""
		},
		success : function(result) {
			var places = result.places;
			var placeIds = "";
			for(var i=0;i<places.length;i++){
				placeIds += places[i].placeId + ",";
			}
			placeIds = placeIds.substring(0,placeIds.length-1)
			$.ajax({
				type : "POST",//方法类型
				async : true,
				dataType : "json",//预期服务器返回的数据类型
				url : getURL()+"queryPlaceLocations",
				data : {
					placeIds : placeIds
				},
				success : function(result) {
					var lngLats = result.lngLats;
					var pls = lngLats.split("#");
					for(var i=0;i<pls.length;i++){
						getBlockInf(pls[i], map,places[i]);
					}
				},
				error : function(e){
					
				}
			});
		},
		error : function(e){
			
		}
	});
}
//画点
function getBlockInf(lnglats, map,place) {
	var strokeColor = "";
	var strokeOpacity = 0;
	var fillColor = "";
	var fillOpacity = 0;
	if(place == null){
		strokeColor = "#009966";
		strokeOpacity = 1;
		fillColor = "#009966";
		fillOpacity = 0.5;
	}else{
		strokeColor = place.strokeColor;
		strokeOpacity = place.strokeOpacity;
		fillColor = place.fillColor;
		fillOpacity = place.fillOpacity;
	}
    let tmp = lnglats.split('@');
    let points = [];
    for (let i = 0; i < tmp.length; i++) {
        let lnglat = tmp[i].split(',');
        points.push(new T.LngLat(lnglat[0], lnglat[1]));
    }
    let polygon = new T.Polygon(points, {
        color: strokeColor, weight: 3, opacity: strokeOpacity, fillColor: fillColor, fillOpacity: fillOpacity
    });
    if(place == null){
    	polygon.placeId = -1;
    	var lngLat = polygon.getBounds().getCenter();
    	marker.setLngLat(lngLat);
    }else{
    	var lngLat = polygon.getBounds().getCenter();
    	var markerPlace = addMarkerPlace(lngLat, map, place.type);
    	markerPlace.setLngLat(lngLat);
    	polygon.placeId = place.placeId;
    	polygon.name = place.placeName;
    	polygon.croptype = place.crop;
    }
    if(place == null){
    	addEvent(polygon);
    }else if(place.type == 'plant'){
    	addEvent(polygon);
    }
    map.addOverLay(polygon);
}
//绑定事件
function addEvent(polygon) {
    polygon.addEventListener('dblclick', function () {
        if (currentPolygon === null) {
            currentPolygon = this;
            if (!this.isEditable()) {
                currentPolygon.enableEdit();
                $('.buttons .addBlock').addClass('hide');
                $('#side-name').val(currentPolygon.name);
                $('#side-croptype').val(currentPolygon.croptype);
                $('#side-strokecolor').val(currentPolygon.getColor());
                $('#side-strokeopacity').val(currentPolygon.getOpacity() * 100);
                $('#side-fillcolor').val(currentPolygon.getFillColor());
                $('#side-fillopacity').val(currentPolygon.getFillOpacity() * 100);
                $('.mg-plot-side').slideDown('fast');
            }
        }
    });
}

//添加标注点
function addMarker(lnglat, map) {
    //创建图片对象
    let icon = new T.Icon({
        iconUrl: getURL()+"img/marker.png",
        iconSize: new T.Point(20, 20),
        iconAnchor: new T.Point(20, 20)
    });
    //向地图上添加自定义标注
    let marker = new T.Marker(new T.LngLat(lnglat[0], lnglat[1]), { icon: icon });
    map.addOverLay(marker);
    return marker;
}

//地块添加标注点
function addMarkerPlace(lnglat, map, type) {
    //创建图片对象
    let icon = new T.Icon({
        iconUrl: getURL()+"img/"+type+".png",
        iconSize: new T.Point(20, 20),
        iconAnchor: new T.Point(20, 20)
    });
    //向地图上添加自定义标注
    let marker = new T.Marker(new T.LngLat(lnglat[0], lnglat[1]), { icon: icon });
    map.addOverLay(marker);
    return marker;
}
//获取当前地块经纬度
function getCurrentLngLats() {
    let lnglatArray = currentPolygon.getLngLats();
    let lnglatStr = '';
    for (let i = 0; i < lnglatArray[0].length; i++) {
        lnglatStr += `${lnglatArray[0][i].getLng()},${lnglatArray[0][i].getLat()}@`;
    }
    return lnglatStr = lnglatStr.substring(0, lnglatStr.length - 1);
}
