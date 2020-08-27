setLoginState();

//判断用户处于登录状态还是非登录状态
function setLoginState(){
	var account = sessionStorage.getItem("account");
	var Log_true = $('#login_true');
	var Log_false = $('#login_false');
	if(account == null || account == ""){//非登录状态
		Log_false.removeClass('hide');
		Log_true.addClass('hide');
	}else{//登录状态
		setUserData();
		Log_true.removeClass('hide');
		Log_false.addClass('hide');
	}
}
//跳转后台页面
function toPageForRole(){
	var role = sessionStorage.getItem("role");
	if(role == 0){
		skipPage('cloudranch/cp_manageSide.html#!/farmManagement');
	}else if(role == 1){
		skipPage('cloudranch/cp_farmerManagementSide.html#!/essentialInformation');
	}else if(role == 3){
		//skipPage('cloudranch/cp_manageSide.html#!/merchantManagement');
	}else{
		skipPage('login.html');
	}
}
//设置登录资料
function setUserData(){
	document.getElementById("user_image").src = getImgURL()+"userImage/"+sessionStorage.getItem("image");
	document.getElementById("user_image_1").src = getImgURL()+"userImage/"+sessionStorage.getItem("image");
	document.getElementById("my_userName").innerHTML = sessionStorage.getItem("userName");
	document.getElementById("my_email").innerHTML = "账号：" + sessionStorage.getItem("account");
}

//设置个人资料
function setPersonData(){
	document.getElementById("person_image").src = getImgURL()+"userImage/"+sessionStorage.getItem("image");
	document.getElementById("person_userName").innerHTML = sessionStorage.getItem("userName");
	var address = sessionStorage.getItem("address");
	document.getElementById("person_my_infos").innerHTML = sessionStorage.getItem("sex") + " " + address.split("-")[0]+address.split("-")[1];
	
	document.getElementById("person_image_1").src = getImgURL()+"userImage/"+sessionStorage.getItem("image");
	$("#userName").val(sessionStorage.getItem("userName"));
	document.getElementById("showSex").innerHTML = sessionStorage.getItem("sex");
	document.getElementById("test_1").innerHTML = address.split("-")[0];
	$("#province").data('province',address.split("-")[0]);
	document.getElementById("showCity").innerHTML = address.split("-")[1];
	$("#city").data('city',address.split("-")[1]);
	document.getElementById("showCounty").innerHTML = address.split("-")[2];
	$("#county").data('district',address.split("-")[2]);
	$("#location").val(address.split("-")[3]);
}

//保存URL前缀，首页加载时调用
function setURL(url){
	if(url == ""){
		var href = window.location.href;
		var str = href.substring(0,href.lastIndexOf("/"));
		href = href.substring(0,href.lastIndexOf("/")+1);
		sessionStorage.setItem("URL",href);
		sessionStorage.setItem("imgURL",str.substring(0,str.lastIndexOf("/")+1)+"image/CloudRanchFileStorage/");
	}else{
		sessionStorage.setItem("URL",url);
		url = url.substring(0,url.length-1);
		sessionStorage.setItem("imgURL",url.substring(0,url.lastIndexOf("/")+1)+"image/CloudRanchFileStorage/");
	}
}
//获取URL前缀
function getURL(){
	return sessionStorage.getItem("URL");
}
//获取图片服务器URL前缀
function getImgURL(){
	return sessionStorage.getItem("imgURL");
}
//跳转页面
function skipPage(pageName){
	parent.window.location.href=getURL()+pageName;
}
//提交登录
function submitLogin(account,password){
	var account = $("#account").val();
	var password = $("#password").val();
	var code = $("#code").val();
	
	if(account == null || account == ""){
		alert("邮箱不能为空！");
		return;
	}
	if(password == null || password == ""){
		alert("密码不能为空！");
		return;
	}
	if(code == null || code == ""){
		alert("验证码不能为空！");
		return;
	}
	if(code != sessionStorage.getItem("code")){
		alert("验证码错误！");
		getImageCode();
		return;
	}
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"Login",
		data : {
			account : account,
			password : password
			
		},
		success : function(result) {
			if(result.type == 0){
				sessionStorage.setItem("role",result.role);
				$.ajax({
					type : "POST",//方法类型
					async : true,
					dataType : "json",//预期服务器返回的数据类型
					url : getURL()+"getAccountData",
					data : {
						account : account
					},
					success : function(result) {
						var accountInfo = result.accountInfo;
						sessionStorage.setItem("account",accountInfo.account);
						sessionStorage.setItem("userName",accountInfo.userName);
						sessionStorage.setItem("image",accountInfo.image);
						sessionStorage.setItem("sex",accountInfo.sex);
						sessionStorage.setItem("age",accountInfo.age);
						sessionStorage.setItem("address",accountInfo.province+"-"+accountInfo.city+"-"+accountInfo.county+"-"+accountInfo.location);
						sessionStorage.removeItem('code');
						var role = sessionStorage.getItem("role");
						if(role == 0){//管理员登录
							skipPage('cloudranch/cp_manageSide.html#!/merchantManagement');
						}
						if(role == 1){//农场主登录
							$.ajax({
								type : "POST",//方法类型
								async : false,
								dataType : "json",//预期服务器返回的数据类型
								url : getURL()+"famerGetSites",
								data : {
									account : sessionStorage.getItem("account")
								},
								success : function(result) {
									var sites = result.sites;
									sessionStorage.setItem("siteId",sites[0].siteId);
								},
								error : function(e){
									
								}
							});
							skipPage('cloudranch/cp_farmerManagementSide.html#!/essentialInformation');
						}
						if(role == 2){//普通用户登录
							var waitURL = sessionStorage.getItem("waitURL");
							if(waitURL == null || waitURL == ""){
								refreshPage();
								return;
							}
							sessionStorage.removeItem("waitURL");
							window.location.href=getURL()+waitURL;
						}
					},
					error : function(e){
						
					}
				});
			}else if(result.type == 2){
				alert("密码错误！");
				return;
			}else if(result.type == 1){
				alert("账号输入有误！");
				return;
			}
		},
		error : function(e){
			alert(111);
		}
	});
}
//登出
function logout(){
	var isRes = confirm("确认退出系统？"); 
	if(!isRes){
		return;
	}
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"logout",
		success : function(result) {
			if(result){
				var url = getURL();
				sessionStorage.clear();
				parent.window.location.href = url + "index.html";
			}
		},
		error : function(e){
			alert("error");
		}
	});
}
//注册按钮
function submitRegister(){
	var account = $("#account").val();
	var userName = $("#userName").val();
	var password = $("#password").val();
	var is_password = $("#is_password").val();
	var province = $("#province").val();
	var city = $("#city").val();
	var county = $("#county").val();
	var location = $("#location").val();
	var email_code = $("#email_code").val();
	if(account == null || account == ""){
		alert("邮箱不能为空！");
		return;
	}
	if(userName == null || userName == ""){
		alert("用户名不能为空！");
		return;
	}
	if(password == null || password == ""){
		alert("密码不能为空！");
		return;
	}
	if(is_password == null || is_password == ""){
		alert("确认密码不能为空！");
		return;
	}
	if(is_password != password){
		alert("密码输入不一致！");
		return;
	}
	if(province == null || province == ""){
		alert("请选择省份！");
		return;
	}
	if(city == null || city == ""){
		alert("请选择城市！");
		return;
	}
	if(county == null || county == ""){
		alert("请选择区县！");
		return;
	}
	if(location == null || location == ""){
		alert("详细地址不能为空！");
		return;
	}
	if(email_code == null || email_code == ""){
		alert("邮箱验证码不能为空！");
		return;
	}
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"register",
		data : {
			account : account,
			password : password,
			userName : userName,
			image : "",
			sex : '男',
			age : 20,
			province : province,
			city : city,
			county : county,
			location : location,
			phone : '',
			code : email_code
			
		},
		success : function(result) {
			if(result.type == 1){
				alert("邮箱验证码错误！");
			}else if(result.type == 3){
				alert("邮箱已被注册！");
			}else if(result.type == 2){
				alert("注册失败，请重试！");
			}else if(result.type == 0){
				alert("注册成功！");
				refreshPage();
			}
		},
		error : function(e){
			
		}
	});
}
//获取注册验证码
function getRegisterCode(){
	var email = $("#account").val();
	if(email == null || email == ""){
		alert("邮箱不能为空！");
		return;
	}
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"getRegisterCode",
		data : {
			email : email
			
		},
		success : function(result) {
			if(result.type == 1){
				alert("账号已被注册！");
				return;
			}
			if(result.flag){
				alert("验证码获取成功！");
			}else{
				alert("获取验证码失败，请重试！");
			}
		},
		error : function(e){
			alert("获取验证码失败，请重试！");
		}
	});
}
//重新加载页面
function refreshPage(){
	parent.window.parent.location.reload();
}

