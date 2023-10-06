// 根据下拉框的选择更新该国家该项目的前5的运动员
function getAthleteBySport(country,sport)
{
    let xmlHttpRequest  = new XMLHttpRequest();

    xmlHttpRequest.onreadystatechange=function ()
    {
        if(xmlHttpRequest.readyState===4&&xmlHttpRequest.status===200) {
            let names = [];
            let goldNum = [];
            let silverNum = [];
            let bronzeNum = [];
            let score = [];
            let content = xmlHttpRequest.responseText;
            content = JSON.parse(content);
            for (let item of content) {
                names.push(item.name);
                goldNum.push(item.goldNum);
                silverNum.push(item.silverNum);
                bronzeNum.push(item.bronzeNum);
                score.push(item.score);
            }
            const colors = ['#FFD700', '#C0C0C0','#B47440','#1E90FF' ];
            option = {
                color: colors,
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'cross'
                    }
                },
                grid: {
                    right: '20%'
                },
                toolbox: {
                    feature: {
                        dataView: { show: true, readOnly: false },
                        restore: { show: true },
                        saveAsImage: { show: true }
                    }
                },
                legend: {
                    data: ['Gold', 'Silver', 'Bronze','Score']
                },
                xAxis: [
                    {
                        type: 'category',
                        axisTick: {
                            alignWithLabel: true
                        },
                        // prettier-ignore
                        data: names
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        name: 'Gold',
                        position: 'right',
                        alignTicks: true,
                        axisLine: {
                            show: true,
                            lineStyle: {
                                color: colors[0]
                            }
                        },
                        axisLabel: {
                            formatter: '{value}'
                        }
                    },
                    {
                        type: 'value',
                        name: 'Silver',
                        position: 'right',
                        alignTicks: true,
                        offset: 80,
                        axisLine: {
                            show: true,
                            lineStyle: {
                                color: colors[1]
                            }
                        },
                        axisLabel: {
                            formatter: '{value}'
                        }
                    },
                    {
                        type: 'value',
                        name: 'Bronze',
                        position: 'left',
                        alignTicks: true,
                        axisLine: {
                            show: true,
                            lineStyle: {
                                color: colors[2]
                            }
                        },
                        axisLabel: {
                            formatter: '{value}'
                        }
                    }
                ],
                series: [
                    {
                        name: 'Gold',
                        type: 'bar',
                        data: goldNum
                    },
                    {
                        name: 'Silver',
                        type: 'bar',
                        yAxisIndex: 1,
                        data: silverNum
                    },
                    {
                        name: 'Bronze',
                        type: 'bar',
                        yAxisIndex: 2,
                        data: bronzeNum
                    },
                    {
                        name: 'Score',
                        type: 'line',
                        data: score
                    }
                ]
            };
            let element = document.getElementById("InfoGraph");
            element.style.width='1000px';
            element.style.height='500px';
            let myChart = echarts.getInstanceByDom(element);
            if(myChart) myChart.dispose();
            myChart = echarts.init(element,'dark');
            option && myChart.setOption(option);

            startIndex = continueIndex;
            getTopCharts();
        }
    }
    xmlHttpRequest.open("Get","/athlete/"+country+"/"+sport);
    xmlHttpRequest.send();
}
