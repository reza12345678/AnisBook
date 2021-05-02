package android.app.anisbookupdate.Utilities;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.orhanobut.hawk.Hawk;

public class MyTextView_AB extends AppCompatTextView {
    public MyTextView_AB(@NonNull Context context) {
        super(context);
       // setTypeface(Utility.getTypeFace_AB());
        setTextSize(Hawk.get("font_size_AB"));
    }

    public MyTextView_AB(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
      //  setTypeface(Utility.getTypeFace_AB());
      //  setTextSize(Hawk.get("font_size_AB"));
    }

    public MyTextView_AB(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
     //   setTypeface(Utility.getTypeFace_AB());
      //  setTextSize(Hawk.get("font_size_AB"));
    }
}
