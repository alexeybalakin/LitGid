package ru.android.innocurses.litgid.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ru.android.innocurses.litgid.R;

import ru.android.innocurses.litgid.models.Writing;

/**
 * Created by admin on 12.07.2017.
 */

public class WritingListAdapter extends RecyclerView.Adapter<WritingListAdapter.WritingHolder>{
    private List<Writing> writings;
    private Context context;

    public WritingListAdapter(List<Writing> writings, Context context) {
        this.writings = writings;
        this.context = context;
    }

    @Override
    public WritingListAdapter.WritingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.writing_list_item, parent,false);
        return new WritingListAdapter.WritingHolder(v);
    }

    @Override
    public void onBindViewHolder(WritingListAdapter.WritingHolder holder, int position) {
        final Writing writing = writings.get(position);
        holder.tvName.setText(writing.getName());
        holder.tvCategory.setText(writing.getCategory().getName());
        holder.tvAuthor.setText(writing.getAuthor().getLogin());
    }


    @Override
    public int getItemCount() {
        return writings.size();
    }

    public class WritingHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public TextView tvCategory;
        public TextView tvAuthor;


        public WritingHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvWritingListName);
            tvCategory = itemView.findViewById(R.id.tvWritingListCategory);
            tvAuthor = itemView.findViewById(R.id.tvWritingListAuthor);
        }

    }
}
