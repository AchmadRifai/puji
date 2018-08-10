package wahyono.puji.newayam.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import wahyono.puji.newayam.AddItem;
import wahyono.puji.newayam.MenuLst;
import wahyono.puji.newayam.R;
import wahyono.puji.newayam.beans.Menu;
import wahyono.puji.newayam.util.Access;
import wahyono.puji.newayam.util.Work;

public class MenuHolder extends RecyclerView.ViewHolder{
    private Menu data;
    private Activity a;
    private ImageView gbr;
    private TextView nm, hrg;

    public MenuHolder(View itemView) {
        super(itemView);
        gbr = (ImageView) itemView.findViewById(R.id.gbrMenu);
        nm = (TextView) itemView.findViewById(R.id.nmMenu);
        hrg = (TextView) itemView.findViewById(R.id.hrgMenu);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aksi();
            }
        });
    }

    private void aksi() {
        MenuLst ml = (MenuLst) a;
        ml.mlaku = false;
        Intent i = new Intent(a, AddItem.class);
        i.putExtra("gbr", data.getGbr());
        i.putExtra("harga", data.getHarga());
        i.putExtra("kode", data.getKode());
        i.putExtra("nama", data.getNama());
        a.startActivity(i);
        a.finish();
    }

    public void setData(Activity a, Menu m) {
        data = m;
        this.a = a;
        Glide.with(a).load(Work.getURL(a) + m.getGbr()).into(gbr);
        nm.setText(m.getNama());
        hrg.setText(m.getHarga());
    }
}
