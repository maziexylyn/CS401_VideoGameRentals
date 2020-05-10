package Servlets.Rating;

import Classes.Rating;
import Classes.ResponsePackage;
import Classes.Validation;
import db.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Servlets.Rating.Update")

public class Update extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rating_id_string = request.getParameter("id");
        String rating_name = request.getParameter("name");
        String rating_isActive = request.getParameter("isActive");

        ResponsePackage rp = new ResponsePackage();

        if(Validation.checkRatingName(rating_name) && Validation.checkID(rating_id_string) && Validation.checkBoolean(rating_isActive)){
            rp = updateRating(Integer.parseInt(rating_id_string), rating_name, Validation.convertToBoolean(rating_isActive));

        }

        response.setContentType("text/plain");
        response.getWriter().print(rp.formatData());
        response.setStatus(rp.getResponse());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected static ResponsePackage updateRating(int rating_id, String rating_name, boolean rating_isActive){
        ResponsePackage rp = new ResponsePackage();

        try{
            DB db = new DB();

            if(db.openDB()){
                boolean isUpdated = Rating.update(db.getConn(), rating_id, rating_name, rating_isActive);
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
