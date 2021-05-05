package android.app.anisbookupdate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import static android.app.anisbookupdate.Utilities.MyApplication.myDbHelper;

public class RelatedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_related);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}