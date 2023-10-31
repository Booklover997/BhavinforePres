package database;

import androidx.room.Database;
import androidx.room.RoomDatabase;


//Increment version number when changes are made to Mats class
@Database(entities = {Mats.class}, version = 7)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MatsDao MatsDao();
}