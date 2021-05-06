package android.app.anisbookupdate.Adapter;

import android.app.anisbookupdate.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class search_adapter extends BaseAdapter {

    Context mCon;

    ArrayList<String> lstSearch;

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
        txt.setText(lstSearch.get(position));

        return row;
    }
}
