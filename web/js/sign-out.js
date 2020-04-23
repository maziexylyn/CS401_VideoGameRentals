var xhttp = new XMLHttpRequest();
xhttp.open("GET", "../Auth.SignInOut", true);
xhttp.send();
xhttp.onreadystatechange = function() {
    if (this.readyState === 4 && this.status === 200) {
        localStorage.clear();
        window.location.href = "../index.jsp";
    }
};