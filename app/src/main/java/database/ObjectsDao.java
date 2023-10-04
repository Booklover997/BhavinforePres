package database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ObjectsDao {
    @Query("SELECT * FROM Objects")
    List<Objects> getAll();
    @Query("DELETE FROM Objects")
    void deleteAll();


    @Query("SELECT * FROM Objects WHERE object_name LIKE :name LIMIT 1")
    Mats getMatsByName(String name);
    @Insert
    void insert(Objects objects);
    @Delete
    void delete(Objects objects);
    @Update
    void update(Objects objects);

}