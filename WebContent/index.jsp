<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<button onclick="tijiao('安徽省合肥市蜀山区安徽农业大学')">提交</button>
	<button onclick="test()">测试</button>
	<form  id="my_userImage" enctype="multipart/form-data" method="POST" action="return false">
		<input type="file" id="file" name="file"/>
		<input type="button"  value="上传" onclick="uploadFile()"/>
		更改头像
	</form>
	<!-- <video id="monitorVedio" poster="audioImgs/audio.jpg"
		controls playsInline webkit-playsinline autoplay>
		<source src="http://hls.open.ys7.com/openlive/17c5a168e8b448e0bfb392793982a941.hd.m3u8"
			type="application/x-mpegURL" /> -->
		
	<video>
	<source src="http://hls.open.ys7.com/openlive/17c5a168e8b448e0bfb392793982a941.hd.m3u8"
			type="application/x-mpegURL" />
	</video>
</body>
<script src="js/ezuikit.js"></script>
<script src="js/jquery.js"></script>
<script type="text/javascript">
/* var player = new EZUIPlayer('monitorVedio');
player.on('error', function(){
    console.log('error');
});
player.on('play', function(){
    console.log('play');
});
player.on('pause', function(){
    console.log('pause');
}); */
	function uploadFile(){
		alert(1);
		var data = new FormData($("#my_userImage")[0]);
		$.ajax({ 
    		type : 'POST',  
    		async : true,
		     url : "${pageContext.request.contextPath}/uploadFile?account=123456&path=image1",
		     cache : false,   //上传文件不需缓存
		     processData : false, //需设置为false。因为data值是FormData对象，不需要对数据做处理
		     contentType : false, //需设置为false。因为是FormData对象，且已经声明了属性enctype="multipart/form-data"
		     data : data,  
		     dataType : 'json', 
		     success : function(result){
				alert(result.fileName);
		     },
		     error : function(){ 
		      	alert("请求失败")
		     }
		 });
	}
	function tijiao(address){
		var l = addressToLnglat(address);
		alert(l);
	}
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
				alert(lngLat);
			},
			error : function(e){
				
			}
		});
		return lngLat;
	}
	function test(){
		$.ajax({
			type : "POST",//方法类型
			async : false,
			dataType : "json",//预期服务器返回的数据类型
			url : "${pageContext.request.contextPath}/addPar",
			data : {
				intensity : 19,
				hum : 150,
				temp : 50,
				sensorId : 14
			},
			success : function(result) {
				alert(result);
			},
			error : function(e){
				
			}
		});
	}
	window.onbeforeunload=function(){
	    if(document.all){
	        if(event.clientY<0){
	            return "确定要离开吗？";
	        }
	    }else{
	        return "确定要离开吗？";
	    }
	}  
</script>
</html>