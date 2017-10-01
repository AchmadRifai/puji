/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package php;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ai
 */
public class Login extends HttpServlet {

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
        if(null==request.getSession().getAttribute("peg")){
        if(null!=request.getParameter("masuk")){
            String id=request.getParameter("id"),pass=request.getParameter("pass");
            if(null!=id&&null!=pass)try{
                util.Db d=new util.Db();
                java.sql.PreparedStatement ps=d.getPrep("select pass,mlebu from karyawan where kode=?");
                ps.setString(1, id);
                java.sql.ResultSet r=ps.executeQuery();
                if(r.next()){
                    if(pass == null ? r.getString("pass") == null : pass.equals(r.getString("pass"))){
                        if(!r.getBoolean("mlebu")){
                            util.General.login(d,id);
                            request.getSession().setAttribute("peg", id);
                            response.sendRedirect("dash.php");
                        }else response.sendRedirect("index.jsp?error=duplikat%20login");
                    }else response.sendRedirect("index.jsp?error=password%20salah");
                }else response.sendRedirect("index.jsp?error=tidak%20diketahui");
                r.close();
                ps.close();
                d.close();
            }catch(Exception e){
                util.Db.hindar(e, request.getRemoteAddr());
                response.sendRedirect("index.jsp");
            }
        }
    }else response.sendRedirect("dash.php");
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
