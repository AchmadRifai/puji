package wahyono.puji.projectskripsi.ws;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ai on 16/08/2017.
 */

public class Menu {
    @SerializedName("kode")
    private String kode;
    @SerializedName("nama")
    private String nama;
    @SerializedName("harga")
    private String harga;
    @SerializedName("gbr")
    private String gbr;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getGbr() {
        return gbr;
    }

    public void setGbr(String gbr) {
        this.gbr = gbr;
    }
}