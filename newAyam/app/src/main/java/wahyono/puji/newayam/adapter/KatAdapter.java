package wahyono.puji.newayam.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import wahyono.puji.newayam.R;
import wahyono.puji.newayam.beans.Kat;

public class KatAdapter extends RecyclerView.Adapter<KatHolder>{
    private Activity a;
    private List<Kat>lk;

    public KatAdapter(Activity a, List<Kat> lk) {
        this.a = a;
        this.lk = lk;
    }

    @NonNull
    @Override
    public KatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.kat, parent, false);
        return new KatHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull KatHolder holder, int position) {
        Kat k = lk.get(position);
        holder.setData(a, k);
    }

    @Override
    public int getItemCount() {
        return lk.size();
    }
}
