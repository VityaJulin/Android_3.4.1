package com.example.android_332;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Spinner langSpin;
    private Button okBtn;
    private TextView primaryTxt;
    private Spinner themesSpin;
    private int themeSpinPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.app_name);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_main);
        initViews();
        initLangSpin();
        initThemesSpin();

        langSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] languages = getResources().getStringArray(R.array.lang_spin);
                Toast.makeText(MainActivity.this, R.string.toast_select, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        themesSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] themes = getResources().getStringArray(R.array.themes_spin);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (langSpin.getSelectedItemPosition() == 0) {
                    Locale locale = new Locale("ru");
                    Configuration config = new Configuration();
                    config.setLocale(locale);
                    getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                } else if (langSpin.getSelectedItemPosition() == 1) {
                    Locale locale = new Locale("en", "us");
                    Configuration config = new Configuration();
                    config.setLocale(locale);
                    getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                }

                themeSpinPosition = themesSpin.getSelectedItemPosition();

                switch (themeSpinPosition) {
                    case 0:
                        Utils.changeToTheme(MainActivity.this, Utils.THEME_BLACK);
                        break;
                    case 1:
                        Utils.changeToTheme(MainActivity.this, Utils.THEME_GREEN);
                        break;
                    case 2:
                        Utils.changeToTheme(MainActivity.this, Utils.THEME_BLUE);
                        break;
                }

                recreate();
            }
        });
    }

    private void initViews() {
        langSpin = (Spinner) findViewById(R.id.land_spin);
        primaryTxt = findViewById(R.id.primary_txt);
        okBtn = findViewById(R.id.ok_btn);
        themesSpin = findViewById(R.id.themes_spin);
    }

    private void initLangSpin() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.lang_spin, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        langSpin.setAdapter(adapter);
    }

    private void initThemesSpin() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.themes_spin, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        themesSpin.setAdapter(adapter);
    }
}
