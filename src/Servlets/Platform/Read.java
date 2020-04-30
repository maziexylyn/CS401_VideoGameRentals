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
import java.sql.ResultSet;

@WebServlet("/Servlets.Platform.Read")
public class Read extends HttpServlet {

    // quick code test for function
    public static void main(String[] args){
        int platform_id = 1;
        ResponsePackage rp = readPlatform(platform_id);
        System.out.println(rp.formatData());
        System.out.println(rp.getResponse());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String platform_id_string = request.getParameter("id");

        ResponsePackage rp = new ResponsePackage();
        String data = rp.formatData();
        int status = rp.getResponse();

        if(Validation.checkID(platform_id_string)){
            rp = readPlatform(Integer.parseInt(platform_id_string));
            data = rp.formatData();
            status = rp.getResponse();
        }

        response.setContentType("text/plain");
        response.getWriter().print(data);
        response.setStatus(status);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private static ResponsePackage readPlatform(int platform_id){
        ResponsePackage rp = new ResponsePackage();
        try{
            DB db = new DB();

            if(db.openDB()){
                Platform platform = Platform.read(db.getConn(),platform_id);
                db.closeDB();

                if(platform != null){
                    Gson gson = new Gson();
                    rp.setData(gson.toJson(platform));
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
