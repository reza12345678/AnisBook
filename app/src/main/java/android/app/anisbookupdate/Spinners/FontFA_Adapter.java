package android.app.anisbookupdate.Spinners;

import android.app.anisbookupdate.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FontFA_Adapter extends BaseAdapter {
    Context mCon;
    String lstFA[];

    public FontFA_Adapter(Context mCon, String[] lst) {
        this.mCon = mCon;
        this.lstFA = lst;
    }

    @Override
    public int getCount() {
        return lstFA.length;
    }

    @Override
    public Object getItem(int position) {
        return lstFA[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = LayoutInflater.from(mCon)
                .inflate(R.layout.fontfa_spinner_item, parent, false);

        TextView txt = row.findViewById(R.id.txtFontFA_item);
        txt.setText(lstFA[position]);

        return row;
    }
}
