package wahyono.puji.newayam.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import wahyono.puji.newayam.MainActivity;
import wahyono.puji.newayam.MenuLst;
import wahyono.puji.newayam.R;
import wahyono.puji.newayam.beans.Kat;
import wahyono.puji.newayam.util.Work;

public class KatHolder extends RecyclerView.ViewHolder {
    private ImageView gbr;
    private TextView nm;
    private Kat data;
    private Activity a;

    public KatHolder(View itemView) {
        super(itemView);
        gbr = (ImageView) itemView.findViewById(R.id.gbrKat);
        nm = (TextView) itemView.findViewById(R.id.nmKat);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aksi();
            }
        });
    }

    private void aksi() {
        MainActivity m = (MainActivity) a;
        m.mlaku = false;
        Intent i = new Intent(a, MenuLst.class);
        i.putExtra("kat", data.getKode());
        a.startActivity(i);
        a.finish();
    }

    public void setData(Activity a, Kat k) {
        data = k;
        this.a = a;
        nm.setText(k.getNama());
        Glide.with(a).load(Work.getURL(a) + k.getGbr()).into(gbr);
    }
}
