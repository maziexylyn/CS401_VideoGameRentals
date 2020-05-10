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
        <a href="../index.jsp" class="brand-logo text1"><img src="../icon.png"><span> VGRentalZ</span></a>
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

<main>
    <div class="container" id="library-root">
    </div>

    <!-- Modal Structure -->
    <div id="game-modal" class="modal">
        <div class="modal-content">
            <div class="row">
                <div class="col s4">
                    <div class="card">
                        <div class="card-image">
                            <img id="modal-img" src="" alt="">
                        </div>
                    </div>
                </div>
                <div class="col s8">
                    <div class="row">
                        <div class="col s12">
                            <h4 id="modal-title"></h4>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col s12">
                            <div id="modal-platforms"></div>

                        </div>
                    </div>
                    <div class="row">
                        <div class="col s12">
                            <div id="modal-publisher" class="chip"></div>
                            <div id="modal-rating" class="chip"></div>
                            <div id="modal-genre" class="chip"></div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col s12">
                            <h6><u>Game Description</u></h6>
                            <p id="modal-description"></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <a href="#!" class="modal-close waves-effect waves-green btn-flat">Close</a>
        </div>
    </div>

</main>

<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<script src="../js/generic.js"></script>
<script src="../js/game-library.js"></script>
</body>

<!-- Footer container -->
<footer class="page-footer primary1">
    <div class="container">
        <div class="row">
            <div class="col l6 s12">
                <h5 class="white-text">Contact Us</h5>
                <p class="text1">
                    <u>VGRentalZ Office</u><br>
                    525 S Winchester Blvd<br>
                    San Jose, CA 95128<br><br>
                    Phone: (510) 123-4567<br>
                    Email: VGRentalZ-support@email.com<br>
                </p>
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
