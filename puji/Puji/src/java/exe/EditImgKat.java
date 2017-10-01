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
import util.Db;

/**
 *
 * @author ai
 */
public class EditImgKat extends HttpServlet {

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
        javax.servlet.http.Part p=request.getPart("gbr");
        String kat=""+request.getSession().getAttribute("kat");
        if(kat!="null"&&p!=null)try {
            util.Db d=new util.Db();
            deleteOldFile(d,kat);
            String fname=exe.AddKat.getName(p).replace("\"", "");
            exe.AddKat.simpanGbr(fname, p, kat);
            simpanPerubahan(d,kat,fname);
            d.close();
            request.getSession().removeAttribute("kat");
            response.sendRedirect("dash.php");
        } catch (SQLException ex) {
            util.Db.hindar(ex, request.getRemoteAddr());
        }else response.sendRedirect("editImgKat.jsp");
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

    private void simpanPerubahan(Db d, String kat, String fname) throws SQLException {
        java.sql.PreparedStatement p=d.getPrep("update kat_menu set gbr=? where kode=?");
        p.setString(1, "gbr.exe?path="+kat+"/"+fname.replace(" ", "%20"));
        p.setInt(2, Integer.parseInt(kat));
        p.execute();
        p.close();
    }

    private void deleteOldFile(Db d, String kat) throws SQLException {
        java.sql.PreparedStatement p=d.getPrep("select gbr from kat_menu where kode=?");
        p.setString(1, kat);
        java.sql.ResultSet r=p.executeQuery();
        if(r.next()){
            String fname=r.getString("gbr").replace("gbr.exe?path=", "").replace("=", "").replace("%20", " ");
            java.io.File f=new java.io.File(System.getProperty("user.home")+"/.puji/gbr/"+fname);
            if(f.exists())f.delete();
        }r.close();
        p.close();
    }
}