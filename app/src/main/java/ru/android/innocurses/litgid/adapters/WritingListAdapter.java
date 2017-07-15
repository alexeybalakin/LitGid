package ru.android.innocurses.litgid.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.android.innocurses.litgid.R;

import ru.android.innocurses.litgid.activities.WritingDetailsActivity;
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
        holder.bind(writing);
    }


    @Override
    public int getItemCount() {
        return writings.size();
    }

     class WritingHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvName;
        private TextView tvCategory;
        private TextView tvAuthor;
        private Writing writing;
         private ImageView ivBook;
         private CardView cv;


         WritingHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvWritingListName);
            tvCategory = itemView.findViewById(R.id.tvWritingListCategory);
            tvAuthor = itemView.findViewById(R.id.tvWritingListAuthor);
             ivBook = itemView.findViewById(R.id.ivBook);
             cv = itemView.findViewById(R.id.cv);
             itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent= new Intent(context, WritingDetailsActivity.class);
            intent.putExtra("writing", writing.getId());
            context.startActivity(intent);
        }

        public void bind(Writing writing){
            this.writing = writing;
            tvName.setText(writing.getName());
            tvCategory.setText(writing.getCategory().getName());
            tvAuthor.setText(writing.getAuthor().getLogin());
            ivBook.setImageResource(R.drawable.minibook);

        }
    }
}
