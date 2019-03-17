package com.example.ratemaplate.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.ratemaplate.FoodItem;
import com.example.ratemaplate.PlateActivity;
import com.example.ratemaplate.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements Filterable {

    private static final String TAG = "RecyclerViewAdapter";

    // delcaration
    private ArrayList<FoodItem> mFoodItems;
    private ArrayList<FoodItem> mFoodItemsFull;
    private Context mContext;


    // constructor
    public RecyclerViewAdapter(ArrayList<String> mImageNames, ArrayList<Integer> mImages, Context mContextl) {
        mFoodItems = new ArrayList<>();
        for (int i = 0; i < mImageNames.size(); i++) {
            mFoodItems.add(new FoodItem(mImageNames.get(i), mImages.get(i)));
        }

        mFoodItemsFull = new ArrayList<>(mFoodItems);

        this.mContext = mContextl;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // log for debugging
        Log.d(TAG,"onBindViewHolder: called.");

        //gets the images, converts to bitmap, loads into holder
        Glide.with(mContext)
                .asBitmap()
                .load(mFoodItems.get(position).getImage())
                .into(holder.image);

        holder.imageName.setText(mFoodItems.get(position).getImageName());

        // user clicks on list in the list
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on:" + mFoodItems.get(position).getImageName());

                Toast.makeText(mContext, mFoodItems.get(position).getImageName(), Toast.LENGTH_SHORT).show();

                //takes user to the item in the list they clicked on
                Intent intent = new Intent(mContext, PlateActivity.class);
                intent.putExtra("image_url", mFoodItems.get(position).getImage());
                intent.putExtra("image_name", mFoodItems.get(position).getImageName());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        // return the list, without this, blank screen
        return mFoodItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView image;
        TextView imageName;
        RelativeLayout parentLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imageName = itemView.findViewById(R.id.image_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }



    // search bar filter
    @Override
    public Filter getFilter() {
        return filter;
    }

    public Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<FoodItem> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mFoodItemsFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (FoodItem foodItem : mFoodItemsFull) {
                    if (foodItem.getImageName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(foodItem);
                    }

                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mFoodItems.clear();
            mFoodItems.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}