//***********start index JS ************
//搜索按钮响应
function submitSearch(){
	var keys = $("#keys").val();
	if(keys == null || keys == ""){
		alert("搜索内容不能为空！");
		return;
	}
	showSearch(keys);
}
//右侧显示一个随机站点信息
function showOneSite(){
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"getOneSite",
		success : function(result) {
			var site = result.site;
			document.getElementById('one_siteName').innerHTML = site.siteName;
			document.getElementById('one_square').innerHTML = site.square+"亩";
			document.getElementById('one_remarks').innerHTML = site.remarks;
			if(site.image == 'error'){
				document.getElementById('one_image').src = getURL()+"img/noImage.jpg";
			}else{
				document.getElementById('one_image').src = getImgURL()+"siteImage/"+site.image;
			}
		},
		error : function(e){
			
		}
	});
}
//显示匹配结果
function showMates(){
	var mateKey = $("#searchKeys").val();
	if(mateKey == ""){
		return;
	}
	var res = "";
	$.ajax({
		type : "POST",//方法类型
		async : false,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"searchSites",
		data : {
			keys : mateKey
		},
		success : function(result) {
			var sites = result.sites;
			var str = "";
			var str1 = "";
			for(var i=0;i<sites.length;i++){
				str += sites[i].siteName + ",";
				str1 += sites[i].siteId + ",";
			}
			res = str + "@" + str1;
		},
		error : function(e){
			
		}
	});
	return res;
}
//*********end index JS*********

//*********start searchResult JS ****************
//显示搜索结果
function showSearch(keys){
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"searchSites",
		data : {
			keys : keys
		},
		success : function(result) {
			var sites = result.sites;
			var str = "";
			for(var i=0;i<sites.length;i++){
				var imageURL = "";
				if(sites[i].image == ''){
					imageURL = getURL() + "img/noImage.jpg";
				}else{
					imageURL = getImgURL() + "siteImage/" + sites[i].image;
				}
				var crops = "";
				if(sites[i].crops == ""){
					crops = "无";
				}else{
					crops = sites[i].crops;
				}
				str += `
				<li onclick="siteDetail('${sites[i].siteId}')">
                    <a>
                        <div class="results__item_info">
                            <p class="results__item_name">${sites[i].siteName}</p>
                            <p class="results__item_pro">种植：${crops}</p>
                            <p class="results__item_address">`+sites[i].province+sites[i].city+sites[i].county+sites[i].location+`</p>
                        </div>
                        <div class="results__item_img">
                            <img src="${imageURL}" draggable="false" />
                        </div>
                    </a>
                </li>
				`;
			}
			document.getElementById('searchSites').innerHTML = str;
			str = "共搜到<span>"+sites.length+"</span>条结果";
			document.getElementById('results').innerHTML = str;
			refreshMap(result.data);
		},
		error : function(e){
			
		}
	});
}
//地图标点
function refreshMap(data){
	var map;
    var zoom = 4;
    var lnglats;
    var _CloudCollection;
    map = new T.Map('bgMap', {
        minZoom: 4
    });
    map.centerAndZoom(new T.LngLat(87, 39), zoom);
    initRegion(map);
    $.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"querySitesLnglat",
		success : function(result) {
			clusterPoints(result.lngLats,map,result.siteName);
		},
		error : function(e){
			alert('error');
		}
	});
}

//查看站点详情
function siteDetail(siteId){
	window.location.href=getURL()+"cp_farmdetailTian.html?siteId="+siteId;
}
//******** end searchResult JS *********

//******** start siteDetails JS ********
//画站点
function showSiteSize(){
	var href = window.location.href;
	var siteId  = href.split("?siteId=")[1];
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"querySiteBySiteId",
		data : {
			siteId : siteId
		},
		success : function(result) {
			var site = result.site;
			setLngLat(site.lng,site.lat);
		},
		error : function(e){
			alert('error');
		}
	});
}
function showMap(data){
	var map;
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

}
//******** end siteDetails JS **********

//******** start login JS *******************
//获取图片验证码
function getImageCode(){
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"getCodeImage",
		success : function(result) {
			document.getElementById("codeImage").src = getImgURL()+"codeImage/"+result.codeName;
			sessionStorage.setItem("code",result.code);
		},
		error : function(e){
			alert("获取验证码失败，请重试！");
		}
	});
}
//******** end login JS *****************

//*******  start cp_plotManagement JS *********
//地块展示
function showPlaces(){
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"queryPlaces",
		data : {
			siteId : sessionStorage.getItem("siteId"),
			placeId : -1,
			limit : -1,
			pageNumber : -1,
			type : 'plant'
		},
		success : function(result) {
			var places = result.places;
			if(places.length <= 0){
				document.getElementById("places").innerHTML = `<p class="no-plot" style="text-align:center;">您还没有划分地块，请先划分地块！</p>`;
				return;
			}
			var str = "";
			for(var i=0;i<places.length;i++){
				str += `<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 plotSetting">
                <a id="${places[i].placeId}" class="mg-panel" href="#!/plotManagement-sub?placeId=${places[i].placeId}" draggable="false">
                    <h3>${places[i].placeName}</h3>
                    <span class="glyphicon glyphicon-pencil">编辑</span>
                    <span class="glyphicon glyphicon-saved hide">保存</span>
                    <p class="row">
                        <span class="col-xs-3 col-sm-3 col-md-3 col-lg-3 row-child">地块名称</span>
                        <input class="col-xs-9 col-sm-9 col-md-9 col-lg-9 row-child" type="text" value="${places[i].placeName}" readonly />
                    </p>
                    <p class="row">
                        <span class="col-xs-3 col-sm-3 col-md-3 col-lg-3 row-child">地块面积</span>
                        <input class="col-xs-9 col-sm-9 col-md-9 col-lg-9 row-child square-meter-input" type="text"
                            value="${places[i].square}" readonly />
                    </p>
                    <p class="row">
                        <span class="col-xs-3 col-sm-3 col-md-3 col-lg-3 row-child">种植作物</span>
                        <input class="col-xs-9 col-sm-9 col-md-9 col-lg-9 row-child" type="text" value="${places[i].crop}" readonly />
                    </p>`;
				if(places[i].account == "1"){
					str += `<p class="row">
                        <span class="col-xs-3 col-sm-3 col-md-3 col-lg-3 row-child">认领用户</span>
                        <input class="col-xs-9 col-sm-9 col-md-9 col-lg-9 row-child" type="text" value="未被认领" readonly />
                    </p>`;
				}else{
					var str1 = places[i].userName+"("+places[i].account+")";
					str += `<p class="row">
                        <span class="col-xs-3 col-sm-3 col-md-3 col-lg-3 row-child">认领用户</span>
                         <span class="col-xs-9 col-sm-9 col-md-9 col-lg-9 row-child">${str1}</span>
                    </p>`;
				}
                str += `
                <div class="delete" onclick="delPlace('${places[i].placeId}')"><span class="glyphicon glyphicon-trash">删除</span></div>
                `;
                str += `</a>
            </div>`;
			}
			document.getElementById("places").innerHTML = str;
		},
		error : function(e){
			
		}
	});
}
//修改地块
function modifiPlaceInfo(placeId){
	//alert(placeId);
}
//删除地块
function delPlace(placeId){
	var res = confirm("确认删除?");
	if(!res){
		return;
	}
	$.ajax({
		type : "POST",//方法类型
		async : false,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"delPlace",
		data : {
			placeId : placeId
		},
		success : function(result) {
			if(result){
				alert("删除成功");
				refreshPage();
			}else{
				alert('删除成功');
			}
		},
		error : function(e){
			
		}
	});
	document.getElementById(placeId).onclick = function () {
		   	return false;
	}
}
//******** end cp_plotManagement  JS ******

//******** start cp_plotManagement-sub JS ****
//播放视频
function openVido(){
	var player = new EZUIPlayer('monitorVedio');
	player.on('error', function(){
        console.log('error');
    });
    player.on('play', function(){
        console.log('play');
    });
    player.on('pause', function(){
        console.log('pause');
    });
}
//获取视频信息
function getVideos(){
	var href = window.location.href;
	var placeId  = href.split("placeId=")[1];
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"queryVidos",
		data : {
			placeId : placeId
		},
		success : function(result) {
			var videos = result.videos;
			var str = "";
			changeVideo(videos[0].id);
			for(var i=0;i<videos.length;i++){
				str += "<li><a onclick='changeVideo(\""+videos[i].id+"\")'>"+videos[i].vidoName+"</a></li>";
			}
			document.getElementById("fistName").innerHTML = videos[0].vidoName+`<span class="glyphicon glyphicon-chevron-down"
    aria-hidden="true"></span>`;
			document.getElementById("videos").innerHTML = str;
		},
		error : function(e){
			
		}
	});
}
//更换视频
function changeVideo(id){
	document.getElementById("videoCon").innerHTML = "<video id='monitorVedio' autoplay='autoplay'> <source id='nowVideo' type=''application/x-mpegURL' /> </video>";
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"queryVidoById",
		data : {
			id : id
		},
		success : function(result) {
			var video = result.video;
			document.getElementById("nowVideo").src = video.vidoUrl;
			openVido();
		},
		error : function(e){
			
		}
	});
}
//********  end cp_plotManagement-sub JS ****

