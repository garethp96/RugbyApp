package com.gui.garethpower.rugbyapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class Frag1 extends Fragment {

    public RecyclerView mBlogList;
    private DatabaseReference mDatabase;

    //newInstance() method return the referenc to fragment
    public static Frag1 newInstance(){
        Frag1 fragment = new Frag1();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog");
    }

    //Constructor
    public Frag1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_frag1, container, false);
        //RecyclerView buttaids = (RecyclerView) rootView.findViewById(R.id.rabbit_list);
        setupRecyclerView(rootView);
        if(mBlogList == null)
            throw new NullPointerException();
        //setupRecyclerView(rootView);
        // return rootview
        return rootView;
    }

    private void setupRecyclerView(View rootView) {


        //sets up the recycler view
        mBlogList = (RecyclerView) rootView.findViewById(R.id.rabbit_list);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onStart() {
        super.onStart();

        //formats the recycler view
        FirebaseRecyclerAdapter<Blog, BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(
                Blog.class,
                R.layout.blog_row,
                BlogViewHolder.class,
                mDatabase

        ) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setImage(getActivity().getApplicationContext(), model.getImage());
            }
        };
        mBlogList.setAdapter(firebaseRecyclerAdapter);
    }
    public static class BlogViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public BlogViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }
        public void setTitle(String title){
            TextView post_title = (TextView) mView.findViewById(R.id.post_title);
            post_title.setText(title);
        }
        public void setDesc(String desc){
            TextView post_desc = (TextView) mView.findViewById(R.id.post_desc);
            post_desc.setText(desc);
        }
        public void setImage(android.content.Context ctx, String image){
            ImageView post_image = (ImageView) mView.findViewById(R.id.post_image);
            Picasso.with(ctx).load(image).into(post_image);
        }
    }



}

