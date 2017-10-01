/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package php;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ai
 */
public class Dash extends HttpServlet {

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
        if(null!=request.getSession().getAttribute("peg"))try {
            util.Db d=new util.Db();
            String id=""+request.getSession().getAttribute("peg");
            java.sql.PreparedStatement p=d.getPrep("select jab from karyawan where kode=?");
            p.setString(1, id);
            java.sql.ResultSet r=p.executeQuery();
            if(r.next()){
                if(null == r.getString("jab")){
                    request.getSession().removeAttribute("peg");
                    response.sendRedirect("index.jsp");
                }else switch (r.getString("jab")) {
                    case "admin":
                        response.sendRedirect("dash-admin.jsp");
                        break;
                    case "koki":
                        response.sendRedirect("dash-dapur.jsp");
                        break;
                    case "teller":
                        response.sendRedirect("dash-kasir.jsp");
                        break;
                    default:
                        request.getSession().removeAttribute("peg");
                        response.sendRedirect("index.jsp");
                }
            }else response.sendRedirect("index.jsp");
            r.close();
            p.close();
            d.close();
        } catch (SQLException ex) {
            util.Db.hindar(ex, request.getRemoteAddr());
        }else response.sendRedirect("index.jsp");
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
