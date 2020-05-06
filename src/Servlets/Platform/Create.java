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

    // quick code test for function
    public static void main(String[] args){
        String name = "MazieBox";
        ResponsePackage rp = Create.createPlatform(name);
        System.out.println(rp.formatData());
        System.out.println(rp.getResponse());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String platform_name = request.getParameter("name");

        ResponsePackage rp = new ResponsePackage();

        if(Validation.checkGenreName(platform_name)){
            rp = createPlatform(platform_name);
        }

        response.setContentType("text/plain");
        response.getWriter().print(rp.formatData());
        response.setStatus(rp.getResponse());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    private static ResponsePackage createPlatform(String platform_name){
        ResponsePackage rp = new ResponsePackage();
        try{
            DB db = new DB();

            if(db.openDB()){

                boolean isCreated = Platform.create(db.getConn(), platform_name);
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
