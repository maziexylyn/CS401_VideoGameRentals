package Servlets.Auth;

import Classes.*;
import db.DB;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Servlets.Auth.Register")
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String card = request.getParameter("card");
        String address = request.getParameter("address");

        ResponsePackage rp = new ResponsePackage();

        if(     Validation.checkEmail(email) &&
                Validation.checkPassword(password) &&
                Validation.checkName(name) &&
                Validation.checkPhone(phone) &&
                Validation.checkCard(card) &&
                Validation.checkAddress(address)
        ){
            rp = register(email,password,name,phone,card,address);
        }
        
        response.setContentType("text/plain");
        response.getWriter().print(rp.formatData());
        response.setStatus(rp.getResponse());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected static ResponsePackage register(String email, String password, String name, String phone, String card, String address){
        ResponsePackage rp = new ResponsePackage();
        try{
            DB db = new DB();

            if(db.openDB()){

                if(!User.existsByEmail(db.getConn(), email)){
                    User.create(db.getConn(), email, BCrypt.hashpw(password, BCrypt.gensalt()), Role.Type.CUSTOMER );
                }

                User user = User.readByEmail(db.getConn(), email);

                if(!Customer.existsByUserID(db.getConn(), user.getId())) {
                    if(Customer.create(db.getConn(), name,phone,card,address,user.getId())) {
                        rp.setMsgResponse(ResponsePackage.Status.OK);
                    }else {
                        rp.setMsgResponse(ResponsePackage.Status.NOT_IMPLEMENTED);
                    }
                }

                db.closeDB();
            }

        }catch(Exception err){
            err.printStackTrace();
        }
        return rp;
    }

}
