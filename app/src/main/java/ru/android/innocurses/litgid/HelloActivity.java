package ru.android.innocurses.litgid;

import android.app.Activity;
import android.os.Bundle;

public class HelloActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        setTitle(R.string.hello_name);
    }
}
