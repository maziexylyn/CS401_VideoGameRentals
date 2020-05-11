package Servlets.Game;

import Classes.Game;
import Classes.Platform;
import Classes.ResponsePackage;
import Classes.Validation;
import db.DB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

@WebServlet("/Servlets.Game.Update")

public class Update extends HttpServlet {

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String imagePath = request.getParameter("imagePath");
        String publisher_id = request.getParameter("publisher_id");
        String genre_id = request.getParameter("genre_id");
        String rating_id = request.getParameter("rating_id");
        String currentPrice = request.getParameter("currentPrice");
        String isActive = request.getParameter("isActive");

        ResponsePackage rp = new ResponsePackage();

        if(
                Validation.checkGameTitle(title) &&
                        Validation.checkID(id) &&
                        Validation.checkGameDescription(description) &&
                        Validation.checkGameImagePath(imagePath) &&
                        Validation.checkID(publisher_id) &&
                        Validation.checkID(genre_id) &&
                        Validation.checkID(rating_id) &&
                        Validation.checkGamePrice(currentPrice) &&
                        Validation.checkBoolean(isActive)
        ) {
            rp = updateGame(Integer.parseInt(id), title, description, imagePath, Integer.parseInt(publisher_id), Integer.parseInt(genre_id), Integer.parseInt(rating_id), Float.parseFloat(currentPrice), Validation.convertToBoolean(isActive));
        }
        response.setContentType("text/plain");
        response.getWriter().print(rp.formatData());
        response.setStatus(rp.getResponse());
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    /**
     * Updates game information
     * @param title Game's title
     * @param description Game's description
     * @param imagePath Game's image path
     * @param publisher_id Generated publisher ID
     * @param genre_id Generated genre ID
     * @param rating_id Generated rating ID
     * @param currentPrice Game's price
     * @param isActive Filters for active/inactive game
     * @return ResponsePackage object
     */

    protected static ResponsePackage updateGame(int id, String title, String description, String imagePath, int publisher_id, int genre_id, int rating_id, float currentPrice, boolean isActive) {
        ResponsePackage rp = new ResponsePackage();

        try{
            DB db = new DB();

            if(db.openDB()){
                boolean isUpdated = Game.update(db.getConn(), id, title, description, imagePath, publisher_id, genre_id, rating_id, currentPrice, isActive);
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
