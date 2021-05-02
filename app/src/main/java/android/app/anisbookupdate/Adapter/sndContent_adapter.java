package android.app.anisbookupdate.Adapter;

import android.app.anisbookupdate.R;
import android.app.anisbookupdate.Utilities.MyApplication;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class sndContent_adapter extends BaseAdapter {
    Context con;
    ArrayList<String> tbl;

    public sndContent_adapter(Context con, ArrayList<String> tbl) {
        this.con = con;
        this.tbl = tbl;
    }

    @Override
    public int getCount() {

        return tbl.size();
    }

    @Override
    public Object getItem(int position) {

        return tbl.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = LayoutInflater.from(MyApplication.mContex).
                inflate(R.layout.sndcontent_item, parent, false);

        TextView txtCon = row.findViewById(R.id.txtContents);
        txtCon.setText(tbl.get(position));

        return row;
    }

}
