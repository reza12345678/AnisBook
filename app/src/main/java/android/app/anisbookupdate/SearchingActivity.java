package android.app.anisbookupdate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.app.anisbookupdate.Adapter.search_adapter;
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


public class SearchingActivity extends AppCompatActivity {

    Cursor cur = null;
    ListView lstSearch;
    SearchView txtSRC;

    public static Map<String, Integer> mapList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);

        bind();


        txtSRC.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                try {
                    myDbHelper.openDataBase();
                } catch (SQLException e) {
                    toast(e.toString());
                }

                cur = myDbHelper.search_fields("title", newText);

                ArrayList<String> theList = new ArrayList<>();
                search_adapter list_adapter = new search_adapter(MyApplication.mContex, theList, newText);

                if (cur.getCount() == 0) {
                    lstSearch.setAdapter(null);
                    return false;
                } else {
                    while (cur.moveToNext()) {
                        theList.add(cur.getString(2));
                        mapList.put(cur.getString(2), cur.getInt(0));
                    }
                }
                lstSearch.setAdapter(list_adapter);

                return false;
            }
        });

    }

    private void bind() {
        mapList = new HashMap<String, Integer>();

        txtSRC = findViewById(R.id.edsearch);

        lstSearch = findViewById(R.id.lstSearch);

        lstSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        myDbHelper.close();
        this.finish();
    }

}