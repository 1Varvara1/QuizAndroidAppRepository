package com.example.quizapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quizapplication.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {

    private ArrayList<String> booksNames = new ArrayList<String>();
    private ArrayList<String> images = new ArrayList<String>();
    private Context context;


    public BooksAdapter(ArrayList<String> booksNames, ArrayList<String> images, Context context) {
        this.booksNames = booksNames;
        this.images = images;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listbook, parent, false );
        ViewHolder holder = new ViewHolder(view)   ;
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).asBitmap().load(images.get(position)).into(holder.image);

        holder.bookName.setText(booksNames.get(position));
    }

    @Override
    public int getItemCount() {
        return booksNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

       CircleImageView image ;
       TextView bookName;
       RelativeLayout parentLayout ;

       public ViewHolder(View itemView) {
           super(itemView);

           image = itemView.findViewById(R.id.image);
           bookName = itemView.findViewById(R.id.image_name);
           parentLayout = itemView.findViewById(R.id.parent_layout);
       }
   }
}
