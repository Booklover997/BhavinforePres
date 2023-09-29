package database;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Mats")
public class Mats {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "mat_name")
    public String name;

    @ColumnInfo(name = "quantity")
    public int quantity;

}
