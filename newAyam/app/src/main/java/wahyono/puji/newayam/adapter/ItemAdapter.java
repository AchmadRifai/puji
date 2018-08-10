package wahyono.puji.newayam.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import wahyono.puji.newayam.R;
import wahyono.puji.newayam.beans.Iteme;

public class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {
    private Activity a;
    private List<Iteme>li;

    public ItemAdapter(Activity a, List<Iteme> li) {
        this.a = a;
        this.li = li;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.iteme, parent, false);
        return new ItemHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Iteme i = li.get(position);
        holder.setData(a, i);
    }

    @Override
    public int getItemCount() {
        return li.size();
    }
}
