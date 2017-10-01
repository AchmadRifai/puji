package wahyono.puji.projectskripsi.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import wahyono.puji.projectskripsi.ItemTemp;
import wahyono.puji.projectskripsi.R;
import wahyono.puji.projectskripsi.ws.Api;
import wahyono.puji.projectskripsi.ws.ItemBrg;
import wahyono.puji.projectskripsi.ws.Msg;

/**
 * Created by ai on 18/08/2017.
 */

public class ItemBrgAdapter extends RecyclerView.Adapter<ItemBrgAdapter.ItemBrgHolder>{
    private ItemTemp a;
    private List<ItemBrg>l;

    public ItemBrgAdapter(ItemTemp a, List<ItemBrg> l) {
        this.a = a;
        this.l = l;
    }

    @Override
    public ItemBrgHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(a).inflate(R.layout.item_brg_list,parent,false);
        return new ItemBrgHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemBrgHolder holder, int position) {
        final ItemBrg i=l.get(position);
        holder.hrg.setText(i.getHrg());
        holder.jum.setText(""+i.getQty());
        holder.nm.setText(i.getMenu());
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hapus(i);
            }
        });
    }

    private void hapus(ItemBrg i) {
        Retrofit r=new Retrofit.Builder().baseUrl(a.getString(R.string.api_url)).
                addConverterFactory(GsonConverterFactory.create()).build();
        Api a=r.create(Api.class);
        a.delItemPesan(this.a.nota,i.getKode()).enqueue(new Callback<Msg>() {
            @Override
            public void onResponse(Call<Msg> call, Response<Msg> response) {
                Msg m=response.body();
                proses(m);
            }
            @Override
            public void onFailure(Call<Msg> call, Throwable t) {
                Toast.makeText(ItemBrgAdapter.this.a,"Gagal Hapus Item",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void proses(Msg m) {
        if("Error"!=m.getPesan()){
            a.muat();
        }else Toast.makeText(ItemBrgAdapter.this.a,"Gagal Hapus Item",Toast.LENGTH_LONG).show();
    }

    @Override
    public int getItemCount() {
        return l.size();
    }

    public class ItemBrgHolder extends RecyclerView.ViewHolder{
        public TextView nm,jum,hrg;
        public ImageButton del;

        public ItemBrgHolder(View v) {
            super(v);
            nm=(TextView)v.findViewById(R.id.nmItemBrg);
            jum=(TextView)v.findViewById(R.id.jumItemBrg);
            hrg=(TextView)v.findViewById(R.id.hrgItemBrg);
            del=(ImageButton)v.findViewById(R.id.delItemBrg);
        }
    }
}