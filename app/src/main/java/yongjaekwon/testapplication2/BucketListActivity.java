package yongjaekwon.testapplication2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class BucketListActivity extends AppCompatActivity {
    ArrayList<BucketItem> mBucketItems;
    RecyclerView rvContacts;
    BucketListAdapter adapter;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvContacts = (RecyclerView) findViewById(R.id.rvContacts);
        // Initialize contacts
        try {
            mBucketItems = BucketItem.createContactsList(2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Create adapter passing in the sample user data
        adapter = new BucketListAdapter(this, mBucketItems);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        // That's all!
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

    public void addItem(View view) {
        Intent addActivity = new Intent(this, AddItemActivty.class);
        startActivityForResult(addActivity, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Name: " + data.getStringExtra("name"), Toast.LENGTH_LONG);
                BucketItem new_bi = new BucketItem(data.getStringExtra("name"), new Date(), false);
                mBucketItems.add(new_bi);
                adapter.notifyDataSetChanged();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }


    public void sendMessage(View view) {
        TextView currentItem = (TextView) view;
        String text = currentItem.getText().toString();
        Log.d("ListExample", "sendMessage to " + text);
        Toast.makeText(this, "Sending message to " + text, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
