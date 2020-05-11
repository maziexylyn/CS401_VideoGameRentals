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
import java.net.http.HttpResponse;

@WebServlet("/Servlets.Rating.Create")

public class Create extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rating_name = request.getParameter("name");

        ResponsePackage rp = new ResponsePackage();

        if(Validation.checkRatingName(rating_name)){
            rp = createRating(rating_name);
        }

        response.setContentType("text/plain");
        response.getWriter().print(rp.formatData());
        response.setStatus(rp.getResponse());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * Creates game rating
     * @param rating_name Rating's name
     * @return ResponsePackage object
     */
    protected static ResponsePackage createRating(String rating_name){
        ResponsePackage rp = new ResponsePackage();

        try{
            DB db = new DB();

            if(db.openDB()){

                boolean isCreated = Rating.create(db.getConn(), rating_name);
                db.closeDB();

                if(isCreated){
                    rp.setMsgResponse(ResponsePackage.Status.OK);
                }else{
                    rp.setMsgResponse(ResponsePackage.Status.NOT_IMPLEMENTED);
                }
            }

        }catch(Exception err){
            err.printStackTrace();
        }

        return rp;

    }
}
