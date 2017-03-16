package wolfsoft.ozzon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseObject;

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

        final ImageView iv = (ImageView)findViewById(R.id.logo);
        final Animation an = AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate);
        final Animation an2 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.fade_out);
        iv.startAnimation(an);
        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv.startAnimation(an2);
                finish();
                Intent go = new Intent(MainActivity.this, Home.class);
                startActivity(go);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
