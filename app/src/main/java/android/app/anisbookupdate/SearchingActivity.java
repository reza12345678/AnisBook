package android.app.anisbookupdate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.anisbookupdate.Adapter.search_adapter;
import android.app.anisbookupdate.Utilities.MyApplication;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.app.anisbookupdate.Utilities.MyApplication.myDbHelper;
import static android.app.anisbookupdate.Utilities.Utility.toast;

public class SearchingActivity extends AppCompatActivity {

    AutoCompleteTextView txt;
    Cursor cur = null;
    ListView lstSearch;
    RadioButton rdbSearchPersian, rdbSearchArabic;
    TextView txtColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);

        bind();
        txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    myDbHelper.openDataBase();
                } catch (SQLException e) {
                    toast(e.toString());
                }

                if (rdbSearchArabic.isChecked()) {
                    cur = myDbHelper.search_fields("contain", txt.getText().toString());
                } else if (rdbSearchPersian.isChecked()) {
                    cur = myDbHelper.search_fields("translate", txt.getText().toString());
                }
                ArrayList<String> theList = new ArrayList<>();
                search_adapter list_adapter = new search_adapter(MyApplication.mContex, theList, txt.getText().toString());

                if (cur.getCount() == 0) {
                    lstSearch.setAdapter(null);
                    return;
                } else {
                    while (cur.moveToNext())
                        if (rdbSearchArabic.isChecked()) {

                            theList.add(cur.getString(1));
                        } else if (rdbSearchPersian.isChecked()) {
                            theList.add(cur.getString(2));
                        }
                }
                lstSearch.setAdapter(list_adapter);

                if (count == 0) {
                    lstSearch.setAdapter(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void bind() {

        txtColor = findViewById(R.id.textColors);

        txt = findViewById(R.id.edSearch);
        lstSearch = findViewById(R.id.lstSearch);

        rdbSearchPersian = findViewById(R.id.rdbSearchInPersian);
        rdbSearchArabic = findViewById(R.id.rdbSearchInArabic);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        myDbHelper.close();
        this.finish();
    }

}