/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

/**
 *
 * @author ai
 */
public class General {
    public static org.joda.money.Money getBayar(Db d,String nota) throws SQLException{
        org.joda.money.Money m=org.joda.money.Money.zero(CurrencyUnit.of("IDR"));
        java.sql.PreparedStatement p=d.getPrep("select byr from pesanan where nota=?");
        p.setString(1, nota);
        java.sql.ResultSet r=p.executeQuery();
        if(r.next())m=org.joda.money.Money.parse(r.getString("byr"));
        r.close();
        p.close();
        return m;
    }

    public static org.joda.money.Money getKembali(Db d,String nota) throws SQLException{
        org.joda.money.Money m=org.joda.money.Money.zero(CurrencyUnit.of("IDR"));
        java.sql.PreparedStatement p=d.getPrep("select susuk from pesanan where nota=?");
        p.setString(1, nota);
        java.sql.ResultSet r=p.executeQuery();
        if(r.next())m=org.joda.money.Money.parse(r.getString("susuk"));
        r.close();
        p.close();
        return m;
    }

    public static org.joda.money.Money getTotal(Db d,String nota) throws SQLException{
        org.joda.money.Money m=org.joda.money.Money.zero(CurrencyUnit.of("IDR"));
        java.sql.PreparedStatement p=d.getPrep("select total from pesanan where nota=?");
        p.setString(1, nota);
        java.sql.ResultSet r=p.executeQuery();
        if(r.next())m=org.joda.money.Money.parse(r.getString("total"));
        r.close();
        p.close();
        return m;
    }

    public static String getNama(Db d,String menu) throws SQLException{
        String s="";
        java.sql.PreparedStatement p=d.getPrep("select nama from menu where kode=?");
        p.setString(1, menu);
        java.sql.ResultSet r=p.executeQuery();
        if(r.next())s=r.getString("nama");
        r.close();
        p.close();
        return s;
    }

    public static void login(Db d,String id) throws SQLException{
        java.sql.PreparedStatement ps=d.getPrep("update karyawan set mlebu=? where kode=?");
        ps.setBoolean(1, true);
        ps.setString(2, id);
        ps.execute();
        ps.close();
    }

    public static void logout(String id) throws SQLException {
        Db d=new Db();
        java.sql.PreparedStatement p=d.getPrep("update karyawan set mlebu=? where kode=?");
        p.setBoolean(1, false);
        p.setString(2, id);
        p.execute();
        p.close();
        d.close();
    }

    public static void ajarAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException{
        if(null!=request.getSession().getAttribute("peg"))try{
        util.Db d=new util.Db();
        java.sql.PreparedStatement p=d.getPrep("select jab,mlebu from karyawan where kode=?");
        p.setString(1, ""+request.getSession().getAttribute("peg"));
        java.sql.ResultSet r=p.executeQuery();
        if(r.next()){
            if(!r.getBoolean("mlebu")){
                request.getSession().removeAttribute("peg");
                response.sendRedirect("index.jsp");
            }else if(!"admin".equals(r.getString("jab")))response.sendRedirect("dash.php");
        }else{
            request.getSession().removeAttribute("peg");
            response.sendRedirect("index.jsp");
        }r.close();
        p.close();
        d.close();
    }catch(Exception ex){
        util.Db.hindar(ex, request.getRemoteAddr());
    }else response.sendRedirect("index.jsp");
    }

    public static void ajarKoki(HttpServletRequest request, HttpServletResponse response) throws IOException{
        if(null!=request.getSession().getAttribute("peg"))try{
        util.Db d=new util.Db();
        java.sql.PreparedStatement p=d.getPrep("select jab,mlebu from karyawan where kode=?");
        p.setString(1, ""+request.getSession().getAttribute("peg"));
        java.sql.ResultSet r=p.executeQuery();
        if(r.next()){
            if(!r.getBoolean("mlebu")){
                request.getSession().removeAttribute("peg");
                response.sendRedirect("index.jsp");
            }else if(!"koki".equals(r.getString("jab")))response.sendRedirect("dash.php");
        }else{
            request.getSession().removeAttribute("peg");
            response.sendRedirect("index.jsp");
        }r.close();
        p.close();
        d.close();
    }catch(Exception ex){
        util.Db.hindar(ex, request.getRemoteAddr());
    }else response.sendRedirect("index.jsp");
    }

    public static void ajarKasir(HttpServletRequest request, HttpServletResponse response) throws IOException{
        if(null!=request.getSession().getAttribute("peg"))try{
        util.Db d=new util.Db();
        java.sql.PreparedStatement p=d.getPrep("select jab,mlebu from karyawan where kode=?");
        p.setString(1, ""+request.getSession().getAttribute("peg"));
        java.sql.ResultSet r=p.executeQuery();
        if(r.next()){
            if(!r.getBoolean("mlebu")){
                request.getSession().removeAttribute("peg");
                response.sendRedirect("index.jsp");
            }else if(!"teller".equals(r.getString("jab")))response.sendRedirect("dash.php");
        }else{
            request.getSession().removeAttribute("peg");
            response.sendRedirect("index.jsp");
        }r.close();
        p.close();
        d.close();
    }catch(Exception ex){
        util.Db.hindar(ex, request.getRemoteAddr());
    }else response.sendRedirect("index.jsp");
    }

    public static Money getHarga(Db d, String menu) throws SQLException {
        Money m=Money.zero(CurrencyUnit.of("IDR"));
        java.sql.PreparedStatement p=d.getPrep("select harga from menu where kode=?");
        p.setString(1, menu);
        java.sql.ResultSet r=p.executeQuery();
        if(r.next())m=Money.of(CurrencyUnit.of("IDR"), Long.parseLong(r.getString("harga")));
        r.close();
        p.close();
        return m;
    }
}