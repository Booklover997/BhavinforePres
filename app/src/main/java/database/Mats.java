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
    public String mat_name;

    @ColumnInfo(name = "quantity")
    public int quantity;

    @ColumnInfo(name = "wood_type")
    public String wood_type;

    @ColumnInfo(name = "mineral_type")
    public String mineral_type;

    @ColumnInfo(name="wood_quantity")
    public int wood_quantity;

    @ColumnInfo(name="mineral_quantity")
    public int mineral_quantity;

    @ColumnInfo(name="sell_value")
    public int sell_value;

    @ColumnInfo(name="purchase_value")
    public int purchase_value;


}
