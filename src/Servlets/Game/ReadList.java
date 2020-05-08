package Servlets.Game;

import Classes.Game;
import Classes.ResponsePackage;
import Classes.Validation;
import com.google.gson.Gson;
import db.DB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

@WebServlet("/Servlets.Game.ReadList")

public class ReadList extends HttpServlet {

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String game_isActive = request.getParameter("isActive");

        ResponsePackage rp = new ResponsePackage();

        if(Validation.checkBoolean(game_isActive)){
            rp = readListGame(Boolean.parseBoolean(game_isActive));

        }

        response.setContentType("text/plain");
        response.getWriter().print(rp.formatData());
        response.setStatus(rp.getResponse());

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected static ResponsePackage readListGame(boolean game_isActive) {
        ResponsePackage rp = new ResponsePackage();

        try{
            DB db = new DB();

            if(db.openDB()) {
                Game[] games = Game.readList(db.getConn(), game_isActive);
                db.closeDB();

                if (games.length > 0) {
                    Gson gson = new Gson();
                    rp.setData(gson.toJson(games));
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
