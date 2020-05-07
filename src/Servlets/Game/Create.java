package Servlets.Game;

import Classes.ResponsePackage;
import Classes.Validation;
import db.DB;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/Servlets.Game.Create")
public class Create extends javax.servlet.http.HttpServlet {
    public static void main(String[] args){
        String name = "MazieBox";
        ResponsePackage rp = Create.createGameName(name);
        System.out.println(rp.formatData());
        System.out.println(rp.getResponse());
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        String game_name = request.getParameter("name");
        ResponsePackage rp = new ResponsePackage();


        if(Validation.checkGameName(game_name))
        {
           rp = createGameName(game_name);


        }
        response.setContentType("text/plain");
        response.getWriter().print(rp.formatData());
        response.setStatus(rp.getResponse());


    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    private static ResponsePackage createGameName(String game_name) {
        ResponsePackage rp2 = new ResponsePackage();
        /*
        try {
            DB db = new DB();

            if (db.openDB()) {

                boolean isCreated = Game.create(db.getConn(), game_name);
                db.closeDB();

                if (isCreated) {
                    rp2.setMsgResponse(ResponsePackage.Status.OK);
                } else {
                    rp2.setMsgResponse(ResponsePackage.Status.NOT_IMPLEMENTED);
                }
            }
        } catch(Exception err){
            err.printStackTrace();
        }

         */
        return rp2;
    }


}

