package Servlets.Game;

import Classes.Game;
import Classes.ResponsePackage;
import Classes.Validation;
import com.google.gson.Gson;
import db.DB;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

@WebServlet("/Servlets.Game.Read")

public class Read extends HttpServlet {

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String game_id_string = request.getParameter("id");

        ResponsePackage rp = new ResponsePackage();

        if(Validation.checkID(game_id_string)){
            rp = readGame(Integer.parseInt(game_id_string));
        }

        response.setContentType("text/plain");
        response.getWriter().print(rp.formatData());
        response.setStatus(rp.getResponse());
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    /**
     * Reads game by game ID
     * @param game_id Generated game ID
     * @return ResponsePackage object
     */
    protected static ResponsePackage readGame(int game_id){
        ResponsePackage rp = new ResponsePackage();

        try{
            DB db = new DB();

            if(db.openDB()){
                Game game = Game.read(db.getConn(),game_id);
                db.closeDB();

                if(game != null){
                    Gson gson = new Gson();
                    rp.setData(gson.toJson(game));
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
