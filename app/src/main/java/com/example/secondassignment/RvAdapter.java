package com.example.secondassignment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondassignment.model.User;

import java.util.ArrayList;
import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyHolder> {

     List<User> userlist = new ArrayList<>();
     Context context;


    public RvAdapter(List<User> userlist, Context context) {
        this.userlist = userlist;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//layout bind
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_layout,parent,false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        final  User user = userlist.get(position);

        String uri = "@drawable/" + user.getImage();
        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
        Drawable image = ContextCompat.getDrawable(context, imageResource);

        holder.imageview.setImageDrawable(image);
        holder.tvusername.setText(user.getName());


        holder.tvusername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(context, UserDetailActivity.class);
                intent1.putExtra("Name",user.getName());
                intent1.putExtra("Gender",user.getGender());
                intent1.putExtra("DoB",user.getDob());
                intent1.putExtra("Country",user.getCountry());
                intent1.putExtra("Phone",user.getPhone());
                intent1.putExtra("Email",user.getEmail());
                intent1.putExtra("Image",user.getImage());

                context.startActivity(intent1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView imageview;
        TextView tvusername;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageview = itemView.findViewById(R.id.userImage);
            tvusername = itemView.findViewById(R.id.userName);
        }
    }
        }



