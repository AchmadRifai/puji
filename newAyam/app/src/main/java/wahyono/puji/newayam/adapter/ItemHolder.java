package wahyono.puji.newayam.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import wahyono.puji.newayam.Cart;
import wahyono.puji.newayam.R;
import wahyono.puji.newayam.beans.Iteme;
import wahyono.puji.newayam.server.DelAccess;

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
        AlertDialog.Builder b = new AlertDialog.Builder(a);
        b.setTitle("Hapus Item Pemesanan");
        b.setMessage("Apakah ingin membatalkan pembelian "+i.getMenu()+"?");
        b.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });b.setPositiveButton("", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ya();
            }
        }); b.create().show();
    }

    private void ya() {
        new DelAccess(a, new Runnable() {
            @Override
            public void run() {
                berhasil();
            }
        }, new Runnable() {
            @Override
            public void run() {
                gagal();
            }
        }).execute(i.getKode());
    }

    private void gagal() {
        Toast.makeText(a, "Item gagal dihapus", Toast.LENGTH_LONG).show();
        berhasil();
    }

    private void berhasil() {
        Cart c = (Cart) a;
        c.muat();
    }

    public void setData(Activity a, Iteme i) {
        this.a = a;
        this.i = i;
        menu.setText(i.getMenu());
        hrg.setText(i.getHrg());
        qty.setText("" + i.getQty());
    }
}
