package ru.android.innocurses.litgid.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import ru.android.innocurses.litgid.R;
import ru.android.innocurses.litgid.adapters.UserListAdapter;
import ru.android.innocurses.litgid.managers.ManagerUsers;

public class UserListActivity extends Activity {
    private RecyclerView rvUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        rvUsers = (RecyclerView) findViewById(R.id.rvUsers);
        rvUsers.setLayoutManager(new LinearLayoutManager(this));

        UserListAdapter adapter = new UserListAdapter(ManagerUsers.get(this).getUsers(), this);
        rvUsers.setAdapter(adapter);
    }
}
