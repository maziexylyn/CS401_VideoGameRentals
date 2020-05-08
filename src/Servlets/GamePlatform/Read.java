package Servlets.GamePlatform;

import Classes.GamePlatform;
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
import java.sql.ResultSet;

@WebServlet("/Servlets.GamePlatform.Read")

public class Read extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String game_id_text = request.getParameter("game_id");
        String platform_id_text = request.getParameter("platform_id");

        ResponsePackage rp = new ResponsePackage();

        if(Validation.checkID(game_id_text) && Validation.checkID(platform_id_text)){
            rp = readGamePlatform(Integer.parseInt(game_id_text), Integer.parseInt(platform_id_text));
        }

        response.setContentType("text/plain");
        response.getWriter().print(rp.formatData());
        response.setStatus(rp.getResponse());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected static ResponsePackage readGamePlatform(int game_id, int platform_id){
        ResponsePackage rp = new ResponsePackage();

        try{
            DB db = new DB();

            if(db.openDB()){
                GamePlatform gamePlatform = GamePlatform.read(db.getConn(), game_id, platform_id);
                db.closeDB();

                if(gamePlatform != null){
                    Gson gson = new Gson();
                    rp.setData(gson.toJson(gamePlatform));
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
