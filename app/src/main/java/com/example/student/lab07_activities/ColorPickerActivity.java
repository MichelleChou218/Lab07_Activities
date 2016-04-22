package com.example.student.lab07_activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

public class ColorPickerActivity extends AppCompatActivity {

    private static final String TAG = "ColorPickerActivity";

    public static final String BUNDLE_KEY_COLOR_INT = "com.example.student.android.colorInt";
    public static final String BUNDLE_KEY_COLOR_NAME = "com.example.student.android.colorName";

    private int mColorInt;
    private CharSequence mColorName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);
        Log.d(TAG, "onCreate");
        initColorData();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.d(TAG,"onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState");
    }

    private void saveData() {
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(BUNDLE_KEY_COLOR_INT, mColorInt);
        editor.putString(BUNDLE_KEY_COLOR_NAME, mColorName.toString());
        editor.commit();
        Log.d(TAG, "saveData() mColorInt = " + mColorInt + "mColorName = " + mColorName);
    }

    private void restoreData() {
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        mColorInt = prefs.getInt(BUNDLE_KEY_COLOR_INT, 0);
        mColorName = prefs.getString(BUNDLE_KEY_COLOR_NAME, "holo_red_light");
        Log.d(TAG, "restoreData() mColorInt = " + mColorInt + "mColorName = " + mColorName);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        saveData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
        restoreData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    private void initColorData() {
        RadioButton radio = (RadioButton)findViewById(R.id.radio_holo_red_light);
        mColorInt = radio.getCurrentTextColor();
        mColorName = radio.getText();
        Log.d(TAG, "clickColor() mColorInt = " + mColorInt + " mColorName = " + mColorName);
    }

    public void clickColor(View view) {
        RadioButton radio = (RadioButton)view;
        mColorInt = radio.getCurrentTextColor();
        mColorName = radio.getText();
    }

    public void ok(View view) {
        Intent intent = new Intent();
        intent.putExtra(BUNDLE_KEY_COLOR_INT, mColorInt);
        intent.putExtra(BUNDLE_KEY_COLOR_NAME, mColorName);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void cancel(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}
