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
import javax.servlet.http.Part;
import util.Db;

/**
 *
 * @author ai
 */
@javax.servlet.annotation.MultipartConfig
public class AddKat extends HttpServlet {

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
        javax.servlet.http.Part gbr=request.getPart("gbr");
        String kode=request.getParameter("kode"),nama=request.getParameter("nama");
        if(null!=gbr&&kode!=null&&nama!=null)try {
            util.Db d=new util.Db();
            String fname=getName(gbr).replace("\"", "");
            if(!fname.isEmpty()){
                simpanGbr(fname,gbr,kode);
                tambahKat(fname,kode,nama,d);
                response.sendRedirect("dash.php");
            }else response.sendRedirect("addKat.jsp");
            d.close();
        } catch (SQLException ex) {
            response.sendRedirect("addKat.jsp");
            util.Db.hindar(ex, request.getRemoteAddr());
        }else response.sendRedirect("addKat.jsp");
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

    public static String getName(Part p) {
        for(String i:p.getHeader("content-disposition").split(";"))
            if(i.trim().startsWith("filename"))
                return i.substring(i.indexOf("=")+1).trim().replace("/", "");
        return"";
    }

    public static void simpanGbr(String fname, Part gbr, String kode) throws IOException {
        String s=System.getProperty("user.home")+"/.puji/gbr/"+kode+"/"+fname;
        java.io.File f=new java.io.File(s);
        if(!f.getParentFile().exists())f.getParentFile().mkdirs();
        java.io.InputStream i=gbr.getInputStream();
        java.io.FileOutputStream o=new java.io.FileOutputStream(f, false);
        int r=0;
        byte[]b=new byte[1];
        while((r=i.read(b))!=-1)o.write(b, 0, r);
        o.close();
        i.close();
    }

    private void tambahKat(String fname, String kode, String nama, Db d) throws SQLException {
        java.sql.PreparedStatement p=d.getPrep("insert into kat_menu values(?,?,?)");
        p.setInt(1, Integer.parseInt(kode));
        p.setString(2, nama);
        p.setString(3, "gbr.exe?path="+kode+"/"+(fname.replace(" ", "%20")));
        p.execute();
        p.close();
    }

}
