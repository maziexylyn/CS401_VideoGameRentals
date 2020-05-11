package Servlets.Genre;

import Classes.Genre;
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

@WebServlet("/Servlets.Genre.Read")

public class Read extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String genre_id_string = request.getParameter("id");

        ResponsePackage rp = new ResponsePackage();

        if(Validation.checkID(genre_id_string)){
            rp = readGenre(Integer.parseInt(genre_id_string));
        }

        response.setContentType("text/plain");
        response.getWriter().print(rp.formatData());
        response.setStatus(rp.getResponse());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * Reads genre by genre ID
     * @param genre_id Generated genre ID
     * @return ResponsePackage object
     */
    protected static ResponsePackage readGenre(int genre_id){
        ResponsePackage rp = new ResponsePackage();

        try{
            DB db = new DB();

            if(db.openDB()){
                Genre genre = Genre.read(db.getConn(),genre_id);
                db.closeDB();

                if(genre != null){
                    Gson gson = new Gson();
                    rp.setData(gson.toJson(genre));
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
