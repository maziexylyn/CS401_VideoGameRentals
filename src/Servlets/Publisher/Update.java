package Servlets.Publisher;

import Classes.Publisher;
import Classes.ResponsePackage;
import Classes.Validation;
import db.DB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Servlets.Publisher.Update")

public class Update extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String publisher_id_string = request.getParameter("id");
        String publisher_name = request.getParameter("name");
        String publisher_isActive = request.getParameter("isActive");

        ResponsePackage rp = new ResponsePackage();

        if(Validation.checkPublisherName(publisher_name) && Validation.checkID(publisher_id_string) && Validation.checkBoolean(publisher_isActive)){
            rp = updatePublisher(Integer.parseInt(publisher_id_string), publisher_name, Boolean.parseBoolean(publisher_isActive));

        }

        response.setContentType("text/plain");
        response.getWriter().print(rp.formatData());
        response.setStatus(rp.getResponse());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private static ResponsePackage updatePublisher(int publisher_id, String publisher_name, boolean publisher_isActive){
        ResponsePackage rp = new ResponsePackage();

        try{
            DB db = new DB();

            if(db.openDB()){
                boolean isUpdated = Publisher.update(db.getConn(), publisher_id, publisher_name, publisher_isActive);
                db.closeDB();

                if(isUpdated){
                    rp.setMsgResponse(ResponsePackage.Status.OK);
                }else{
                    rp.setMsgResponse(ResponsePackage.Status.NOT_MODIFIED);
                }
            }
        }catch(Exception err){
            err.printStackTrace();
        }

        return rp;
    }
}
