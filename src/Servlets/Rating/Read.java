package Servlets.Rating;

import Classes.Rating;
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

@WebServlet("/Servlets.Rating.Read")

public class Read extends HttpServlet {
    public static void main(String[] args){
        int rating_id = 1;
        ResponsePackage rp = readRating(rating_id);
        System.out.println(rp.formatData());
        System.out.println(rp.getResponse());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rating_id_string = request.getParameter("id");

        ResponsePackage rp = new ResponsePackage();


        if(Validation.checkID(rating_id_string)){
            rp = readRating(Integer.parseInt(rating_id_string));

        }

        response.setContentType("text/plain");
        response.getWriter().print(rp.formatData());
        response.setStatus(rp.getResponse());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    private static ResponsePackage readRating(int rating_id){
        ResponsePackage rp = new ResponsePackage();

        try{
            DB db = new DB();

            if(db.openDB()){
                Rating rating = Rating.read(db.getConn(),rating_id);
                db.closeDB();

                if(rating != null){
                    Gson gson = new Gson();
                    rp.setData(gson.toJson(rating));
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
