package Servlets.GamePlatform;

import Classes.GamePlatform;
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String game_id_text = request.getParameter("game_id");
        String platform_id_text = request.getParameter("platform_id");
        String add_times_rented_text = request.getParameter("add_times_rented");

        ResponsePackage rp = new ResponsePackage();

        if(Validation.checkID(game_id_text) && Validation.checkID(platform_id_text) && Validation.checkAddTimesRented(add_times_rented_text)){
            rp = updateGamePlatform(Integer.parseInt(game_id_text), Integer.parseInt(platform_id_text), Integer.parseInt(add_times_rented_text));
        }

        response.setContentType("text/plain");
        response.getWriter().print(rp.formatData());
        response.setStatus(rp.getResponse());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected static ResponsePackage updateGamePlatform(int game_id, int platform_id, int add_times_rented){
        ResponsePackage rp = new ResponsePackage();

        try{
            DB db = new DB();

            if(db.openDB()){
                boolean isUpdated = GamePlatform.update(db.getConn(), game_id, platform_id, add_times_rented);
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
