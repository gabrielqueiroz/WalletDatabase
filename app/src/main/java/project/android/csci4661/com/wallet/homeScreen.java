package project.android.csci4661.com.wallet;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.app.ListActivity;

import java.util.ArrayList;
import java.util.Collections;

import project.android.csci4661.com.walletDatabase.DatabaseConnector;


public class homeScreen extends ListActivity {

    private static final String ITEMS = "items";
    private SharedPreferences savedItems;
    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        DatabaseConnector databaseConnector = new DatabaseConnector(getParent());

//        Cursor cursor = databaseConnector.getAllItems();

        savedItems = getSharedPreferences(ITEMS, MODE_PRIVATE);

        items = new ArrayList<String>(savedItems.getAll().keySet());
        Collections.sort(items, String.CASE_INSENSITIVE_ORDER);

        adapter = new ArrayAdapter<String>(this, R.layout.list_item, items);
        setListAdapter(adapter);

        getListView().setOnItemClickListener(itemClickListener);

  //      databaseConnector.getAllItems();

        SharedPreferences.Editor preferencesEditor = savedItems.edit();

  //      cursor.moveToFirst();

        for(int i=0;i<3;i++) {
  //          int nameIndex = cursor.getColumnIndex("name");
  //          preferencesEditor.putString(cursor.getString(nameIndex), cursor.getString(nameIndex));
  //          cursor.moveToNext();
        }

        preferencesEditor.apply();

    }

    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view,
                                int position, long id)
        {
            Intent myIntent = new Intent(homeScreen.this, walletItem.class);
            String value = String.valueOf(position)+" Wallet Item";
            myIntent.putExtra("key", value);
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


}
