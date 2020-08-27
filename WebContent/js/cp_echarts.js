// 基于准备好的dom，初始化echarts实例
var airHumidity = echarts.init(document.getElementById('airHumidity'));
var airTemperature = echarts.init(document.getElementById('airTemperature'));
var soilHumidity = echarts.init(document.getElementById('soilHumidity'));
var soilTemperature = echarts.init(document.getElementById('soilTemperature'));
var co2Concentration = echarts.init(document.getElementById('co2Concentration'));
var intensityOfIllumination = echarts.init(document.getElementById('intensityOfIllumination'));
var windSpeedDirection = echarts.init(document.getElementById('windSpeedDirection'));
// 指定图表的配置项和数据
var option = {
    title:{
        text:'近七日采集数据',
        x:'center',
        textStyle:{
            color:'#fff', 
            align:'center',
            fontSize:13
        }
    },
    textStyle:{
        color:'#fff'
    },
    xAxis:{
        type: 'category',
        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
    },
    yAxis:{
        type: 'value'
    },
    tooltip:{
        trigger:'axis',
        triggerOn:'mousemove',
        formatter:'{c0}'
    },
    series:[{
        type: 'line',
        data: [820, 932, 901, 934, 1290, 1330, 1320],
        smooth: true,
        itemStyle:{
            color:'rgb(0,128,0)'
        }
    }]
};
// 使用刚指定的配置项和数据显示图表。
airHumidity.setOption(option);
airTemperature.setOption(option);
soilHumidity.setOption(option);
soilTemperature.setOption(option);
co2Concentration.setOption(option);
intensityOfIllumination.setOption(option);
windSpeedDirection.setOption(option);
