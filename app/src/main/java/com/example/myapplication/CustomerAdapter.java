package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomerAdapter extends ArrayAdapter<Customer> {
    private ArrayList<Customer> dataSet;
    private Context context;

    private static class ViewHolder {
        TextView tvCustomerName;
        TextView tvCustomerNumber;
    }

    public CustomerAdapter(ArrayList<Customer> data, Context context) {
        super(context, R.layout.customer_list_item, data);
        dataSet = data;
        this.context = context;
    }

    @Nullable
    @Override
    public Customer getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Customer customer = getItem(position);

        ViewHolder holder;

        final View resultView;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.customer_list_item, parent, false);

            holder.tvCustomerName = convertView.findViewById(R.id.tvCustomerName);
            holder.tvCustomerNumber = convertView.findViewById(R.id.tvCustomerNumber);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvCustomerName.setText(customer.getName());
        holder.tvCustomerNumber.setText(customer.getNumber());

        return convertView;
    }

    public void updateData(ArrayList<Customer> newData) {
        dataSet.clear();
        dataSet.addAll(newData);
        notifyDataSetChanged();
    }
}
