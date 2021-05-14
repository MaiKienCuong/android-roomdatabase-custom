package android.maikiencuong.room_database_custom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.maikiencuong.room_database_basic.R;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SendingData {

    private ArrayList<Address> addressList;

    private Button btnSave, btnCancel;
    private TextView tvAdd;
    private RecyclerView listView;
    private AddressAdapter adapter;
    private int id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);
        tvAdd = findViewById(R.id.tvAdd);
        listView = findViewById(R.id.recyclerview);

        addressList = new ArrayList<>();

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "address_db_room_custom")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

//        addressList.add(new Address("Hà Nội"));
//        addressList.add(new Address("Hồ Chí Minh"));
//        for (Address address : addressList) {
//            db.addressDao().insertAll(address);
//        }


        adapter = new AddressAdapter(db, MainActivity.this);
        listView.setAdapter(adapter);
        listView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Address address = new Address(tvAdd.getText().toString().trim());
                if (id != -1 && !address.getName().trim().isEmpty()) {
                    address.setId(id);
                    db.addressDao().update(address);
                } else if (id == -1 && !address.getName().trim().isEmpty()) {
                    db.addressDao().insertAll(address);
                }
                adapter.notityDataChanged();
                tvAdd.setText("");
                id = -1;
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvAdd.setText("");
                adapter.notityDataChanged();
            }
        });

    }

    @Override
    public void sendData(Serializable obj) {
        Address address = (Address) obj;
        tvAdd.setText(address.getName());
        id = address.getId();
    }
}