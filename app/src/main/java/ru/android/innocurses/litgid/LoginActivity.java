package ru.android.innocurses.litgid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends Activity {
    private Button regButton;
    private Button okButton;
    private EditText loginEditText;
    private EditText passEditText;
    private Context context;
    static Map<String, String> logins = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle(R.string.login_name);

        context = this;
        regButton = (Button) findViewById(R.id.regButton);
        okButton = (Button)  findViewById(R.id.okButton);
        loginEditText = (EditText) findViewById(R.id.loginEditText);
        passEditText = (EditText) findViewById(R.id.passEditText);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, RegistrationActivity.class));

            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!logins.containsKey(loginEditText.getText().toString())){
                    Toast.makeText(LoginActivity.this,
                            "Пользователь с таким логином не существует", Toast.LENGTH_SHORT).show();
                }
                else if (!logins.get(loginEditText.getText().toString()).equals(passEditText.getText().toString())){
                    Toast.makeText(LoginActivity.this,
                            "Не верный пароль", Toast.LENGTH_SHORT).show();
                }
                else {
                    startActivity(new Intent(context, HelloActivity.class));
                }
            }
        });

    }

}