//******** start  cp_essentialInformation  JS*****
//显示站点下相关信息数量
function getCountBySite(){
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"queryCountBySite",
		data : {
			siteId : sessionStorage.getItem("siteId")
		},
		success : function(result) {
			var counts = result.counts;
			document.getElementById("placeCount").innerHTML = counts.placeCount;
			document.getElementById("userCount").innerHTML = counts.userCount;
			document.getElementById("cropCount").innerHTML = counts.cropCount;
			document.getElementById("vidoCount").innerHTML = counts.vidoCount;
			document.getElementById("sensorCount").innerHTML = counts.sensorCount;
			document.getElementById("placeNotCount").innerHTML = counts.placeNotCount;
		},
		error : function(e){
			
		}
	});
}
//显示上次登录日志
function getLastLog(){
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"getLoginLog",
		data : {
			account : sessionStorage.getItem("account")
		},
		success : function(result) {
			var loginLog = result.loginLog;
			var str = "<h3>上次登录信息</h3>";
			str += "<p><span>登录时间</span>"+loginLog.loginDate+"</p>";
			str += "<p><span>登录IP</span>"+loginLog.ip+"</p>";
			str += "<p><span>登录地点</span>"+loginLog.address+"</p>";
			document.getElementById("lastLog").innerHTML = str;
		},
		error : function(e){
			
		}
	});
}
//******** end cp_essentialInformation  Js*******

//******** start cp_farmerManagementSetting  Js*******
//站点信息
function showSiteInfo(){
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"querySite",
		data : {
			siteId : sessionStorage.getItem("siteId")
		},
		success : function(result) {
			var site = result.site;
			$("#mySiteName").val(site.siteName);
			$("#mySiteSquare").val(site.square);
			document.getElementById("mySiteRemark").innerHTML = site.remarks;
			$("#mySiteRemark1").val(site.remarks);
			$.ajax({
				type : "POST",//方法类型
				async : true,
				dataType : "json",//预期服务器返回的数据类型
				url : getURL()+"queryPlaces",
				data : {
					siteId : site.siteId,
					placeId : -1,
					limit : -1,
					pageNumber : -1,
					type : 'plant'
				},
				success : function(result) {
					var places = result.places;
					var str = "";
					for(var i=0;i<places.length;i++){
						if(str.indexOf(places[i].crop) == -1){
							str += places[i].crop + "、";
						}
					}
					document.getElementById("mySiteCrops").innerHTML = str.substring(0,str.length-1);
				},
				error : function(e){
					
				}
			});
		},
		error : function(e){
			
		}
	});
}
//修改站点
function submitModifiSite(){
	var siteName = $("#mySiteName").val();
	var square = $("#mySiteSquare").val();
	var remarks = $("#mySiteRemark1").val();
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"modifiySite",
		data : {
			siteName : siteName,
			square : square,
			siteId : sessionStorage.getItem("siteId"),
			remarks : remarks
		},
		success : function(result) {
			if(result){
				showSiteInfo();
			}else{
				alert('修改失败');
			}
		},
		error : function(e){
			
		}
	});
}
//展示站点图片
function showSiteImage(){
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"querySiteImages",
		data : {
			siteId : sessionStorage.getItem("siteId")
		},
		success : function(result) {
			var siteImages = result.siteImages;
			var str = "";
			if(siteImages.length <= 0){//无图片
				str += 
				`<form class="nopicture" id="Image_2" enctype="multipart/form-data" method="POST" action="return false">
                <input type="file" id="addPicture1" name="file"/>
                <label for="addPicture1">
                   	 您还没有农场图片，点击添加！
                </label>
            </form>`;
				document.getElementById("siteImageShow").innerHTML = str;
			}else{
				str +=
					`<div class="row pictures" id="siteImages">
                        
                    </div>
					`;
				document.getElementById("siteImageShow").innerHTML = str;
				str = "";
				for(var i=0;i<siteImages.length;i++){
					str += "<div class='col-xs-4 col-sm-4 col-md-4 col-lg-4' >";
					str += "<a href='"+getImgURL()+"siteImage/"+siteImages[i].image+"' target='_blank' draggable='false'>";
					str += "<img id='"+siteImages[i].id+"'  src='"+getImgURL()+"siteImage/"+siteImages[i].image+"' title='单击右键删除图片' alt='农场图片' draggable='false' /></a>";
                    str += "</div>";
				}
				document.getElementById("siteImages").innerHTML = str;
				$('.pictures img').contextmenu(function () {
					sessionStorage.setItem("imageId",$(this).attr('id'));
				    $('.removepicture').removeClass('hide');
				});
				$('.removepicture .mg-pop-mod__body_cancel').click(function() {
				    $(this).parents('.removepicture').addClass('hide');
				});
			}
		},
		error : function(e){
			
		}
	});
}
//站点图片删除
function deleteSiteImage(_this){
	var id = sessionStorage.getItem("imageId");
	if(id != null && id != ""){
		$.ajax({
			type : "POST",//方法类型
			async : true,
			dataType : "json",//预期服务器返回的数据类型
			url : getURL()+"delSiteImage",
			data : {
				id : id
			},
			success : function(result) {
				if(result){
					alert("删除成功！");
					sessionStorage.removeItem("imageId");
					$(_this).parents('.removepicture').addClass('hide');
					showSiteImage();
				}else{
					alert('删除失败');
				}
			},
			error : function(e){
				
			}
		});
	}
}
//站点图片上传
function uploadSiteImage(type){
	var data = new FormData($("#Image_"+type)[0]);
	$.ajax({ 
		type : 'POST',  
		async : true,
	     url : getURL()+"uploadFile?account="+sessionStorage.getItem("account")+"&path=siteImage",
	     cache : false,   //上传文件不需缓存
	     processData : false, //需设置为false。因为data值是FormData对象，不需要对数据做处理
	     contentType : false, //需设置为false。因为是FormData对象，且已经声明了属性enctype="multipart/form-data"
	     data : data,  
	     dataType : 'json', 
	     success : function(result){
			if(result.fileName == 'error'){
				alert("上传失败！");
				return;
			}else{
				$.ajax({
					type : "POST",//方法类型
					async : true,
					dataType : "json",//预期服务器返回的数据类型
					url : getURL()+"addSiteImage",
					data : {
						siteId : sessionStorage.getItem("siteId"),
						image : result.fileName
					},
					success : function(result) {
						if(result){
							alert("上传成功！");
							showSiteImage();
						}else{
							alert("最多可上传9张图片！");
						}
					},
					error : function(e){
						
					}
				});
			}
	     },
	     error : function(){ 
	      	alert("请求失败")
	     }
	 });
}
//******** end cp_farmerManagementSetting  Js*******

//********start cp_merchantManagement.html JS******
//展示站点
function showSites(){
	refreshSite(1,10);
}
//刷新站点信息
function refreshSite(pageNumber,limit){
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"adminGetSites",
		data : {
			pageNumber : pageNumber,
			limit : limit
		},
		success : function(result) {
			var sites = result.sites;
			var str = "";
			for(var i=0;i<sites.length;i++){
				str += "<tr>";
				str += "<td>"+(i+1)+"</td>";
				str += "<td>"+sites[i].siteName+"</td>";
				str += "<td>"+sites[i].province+"</td>";
				str += "<td>"+sites[i].city+"</td>";
				str += "<td>"+sites[i].county+"</td>";
				str += "<td>"+sites[i].location+"</td>";
				str += "<td>"+sites[i].account+"</td>";
				str += `<td class="table-operation">`;
				str += `<a href="#!/farmManagement-sub?siteId=${sites[i].siteId}"><i class="fa fa-eye" title="详情"></i></a>`;
				str += `<i class="fa fa-trash" title="删除" onclick="delSite('${sites[i].siteId}')"></i>`;
				str += `</td>
					</tr>`;
			}
			document.getElementById("sites").innerHTML = str;
		},
		error : function(e){
			
		}
	});
}
//添加站点
function submitAddSite(){
	var siteName = $("#newSiteName").val();
	var account = $("#newSiteAccount").val();
	var square = $("#newSiteSquare").val();
	var province = $("#newProvince").val();
	var city = $("#newCity").val();
	var county = $("#newCounty").val();
	var location = $("#newLocation").val();
	if(siteName == ""){
		alert("站点名不能为空！")
		return;
	}
	if(square == ""){
		alert("面积不能为空！")
		return;
	}
	if(province == ""){
		alert("请选择省份！")
		return;
	}
	if(city == ""){
		alert("请选择城市！")
		return;
	}
	if(county == ""){
		alert("请选择区县！")
		return;
	}
	if(location == ""){
		alert("详细地址不能为空！")
		return;
	}
	var lngLat = addressToLnglat(province+city+county+location);
	var lng = lngLat.split(",")[0];
	var lat = lngLat.split(",")[1];
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"addSite",
		data : {
			siteName : siteName,
			square : square,
			province : province,
			city : city,
			county : county,
			location : location,
			lng : lng,
			lat : lat,
			account : account,
			remarks : '暂无介绍'
		},
		success : function(result) {
			if(result.flag){
				alert("添加成功");
				skipPage('cloudranch/management/cp_drawSiteTian.html?siteId='+result.siteId);
			}else{
				alert('添加失败！');
			}
		},
		error : function(e){
			
		}
	});
}
//展示农场主账号
function showSelectAccounts(){
	$.ajax({
		type : "POST",//方法类型
		async : false,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"queryAccounts",
		data : {
			pageNumber : -1,
			limit : -1,
			role : 3
		},
		success : function(result) {
			var accounts = result.accounts;
			var str = "";
			for(var i=0;i<accounts.length;i++){
				if(accounts[i].role == 1){
					str += "<option value='"+accounts[i].account+"' >"+accounts[i].account+"("+accounts[i].userName+")"+"</option>";
				}
			}
			if(str == ""){
				str = "<option value='1' >暂无农场主账号可绑定</option>";
			}
			document.getElementById("newSiteAccount").innerHTML = str;
		},
		error : function(e){
			
		}
	});
}
//删除站点
function delSite(siteId){
	var res = confirm("确认删除?");
	if(!res){
		return;
	}
	$.ajax({
		type : "POST",//方法类型
		async : false,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"delSite",
		data : {
			siteId : siteId
		},
		success : function(result) {
			if(result){
				alert("删除成功");
				refreshPage();
			}else{
				alert('删除成功');
			}
		},
		error : function(e){
			
		}
	});
}
//********end cp_merchantManagement.html JS*****

