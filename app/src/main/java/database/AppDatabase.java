package database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Mats.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MatsDao MatsDao();
}