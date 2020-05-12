M.AutoInit();

function toggleSignIn(){
    document.getElementById("signin_form").hidden = !document.getElementById("signin_form").hidden;
    document.getElementById("register_form").hidden = !document.getElementById("register_form").hidden;
}


function isNameValid(name){
    let namePattern = /[\w][\w\s]+/;
    return namePattern.test(name);
}

function isEmailValid(email){
    let emailPattern = /[\w\d\S]+[@][\w\d\S]+[.][\w\d\s]+/;
    return emailPattern.test(email);
}

function isPasswordValid(password){
    let passwordPattern = /[\w\d\S!@#$%^&*()?]{8,}/;
    return passwordPattern.test(password);
}

function isCardValid(card){
    let cardPattern = /[\d]{16}/;
    return cardPattern.test(card);
}

function isAddressValid(address){
    let addressPattern = /[\d]+[\w\d\s#]+[,][\w\d\s]+[,][\s]?[A-Z]{2}[,][\s]?[0-9]{5}/;
    return addressPattern.test(address);
}

function isPhoneValid(phone){
    let phonePattern = /[\d]{10}/;
    return phonePattern.test(phone);
}

function validateSignIn(){

    let email_error = document.getElementById("signin_email_error");
    let password_error = document.getElementById("signin_password_error");

    email_error.innerText = '';
    email_error.hidden = true;

    password_error.innerText = '';
    password_error.hidden = true;

    let v1 = isEmailValid(document.getElementById("signin_email").value);
    let v2 = isPasswordValid(document.getElementById("signin_password").value);

    if(!v1){
        email_error.innerText = "* Invalid Email";
        email_error.hidden = false;
        document.getElementById("signin_email").classList.remove("valid");
        document.getElementById("signin_email").classList.add("invalid");
    }

    if(!v2){
        password_error.innerText = "* Invalid Password";
        password_error.hidden = false;
        document.getElementById("signin_password").classList.remove("valid");
        document.getElementById("signin_password").classList.add("invalid");
    }

    return (v1 && v2);
}

function validateRegister(){

    let name_error = document.getElementById("register_name_error");
    let email_error = document.getElementById("register_email_error");
    let password_error = document.getElementById("register_password_error");
    let card_error = document.getElementById("register_card_error");
    let address_error = document.getElementById("register_address_error");
    let phone_error = document.getElementById("register_phone_error");

    name_error.innerText = '';
    name_error.hidden = true;

    email_error.innerText = '';
    email_error.hidden = true;

    password_error.innerText = '';
    password_error.hidden = true;

    card_error.innerText = '';
    card_error.hidden = true;

    address_error.innerText = '';
    address_error.hidden = true;

    phone_error.innerText = '';
    phone_error.hidden = true;

    let v1 = isNameValid(document.getElementById("register_name").value);
    let v2 = isEmailValid(document.getElementById("register_email").value)
    let v3 = isPasswordValid(document.getElementById("register_password").value);
    let v4 = isCardValid(document.getElementById("register_card").value);
    let v5 = isAddressValid(document.getElementById("register_address").value);
    let v6 = isPhoneValid(document.getElementById("register_phone").value);

    if(!v1){
        name_error.innerText = "* Invalid Name";
        name_error.hidden = false;
        document.getElementById("register_name").classList.remove("valid");
        document.getElementById("register_name").classList.add("invalid");
    }

    if(!v2){
        email_error.innerText = "* Invalid Email";
        email_error.hidden = false;
        document.getElementById("register_email").classList.remove("valid");
        document.getElementById("register_email").classList.add("invalid");
    }

    if(!v3){
        password_error.innerText = "* Invalid Password\n" +
            "\t- Use at least 8 alphanumeric characters\n" +
            "\t- Allowed symbols: !@#$%^&*()?";
        password_error.hidden = false;
        document.getElementById("register_password").classList.remove("valid");
        document.getElementById("register_password").classList.add("invalid");
    }

    if(!v4){
        card_error.innerText = "* Invalid Card Number\n\te.g. 1234567812345678";
        card_error.hidden = false;
        document.getElementById("register_card").classList.remove("valid");
        document.getElementById("register_card").classList.add("invalid");
    }

    if(!v5){
        address_error.innerText = "* Invalid Shipping Address\n\te.g. 123 Home St #34, City, CA, 12345";
        address_error.hidden = false;
        document.getElementById("register_address").classList.remove("valid");
        document.getElementById("register_address").classList.add("invalid");
    }

    if(!v6){
        phone_error.innerText = "* Invalid Phone Number\n\te.g. 1234567890";
        phone_error.hidden = false;
        document.getElementById("register_phone").classList.remove("valid");
        document.getElementById("register_phone").classList.add("invalid");
    }

    return (v1 && v2 && v3 && v4 && v5 && v6);
}

function signIn(){
    if( validateSignIn() ) {

        var xhttp = new XMLHttpRequest();
        xhttp.open("POST", "../Servlets.Auth.SignInOut", true);
        xhttp.setRequestHeader('Content-Type', "application/x-www-form-urlencoded");
        xhttp.send(
            "email=" + document.getElementById("signin_email").value + "&" +
            "password=" + document.getElementById("signin_password").value
        );

        xhttp.onreadystatechange = function () {
            console.log(this.responseText);
            if (this.readyState === 4 && this.status === 200) {
                window.location.href = "../index.jsp";
            } else if (this.readyState === 4) {
                M.toast({html: JSON.parse(this.responseText).msg})
            }
        };

    }
}

function register(){
    if( validateRegister() ){

        var xhttp = new XMLHttpRequest();
        xhttp.open("POST", "../Servlets.Auth.Register", true);
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
                toggleSignIn();
                M.toast({html: "Registration complete! Please sign in."})
            }else if(this.readyState === 4){
                M.toast({html: JSON.parse(this.responseText).msg})
            }
        }

    }
}