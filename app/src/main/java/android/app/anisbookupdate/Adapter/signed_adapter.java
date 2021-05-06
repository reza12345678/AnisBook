package android.app.anisbookupdate.Adapter;

import android.app.anisbookupdate.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class signed_adapter extends BaseAdapter {
    Context con;
    ArrayList<String> tbl;

    public signed_adapter(Context con, ArrayList<String> tbl) {
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
        View row = LayoutInflater.from(con)
                .inflate(R.layout.signed_items, parent, false);

        TextView txt = row.findViewById(R.id.txtSigned);
        txt.setText(tbl.get(position));

        return row;
    }
}
