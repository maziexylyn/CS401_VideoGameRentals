package Servlets.Platform;

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

    // quick code test for function
    public static void main(String[] args){
        boolean platform_isActive = true;
        ResponsePackage rp = readListPlatform(platform_isActive);
        System.out.println(rp.formatData());
        System.out.println(rp.getResponse());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String platform_isActive = request.getParameter("isActive");

        ResponsePackage rp = new ResponsePackage();


        if(Validation.checkBoolean(platform_isActive)){
            rp = readListPlatform(Boolean.parseBoolean(platform_isActive));

        }

        response.setContentType("text/plain");
        response.getWriter().print(rp.formatData());
        response.setStatus(rp.getResponse());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private static ResponsePackage readListPlatform(boolean platform_isActive) {
        ResponsePackage rp = new ResponsePackage();
        try{
            DB db = new DB();

            if(db.openDB()) {
                Platform[] platforms = Platform.readList(db.getConn(), platform_isActive);
                db.closeDB();

                if (platforms.length > 0) {
                    Gson gson = new Gson();
                    rp.setData(gson.toJson(platforms));
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
