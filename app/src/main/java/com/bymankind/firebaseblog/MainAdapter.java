package com.bymankind.firebaseblog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Server-Panduit on 11/3/2017.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    private List<Blog> blogList ;
    private Context context;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog");

    public MainAdapter(Context context, List<Blog> blogList) {
        this.context = context;
        this.blogList = blogList;
    }

    @Override
    public MainAdapter.MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_row,parent,false));
    }

    @Override
    public void onBindViewHolder(final MainAdapter.MainViewHolder holder, final int position) {
        final Blog blogItem = blogList.get(position);

        holder.post_title.setText(blogItem.getTitle());
        holder.post_desc.setText(blogItem.getDescription());


        Picasso.with(context)
                .load(blogItem.getImage())
                .resize(100, 100)
                .centerCrop()
                .into(holder.post_image);

        holder.btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String new_desc = String.valueOf(holder.post_desc.getText());
                DatabaseReference updateRef = mDatabase.child(blogItem.getTitle());
                Map<String, Object> blogMap = new HashMap<String, Object>();
                blogMap.put("description", new_desc);
                updateRef.updateChildren(blogMap);
            }
        });

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String key = mDatabase.child(blogItem.getTitle()).getKey();
                mDatabase.child(key).removeValue();
                notifyItemRemoved(position);
                Toast.makeText(context, "Item Telah Terhapus !", Toast.LENGTH_SHORT).show();

            }
        });
    }
    @Override
    public int getItemCount() {
        return blogList.size();
    }


    public class MainViewHolder extends RecyclerView.ViewHolder {
        TextView post_title;
        EditText post_desc;
        ImageView post_image;
        Button btn_delete, btn_save;

        public MainViewHolder(View itemView) {
            super(itemView);
            post_title = (TextView) itemView.findViewById(R.id.post_title);
            post_desc = (EditText) itemView.findViewById(R.id.post_desc);
            post_image = (ImageView) itemView.findViewById(R.id.post_image);
            btn_delete = (Button) itemView.findViewById(R.id.btn_delete);
            btn_save = (Button) itemView.findViewById(R.id.btn_save);
        }
    }
}
