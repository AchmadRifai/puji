/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exe;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import util.Db;

/**
 *
 * @author ai
 */
public class TerimaPesan extends HttpServlet {

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
        util.General.ajarKoki(request, response);
        if(null!=request.getParameter("struk"))try {
            String s=request.getParameter("struk");
            util.Db d=new util.Db();
            org.joda.money.Money m=getTotal(d,s);
            java.sql.PreparedStatement p=d.getPrep("update pesanan set terima=?,total=? where nota=?");
            p.setBoolean(1, true);
            p.setString(2, ""+m);
            p.setString(3, s);
            p.execute();
            p.close();
            sudo(d,s);
            d.close();
        } catch (SQLException ex) {
            util.Db.hindar(ex, request.getRemoteAddr());
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

    private Money getTotal(Db d, String s) throws SQLException {
        Money m=Money.zero(CurrencyUnit.of("IDR"));
        java.sql.PreparedStatement p=d.getPrep("select hrg from item_pesanan where nota=?");
        p.setString(1, s);
        java.sql.ResultSet r=p.executeQuery();
        while(r.next()){
            org.joda.money.Money mo=org.joda.money.Money.parse(r.getString("hrg"));
            m=m.plus(mo);
        }r.close();
        p.close();
        return m;
    }

    private void sudo(Db d, String s) throws SQLException {
        java.sql.PreparedStatement p1=d.getPrep("select menu,qty from item_pesanan where nota=?");
        p1.setString(1, s);
        java.sql.ResultSet r1=p1.executeQuery();
        while(r1.next()){
            String menu=r1.getString("menu");
            int qty=r1.getInt("qty");
            sudo2(d,menu,qty);
        }r1.close();
        p1.close();
    }

    private void sudo2(Db d, String menu, int qty) throws SQLException {
        java.sql.PreparedStatement p=d.getPrep("select bahan,qty from bahan where menu=?");
        p.setString(1, menu);
        java.sql.ResultSet r=p.executeQuery();
        while(r.next()){
            String bahan=r.getString("bahan");
            float q=r.getFloat("qty")*qty;
            sudoTenan(d,bahan,q);
        }r.close();
        p.close();
    }

    private void sudoTenan(Db d, String bahan, float q) throws SQLException {
        java.sql.PreparedStatement p=d.getPrep("update persediaan set stok=stok-? where kode=?");
        p.setFloat(1, q);
        p.setString(2, bahan);
        p.execute();
        p.close();
    }

}
