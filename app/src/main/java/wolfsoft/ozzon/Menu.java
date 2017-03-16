package wolfsoft.ozzon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.parse.ParseUser;

/**
 * Created by Farazsid on 12/27/2016.
 */
public class Menu extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
    }
    public void OnLogoutClick(View v){
        if(v.getId()==R.id.logout){
            if(v.getId() == R.id.logout){
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser();
                Intent i = new Intent(this, ActivitySignin.class);
                startActivity(i);
                finish();
            }
        }
    }
    public void OnHomeClick(View v){
        if(v.getId()==R.id.home){
            Intent bck = new Intent(this, UserProfile.class);
            startActivity(bck);
            finish();
        }
    }
    public void OnAdvertiseClick(View v){
        if(v.getId()==R.id.advertise){
            Intent adv = new Intent(this,Advertise.class);
            startActivity(adv);
            finish();
        }
    }
    public void OnSearchClick(View v){
        if(v.getId()==R.id.searchtutor){
            Intent srch = new Intent(this,Search.class);
            startActivity(srch);

        }
    }
}
