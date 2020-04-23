M.AutoInit();

function toggleSignIn(){
    document.getElementById("signin_form").hidden = !document.getElementById("signin_form").hidden;
    document.getElementById("register_form").hidden = !document.getElementById("register_form").hidden;
}

function validateSignIn(){
    let v1 = document.getElementById("signin_email").classList.contains("valid");
    let v2 = document.getElementById("signin_password").classList.contains("valid");

    return (v1 && v2);
}

function validateRegister(){
    let v1 = document.getElementById("register_name").classList.contains("valid");
    let v2 = document.getElementById("register_email").classList.contains("valid");
    let v3 = document.getElementById("register_password").classList.contains("valid");
    let v4 = document.getElementById("register_card").classList.contains("valid");
    let v5 = document.getElementById("register_address").classList.contains("valid");
    let v6 = document.getElementById("register_phone").classList.contains("valid");

    return (v1 && v2 && v3 && v4 && v5 && v6);
}

function signIn(){
    if( validateSignIn() ){

        var xhttp = new XMLHttpRequest();
        xhttp.open("POST", "../Auth.SignInOut", true);
        xhttp.setRequestHeader('Content-Type', "application/x-www-form-urlencoded");
        xhttp.send(
            "email="+document.getElementById("signin_email").value + "&" +
            "password=" + document.getElementById("signin_password").value
        );

        xhttp.onreadystatechange = function() {
            if (this.readyState === 4 && this.status === 200) {
                window.location.href = "../index.jsp";
            }else if(this.readyState === 4){
                M.toast({html: this.responseText})
                console.log(this.responseText);
            }
        };


        console.log("sent request");

    }else{
        // give error msg
    }
}

function register(){
    if( validateRegister() ){

        var xhttp = new XMLHttpRequest();
        xhttp.open("POST", "../Auth.Register", true);
        xhttp.setRequestHeader('Content-Type', "application/x-www-form-urlencoded");
        xhttp.send(
            "email="+document.getElementById("register_email").value + "&" +
            "name="+document.getElementById("register_name").value + "&" +
            "phone="+document.getElementById("register_phone").value + "&" +
            "address="+document.getElementById("register_address").value + "&" +
            "card="+document.getElementById("register_card").value + "&" +
            "password=" + document.getElementById("register_password").value
        );

        xhttp.onreadystatechange = function() {
            if(this.readyState === 4 && this.status === 200) {
                window.location.href = "../index.jsp";
            }else if(this.readyState === 4){
                M.toast({html: this.responseText})
                console.log(this.responseText);
            }
        }

    } else {
        // give error msg
    }
}