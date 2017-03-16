package wolfsoft.ozzon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.util.ArrayList;

import customfonts.MyEditText;

/**
 * Created by Farazsid on 12/29/2016.
 */
public class Advertise extends Activity {
    ParseUser currentUser = ParseUser.getCurrentUser();
    String name;
    Spinner Subjects;
    Spinner City;
    Spinner address;
    Spinner level;
    MyParseAdapter subjectAdapter;
    MyParseAdapter cityAdapter;
    MyParseAdapter areaAdapter;
    MyParseAdapter levelAdapter;
    ParseObject subObject;
    ParseObject cityObject;
    ParseObject addObject;
    ParseObject levelObject;
    //ArrayList<ItemsModel> data = new ArrayList<>();
    String Cityname;
    String Subjectname;
    String Areaname;
    String Levelname;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advertise);
        City = (Spinner)findViewById(R.id.city);
        Subjects = (Spinner)findViewById(R.id.subject);
        address = (Spinner)findViewById(R.id.area);
        level = (Spinner)findViewById(R.id.level);
        citySpinnerSetup();
        subjectSpinnerSetup();
        areaSpinnerSetup();
        levelSpinnerSetup();


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
        Subjects.setAdapter(subjectAdapter);
        Subjects.setSelection(1);
        Subjects.setOnItemSelectedListener(new subjectSpinnerListener());
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
        level.setAdapter(levelAdapter);
        level.setSelection(1);
        level.setOnItemSelectedListener(new levelSpinnerListener());
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
        City.setAdapter(cityAdapter);
        City.setSelection(1);
        City.setOnItemSelectedListener(new citySpinnerListener());
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
        address.setAdapter(areaAdapter);
        address.setSelection(1);
        address.setOnItemSelectedListener(new AreaSpinnerListener());
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
        address.setAdapter(areaAdapter);
        address.setSelection(1);
        address.setOnItemSelectedListener(new AreaSpinnerListener());
    }
    class subjectSpinnerListener implements Spinner.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            ParseObject theSelectedObject = subjectAdapter.getItem(position);
            Log.e("ABC", "Name is: " + theSelectedObject.getString("Sname") + "objectId is: " + theSelectedObject.getObjectId());
            subObject = theSelectedObject;
            //TextView myText = (TextView)view;
            Subjectname = theSelectedObject.getString("Sname");
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
            Levelname = theSelectedObject.getString("LevelDesc");
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
            Cityname = theSelectedObject.getString("Cname");
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
            Areaname = theSelectedObject.getString("AddDesc");
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
    public void OnAdvertiseClick(View v){
        if(v.getId()==R.id.placead) {
            MyEditText prc = (MyEditText) findViewById(R.id.price);
            MyEditText cont = (MyEditText) findViewById(R.id.contact);
            String price = prc.getText().toString();
            String contact = cont.getText().toString();
            name = currentUser.getUsername();
            SaveData(name, Subjectname, Levelname, Cityname, Areaname, price, contact);
            Intent proceed = new Intent(this,Menu.class);
            startActivity(proceed);
            finish();
        }
    }
    public void SaveData(String name, String subject, String level, String city, String area, String price, String contact){
        ParseObject tutor = new ParseObject("Tutor");
        tutor.put("Name", name);
        tutor.put("Subject", subject);
        tutor.put("Level", level);
        tutor.put("City", city);
        tutor.put("Area", area);
        tutor.put("Price", price);
        tutor.put("Contact", contact);

        tutor.saveInBackground();
    }
}
