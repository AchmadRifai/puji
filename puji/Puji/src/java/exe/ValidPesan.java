/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exe;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.joda.money.CurrencyUnit;

/**
 *
 * @author ai
 */
public class ValidPesan extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        util.General.ajarAdmin(request, response);
        String nota=""+request.getSession().getAttribute("nota");
        if(nota!="null")try{
            util.Db d=new util.Db();
            java.sql.PreparedStatement p=d.getPrep("select hrg from item_pesanan where nota=?");
            p.setString(1, nota);
            java.sql.ResultSet r=p.executeQuery();
            org.joda.money.Money m=org.joda.money.Money.zero(CurrencyUnit.of("IDR")),susuk,byr=util.General.getBayar(d, nota);
            while(r.next())m=m.plus(org.joda.money.Money.parse(r.getString("hrg")));
            r.close();
            p.close();
            susuk=byr.minus(m);
            java.sql.PreparedStatement p2=d.getPrep("update pesanan set total=?,susuk=? where nota=?");
            p2.setString(1, ""+m);
            p2.setString(2, ""+susuk);
            p2.setString(3, nota);
            p2.execute();
            p2.close();
            d.close();
        }catch(java.sql.SQLException e){
            util.Db.hindar(e, request.getRemoteAddr());
        }response.sendRedirect("dash.php");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
