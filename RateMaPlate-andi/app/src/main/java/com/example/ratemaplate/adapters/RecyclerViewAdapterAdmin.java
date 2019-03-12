package com.example.ratemaplate.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ratemaplate.PlateActivity;
import com.example.ratemaplate.R;

import java.util.ArrayList;



public class RecyclerViewAdapterAdmin extends RecyclerView.Adapter<RecyclerViewAdapterAdmin.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    // delcaration
    private ArrayList<String> flaggedContent = new ArrayList<>();
    private Context mContext;



    // constructor
    public RecyclerViewAdapterAdmin(ArrayList<String> flaggedContent) {
        this.flaggedContent = flaggedContent;

    }

    @Override
    public RecyclerViewAdapterAdmin.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem_admin, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // log for debugging
        Log.d(TAG,"onBindViewHolder: called.");


        // user clicks on list in the list
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on:" + flaggedContent.get(position));


                //takes user to the item in the list they clicked on
                Intent intent = new Intent(mContext, PlateActivity.class);
                intent.putExtra("plate_url", flaggedContent.get(position));

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        // return the list, without this, blank screen
        return flaggedContent.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView imageName;
        RelativeLayout parentLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            imageName = itemView.findViewById(R.id.image_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}