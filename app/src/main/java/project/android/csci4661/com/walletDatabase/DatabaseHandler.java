package project.android.csci4661.com.walletDatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by gabrielqueiroz on 11/25/14.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String dbName = "myWallet2";
    private static final int dbVersion = 1;

    private static final String tableName = "items";

    private static final String itemId = "itemId";
    private static final String itemName = "itemName";
    private static final String itemValue = "itemValue";
    private static final String itemImage = "itemImage";


    private final item item1 = new item(1,"Wallet",99.9,"Wallet");
    private final item item2 = new item(2,"Bank",99.9,"Bank");
    private final item item3 = new item(3,"CreditCard",99.9,"CreditCard");
    private final item[] defaultItems = new item[]{item1,item2,item3};

    public DatabaseHandler(Context context){
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sampleDB) {
        sampleDB.execSQL("CREATE TABLE IF NOT EXISTS "+tableName+"(itemId INTEGER PRIMARY KEY AUTOINCREMENT, itemName TEXT, itemValue REAL, itemImage TEXT);");

        for(item item : defaultItems)
            sampleDB.execSQL("INSERT INTO "+tableName+" Values ('"+String.valueOf(item.getId())+"', " +
                    "'"+item.getName()+"', '"+String.valueOf(item.getValue())+"', '"+item.getImage()+"');");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sampleDB, int oldVersion, int newVersion) {
        sampleDB.execSQL("DROP TABLE IF EXISTS "+tableName);
        onCreate(sampleDB);
    }

    public item getItemByName(String name){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.query(tableName, new String[]{itemId,itemName,itemValue,itemImage},itemName+"=?",new String[]{name},null,null,null,null);

        if(c!=null)
            c.moveToFirst();

        item itemQuery = new item(Integer.parseInt(c.getString(0)),c.getString(1),Double.parseDouble(c.getString(2)),c.getString(3));

        return itemQuery;
    }


    public ArrayList<String> getAllItemsNames(){
        ArrayList<String> results = new ArrayList<String>();
        SQLiteDatabase sampleDB = this.getReadableDatabase();

        Cursor c = sampleDB.rawQuery("SELECT itemName FROM "+ tableName,null);

        if (c!=null){
            if (c.moveToFirst()){
                do {
                    String firstName = c.getString(c.getColumnIndex("itemName"));
                    results.add(firstName);
                } while (c.moveToNext());
                c.close();
            }
        }
        return results;
    }
}
