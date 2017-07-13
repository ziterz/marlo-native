package com.ziterz.marlo.User.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import com.ziterz.marlo.R;
import com.ziterz.marlo.User.Item.LaundryData;
import com.ziterz.marlo.User.Model.Laundry;
import com.ziterz.marlo.User.UserDetailActivity;

/**
 * Created by ziter on 4/22/2017.
 */

public class LaundryAdapter extends RecyclerView.Adapter<LaundryAdapter.MyViewHolder> {

    private Context mContext;
    private List<Laundry> laundryDataList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, alamat, rating,jarak;
        ImageView thumbnail;
        public MyViewHolder(View view){
            super (view);
            title = (TextView) view.findViewById(R.id.title_card);
            alamat = (TextView) view.findViewById(R.id.alamat);
            rating = (TextView) view.findViewById(R.id.text_rating);
            jarak = (TextView) view.findViewById(R.id.jarak);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            Typeface medium = Typeface.createFromAsset(mContext.getAssets(), "fonts/Roboto-Medium.ttf");
            title.setTypeface(medium);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, UserDetailActivity.class);
                    intent.putExtra("laundry", laundryDataList.get(getAdapterPosition()));
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    public LaundryAdapter(Context mContext, List<Laundry> laundryDataList) {
        this.mContext = mContext;
        this.laundryDataList = laundryDataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.laundry_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Laundry laundryData = laundryDataList.get(position);
        holder.title.setText(laundryData.getNamaLaundry());
        holder.alamat.setText(laundryData.getAlamat());
        holder.rating.setText("4.6");
        holder.jarak.setText("32m");
        Glide.with(mContext).load("http://marloapp.com"+laundryData.getDirectoryFoto()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return laundryDataList.size();
    }

}
