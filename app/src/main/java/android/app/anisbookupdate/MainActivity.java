package android.app.anisbookupdate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.anisbookupdate.Utilities.MyApplication;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import me.toptas.fancyshowcase.FancyShowCaseQueue;
import me.toptas.fancyshowcase.FancyShowCaseView;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DrawerLayout drawer;

    ImageButton btnToggle;
    Button btnContents, btnSetting, btnSearch, btnSigned;

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
                .setTitleText("منوی اصلی برنامه")
                .setDismissText("متوجه شدم")
                .setContentText("برای دیدن منوهای مختلف برنامه بر روی این دکمه کلیک کنید")
                .singleUse("IDOne") // provide a unique ID used to ensure it is only shown once
                .show();
    }


    private void showCaseContents() {
// sequence example
        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(500); // half second between each showcase view

        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this, "IDTwo");

        sequence.setConfig(config);
        sequence.hasFired();

        sequence.addSequenceItem(btnContents, "فهرست مطالب",
                "برای مشاهده فهرست مطالب بر روی این دکمه کلیک کنید", "متوجه شدم");

        sequence.addSequenceItem(btnSetting, "تنظیمات",
                "برای دیدن صفحه ی تنظیمات بر روی این دکمه کلیک کنید", "متوجه شدم");

        sequence.addSequenceItem(btnSearch, "جستجوی مطالب",
                "برای جستجوی مطالب بر روی این دکمه کلیک کنید", "متوجه شدم");

        sequence.addSequenceItem(btnSigned, "نشان گذاری",
                "برای مشاهده کردن صفحات علامت گذاری شده وارد این بخش شوید", "متوجه شدم");

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

        btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);


        btnSigned = findViewById(R.id.btnSigned);
        btnSigned.setOnClickListener(this);

        findViewById(R.id.btnCloseApp).setOnClickListener(this);
        findViewById(R.id.btnSeenLast).setOnClickListener(this);
        findViewById(R.id.btnRelateUs).setOnClickListener(this);
        findViewById(R.id.btnAbout).setOnClickListener(this);

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

            showCaseContents();
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