package Servlets.Game;


import Classes.ResponsePackage;
import Classes.Validation;
import com.google.gson.Gson;
import db.DB;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/Servlets.Publisher.Read")
public class Read extends javax.servlet.http.HttpServlet {
    public static void main(String[] args){
        int game_id = 1;
        ResponsePackage rp = readGameName(game_id);
        System.out.println(rp.formatData());
        System.out.println(rp.getResponse());
    }
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        String game_id_string = request.getParameter("id");

        ResponsePackage rp = new ResponsePackage();

        if(Validation.checkID(game_id_string)){
            rp = readGameName(Integer.parseInt(game_id_string));


        }

        response.setContentType("text/plain");
        response.getWriter().print(rp.formatData());
        response.setStatus(rp.getResponse());
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }



    private static ResponsePackage readGameName(int game_id){
        ResponsePackage rp = new ResponsePackage();
        /*
        try{
            DB db = new DB();

            if(db.openDB()){
                Game game1 = Game.read(db.getConn(),game_id);
                db.closeDB();

                if(game1 != null){
                    Gson gson = new Gson();
                    rp.setData(gson.toJson(game1));
                    rp.setMsgResponse(ResponsePackage.Status.OK);
                }else{
                    rp.setMsgResponse(ResponsePackage.Status.NOT_FOUND);
                }
            }
        }catch(Exception err){
            err.printStackTrace();
        }*/
        return rp;
    }


}