//********start cp_farmManagement-plot JS******
//添加传感器
function addSensor(){
	var href = window.location.href;
	var placeId  = href.split("?placeId=")[1];
	var type = $("#sensorType").val();
	var sensorName = $("#sensorName").val();
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"addSensor",
		data : {
			type :type,
			sensorName : sensorName,
			placeId :placeId,
			mac : "",
			interval : 0,
			isReport : 0,
			status : "",
			battery : 100,
			picture : "",
			icon : ""
		},
		success : function(result) {
			if(result){
				alert("添加成功");
			}else{
				alert("修改失败");
			}
			refreshPage();
		},
		error : function(e){
			
		}
	});
}
//删除传感器
function delSensor(sensorId){
	var res = confirm("确认删除?");
	if(!res){
		return;
	}
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"delSensor",
		data : {
			sensorId :sensorId
		},
		success : function(result) {
			if(result){
				alert("删除成功");
			}else{
				alert("删除失败");
			}
			refreshPage();
		},
		error : function(e){
			
		}
	});
}
//显示传感器
function showSensors(){
	var href = window.location.href;
	var placeId  = href.split("?placeId=")[1];
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"querySensors",
		data : {
			placeId :placeId,
			sensorId :-1,
			limit :-1,
			pageNumber :-1
		},
		success : function(result) {
			var sensors = result.sensors;
			var senStr = "";
			for(var i=0;i<sensors.length;i++){
				senStr += sensors[i].sensorId + ",";
			}
			$.ajax({
        		type : "POST",//方法类型
        		async : true,
        		dataType : "json",//预期服务器返回的数据类型
        		url : getURL()+"adminGetSensorData",
        		data : {
        			sensorIds :senStr
        		},
        		success : function(result) {
        			var data = result.data;
        			var div = document.getElementById("appendSensor");
        			for(var i=0;i<sensors.length;i++){
        				var div_ = document.createElement("div");
        				div_.setAttribute("id", sensors[i].sensorId);
        				div_.setAttribute("class", "sensor-item");
        				div.appendChild(div_);
        				var name = sensors[i].type + "(" + sensors[i].sensorName +")";
        				var str = `
        				<div class="sensor-item__namewrapper">
                            <span class="sensor-item__name" title="${name}">${name}</span>
                            <span class="glyphicon glyphicon-trash hide" title="删除传感器" onclick="delSensor(${sensors[i].sensorId})"></span>
                        </div>`;
        				if(sensors[i].type == "空气温湿度"){
        					if(data[i] == "--"){
        						str += "<div class='sensor-item__data'>-/-</div>";
        					}else{
        						str += "<div class='sensor-item__data'>"+data[i].temp+"℃/"+data[i].hum+"%</div>";
        					}
        				}else if(sensors[i].type == "土壤温湿度"){
        					if(data[i] == "--"){
        						str += "<div class='sensor-item__data'>-/-</div>";
        					}else{
        						str += "<div class='sensor-item__data'>"+data[i].temp+"℃/"+data[i].hum+"%</div>";
        					}
        				}else if(sensors[i].type == "光照强度"){
        					if(data[i] == "--"){
        						str += "<div class='sensor-item__data'>-/-</div>";
        					}else{
        						str += "<div class='sensor-item__data'>"+data[i].intensity+"Lux</div>";
        					}
        				}else if(sensors[i].type == "光照辐射"){
        					if(data[i] == "--"){
        						str += "<div class='sensor-item__data'>-/-</div>";
        					}else{
        						str += "<div class='sensor-item__data'>"+data[i].intensity+"µmol/m2•s</div>";
        					}
        				}else if(sensors[i].type == "风速风向"){
        					if(data[i] == "--"){
        						str += "<div class='sensor-item__data'>-/-</div>";
        					}else{
        						str += "<div class='sensor-item__data'>"+data[i].speed+"m/s/"+data[i].direction+"°</div>";
        					}
        				}else if(sensors[i].type == "露点温度"){
        					if(data[i] == "--"){
        						str += "<div class='sensor-item__data'>-/-</div>";
        					}else{
        						str += "<div class='sensor-item__data'>"+data[i].temp+"℃</div>";
        					}
        				}else if(sensors[i].type == "降雨量"){
        					if(data[i] == "--"){
        						str += "<div class='sensor-item__data'>-/-</div>";
        					}else{
        						str += "<div class='sensor-item__data'>"+data[i].rainFall+"mm</div>";
        					}
        				}
        				document.getElementById(sensors[i].sensorId).innerHTML = str;
        			}
        			$('.sensor-item__name').mouseenter(function() {
        			    $(this).siblings().removeClass('hide');
        			}).mouseleave(function() {
        			    $(this).siblings().addClass('hide');
        			});
        			$('.sensor-item__namewrapper .glyphicon').mouseenter(function() {
        			    $(this).removeClass('hide');
        			}).mouseleave(function() {
        			    $(this).addClass('hide');
        			});
        		},
        		error : function(e){
        			
        		}
        	});
		},
		error : function(e){
			
		}
	});
}
//传感器类型
function showSensorType(){
	var str = "<option value='空气温湿度'>空气温湿度</option>";
	str += "<option value='土壤温湿度'>土壤温湿度</option>";
	str += "<option value='光照强度'>光照强度</option>";
	str += "<option value='光照辐射'>光照辐射</option>";
	str += "<option value='风速风向'>风速风向</option>";
	str += "<option value='露点温度'>露点温度</option>";
	str += "<option value='降雨量'>降雨量</option>";
	document.getElementById("sensorType").innerHTML = str;
}
//********end cp_farmManagement-plot JS******

