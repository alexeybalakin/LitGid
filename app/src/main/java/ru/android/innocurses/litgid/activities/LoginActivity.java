package ru.android.innocurses.litgid.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import ru.android.innocurses.litgid.R;
import ru.android.innocurses.litgid.managers.ManagerUsers;

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
        setTitle(R.string.login);

        context = this;
        regButton = (Button) findViewById(R.id.regButton);
        okButton = (Button)  findViewById(R.id.okButton);
        loginEditText = (EditText) findViewById(R.id.loginEditText);
        passEditText = (EditText) findViewById(R.id.passEditText);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if(sharedPreferences.getBoolean("save_login", false)){
            loginEditText.setText(sharedPreferences.getString("user_login", ""));
        }
        if(sharedPreferences.getBoolean("save_password", false)){
            passEditText.setText(sharedPreferences.getString("user_password", ""));
        }

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, RegistrationActivity.class));

            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ManagerUsers.get(LoginActivity.this).getUser(loginEditText.getText().toString()) == null){
                    Toast.makeText(LoginActivity.this,
                            "Пользователь с таким логином не существует", Toast.LENGTH_SHORT).show();
                }
                else if (!ManagerUsers.get(LoginActivity.this).getUser(loginEditText.getText()
                        .toString()).getPassword().equals(passEditText.getText().toString())){
                    Toast.makeText(LoginActivity.this,
                            "Не верный пароль", Toast.LENGTH_SHORT).show();
                }
                else {
                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                        if(sharedPreferences.getBoolean("save_login", false)){
                            sharedPreferences.edit().putString("user_login", loginEditText.getText().toString()).commit();
                        }
                        if(sharedPreferences.getBoolean("save_password", false)){
                            sharedPreferences.edit().putString("user_password", passEditText.getText().toString()).commit();
                        }
                    startActivity(new Intent(context, AdminActivity.class));
                }
            }
        });

    }

}
