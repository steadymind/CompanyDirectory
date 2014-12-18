package com.demo.hmyu.companydirectoryforconfide.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.hmyu.companydirectoryforconfide.R;
import com.demo.hmyu.companydirectoryforconfide.model.Employee;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Hsiang-Min on 12/16/14.
 */
public class ContactAdapter extends BaseAdapter {

    private List<Employee> mEmployeeList;

    private LayoutInflater mInflater = null;

    private Context mContext;

    public ContactAdapter(Context context, List<Employee> employeeList) {
        mContext = context;
        mEmployeeList = employeeList;
    }


    @Override
    public int getCount() {
        return mEmployeeList.size();
    }

    @Override
    public Object getItem(int position) {
        return mEmployeeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ContactViewHolder viewHolder;

        Employee employee = mEmployeeList.get(position);

        if (view == null) {

            view = LayoutInflater.from(mContext).inflate(R.layout.listitem_person, null);
            viewHolder = new ContactViewHolder(view);
            view.setTag(viewHolder);
        }

        viewHolder = (ContactViewHolder) view.getTag();

        if (employee != null) {
            viewHolder.firstName.setText(employee.getFirst_name());
            viewHolder.lastName.setText(employee.getLast_name());
            viewHolder.title.setText(employee.getTitle());
            viewHolder.email.setText(employee.getEmails().getWork());
            viewHolder.phone.setText(employee.getPhones().getWork());

            Picasso.with(mContext).load(employee.getPhoto_url()).fit().centerCrop()
                    .into(viewHolder.img);
            //.transform(new CircleTransformation())
        }
        return view;
    }

    private class ContactViewHolder {

        public ImageView img;

        public TextView firstName;

        public TextView lastName;

        public TextView title;

        public TextView phone;

        public TextView email;

        public ContactViewHolder(View view) {
            img = (ImageView) view.findViewById(R.id.img_photo);
            firstName = (TextView) view.findViewById(R.id.txt_firstname);
            lastName = (TextView) view.findViewById(R.id.txt_lastname);
            title = (TextView) view.findViewById(R.id.txt_title);
            phone = (TextView) view.findViewById(R.id.txt_phone);
            email = (TextView) view.findViewById(R.id.txt_email);
        }
    }

}
