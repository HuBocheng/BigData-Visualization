function drawMap()
{
    let dom = document.getElementById('worldMap');
    dom.style.height = '450px';
    dom.style.width = '95%';
    dom.style.margin = 'auto auto auto auto';
    console.log(dom);
    let myChart = echarts.getInstanceByDom(dom);
    if(myChart) myChart.dispose();
    myChart = echarts.init(dom);
    var geoCoordMap = {
        雅典:[23.43,37.58],
        巴黎:[2.25,48.52],
        圣路易斯:[-90.19833,38.62750],
        伦敦:[0.5,51.30,],
        斯德哥尔摩:[18.03535,59.1945],
        安特卫普:[4.41,51.22,],
        阿姆斯特丹:[4.89,52.37],
        洛杉矶:[-118.25,34.05,],
        柏林:[13.41,52.52],
        赫尔辛基:[60.17,24.94],
        墨尔本:[144.96,-37.81],
        罗马:[41.90,12.50],
        东京:[139.76,35.68],
        墨西哥城:[-99.13,19.43],
        慕尼黑:[48.14,11.58],
        蒙特利尔:[-73.56,45.50],
        莫斯科:[55.75,37.62],
        汉城:[126.97,37.56],
        巴塞罗那:[41.39,2.16],
        亚特兰大:[-84.39,33.75,],
        悉尼:[151.21,-33.87],
        北京:[116.41,39.9042],
        里约:[-43.17,-22.91]
    };
    var BJData = [
        [{
            name: "雅典",
            value: 9100
        }, {
            name: "巴黎"
        }],
        [{
            name: "巴黎",
            value: 9100
        }, {
            name: "圣路易斯"
        }],
        [{
            name: "圣路易斯",
            value: 2370
        }, {
            name: "伦敦"
        }],
        [{
            name: "伦敦",
            value: 3130
        }, {
            name: "斯德哥尔摩"
        }],
        [{
            name: "斯德哥尔摩",
            value: 2350
        }, {
            name: "安特卫普"
        }],
        [{
            name: "安特卫普",
            value: 5120
        }, {
            name: "巴黎"
        }],
        [{
            name: "巴黎",
            value: 3110
        }, {
            name: "阿姆斯特丹"
        }],
        [{
            name: "阿姆斯特丹",
            value: 6280
        }, {
            name: "洛杉矶"
        }],
        [{
            name: "洛杉矶",
            value: 7255
        }, {
            name: "柏林"
        }],
        [{
            name: "柏林",
            value: 8125
        }, {
            name: "伦敦"
        }],
        [{
            name: "伦敦",
            value: 3590
        }, {
            name: "赫尔辛基"
        }],
        [{
            name: "赫尔辛基",
            value: 3590
        }, {
            name: "墨尔本"
        }],
        [{
            name: "墨尔本",
            value: 3590
        }, {
            name: "罗马"
        }],
        [{
            name: "罗马",
            value: 3590
        }, {
            name: "东京"
        }],
        [{
            name: "东京",
            value: 3590
        }, {
            name: "墨西哥城"
        }],
        [{
            name: "墨西哥城",
            value: 3590
        }, {
            name: "慕尼黑"
        }],
        [{
            name: "慕尼黑",
            value: 3590
        }, {
            name: "蒙特利尔"
        }],
        [{
            name: "蒙特利尔",
            value: 3590
        }, {
            name: "莫斯科"
        }],
        [{
            name: "莫斯科",
            value: 3590
        }, {
            name: "洛杉矶"
        }],
        [{
            name: "洛杉矶",
            value: 3590
        }, {
            name: "汉城"
        }],
        [{
            name: "汉城",
            value: 3590
        }, {
            name: "巴塞罗那"
        }],
        [{
            name: "巴塞罗那",
            value: 3590
        }, {
            name: "亚特兰大"
        }],
        [{
            name: "亚特兰大",
            value: 3590
        }, {
            name: "悉尼"
        }],
        [{
            name: "悉尼",
            value: 3590
        }, {
            name: "雅典"
        }],
        [{
            name: "雅典",
            value: 3590
        }, {
            name: "北京"
        }],
        [{
            name: "北京",
            value: 3590
        }, {
            name: "里约"
        }]
    ];
    var convertData = function (data) {
        var res = [];
        for (var i = 0; i < data.length; i++) {
            var dataItem = data[i];
            var fromCoord = geoCoordMap[dataItem[0].name];
            var toCoord = geoCoordMap[dataItem[1].name];
            if (fromCoord && toCoord) {
                res.push([{
                    coord: fromCoord,
                    value: dataItem[0].value
                },
                    {
                        coord: toCoord
                    }
                ]);
            }
        }
        return res;
    };

    var series = [];
    [
        ["雅典", BJData]
    ].forEach(function (item, i) {
        series.push({
                type: "lines",
                zlevel: 2,
                effect: {
                    show: true,
                    period: 4, //箭头指向速度，值越小速度越快
                    trailLength: 0.02, //特效尾迹长度[0,1]值越大，尾迹越长重
                    symbol: "arrow", //箭头图标
                    symbolSize: 5 //图标大小
                },
                lineStyle: {
                    normal: {
                        width: 1, //尾迹线条宽度
                        opacity: 0, //尾迹线条透明度
                        curveness: 0.3 //尾迹线条曲直度
                    }
                },

                data: convertData(item[1])
            }, {
                type: "effectScatter",
                coordinateSystem: "geo",
                zlevel: 2,
                rippleEffect: {
                    //涟漪特效
                    period: 4, //动画时间，值越小速度越快
                    brushType: "stroke", //波纹绘制方式 stroke, fill
                    scale: 4 //波纹圆环最大限制，值越大波纹越大
                },
                label: {
                    normal: {
                        show: true,
                        position: "right", //显示位置
                        offset: [5, 0], //偏移设置
                        formatter: "{b}" //圆环显示文字
                    },
                    emphasis: {
                        show: true
                    }
                },
                symbol: "circle",
                symbolSize: function (val) {
                    return 4 + val[2] / 1000; //圆环大小
                },
                itemStyle: {
                    normal: {
                        show: false,
                    }
                },
                data: item[1].map(function (dataItem) {
                    return {
                        name: dataItem[0].name,
                        value: geoCoordMap[dataItem[0].name].concat([dataItem[0].value])
                    };
                })
            },
            //被攻击点
            {
                type: "scatter",
                coordinateSystem: "geo",
                zlevel: 2,
                rippleEffect: {
                    period: 4,
                    brushType: "stroke",
                    scale: 4
                },
                label: {
                    normal: {
                        show: true,
                        position: "right",
                        color: "#00ffff",
                        formatter: "{b}",
                        textStyle: {
                            color: "#0bc7f3"
                        }
                    },
                    emphasis: {
                        show: true
                    }
                },
                symbol: "pin",
                symbolSize: 30,
                itemStyle: {
                    normal: {
                        show: true,
                        color: "#9966cc"
                    }
                },
                data: [{
                    name: item[0],
                    value: geoCoordMap[item[0]].concat([100])
                }]
            }
        );
    });

    option = {
        backgroundColor: '#000',
        tooltip: {
            trigger: "item",
            backgroundColor: "#1540a1",
            borderColor: "#FFFFCC",
            showDelay: 0,
            hideDelay: 0,
            enterable: true,
            transitionDuration: 0,
            extraCssText: "z-index:100",
            formatter: function (params, ticket, callback) {
                //根据业务自己拓展要显示的内容
                var res = "";
                var name = params.name;
                var value = params.value[params.seriesIndex + 1];
                res =
                    "<span style='color:#fff;'>" +
                    name +
                    "</span><br/>数据：" +
                    value;
                return res;
            }
        },
        visualMap: {
            //图例值控制
            min: 0,
            max: 10000,
            show: false,
            calculable: true,
            color: ["#0bc7f3"],
            textStyle: {
                color: "#fff"
            },

        },
        geo: {
            map: "world",
            label: {
                emphasis: {
                    show: false
                }
            },
            roam: true, //是否允许缩放
            layoutCenter: ["50%", "50%"], //地图位置
            layoutSize: "180%",
            itemStyle: {
                normal: {
                    color: "#04284e", //地图背景色
                    borderColor: "#5bc1c9" //省市边界线
                },
                emphasis: {
                    color: "rgba(37, 43, 61, .5)" //悬浮背景
                }
            }
        },
        series: series
    };
    myChart.setOption(option);
    myChart.on('click', function (params) {
            if (params.name == "Australia" || params.name == "Bahamas" || params.name == "Belgium" || params.name == "Belarus" || params.name == "Brazil" ||
                params.name == "Bulgaria" || params.name == "Canada" || params.name == "China" || params.name == "Cuba" || params.name == "Czech Rep." ||
                params.name == "Spain" || params.name == "Ethiopia" || params.name == "Finland" || params.name == "France" || params.name == "Germany" ||
                params.name == "United Kingdom" || params.name == "Greece" || params.name == "Hungary" || params.name == "Italy" || params.name == "Jamaica" ||
                params.name == "Japan" || params.name == "Kenya" || params.name == "Morocco" || params.name == "Mexico" || params.name == "Netherlands" ||
                params.name == "Nigeria" || params.name == "Norway" || params.name == "New Zealand" || params.name == "Poland" || params.name == "Romania" ||
                params.name == "South Africa" || params.name == "Russia" || params.name == "Sweden" || params.name == "Slovakia"
                || params.name == "Trinidad and Tobago" || params.name == "Ukraine" || params.name == "United States") {
                //函数体
                console.log(params.name + " gets data");
                window.location.href = "http://localhost:721/finance?country="+params.name;
            }
            else console.log("this country hasn't gain data");
        }
    );
}
drawMap();
