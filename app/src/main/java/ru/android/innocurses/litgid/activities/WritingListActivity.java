package ru.android.innocurses.litgid.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import ru.android.innocurses.litgid.R;
import ru.android.innocurses.litgid.adapters.UserListAdapter;
import ru.android.innocurses.litgid.adapters.WritingListAdapter;
import ru.android.innocurses.litgid.managers.ManagerUsers;
import ru.android.innocurses.litgid.managers.ManagerWritings;

public class WritingListActivity extends Activity {
    private RecyclerView rvWritings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_list);

        rvWritings = (RecyclerView) findViewById(R.id.rvWritings);
        rvWritings.setLayoutManager(new LinearLayoutManager(this));

        WritingListAdapter adapter = new WritingListAdapter(ManagerWritings.get(this).getWritings(), this);
        rvWritings.setAdapter(adapter);
    }
}
