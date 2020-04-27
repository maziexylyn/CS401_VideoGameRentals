package Auth;

import Classes.Customer;
import Classes.User;
import db.DB;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Auth.Register")
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String card = request.getParameter("card");
        String address = request.getParameter("address");

        int status = response.SC_NOT_ACCEPTABLE;
        String msg = "Invalid information";

        if(     Validation.checkEmail(email) &&
                Validation.checkPassword(password) &&
                Validation.checkName(name) &&
                Validation.checkPhone(phone) &&
                Validation.checkCard(card) &&
                Validation.checkAddress(address)
        ){

            DB db = new DB();

            if(db.openDB()){
                User user = User.readUserByEmail(db.getConn(), email);

                if(user == null){
                    user = User.createUser(db.getConn(), email, BCrypt.hashpw(password, BCrypt.gensalt()));
                }

                if(user != null){

                    Customer customer = Customer.readCustomerByUserID(db.getConn(), user.getUserID());

                    if(customer == null){
                        customer = Customer.createCustomer(db.getConn(), name, phone, card, address, user.getUserID());
                        HttpSession session = request.getSession();
                        session.setAttribute("email", email);
                        session.setAttribute("isAdmin", user.isAdmin());

                        status = response.SC_OK;
                        msg = "Customer registered successfully";
                    }else{
                        status = response.SC_FOUND;
                        msg = "Customer already registered";
                    }

                }else{
                    status = response.SC_NOT_FOUND;
                    msg = "User not found.";
                }

                db.closeDB();
            }else{
                status = response.SC_INTERNAL_SERVER_ERROR;
                msg = "Internal Server Error";
            }
        }
        
        response.setContentType("text/plain");
        response.getWriter().print(msg);
        response.setStatus(status);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
