package Servlets.Game;

import Classes.Platform;
import Classes.ResponsePackage;
import Classes.Validation;
import db.DB;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/Servlets.Platform.Update")

public class Update extends javax.servlet.http.HttpServlet {
    public static void main(String[] args){
        int game_id = 7;
        String game_name = "MazieStation";
        boolean game_isActive = true;
        ResponsePackage rp = updateGame(game_id, game_name, game_isActive);
        System.out.println(rp.formatData());
        System.out.println(rp.getResponse());
    }
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String game_id_string = request.getParameter("id");
        String game_name = request.getParameter("name");
        String game_isActive = request.getParameter("isActive");

        ResponsePackage rp = new ResponsePackage();


        if(Validation.checkGameName(game_name) && Validation.checkID(game_id_string) && Validation.checkBoolean(game_isActive)){
            rp = updateGame(Integer.parseInt(game_id_string), game_name, Boolean.parseBoolean(game_isActive));

        }

        response.setContentType("text/plain");
        response.getWriter().print(rp.formatData());
        response.setStatus(rp.getResponse());
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    private static ResponsePackage updateGame(int game_id, String game_name, boolean game_isActive){
        ResponsePackage rp = new ResponsePackage();
        /*
        try{
            DB db = new DB();

            if(db.openDB()){
                boolean isUpdated = Game.update(db.getConn(), game_id, game_name, game_isActive);
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
        
         */
        return rp;
    }
}
