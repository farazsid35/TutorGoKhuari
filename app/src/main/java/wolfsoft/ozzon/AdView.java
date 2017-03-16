package wolfsoft.ozzon;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Farazsid on 1/2/2017.
 */
public class AdView extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adview);
        String City =getIntent().getStringExtra("city");
        String Area = getIntent().getStringExtra("area");
        String Subject = getIntent().getStringExtra("subject");
        String Level = getIntent().getStringExtra("level");
        String Price = getIntent().getStringExtra("price");
        String Contact = getIntent().getStringExtra("contact");
        TextView c = (TextView)findViewById(R.id.mycity);
        TextView a = (TextView)findViewById(R.id.myarea);
        TextView s = (TextView)findViewById(R.id.mysub);
        TextView l = (TextView)findViewById(R.id.mylevel);
        TextView p = (TextView)findViewById(R.id.myprice);
        TextView co = (TextView)findViewById(R.id.mycontact);
        c.setText(City);
        a.setText(Area);
        s.setText(Subject);
        l.setText(Level);
        p.setText(Price);
        co.setText(Contact);
    }
}
