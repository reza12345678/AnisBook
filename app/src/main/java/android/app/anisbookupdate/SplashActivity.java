package android.app.anisbookupdate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    ViewGroup root;
    ImageView img;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        root = findViewById(R.id.ACTsplash);
        img = findViewById(R.id.imgSplashlogo);
        txt = findViewById(R.id.txtSlashSite);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Transition tr = new Explode();
                tr.setDuration(1000);
                TransitionManager.beginDelayedTransition(root, tr);
                img.setVisibility(View.VISIBLE);
                txt.setVisibility(View.VISIBLE);
            }
        }, 100);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent ACTMain = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(ACTMain);
                finish();
            }
        }, 4000);
    }
}