//********start cp_farmManagement-sub JS****
//展示站点详情
function showSiteDetail(){
	var href = window.location.href;
	var siteId  = href.split("?siteId=")[1].split("&")[0];
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"querySiteBySiteId",
		data : {
			siteId : siteId
		},
		success : function(result) {
			var site = result.site;
			sessionStorage.setItem("siteId",site.siteId);
			sessionStorage.setItem("siteName",site.siteName);
			document.getElementById("locationSite").innerHTML = site.siteName;
			$.ajax({
				type : "POST",//方法类型
				async : true,
				dataType : "json",//预期服务器返回的数据类型
				url : getURL()+"queryPlaces",
				data : {
					siteId : site.siteId,
					placeId : -1,
					limit : -1,
					pageNumber : -1,
					type : ''
				},
				success : function(result) {
					var places = result.places;
					var str = "";
					var str1 = "";
					for(var i=0;i<places.length;i++){
						if(places[i].type == 'plant'){
							if(str.indexOf(places[i].crop) == -1){
								str += places[i].crop + "、";
							}
						}else{
							if(str.indexOf(places[i].crop) == -1){
								str1 += places[i].crop + "、";
							}

						}
					}
					if(str.indexOf("、") != -1){
						str = str.substring(0,str.length-1);
					}else{
						str = "无";
					}
					if(str1.indexOf("、") != -1){
						str1 = str1.substring(0,str1.length-1);
					}else{
						str1 = "无";
					}
					document.getElementById("siteName").innerHTML = site.siteName;
					document.getElementById("crops").innerHTML = str;
					document.getElementById("crops_ani").innerHTML = str1;
					document.getElementById("remarks").innerHTML = site.remarks;
					$("#remarks1").val(site.remarks);
					showPlacesBySite(site.siteId);
				},
				error : function(e){
					
				}
			});
			
		},
		error : function(e){
			
		}
	});
}
//地块展示
function showPlacesBySite(siteId){
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"queryPlaces",
		data : {
			siteId : siteId,
			placeId : -1,
			limit : -1,
			pageNumber : -1,
			type : ''
		},
		success : function(result) {
			var places = result.places;
			var str = "";
			for(var i=0;i<places.length;i++){
				str += `<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 plotSetting">
            <a class="mg-panel" href="#!/farmManagement-plot?placeId=${places[i].placeId}" draggable="false">
                <h3>${places[i].placeName}</h3>
                <p class="row">
                    <span class="col-xs-3 col-sm-3 col-md-3 col-lg-3 row-child">地块名称</span>
                    <input class="col-xs-9 col-sm-9 col-md-9 col-lg-9 row-child" type="text" value="${places[i].placeName}" readonly />
                </p>
                <p class="row">
                    <span class="col-xs-3 col-sm-3 col-md-3 col-lg-3 row-child">地块面积</span>
                    <input class="col-xs-9 col-sm-9 col-md-9 col-lg-9 row-child square-meter-input" type="text" value="${places[i].square}"
                        readonly />
                </p>
                <p class="row">
                    <span class="col-xs-3 col-sm-3 col-md-3 col-lg-3 row-child">种植作物</span>
                    <input class="col-xs-9 col-sm-9 col-md-9 col-lg-9 row-child" type="text" value="${places[i].crop}" readonly />
                </p>`;
				if(places[i].account == "1"){
					str += `<p class="row">
                    <span class="col-xs-3 col-sm-3 col-md-3 col-lg-3 row-child">认领用户</span>
                    <span class="col-xs-9 col-sm-9 col-md-9 col-lg-9 row-child">未被认领</span>
                </p>`;
				}else{
					var str1 = places[i].userName+"("+places[i].account+")";
					str += `<p class="row">
	                    <span class="col-xs-3 col-sm-3 col-md-3 col-lg-3 row-child">认领用户</span>
	                    <span class="col-xs-9 col-sm-9 col-md-9 col-lg-9 row-child">${str1}</span>
	                </p>`;
				}
            str += `</a>
            	</div>`;
			}
			document.getElementById("places").innerHTML = str;
		},
		error : function(e){
			
		}
	});
}
//摄像头显示
function showVideo(){
	var href = window.location.href;
	var placeId  = href.split("?placeId=")[1];
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"queryVidos",
		data : {
			placeId : placeId
		},
		success : function(result) {
			var videos = result.videos;
			var str = "";
			changeVideo(videos[0].id);
			for(var i=0;i<videos.length;i++){
				str += "<li><a onclick='changeVideo(\""+videos[i].id+"\")'>"+videos[i].vidoName+"</a></li>";
			}
			document.getElementById("fistName").innerHTML = videos[0].vidoName+`<span class="glyphicon glyphicon-chevron-down"
    aria-hidden="true"></span>`;
			document.getElementById("videos").innerHTML = str;
		},
		error : function(e){
			
		}
	});
}
//农场主端获取传感器数据
function famerGetSensorData(){
	var href = window.location.href;
	var placeId  = href.split("?placeId=")[1];
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"queryPlaces",
		data : {
			placeId : placeId,
			siteId : -1,
			limit : -1,
			pageNumber : -1,
			type : 'plant'
		},
		success : function(result) {
			var place = result.places[0];
			document.getElementById("navPlaceName").innerHTML = place.placeName;
			document.getElementById("crop").innerHTML = "<span>作物种类</span>"+place.crop;
			document.getElementById("square").innerHTML = "<span>地块面积</span>葡萄、苹果"+place.square+"m²";
		},
		error : function(e){
			
		}
	});
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"querySensors",
		data : {
			placeId :placeId,
			sensorId :-1,
			limit :-1,
			pageNumber :-1
		},
		success : function(result) {
			var sensors = result.sensors;
			var senStr = "";
			for(var i=0;i<sensors.length;i++){
				senStr += sensors[i].sensorId + ",";
			}
			$.ajax({
        		type : "POST",//方法类型
        		async : true,
        		dataType : "json",//预期服务器返回的数据类型
        		url : getURL()+"adminGetSensorData",
        		data : {
        			sensorIds :senStr
        		},
        		success : function(result) {
        			var data = result.data;
        			for(var i=0;i<sensors.length;i++){
        				if(sensors[i].type == "空气温湿度"){
        					if(data[i] == "--"){
        						document.getElementById("airTemp").innerHTML = "--";
        						document.getElementById("airHum").innerHTML = "--";
        					}else{
        						document.getElementById("airTemp").innerHTML = data[i].temp+"℃";
        						document.getElementById("airHum").innerHTML = data[i].hum+"%";
        					}
        				}else if(sensors[i].type == "土壤温湿度"){
        					if(data[i] == "--"){
        						document.getElementById("soilTemp").innerHTML = "--";
        						document.getElementById("soilHum").innerHTML = "--";
        					}else{
        						document.getElementById("soilTemp").innerHTML = data[i].temp+"℃";
        						document.getElementById("soilHum").innerHTML = data[i].hum+"%";
        					}
        				}else if(sensors[i].type == "光照强度"){
        					if(data[i] == "--"){
        						document.getElementById("radiation").innerHTML = "--";
        					}else{
        						document.getElementById("radiation").innerHTML = data[i].intensity+"Lux";
        					}
        				}else if(sensors[i].type == "光照辐射"){
        					if(data[i] == "--"){
        						document.getElementById("par").innerHTML = "--";
        					}else{
        						document.getElementById("par").innerHTML = data[i].intensity+"µmol/m2•s";
        					}
        				}else if(sensors[i].type == "风速风向"){
        					if(data[i] == "--"){
        						document.getElementById("windSpeed").innerHTML = "--";
        						document.getElementById("winDirection").innerHTML = "--";
        					}else{
        						document.getElementById("windSpeed").innerHTML = data[i].speed+"m/s";
        						document.getElementById("winDirection").innerHTML = data[i].direction+"°";
        					}
        				}else if(sensors[i].type == "露点温度"){
        					if(data[i] == "--"){
        						document.getElementById("dew").innerHTML = "--";
        					}else{
        						document.getElementById("dew").innerHTML = data[i].temp+"℃";
        					}
        				}else if(sensors[i].type == "降雨量"){
        					if(data[i] == "--"){
        						document.getElementById("rainFall").innerHTML = "--";
        					}else{
        						document.getElementById("rainFall").innerHTML = data[i].rainFall+"mm";
        					}
        				}
        			}
        			$('.sensor-item__name').mouseenter(function() {
        			    $(this).siblings().removeClass('hide');
        			}).mouseleave(function() {
        			    $(this).siblings().addClass('hide');
        			});
        			$('.sensor-item__namewrapper .glyphicon').mouseenter(function() {
        			    $(this).removeClass('hide');
        			}).mouseleave(function() {
        			    $(this).addClass('hide');
        			});
        		},
        		error : function(e){
        			
        		}
        	});
		},
		error : function(e){
			
		}
	});
}
//地块详情展示
function showPlaceInfo(){
	
}

