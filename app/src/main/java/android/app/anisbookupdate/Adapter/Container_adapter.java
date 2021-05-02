package android.app.anisbookupdate.Adapter;

import android.app.anisbookupdate.Database.Container_Model;
import android.app.anisbookupdate.R;
import android.app.anisbookupdate.Utilities.MyTextView_AB;
import android.app.anisbookupdate.Utilities.MyTextView_FA;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import static android.app.anisbookupdate.Utilities.MyApplication.myDbHelper;
import static android.app.anisbookupdate.Utilities.Utility.toast;

public class Container_adapter extends BaseAdapter {
    Context mCon;
    List<Container_Model> model;

    String ft_color_fa = null, ft_color_ab = null;
    Cursor cur = null;

    public Container_adapter(Context mCon, List<Container_Model> model) {
        this.mCon = mCon;
        this.model = model;
    }

    @Override
    public int getCount() {

        return model.size();
    }

    @Override
    public Object getItem(int position) {

        return model.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = LayoutInflater.from(mCon)
                .inflate(R.layout.container_items, parent, false);

        MyTextView_FA txt_fa = row.findViewById(R.id.txtContainer_fa);
        MyTextView_AB txt_ab = row.findViewById(R.id.txtContainer_ab);

        txt_fa.setText(model.get(position).getTranslate());
        txt_ab.setText(model.get(position).getContain());


        try {
            myDbHelper.openDataBase();
        } catch (SQLException e) {
            toast(e.toString());
        }

        cur = myDbHelper.get_default_setting();
        if (cur.getCount() != 0) {
            while (cur.moveToNext()) {
                ft_color_fa = cur.getString(6);
                ft_color_ab = cur.getString(7);
            }
        }

        txt_fa.setTextColor(Color.parseColor(ft_color_fa));
        txt_ab.setTextColor(Color.parseColor(ft_color_ab));

        return row;
    }
}
