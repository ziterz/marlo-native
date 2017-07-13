package com.ziterz.marlo.User.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import com.ziterz.marlo.R;
import com.ziterz.marlo.User.Item.Inprogress;

/**
 * Created by ziterz on 6/14/2017.
 */

public class InprogressAdapter extends BaseAdapter {

    Context context;
    ArrayList<Inprogress> inprogressArrayList;

    public InprogressAdapter(Context context, ArrayList<Inprogress> inprogressArrayList) {
        this.context = context;
        this.inprogressArrayList = inprogressArrayList;
    }

    @Override
    public int getCount() {
        return inprogressArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return inprogressArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder = new ViewHolder();

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_inprogress, null);
            holder.detail_order = (TextView) view.findViewById(R.id.detail_order);
            holder.nama_laundry = (TextView) view.findViewById(R.id.nama_laundry);
            holder.harga_order = (TextView) view.findViewById(R.id.harga_order);
            Typeface medium = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Medium.ttf");
            Typeface bold = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Bold.ttf");
            holder.title1 = (TextView) view.findViewById(R.id.title1);
            holder.title2 = (TextView) view.findViewById(R.id.title2);
            holder.title3 = (TextView) view.findViewById(R.id.title3);
            holder.title4 = (TextView) view.findViewById(R.id.title4);
            holder.title5 = (TextView) view.findViewById(R.id.title5);
            holder.harga_order = (TextView) view.findViewById(R.id.harga_order);
            holder.title1.setTypeface(medium);
            holder.title2.setTypeface(medium);
            holder.title3.setTypeface(medium);
            holder.title4.setTypeface(bold);
            holder.title5.setTypeface(medium);
            holder.harga_order.setTypeface(bold);
            Inprogress inprogress = inprogressArrayList.get(position);
            holder.detail_order.setText(inprogress.getDetailOrder().toString());
            holder.nama_laundry.setText(inprogress.getNamaLaundry().toString());
            holder.harga_order.setText("Rp " + inprogress.getHargaOrder().toString());

        }
        return  view;
    }

    static class ViewHolder {
        TextView nama_laundry, status_order, detail_order,title1,title2,title3,title4,title5,harga_order;
    }
}
