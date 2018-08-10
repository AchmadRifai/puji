/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.json;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.ws.rs.PathParam;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import util.Db;

/**
 *
 * @author ai
 */
@javax.ws.rs.Path("/pesan")
public class PesanMenu {
    @javax.ws.rs.GET
    @javax.ws.rs.Path("/{m}/{nm}/{hp}")
    @javax.ws.rs.Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String dari(@PathParam("m") String m,@PathParam("nm") String nm,@PathParam("hp") String hp){
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();try {
            util.Db d=new util.Db();
            java.sql.Timestamp t=java.sql.Timestamp.valueOf(LocalDateTime.now());
            String s=m+t.getDate()+t.getMonth()+t.getYear()+t.getHours()+t.getMinutes()+t.getSeconds();
            java.sql.PreparedStatement p=d.getPrep("insert into pesanan values(?,?,?,?,?,?,?,?,?,?,?,?)");
            p.setString(1, s);
            p.setInt(2, Integer.parseInt(m));
            p.setString(3, nm);
            p.setString(4, hp);
            p.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
            p.setString(6, org.joda.money.Money.zero(CurrencyUnit.of("IDR")).toString());
            p.setString(7, org.joda.money.Money.zero(CurrencyUnit.of("IDR")).toString());
            p.setString(8, org.joda.money.Money.zero(CurrencyUnit.of("IDR")).toString());
            p.setBoolean(9, false);
            p.setBoolean(10, false);
            p.setBoolean(11, false);
            p.setBoolean(12, true);
            p.execute();
            p.close();
            d.close();
            o.put("nota", s);
        } catch (SQLException ex) {
            o.put("nota", "Error");
            util.Db.hindar(ex, m);
        }return o.toJSONString();
    }

    @javax.ws.rs.GET
    @javax.ws.rs.Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @javax.ws.rs.Path("/add/{nota}/{menu}/{qty}")
    public String tambah(@PathParam("nota") String nota,@PathParam("menu") String menu,@PathParam("qty") String qty){
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();try {
            Db d=new Db();
            if(notaAda(d,nota,menu)){
                if(sedia(d,menu,qty)){
                    updatePesanan(d,nota,menu,qty);
                    o.put("pesan", "Sukses");
                }else o.put("pesan", "Kehabisan Stok");
            }else{
                pesananBaru(d,nota,menu,qty);
                o.put("pesan", "Sukses");
            }d.close();
        } catch (SQLException ex) {
            o.put("pesan", "Error");
            util.Db.hindar(ex, nota);
        }return o.toJSONString();
    }

    private boolean notaAda(Db d, String nota, String menu) {
        boolean b=false;try {
            java.sql.PreparedStatement p=d.getPrep("select qty from item_pesanan where nota=? and menu=?");
            p.setString(1, nota);
            p.setString(2, menu);
            java.sql.ResultSet r=p.executeQuery();
            b=r.next();
            r.close();
            p.close();
        } catch (SQLException ex) {
            Db.hindar(ex, nota);
        }return b;
    }

    private void updatePesanan(Db d, String nota, String menu, String qty) throws SQLException {
        Money hrg=getHarga(menu,d);
        hrg=hrg.multipliedBy(Long.parseLong(qty));
        java.sql.PreparedStatement p=d.getPrep("update item_pesanan set qty=?,hrg=? where nota=? and menu=?");
        p.setInt(1, Integer.parseInt(qty));
        p.setString(2, ""+hrg);
        p.setString(3, nota);
        p.setString(4, menu);
        p.execute();
        p.close();
    }

    private void pesananBaru(Db d, String nota, String menu, String qty) throws SQLException {
        Money hrg=getHarga(menu,d);
        hrg=hrg.multipliedBy(Long.parseLong(qty));
        java.sql.PreparedStatement p=d.getPrep("insert into item_pesanan values(?,?,?,?)");
        p.setString(1, nota);
        p.setString(2, menu);
        p.setInt(3, Integer.parseInt(qty));
        p.setString(4, ""+hrg);
        p.execute();
        p.close();
    }

    private Money getHarga(String menu, Db d) throws SQLException {
        java.sql.PreparedStatement p=d.getPrep("select harga from menu where kode=?");
        Money m=null;
        p.setString(1, menu);
        java.sql.ResultSet r=p.executeQuery();
        if(r.next())m=org.joda.money.Money.of(CurrencyUnit.of("IDR"), new java.math.BigDecimal(r.getString("harga")));
        r.close();
        p.close();
        return m;
    }

    @javax.ws.rs.GET
    @javax.ws.rs.Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @javax.ws.rs.Path("/daftar/{nota}")
    public String daftarPesan(@PathParam("nota")String nota){
        org.json.simple.JSONArray a=new org.json.simple.JSONArray();try {
            Db d=new Db();
            java.sql.PreparedStatement p=d.getPrep("select menu,qty,hrg from item_pesanan where nota=?");
            p.setString(1, nota);
            java.sql.ResultSet r=p.executeQuery();
            while(r.next()){
                org.json.simple.JSONObject o=new org.json.simple.JSONObject();
                String nama=getNama(d,r.getString("menu"));
                o.put("menu", nama);
                o.put("qty", r.getInt("qty"));
                o.put("hrg", r.getString("hrg"));
                o.put("kode", r.getString("menu"));
                a.add(o);
            }r.close();
            p.close();
            d.close();
        } catch (SQLException ex) {
            Db.hindar(ex, nota);
        }return a.toJSONString();
    }

