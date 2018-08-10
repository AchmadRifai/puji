package wahyono.puji.projectskripsi.ws;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ai on 14/08/2017.
 */

public class BahanNota {
    @SerializedName("nota")
    private String nota;

    public BahanNota() {
        nota="";
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}