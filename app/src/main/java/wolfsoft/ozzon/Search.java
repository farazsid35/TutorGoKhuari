package wolfsoft.ozzon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.test.ActivityTestCase;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by Farazsid on 12/30/2016.
 */
public class Search extends Activity {
    ParseUser currentUser = ParseUser.getCurrentUser();
    Spinner SearchSubject;
    Spinner SearchLevel;
    Spinner SearchCity;
    Spinner SearchArea;
    MyParseAdapter subjectAdapter;
    MyParseAdapter cityAdapter;
    MyParseAdapter areaAdapter;
    MyParseAdapter levelAdapter;
    ParseObject subObject;
    ParseObject cityObject;
    ParseObject addObject;
    ParseObject levelObject;
    String subname;
    String levelname;
    String cityname;
    String areaname;
    ParseObject tutor;
    String tname;
    String tsubject;
    String tlevel;
    String tcity;
    String tarea;
    String[] Tname;
    String[]Tsubject;
    String[]Tlevel;
    String[]Tcity;
    String[]Tarea;
    String tcontact;
    String tprice;
    int i = 0;
    boolean found;
    //ParseObject saveResult;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        ParseObject saveResult = new ParseObject("SearchResults");
        saveResult.deleteInBackground();
        SearchSubject = (Spinner)findViewById(R.id.searchsub);
        SearchLevel = (Spinner)findViewById(R.id.searchlevel);
        SearchCity = (Spinner)findViewById(R.id.searchcity);
        SearchArea = (Spinner)findViewById(R.id.searcharea);
        saveResult = new ParseObject("UserSearch");
        tutor = new ParseObject("Tutor");
        subjectSpinnerSetup();
        levelSpinnerSetup();
        citySpinnerSetup();
        areaSpinnerSetup();
    }
    public void subjectSpinnerSetup(){
        ParseQueryAdapter.QueryFactory<ParseObject>factory = new ParseQueryAdapter.QueryFactory<ParseObject>(){
            @Override
            public ParseQuery create(){
                ParseQuery query = new ParseQuery("Subject");
                return query;
            }
        };
        subjectAdapter = new MyParseAdapter(this, factory);
        subjectAdapter.setTextKey("Sname");
        SearchSubject.setAdapter(subjectAdapter);
        SearchSubject.setSelection(1);
        SearchSubject.setOnItemSelectedListener(new subjectSpinnerListener());
    }
    public void levelSpinnerSetup(){
        ParseQueryAdapter.QueryFactory<ParseObject>factory = new ParseQueryAdapter.QueryFactory<ParseObject>(){
            @Override
            public ParseQuery create(){
                ParseQuery query = new ParseQuery("Level");
                return query;
            }
        };
        levelAdapter = new MyParseAdapter(this, factory);
        levelAdapter.setTextKey("LevelDesc");
        SearchLevel.setAdapter(levelAdapter);
        SearchLevel.setSelection(1);
        SearchLevel.setOnItemSelectedListener(new levelSpinnerListener());
    }
    public void citySpinnerSetup(){
        ParseQueryAdapter.QueryFactory<ParseObject>factory = new ParseQueryAdapter.QueryFactory<ParseObject>(){
            @Override
            public ParseQuery create(){
                ParseQuery query = new ParseQuery("City");
                return query;
            }
        };
        cityAdapter = new MyParseAdapter(this, factory);
        cityAdapter.setTextKey("Cname");
        SearchCity.setAdapter(cityAdapter);
        SearchCity.setSelection(1);
        SearchCity.setOnItemSelectedListener(new citySpinnerListener());
    }

    public void areaSpinnerSetup(){
        ParseQueryAdapter.QueryFactory<ParseObject>factory = new ParseQueryAdapter.QueryFactory<ParseObject>(){
            @Override
            public  ParseQuery create(){
                ParseQuery query = new ParseQuery("Address");
                return query;
            }
        };
        areaAdapter = new MyParseAdapter(this, factory);
        areaAdapter.setTextKey("AddDesc");
        SearchArea.setAdapter(areaAdapter);
        SearchArea.setSelection(1);
        SearchArea.setOnItemSelectedListener(new AreaSpinnerListener());
    }

    public void areaSpinnerFast(){
        final ParseQueryAdapter.QueryFactory<ParseObject>factory = new ParseQueryAdapter.QueryFactory<ParseObject>(){
            @Override
            public  ParseQuery create(){
                ParseRelation relation = cityObject.getRelation("CiD");
                //ParseQuery query = new ParseQuery("Address");
                ParseQuery query = relation.getQuery();
                return query;

            }
        };
        areaAdapter = new MyParseAdapter(this, factory);
        areaAdapter.setTextKey("AddDesc");
        SearchArea.setAdapter(areaAdapter);
        SearchArea.setSelection(1);
        SearchArea.setOnItemSelectedListener(new AreaSpinnerListener());
    }
    class subjectSpinnerListener implements Spinner.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            ParseObject theSelectedObject = subjectAdapter.getItem(position);
            Log.e("ABC", "Name is: " + theSelectedObject.getString("Sname") + "objectId is: " + theSelectedObject.getObjectId());
            subObject = theSelectedObject;
            //TextView myText = (TextView)view;
            subname = theSelectedObject.getString("Sname");
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    class levelSpinnerListener implements Spinner.OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
            ParseObject theSelectedObject = levelAdapter.getItem(position);
            Log.e("ABC", "Name is: " + theSelectedObject.getString("LevelDesc") + "objectId is: " + theSelectedObject.getObjectId());
            levelObject = theSelectedObject;
            //TextView myText = (TextView)view;
            levelname = theSelectedObject.getString("LevelDesc");
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent){

        }
    }
    class citySpinnerListener implements Spinner.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            ParseObject theSelectedObject = cityAdapter.getItem(position);
            Log.e("ABC", "Name is: " + theSelectedObject.getString("Cname") + "objectId is: " + theSelectedObject.getObjectId());
            cityObject = theSelectedObject;
            //TextView myText = (TextView)view;
            //Cityname = myText.getText().toString();
            areaSpinnerFast();
            //TextView myText = (TextView)view;
            cityname = theSelectedObject.getString("Cname");
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    class AreaSpinnerListener implements Spinner.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            ParseObject theSelectedObject = areaAdapter.getItem(position);
            Log.e("ABC", "Name is: " + theSelectedObject.getString("AddDesc") + "objectId is: " + theSelectedObject.getObjectId());
            addObject = theSelectedObject;
            //TextView myText = (TextView)view;
            areaname = theSelectedObject.getString("AddDesc");
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
    public void SearchTutors(){

        ParseQuery<ParseObject> findtutor = ParseQuery.getQuery("Tutor");
        findtutor.whereNotEqualTo("Name", currentUser.getUsername());
        findtutor.whereEqualTo("Subject", subname);
        findtutor.whereEqualTo("Level", levelname);
        findtutor.whereEqualTo("City", cityname);
        findtutor.whereEqualTo("Area", areaname);
        findtutor.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                Tname = new String[objects.size()];
                Tsubject = new String[objects.size()];
                Tlevel = new String[objects.size()];
                Tcity = new String[objects.size()];
                Tarea = new String[objects.size()];
                if(e == null){
                    found = true;
                    Log.d("ABC", "Retrieved" + objects.size() + " ABC");

                    for(ParseObject tutor : objects){
                        tname=tutor.getString("Name");
                        tsubject=tutor.getString("Subject");
                        tlevel=tutor.getString("Level");
                        tcity=tutor.getString("City");
                        tarea=tutor.getString("Area");
                        tcontact=tutor.getString("Contact");
                        tprice=tutor.getString("Price");
                        SaveResults(currentUser.getUsername(), tname, tsubject, tlevel, tcity, tarea, tcontact, tprice);
                        System.out.println(tname + tsubject + tlevel + tcity + tarea);
                        Tname[i] = tname;
                        Tsubject[i] = tsubject;
                        Tlevel[i] = tlevel;
                        Tcity[i] = tcity;
                        Tarea[i] = tarea;
                        //saveResult.put("Name", tname);
                        //saveResult.put("Subject", tsubject);
                        //saveResult.put("Level", tlevel);
                        //saveResult.put("City", tcity);
                        //saveResult.put("Area", tarea);
                        i++;

                    }
                    System.out.println(Tname[0] + Tname[1]);

                    //SaveResults(tname, tsubject, tlevel, tcity, tarea);
                }
                else{
                    Log.d("ABC", "Error " + e.getMessage());
                    Toast fail = Toast.makeText(Search.this,"Tutor not found",Toast.LENGTH_SHORT);
                    fail.show();
                }
                if(objects == null){
                    Toast fail = Toast.makeText(Search.this,"Tutor not found",Toast.LENGTH_SHORT);
                    fail.show();
                }

            }
        });
        /*for(int i = 0; i<Tname.length; i++){
            saveResult.put("Name", Tname[i]);
            saveResult.put("Subject", Tsubject[i]);
            saveResult.put("City", Tcity[i]);
            saveResult.put("Area", Tarea[i]);
            saveResult.put("Level", Tlevel[i]);
        }*/
    }

    public void SaveResults(String uname, String name, String subject, String level, String city, String area, String contact, String price){
        ParseObject saveResult = new ParseObject("SearchResults");
        saveResult.put("Uname", uname);
        saveResult.put("Name", name);
        saveResult.put("Subject", subject);
        saveResult.put("Level", level);
        saveResult.put("City", city);
        saveResult.put("Area", area);
        saveResult.put("Contact", contact);
        saveResult.put("Price", price);
        saveResult.saveInBackground();
    }

    public void OnSearchClick(View v){
        if(v.getId()==R.id.searchtut){
            System.out.println(tname + tsubject);
            SearchTutors();
            Intent sr = new Intent(this, SearchResults.class);
            startActivity(sr);

        }
    }
}
