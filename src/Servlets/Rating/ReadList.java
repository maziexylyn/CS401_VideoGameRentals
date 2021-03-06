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

@WebServlet("/Servlets.Rating.ReadList")

public class ReadList extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rating_isActive = request.getParameter("isActive");

        ResponsePackage rp = new ResponsePackage();


        if(Validation.checkBoolean(rating_isActive)){
            rp = readListRating(Validation.convertToBoolean(rating_isActive));
        }

        response.setContentType("text/plain");
        response.getWriter().print(rp.formatData());
        response.setStatus(rp.getResponse());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * Reads list of ratings
     * @param rating_isActive Filters for active/ianctive games
     * @return ResponsePackage object
     */
    protected static ResponsePackage readListRating(boolean rating_isActive) {
        ResponsePackage rp = new ResponsePackage();

        try{
            DB db = new DB();

            if(db.openDB()) {
                Rating[] rating = Rating.readList(db.getConn(), rating_isActive);
                db.closeDB();

                if (rating.length > 0) {
                    Gson gson = new Gson();
                    rp.setData(gson.toJson(rating));
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
