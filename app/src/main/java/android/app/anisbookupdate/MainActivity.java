package android.app.anisbookupdate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.anisbookupdate.Utilities.MyApplication;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DrawerLayout drawer;

    ImageButton btnToggle;
    Button btnContents, btnSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bind();
        showcaseToggle();
    }

    private void showcaseToggle() {
        new MaterialShowcaseView.Builder(this)
                .setTarget(btnToggle)
                .setDismissText("متوجه شدم")
                .setContentText("برای دیدن فهرست مطالب و تنظیمات برنامه بر روی این دکمه کلیک کنید")
                .setDelay(1000) // optional but starting animations immediately in onCreate can make them choppy
                .singleUse("IDOne") // provide a unique ID used to ensure it is only shown once
                .setGravity(Gravity.CENTER)
                .show();
    }

    private void showcasepanel() {

        // sequence example
        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(500); // half second between each showcase view
        config.setDismissTextColor(Color.RED);


        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this, "IDTwO");

        sequence.setConfig(config);

        sequence.addSequenceItem(btnContents,
                "برای نمایش فهرست مطالب بر روی این دکمه کلیک کنید", "متوجه شدم");

        sequence.addSequenceItem(btnSetting,
                "برای تنظیمات برنامه بر روی این دکمه کلیک کنید", "متوجه شدم");

        sequence.start();

    }

    private void bind() {
        drawer = findViewById(R.id.drawerLay);

        btnToggle = findViewById(R.id.btnToggle);
        btnToggle.setOnClickListener(this);

        btnContents = findViewById(R.id.btnContents);
        btnContents.setOnClickListener(this);

        btnSetting = findViewById(R.id.btnSetting);
        btnSetting.setOnClickListener(this);

        findViewById(R.id.btnCloseApp).setOnClickListener(this);
        findViewById(R.id.btnSeenLast).setOnClickListener(this);
        findViewById(R.id.btnRelateUs).setOnClickListener(this);
        findViewById(R.id.btnAbout).setOnClickListener(this);
        findViewById(R.id.btnSearch).setOnClickListener(this);
        findViewById(R.id.btnSigned).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnCloseApp) {
            this.finish();
            System.exit(0);
        }

        if (v.getId() == R.id.btnToggle) {
            if (!drawer.isDrawerOpen(Gravity.RIGHT))
                drawer.openDrawer(Gravity.RIGHT);
            showcasepanel();
        }

        if (v.getId() == R.id.btnSetting) {
            Intent actSetting = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(actSetting);
        }

        if (v.getId() == R.id.btnContents) {
            Intent actContents = new Intent(MyApplication.mContex, fstContentsActivity.class);
            startActivity(actContents);
        }

        if (v.getId() == R.id.btnSearch) {
            Intent actSearch = new Intent(MainActivity.this, SearchingActivity.class);
            startActivity(actSearch);
        }

        if (v.getId() == R.id.btnRelateUs) {
            Intent actRelated = new Intent(MyApplication.mContex, RelatedActivity.class);
            startActivity(actRelated);
        }

        if (v.getId() == R.id.btnAbout) {
            Intent actAbout = new Intent(MyApplication.mContex, AboutActivity.class);
            startActivity(actAbout);
        }

        if (v.getId() == R.id.btnSigned) {
            Intent actSign = new Intent(MyApplication.mContex, SignedActivity.class);
            startActivity(actSign);
        }


    }
}