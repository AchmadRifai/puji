package wahyono.puji.projectskripsi.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.util.List;

import wahyono.puji.projectskripsi.AddYo;
import wahyono.puji.projectskripsi.R;
import wahyono.puji.projectskripsi.util.Work;
import wahyono.puji.projectskripsi.ws.Menu;

/**
 * Created by ai on 18/08/2017.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuHolder>{
    private String nota,kode;
    private Activity a;
    private List<Menu>l;

    public MenuAdapter(String nota, Activity a, List<Menu> l,String s) {
        this.nota = nota;
        this.a = a;
        this.l = l;
        kode=s;
    }

    @Override
    public MenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(a).inflate(R.layout.menu_card,parent,false);
        return new MenuHolder(v);
    }

    @Override
    public void onBindViewHolder(MenuHolder holder, int position) {
        final Menu m=l.get(position);
        Money mon=Money.of(CurrencyUnit.of("IDR"),Long.parseLong(m.getHarga()));
        holder.hrg.setText(""+mon);
        holder.nama.setText(m.getNama());
        Glide.with(a).load(Work.getUrl(a)+m.getGbr()).into(holder.thumb);
        holder.thumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPesanan(m);
            }
        });holder.hrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPesanan(m);
            }
        });holder.nama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPesanan(m);
            }
        });
    }

    private void addPesanan(Menu m) {
        Intent i=new Intent(a, AddYo.class);
        i.putExtra("nama",m.getNama());
        i.putExtra("nota",nota);
        i.putExtra("gbr",m.getGbr());
        i.putExtra("hrg",m.getHarga());
        i.putExtra("menu",m.getKode());
        i.putExtra("kat",kode);
        a.startActivity(i);
        a.finish();
    }

    @Override
    public int getItemCount() {
        return l.size();
    }

    public class MenuHolder extends RecyclerView.ViewHolder{
        public ImageView thumb;
        public TextView nama,hrg;

        public MenuHolder(View v) {
            super(v);
            thumb=(ImageView)v.findViewById(R.id.thumb_menu);
            nama=(TextView)v.findViewById(R.id.nama_menu);
            hrg=(TextView)v.findViewById(R.id.hrg_menu);
        }
    }
}