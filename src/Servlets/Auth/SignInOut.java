package Servlets.Auth;




import Classes.User;
import Classes.Validation;
import db.DB;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;


@WebServlet("/Servlets.Auth.SignInOut")
public class SignInOut extends HttpServlet {

    // Sign In
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("In SignIn Post");
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        int status = response.SC_NOT_ACCEPTABLE;
        String msg = "Invalid information";

        if(Validation.checkEmail(email) && Validation.checkPassword(password)){

            DB db = new DB();
            if(db.openDB()){

                User user = User.readUserByEmail(db.getConn(), email);
                db.closeDB();

                if(user != null){
                    if(BCrypt.checkpw(password, user.getPasswordHash())){

                        HttpSession session = request.getSession();
                        session.setAttribute("email", email);
                        session.setAttribute("isAdmin", user.isAdmin());

                        status = response.SC_OK;
                        msg = "User credentials valid";

                    }else{
                        status = response.SC_UNAUTHORIZED;
                        msg = "Email and password combination is invalid";
                    }
                }else{
                    status = response.SC_NOT_FOUND;
                    msg = "Email does not exist";
                }
            }else{
                status = response.SC_INTERNAL_SERVER_ERROR;
                msg = "Internal Server Error";
            }
        }

        response.setContentType("text/plain");
        response.getWriter().print(msg);
        response.setStatus(status);

    }

    // Sign Out
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Enumeration e = session.getAttributeNames();
        while (e.hasMoreElements()) {
            session.setAttribute(e.nextElement().toString(), "");
        }
        int status = response.SC_OK;
        response.setStatus(status);
    }

}
