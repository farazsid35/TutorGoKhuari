package com.example.farazsid.tutorgokhuari;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.parse.Parse;
import com.parse.ParseObject;

import wolfsoft.ozzon.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("t50cXEYb7gTTXNn8090Pqumn6PjE3suSEeo0MlDu")
                .clientKey("QirKZoxYze4cX9Yejz48HXf451obgOdrw4U7rJtS")
                .server(" https://parseapi.back4app.com") // The trailing slash is important.



                .build()
        );
        ParseObject score = new ParseObject("Score");
        score.put("user", "islam");
        score.put("score", 10000);
        score.saveInBackground();
    }
}
