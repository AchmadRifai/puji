/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ai
 */
public class Db {
    public static void hindar(Exception ex, String r) {
        java.util.Date d=new java.util.Date();
        java.io.File f=new java.io.File(System.getProperty("user.home")+"/.puji/error/"+r+"/"+d.getDate()+"-"+d.getMonth()+
                "-"+d.getYear()+"/"+d.getHours()+"-"+d.getMinutes()+"-"+d.getSeconds()+".log");
        if(!f.getParentFile().exists())f.getParentFile().mkdirs();
        if(f.exists())f.delete();
        try {
            java.io.PrintStream o = new java.io.PrintStream(f);
            ex.printStackTrace(o);
            o.close();
        } catch (FileNotFoundException ex1) {
            Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex1);
        }
    }

    private java.sql.Connection c;
    private java.sql.Statement s;

    public java.sql.Connection getC(){
        return c;
    }

    public Db() throws SQLException{
        try {
            com.mysql.jdbc.Driver.class.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Db.hindar(ex,"localhost");
        }c=java.sql.DriverManager.getConnection("jdbc:mysql://localhost/resto", "root", "kirana");
        s=c.createStatement();
    }

    public void execSQL(String sql) throws SQLException{
        s.executeUpdate(sql);
    }

    public void close() throws SQLException{
        s.close();
        c.close();
    }

    public java.sql.ResultSet getResult(String sql) throws SQLException{
        return s.executeQuery(sql);
    }

    public java.sql.PreparedStatement getPrep(String sql) throws SQLException{
        return c.prepareStatement(sql);
    }
}