    private String getNama(Db d, String menu) throws SQLException {
        String s="";
        java.sql.PreparedStatement p=d.getPrep("select nama from menu where kode=?");
        p.setString(1, menu);
        java.sql.ResultSet r=p.executeQuery();
        if(r.next())s=r.getString("nama");
        r.close();
        p.close();
        return s;
    }

    private boolean sedia(Db d, String menu, String qty) throws SQLException {
        boolean b=true;
        java.sql.PreparedStatement p1=d.getPrep("select bahan,qty from bahan where menu=?");
        p1.setString(1, menu);
        java.sql.ResultSet r1=p1.executeQuery();
        while(r1.next()){
            java.sql.PreparedStatement p2=d.getPrep("select stok from persediaan where kode=?");
            p2.setString(1, r1.getString("bahan"));
            java.sql.ResultSet r2=p2.executeQuery();
            if(r2.next()){
                float f=r2.getFloat("stok");
                int i=Integer.parseInt(qty);
                if(f-i<=0){
                    b=false;
                    break;
                }
            }r2.close();
            p2.close();
        }r1.close();
        p1.close();
        return b;
    }

    @javax.ws.rs.GET
    @javax.ws.rs.Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @javax.ws.rs.Path("/valid/{nota}")
    public String valid(@PathParam("nota") String nota){
        org.json.simple.JSONObject o=new org.json.simple.JSONObject();try {
            Db d=new Db();
            java.sql.PreparedStatement p=d.getPrep("update pesanan set submitted=? where nota=?");
            p.setBoolean(1, true);
            p.setString(2, nota);
            p.execute();
            p.close();
            d.close();
            o.put("pesan", "Sukses");
        } catch (SQLException ex) {
            o.put("pesan", "Error");
            Db.hindar(ex, nota);
        }return o.toJSONString();
    }

	@javax.ws.rs.GET
    @javax.ws.rs.Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @javax.ws.rs.Path("/batal/{nota}")
	public String batal(@PathParam("nota") String nota){
		org.json.simple.JSONObject o=new org.json.simple.JSONObject();try{
			Db d=new Db();
			java.sql.PreparedStatement p=d.getPrep("delete from pesanan where nota=?");
			p.setString(1, nota);
			p.execute();
			p.close();
			d.close();
			o.put("pesan", "Sukses");
	 	}catch(SQLException ex){
			o.put("pesan", "Error");
            Db.hindar(ex, nota);
		}return o.toJSONString();
	}

        @javax.ws.rs.GET
    @javax.ws.rs.Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @javax.ws.rs.Path("/delItem/{nota}/{menu}")
        public String delItem(@PathParam("nota")String nota,@PathParam("menu")String menu){
            org.json.simple.JSONObject o=new org.json.simple.JSONObject();try {
            Db d=new Db();
            java.sql.PreparedStatement p=d.getPrep("delete from item_pesanan where nota=? and menu=?");
            p.setString(1, nota);
            p.setString(2, menu);
            p.execute();
            p.close();
            d.close();
            o.put("pesan", "Sukses");
        } catch (SQLException ex) {
            o.put("pesan", "Error");
            Db.hindar(ex, nota);
        }return o.toJSONString();
    }

        @javax.ws.rs.GET
        @javax.ws.rs.Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
        @javax.ws.rs.Path("/end/{nota}")
        public String confirm(@PathParam("nota")String nota){
            org.json.simple.JSONObject o=new org.json.simple.JSONObject();
        try {
            Db d=new Db();
            java.sql.PreparedStatement p=d.getPrep("update pesanan set lagi=? where nota=?");
            p.setBoolean(1, false);
            p.setString(2, nota);
            p.execute();
            p.close();
            d.close();
            o.put("pesan", "Sukses");
        } catch (SQLException ex) {
            o.put("pesan", "Error");
            Db.hindar(ex, "android");
        } return o.toJSONString();
        }

        @javax.ws.rs.GET
        @javax.ws.rs.Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
        @javax.ws.rs.Path("/lagi/{nota}")
        public String lagiKah(@PathParam("nota")String nota){
            org.json.simple.JSONObject o=new org.json.simple.JSONObject();try {
            Db d=new Db();
            java.sql.PreparedStatement p=d.getPrep("select lagi from pesanan where nota=?");
            p.setString(1, nota);
            java.sql.ResultSet r=p.executeQuery();
            if(r.next()){
                if(r.getBoolean("lagi"))o.put("pesan", "Dapat");
                else o.put("pesan", "Keluar");
            }else o.put("pesan", "Error");
            r.close();
            p.close();
            d.close();
        } catch (SQLException ex) {
            o.put("pesan", "Error");
            Db.hindar(ex, "android");
        } return o.toJSONString();
        }
}