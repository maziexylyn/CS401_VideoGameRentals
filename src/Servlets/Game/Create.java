package Servlets.Game;

import Classes.Game;
import Classes.ResponsePackage;
import Classes.Validation;
import db.DB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

@WebServlet("/Servlets.Game.Create")

public class Create extends HttpServlet {

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String imagePath = request.getParameter("imagePath");
        String publisher_id = request.getParameter("publisher_id");
        String genre_id = request.getParameter("genre_id");
        String rating_id = request.getParameter("rating_id");
        String currentPrice = request.getParameter("currentPrice");

        ResponsePackage rp = new ResponsePackage();

        if(
                Validation.checkGameTitle(title) &&
                Validation.checkGameDescription(description) &&
                Validation.checkGameImagePath(imagePath) &&
                Validation.checkID(publisher_id) &&
                Validation.checkID(genre_id) &&
                Validation.checkID(rating_id) &&
                Validation.checkGamePrice(currentPrice)
        ) {
           rp = createGame(title, description, imagePath, Integer.parseInt(publisher_id), Integer.parseInt(genre_id), Integer.parseInt(rating_id), Float.parseFloat(currentPrice));
        }
        response.setContentType("text/plain");
        response.getWriter().print(rp.formatData());
        response.setStatus(rp.getResponse());
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    /**
     * Creates game
     * @param title Game's title
     * @param description Game's description
     * @param imagePath Game's image path
     * @param publisher_id Generated publisher ID
     * @param genre_id Generated genre ID
     * @param rating_id Generated rating ID
     * @param currentPrice Game's price
     * @return ResponsePackage object
     */
    protected static ResponsePackage createGame(String title, String description, String imagePath, int publisher_id, int genre_id, int rating_id, float currentPrice) {
        ResponsePackage rp2 = new ResponsePackage();

        try {
            DB db = new DB();

            if (db.openDB()) {

                boolean isCreated = Game.create(db.getConn(), title, description, imagePath, publisher_id, genre_id, rating_id, currentPrice);
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

        return rp2;
    }


}

