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

public class sndContentsActivity extends AppCompatActivity {

    ListView lst_sndContent;
    Cursor cur = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snd_contents);
        bind();
        load_data();

        lst_sndContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                try {
                    myDbHelper.openDataBase();
                } catch (SQLException e) {
                    toast(e.toString());
                }

                String get_title_tblAnis = (String) parent.getItemAtPosition(position);
                cur = myDbHelper.get_all("title", get_title_tblAnis);

                int pa_id_tblAnis = 0;
                if (cur.getCount() == 0) {
                    return;
                } else {
                    while (cur.moveToNext())
                        pa_id_tblAnis = cur.getInt(0);
                }
                Hawk.put("tblAnis_id", pa_id_tblAnis);
                Intent container_activity = new Intent(MyApplication.mContex, ContainerActivity.class);
                startActivity(container_activity);
                myDbHelper.close();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        myDbHelper.close();
        this.finish();
    }

    private void bind() {
        lst_sndContent = findViewById(R.id.lst_sndContents);
    }

    private void load_data() {
        try {
            myDbHelper.openDataBase();
        } catch (SQLException e) {
            toast(e.toString());
        }

        String unit_param = Hawk.get("unit_param");
        cur = myDbHelper.get_all("unit", unit_param);

        ArrayList<String> theTitle = new ArrayList<>();
        if (cur.getCount() == 0) {
            return;
        } else {
            while (cur.moveToNext())
                theTitle.add(cur.getString(2));
        }
        fstContent_adapter list_adapter = new fstContent_adapter(MyApplication.mContex, theTitle);
        lst_sndContent.setAdapter(list_adapter);
        myDbHelper.close();
    }
}