/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.json;

import java.sql.SQLException;

/**
 *
 * @author ai
 */
@javax.ws.rs.Path("/menu")
public class Menune {
    @javax.ws.rs.GET
    @javax.ws.rs.Path("/kat")
    @javax.ws.rs.Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String kat(){
        org.json.simple.JSONArray a=new org.json.simple.JSONArray();try {
            util.Db d=new util.Db();
            java.sql.ResultSet r=d.getResult("select*from kat_menu");
            while(r.next()){
                org.json.simple.JSONObject o=new org.json.simple.JSONObject();
                o.put("kode", r.getInt("kode"));
                o.put("nama", r.getString("nama"));
                o.put("gbr", r.getString("gbr"));
                a.add(o);
            }r.close();
            d.close();
        } catch (SQLException ex) {
            util.Db.hindar(ex, "android");
        }return a.toJSONString();
    }

    @javax.ws.rs.GET
    @javax.ws.rs.Path("/menu")
    @javax.ws.rs.Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String menu(){
        org.json.simple.JSONArray a=new org.json.simple.JSONArray();try {
            util.Db d=new util.Db();
            java.sql.ResultSet r=d.getResult("select kode,nama,harga,gbr from menu");
            while(r.next()){
                if(olehKadol(d,r.getString("kode"))){
                    org.json.simple.JSONObject o=new org.json.simple.JSONObject();
                    o.put("kode", r.getString("kode"));
                    o.put("nama", r.getString("nama"));
                    o.put("harga", r.getString("harga"));
                    o.put("gbr", r.getString("gbr"));
                    a.add(o);
                }
            }r.close();
            d.close();
        } catch (SQLException ex) {
            util.Db.hindar(ex, "android");
        }return a.toJSONString();
    }

    @javax.ws.rs.GET
    @javax.ws.rs.Path("/menu/{kat}")
    @javax.ws.rs.Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String menu(@javax.ws.rs.PathParam("kat")int kat){
        org.json.simple.JSONArray a=new org.json.simple.JSONArray();try {
            util.Db d=new util.Db();
            java.sql.PreparedStatement p=d.getPrep("select kode,nama,harga,gbr from menu where kat=?");
            p.setInt(1, kat);
            java.sql.ResultSet r=p.executeQuery();
            while(r.next()){
                if(olehKadol(d,r.getString("kode"))){
                    org.json.simple.JSONObject o=new org.json.simple.JSONObject();
                    o.put("kode", r.getString("kode"));
                    o.put("nama", r.getString("nama"));
                    o.put("harga", r.getString("harga"));
                    o.put("gbr", r.getString("gbr"));
                    a.add(o);
                }
            }r.close();
            p.close();
            d.close();
        } catch (SQLException ex) {
            util.Db.hindar(ex, "android");
        }return a.toJSONString();
    }

    private boolean olehKadol(util.Db d,String s)throws SQLException{
        java.sql.PreparedStatement p=d.getPrep("select bahan,qty from bahan where menu=?");
        p.setString(1, s);
        boolean b=true;
        java.sql.ResultSet r=p.executeQuery();
        while(r.next()){
            java.sql.PreparedStatement p2=d.getPrep("select stok from persediaan where kode=?");
            p2.setString(1, r.getString("bahan"));
            java.sql.ResultSet r2=p2.executeQuery();
            if(r2.next()&&r.getFloat("qty")>=r2.getInt("stok")){
                b=false;
                break;
            }
        }r.close();
        p.close();
        return b;
    }
}