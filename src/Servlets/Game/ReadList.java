package Servlets.Game;


import Classes.ResponsePackage;
import Classes.Validation;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/Servlets.Platform.ReadList")


public class ReadList extends javax.servlet.http.HttpServlet {
    public static void main(String[] args){
        boolean game_isActive = true;
        ResponsePackage rp = readListGame(game_isActive);
        System.out.println(rp.formatData());
        System.out.println(rp.getResponse());
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String game_isActive = request.getParameter("isActive");

        ResponsePackage rp = new ResponsePackage();


        if(Validation.checkBoolean(game_isActive)){
            rp = readListGame(Boolean.parseBoolean(game_isActive));

        }

        response.setContentType("text/plain");
        response.getWriter().print(rp.formatData());
        response.setStatus(rp.getResponse());

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    private static ResponsePackage readListGame(boolean game_isActive) {
        ResponsePackage rp = new ResponsePackage();
        /*
        try{
            DB db = new DB();

            if(db.openDB()) {
                Platform[] games = Platform.readList(db.getConn(), game_isActive);
                db.closeDB();

                if (games.length > 0) {
                    Gson gson = new Gson();
                    rp.setData(gson.toJson(games));
                    rp.setMsgResponse(ResponsePackage.Status.OK);
                } else {
                    rp.setMsgResponse(ResponsePackage.Status.NOT_FOUND);
                }
            }
        }catch(Exception err){
            err.printStackTrace();
        }

         */
        return rp;
    }
}
