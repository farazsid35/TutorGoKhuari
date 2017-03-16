package wolfsoft.ozzon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by Farazsid on 12/31/2016.
 */
public class SearchResults extends Activity {
    ListView listView;
    List<ParseObject> ob;
    ArrayAdapter<String> adapter;
    ParseUser currentuser = ParseUser.getCurrentUser();
    MyParseAdapter SearchAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchresults);
        listView = (ListView) findViewById(R.id.SearchResults);
        adapter = new ArrayAdapter<String>(this, R.layout.myviewitem);
        final ImageView iv = (ImageView)findViewById(R.id.buffer);
        final Animation an = AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate2);
        final Animation an2 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.fade_out);
        iv.startAnimation(an);
        TutorListSetup();
        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv.startAnimation(an2);
                iv.setImageDrawable(null);
                TutorListSetup();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    public void TutorListSetup(){
        ParseQueryAdapter.QueryFactory<ParseObject>factory = new ParseQueryAdapter.QueryFactory<ParseObject>(){
            @Override
            public ParseQuery create(){
                ParseQuery query = new ParseQuery("SearchResults");
                query.whereEqualTo("Uname",currentuser.getUsername());
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
        SearchAdapter.setTextKey("Name");
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
            Intent i = new Intent(SearchResults.this,SingleView.class);
            i.putExtra("name", theSelectedObject.getString("Name").toString());
            i.putExtra("subject",theSelectedObject.getString("Subject").toString());
            i.putExtra("level",theSelectedObject.getString("Level").toString());
            i.putExtra("price",theSelectedObject.getString("Price").toString());
            i.putExtra("contact",theSelectedObject.getString("Contact"));
            startActivity(i);

        }
    }
}
