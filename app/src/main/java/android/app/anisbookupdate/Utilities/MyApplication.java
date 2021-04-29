package android.app.anisbookupdate.Utilities;

import android.app.Application;
import android.app.anisbookupdate.Database.AnisDataBase;
//import android.app.anisbookupdate.sndContentsActivity;
import android.app.anisbookupdate.Database.AnisDataBase;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

import com.orhanobut.hawk.Hawk;

import java.io.IOException;

import static android.app.anisbookupdate.Utilities.Utility.toast;

public class MyApplication extends Application {
    public static Context mContex;
    public static AnisDataBase myDbHelper;
    public static String default_font_NameFA = null, default_font_NameAB = null;
    public static int default_font_sizeFA, default_font_sizeAB;
    public static String default_bgcolor = null, default_font_colorFA = null, default_font_colorAB = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mContex = this;


        myDbHelper = new AnisDataBase(MyApplication.mContex);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
        } catch (SQLException e) {
            toast(e.toString());
        }
        Hawk.init(mContex).build();

        // read Database to know which theme set on app
        Cursor default_setting = myDbHelper.get_default_setting();
        if (default_setting.getCount() == 0) {
            return;
        } else {
            while (default_setting.moveToNext()) {
                default_font_NameFA = default_setting.getString(1);
                default_font_NameAB = default_setting.getString(2);
                default_font_sizeFA = default_setting.getInt(3);
                default_font_sizeAB = default_setting.getInt(4);
                default_bgcolor = default_setting.getString(5);
                default_font_colorFA = default_setting.getString(6);
                default_font_colorAB = default_setting.getString(7);
            }
            Hawk.put("font_default_FA", default_font_NameFA);
            Hawk.put("font_default_AB", default_font_NameAB);
            Hawk.put("font_size_FA", (float) default_font_sizeFA);
            Hawk.put("font_size_AB", (float) default_font_sizeAB);
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}

