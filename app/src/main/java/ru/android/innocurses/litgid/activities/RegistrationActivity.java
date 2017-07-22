package ru.android.innocurses.litgid.activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ru.android.innocurses.litgid.R;
import ru.android.innocurses.litgid.managers.ManagerUsers;
import ru.android.innocurses.litgid.models.User;

public class RegistrationActivity extends Activity {

    private Button okRegButton;
    private Button cancelRegButton;
    private EditText loginRegEditText;
    private EditText pasRegEditText;
    private EditText pasConfirmEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setTitle(R.string.registration);

        okRegButton = (Button) findViewById(R.id.okRegButton);
        cancelRegButton = (Button) findViewById(R.id.cancelRegButton);
        loginRegEditText = (EditText)  findViewById(R.id.loginRegEditText);
        pasRegEditText = (EditText)  findViewById(R.id.pasRegEditText);
        pasConfirmEditText = (EditText)  findViewById(R.id.pasConfirmEditText);



        okRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loginRegEditText.getText().length() < 3){
                    Toast.makeText(RegistrationActivity.this,
                            "Логин должен быть не менее 3 символов", Toast.LENGTH_SHORT).show();
                }
                else if(pasRegEditText.getText().length() < 6){
                    Toast.makeText(RegistrationActivity.this,
                            "Пароль должен быть не менее 6 символов", Toast.LENGTH_SHORT).show();
                }
                else if(pasRegEditText.getText().length() != pasConfirmEditText.getText().length()){
                    Toast.makeText(RegistrationActivity.this,
                            "Пароль и подтверждение не совпадают!", Toast.LENGTH_SHORT).show();
                }
                else if(ManagerUsers.get(RegistrationActivity.this).getUser(loginRegEditText.getText().toString()) != null){
                    Toast.makeText(RegistrationActivity.this,
                            "Пользователь с таким логином уже существует", Toast.LENGTH_SHORT).show();
                }
                else {
                    User user = new User(loginRegEditText.getText().toString(),pasRegEditText.getText().toString());
                    ManagerUsers.get(RegistrationActivity.this).addUser(user);

                    Toast.makeText(RegistrationActivity.this,
                            "Регистрация прошла успешно!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        cancelRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginRegEditText.setText("");
                pasRegEditText.setText("");
                pasConfirmEditText.setText("");
            }
        });
    }
}
