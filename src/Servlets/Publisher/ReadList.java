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

@WebServlet("/Servlets.Publisher.ReadList")

public class ReadList extends HttpServlet {

    public static void main(String[] args){
        boolean publisher_isActive = true;
        ResponsePackage rp = readListPublisher(publisher_isActive);
        System.out.println(rp.formatData());
        System.out.println(rp.getResponse());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String publisher_isActive = request.getParameter("isActive");

        ResponsePackage rp = new ResponsePackage();

        if(Validation.checkBoolean(publisher_isActive)){
            rp = readListPublisher(Boolean.parseBoolean(publisher_isActive));

        }

        response.setContentType("text/plain");
        response.getWriter().print(rp.formatData());
        response.setStatus(rp.getResponse());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private static ResponsePackage readListPublisher(boolean publisher_isActive) {
        ResponsePackage rp = new ResponsePackage();

        try{
            DB db = new DB();

            if(db.openDB()) {
                Publisher[] publisher = Publisher.readList(db.getConn(), publisher_isActive);
                db.closeDB();

                if (publisher.length > 0) {
                    Gson gson = new Gson();
                    rp.setData(gson.toJson(publisher));
                    rp.setMsgResponse(ResponsePackage.Status.OK);
                } else {
                    rp.setMsgResponse(ResponsePackage.Status.NOT_FOUND);
                }
            }
        }catch(Exception err){
            err.printStackTrace();
        }

        return rp;
    }
}
