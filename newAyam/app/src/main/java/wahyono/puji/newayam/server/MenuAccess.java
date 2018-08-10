package wahyono.puji.newayam.server;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import wahyono.puji.newayam.adapter.MenuAdapter;
import wahyono.puji.newayam.beans.Menu;
import wahyono.puji.newayam.util.Access;
import wahyono.puji.newayam.util.Work;

public class MenuAccess extends AsyncTask<Integer, Void, List<Menu>> {
    private Activity a;
    private RecyclerView rv;

    public MenuAccess(Activity a, RecyclerView rv) {
        this.a = a;
        this.rv = rv;
    }

    @Override
    protected List<Menu> doInBackground(Integer... integers) {
        List<Menu> lm = new LinkedList<>();
        int kode = integers[0]; try {
            String data = Work.dataAccess(Work.getURL(a) + Access.LIST_MENU_ALL + "/" + kode);
            JsonArray a = new com.google.gson.JsonParser().parse(data).getAsJsonArray();
            for(int x = 0; x < a.size(); x ++) {
                JsonObject o = a.get(x).getAsJsonObject();
                Menu m = new Menu();
                m.setNama(o.get("nama").getAsString());
                m.setKode(o.get("kode").getAsString());
                m.setHarga(o.get("harga").getAsString());
                m.setGbr(o.get("gbr").getAsString());
                lm.add(m);
            }
        } catch (IOException e) {
            Log.i("IOException", e.getMessage());
        } return lm;
    }

    @Override
    protected void onPostExecute(List<Menu> lm) {
        MenuAdapter ma = new MenuAdapter(a, lm);
        rv.setAdapter(ma);
        ma.notifyDataSetChanged();
    }
}
