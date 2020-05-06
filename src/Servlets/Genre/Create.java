package Servlets.Genre;

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

@WebServlet("/Servlets.Genre.Create")
public class Create extends HttpServlet {
    public static void main(String[] args){
        String name = "MazieBox";
        ResponsePackage rp = Servlets.Genre.Create.createGenre(name);
        System.out.println(rp.formatData());
        System.out.println(rp.getResponse());
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String genre_name = request.getParameter("name");

        ResponsePackage rp = new ResponsePackage();

        if(Validation.checkGenreName(genre_name)){
            rp = createGenre(genre_name);
        }

        response.setContentType("text/plain");
        response.getWriter().print(rp.formatData());
        response.setStatus(rp.getResponse());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private static ResponsePackage createGenre(String genre_name){
        ResponsePackage rp = new ResponsePackage();
        /*
        try{
            DB db = new DB();

            if(db.openDB()){

                boolean isCreated = Platform.create(db.getConn(), genre_name);
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

         */
        return rp;

    }
}
