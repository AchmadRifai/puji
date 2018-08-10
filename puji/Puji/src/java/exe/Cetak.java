/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exe;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import util.Db;

/**
 *
 * @author ai
 */
public class Cetak extends HttpServlet {

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
        util.General.ajarKasir(request, response);
        if(null!=request.getSession().getAttribute("nota")&&null!=request.getParameter("byr"))try {
            String nota=""+request.getSession().getAttribute("nota");
            Money byr=Money.of(CurrencyUnit.of("IDR"), Long.parseLong(request.getParameter("byr")));
            util.Db d=new util.Db();
            changeData(d,nota,byr);
            new java.lang.Thread(()->{jalan(nota,request);}).start();
            d.close();
            request.getSession().removeAttribute("nota");
        } catch (SQLException ex) {
            util.Db.hindar(ex, request.getRemoteAddr());
        }else{
            if(null==request.getSession().getAttribute("nota"))System.out.println("nota kosong");
            if(null==request.getParameter("byr"))System.out.println("bayar kosong");
        }response.sendRedirect("dash-kasir.jsp");
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

    private void changeData(Db d, String nota, Money byr) throws SQLException {
        Money total=getTotal(d,nota),kembali=byr.minus(total);
        java.sql.PreparedStatement p=d.getPrep("update pesanan set susuk=?,byr=?,cetak=?,lagi=? where nota=?");
        p.setString(1, ""+kembali);
        p.setString(2, ""+byr);
        p.setBoolean(3, true);
        p.setBoolean(4, false);
        p.setString(5, nota);
        p.execute();
        p.close();
    }

    private Money getTotal(Db d, String nota) throws SQLException {
        Money m=Money.zero(CurrencyUnit.of("IDR"));
        java.sql.PreparedStatement p=d.getPrep("select total from pesanan where nota=?");
        p.setString(1, nota);
        java.sql.ResultSet r=p.executeQuery();
        if(r.next())m=Money.parse(r.getString("total"));
        r.close();
        p.close();
        return m;
    }

    private void jalan(String nota, HttpServletRequest request) {
        try {
            util.Db d=new util.Db();
            beans.Proses.cetak(nota,d);
            d.close();
        } catch (Exception ex) {
            util.Db.hindar(ex, request.getRemoteAddr());
        }
    }

}
