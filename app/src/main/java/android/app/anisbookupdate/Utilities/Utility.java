package android.app.anisbookupdate.Utilities;

import android.Manifest;
import android.graphics.Typeface;
import android.widget.Toast;
import com.orhanobut.hawk.Hawk;

import java.util.List;

public class Utility {

    public static void toast(String str) {
        Toast.makeText(MyApplication.mContex, str, Toast.LENGTH_SHORT).show();
    }

    public static Typeface getTypeFace_FA() {
        return Typeface.createFromAsset(MyApplication.mContex.getAssets(), Hawk.get("font_default_FA"));
    }

    public static Typeface getTypeFace(String param) {
        return Typeface.createFromAsset(MyApplication.mContex.getAssets(), param);
    }

    public static Typeface getTypeFace_AB() {
        return Typeface.createFromAsset(MyApplication.mContex.getAssets(), Hawk.get("font_default_AB"));
    }

}
