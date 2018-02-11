package wahyono.puji.projectskripsi.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import wahyono.puji.projectskripsi.DftrMenu;
import wahyono.puji.projectskripsi.R;
import wahyono.puji.projectskripsi.util.Work;
import wahyono.puji.projectskripsi.ws.Kat;

/**
 * Created by ai on 17/08/2017.
 */

public class KatAdapter extends RecyclerView.Adapter<KatAdapter.KatHolder>{
    private Context c;
    private List<Kat>l;
    private Activity a;
    private String nota;

    public KatAdapter(Context c, List<Kat> l,Activity a,String s) {
        this.c = c;
        this.l = l;
        this.a = a;
        nota=s;
    }

    @Override
    public KatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(c).inflate(R.layout.kateg_card,parent,false);
        return new KatHolder(v);
    }

    @Override
    public void onBindViewHolder(KatHolder holder, int position) {
        final Kat k=l.get(position);
        holder.nama.setText(k.getNama());
        Glide.with(c).load(Work.getUrl(a)+k.getGbr()).into(holder.thumb);
        holder.nama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lihatMenu(k.getKode());
            }
        });holder.thumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lihatMenu(k.getKode());
            }
        });
    }

    private void lihatMenu(int kode) {
        Intent i=new Intent(a, DftrMenu.class);
        i.putExtra("nota",nota);
        i.putExtra("kode",""+kode);
        a.startActivity(i);
        a.finish();
    }

    @Override
    public int getItemCount() {
        return l.size();
    }

    public class KatHolder extends RecyclerView.ViewHolder{
        public ImageView thumb;
        public TextView nama;

        public KatHolder(View v) {
            super(v);
            thumb=(ImageView)v.findViewById(R.id.thumb_kat);
            nama=(TextView)v.findViewById(R.id.nama_kat);
        }
    }
}