package wolfsoft.ozzon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

import customfonts.MyEditText;

public class ActivitySignin extends AppCompatActivity {


     ImageView signinback;
     MyEditText username;
     MyEditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        signinback = (ImageView)findViewById(R.id.signinback);


        signinback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ActivitySignin.this,MainActivity.class);
                startActivity(it);
            }
        });
    }

    public void OnSignInClick(View v){
        if(v.getId()==R.id.signin){
            username = (MyEditText)findViewById(R.id.username);
            password = (MyEditText)findViewById(R.id.password);
            String user = username.getText().toString();
            String pass = password.getText().toString();
            login(user, pass);
            savedata(user, pass);
        }
    }

    public void savedata(String s, String p){
        ParseObject test = new ParseObject("Test");
        test.put("username", String.valueOf(s));
        test.put("password", String.valueOf(p));
        test.saveInBackground();
    }
    public void login(final String u, String pass){
        //ParseUser newUser;
        //ParseException e;
        ParseUser.logInInBackground(u, pass, new LogInCallback() {
            @Override
            public void done(ParseUser newUser, ParseException e) {
                if (newUser != null){
                    Intent i = new Intent(ActivitySignin.this, UserProfile.class);
                    i.putExtra("Username", u);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast error = Toast.makeText(ActivitySignin.this, "User does not exist, Please Signup", Toast.LENGTH_SHORT);
                    error.show();
                }
                //moveTaskToBack(true);

            }
        });

    }

}
