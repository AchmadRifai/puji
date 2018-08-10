/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import util.Db;

/**
 *
 * @author ai
 */
public class Proses {
    public static void cetak(String nota, Db d) throws SQLException, JRException {
        java.util.Map<String,Object>m=new java.util.HashMap<>();
        m.put("notane", nota);
        JasperReport jr=JasperCompileManager.compileReport(System.getProperty("user.home")+"/struk.jrxml");
        JasperPrint jp=JasperFillManager.fillReport(jr, m, d.getC());
        JasperExportManager.exportReportToHtmlFile(jp, System.getProperty("user.home")+"/Desktop/s.html");
        //JasperPrintManager.printReport(jp, false);
    }

    private static List<Data> loading(String nota, Db d) throws SQLException {
        List<Data>l=new java.util.LinkedList<>();
        java.sql.PreparedStatement p=d.getPrep("select tgl,byr,susuk,total from pesanan where nota=?");
        p.setString(1, nota);
        java.sql.ResultSet r=p.executeQuery();
        while(r.next()){
            String bayar=r.getString("byr"),kembali=r.getString("susuk"),total=r.getString("total");
            Date tgl=r.getDate("tgl");
            List<Data>l2=item(d, nota, tgl, bayar, kembali, total);
            for(Data dat:l2)l.add(dat);
        }r.close();
        p.close();
        return l;
    }

    private static List<Data> item(Db d, String nota, Date tgl, String bayar, String kembali, String total) throws SQLException {
        List<Data>l=new java.util.LinkedList<>();
        java.sql.PreparedStatement p=d.getPrep("select menu,qty,hrg from item_pesanan where nota=?");
        p.setString(1, nota);
        java.sql.ResultSet r=p.executeQuery();
        while(r.next()){
            Data dat=new Data();
            dat.setBayar(bayar);
            dat.setKembali(kembali);
            dat.setNota(nota);
            dat.setTgl(tgl);
            dat.setTotal(total);
            dat.setQty(r.getInt("qty"));
            dat.setMenu(r.getString("menu"));
            dat.setSubtotal(r.getString("hrg"));
            dat.setNama(util.General.getNama(d, dat.getMenu()));
            org.joda.money.Money sat=util.General.getHarga(d,dat.getMenu());
            dat.setSatuan(""+sat);
            l.add(dat);
        }r.close();
        p.close();
        return l;
    }

    public static JRBeanCollectionDataSource genData(Db d, String nota) throws SQLException {
        return new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource(loading(nota,d));
    }
}