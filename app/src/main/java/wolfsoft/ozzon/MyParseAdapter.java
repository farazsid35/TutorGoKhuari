package wolfsoft.ozzon;

import android.content.Context;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

/**
 * Created by Farazsid on 12/29/2016.
 */
public class MyParseAdapter extends ParseQueryAdapter {
    public MyParseAdapter(Context context, QueryFactory<ParseObject> queryFactory) {
        super(context, queryFactory);
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }
}
