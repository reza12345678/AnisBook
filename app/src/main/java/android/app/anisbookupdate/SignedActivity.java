package android.app.anisbookupdate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.anisbookupdate.Adapter.signed_adapter;
import android.app.anisbookupdate.Utilities.MyApplication;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import static android.app.anisbookupdate.Utilities.MyApplication.myDbHelper;
import static android.app.anisbookupdate.Utilities.Utility.toast;

public class SignedActivity extends AppCompatActivity {

    Cursor cur = null;
    ListView lstSigned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed);

        bind();
        loadData();
    }

    private void bind() {
        lstSigned = findViewById(R.id.lstSigned);
    }

    private void loadData() {
        try {
            myDbHelper.openDataBase();
        } catch (SQLException e) {
            toast(e.toString());
        }

        cur =myDbHelper.get_all("favorite", 1);
        ArrayList<String> theList = new ArrayList<>();

        if (cur.getCount() == 0)
            return;
        else {
            while (cur.moveToNext())
                theList.add(cur.getString(2));
        }

        signed_adapter adapter =new signed_adapter(MyApplication.mContex, theList);
        lstSigned.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        myDbHelper.close();
        this.finish();
    }
}