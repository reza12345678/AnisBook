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


/*                SpannableString spannableString = new SpannableString(txt.getText().toString());
                ForegroundColorSpan foregroundSpan = new ForegroundColorSpan(Color.RED);
                spannableString.setSpan(foregroundSpan, 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                txtColor.setText(spannableString);*/

                if (rdbSearchArabic.isChecked()) {
                    cur = myDbHelper.search_fields("contain", txt.getText().toString());
                } else if (rdbSearchPersian.isChecked()) {
                    cur = myDbHelper.search_fields("translate", txt.getText().toString());
                }
                ArrayList<String> theList = new ArrayList<>();

                if (cur.getCount() == 0) {
                    lstSearch.setAdapter(null);
                    return;
                } else {
                    while (cur.moveToNext())
                        if (rdbSearchArabic.isChecked()) {

                            theList.add(cur.getString(1));
                        } else if (rdbSearchPersian.isChecked()) {
                            String curField = coloringTextSearch(cur.getString(2), txt.getText().toString());

                            txtColor.setText(curField);

                            theList.add(curField);
                        }
                }
                search_adapter list_adapter = new search_adapter(MyApplication.mContex, theList);
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


    private String highlightString(String input, String mTextView) {

        //Get the text from text view and create a spannable string
        SpannableString spannableString = new SpannableString(mTextView);

        //Get the previous spans and remove them
        BackgroundColorSpan[] backgroundSpans = spannableString.getSpans(0, spannableString.length(), BackgroundColorSpan.class);
        for (BackgroundColorSpan span : backgroundSpans) {
            spannableString.removeSpan(span);
        }

        //Search for all occurrences of the keyword in the string
        int indexOfKeyword = spannableString.toString().indexOf(input);

        while (indexOfKeyword > 0) {
            //Create a background color span on the keyword
            spannableString.setSpan(new BackgroundColorSpan(Color.YELLOW), indexOfKeyword, indexOfKeyword + input.length(), SpannedString.SPAN_EXCLUSIVE_EXCLUSIVE);

            //Get the next index of the keyword
            indexOfKeyword = spannableString.toString().indexOf(input, indexOfKeyword + input.length());
        }

        //Set the final text on TextView
        return spannableString.toString();
    }




    public String coloringTextSearch(String cursorText, String textToSearch){

        //Spannable string to highlight matching searched words
        SpannableString spannableStringSearch = null;

        if ((textToSearch != null) && (!textToSearch.isEmpty())) {
            spannableStringSearch = new SpannableString(cursorText);

            //compile the pattern of input text
            Pattern pattern = Pattern.compile(textToSearch,
                    Pattern.CASE_INSENSITIVE);

            //giving the compliled pattern to matcher to find matching pattern in cursor text
            Matcher matcher = pattern.matcher(cursorText);
            spannableStringSearch.setSpan(new BackgroundColorSpan(
                            Color.TRANSPARENT), 0, spannableStringSearch.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            while (matcher.find()) {

                //highlight all matching words in cursor with white background(since i have a colorfull background image)
                spannableStringSearch.setSpan(new BackgroundColorSpan(
                                Color.WHITE), matcher.start(), matcher.end(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

            //else set plain cursor text
            return spannableStringSearch.toString();

    }




}