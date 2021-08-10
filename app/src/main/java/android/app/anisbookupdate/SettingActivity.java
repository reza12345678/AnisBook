package android.app.anisbookupdate;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.anisbookupdate.Spinners.FontAB_Adapter;
import android.app.anisbookupdate.Spinners.FontFA_Adapter;
import android.app.anisbookupdate.Utilities.MyApplication;
import android.app.anisbookupdate.Utilities.MyTextView_AB;
import android.app.anisbookupdate.Utilities.MyTextView_FA;
import android.app.anisbookupdate.Utilities.Utility;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.orhanobut.hawk.Hawk;

import static android.app.anisbookupdate.Utilities.MyApplication.myDbHelper;
import static android.app.anisbookupdate.Utilities.Utility.toast;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    String lstFont_FA[], lstFont_AB[];
    Spinner spnFont_FA, spnFont_AB;
    TextView txtExplainFontFA, txtExplainFontAB, txtExample_FA, txtExample_AB;

    MyTextView_FA txtFontSizeFA;
    MyTextView_AB txtFontSizeAB;

    String choose_fontFA, choose_fontAB;
    int font_position_fa, font_position_ab;
    int fontSize_FA, fontSize_AB;

    SeekBar sldFontFA, sldFontAB;
    Cursor cur = null;
    String spn_fontNameFA_Default, spn_fontNameAB_Default;
    int fontSizeFA_Default, fontSizeAB_Default;

    LinearLayout rootView;

    String bg_color = null, ft_color_fa = null, ft_color_ab = null;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        rootView = findViewById(R.id.rootView);
        bind();
        loadDefaults();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
        myDbHelper.close();
    }


    private void bind() {
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


        sldFontFA.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtFontSizeFA.setTextSize(progress);
                fontSize_FA = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sldFontAB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtFontSizeAB.setTextSize(progress);
                fontSize_AB = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

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
    }

    private void loadDefaults() {
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

        sldFontFA.setProgress(fontSizeFA_Default);
        sldFontAB.setProgress(fontSizeAB_Default);

        txtFontSizeFA.setTextSize((float) fontSizeFA_Default);
        txtFontSizeAB.setTextSize((float) fontSizeAB_Default);
    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnThemeBack) {
            ColorPickerDialogBuilder
                    .with(SettingActivity.this)
                    .setTitle("گالری انتخاب رنگ پشت زمینه")
                    .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                    .density(12)
                    .setOnColorSelectedListener(new OnColorSelectedListener() {
                        @Override
                        public void onColorSelected(int selectedColor) {
                            Toast.makeText(SettingActivity.this, "شماره ی رنگ مورد نظر" + Integer.toHexString(selectedColor), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setPositiveButton("انتخاب شود", new ColorPickerClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                            rootView.setBackgroundColor(selectedColor);
                            bg_color = null;
                            bg_color = "#" + Integer.toHexString(selectedColor);
                        }
                    })
                    .setNegativeButton("خروج", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            toast("هیچ رنگی انتخاب نگردید");
                        }
                    })
                    .build()
                    .show();
        }

        if (v.getId() == R.id.btnTextColor_FA) {
            ColorPickerDialogBuilder
                    .with(SettingActivity.this)
                    .setTitle("گالری انتخاب رنگ نوشته های فارسی")
                    .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                    .density(12)
                    .setOnColorSelectedListener(new OnColorSelectedListener() {
                        @Override
                        public void onColorSelected(int selectedColor) {
                            Toast.makeText(SettingActivity.this, "شماره ی رنگ مورد نظر" + String.valueOf(selectedColor), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setPositiveButton("انتخاب شود", new ColorPickerClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                            Button btnText_FA = findViewById(R.id.btnTextColor_FA);
                            btnText_FA.setBackgroundColor(selectedColor);
                            txtExample_FA.setTextColor(selectedColor);
                            ft_color_fa = null;
                            ft_color_fa = "#" + Integer.toHexString(selectedColor);
                        }
                    })
                    .setNegativeButton("خروج", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            toast("هیچ رنگی انتخاب نگردید");
                        }
                    })
                    .build()
                    .show();
        }

        if (v.getId() == R.id.btnTextColor_AB) {
            ColorPickerDialogBuilder
                    .with(SettingActivity.this)
                    .setTitle("گالری انتخاب رنگ نوشته های عربی")
                    .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                    .density(12)
                    .setOnColorSelectedListener(new OnColorSelectedListener() {
                        @Override
                        public void onColorSelected(int selectedColor) {
                            Toast.makeText(SettingActivity.this, "شماره ی رنگ مورد نظر" + String.valueOf(selectedColor), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setPositiveButton("انتخاب شود", new ColorPickerClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                            Button btnText_AB = findViewById(R.id.btnTextColor_AB);
                            btnText_AB.setBackgroundColor(selectedColor);
                            txtExample_AB.setTextColor(selectedColor);
                            ft_color_ab = null;
                            ft_color_ab = "#" + Integer.toHexString(selectedColor);
                        }
                    })
                    .setNegativeButton("خروج", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            toast("هیچ رنگی انتخاب نگردید");
                        }
                    })
                    .build()
                    .show();
        }

        if (v.getId() == R.id.btnChooseFont_FA) {
            try {
                myDbHelper.EditDataBase();
                myDbHelper.update_fontFA(choose_fontFA, font_position_fa);
                Hawk.put("font_default_FA", choose_fontFA);
                toast("تغییرات داده شده اعمال گردید");
            } catch (SQLException e) {
                toast(e.toString());
            }
        }

        if (v.getId() == R.id.btnChooseFont_AB) {
            try {
                myDbHelper.EditDataBase();
                myDbHelper.update_fontAB(choose_fontAB, font_position_ab);
                Hawk.put("font_default_AB", choose_fontAB);
                toast("تغییرات داده شده اعمال گردید");
            } catch (SQLException e) {
                toast(e.toString());
            }
        }

        if (v.getId() == R.id.btnChangeFontSize_FA) {
            try {
                myDbHelper.EditDataBase();
                myDbHelper.update_fontSizeFA(fontSize_FA);
                Hawk.put("font_size_FA", (float) fontSize_FA);
                toast("تغییرات داده شده اعمال گردید");
            } catch (SQLException e) {
                toast(e.toString());
            }
        }

        if (v.getId() == R.id.btnChangeFontSize_AB) {
            try {
                myDbHelper.EditDataBase();
                myDbHelper.update_fontSizeAB(fontSize_AB);
                Hawk.put("font_size_AB", (float) fontSize_AB);
                toast("تغییرات داده شده اعمال گردید");
            } catch (SQLException e) {
                toast(e.toString());
            }
        }

        if (v.getId() == R.id.btnChooseTheme) {
            try {
                myDbHelper.EditDataBase();
                myDbHelper.update_theme(String.valueOf(bg_color), String.valueOf(ft_color_fa), String.valueOf(ft_color_ab));
                toast("تغییرات داده شده اعمال گردید");
            } catch (SQLException e) {
                toast(e.toString());
            }
        }
    }
}