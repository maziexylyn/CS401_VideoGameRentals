M.AutoInit();

document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.datepicker');
    var instances = M.Datepicker.init(elems, {});
});

function changeIt(){
    document.getElementById("testMazie").innerText = "HELLO MAZIE!!!";
}