function drawSite(){
	var href = window.location.href;
	var siteId  = href.split("siteId=")[1];
	skipPage('cloudranch/management/cp_drawSiteTian.html?siteId='+siteId);
}
//********end cp_farmManagement-sub JS*****
//********start cp_drawSiteBorder JS****
//定位加载地图
function loadMap(){
	var href = window.location.href;
	var siteId  = href.split("siteId=")[1];
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"querySiteBySiteId",
		data : {
			siteId : siteId
		},
		success : function(result) {
			var site = result.site;
			initMap(site.lat,site.lng);
		},
		error : function(e){
			
		}
	});
}
//********end cp_drawSiteBorder JS***
//*******start cp_drawPlaceTian JS****
function showSiteMap(){
	$.ajax({
		type : "POST",//方法类型
		async : false,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"querySiteBySiteId",
		data : {
			siteId : sessionStorage.getItem("siteId")
		},
		success : function(result) {
			var site = result.site;
			setLngLat(site.lng,site.lat);
		},
		error : function(e){
			alert(12);
		}
	});
}
//*******end cp_drawPlaceTian JS****
//*******start cp_userManagement JS******
//显示注册用户
function showAccounts(){
	$.ajax({
			type : "POST",//方法类型
			async : true,
			dataType : "json",//预期服务器返回的数据类型
			url : getURL()+"queryAccounts",
			data : {
				pageNumber : 1,
				limit : 10,
				role : 3
			},
			success : function(result) {
				var accounts = result.accounts;
				sessionStorage.setItem("size",result.size);
				sessionStorage.setItem("index",result.index);
				navPage();
				var str = "";
				for(var i=0;i<accounts.length;i++){
					str += "<tr>";
					str += "<td>"+(i+1)+"</td>";
					str += "<td>"+accounts[i].account+"</td>";
					str += "<td>"+accounts[i].userName+"</td>";
					str += "<td>"+accounts[i].email+"</td>";
					str += "<td>"+accounts[i].province+"</td>";
					str += "<td>"+accounts[i].city+"</td>";
					str += "<td>"+accounts[i].county+"</td>";
					str += "<td>"+accounts[i].location+"</td>";
					str += `
				     <td class="table-operation">
                        <i onclick="getRole('${accounts[i].role}','${accounts[i].account}')" class="fa fa-pencil-alt" title="编辑" data-toggle="modal" data-target=".editRole"></i>
                        <span onclick="delAccount('${accounts[i].account}')" class="glyphicon glyphicon-trash" title="删除"></span>
                    </td>
				     `;
					str += "</tr>";
				}
				document.getElementById("accounts").innerHTML = str;
			},
			error : function(e){
				alert(12);
			}
	});
}
//分页
function navPage(){
	var size = sessionStorage.getItem("size");
	var index = sessionStorage.getItem("index");
	size = Math.ceil(size/10);
	var str = `
	<li>
        <a onclick="changeNavPage('last')" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
        </a>
    </li>`;
	for(var i=0;i<size;i++){
		str +=`<li><a onclick="changeNavPage(${(i+1)})">${(i+1)}</a></li>`;
	}
	str += `
		<li>
	        <a onclick="changeNavPage('next')" aria-label="Next">
	            <span aria-hidden="true">&raquo;</span>
	        </a>
	    </li>
	`;
	document.getElementById("navPage").innerHTML = str;
}
//更换分页
function changeNavPage(type){
	var size = sessionStorage.getItem("size");
	size = Math.ceil(size/10);
	var index = sessionStorage.getItem("index");
	if(type == 'last'){
		if(index != 1){
			index--;
		}
	}else if(type == 'next'){
		if(index != size){
			index++;
		}
	}else{
		index = type;
	}
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"queryAccounts",
		data : {
			pageNumber : index,
			limit : 10,
			role : 3
		},
		success : function(result) {
			var accounts = result.accounts;
			var str = "";
			for(var i=0;i<accounts.length;i++){
				str += "<tr>";
				str += "<td>"+(i+1)+"</td>";
				str += "<td>"+accounts[i].account+"</td>";
				str += "<td>"+accounts[i].userName+"</td>";
				str += "<td>"+accounts[i].email+"</td>";
				str += "<td>"+accounts[i].province+"</td>";
				str += "<td>"+accounts[i].city+"</td>";
				str += "<td>"+accounts[i].county+"</td>";
				str += "<td>"+accounts[i].location+"</td>";
				str += `
			     <td class="table-operation">
                    <i onclick="getRole('${accounts[i].role}','${accounts[i].account}')" class="fa fa-pencil-alt" title="编辑" data-toggle="modal" data-target=".editRole"></i>
                    <span onclick="delAccount('${accounts[i].account}')" class="glyphicon glyphicon-trash" title="删除"></span>
                </td>
			     `;
				str += "</tr>";
			}
			document.getElementById("accounts").innerHTML = str;
		},
		error : function(e){
			alert(12);
		}
	});
	
}
//删除账号
function delAccount(account){
	var res = confirm("确认删除?");
	if(!res){
		return;
	}
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"delAccount",
		data : {
			account : account
		},
		success : function(result) {
			if(result){
				alert("删除成功");
				refreshPage();
			}else{
				alert("删除失败");
			}
		},
		error : function(e){
			alert('error');
		}
	});
}
//*******end cp_userManagement JS ******
//*******start cp_selectSite JS ******
function showAllSitesToAccount(){
	$.ajax({
		type : "POST",//方法类型
		async : false,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"famerGetSites",
		data : {
			account : sessionStorage.getItem("account")
		},
		success : function(result) {
			var sites = result.sites;
			var lngLats = result.lngLats;
			for(var i=0;i<sites.length;i++){
				let lnglat = getCenterPoint(lngLats[i])
			    let lng = lnglat.lng;
			    let lat = lnglat.lat;
			    setLocation(lng,lat);
				let site = drawSite_select(lngLats[i], map);
			    site.addLabelWindow({
			        '地块': sites[i].siteName,
			        '面积': sites[i].square+'㎡'
			    });
			    site.bindLink(sites[i].siteId);
			}
		},
		error : function(e){
			alert("error");
		}
	});
}
//进入指定站点
function showTheSite(siteId){
	sessionStorage.setItem("siteId",siteId);
	refreshPage();
}
//*******end cp_selectSite JS *****
//*******start cp_farmdetailTian JS *****
function showAllSitesDetails(){
	var href = window.location.href;
	var siteId  = href.split("siteId=")[1];
	$.ajax({
		type : "POST",//方法类型
		async : false,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"querySiteBySiteId",
		data : {
			siteId : siteId
		},
		success : function(result) {
			var site = result.site;
			document.getElementById("siteName").innerHTML = site.siteName;
			document.getElementById("siteRemark").innerHTML = site.remarks;
			document.getElementById("siteAddress").innerHTML = site.province+site.city+site.county+site.location;
			showDetailsSiteImage();
			var lngLats = result.lngLats;
			let lnglat = getCenterPoint(lngLats)
		    let lng = lnglat.lng;
		    let lat = lnglat.lat;
		    setLocation1(lng,lat);
		    let siteB = drawSite_select(lngLats, map,"#FFFFFF");
		    siteB.addLabelWindow({
		        '地块': site.siteName,
		        '面积': site.square+'㎡'
		    });
		    drawPlaceBorder1(map,siteId);
		},
		error : function(e){
			alert("error");
		}
	});
}
//站点图片显示
function showDetailsSiteImage(){
	var href = window.location.href;
	var siteId  = href.split("siteId=")[1];
	$.ajax({
		type : "POST",//方法类型
		async : false,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"querySiteImages",
		data : {
			siteId : siteId
		},
		success : function(result) {
			var siteImages = result.siteImages;
			var str = "";
			if(siteImages.length <= 0){//无图片
				var imgUrl = getURL() + "img/noImage.jpg";
				str = `
					<a href="${imgUrl}" target="_blank" class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
		                 <img src="${imgUrl}" draggable="false" alt="农场图片" />
		             </a>
					`;
				
			}else{
				for(var i=0;i<siteImages.length;i++){
					var imgUrl = getImgURL() + "siteImage/" + siteImages[i].image;
					str += `
					<a href="${imgUrl}" target="_blank" class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
	                    <img src="${imgUrl}" draggable="false" alt="农场图片" />
	                </a>
					`;
				}
			}
			document.getElementById("siteImages").innerHTML = str;
		},
		error : function(e){
			alert("error");
		}
	});
}
//画地块边界
function drawPlaceBorder1(map,siteId){
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"queryPlaces",
		data : {
			siteId : siteId,
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
			placeIds = placeIds.substring(0,placeIds.length-1);
			showSensorDatas(placeIds);
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
						let placeB = drawPlace_select(pls[i], map,places[i]);
						placeB.bindEvent(places[i]);
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
function showSensorDatas(placeIds){
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"querySensorData",
		data : {
			placeIds : placeIds
		},
		success : function(result) {
			var air = result.air;
			if(air == null){
				document.getElementById("airHum").innerHTML = "--";
				document.getElementById("airTemp").innerHTML = "--";
			}else{
				document.getElementById("airHum").innerHTML = air.hum+"%";
				document.getElementById("airTemp").innerHTML = air.temp+"℃";
			}
			var soil = result.soil;
			if(soil == null){
				document.getElementById("soilHum").innerHTML = "--";
				document.getElementById("soilTemp").innerHTML = "--";
			}else{
				document.getElementById("soilHum").innerHTML = soil.hum+"%";
				document.getElementById("soilTemp").innerHTML = soil.temp+"℃";
			}
			var radiation = result.radiation;
			if(radiation == null){
				document.getElementById("radiation").innerHTML = "--";
			}else{
				document.getElementById("radiation").innerHTML = radiation.intensity+"Lux";
			}
			var par = result.par;
			if(par == null){
				document.getElementById("par").innerHTML = "--";
			}else{
				document.getElementById("par").innerHTML = par.intensity+"µmol/m2•s";
			}
			var wind = result.wind;
			if(wind == null){
				document.getElementById("windSpeed").innerHTML = "--";
				document.getElementById("windDir").innerHTML = "--";
			}else{
				document.getElementById("windSpeed").innerHTML = wind.speed+"m/s";
				document.getElementById("windDir").innerHTML = wind.direction+"°";
			}
			var dew = result.dew;
			if(dew == null){
				document.getElementById("dew").innerHTML = "--";
			}else{
				document.getElementById("dew").innerHTML = dew.temp+"°C";
			}
			var rainFall = result.rainFall;
			if(rainFall == null){
				document.getElementById("rainFall").innerHTML = "--";
			}else{
				document.getElementById("rainFall").innerHTML = rainFall.rainFall+"mm";
			}
		},
		error : function(e){
			
		}
	});
}
function placeToAccount(placeId){
  var account = sessionStorage.getItem("account");
  if(account == null || account == ""){
	  var res = confirm("您还没有登录，是否前往登录?");
	  if(res){
		  var href = window.location.href;
		  var siteId  = href.split("?siteId=")[1];
		  sessionStorage.setItem("waitURL","cp_farmdetailTian.html?siteId="+siteId);
		  skipPage('login.html');
	  }
	  return;
  }
  $.ajax({
		type : "POST",//方法类型
		async : false,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"changePlaceAccount",
		data : {
			placeId : placeId,
			account : account
		},
		success : function(result) {
			if(result){
				alert("认领成功");
			}else{
				alert("认领失败");
			}
			refreshPage();
		},
		error : function(e){
			
		}
	});
}
//*******end cp_farmdetailTian JS *****
//*******start informationentry JS*****
//添加种植记录
function saveAddPlant(){
	var placeId = $("#places").val();
	var cropName = $("#cropName").val();
	var fileInput = $('#chooseFile').get(0).files[0];
	var image = "";
	if(fileInput){
		var data = new FormData($("#plant_image")[0]);
		$.ajax({ 
    		type : 'POST',  
    		async : false,
		     url : getURL()+"uploadFile?account="+sessionStorage.getItem("account")+"&path=plantImage",
		     cache : false,   //上传文件不需缓存
		     processData : false, //需设置为false。因为data值是FormData对象，不需要对数据做处理
		     contentType : false, //需设置为false。因为是FormData对象，且已经声明了属性enctype="multipart/form-data"
		     data : data,  
		     dataType : 'json', 
		     success : function(result){
				image = result.fileName;
		     },
		     error : function(){ 
		      	alert("请求失败")
		     }
		 });
	}
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"addPlant",
		data : {
			placeId : placeId,
			cropName : cropName,
			image : image
		},
		success : function(result) {
			if(result){
				alert("添加成功");
			}else{
				alert("添加失败");
			}
			refreshPage();
		},
		error : function(e){
			
		}
	});
}
//显示种植记录
function showPlants(){
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"queryPlants",
		data : {
			createDate : "",
			cropId : "",
			limit : -1,
			placeId : -1,
			pageNumber : -1
		},
		success : function(result) {
			var plants = result.plants;
			var tbody = document.getElementById("plants");
			for(var i=0;i<plants.length;i++){
				var tr = document.createElement("tr");
				tr.setAttribute("id", plants[i].cropId);
				tbody.appendChild(tr);
				var str = "<td>"+plants[i].createDate+"</td>";
                str += "<td title='"+plants[i].placeName+"'>"+plants[i].placeName+"</td>";
                str += "<td title='"+plants[i].cropName+"'>"+plants[i].cropName+"</td>";
                str += "<td title='图片' class='picture'>";
                if(plants[i].image == ""){
                	str += "<a href='"+getURL()+"img/noImage.jpg' target='_blank'>";
                    str += "<img src='"+getURL()+"img/noImage.jpg' />";
                }else{
                	str += "<a href='"+getImgURL()+"plantImage/"+plants[i].image+"' target='_blank'>";
                    str += "<img src='"+getImgURL()+"plantImage/"+plants[i].image+"' />";
                }
                str += "</a>";
                str += "</td>";
                document.getElementById(plants[i].cropId).innerHTML = str;
			}
		},
		error : function(e){
			
		}
	});
}
//显示可种植地块
function showSelectPlaces(){
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"queryPlaces",
		data : {
			siteId : sessionStorage.getItem("siteId"),
			limit : -1,
			placeId : -1,
			pageNumber : -1,
			type : "plant"
		},
		success : function(result) {
			var places = result.places;
			var str = "";
			if(places.length <= 0){
				str = "<option value='无'>暂无可种植地块</option>";
			}else{
				for(var i=0;i<places.length;i++){
					str += "<option value='"+places[i].placeId+"'>"+places[i].placeName+"</option>";
				}
			}
			document.getElementById("places").innerHTML = str;
		},
		error : function(e){
			
		}
	});
}
//添加收成记录
function saveAddHarvest(){
	var remarks = $("#remarks").val();
	var cropId = $("#plants_1").val();
	var fileInput = $('#chooseFile').get(0).files[0];
	var image = "";
	if(fileInput){
		var data = new FormData($("#harvest_image")[0]);
		$.ajax({ 
    		type : 'POST',  
    		async : false,
		     url : getURL()+"uploadFile?account="+sessionStorage.getItem("account")+"&path=harvestImage",
		     cache : false,   //上传文件不需缓存
		     processData : false, //需设置为false。因为data值是FormData对象，不需要对数据做处理
		     contentType : false, //需设置为false。因为是FormData对象，且已经声明了属性enctype="multipart/form-data"
		     data : data,  
		     dataType : 'json', 
		     success : function(result){
				image = result.fileName;
		     },
		     error : function(){ 
		      	alert("请求失败")
		     }
		 });
	}
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"addHarvest",
		data : {
			remarks : remarks,
			cropId : cropId,
			image : image
		},
		success : function(result) {
			if(result){
				alert("添加成功");
			}else{
				alert("添加失败");
			}
			refreshPage();
		},
		error : function(e){
			
		}
	});
}
//显示收成记录
function showHarvest(){
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"queryHarvests",
		data : {
			cropId : ""
		},
		success : function(result) {
			var harvests = result.harvests;
			var tbody = document.getElementById("harvests");
			for(var i=0;i<harvests.length;i++){
				var tr = document.createElement("tr");
				tr.setAttribute("id", harvests[i].id);
				tbody.appendChild(tr);
				var str = "<td>"+harvests[i].createDate+"</td>";
                str += "<td title='"+harvests[i].cropName+"'>"+harvests[i].cropName+"</td>";
                str += "<td title='"+harvests[i].remarks+"'>"+harvests[i].remarks+"</td>";
                str += "<td title='图片' class='picture'>";
                if(harvests[i].image == ""){
                	str += "<a href='"+getURL()+"img/noImage.jpg' target='_blank'>";
                    str += "<img src='"+getURL()+"img/noImage.jpg' />";
                }else{
                	str += "<a href='"+getImgURL()+"harvestImage/"+harvests[i].image+"' target='_blank'>";
                    str += "<img src='"+getImgURL()+"harvestImage/"+harvests[i].image+"' />";
                }
                str += "</a>";
                str += "</td>";
                document.getElementById(harvests[i].id).innerHTML = str;
			}
		},
		error : function(e){
			
		}
	});
}
//添加施肥记录
function saveAddSpreadMan(){
	var remarks = $("#remarks").val();
	var cropId = $("#plants_1").val();
	var fileInput = $('#chooseFile').get(0).files[0];
	var image = "";
	if(fileInput){
		var data = new FormData($("#spreadMan_image")[0]);
		$.ajax({ 
    		type : 'POST',  
    		async : false,
		     url : getURL()+"uploadFile?account="+sessionStorage.getItem("account")+"&path=spreadManImage",
		     cache : false,   //上传文件不需缓存
		     processData : false, //需设置为false。因为data值是FormData对象，不需要对数据做处理
		     contentType : false, //需设置为false。因为是FormData对象，且已经声明了属性enctype="multipart/form-data"
		     data : data,  
		     dataType : 'json', 
		     success : function(result){
				image = result.fileName;
		     },
		     error : function(){ 
		      	alert("请求失败")
		     }
		 });
	}
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"addSpreadMan",
		data : {
			remarks : remarks,
			cropId : cropId,
			image : image
		},
		success : function(result) {
			if(result){
				alert("添加成功");
			}else{
				alert("添加失败");
			}
			refreshPage();
		},
		error : function(e){
			
		}
	});
}
//显示施肥记录
function showSpreadMan(){
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"querySpreadMans",
		data : {
			cropId : ""
		},
		success : function(result) {
			var spreadMans = result.spreadMans;
			var tbody = document.getElementById("spreadMans");
			for(var i=0;i<spreadMans.length;i++){
				var tr = document.createElement("tr");
				tr.setAttribute("id", spreadMans[i].id);
				tbody.appendChild(tr);
				var str = "<td>"+spreadMans[i].createDate+"</td>";
                str += "<td title='"+spreadMans[i].cropName+"'>"+spreadMans[i].cropName+"</td>";
                str += "<td title='"+spreadMans[i].remarks+"'>"+spreadMans[i].remarks+"</td>";
                str += "<td title='图片' class='picture'>";
                if(spreadMans[i].image == ""){
                	str += "<a href='"+getURL()+"img/noImage.jpg' target='_blank'>";
                    str += "<img src='"+getURL()+"img/noImage.jpg' />";
                }else{
                	str += "<a href='"+getImgURL()+"spreadManImage/"+spreadMans[i].image+"' target='_blank'>";
                    str += "<img src='"+getImgURL()+"spreadManImage/"+spreadMans[i].image+"' />";
                }
                str += "</a>";
                str += "</td>";
                document.getElementById(spreadMans[i].id).innerHTML = str;
			}
		},
		error : function(e){
			
		}
	});
}
//显示已种植作物
function showSelectPlants(num){
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"queryPlants",
		data : {
			createDate : "",
			cropId : "",
			placeId : -1,
			limit : -1,
			pageNumber : -1
		},
		success : function(result) {
			var plants = result.plants;
			var str = "";
			if(plants.length <= 0){
				str = "<option value='无'>暂无已种植作物</option>";
			}else{
				for(var i=0;i<plants.length;i++){
					str += "<option value='"+plants[i].cropId+"'>"+plants[i].cropName+"</option>";
				}
			}
			for(var i=1;i<=num;i++){
				document.getElementById("plants_"+i).innerHTML = str;
			}
		},
		error : function(e){
			
		}
	});
}
//添加农药记录
function saveAddPescitide(){
	var remarks = $("#remarks").val();
	var cropId = $("#plants").val();
	var fileInput = $('#chooseFile').get(0).files[0];
	var image = "";
	if(fileInput){
		var data = new FormData($("#pesticide_image")[0]);
		$.ajax({ 
    		type : 'POST',  
    		async : false,
		     url : getURL()+"uploadFile?account="+sessionStorage.getItem("account")+"&path=pesticideImage",
		     cache : false,   //上传文件不需缓存
		     processData : false, //需设置为false。因为data值是FormData对象，不需要对数据做处理
		     contentType : false, //需设置为false。因为是FormData对象，且已经声明了属性enctype="multipart/form-data"
		     data : data,  
		     dataType : 'json', 
		     success : function(result){
				image = result.fileName;
		     },
		     error : function(){ 
		      	alert("请求失败")
		     }
		 });
	}
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"addPesticide",
		data : {
			remarks : remarks,
			cropId : cropId,
			image : image
		},
		success : function(result) {
			if(result){
				alert("添加成功");
			}else{
				alert("添加失败");
			}
			refreshPage();
		},
		error : function(e){
			
		}
	});
}
//显示农药记录
function showPesticide(){
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"queryPesticides",
		data : {
			cropId : ""
		},
		success : function(result) {
			var pesticides = result.pesticides;
			var tbody = document.getElementById("pesticides");
			for(var i=0;i<pesticides.length;i++){
				var tr = document.createElement("tr");
				tr.setAttribute("id", pesticides[i].id);
				tbody.appendChild(tr);
				var str = "<td>"+pesticides[i].createDate+"</td>";
                str += "<td title='"+pesticides[i].cropName+"'>"+pesticides[i].cropName+"</td>";
                str += "<td title='"+pesticides[i].remarks+"'>"+pesticides[i].remarks+"</td>";
                str += "<td title='图片' class='picture'>";
                if(pesticides[i].image == ""){
                	str += "<a href='"+getURL()+"img/noImage.jpg' target='_blank'>";
                    str += "<img src='"+getURL()+"img/noImage.jpg' />";
                }else{
                	str += "<a href='"+getImgURL()+"pesticideImage/"+pesticides[i].image+"' target='_blank'>";
                    str += "<img src='"+getImgURL()+"pesticideImage/"+pesticides[i].image+"' />";
                }
                str += "</a>";
                str += "</td>";
                document.getElementById(pesticides[i].id).innerHTML = str;
			}
		},
		error : function(e){
			
		}
	});
}
//添加病虫害记录
function saveAddWog(){
	var remarks = $("#remarks2").val();
	var cropId = $("#plants_3").val();
	var fileInput = $('#chooseFile').get(0).files[0];
	var image = "";
	if(fileInput){
		var data = new FormData($("#wog_image")[0]);
		$.ajax({ 
    		type : 'POST',  
    		async : false,
		     url : getURL()+"uploadFile?account="+sessionStorage.getItem("account")+"&path=wogImage",
		     cache : false,   //上传文件不需缓存
		     processData : false, //需设置为false。因为data值是FormData对象，不需要对数据做处理
		     contentType : false, //需设置为false。因为是FormData对象，且已经声明了属性enctype="multipart/form-data"
		     data : data,  
		     dataType : 'json', 
		     success : function(result){
				image = result.fileName;
		     },
		     error : function(){ 
		      	alert("请求失败")
		     }
		 });
	}
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"addWog",
		data : {
			remarks : remarks,
			cropId : cropId,
			image : image
		},
		success : function(result) {
			if(result){
				alert("添加成功");
			}else{
				alert("添加失败");
			}
			refreshPage();
		},
		error : function(e){
			
		}
	});
}
//显示病虫害记录
function showWog(){
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"queryWogs",
		data : {
			cropId : ""
		},
		success : function(result) {
			var wogs = result.wogs;
			var tbody = document.getElementById("wogs");
			for(var i=0;i<wogs.length;i++){
				var tr = document.createElement("tr");
				tr.setAttribute("id", wogs[i].id);
				tbody.appendChild(tr);
				var str = "<td>"+wogs[i].createDate+"</td>";
                str += "<td title='"+wogs[i].cropName+"'>"+wogs[i].cropName+"</td>";
                str += "<td title='"+wogs[i].remarks+"'>"+wogs[i].remarks+"</td>";
                str += "<td title='图片' class='picture'>";
                if(wogs[i].image == ""){
                	str += "<a href='"+getURL()+"img/noImage.jpg' target='_blank'>";
                    str += "<img src='"+getURL()+"img/noImage.jpg' />";
                }else{
                	str += "<a href='"+getImgURL()+"wogImage/"+wogs[i].image+"' target='_blank'>";
                    str += "<img src='"+getImgURL()+"wogImage/"+wogs[i].image+"' />";
                }
                str += "</a>";
                str += "</td>";
                document.getElementById(wogs[i].id).innerHTML = str;
			}
		},
		error : function(e){
			
		}
	});
}
//添加长势记录
function saveAddGrow(){
	var remarks = $("#remarks").val();
	var cropId = $("#plants").val();
	var fileInput = $('#chooseFile').get(0).files[0];
	var image = "";
	if(fileInput){
		var data = new FormData($("#grow_image")[0]);
		$.ajax({ 
    		type : 'POST',  
    		async : false,
		     url : getURL()+"uploadFile?account="+sessionStorage.getItem("account")+"&path=growImage",
		     cache : false,   //上传文件不需缓存
		     processData : false, //需设置为false。因为data值是FormData对象，不需要对数据做处理
		     contentType : false, //需设置为false。因为是FormData对象，且已经声明了属性enctype="multipart/form-data"
		     data : data,  
		     dataType : 'json', 
		     success : function(result){
				image = result.fileName;
		     },
		     error : function(){ 
		      	alert("请求失败")
		     }
		 });
	}
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"addGrow",
		data : {
			remarks : remarks,
			cropId : cropId,
			image : image
		},
		success : function(result) {
			if(result){
				alert("添加成功");
			}else{
				alert("添加失败");
			}
			refreshPage();
		},
		error : function(e){
			
		}
	});
}
//显示长势记录
function showGrow(){
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"queryGrows",
		data : {
			cropId : ""
		},
		success : function(result) {
			var grows = result.grows;
			var tbody = document.getElementById("grows");
			for(var i=0;i<grows.length;i++){
				var tr = document.createElement("tr");
				tr.setAttribute("id", grows[i].id);
				tbody.appendChild(tr);
				var str = "<td>"+grows[i].createDate+"</td>";
                str += "<td title='"+grows[i].cropName+"'>"+grows[i].cropName+"</td>";
                str += "<td title='"+grows[i].remarks+"'>"+grows[i].remarks+"</td>";
                str += "<td title='图片' class='picture'>";
                if(grows[i].image == ""){
                	str += "<a href='"+getURL()+"img/noImage.jpg' target='_blank'>";
                    str += "<img src='"+getURL()+"img/noImage.jpg' />";
                }else{
                	str += "<a href='"+getImgURL()+"growImage/"+grows[i].image+"' target='_blank'>";
                    str += "<img src='"+getImgURL()+"growImage/"+grows[i].image+"' />";
                }
                str += "</a>";
                str += "</td>";
                document.getElementById(grows[i].id).innerHTML = str;
			}
		},
		error : function(e){
			
		}
	});
}
//添加灌溉记录
function saveAddIrrigate(){
	var remarks = $("#remarks1").val();
	var cropId = $("#plants_2").val();
	var fileInput = $('#chooseFile').get(0).files[0];
	var image = "";
	if(fileInput){
		var data = new FormData($("#irrigate_image")[0]);
		$.ajax({ 
    		type : 'POST',  
    		async : false,
		     url : getURL()+"uploadFile?account="+sessionStorage.getItem("account")+"&path=irrgateImage",
		     cache : false,   //上传文件不需缓存
		     processData : false, //需设置为false。因为data值是FormData对象，不需要对数据做处理
		     contentType : false, //需设置为false。因为是FormData对象，且已经声明了属性enctype="multipart/form-data"
		     data : data,  
		     dataType : 'json', 
		     success : function(result){
				image = result.fileName;
		     },
		     error : function(){ 
		      	alert("请求失败")
		     }
		 });
	}
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"addIrrigate",
		data : {
			remarks : remarks,
			cropId : cropId,
			image : image
		},
		success : function(result) {
			if(result){
				alert("添加成功");
			}else{
				alert("添加失败");
			}
			refreshPage();
		},
		error : function(e){
			
		}
	});
}
//显示施肥记录
function showIrrigate(){
	$.ajax({
		type : "POST",//方法类型
		async : true,
		dataType : "json",//预期服务器返回的数据类型
		url : getURL()+"queryIrrigates",
		data : {
			cropId : ""
		},
		success : function(result) {
			var irrigates = result.irrigates;
			var tbody = document.getElementById("irrigates");
			for(var i=0;i<irrigates.length;i++){
				var tr = document.createElement("tr");
				tr.setAttribute("id", irrigates[i].id);
				tbody.appendChild(tr);
				var str = "<td>"+irrigates[i].createDate+"</td>";
                str += "<td title='"+irrigates[i].cropName+"'>"+irrigates[i].cropName+"</td>";
                str += "<td title='"+irrigates[i].remarks+"'>"+irrigates[i].remarks+"</td>";
                str += "<td title='图片' class='picture'>";
                if(irrigates[i].image == ""){
                	str += "<a href='"+getURL()+"img/noImage.jpg' target='_blank'>";
                    str += "<img src='"+getURL()+"img/noImage.jpg' />";
                }else{
                	str += "<a href='"+getImgURL()+"irrgateImage/"+irrigates[i].image+"' target='_blank'>";
                    str += "<img src='"+getImgURL()+"irrgateImage/"+irrigates[i].image+"' />";
                }
                str += "</a>";
                str += "</td>";
                document.getElementById(irrigates[i].id).innerHTML = str;
			}
		},
		error : function(e){
			
		}
	});
}
//*******end informationentry JS*****

//地理编码
function addressToLnglat(address){
	var lngLat = "";
	$.ajax({
		type : "POST",//方法类型
		async : false,
		dataType : "json",//预期服务器返回的数据类型
		url : "https://api.tianditu.gov.cn/geocoder?ds={\"keyWord\":\""+address+"\"}&tk=3f44451cff26b689a375c59edcdae223",
		success : function(result) {
			var location = result.location;
			var lng = location.lon;
			var lat = location.lat;
			lngLat = lng + "," + lat;
		},
		error : function(e){
			
		}
	});
	return lngLat;
}