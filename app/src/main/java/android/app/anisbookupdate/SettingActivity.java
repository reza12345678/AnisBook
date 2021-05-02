package android.app.anisbookupdate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.anisbookupdate.Utilities.MyTextView_AB;
import android.app.anisbookupdate.Utilities.MyTextView_FA;
import android.app.anisbookupdate.Utilities.Utility;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.slider.Slider;

import static android.app.anisbookupdate.Utilities.MyApplication.myDbHelper;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    String lstFont_FA[], lstFont_AB[];
    Spinner spnFont_FA, spnFont_AB;
    TextView txtExplainFontFA, txtExplainFontAB, txtExample_FA, txtExample_AB;

    MyTextView_FA txtFontSizeFA;
    MyTextView_AB txtFontSizeAB;

    String choose_fontFA, choose_fontAB;
    int font_position_fa, font_position_ab;
    int fontSize_FA, fontSize_AB;

    Slider sldFontFA, sldFontAB;
    Cursor cur = null;
    String spn_fontNameFA_Default, spn_fontNameAB_Default;
    int fontSizeFA_Default, fontSizeAB_Default;

    LinearLayout rootView;

    String bg_color = null, ft_color_fa = null, ft_color_ab = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        rootView = findViewById(R.id.rootView);
/*        bind();
        loadDefaults();*/
    }

/*    private void bind() {
        txtExplainFontFA = findViewById(R.id.txtExplain_FA);
        txtExplainFontAB = findViewById(R.id.txtExplain_AB);


        findViewById(R.id.btnThemeBack).setOnClickListener(this);
        findViewById(R.id.btnTextColor_FA).setOnClickListener(this);
        findViewById(R.id.btnTextColor_AB).setOnClickListener(this);

        sldFontFA = findViewById(R.id.sldFontSize_FA);
        sldFontAB = findViewById(R.id.sldFontSize_AB);
        txtFontSizeFA = findViewById(R.id.txtFontSize_FA);
        txtFontSizeAB = findViewById(R.id.txtFontSize_AB);

        txtExample_FA = findViewById(R.id.txtExample_FA);
        txtExample_AB = findViewById(R.id.txtExample_AB);

        sldFontFA.setOnPositionChangeListener(new Slider.OnPositionChangeListener() {
            @Override
            public void onPositionChanged(Slider view, boolean fromUser, float oldPos, float newPos, int oldValue, int newValue) {
                txtFontSizeFA.setTextSize((float) newValue);
                fontSize_FA = newValue;
            }
        });

        sldFontAB.setOnPositionChangeListener(new Slider.OnPositionChangeListener() {
            @Override
            public void onPositionChanged(Slider view, boolean fromUser, float oldPos, float newPos, int oldValue, int newValue) {
                txtFontSizeAB.setTextSize((float) newValue);
                fontSize_AB = newValue;
            }
        });

        findViewById(R.id.btnChooseTheme).setOnClickListener(this);
        findViewById(R.id.btnChooseFont_FA).setOnClickListener(this);
        findViewById(R.id.btnChooseFont_AB).setOnClickListener(this);

        findViewById(R.id.btnChangeFontSize_FA).setOnClickListener(this);
        findViewById(R.id.btnChangeFontSize_AB).setOnClickListener(this);

        lstFont_FA = new String[]{"آفاق", "کتاب", "لوتوس", "میترا", "نبی", "نازنین", "یاقوت", "یکان"};
        spnFont_FA = findViewById(R.id.spnFont_FA);

        lstFont_AB = new String[]{"قرآن زیبا", "کتاب", "نازنین", "نیریزی", "عثمان طه", "ثلث"};
        spnFont_AB = findViewById(R.id.spnFont_AB);


        FontFA_Adapter adapter_fa = new FontFA_Adapter(MyApplication.mContex, lstFont_FA);
        spnFont_FA.setAdapter(adapter_fa);
        spnFont_FA.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    choose_fontFA = "farsiafaaq.ttf";
                    font_position_fa = 0;
                    txtExplainFontFA.setTypeface(Utility.getTypeFace(choose_fontFA));
                } else if (position == 1) {
                    choose_fontFA = "farsiketab.ttf";
                    font_position_fa = 1;
                    txtExplainFontFA.setTypeface(Utility.getTypeFace(choose_fontFA));

                } else if (position == 2) {
                    choose_fontFA = "farsilotus.ttf";
                    font_position_fa = 2;
                    txtExplainFontFA.setTypeface(Utility.getTypeFace(choose_fontFA));
                } else if (position == 3) {
                    choose_fontFA = "farsimitra.ttf";
                    font_position_fa = 3;
                    txtExplainFontFA.setTypeface(Utility.getTypeFace(choose_fontFA));

                } else if (position == 4) {
                    choose_fontFA = "farsinabi.ttf";
                    font_position_fa = 4;
                    txtExplainFontFA.setTypeface(Utility.getTypeFace(choose_fontFA));

                } else if (position == 5) {
                    choose_fontFA = "farsinazanin.ttf";
                    font_position_fa = 5;
                    txtExplainFontFA.setTypeface(Utility.getTypeFace(choose_fontFA));

                } else if (position == 6) {
                    choose_fontFA = "farsiyagut.ttf";
                    font_position_fa = 6;
                    txtExplainFontFA.setTypeface(Utility.getTypeFace(choose_fontFA));

                } else if (position == 7) {
                    choose_fontFA = "farsiyekan.ttf";
                    font_position_fa = 7;
                    txtExplainFontFA.setTypeface(Utility.getTypeFace(choose_fontFA));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        FontAB_Adapter adapter_ab = new FontAB_Adapter(MyApplication.mContex, lstFont_AB);
        spnFont_AB.setAdapter(adapter_ab);
        spnFont_AB.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    choose_fontAB = "arabibuityquraan.ttf";
                    font_position_ab = 0;
                    txtExplainFontAB.setTypeface(Utility.getTypeFace(choose_fontAB));
                } else if (position == 1) {
                    choose_fontAB = "arabiketab.ttf";
                    font_position_ab = 1;
                    txtExplainFontAB.setTypeface(Utility.getTypeFace(choose_fontAB));
                } else if (position == 2) {
                    choose_fontAB = "arabinazanin.ttf";
                    font_position_ab = 2;
                    txtExplainFontAB.setTypeface(Utility.getTypeFace(choose_fontAB));
                } else if (position == 3) {
                    choose_fontAB = "arabineirizi.ttf";
                    font_position_ab = 3;
                    txtExplainFontAB.setTypeface(Utility.getTypeFace(choose_fontAB));
                } else if (position == 4) {
                    choose_fontAB = "arabiosmantaha.ttf";
                    font_position_ab = 4;
                    txtExplainFontAB.setTypeface(Utility.getTypeFace(choose_fontAB));
                } else if (position == 5) {
                    choose_fontAB = "arabisuls.ttf";
                    font_position_ab = 5;
                    txtExplainFontAB.setTypeface(Utility.getTypeFace(choose_fontAB));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }*/

