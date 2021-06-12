package android.app.anisbookupdate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.anisbookupdate.Adapter.signed_adapter;
import android.app.anisbookupdate.Utilities.MyApplication;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.app.anisbookupdate.Utilities.MyApplication.myDbHelper;
import static android.app.anisbookupdate.Utilities.Utility.toast;

public class SignedActivity extends AppCompatActivity {

    Cursor cur = null;
    ListView lstSigned;

    public static Map<String, Integer> mapList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed);

        bind();
        loadData();
    }

    private void bind() {
        lstSigned = findViewById(R.id.lstSigned);
        mapList = new HashMap<String, Integer>();

        lstSigned.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tblAnis_id = (String) parent.getItemAtPosition(position);

                int value = (int) mapList.get(tblAnis_id);
                Hawk.put("tblAnis_id", value);

                Intent contain_activity = new Intent(MyApplication.mContex, ContainerActivity.class);
                startActivity(contain_activity);
            }
        });
    }

    private void loadData() {
        try {
            myDbHelper.openDataBase();
        } catch (SQLException e) {
            toast(e.toString());
        }

        cur = myDbHelper.get_all("favorite", 1);
        ArrayList<String> theList = new ArrayList<>();

        if (cur.getCount() == 0)
            return;
        else {
            while (cur.moveToNext()) {
                theList.add(cur.getString(2));
                mapList.put(cur.getString(2), cur.getInt(0));
            }
        }


        signed_adapter adapter = new signed_adapter(MyApplication.mContex, theList);
        lstSigned.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        myDbHelper.close();
        this.finish();
    }
}