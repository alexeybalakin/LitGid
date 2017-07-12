package ru.android.innocurses.litgid.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ru.android.innocurses.litgid.R;
import ru.android.innocurses.litgid.managers.ManagerUsers;
import ru.android.innocurses.litgid.models.User;

/**
 * Created by admin on 12.07.2017.
 */

public class WritingListAdapter {
    private List<User> users;
    private Context context;

    public UserListAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @Override
    public UserListAdapter.UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent,false);
        return new UserListAdapter.UserHolder(v);
    }

    @Override
    public void onBindViewHolder(UserListAdapter.UserHolder holder, int position) {
        final User user = users.get(position);
        holder.tvLogin.setText(user.getLogin());
        holder.tvPassword.setText(user.getPassword());
        holder.cbBlocked.setChecked(user.isBlocked());
  

    }


    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder {
        public TextView tvLogin;
        public TextView tvPassword;
        public CheckBox cbBlocked;

        public UserHolder(View itemView) {
            super(itemView);
            tvLogin = itemView.findViewById(R.id.userListItemLogin);
            tvPassword = itemView.findViewById(R.id.userListItemPassword);
            cbBlocked = itemView.findViewById(R.id.userListItemBlocked);
        }

    }
}
