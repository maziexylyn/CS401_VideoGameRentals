package Servlets.Auth;




import Classes.Platform;
import Classes.ResponsePackage;
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
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        ResponsePackage rp = new ResponsePackage();

        if(Validation.checkEmail(email) && Validation.checkPassword(password)){

            DB db = new DB();
            if(db.openDB()){

                if(User.existsByEmail(db.getConn(), email)){

                    User user = User.readByEmail(db.getConn(), email);
                    db.closeDB();

                    if(BCrypt.checkpw(password, user.getPasswordHash())){

                        HttpSession session = request.getSession();
                        session.setAttribute("email", email);
                        session.setAttribute("role", user.getRole_id());

                        rp.setMsgResponse(ResponsePackage.Status.OK);
                        rp.setMsg("User credentials valid");
                    }else{
                        rp.setMsgResponse(ResponsePackage.Status.UNAUTHORIZED);
                        rp.setMsg("Email and password combination is invalid");
                    }

                }else{
                    rp.setMsgResponse(ResponsePackage.Status.NOT_FOUND);
                    rp.setMsg("Email does not exist");
                }

            }else{
                rp.setMsgResponse(ResponsePackage.Status.INTERNAL_SERVER_ERROR);
                rp.setMsg("Internal Server Error");
            }
        }

        response.setContentType("text/plain");
        response.getWriter().print(rp.formatData());
        response.setStatus(rp.getResponse());

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
