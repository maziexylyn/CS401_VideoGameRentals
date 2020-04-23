<%--
  Created by IntelliJ IDEA.
  Classes.User: Mazie
  Date: 4/18/2020
  Time: 3:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>VGRentalZ</title>
    <link href="../favicon.ico" rel="icon" type="image/x-icon" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="../css/main.css">
</head>
<body>
<nav>
    <div class="nav-wrapper primary1">
        <a href="../index.jsp" class="brand-logo text1">VGRentalZ</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li><a href="game-library.jsp">Game Library</a></li>
            <li><a href="../html/vgrFrontPage.html">Contact Us</a></li>
            <li class="active" ><a href="signin-register.jsp">Sign In</a></li>
            <li><a href="../html/vgrFrontPage.html">
                <i class="small material-icons">shopping_cart</i></a>
            </li>
        </ul>
    </div>
</nav>
<br>
<div class="container">
<!-- Card: Sign in -->
    <div id="signin_form" class="row center-align">
        <div class="col s12 m8 l6 offset-m2 offset-l3">
            <div class="card primary1">
                <div class="card-content white-text">
                    <span class="card-title">Login</span>
                    <div class="row">
                        <div class="input-field col s12">
                            <i class="material-icons prefix text1">mail_outline</i>
                            <input id="signin_email" type="email" class="validate white-text">
                            <label for="signin_email">E-mail</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <i class="material-icons prefix text1">lock_outline</i>
                            <input id="signin_password" type="password" class="validate white-text">
                            <label for="signin_password">Password</label>
                        </div>
                    </div>
                </div>
                <div class="card-action">
                    <a class="waves-effect waves-light btn" onclick="signIn()">Submit</a>
                    <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
                    <a href="#" onclick="toggleSignIn()">Register</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Card: Register -->
    <div id="register_form" class="row center-align" hidden>
        <div class="col s12 m8 l6 offset-m2 offset-l3">
            <div class="card primary1">
                <div class="card-content white-text">
                    <span class="card-title">Register</span>
                    <div class="row">
                        <div class="input-field col s12">
                            <i class="material-icons prefix text1">person_outline</i>
                            <input id="register_name" type="text" class="validate white-text">
                            <label for="register_name">Name</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <i class="material-icons prefix text1">mail_outline</i>
                            <input id="register_email" type="email" class="validate white-text">
                            <label for="register_email">E-mail</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <i class="material-icons prefix text1">phone</i>
                            <input id="register_phone" type="number" class="validate white-text">
                            <label for="register_phone">Phone</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <i class="material-icons prefix text1">home</i>
                            <input id="register_address" type="text" class="validate white-text">
                            <label for="register_address">Shipping Address</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <i class="material-icons prefix text1">credit_card</i>
                            <input id="register_card" type="number" class="validate white-text">
                            <label for="register_card">Card Number</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <i class="material-icons prefix text1">lock_outline</i>
                            <input id="register_password" type="password" class="validate white-text">
                            <label for="register_password">Password</label>
                        </div>
                    </div>
                </div>
                <div class="card-action">
                    <a class="waves-effect waves-light btn" onclick="register()">Submit</a>
                    <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
                    <a href="#" onclick="toggleSignIn()">Sign In</a>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<script src="../js/generic.js"></script>
<script src="../js/signin-register.js"></script>
</body>

<footer class="page-footer primary1">
    <div class="container">
        <div class="row">
            <div class="col l6 s12">
                <h5 class="white-text">Footer Content</h5>
                <p class="text1">You can use rows and columns here to organize your footer content.</p>
            </div>
            <div class="col l4 offset-l2 s12">
                <h5 class="white-text">Let Us Help You</h5>
                <ul>
                    <li><a class="grey-text text-lighten-3" href="#!">Your Account</a></li>
                    <li><a class="grey-text text-lighten-3" href="#!">Your Orders</a></li>
                    <li><a class="grey-text text-lighten-3" href="#!">Careers</a></li>
                    <li><a class="grey-text text-lighten-3" href="#!">Help</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="footer-copyright">
        <div class="container">
            <a class="text1 right" href="../index.jsp">VGRentalZ</a>
        </div>
    </div>
</footer>

</html>