package Servlets.Publisher;

import Classes.Publisher;
import Classes.ResponsePackage;
import Classes.Validation;
import com.google.gson.Gson;
import db.DB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;

@WebServlet("/Servlets.Publisher.Read")

public class Read extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String publisher_id_string = request.getParameter("id");

        ResponsePackage rp = new ResponsePackage();

        if(Validation.checkID(publisher_id_string)){
            rp = readPublisher(Integer.parseInt(publisher_id_string));
        }

        response.setContentType("text/plain");
        response.getWriter().print(rp.formatData());
        response.setStatus(rp.getResponse());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected static ResponsePackage readPublisher(int publisher_id){
        ResponsePackage rp = new ResponsePackage();

        try{
            DB db = new DB();

            if(db.openDB()){
                Publisher publisher = Publisher.read(db.getConn(),publisher_id);
                db.closeDB();

                if(publisher != null){
                    Gson gson = new Gson();
                    rp.setData(gson.toJson(publisher));
                    rp.setMsgResponse(ResponsePackage.Status.OK);
                }else{
                    rp.setMsgResponse(ResponsePackage.Status.NOT_FOUND);
                }
            }
        }catch(Exception err){
            err.printStackTrace();
        }

        return rp;
    }
}
