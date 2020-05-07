package Servlets.Publisher;

import Classes.Platform;
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
public class Create extends javax.servlet.http.HttpServlet{
    public static void main(String[] args){
        String name = "MazieBox";
        ResponsePackage rp = Create.createPublisherName(name);
        System.out.println(rp.formatData());
        System.out.println(rp.getResponse());
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String publisher_name = request.getParameter("name");
        ResponsePackage rp = new ResponsePackage();


        if(Validation.checkPublisherName(publisher_name))
        {
            rp = createPublisherName(publisher_name);


        }
        response.setContentType("text/plain");
        response.getWriter().print(rp.formatData());
        response.setStatus(rp.getResponse());


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    private static ResponsePackage createPublisherName(String publisher_name) {
        ResponsePackage rp2 = new ResponsePackage();
        /*
        try {
            DB db = new DB();

            if (db.openDB()) {

                boolean isCreated = Publisher.create(db.getConn(), publisher_name);
                db.closeDB();

                if (isCreated) {
                    rp2.setMsgResponse(ResponsePackage.Status.OK);
                } else {
                    rp2.setMsgResponse(ResponsePackage.Status.NOT_IMPLEMENTED);
                }
            }
        } catch(Exception err){
            err.printStackTrace();
        }

         */
        return rp2;
    }

}
