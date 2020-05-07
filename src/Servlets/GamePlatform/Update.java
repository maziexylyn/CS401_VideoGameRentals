package Servlets.GamePlatform;

import Classes.Platform;
import Classes.ResponsePackage;
import Classes.Validation;
import db.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Servlets.GamePlatform.Update")
public class Update extends HttpServlet {
    public static void main(String[] args){
        int game_platform_id = 7;
        String game_platform_name = "MazieStation";
        boolean game_platform_isActive = true;
        ResponsePackage rp = updateGamePlatform(game_platform_id, game_platform_name, game_platform_isActive);
        System.out.println(rp.formatData());
        System.out.println(rp.getResponse());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String game_platform_id_string = request.getParameter("id");
        String game_platform_name = request.getParameter("name");
        String game_platform_isActive = request.getParameter("isActive");

        ResponsePackage rp = new ResponsePackage();


        if(Validation.checkGamePlatformName(game_platform_name) && Validation.checkID(game_platform_id_string) && Validation.checkBoolean(game_platform_isActive)){
            rp = updateGamePlatform(Integer.parseInt(game_platform_id_string), game_platform_name, Boolean.parseBoolean(game_platform_isActive));

        }

        response.setContentType("text/plain");
        response.getWriter().print(rp.formatData());
        response.setStatus(rp.getResponse());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    private static ResponsePackage updateGamePlatform(int game_platform_id, String game_platform_name, boolean game_platform_isActive){
        ResponsePackage rp = new ResponsePackage();
        /*
        try{
            DB db = new DB();

            if(db.openDB()){
                boolean isUpdated = GamePlatform.update(db.getConn(), game_platform_id, game_platform_name, game_platform_isActive);
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
