package database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MatsDao {
    @Query("SELECT * FROM Mats")
     List<Mats> getAll();
    @Query("DELETE FROM Mats")
    void deleteAll();


    @Query("SELECT * FROM Mats WHERE mat_name LIKE :name LIMIT 1")
    Mats getMatsByName(String name);
    @Insert
    void insert(Mats mats);
    @Delete
    void delete(Mats mats);
    @Update
    void update(Mats mats);

}
