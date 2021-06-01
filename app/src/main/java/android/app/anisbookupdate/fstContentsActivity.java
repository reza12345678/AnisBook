package android.app.anisbookupdate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.anisbookupdate.Adapter.fstContent_adapter;
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

import static android.app.anisbookupdate.Utilities.MyApplication.myDbHelper;
import static android.app.anisbookupdate.Utilities.Utility.toast;

public class fstContentsActivity extends AppCompatActivity {

    ListView lstCon;
    Cursor cur = null;
    public static String unit_param;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fst_contents);
        bind();
        load_data();

        lstCon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                unit_param = (String) parent.getItemAtPosition(position);

                try {
                    myDbHelper.openDataBase();
                } catch (SQLException e) {
                    toast(e.toString());
                }

                cur = myDbHelper.get_snd_content(unit_param);
                Hawk.put("unit_param", unit_param);

                if (cur.getCount() > 1) {
                    Intent snd_activity = new Intent(MyApplication.mContex, sndContentsActivity.class);
                    startActivity(snd_activity);
                } else if (cur.getCount() == 0) {
                    return;
                } else {
                    while (cur.moveToNext()) {
                        String lst_title = cur.getString(0);

                        int pa_id_tblAnis = 0;

                        Cursor cursor = myDbHelper.get_title(lst_title);
                        while (cursor.moveToNext())
                            pa_id_tblAnis = cursor.getInt(0);
                        Hawk.put("tblAnis_id", pa_id_tblAnis);
                    }
                    Intent contain_activity = new Intent(MyApplication.mContex, ContainerActivity.class);
                    startActivity(contain_activity);
                }
                cur = null;
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
        myDbHelper.close();
    }

    private void bind() {
        lstCon = findViewById(R.id.lst_fstContents);
    }

    private void load_data() {
        try {
            myDbHelper.openDataBase();
        } catch (SQLException e) {
            toast(e.toString());
        }
        cur = myDbHelper.get_fst_content();

        ArrayList<String> theList = new ArrayList<>();
        if (cur.getCount() == 0) {
            return;
        } else {
            while (cur.moveToNext())
                theList.add(cur.getString(0));
        }
        fstContent_adapter list_adapter = new fstContent_adapter(MyApplication.mContex, theList);
        lstCon.setAdapter(list_adapter);

        myDbHelper.close();
    }
}