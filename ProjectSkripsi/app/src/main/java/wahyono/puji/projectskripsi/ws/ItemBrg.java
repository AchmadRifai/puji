package wahyono.puji.projectskripsi.ws;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ai on 15/08/2017.
 */

public class ItemBrg {
    @SerializedName("menu")
    private String menu;
    @SerializedName("hrg")
    private String hrg;
    @SerializedName("qty")
    private int qty;
    @SerializedName("kode")
    private String kode;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getHrg() {
        return hrg;
    }

    public void setHrg(String hrg) {
        this.hrg = hrg;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}