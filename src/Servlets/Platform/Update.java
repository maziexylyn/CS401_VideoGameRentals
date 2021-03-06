package Servlets.Platform;

import Classes.Platform;
import Classes.ResponsePackage;
import Classes.Validation;
import db.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Servlets.Platform.Update")
public class Update extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String platform_id_string = request.getParameter("id");
        String platform_name = request.getParameter("name");
        String platform_isActive = request.getParameter("isActive");
        String platform_imagePath = request.getParameter("imagePath");


        ResponsePackage rp = new ResponsePackage();


        if(Validation.checkPlatformName(platform_name) && Validation.checkID(platform_id_string) && Validation.checkBoolean(platform_isActive) && Validation.checkGameImagePath(platform_imagePath)){
            rp = updatePlatform(Integer.parseInt(platform_id_string), platform_name, Validation.convertToBoolean(platform_isActive), platform_imagePath);

        }

        response.setContentType("text/plain");
        response.getWriter().print(rp.formatData());
        response.setStatus(rp.getResponse());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * Updates platform
     * @param platform_id Generated platform ID
     * @param platform_name Platform's name
     * @param platform_isActive Filters for active/inactive platforms
     * @param platform_imagePath Platform's image path
     * @return ResponsePackage object
     */
    protected static ResponsePackage updatePlatform(int platform_id, String platform_name, boolean platform_isActive, String platform_imagePath){
        ResponsePackage rp = new ResponsePackage();
        try{
            DB db = new DB();

            if(db.openDB()){
                boolean isUpdated = Platform.update(db.getConn(), platform_id, platform_name, platform_isActive, platform_imagePath);
                db.closeDB();

                if(isUpdated){
                    rp.setMsgResponse(ResponsePackage.Status.OK);
                }else{
                    rp.setMsgResponse(ResponsePackage.Status.NOT_MODIFIED);
                }
            }
        }catch(Exception err){
            err.printStackTrace();
        }
        return rp;
    }
}
