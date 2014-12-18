package com.demo.hmyu.companydirectoryforconfide.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.hmyu.companydirectoryforconfide.R;
import com.demo.hmyu.companydirectoryforconfide.model.Employee;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hsiang-Min on 12/16/14.
 */
public class ContactAdapter extends BaseAdapter implements Filterable {

    private List<Employee> mEmployeeList;

    private List<Employee> filteredData = null;

    private LayoutInflater mInflater = null;

    private Context mContext;

    private ItemFilter mFilter = new ItemFilter();

    public ContactAdapter(Context context, List<Employee> employeeList) {
        mContext = context;
        mEmployeeList = employeeList;
        filteredData = employeeList;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<Employee> employeeList) {
        mEmployeeList = employeeList;
    }


    @Override
    public int getCount() {
        return filteredData.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public Filter getFilter() {
        return mFilter;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ContactViewHolder viewHolder;

        Employee employee = mEmployeeList.get(position);

        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.listitem_person, parent, false);
            viewHolder = new ContactViewHolder(view);
            view.setTag(viewHolder);
        }

        viewHolder = (ContactViewHolder) view.getTag();

        if (employee != null && viewHolder != null) {
            viewHolder.firstName.setText(employee.getFirst_name());
            viewHolder.lastName.setText(employee.getLast_name());
            viewHolder.title.setText(employee.getTitle());
            if (employee.getEmails() != null) {
                viewHolder.email.setText(employee.getEmails().getWork());
            }

            if (employee.getPhones() != null) {
                viewHolder.phone.setText(employee.getPhones().getWork());
            }

            if (employee.getPhoto_url() != null) {
                Picasso.with(mContext).load(employee.getPhoto_url()).fit().centerCrop()
                        .into(viewHolder.img);
            }

        }
        return view;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    private class ItemFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint == null || constraint.length() == 0) {
                // set the Original result to return
                results.count = mEmployeeList.size();
                results.values = mEmployeeList;
            } else {
                String filterString = constraint.toString().toLowerCase();

                final List<Employee> list = mEmployeeList;
                int count = list.size();

                final ArrayList<Employee> newList = new ArrayList<>();

                for (int i = 0; i < count; i++) {
                    Employee e = list.get(i);
                    if (e.getFirst_name() != null && e.getFirst_name().toLowerCase()
                            .contains(filterString)) {
                        newList.add(e);
                    } else if (e.getLast_name() != null && e.getLast_name().toLowerCase()
                            .contains(filterString)) {
                        newList.add(e);
                    } else if (e.getEmails().getPersonal() != null && e.getEmails().getPersonal()
                            .toLowerCase().contains(filterString)) {
                        newList.add(e);
                    } else if (e.getEmails().getWork() != null && e.getEmails().getWork()
                            .toLowerCase().contains(filterString)) {
                        newList.add(e);
                    } else if (e.getPhones().getPersonal() != null && e.getPhones().getPersonal()
                            .toLowerCase().contains(filterString)) {
                        newList.add(e);
                    } else if (e.getPhones().getWork() != null && e.getPhones().getWork()
                            .toLowerCase().contains(filterString)) {
                        newList.add(e);
                    } else if (e.getTitle() != null && e.getTitle()
                            .contains(filterString)) {
                        newList.add(e);
                    } else if (e.getWebsite() != null && e.getWebsite()
                            .contains(filterString)) {
                        newList.add(e);
                    }
                }

                results.values = newList;
                results.count = newList.size();
            }
            return results;
        }


        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            Log.v("HERE", "results count " + results.count);
            Log.v("HERE", "results values " + results.values);

            if (results.values != null) {
                filteredData = (ArrayList<Employee>) results.values;
                notifyDataSetChanged();
            }

        }

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
