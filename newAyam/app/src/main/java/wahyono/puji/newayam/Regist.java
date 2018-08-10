package wahyono.puji.newayam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import wahyono.puji.newayam.server.PesanAccess;

public class Regist extends AppCompatActivity {
    private EditText nm, hp;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        binding();
        evene();
    }

    private void evene() {
        nm.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    validasiForm();
                    return true;
                } return false;
            }
        }); hp.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    validasiForm();
                    return true;
                } return false;
            }
        }); submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanjut();
            }
        });
    }

    private void lanjut() {
        new PesanAccess(this, new Runnable() {
            @Override
            public void run() {
                dapat();
            }
        }, new Runnable() {
            @Override
            public void run() {
                gagal();
            }
        }, new Runnable() {
            @Override
            public void run() {
                fail();
            }
        }).
                execute("" + nm.getText(), "" + hp.getText());
    }

    private void fail() {
        Toast.makeText(this, "Jaringan Bermasalah", Toast.LENGTH_LONG).show();
        submit.setEnabled(false);
        nm.setText("");
        hp.setText("");
    }

    private void gagal() {
        Toast.makeText(this, "Pemesanan gagal", Toast.LENGTH_LONG).show();
        submit.setEnabled(false);
        nm.setText("");
        hp.setText("");
    }

    private void dapat() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void validasiForm() {
        submit.setEnabled("" != ""+nm.getText() && "" != ""+hp.getText());
    }

    private void binding() {
        nm = (EditText) findViewById(R.id.nama);
        hp = (EditText) findViewById(R.id.hp);
        submit = (Button) findViewById(R.id.submitReg);
    }

    @Override
    public void onBackPressed() {
        finish();
        System.exit(0);
    }
}
