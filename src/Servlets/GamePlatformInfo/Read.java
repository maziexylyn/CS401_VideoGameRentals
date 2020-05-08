package Servlets.GamePlatformInfo;

import Classes.*;
import com.google.gson.Gson;
import db.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Servlets.GamePlatformInfo.Read")
public class Read extends HttpServlet {
    // quick code test for function
    public static void main(String[] args){
        ResponsePackage rp = readGamePlatformInfo();
        System.out.println(rp.formatData());
        System.out.println(rp.getResponse());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ResponsePackage rp = readGamePlatformInfo();

        response.setContentType("text/plain");
        response.getWriter().print(rp.formatData());
        response.setStatus(rp.getResponse());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected static ResponsePackage readGamePlatformInfo(){
        ResponsePackage rp = new ResponsePackage();
        try{
            DB db = new DB();

            if(db.openDB()){
                GamePlatformInfo[] gamePlatformInfo = GamePlatformInfo.read(db.getConn());
                db.closeDB();

                if(gamePlatformInfo.length > 0){
                    Gson gson = new Gson();
                    rp.setData(gson.toJson(gamePlatformInfo));
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
