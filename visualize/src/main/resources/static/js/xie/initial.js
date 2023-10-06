// 根据已有数据数据初始化下拉框
// 初始化国家和该国家参与的体育项目
function initCountries()
{
    let initial = new XMLHttpRequest();
    initial.onreadystatechange=function ()
    {
        if(initial.readyState===4&&initial.status===200) {
            let content = initial.responseText;
            content = JSON.parse(content);
            let selector = document.getElementById("initCountry");
            for(let i of content)
            {
                selector.innerHTML=selector.innerHTML+"<option value=\'"+i+"\'>"+i+"</option>";
            }
            initSports(content[0]);
        }
    }
    initial.open("Get", "/initial/country", true);

    initial.send();
}
function initSports(country)
{
    let initial = new XMLHttpRequest();
    initial.onreadystatechange=function ()
    {
        if(initial.readyState===4&&initial.status===200) {
            let content = initial.responseText;
            content = JSON.parse(content);
            let selector = document.getElementById("initSport");
            for(let i of content)
            {
                selector.innerHTML=selector.innerHTML+"<option value=\""+i+"\">"+i+"</option>";
            }
        }
    }
    initial.open("Get", "/initial/country/"+country, true);
    initial.send();
}
initCountries();
startIndex = continueIndex;
getTopCharts();

