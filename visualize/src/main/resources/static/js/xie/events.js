let button = document.getElementById("CheckButton");
let overlay = document.querySelector(".overlay");
let container = document.querySelector(".button-container");

overlay.addEventListener("click", function(event) {
    // 阻止默认行为
    event.preventDefault();
    // 阻止事件冒泡
    event.stopPropagation();

    getAthleteBySport(document.getElementById('initCountry').value,document.getElementById('initSport').value);
});
