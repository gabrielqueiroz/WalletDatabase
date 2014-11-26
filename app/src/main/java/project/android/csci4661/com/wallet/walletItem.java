package project.android.csci4661.com.wallet;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import project.android.csci4661.com.walletDatabase.DatabaseHandler;
import project.android.csci4661.com.walletDatabase.item;


public class walletItem extends Activity {

    private final String dbName = "myWallet2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();

        String value = "wallet";
        if(extras != null)
            value = extras.getString("item");

        DatabaseHandler db = new DatabaseHandler(this);
        item item = db.getItemByName(value);

        TextView textBalance = (TextView) findViewById(R.id.balance);
        textBalance.setText(String.valueOf(item.getValue()));
        TextView textTest = (TextView) findViewById(R.id.textTest);
        textTest.setText(value);

        setContentView(R.layout.activity_wallet_item);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.wallet_item, menu);
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

    /**
     try {
     sampleDB = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);

     Cursor c = sampleDB.rawQuery("SELECT itemName, itemValue FROM items WHERE itemName = \"" + value + "\"",null);

     if(c.moveToFirst()) {
     c.moveToFirst();
     item.setName(c.getString(c.getColumnIndex("itemName")));
     item.setValue(c.getDouble(c.getColumnIndex("itemValue")));
     c.close();
     } else
     item = new item(0,"Default",00.0,"Default");


     } catch (SQLiteException se){
     Toast.makeText(getApplicationContext(), "Couldn't OPEN the database", Toast.LENGTH_LONG).show();
     } finally {
     if (sampleDB!=null)
     sampleDB.close();
     }
     */

}
