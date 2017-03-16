package wolfsoft.ozzon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.CountCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by Farazsid on 12/25/2016.
 */
public class UserProfile extends Activity {
    TextView ads;
    ParseUser currentuser = ParseUser.getCurrentUser();
    int c;
    String num;
    ListView listView;
    List<ParseObject> ob;
    ArrayAdapter<String> adapter;
    MyParseAdapter SearchAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofile);
        TextView fn = (TextView)findViewById(R.id.fullname);
        fn.setText(currentuser.getString("Fullname"));
        ads = (TextView)findViewById(R.id.noad);
        listView = (ListView) findViewById(R.id.myads);
        adapter = new ArrayAdapter<String>(this, R.layout.myviewitem);
        CountSubjects();
        AdListSetup();
    }

    public void OnMenuClick(View v){
        if(v.getId()==R.id.menu){
            Intent i = new Intent(this,Menu.class);
            startActivity(i);
            finish();
        }
    }
    public void CountSubjects(){
        //final String num;



        ParseQuery query = new ParseQuery("Tutor");
        query.whereEqualTo("Name", currentuser.getUsername());
        query.countInBackground(new CountCallback() {
            @Override
            public void done(int count, ParseException e) {
                if(e == null){
                    Log.e("ABC", "Number of ads are" + count);
                    System.out.println(count);
                    c = count;
                    System.out.println(c);
                    num = String.valueOf(count);
                    System.out.println(num);
                    ads.setText(num);
                }
            }
        });



        //countAdapter = new MyParseAdapter(this, factory);
        //countAdapter.setTextKey(num);
        //num = String.valueOf(c);
        //System.out.println(num);
        //filledAds = (TextView)findViewById(R.id.Tads);
        //filledAds.setText(num);
    }
    public void AdListSetup(){
        ParseQueryAdapter.QueryFactory<ParseObject>factory = new ParseQueryAdapter.QueryFactory<ParseObject>(){
            @Override
            public ParseQuery create(){
                ParseQuery query = new ParseQuery("Tutor");
                query.whereEqualTo("Name",currentuser.getUsername());
                try{
                    ob = query.find();
                }
                catch (ParseException e){
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
                return query;
            }
        };
        SearchAdapter = new MyParseAdapter(this, factory);
        SearchAdapter.setTextKey("Subject");
        listView.setAdapter(SearchAdapter);
        listView.setSelection(1);
        listView.setOnItemClickListener(new resultListView());
        /*ParseQuery<ParseObject> disp = ParseQuery.getQuery("SearchResults");
        disp.whereEqualTo("Uname", currentuser.getUsername());
        try{
            ob = disp.find();
        }
        catch (ParseException e){
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        adapter = new ArrayAdapter<String>(this, R.layout.listview_item);
        for(ParseObject results:ob){
            adapter.add((String)results.get("Name"));
        }
        listView.setAdapter(adapter);*/
    }
    class resultListView implements ListView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ParseObject theSelectedObject = SearchAdapter.getItem(position);
            Intent i = new Intent(UserProfile.this,AdView.class);
            //i.putExtra("name", theSelectedObject.getString("Name").toString());
            i.putExtra("subject",theSelectedObject.getString("Subject").toString());
            i.putExtra("city",theSelectedObject.getString("City").toString());
            i.putExtra("area",theSelectedObject.getString("Area").toString());
            i.putExtra("level",theSelectedObject.getString("Level").toString());
            i.putExtra("price",theSelectedObject.getString("Price").toString());
            i.putExtra("contact",theSelectedObject.getString("Contact"));
            startActivity(i);

        }
    }
}
