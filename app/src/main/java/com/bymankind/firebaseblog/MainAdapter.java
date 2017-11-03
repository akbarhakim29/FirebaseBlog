package com.bymankind.firebaseblog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Server-Panduit on 11/3/2017.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder>{
    List<Blog> blogList ;
    Context context;

    public MainAdapter(Context context, List<Blog> blogList) {
        this.context = context;
        this.blogList = blogList;
    }

    @Override
    public MainAdapter.MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_row,parent,false));
    }

    @Override
    public void onBindViewHolder(final MainAdapter.MainViewHolder holder, int position) {
        Blog blogItem = blogList.get(position);

        holder.post_title.setText(blogItem.getTitle());
        holder.post_desc.setText(blogItem.getDescription());

        Picasso.with(context)
                .load(blogItem.getImage())
                .resize(40, 40)
                .centerCrop()
                .into(holder.post_image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return blogList.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        TextView post_title, post_desc;
        ImageView post_image;

        public MainViewHolder(View itemView) {
            super(itemView);
            post_title = (TextView) itemView.findViewById(R.id.post_title);
            post_desc = (TextView) itemView.findViewById(R.id.post_desc);
            post_image = (ImageView) itemView.findViewById(R.id.post_image);
        }
    }
}
