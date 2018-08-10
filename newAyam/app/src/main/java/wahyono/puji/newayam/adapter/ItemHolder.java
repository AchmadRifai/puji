package wahyono.puji.newayam.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import wahyono.puji.newayam.R;
import wahyono.puji.newayam.beans.Iteme;

public class ItemHolder extends RecyclerView.ViewHolder {
    private Activity a;
    private TextView menu, hrg, qty;
    private Iteme i;

    public ItemHolder(View itemView) {
        super(itemView);
        menu = (TextView) itemView.findViewById(R.id.menuIteme);
        hrg = (TextView) itemView.findViewById(R.id.hrgIteme);
        qty = (TextView) itemView.findViewById(R.id.qtyIteme);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aksi();
            }
        });
    }

    private void aksi() {
        //
    }

    public void setData(Activity a, Iteme i) {
        this.a = a;
        this.i = i;
        menu.setText(i.getMenu());
        hrg.setText(i.getHrg());
        qty.setText("" + i.getQty());
    }
}
