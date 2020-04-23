<%--
  Created by IntelliJ IDEA.
  Classes.User: Mazie
  Date: 4/17/2020
  Time: 8:08 PM
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
            <li><a href="signin-register.jsp">Sign In</a></li>
            <li><a href="../html/vgrFrontPage.html">
                <i class="small material-icons">shopping_cart</i></a>
            </li>
        </ul>
    </div>
</nav>
<br><br>
<div class="container hide-on-small-only">
    <div class="row">
        <div class="col s4">
            <div class="card">
                <div class="card-image">
                    <img src="https://media.gamestop.com/i/gamestop/10141904/The-Legend-of-Zelda-Breath-of-the-Wild?$pdp$">
                    <span class="card-title">Card Title</span>
                    <a class="btn-floating halfway-fab waves-effect waves-light red"><i class="material-icons">add</i></a>
                </div>
                <div class="card-content">
                    <p>I am a very simple card. I am good at containing small bits of information. I am convenient because I require little markup to use effectively.</p>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<script src="../js/generic.js"></script>
<script src="../js/game-library.js"></script>
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
