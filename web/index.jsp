<%@ page import="Classes.Role" %>
<%@ page import="Classes.Validation" %><%--
  Created by IntelliJ IDEA.
  Classes.User: Mazie
  Date: 4/17/2020
  Time: 4:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String activity, activityText;
    Role.Type role = Role.Type.CUSTOMER;
    boolean isSignedIn = false;
    if (session.getAttribute("email") != null && session.getAttribute("email") != "") {
        activity = "jsp/sign-out.jsp";
        activityText = "Sign Out";
        String temp = session.getAttribute("role").toString();
        if(Validation.checkRoleCode(temp)){
            int roleCode = Integer.parseInt(temp);
            if(Role.Type.codeExists(roleCode)){
                role = Role.Type.getType(roleCode);
                isSignedIn = true;
            }
        }
    } else {
        activity = "jsp/signin-register.jsp";
        activityText = "Sign In";
    }
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>VGRentalZ</title>
    <link href="favicon.ico" rel="icon" type="image/x-icon" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">  <!-- icon usage -->
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<nav>
    <div class="nav-wrapper primary1">
        <a href="index.jsp" class="brand-logo text1">VGRentalZ</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <% if(role.equals(Role.Type.ADMIN)){ %>
            <li>
                <a href="jsp/admin/admin-home.jsp">Admin</a>
            </li>
            <% } %>
            <% if(role.equals(Role.Type.MANAGER)){ %>
            <li>
                <a href="jsp/manager/manager-home.jsp">Admin</a>
            </li>
            <% } %>
            <li><a href="jsp/game-library.jsp">
                Game Library</a></li>
            <li><a href="jsp/contact-us.jsp">Contact Us</a></li>
            <li><a href="<%=activity%>" ><%=activityText%></a></li>
            <% if(role.equals(Role.Type.CUSTOMER) && isSignedIn){ %>
            <li><a href="jsp/customer/shopping-cart.jsp">
                <i class="small material-icons">shopping_cart</i></a>
            </li>
            <% } %>
        </ul>
    </div>
</nav>

<br><br>
<!-- Banner container -->
<div class="container hide-on-small-only">
    <div class="slider">
        <ul class="slides">
            <li>
                <!-- cod modern warfare -->
                <img src="https://cdn.wccftech.com/wp-content/uploads/2019/10/MW-PC-SPECS-TOUT.jpg">
            </li>
            <li>
                <!-- zelda -->
                <img src="https://www.nintendo.com/content/dam/noa/en_US/games/switch/t/the-legend-of-zelda-breath-of-the-wild-switch/the-legend-of-zelda-breath-of-the-wild-switch-hero.jpg">
            </li>
            <li>
                <!-- red dead redemption -->
                <img src="https://i0.wp.com/www.nerdpool.it/wp-content/uploads/2019/11/red-dead-redemption-2-avra-dlc-single-player-risposta-rockstar-games-v3-402152.jpg?resize=1068%2C601&ssl=1">
            </li>
            <li>
                <!-- pokemon eevee -->
                <img src="https://i.ytimg.com/vi/X2YfNeBxGLg/maxresdefault.jpg">
            </li>
            <li>
                <!-- final fantasy -->
                <img src="https://steam.cryotank.net/wp-content/gallery/finalfantasyxx2hd/Final-Fantasy-X-X-2-HD-Remaster-06-HD.png">
            </li>
        </ul>
    </div>
</div>
<br>
<!-- Main container -->
<div class="container hide-on-small-only">

    <div class="row">
        <div class="col s4">
            <!-- Basic card: about the company -->
            <div class="card primary1">
                <div class="card-content white-text">
                    <span class="card-title text1"><i class="small material-icons">gamepad</i> Gaming with VGRentalZ</span>
                    <p>
                        Our goal is to provide you with a wide variety of video games at an affordable price!<br><br>
                        STANDARD FEATURES:<br>
                        --------------------------<br>
                        - Only $2 a week<br>
                        - No late fees! Pay weekly until you own the Classes.game or return it!<br>
                        - 2 games per customer MAX at a time<br>
                        - Free shipping in 3-5 business days<br><br>
                        PREMIUM FEATURES:<br>
                        -----------------------<br>
                        - Only $10 a month<br>
                        - $1.50/week instead of $2/week for standard members<br>
                        - 4 games per customer MAX at a time<br>
                        - Free 2-day shipping guarantee!
                    </p>
                </div>
                <div class="card-action">
                    <a href="#">This is a link</a>
                    <a href="#">This is a link</a>
                </div>
            </div>
        </div>

        <!-- Card: Classes.game library -->
        <div class="col s4">
            <div class="card primary1">
                <div class="card-image">
                    <img src="https://c1.wallpaperflare.com/preview/184/835/736/arcade-game-pacman-retro.jpg">
<%--                    <img src="https://miro.medium.com/max/3200/0*Pyrf3so4KxTQwMto">--%>
                    <span class="card-title">Game Library</span>
                </div>
                <div class="card-content">
                    <span class = "text1"> We carry games for the following gaming consoles:<br>
                        - Xbox One<br>
                        - PS4<br>
                        - Nintendo Switch<br>
                    </span>
                </div>
                <div class="card-action">
                    <a href="jsp/game-library.jsp">Browse Game Library</a>
                </div>
            </div>
        </div>

        <!-- Card: registration -->
        <div class="col s4">
            <div class="card primary1">
                <div class="card-image">
                    <img src="https://png.pngtree.com/thumb_back/fw800/background/20190903/pngtree-dark-abstract-background-with-black-image_313626.jpg">
                    <span class="card-title">Sign Up</span>
                </div>
                <div class="card-content">
                    <span class = "text1"> Register now!!!<br>
                    </span>
                </div>
                <div class="card-action">
                    <a href="jsp/game-library.jsp">Browse Game Library</a>
                </div>
            </div>
        </div>
    </div>

</div>

<!--    <script-->
<!--            src="https://code.jquery.com/jquery-3.4.1.slim.min.js"-->
<!--            integrity="sha256-pasqAKBDmFT4eHoN2ndd6lN370kFiGUFyTiUHWhU7k8="-->
<!--            crossorigin="anonymous"></script>-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<script src="js/generic.js"></script>
<script src="js/index.js"></script>
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
            <a class="text1 right" href="index.jsp">VGRentalZ</a>
        </div>
    </div>
</footer>

</html>
