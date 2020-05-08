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

@WebServlet("/Servlets.GamePlatform.ReadList")

public class ReadList extends HttpServlet {
    public static void main(String[] args){
        ResponsePackage rp = readListGamePlatform();
        System.out.println(rp.formatData());
        System.out.println(rp.getResponse());
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResponsePackage rp = readListGamePlatform();

        response.setContentType("text/plain");
        response.getWriter().print(rp.formatData());
        response.setStatus(rp.getResponse());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected static ResponsePackage readListGamePlatform() {
        ResponsePackage rp = new ResponsePackage();

        try{
            DB db = new DB();

            if(db.openDB()) {
                GamePlatform[] gamePlatforms = GamePlatform.readList(db.getConn());
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
        return rp;
    }
}
