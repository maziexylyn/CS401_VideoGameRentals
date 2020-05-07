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

@WebServlet("/Servlets.Platform.ReadList")
public class ReadList extends HttpServlet {
    public static void main(String[] args){
        boolean game_platform_isActive = true;
        ResponsePackage rp = readListGamePlatform(game_platform_isActive);
        System.out.println(rp.formatData());
        System.out.println(rp.getResponse());
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String game_platform_isActive = request.getParameter("isActive");

        ResponsePackage rp = new ResponsePackage();


        if(Validation.checkBoolean(game_platform_isActive)){
            rp = readListGamePlatform(Boolean.parseBoolean(game_platform_isActive));

        }

        response.setContentType("text/plain");
        response.getWriter().print(rp.formatData());
        response.setStatus(rp.getResponse());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    private static ResponsePackage readListGamePlatform(boolean game_platform_isActive) {
        ResponsePackage rp = new ResponsePackage();
        /*
        try{
            DB db = new DB();

            if(db.openDB()) {
                GamePlatform[] gamePlatforms = GamePlatform.readList(db.getConn(), game_platform_isActive);
                db.closeDB();

                if (gamePlatforms.length > 0) {
                    Gson gson = new Gson();
                    rp.setData(gson.toJson(gamePlatforms));
                    rp.setMsgResponse(ResponsePackage.Status.OK);
                } else {
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
