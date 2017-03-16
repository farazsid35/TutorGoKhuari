package wolfsoft.ozzon;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.DeleteCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by Farazsid on 12/31/2016.
 */
public class SingleView extends Activity {
    ParseUser current_user = ParseUser.getCurrentUser();
    ParseObject searchResult = new ParseObject("SearchResults");
    String tel;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publicprofile);
        String Name =getIntent().getStringExtra("name");
        String Subject = getIntent().getStringExtra("subject");
        String Level = getIntent().getStringExtra("level");
        String Price = getIntent().getStringExtra("price");
        String Contact = getIntent().getStringExtra("contact");
        TextView n = (TextView)findViewById(R.id.selectedname);
        TextView s = (TextView)findViewById(R.id.selectedsubject);
        TextView l = (TextView)findViewById(R.id.selectedlevel);
        TextView p = (TextView)findViewById(R.id.selectedprice);
        TextView c = (TextView)findViewById(R.id.selectecontact);
        n.setText(Name);
        s.setText(Subject);
        l.setText(Level);
        p.setText(Price);
        c.setText(Contact);
        tel = c.getText().toString();
    }
    public void OnContactClick(View v) {
        if (v.getId() == R.id.selectecontact) {
            System.out.println(tel);
            Intent phoneIntent = new Intent(Intent.ACTION_CALL);
            phoneIntent.setData(Uri.parse("tel:"+tel));
            if (ActivityCompat.checkSelfPermission(SingleView.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            startActivity(phoneIntent);
        }
    }
}
