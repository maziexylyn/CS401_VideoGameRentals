<%@ page import="Classes.Role" %>
<%@ page import="Classes.Validation" %><%--
  Created by IntelliJ IDEA.
  Classes.User: Mazie
  Date: 4/17/2020
  Time: 8:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String activity, activityText;
    Role.Type role = Role.Type.CUSTOMER;
    boolean isSignedIn = false;
    if (session.getAttribute("email") != null && session.getAttribute("email") != "") {
        activity = "../jsp/sign-out.jsp";
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
        activity = "../jsp/signin-register.jsp";
        activityText = "Sign In";
    }

%>
<html>
<head>
    <meta charset="UTF-8">
    <title>VGRentalZ</title>
    <link href="../favicon.ico" rel="icon" type="image/x-icon" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="../css/main.css">
</head>
<body class = "Site">
<nav>
    <div class="nav-wrapper primary1">
        <a href="../index.jsp" class="brand-logo text1">VGRentalZ</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <% if(role.equals(Role.Type.ADMIN)){ %>
            <li>
                <a href="../jsp/admin/admin-home.jsp">Admin</a>
            </li>
            <% } %>
            <% if(role.equals(Role.Type.MANAGER)){ %>
            <li>
                <a href="../jsp/manager/manager-home.jsp">Admin</a>
            </li>
            <% } %>
            <li><a href="../jsp/game-library.jsp">
                Game Library</a></li>
            <li><a href="../jsp/contact-us.jsp">Contact Us</a></li>
            <li><a href="<%=activity%>" ><%=activityText%></a></li>
            <% if(role.equals(Role.Type.CUSTOMER) && isSignedIn){ %>
            <li><a href="../jsp/customer/shopping-cart.jsp">
                <i class="small material-icons">shopping_cart</i></a>
            </li>
            <% } %>
        </ul>
    </div>
</nav>
<br><br>

<div class="container" id="library-root">


<%--    <div class="row">--%>

<%--        <div class="col s4">--%>
<%--            <div class="card">--%>
<%--                <div class="card-image">--%>
<%--                    <img src="https://upload.wikimedia.org/wikipedia/en/1/1f/Animal_Crossing_New_Horizons.jpg" alt="" onClick=loadModal(game_id)>--%>
<%--                    <span class="card-title red"><b>$59.99</b></span>--%>
<%--                    <a class="btn-floating btn-large halfway-fab waves-effect waves-light red"><i class="material-icons">add_shopping_cart</i></a>--%>
<%--                </div>--%>
<%--                <div class="card-content">--%>
<%--                    <h5>Animal Crossing New Horizons</h5>--%>
<%--                    <div class="chip">--%>
<%--                        <img src="https://p7.hiclipart.com/preview/326/357/198/nintendo-switch-super-nintendo-entertainment-system-%E4%BB%BB%E5%A4%A9%E5%A0%82switch%E5%9C%A8%E7%BA%BF%E6%9C%8D%E5%8A%A1-video-games-nintendo.jpg" alt="img">--%>
<%--                        Nintendo Switch--%>
<%--                    </div>--%>
<%--                    <div class="chip">--%>
<%--                        Simulator--%>
<%--                    </div>--%>
<%--                    <div class="chip">--%>
<%--                        Rated E--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>

<%--    </div>--%>


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
