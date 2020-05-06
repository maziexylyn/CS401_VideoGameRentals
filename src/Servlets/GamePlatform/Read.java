package Servlets.GamePlatform;

import Classes.Platform;
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
    public static void main(String[] args){
        int game_platform_id = 1;
        ResponsePackage rp = readGamePlatform(game_platform_id);
        System.out.println(rp.formatData());
        System.out.println(rp.getResponse());
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String game_platform_id_string = request.getParameter("id");

        ResponsePackage rp = new ResponsePackage();


        if(Validation.checkGamePlatformID(game_platform_id_string)){
            rp = readGamePlatform(Integer.parseInt(game_platform_id_string));

        }

        response.setContentType("text/plain");
        response.getWriter().print(rp.formatData());
        response.setStatus(rp.getResponse());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    private static ResponsePackage readGamePlatform(int game_platform_id){
        ResponsePackage rp = new ResponsePackage();
       /*
        try{
            DB db = new DB();

            if(db.openDB()){
                GamePlatform gamePlatform = GamePlatform.read(db.getConn(),game_platform_id);
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

        */
        return rp;
    }
}
