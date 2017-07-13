package ru.android.innocurses.litgid.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.android.innocurses.litgid.R;
import ru.android.innocurses.litgid.models.Comment;


/**
 * Created by admin on 13.07.2017.
 */

public class CommentListAdapter  extends RecyclerView.Adapter<CommentListAdapter.CommentHolder>{
    private List<Comment> comments;
    private Context context;

    public CommentListAdapter(List<Comment> comments, Context context) {
        this.comments = comments;
        this.context = context;
    }

    @Override
    public CommentListAdapter.CommentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_list_item, parent,false);
        return new CommentListAdapter.CommentHolder(v);
    }

    @Override
    public void onBindViewHolder(CommentListAdapter.CommentHolder holder, int position) {
        final Comment comment = comments.get(position);
        holder.tvComment.setText(comment.getComment());
        holder.tvAuthor.setText(comment.getUser().getLogin());
    }


    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class CommentHolder extends RecyclerView.ViewHolder {
        public TextView tvComment;
        public TextView tvAuthor;


        public CommentHolder(View itemView) {
            super(itemView);
            tvComment = itemView.findViewById(R.id.tvCommentListComment);
            tvAuthor = itemView.findViewById(R.id.tvCommentListUser);
        }

    }
}

