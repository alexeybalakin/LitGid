package ru.android.innocurses.litgid.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ru.android.innocurses.litgid.R;
import ru.android.innocurses.litgid.adapters.CommentListAdapter;
import ru.android.innocurses.litgid.managers.ManagerCategories;
import ru.android.innocurses.litgid.managers.ManagerComments;
import ru.android.innocurses.litgid.managers.ManagerUsers;
import ru.android.innocurses.litgid.managers.ManagerWritings;
import ru.android.innocurses.litgid.models.Category;
import ru.android.innocurses.litgid.models.Comment;
import ru.android.innocurses.litgid.models.User;
import ru.android.innocurses.litgid.models.Writing;

public class WritingDetailsActivity extends Activity {
    private TextView tvCategory;
    private TextView tvWritingName;
    private TextView tvWritingDetails;
    private TextView tvUser;
    private EditText etComment;
    private Button bAddComment;
    private RecyclerView rvComments;
    private Writing writing;
    private CommentListAdapter adapter;
    List<Comment> items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_details);

        //Получаем из интента  id для которого будем выводить всю информацию
        int writingId =  getIntent().getIntExtra("writing",1);
        writing = ManagerWritings.get(this).getWriting(writingId);

        tvCategory = (TextView)  findViewById(R.id.tvWritingDetailsCategory);
        tvCategory.setText(writing.getCategory().getName());
        tvWritingName = (TextView)  findViewById(R.id.tvWritingDetailsName);
        tvWritingName.setText(writing.getName());
        tvWritingDetails = (TextView)  findViewById(R.id.tvWritingDetailsText);
        tvWritingDetails.setText(writing.getText());
        tvUser = (TextView)  findViewById(R.id.tvWritingDetailsUser);
        tvUser.setText(writing.getAuthor().getLogin());
        etComment = (EditText) findViewById(R.id.etWritingDetailsComment);
        bAddComment = (Button) findViewById(R.id.bAddComment);
        bAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Получаем из Preferences логин пользователя
                if(etComment.getText().toString().length() == 0){
                    Toast.makeText(view.getContext(),
                            "Введите ваш комментарий", Toast.LENGTH_SHORT).show();
                }
                else {
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                    String login = sharedPreferences.getString("current_ user", "");
                    User user = ManagerUsers.get(view.getContext()).getUser(login);

                    //Сохраняем новый комментарий в БД
                    Comment comment = new Comment(etComment.getText().toString(), writing, user);
                    ManagerComments.get(view.getContext()).addComment(comment);

                    //Обновляем адптер чтобы изменения сразу отобразились на экране
                    items.add(comment);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        rvComments = (RecyclerView) findViewById(R.id.rvComments);
        rvComments.setLayoutManager(new LinearLayoutManager(this));

        items = ManagerComments.get(this).getComments(writingId);
        adapter = new CommentListAdapter(items, this);
        rvComments.setAdapter(adapter);

    }
}
