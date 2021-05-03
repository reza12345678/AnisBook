package android.app.anisbookupdate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.anisbookupdate.Adapter.container_adapter;
import android.app.anisbookupdate.Database.Container_Model;
import android.app.anisbookupdate.Utilities.MyApplication;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import static android.app.anisbookupdate.Utilities.MyApplication.myDbHelper;
import static android.app.anisbookupdate.Utilities.Utility.toast;

public class ContainerActivity extends AppCompatActivity {

    Cursor cur = null, cursor = null;
    ListView lstContainer;
    List<Container_Model> list_model;
    Container_Model model;

    RelativeLayout rootView, rtlFooter;

    String bg_color = null;

    Switch swhSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        bind();
        load_Data();
    }

    private void bind() {
        lstContainer = findViewById(R.id.lstContainer);
        rootView = findViewById(R.id.contentView);
        rtlFooter = findViewById(R.id.rtlFooter);

        swhSign = findViewById(R.id.swhSign);

        lstContainer.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                rtlFooter.setVisibility(View.VISIBLE);
                return true;
            }
        });


        if (swhSign.isChecked()) {
            try {
                myDbHelper.EditDataBase();
            } catch (SQLException e) {
                toast(e.toString());
            }

            //   cur =myDbHelper.update_tblAnis("favorite", 1, );
        }

    }

    public void load_Data() {
        try {
            myDbHelper.openDataBase();
        } catch (SQLException e) {
            toast(e.toString());
        }

        int tblAnis_id = Hawk.get("tblAnis_id");
        Hawk.delete("tblAnis_id");
        cur = myDbHelper.get_container(tblAnis_id);

        list_model = new ArrayList<>();
        if (cur.getCount() == 0) {
            return;
        } else {
            while (cur.moveToNext()) {
                model = new Container_Model(cur.getInt(0), cur.getString(1)
                        , cur.getString(2), cur.getInt(3));

                list_model.add(model);
            }
        }
        container_adapter list_adapter = new container_adapter(MyApplication.mContex, list_model);
        lstContainer.setAdapter(list_adapter);


        cursor = myDbHelper.get_default_setting();
        if (cursor.getCount() == 0) {
            return;
        } else {
            while (cursor.moveToNext())
                bg_color = cursor.getString(5);
        }

        rootView.setBackgroundColor(Color.parseColor(bg_color));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        myDbHelper.close();
        this.finish();
    }

}