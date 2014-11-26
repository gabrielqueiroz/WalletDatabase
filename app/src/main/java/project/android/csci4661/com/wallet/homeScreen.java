package project.android.csci4661.com.wallet;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.app.ListActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import project.android.csci4661.com.walletDatabase.DatabaseHandler;
import project.android.csci4661.com.walletDatabase.item;

public class homeScreen extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseHandler db = new DatabaseHandler(this);

        ArrayList<String> results = db.getAllItemsNames();

        ArrayAdapter<String> aAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,results);
        this.setListAdapter(aAdapter);

        getListView().setOnItemClickListener(itemClickListener);

    }

    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            Intent myIntent = new Intent(homeScreen.this, walletItem.class);
            String item = ((TextView) view).getText().toString();
            myIntent.putExtra("item", item);
            homeScreen.this.startActivity(myIntent);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public interface itemClickListener{

        public void onContactSelected(long rowID);
    }

    /**

     private final String dbName = "myWallet2";
     private final String tableName = "items";

     private final item item1 = new item(1,"wallet",99.9,"wallet");
     private final item item2 = new item(2,"Bank",99.9,"Bank");
     private final item item3 = new item(3,"CreditCard",99.9,"CreditCard");
     private final item[] defaultItems = new item[]{item1,item2,item3};

     try {
     sampleDB = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);
     sampleDB.execSQL("CREATE TABLE IF NOT EXISTS "+tableName+"(itemId INTEGER PRIMARY KEY AUTOINCREMENT, itemName TEXT, itemValue REAL, itemImage TEXT);");

     for(item item : defaultItems)
     sampleDB.execSQL("INSERT INTO "+tableName+" Values ('"+String.valueOf(item.getId())+"', " +
     "'"+item.getName()+"', '"+String.valueOf(item.getValue())+"', '"+item.getImage()+"');");

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

     ArrayAdapter<String> aAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,results);
     this.setListAdapter(aAdapter);

     } catch (SQLiteException se){
     Toast.makeText(getApplicationContext(), "Couldn't CREATE or OPEN the database", Toast.LENGTH_LONG).show();
     } finally {
     if (sampleDB!=null){
     sampleDB.execSQL("DELETE FROM "+tableName);
     sampleDB.close();
     }
     }
     */

}
