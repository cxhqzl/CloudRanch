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
function setLocation1(lng, lat){
	map.centerAndZoom(new T.LngLat(lng, lat), 16);
    map.disableDoubleClickZoom();
    map.setMapType(TMAP_HYBRID_MAP);

    slideUpClaimBlock(map);
}
function slideUpClaimBlock(map) {
    map.addEventListener('click', function (e) {
        // let state = $('.claimblock').css('display');
        // console.log(state);
        // if(state === none){
            $('.claimblock').slideUp('fast');
        // }
    });
}
//绘图
function drawSite_select(lnglats, map,color) {
    // 将经纬度字符串转化成数组：'...,...@...,...@...' => [[...,...],[...,...],...]
    let tmp = lnglats.split('@');
    let points = [];
    for (let i = 0; i < tmp.length; i++) {
        let lnglat = tmp[i].split(',');
        points.push(new T.LngLat(lnglat[0], lnglat[1]));
    }
    // 生成站点
    let polygon = new T.Polygon(points, {
        color: "#000000", weight: 3, opacity: 0.4, fillColor: color, fillOpacity: 0.5
    });

    // 为站点多边形绑定鼠标事件
    let bound = polygon.getBounds();
    let centerpoint = bound.getCenter();
    polygon.centerpoint = centerpoint;
    bindMouseHoverEventToSite(polygon);

    // 添加图片标注
    polygon.marker = addMarker(centerpoint, map,'marker');
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
    // 设置点击认领地块
    polygon.bindEvent = function () {
        this.addEventListener('mouseover', function (e) {
            $('.claimblock').slideUp('fast');
            $('.claimblock').slideDown('fast');
        });
    }

    map.addOverLay(polygon);
    return polygon;
}
//绘图
function drawPlace_select(lnglats, map,place) {
    // 将经纬度字符串转化成数组：'...,...@...,...@...' => [[...,...],[...,...],...]
    let tmp = lnglats.split('@');
    let points = [];
    for (let i = 0; i < tmp.length; i++) {
        let lnglat = tmp[i].split(',');
        points.push(new T.LngLat(lnglat[0], lnglat[1]));
    }
    // 生成站点
    let polygon = new T.Polygon(points, {
    	color: place.strokeColor, weight: 3, opacity: place.strokeOpacity, fillColor: place.fillColor, fillOpacity: place.fillOpacity
    });
    // 为站点多边形绑定鼠标事件
    let bound = polygon.getBounds();
    let centerpoint = bound.getCenter();
    polygon.centerpoint = centerpoint;
    bindMouseHoverEventToSite(polygon);
    
    polygon.marker = addMarker(centerpoint, map,place.type);
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
    // 设置点击认领地块
    polygon.bindEvent = function (place) {
        this.addEventListener('mouseover', function (e) {
            //$('.claimblock').slideUp('fast');
            $('.claimblock').slideDown('fast');
            document.getElementById("placeName").innerHTML = place.placeName;
            document.getElementById("placeSquare").innerHTML = place.square +"㎡";
            document.getElementById("placeCrop").innerHTML = place.crop;
            var str = "";
            if(place.account == "1"){
            	str = "<button class='' onclick='placeToAccount(\""+place.placeId+"\")'>认领</button>";
            }else{
            	if(place.account == sessionStorage.getItem("account")){
            		$.ajax({
            			type : "POST",//方法类型
            			async : false,
            			dataType : "json",//预期服务器返回的数据类型
            			url : getURL()+"queryPlants",
            			data : {
            				createDate : "",
            				cropId : "",
            				placeId : place.placeId,
            				limit : -1,
            				pageNumber : -1
            			},
            			success : function(result) {
            				str += `<div class="source">`;
            				var plants = result.plants;
            				var imgUrl1 = "";
        					if(plants[0].image == ""){
        						imgUrl1 = getURL() + "img/noImage.jpg";
        					}else{
        						imgUrl1 = getImgURL() + "plantImage/" + plants[0].image;
        					}
            				str += `
           					 <div class="source__title">种植信息</div>
					            <div class="source__items">
					                <div class="source__item">
					                    <div>${plants[0].createDate}</div>
					                    <ul>
					                        <li>${plants[0].cropName}</li>
					                        <li>
					                            	施肥图片：
					                            <img src="${imgUrl1}" draggable="false" alt="种植信息" />
					                        </li>
					                    </ul>
					                </div>
					            </div>
           					`;
            				var cropId = plants[0].cropId;
            				$.ajax({
                    			type : "POST",//方法类型
                    			async : false,
                    			dataType : "json",//预期服务器返回的数据类型
                    			url : getURL()+"queryOriginDatas",
                    			data : {
                    				cropId : cropId
                    			},
                    			success : function(result) {
                    				
                    				var spreadMan = result.spreadMan;
                    				if(spreadMan != null){
                    					var imgUrl = "";
                    					if(spreadMan.image == ""){
                    						imgUrl = getURL() + "img/noImage.jpg";
                    					}else{
                    						imgUrl = getImgURL() + "spreadManImage/" + spreadMan.image;
                    					}
                    					str += `
                    					 <div class="source__title">施肥信息</div>
							            <div class="source__items">
							                <div class="source__item">
							                    <div>${spreadMan.createDate}</div>
							                    <ul>
							                        <li>${spreadMan.remarks}</li>
							                        <li>
							                            	施肥图片：
							                            <img src="${imgUrl}" draggable="false" alt="种植信息" />
							                        </li>
							                    </ul>
							                </div>
							            </div>
                    					`;
                    				}
                    				var irrigate = result.irrigate;
                    				if(irrigate != null){
                    					var imgUrl = "";
                    					if(irrigate.image == ""){
                    						imgUrl = getURL() + "img/noImage.jpg";
                    					}else{
                    						imgUrl = getImgURL() + "irrigateImage/" + irrigate.image;
                    					}
                    					str += `
                    					 <div class="source__title">灌溉信息</div>
							            <div class="source__items">
							                <div class="source__item">
							                    <div>${irrigate.createDate}</div>
							                    <ul>
							                        <li>${irrigate.remarks}</li>
							                        <li>
							                            	施肥图片：
							                            <img src="${imgUrl}" draggable="false" alt="种植信息" />
							                        </li>
							                    </ul>
							                </div>
							            </div>
                    					`;
                    				}
                    				var pesticide = result.pesticide;
                    				if(spreadMan != null){
                    					var imgUrl = "";
                    					if(pesticide.image == ""){
                    						imgUrl = getURL() + "img/noImage.jpg";
                    					}else{
                    						imgUrl = getImgURL() + "pesticideImage/" + pesticide.image;
                    					}
                    					str += `
                    					 <div class="source__title">农药信息</div>
							            <div class="source__items">
							                <div class="source__item">
							                    <div>${pesticide.createDate}</div>
							                    <ul>
							                        <li>${pesticide.remarks}</li>
							                        <li>
							                            	施肥图片：
							                            <img src="${imgUrl}" draggable="false" alt="种植信息" />
							                        </li>
							                    </ul>
							                </div>
							            </div>
                    					`;
                    				}
                    				var wog = result.wog;
                    				if(wog != null){
                    					var imgUrl = "";
                    					if(wog.image == ""){
                    						imgUrl = getURL() + "img/noImage.jpg";
                    					}else{
                    						imgUrl = getImgURL() + "wogImage/" + wog.image;
                    					}
                    					str += `
                    					 <div class="source__title">病虫害信息</div>
							            <div class="source__items">
							                <div class="source__item">
							                    <div>${wog.createDate}</div>
							                    <ul>
							                        <li>${wog.remarks}</li>
							                        <li>
							                            	施肥图片：
							                            <img src="${imgUrl}" draggable="false" alt="种植信息" />
							                        </li>
							                    </ul>
							                </div>
							            </div>
                    					`;
                    				}
                    				var grow = result.grow;
                    				if(grow != null){
                    					var imgUrl = "";
                    					if(grow.image == ""){
                    						imgUrl = getURL() + "img/noImage.jpg";
                    					}else{
                    						imgUrl = getImgURL() + "growImage/" + grow.image;
                    					}
                    					str += `
                    					 <div class="source__title">长势信息</div>
							            <div class="source__items">
							                <div class="source__item">
							                    <div>${grow.createDate}</div>
							                    <ul>
							                        <li>${grow.remarks}</li>
							                        <li>
							                            	施肥图片：
							                            <img src="${imgUrl}" draggable="false" alt="种植信息" />
							                        </li>
							                    </ul>
							                </div>
							            </div>
                    					`;
                    				}
                    				var harvest = result.harvest;
                    				if(harvest != null){
                    					var imgUrl = "";
                    					if(harvest.image == ""){
                    						imgUrl = getURL() + "img/noImage.jpg";
                    					}else{
                    						imgUrl = getImgURL() + "harvestImage/" + harvest.image;
                    					}
                    					str += `
                    					 <div class="source__title">长势信息</div>
							            <div class="source__items">
							                <div class="source__item">
							                    <div>${harvest.createDate}</div>
							                    <ul>
							                        <li>${harvest.remarks}</li>
							                        <li>
							                            	施肥图片：
							                            <img src="${imgUrl}" draggable="false" alt="种植信息" />
							                        </li>
							                    </ul>
							                </div>
							            </div>
                    					`;
                    				}
                    				str += "</div>";
                    				
                    			},
                    			error : function(e){
                    				alert("error");
                    			}
                    		});
            			},
            			error : function(e){
            				alert("error");
            			}
            		});
            		/*str = `
            		<div class="source">
			            <div class="source__title">种植信息</div>
			            <div class="source__items">
			                <div class="source__item">
			                    <div>2019-03-07</div>
			                    <ul>
			                        <li>施肥施肥施肥施肥施肥施肥施肥施肥施肥施肥施肥施肥</li>
			                        <li>施肥施肥施肥施肥施肥施肥</li>
			                        <li>施肥施肥施肥施肥施肥等等发放个人施肥</li>
			                        <li>
			                            	种植图片：
			                            <img src="../img/bannerPic-1.jpg" draggable="false" alt="种植信息" />
			                        </li>
			                    </ul>
			                </div>
			            </div>
			        </div>
            		`;*/
            	}else{
            		str = "<p class='tip'>此地块已经被认领！</p>";
            	}
            }
            document.getElementById("operateButton").innerHTML = str;
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
function addMarker(lnglat, map, type) {
    //创建图片对象
    let icon = new T.Icon({
        iconUrl: getURL()+"img/"+type+".png",
        iconSize: new T.Point(20, 20),
        iconAnchor: new T.Point(20, 20)
    });
    //向地图上添加自定义标注
    let marker = new T.Marker(lnglat, { icon: icon });
    map.addOverLay(marker);
    return marker;
}

// 底部滑块效果
$('.farm-info__body_nav>li').click(function () {
    let num = $(this).data('num');
    let left = num * 25 + '%';
    $('.active-bar').css('left', left);
    $('.slidepage:nth-child(' + (num + 1) + ')').removeClass('hide').siblings().addClass('hide');
});
$('.claim-block>i').click(function () {
    $('.claim-block__form').toggleClass('claim-block__form_hide');
});
$('.farm-info__body_nav_page_4>span').click(function () {
    if ($('.claim-block__form').hasClass('claim-block__form_hide')) {
        $('.claim-block__form').removeClass('claim-block__form_hide');
    }
});
$('.claim-block__form_body>.row>div>div').click(function () {
    $(this).addClass('selected').siblings().removeClass('selected');
});
// 图表动画
$('.farm-info__body_nav_page_1 li').mouseenter(function () {
    $(this).children('section').addClass('show');
}).mouseleave(function () {
    $(this).children('section').removeClass('show');
});
// 侧边栏收缩
$('.farm-info__title > span').click(function () {
    $('.farm-info__wrapper').slideToggle('fast');
    $('.farm-info__title > span').toggleClass('slideUp');
});
(function () {
    $('.farm-info__wrapper').slideDown('fast');
    $('.farm-info__title > span').toggleClass('slideUp');
})();