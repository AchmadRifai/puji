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
import util.Db;

/**
 *
 * @author ai
 */
public class AddBahan extends HttpServlet {

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
        String kode=request.getParameter("kode"),nama=request.getParameter("nama"),stok=request.getParameter("stok"),
                satuan=request.getParameter("satuan");
        if(kode!=null&&nama!=null&&stok!=null&&satuan!=null)try {
            util.Db d=new util.Db();
            if(!ada(d,kode)){
                java.sql.PreparedStatement p=d.getPrep("insert into persediaan values(?,?,?,?)");
                p.setString(1, kode);
                p.setString(2, nama);
                p.setFloat(3, Float.parseFloat(stok));
                p.setString(4, satuan);
                p.execute();
                p.close();
                response.sendRedirect("dash.php");
            }else response.sendRedirect("tambahBahan.jsp");
            d.close();
        } catch (SQLException ex) {
            response.sendRedirect("tambahBahan.jsp");
            util.Db.hindar(ex, request.getRemoteAddr());
        }else response.sendRedirect("tambahBahan.jsp");
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

    private boolean ada(Db d, String kode) throws SQLException {
        boolean b;
        java.sql.PreparedStatement p=d.getPrep("select nama from from persediaan where kode=?");
        p.setString(1, kode);
        java.sql.ResultSet r=p.executeQuery();
        b=r.next();
        r.close();
        p.close();
        return b;
    }

}
