package android.maikiencuong.room_database_custom;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AddressAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private ArrayList<Address> addressList;
    private Context context;
    private AppDatabase appDatabase;

    public AddressAdapter(AppDatabase appDatabase, Context context) {
        this.appDatabase = appDatabase;
        this.context = context;
        notityDataChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Address address = addressList.get(position);
        holder.tvName.setText(address.getId() + ". " + address.getName().toString());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appDatabase.addressDao().delete(address);
                notityDataChanged();
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendingData s = (SendingData) context;
                s.sendData(address);
            }
        });

        holder.setItemClickListenner(new ItemClickListenner() {
            @Override
            public void onclick(View view, int position, boolean isLongClick) {
                view.setBackgroundColor(Color.argb(100, 255, 0, 0));
            }
        });

    }

    public void notityDataChanged() {
        addressList = (ArrayList<Address>) appDatabase.addressDao().getAll();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

}
