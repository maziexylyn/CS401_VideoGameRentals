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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String platform_id_string = request.getParameter("id");

        ResponsePackage rp = new ResponsePackage();


        if(Validation.checkID(platform_id_string)){
            rp = readPlatform(Integer.parseInt(platform_id_string));

        }

        response.setContentType("text/plain");
        response.getWriter().print(rp.formatData());
        response.setStatus(rp.getResponse());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * Reads platform by platform ID
     * @param platform_id Generated platform ID
     * @return ResponsePackage object
     */
    protected static ResponsePackage readPlatform(int platform_id){
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
