
/**
 * 绘制模型图
 * @param country
 * @param sport
 * @param chart
 */
function getChartsModel(country,sport,chart)
{
    let xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onreadystatechange=function ()
    {
        if(xmlHttpRequest.readyState===4&&xmlHttpRequest.status===200) {
            let content = xmlHttpRequest.responseText;
            content = JSON.parse(content);
            var year=[];//年
            var goldNum=[];//金牌总数
            var silverNum=[];//银牌总数
            var bronzeNum=[];//铜牌总数
            var totalNum=[];//全部奖牌数
            var goldTotal =0;
            var silverTotal =0;
            var bronzeTotal =0;
            for(let item of content)
            {
                year.push(item.year);
                goldNum.push(item.goldNum);
                goldTotal+=item.goldNum;
                silverNum.push(item.silverNum);
                silverTotal+=item.silverNum;
                bronzeNum.push(item.bronzeNum);
                bronzeTotal+=item.bronzeNum;
                totalNum.push(item.totalNum);
            }
            let element = document.getElementById('InfoGraph');
            element.style.width = '1000px';
            element.style.height = '500px';
            let myChart = echarts.getInstanceByDom(element);

            if(chart === "0"){
                let option;
                option = {
                    backgroundColor: "#344b58",
                    "title": {
                        "text": country+" 奖牌数目统计",
                        "subtext": "项目: "+sport,
                        x: "40%",

                        textStyle: {
                            color: '#fff',
                            fontSize: '22'
                        },
                        subtextStyle: {
                            color: '#90979c',
                            fontSize: '16',

                        },
                    },
                    "tooltip": {
                        "trigger": "axis",
                        "axisPointer": {
                            "type": "shadow",
                            textStyle: {
                                color: "#fff"
                            }

                        },
                    },
                    "grid": {
                        "borderWidth": 0,
                        "top": 110,
                        "bottom": 95,
                        textStyle: {
                            color: "#fff"
                        }
                    },
                    "legend": {
                        x: '4%',
                        top: '8%',
                        textStyle: {
                            color: '#90979c',
                        },
                        "data": ['金牌','银牌','铜牌']
                    },


                    "calculable": true,
                    "xAxis": [{
                        "type": "category",
                        "axisLine": {
                            lineStyle: {
                                color: '#90979c'
                            }
                        },
                        "splitLine": {
                            "show": false
                        },
                        "axisTick": {
                            "show": false
                        },
                        "splitArea": {
                            "show": false
                        },
                        "axisLabel": {
                            "interval": 0,

                        },
                        "data": year,
                    }],
                    "yAxis": [{
                        "type": "value",
                        "splitLine": {
                            "show": false
                        },
                        "axisLine": {
                            lineStyle: {
                                color: '#90979c'
                            }
                        },
                        "axisTick": {
                            "show": false
                        },
                        "axisLabel": {
                            "interval": 0,

                        },
                        "splitArea": {
                            "show": false,
                        },

                    }],
                    "dataZoom": [{
                        "show": true,
                        "height": 30,
                        "xAxisIndex": [
                            0
                        ],
                        bottom: 30,
                        "start": 10,
                        "end": 80,
                        handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
                        handleSize: '110%',
                        handleStyle:{
                            color:"#d3dee5",

                        },
                        textStyle:{
                            color:"#fff"},
                        borderColor:"#90979c"


                    }, {
                        "type": "inside",
                        "show": true,
                        "height": 15,
                        "start": 1,
                        "end": 35
                    }],
                    "series": [{
                        "name": "金牌",
                        "type": "bar",
                        "stack": "总量",
                        "barMaxWidth": 35,
                        "barGap": "10%",
                        "itemStyle": {
                            "normal": {
                                "color":"rgba(255, 215, 0, 1.0)",
                                "label": {
                                    textStyle:{
                                        color:"#000000"},
                                    "show": true,
                                    "position": "inside",
                                    formatter: function(p) {
                                        return p.value > 0 ? (p.value) : '';
                                    }
                                }
                            }
                        },
                        "data":goldNum,
                    },

                        {
                            "name": "银牌",
                            "type": "bar",
                            "stack": "总量",
                            "barMaxWidth": 35,
                            "barGap": "10%",
                            "itemStyle": {
                                "normal": {
                                    "color": "rgba(192, 192, 192, 1.0)",
                                    "barBorderRadius": 0,
                                    "label": {
                                        "show": true,
                                        "position": "inside",
                                        formatter: function(p) {
                                            return p.value > 0 ? (p.value) : '';
                                        }
                                    }
                                }
                            },
                            "data": silverNum
                        }, {
                            "name": "铜牌",
                            "type": "bar",
                            "stack": "总量",
                            "barMaxWidth": 35,
                            "barGap": "10%",
                            "itemStyle": {
                                "normal": {
                                    "color": "rgba(180, 116, 64, 1.0)",
                                    "barBorderRadius": 0,
                                    "label": {
                                        "show": true,
                                        "position": "inside",
                                        formatter: function(p) {
                                            return p.value > 0 ? (p.value) : '';
                                        }
                                    }
                                }
                            },
                            "data":bronzeNum
                        },
                        {
                            "name": "总数",
                            "type": "line",
                            symbolSize:10,
                            symbol:'circle',
                            "itemStyle": {
                                "normal": {
                                    "color": "rgba(252,230,48,1)",
                                    "barBorderRadius": 0,
                                    "label": {
                                        "show": true,
                                        "position": "top",
                                        formatter: function(p) {
                                            return p.value > 0 ? (p.value) : '';
                                        }
                                    }
                                }
                            },
                            "data":totalNum
                        },
                    ]
                };
                if(myChart) myChart.dispose();
                myChart = echarts.init(element);
                myChart.setOption(option);
            }
            else if(chart === "1")
            {
                let option;
                option = {
                    backgroundColor: "#344b58",
                    title: {
                        text: country+'奖牌饼状图',
                        subtext: '项目'+sport,
                        x:"40%",
                        left: 'center',
                        textStyle: {
                            color: '#fff',
                            fontSize: '22'
                        },
                        subtextStyle: {
                            color: '#90979c',
                            fontSize: '16',
                        },
                    },
                    tooltip: {
                        trigger: 'item',
                        axisPointer: {
                            type:"shadow",
                            textStyle:{
                                color: "#fff"
                            }
                        }
                    },
                    legend: {
                        x: '4%',
                        top: '8%',
                        textStyle: {
                            color: '#90979c',
                        },
                        "data": ['金牌','银牌','铜牌']
                    },
                    series: [{
                        name: '奖牌分布',
                        type: 'pie',
                        radius: '50%',
                        data: [
                            { value: goldTotal, name: '金牌' },
                            { value: silverTotal, name: '银牌' },
                            { value: bronzeTotal, name: '铜牌' },
                        ],
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                    ]
                };
                if(myChart) myChart.dispose();
                myChart = echarts.init(element);
                myChart.setOption(option);
            }
        }
    };

    xmlHttpRequest.open("Get", "/Info/"+country+"/"+sport, true);
    xmlHttpRequest.send();
}
getChartsModel("Australia","Athletics","0");
