package project.android.csci4661.com.walletDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**
 * Created by gabrielqueiroz on 11/24/14.
 */
public class DatabaseConnector {
    private static final String DATABASE_NAME = "WalletItems";
    private SQLiteDatabase database; // for interacting with the database
    private DatabaseOpenHelper databaseOpenHelper; // creates the database

    public DatabaseConnector(Context context)
    {
        databaseOpenHelper =
                new DatabaseOpenHelper(context, DATABASE_NAME, null, 1);
    }

    public void open() throws SQLException
    {
        database = databaseOpenHelper.getWritableDatabase();
    }

    public void close()
    {
        if (database != null)
            database.close();
    }

    public void updateItem(item item)
    {
        ContentValues editItem = new ContentValues();
        editItem.put("id_item", item.getId());
        editItem.put("name", item.getName());
        editItem.put("value", item.getValue());
        editItem.put("image", item.getImage());

        open();
        database.update("items", editItem, "id_item=" + item.getId(), null);
        close();
    }
/**
    public Cursor getAllItems()
    {
        return database.query("items", new String[] {"id_item", "name"},null,null,null,null,null);
    }
*/
    private class DatabaseOpenHelper extends SQLiteOpenHelper
    {
        public DatabaseOpenHelper(Context context, String name,
                                  CursorFactory factory, int version)
        {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            String createQuery = "CREATE TABLE items" +
                    "(id_item integer primary key," +
                    "name TEXT, value REAL, image TEXT);";
            db.execSQL(createQuery);

            String createQuery1 = "CREATE TABLE historic" +
                    "(id_historic integer primary key," +
                    "id_item integer"+
                    "date DATE, value REAL, local TEXT, transaction TEXT," +
                    "FOREIGN KEY(id_item) REFERENCES item(id_item)";
            db.execSQL(createQuery1);

            String insertQuery = "INSERT INTO items VALUES (1, wallet, 9.99, wallet);"+
                                 "INSERT INTO items VALUES (2, bank, 49.99, bank);"+
                                 "INSERT INTO items VALUES (3, creditCard, 99.99, creditCard);";
            db.execSQL(insertQuery);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion)
        {
        }
    }

}
