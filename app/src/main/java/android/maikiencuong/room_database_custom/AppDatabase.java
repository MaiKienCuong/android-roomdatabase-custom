package android.maikiencuong.room_database_custom;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Address.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract AddressDao addressDao();

}