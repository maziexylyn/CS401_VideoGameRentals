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

@WebServlet("/Servlets.Genre.ReadList")

public class ReadList extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String genre_isActive = request.getParameter("isActive");

        ResponsePackage rp = new ResponsePackage();


        if(Validation.checkBoolean(genre_isActive)){
            rp = readListGenre(Boolean.parseBoolean(genre_isActive));

        }

        response.setContentType("text/plain");
        response.getWriter().print(rp.formatData());
        response.setStatus(rp.getResponse());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected static ResponsePackage readListGenre(boolean genre_isActive) {
        ResponsePackage rp = new ResponsePackage();

        try{
            DB db = new DB();

            if(db.openDB()) {
                Genre[] genre = Genre.readList(db.getConn(), genre_isActive);
                db.closeDB();

                if (genre.length > 0) {
                    Gson gson = new Gson();
                    rp.setData(gson.toJson(genre));
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
