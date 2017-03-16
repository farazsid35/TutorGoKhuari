package wolfsoft.ozzon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.ParseUser;

import customfonts.MyEditText;

public class ActivitySignup extends AppCompatActivity {

  ImageView signupback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        signupback = (ImageView)findViewById(R.id.signupback);


        signupback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent it = new Intent(ActivitySignup.this, MainActivity.class);
                startActivity(it);

            }
        });

    }
    public void OnCreateClick(View v){
        if(v.getId()==R.id.create){
            MyEditText fullname = (MyEditText)findViewById(R.id.fullname);
            MyEditText email = (MyEditText)findViewById(R.id.email);
            MyEditText username = (MyEditText)findViewById(R.id.username);
            MyEditText password = (MyEditText)findViewById(R.id.password);
            String fname = fullname.getText().toString();
            String mail = email.getText().toString();
            String uname = username.getText().toString();
            String pass = password.getText().toString();
            System.out.println(fname);
            //Intent s = new Intent(this, ActivitySignin.class);
            //startActivity(s);


                saveData(fname,mail,uname,pass);

            finish();
        }
    }
    public void saveData(String fn, String e, String un, String p1){
        ParseUser newUser = new ParseUser();
        newUser.put("Fullname", fn);
        newUser.setUsername(un);
        newUser.setPassword(p1);
        newUser.setEmail(e);
        newUser.signUpInBackground();
        System.out.println(e);
    }
}
