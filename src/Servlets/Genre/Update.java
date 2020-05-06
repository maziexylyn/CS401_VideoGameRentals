package Servlets.Genre;

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

@WebServlet("/Servlets.Genre.Update")
public class Update extends HttpServlet {
    public static void main(String[] args){
        int genre_id = 7;
        String genre_name = "MazieStation";
        boolean genre_isActive = true;
        ResponsePackage rp = updateGenre(genre_id, genre_name, genre_isActive);
        System.out.println(rp.formatData());
        System.out.println(rp.getResponse());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String genre_id_string = request.getParameter("id");
        String genre_name = request.getParameter("name");
        String genre_isActive = request.getParameter("isActive");

        ResponsePackage rp = new ResponsePackage();


        if(Validation.checkGenreName(genre_name) && Validation.checkID(genre_id_string) && Validation.checkBoolean(genre_isActive)){
            rp = updateGenre(Integer.parseInt(genre_id_string), genre_name, Boolean.parseBoolean(genre_isActive));

        }

        response.setContentType("text/plain");
        response.getWriter().print(rp.formatData());
        response.setStatus(rp.getResponse());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    private static ResponsePackage updateGenre(int genre_id, String genre_name, boolean genre_isActive){
        ResponsePackage rp = new ResponsePackage();
       /*
        try{
            DB db = new DB();

            if(db.openDB()){
                boolean isUpdated = Genre.update(db.getConn(), genre_id, genre_name, genre_isActive);
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

        */
        return rp;
    }
}
