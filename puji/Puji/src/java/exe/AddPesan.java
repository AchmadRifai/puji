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
public class AddPesan extends HttpServlet {

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
        String nota=request.getParameter("nota"),meja=request.getParameter("meja"),tgl=request.getParameter("tgl"),
                byr=request.getParameter("byr");
        if(nota!=null&&meja!=null&&tgl!=null&&byr!=null)try{
            util.Db d=new util.Db();
            java.sql.PreparedStatement p=d.getPrep("insert into pesanan values(?,?,?,?,?,?,?,?,?)");
            p.setString(1, nota);
            p.setInt(2, Integer.parseInt(meja));
            p.setDate(3, java.sql.Date.valueOf(tgl));
            org.joda.money.Money m1=org.joda.money.Money.of(CurrencyUnit.of("IDR"), Long.parseLong(byr)),
                    m2=org.joda.money.Money.zero(CurrencyUnit.of("IDR"));
            p.setString(4, ""+m1);
            p.setString(5, ""+m2);
            p.setString(6, ""+m2);
            p.setBoolean(7, true);
            p.setBoolean(8, true);
            p.setBoolean(9, true);
            p.execute();
            p.close();
            d.close();
            request.getSession().setAttribute("nota", nota);
            response.sendRedirect("tambahPesan2.jsp");
        }catch(java.sql.SQLException e){
            response.sendRedirect("tambahPesan.jsp");
            util.Db.hindar(e, request.getRemoteAddr());
        }else response.sendRedirect("tambahPesan.jsp");
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
