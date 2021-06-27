 package android.app.anisbookupdate.Database;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AnisDataBase extends SQLiteOpenHelper {

    String DB_PATH = null;
    private static String DB_NAME = "AnisDB";
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    public AnisDataBase(Context context) {
        super(context, DB_NAME, null, 2);
        this.myContext = context;
        this.DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
        Log.e("Path 1", DB_PATH);
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {
        } else {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[10];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    public void EditDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            try {
                copyDataBase();
            } catch (IOException e) {
                e.printStackTrace();

            }
    }


    public Cursor get_Text() {
        return myDataBase.rawQuery("SELECT * FROM tblAnis", null);
    }

    public Cursor get_fst_content() {
        return myDataBase.rawQuery("SELECT DISTINCT(unit)  FROM tblAnis ", null);
    }

    public Cursor get_snd_content(String param) {
        return myDataBase.rawQuery("SELECT title FROM tblAnis WHERE unit = '" + param + "'", null);
    }

    public Cursor get_title(String param) {
        return myDataBase.rawQuery("SELECT pa_id FROM tblAnis WHERE title = '" + param + "'", null);
    }

    public Cursor get_all(String field, Object param) {
        return myDataBase.rawQuery("SELECT * FROM tblAnis WHERE " + field + "= '" + param + "'", null);
    }

    public void update_fontFA(String font_name, int font_position) {
        myDataBase.execSQL("Update tblSetting set font_name_fa ='" + font_name + "', font_position_fa =" + font_position + " where pa_id = 1");
    }

    public void update_fontSizeFA(int param) {
        myDataBase.execSQL("Update tblSetting set font_size_fa =" + param + " where pa_id = 1");
    }

    public void update_fontAB(String font_name, int font_position) {
        myDataBase.execSQL("Update tblSetting set font_name_ab ='" + font_name + "', font_position_ab =" + font_position + " where pa_id = 1");
    }

    public void update_fontSizeAB(int param) {
        myDataBase.execSQL("Update tblSetting set font_size_ab =" + param + " where pa_id = 1");
    }

    public void update_theme(String bkTheme, String ft_color_fa, String ft_color_ab) {
        myDataBase.execSQL("Update tblSetting set back_theme ='" + bkTheme + "', font_color_fa ='" + ft_color_fa + "', font_color_ab ='" + ft_color_ab + "' where pa_id = 1");
    }

    public Cursor get_container(int tbl_id) {
        return myDataBase.rawQuery("Select * from tblContainer where tblAnis_id =" + tbl_id, null);
    }

    public Cursor get_default_setting() {
        return myDataBase.rawQuery("Select * from tblSetting where pa_id = 1", null);
    }

    public Cursor search_fields(String fieldContain, String param) {
        return myDataBase.rawQuery("SELECT * FROM tblContainer where " + fieldContain + " like '%" + param + "%'", null);
    }

    public Cursor search_fields_two(String fieldContain, String param) {
        return myDataBase.rawQuery("SELECT * FROM tblContainer where " + fieldContain + " like '%" + param + "%'", null);
    }

    public void update_tblAnis(String field_name, int value, int pa_id) {
        myDataBase.execSQL("Update tblAnis set " + field_name + " =" + value + " where pa_id =" + pa_id);
    }


}