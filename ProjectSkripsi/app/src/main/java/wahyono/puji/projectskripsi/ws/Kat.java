package wahyono.puji.projectskripsi.ws;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ai on 16/08/2017.
 */

public class Kat {
    @SerializedName("nama")
    private String nama;
    @SerializedName("gbr")
    private String gbr;
    @SerializedName("kode")
    private int kode;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGbr() {
        return gbr;
    }

    public void setGbr(String gbr) {
        this.gbr = gbr;
    }

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }
}