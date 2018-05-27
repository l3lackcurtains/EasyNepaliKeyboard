package com.crumet.awesomekeyboard;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    public static String PACKAGE_NAME;

    LinearLayout confKeyboard;
    Button enableKeyboard;
    Button switchKeyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        PACKAGE_NAME = getApplicationContext().getPackageName();
        confKeyboard = findViewById(R.id.configure_keyboard_view);
        enableKeyboard = findViewById(R.id.btn_enable_keyboard);
        switchKeyboard = findViewById(R.id.btn_switch_keyboard);

        PACKAGE_NAME = getApplicationContext().getPackageName();

        if (!isInputMethodEnabled(this) ||
                !isThisKeyboardSetAsDefaultIME(this)) {
            confKeyboard.setVisibility(View.VISIBLE);
        } else {
            confKeyboard.setVisibility(View.INVISIBLE);
        }


        enableKeyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS));
                if (isInputMethodEnabled(MainActivity.this)) {
                    enableKeyboard.setVisibility(View.INVISIBLE);
                }
            }
        });

        switchKeyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imeManager = (InputMethodManager) getApplicationContext().getSystemService(INPUT_METHOD_SERVICE);
                assert imeManager != null;
                imeManager.showInputMethodPicker();
                onResume();
                if (isThisKeyboardSetAsDefaultIME(MainActivity.this)){
                    switchKeyboard.setVisibility(View.INVISIBLE);
                    confKeyboard.setVisibility(View.INVISIBLE);
                }
            }
        });

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imeManager = (InputMethodManager) getApplicationContext().getSystemService(INPUT_METHOD_SERVICE);
                assert imeManager != null;
                imeManager.showInputMethodPicker();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isInputMethodEnabled(MainActivity.this)) {
            enableKeyboard.setVisibility(View.INVISIBLE);
        }
        if(isThisKeyboardSetAsDefaultIME(MainActivity.this)){
            switchKeyboard.setVisibility(View.INVISIBLE);
        }
    }

    public static boolean isInputMethodEnabled(Context context) {
        final String defaultIME = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ENABLED_INPUT_METHODS);
        return !TextUtils.isEmpty(defaultIME) && defaultIME.contains(PACKAGE_NAME);

    }

    public boolean isThisKeyboardSetAsDefaultIME(Context context) {
        String id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.DEFAULT_INPUT_METHOD);
        ComponentName defaultInputMethod = ComponentName.unflattenFromString(id);
        ComponentName myInputMethod = new ComponentName(this, SimpleIme.class);
        return myInputMethod.equals(defaultInputMethod);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
