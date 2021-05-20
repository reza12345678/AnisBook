package android.app.anisbookupdate.Adapter;

import android.app.anisbookupdate.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static android.app.anisbookupdate.Utilities.Utility.toast;

public class search_adapter extends BaseAdapter {

    Context mCon;
    ArrayList<String> lstSearch;
    String queryText;

    public search_adapter(Context mCon, ArrayList<String> lstSearch, String queryText) {
        this.mCon = mCon;
        this.lstSearch = lstSearch;
        this.queryText = queryText;
    }

    public search_adapter(Context mCon, ArrayList<String> lstSearch) {
        this.mCon = mCon;
        this.lstSearch = lstSearch;
    }

    @Override
    public int getCount() {
        return lstSearch.size();
    }

    @Override
    public Object getItem(int position) {
        return lstSearch.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = LayoutInflater.from(mCon)
                .inflate(R.layout.search_items, parent, false);

        TextView txt = row.findViewById(R.id.txtSearch);

        String dataText = lstSearch.get(position);

        int startPos = dataText.toLowerCase().indexOf(queryText.toLowerCase());
        int endPos = startPos + queryText.length();
        if (startPos != -1) {
            Spannable spannable = new SpannableString(dataText);
            ColorStateList colorStateList = new ColorStateList(new int[][]{new int[]{}}, new int[]{Color.RED});
            TextAppearanceSpan textAppearanceSpan = new TextAppearanceSpan(null, Typeface.BOLD, -1, colorStateList, null);
            spannable.setSpan(textAppearanceSpan, startPos, endPos, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            txt.setText(spannable);
            return row;
        } else
            txt.setText(lstSearch.get(position));
        return row;
    }
}