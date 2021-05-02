package android.app.anisbookupdate.Spinners;

import android.app.anisbookupdate.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FontAB_Adapter extends BaseAdapter {
    Context mCon;
    String lstAB[];

    public FontAB_Adapter(Context mCon, String[] lstAB) {
        this.mCon = mCon;
        this.lstAB = lstAB;
    }

    @Override
    public int getCount() {
        return lstAB.length;
    }

    @Override
    public Object getItem(int position) {
        return lstAB[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = LayoutInflater.from(mCon)
                .inflate(R.layout.fontab_spinner_item, parent, false);

        TextView txt = row.findViewById(R.id.txtFontAB_item);
        txt.setText(lstAB[position]);

        return row;
    }
}
