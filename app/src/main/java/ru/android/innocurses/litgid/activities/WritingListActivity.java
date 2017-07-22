package ru.android.innocurses.litgid.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import ru.android.innocurses.litgid.R;
import ru.android.innocurses.litgid.adapters.UserListAdapter;
import ru.android.innocurses.litgid.adapters.WritingListAdapter;
import ru.android.innocurses.litgid.managers.ManagerCategories;
import ru.android.innocurses.litgid.managers.ManagerComments;
import ru.android.innocurses.litgid.managers.ManagerUsers;
import ru.android.innocurses.litgid.managers.ManagerWritings;
import ru.android.innocurses.litgid.models.User;
import ru.android.innocurses.litgid.models.Writing;

public class WritingListActivity extends Activity {
    private static final int CM_DELETE_ID = 1;
    private RecyclerView rvWritings;
    private Button bAddWriting;
    private WritingListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_list);

        rvWritings = (RecyclerView) findViewById(R.id.rvWritings);
        rvWritings.setLayoutManager(new LinearLayoutManager(this));

        bAddWriting = (Button) findViewById(R.id.bAddWriting);
        bAddWriting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), WritingAddActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

            adapter = new WritingListAdapter(ManagerWritings.get(this).getWritings(), this);
            rvWritings.setAdapter(adapter);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == CM_DELETE_ID) {

            //Получаем из Preferences логин пользователя
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            String login  = sharedPreferences.getString("current_ user", "");

            Writing writingForDelete = adapter.getItem(adapter.delPosition);

            //Получаем пользователя которым был создан данный Writing
            User user = writingForDelete.getAuthor();
            //Проверяем, что удаляемое лит. произведение принадлежит текущему пользователю
            //либо пользователь admin
            if(login.equals(user.getLogin()) || login.equals("admin")) {

                // Удаляем из БД Writing
                ManagerWritings.get(this).delWriting(writingForDelete);
                //Удаляем из БД все комментарии относящиеся к данному Writing
                ManagerComments.get(this).delComments(writingForDelete);
                //Удаляем запись из адаптера, чтобы изменения сразу отобразились на экране
                adapter.remove(adapter.delPosition);
                adapter.notifyDataSetChanged();
                return true;
            }
            else{
                Toast.makeText(this, "Вы можете удалять только свои собственные произведения", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_preferences, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.preferences:
                Intent intent = new Intent(this, PreferencesActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