/*    private void loadDefaults() {
        // set the default setting here
        try {
            myDbHelper.openDataBase();
        } catch (SQLException e) {
            toast(e.toString());
        }

        cur = myDbHelper.get_default_setting();
        if (cur.getCount() == 0) {
            return;
        } else {
            while (cur.moveToNext()) {
                spn_fontNameFA_Default = cur.getString(1);
                spn_fontNameAB_Default = cur.getString(2);
                fontSizeFA_Default = cur.getInt(3);
                fontSizeAB_Default = cur.getInt(4);
                bg_color = cur.getString(5);
                ft_color_fa = cur.getString(6);
                ft_color_ab = cur.getString(7);
                font_position_fa = cur.getInt(8);
                font_position_ab = cur.getInt(9);
            }
        }

        rootView.setBackgroundColor(Color.parseColor(bg_color));
        txtExample_FA.setTextColor(Color.parseColor(ft_color_fa));
        txtExample_AB.setTextColor(Color.parseColor(ft_color_ab));

        spnFont_FA.setSelection(font_position_fa);
        spnFont_AB.setSelection(font_position_ab);

        sldFontFA.setValue((float) fontSizeFA_Default, false);
        sldFontAB.setValue((float) fontSizeAB_Default, false);

        txtFontSizeFA.setTextSize((float) fontSizeFA_Default);
        txtFontSizeAB.setTextSize((float) fontSizeAB_Default);
    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
        myDbHelper.close();
    }

    @Override
    public void onClick(View v) {

    }
}