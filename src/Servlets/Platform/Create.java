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
import java.net.http.HttpResponse;

@WebServlet("/Servlets.Platform.Create")
public class Create extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String platform_name = request.getParameter("name");
        String platform_imagePath = request.getParameter("imagePath");


        ResponsePackage rp = new ResponsePackage();

        if(Validation.checkGenreName(platform_name) && Validation.checkGameImagePath(platform_imagePath)){
            rp = createPlatform(platform_name, platform_imagePath);
        }

        response.setContentType("text/plain");
        response.getWriter().print(rp.formatData());
        response.setStatus(rp.getResponse());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected static ResponsePackage createPlatform(String platform_name, String platform_imagePath){
        ResponsePackage rp = new ResponsePackage();
        try{
            DB db = new DB();

            if(db.openDB()){

                boolean isCreated = Platform.create(db.getConn(), platform_name, platform_imagePath);
                db.closeDB();

                if(isCreated){
                  rp.setMsgResponse(ResponsePackage.Status.OK);
                }else{
                    rp.setMsgResponse(ResponsePackage.Status.NOT_IMPLEMENTED);
                }
            }

        }catch(Exception err){
            err.printStackTrace();
        }
        return rp;

    }
}
