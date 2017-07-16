package ru.android.innocurses.litgid.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.UserManager;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import ru.android.innocurses.litgid.R;
import ru.android.innocurses.litgid.managers.ManagerCategories;
import ru.android.innocurses.litgid.managers.ManagerUsers;
import ru.android.innocurses.litgid.managers.ManagerWritings;
import ru.android.innocurses.litgid.models.Category;
import ru.android.innocurses.litgid.models.Writing;

public class WritingAddActivity extends Activity {
    private EditText etWritingName;
    private EditText etWritingText;
    private Spinner spCategory;
    private Button bWritingSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_add);

        etWritingName = (EditText) findViewById(R.id.etWritingName);
        etWritingText = (EditText) findViewById(R.id.etWritingText);
        spCategory = (Spinner)  findViewById(R.id.spCategory);
        bWritingSave = (Button) findViewById(R.id.bWritingSave);

        ArrayAdapter<Category> adapter = new ArrayAdapter<Category>(this,
                android.R.layout.simple_spinner_item, ManagerCategories.get(this).getCategories());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(adapter);
        spCategory.setPrompt("Категория");

        bWritingSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etWritingName.getText().length() == 0){
                    Toast.makeText(view.getContext(),
                            "Укажите название произведения", Toast.LENGTH_SHORT).show();
                }
                else if(etWritingText.getText().length() == 0){
                    Toast.makeText(view.getContext(),
                            "Содержание не может быть пустым", Toast.LENGTH_SHORT).show();
                }
                else {
                    //Получаем из Preferences логин пользователя
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                    String login  = sharedPreferences.getString("current_ user", "");

                    //Создаем новое лит. произведение
                    Writing writing = new Writing(etWritingName.getText().toString(),etWritingText.getText().toString(),
                            (Category) spCategory.getSelectedItem(), ManagerUsers.get(view.getContext()).getUser(login));
                    ManagerWritings.get(view.getContext()).addWriting(writing);
                    Toast.makeText(view.getContext(),
                            "Лит. произведение добавлено", Toast.LENGTH_SHORT).show();
            //        Intent intent = new Intent(view.getContext(), WritingListActivity.class);
                  //  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
             //       startActivity(intent);
finish();

                }
            }
        });
    }
}
