package wahyono.puji.newayam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import wahyono.puji.newayam.server.DaftarNotaAccess;
import wahyono.puji.newayam.server.TumpukNotaAccess;

public class Cart extends AppCompatActivity {
    private RecyclerView rv;
    private TextView tot;
    private Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        binding();
        evene();
        muat();
    }

    private void evene() {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tumpuk();
            }
        });
    }

    private void tumpuk() {
        new TumpukNotaAccess(this, new Runnable() {
            @Override
            public void run() {
                berhasil();
            }
        }, new Runnable() {
            @Override
            public void run() {
                masalah();
            }
        }).execute();
    }

    private void berhasil() {
        Toast.makeText(this, "Anda masih bisa pesan lagi", Toast.LENGTH_LONG).show();
        onBackPressed();
    }

    private void masalah() {
        Toast.makeText(this, "Gagal mengirim pesanan", Toast.LENGTH_LONG).show();
        muat();
    }

    public void muat() {
        new DaftarNotaAccess(this, rv, tot, b).execute();
    }

    private void binding() {
        rv = (RecyclerView) findViewById(R.id.myOrder);
        tot = (TextView) findViewById(R.id.totalan);
        b = (Button) findViewById(R.id.submitMyOrder);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
