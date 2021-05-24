package android.maikiencuong.room_database_custom;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView tvName;
    public ImageButton btnEdit, btnDelete;

    private ItemClickListenner itemClickListenner;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        itemView.setOnClickListener(this);

        tvName = itemView.findViewById(R.id.tvName);
        btnEdit = itemView.findViewById(R.id.imgbtnEdit);
        btnDelete = itemView.findViewById(R.id.imgbtnDelete);

    }

    public void setItemClickListenner(ItemClickListenner itemClickListenner) {
        this.itemClickListenner = itemClickListenner;
    }


    @Override
    public void onClick(View v) {
        itemClickListenner.onclick(v, getAdapterPosition(), false);
    }
}
