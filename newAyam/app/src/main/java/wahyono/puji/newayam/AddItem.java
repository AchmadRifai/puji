package wahyono.puji.newayam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import wahyono.puji.newayam.beans.Menu;
import wahyono.puji.newayam.server.AddItemAccess;
import wahyono.puji.newayam.util.Work;

public class AddItem extends AppCompatActivity {
    private ImageView gbr;
    private TextView nm, hrg, num;
    private Button submit, min, plus;
    private Menu data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        binding();
        muat();
        evene();
    }

    private void evene() {
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCount();
            }
        }); min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minCount();
            }
        }); submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aksi();
            }
        });
    }

    private void aksi() {
        new AddItemAccess(this, new Runnable() {
            @Override
            public void run() {
                ya();
            }
        }, new Runnable() {
            @Override
            public void run() {
                tidak();
            }
        }).execute(data.getKode(), "" + num.getText());
    }

    private void tidak() {
        Toast.makeText(this, "Penambahan Tidak Valid", Toast.LENGTH_LONG).show();
    }

    private void ya() {
        finish();
    }

    private void minCount() {
        int i = Integer.parseInt("" + num.getText());
        i--;
        num.setText("" + i);
        min.setEnabled(0 < i);
        submit.setEnabled(0 < i);
    }

    private void addCount() {
        int i = Integer.parseInt("" + num.getText());
        i++;
        num.setText("" + i);
        submit.setEnabled(0 < i);
        min.setEnabled(0 < i);
    }

    private void muat() {
        genData();
        num.setText("0");
        min.setEnabled(false);
        submit.setEnabled(false);
        nm.setText(data.getNama());
        hrg.setText(data.getHarga());
        Glide.with(this).load(Work.getURL(this) + data.getGbr()).into(gbr);
    }

    private void genData() {
        data = new Menu();
        data.setGbr(getIntent().getExtras().getString("gbr"));
        data.setHarga(getIntent().getExtras().getString("harga"));
        data.setKode(getIntent().getExtras().getString("kode"));
        data.setNama(getIntent().getStringExtra("nama"));
    }

    private void binding() {
        gbr = (ImageView) findViewById(R.id.gbrMenu);
        nm = (TextView) findViewById(R.id.nmMenu);
        hrg = (TextView) findViewById(R.id.hrgMenu);
        num = (TextView) findViewById(R.id.numCount);
        submit = (Button) findViewById(R.id.addToTemp);
        min = (Button) findViewById(R.id.minCount);
        plus = (Button) findViewById(R.id.plusCount);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
