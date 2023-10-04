package database;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Objects")
public class Objects {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "object_name")
    public String name;

    @ColumnInfo(name = "quantity")
    public int quantity;

}
