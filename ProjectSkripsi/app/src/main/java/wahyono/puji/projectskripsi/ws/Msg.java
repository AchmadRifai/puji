package wahyono.puji.projectskripsi.ws;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ai on 14/08/2017.
 */

public class Msg {
    @SerializedName("pesan")
    private String pesan;

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }
}