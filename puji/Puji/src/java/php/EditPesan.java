/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package php;

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
public class EditPesan extends HttpServlet {

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
        String byr=request.getParameter("byr"),tgl=request.getParameter("tgl"),meja=request.getParameter("meja");
        if(null!=request.getSession().getAttribute("pesanan")&&null!=byr&&null!=tgl&&meja!=null)try{
            util.Db d=new util.Db();
            org.joda.money.Money m=org.joda.money.Money.of(CurrencyUnit.of("IDR"), Long.parseLong(byr));
            java.sql.PreparedStatement p=d.getPrep("update pesanan set byr=?,tgl=?,meja=? where nota=?");
            p.setString(1, ""+m);
            p.setDate(2, java.sql.Date.valueOf(tgl));
            p.setInt(3, Integer.parseInt(meja));
            p.setString(4, ""+request.getSession().getAttribute("pesanan"));
            p.execute();
            p.close();
            d.close();
            request.getSession().setAttribute("nota", ""+request.getSession().getAttribute("pesanan"));
            request.getSession().removeAttribute("pesanan");
            response.sendRedirect("tambahPesan2.jsp");
        }catch(java.sql.SQLException e){
            response.sendRedirect("editPesan.jsp");
            util.Db.hindar(e, request.getRemoteAddr());
        }else response.sendRedirect("editPesan.jsp");
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
