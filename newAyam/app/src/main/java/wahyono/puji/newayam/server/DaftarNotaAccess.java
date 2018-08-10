package wahyono.puji.newayam.server;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import wahyono.puji.newayam.adapter.ItemAdapter;
import wahyono.puji.newayam.beans.Iteme;
import wahyono.puji.newayam.util.Access;
import wahyono.puji.newayam.util.Work;

public class DaftarNotaAccess extends AsyncTask<Void, Void, List<Iteme>> {
    private Activity a;
    private RecyclerView rv;

    public DaftarNotaAccess(Activity a, RecyclerView rv) {
        this.a = a;
        this.rv = rv;
    }

    @Override
    protected List<Iteme> doInBackground(Void... voids) {
        List<Iteme> l = new LinkedList<>();
        String nota = Work.getNota(a); try {
            String data = Work.dataAccess(Work.getURL(a) + Access.DAFTAR_KERANJANG + nota);
            JsonArray a = new com.google.gson.JsonParser().parse(data).getAsJsonArray();
            for(int x = 0; x < a.size(); x ++) {
                JsonObject o = a.get(x).getAsJsonObject();
                Iteme i = new Iteme();
                i.setHrg(o.get("hrg").getAsString());
                i.setKode(o.get("kode").getAsString());
                i.setMenu(o.get("menu").getAsString());
                i.setQty(o.get("qty").getAsInt());
                l.add(i);
            }
        } catch (IOException e) {
            Log.i("IOException",e.getMessage());
        } return l;
    }

    @Override
    protected void onPostExecute(List<Iteme> itemes) {
        GridLayoutManager glm = new GridLayoutManager(a, 1);
        glm.setOrientation(GridLayoutManager.VERTICAL);
        rv.setLayoutManager(glm);
        ItemAdapter ia = new ItemAdapter(a, itemes);
        rv.setAdapter(ia);
        ia.notifyDataSetChanged();
    }
}