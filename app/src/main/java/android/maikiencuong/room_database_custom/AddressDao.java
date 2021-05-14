package android.maikiencuong.room_database_custom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AddressDao {

    @Query("SELECT * FROM Address")
    List<Address> getAll();

    @Query("SELECT * FROM Address WHERE id IN (:addressIds)")
    List<Address> loadAllByIds(int[] addressIds);

    @Query("SELECT * FROM Address WHERE name LIKE :name LIMIT 1")
    Address findByName(String name);

    @Update
    void update(Address address);

    @Insert
    void insertAll(Address... addresses);

    @Delete
    void delete(Address address);

}
