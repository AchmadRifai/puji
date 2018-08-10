package wahyono.puji.newayam.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import wahyono.puji.newayam.R;
import wahyono.puji.newayam.beans.Menu;

public class MenuAdapter extends RecyclerView.Adapter<MenuHolder>{
    private Activity a;
    private List<Menu>lm;

    public MenuAdapter(Activity a, List<Menu> lm) {
        this.a = a;
        this.lm = lm;
    }

    @NonNull
    @Override
    public MenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu, parent, false);
        return new MenuHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuHolder holder, int position) {
        Menu m = lm.get(position);
        holder.setData(a, m);
    }

    @Override
    public int getItemCount() {
        return lm.size();
    }
}
