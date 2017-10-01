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

/**
 *
 * @author ai
 */
public class AddKaryawan extends HttpServlet {

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
        String kode=request.getParameter("kode"),pass=request.getParameter("pass"),nama=request.getParameter("nama"),
                almt=request.getParameter("almt"),jab=request.getParameter("jab");
        if(kode!=null&&pass!=null&&nama!=null&&almt!=null&&jab!=null)try {
            util.Db d=new util.Db();
            java.sql.PreparedStatement p=d.getPrep("insert into karyawan values(?,?,?,?,?,?)");
            p.setString(1, kode);
            p.setString(2, pass);
            p.setString(3, nama);
            p.setString(4, almt);
            p.setString(5, jab);
            p.setBoolean(6, false);
            p.execute();
            p.close();
            d.close();
            response.sendRedirect("dash.php");
        } catch (SQLException ex) {
            util.Db.hindar(ex, request.getRemoteAddr());
        }else response.sendRedirect("tambahKaryawan.jsp");
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
