package com.demo.hmyu.companydirectoryforconfide.fragments;

import com.demo.hmyu.companydirectoryforconfide.R;
import com.demo.hmyu.companydirectoryforconfide.adapter.ContactAdapter;
import com.demo.hmyu.companydirectoryforconfide.http.HttpClient;
import com.demo.hmyu.companydirectoryforconfide.model.Employee;
import com.demo.hmyu.companydirectoryforconfide.model.Employees;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.SearchView;

import java.util.List;


/**
 * Created by Hsiang-Min on 12/16/14.
 */
public class ContactListFragment extends Fragment
        implements AbsListView.OnItemClickListener {


    private onContactListFragmentListener mListener;

    private String TAG = this.getClass().getSimpleName();

    private AbsListView mListView;

    private Employees mEmployees;

    private ContactAdapter mAdapter;

    public static ContactListFragment newInstance() {
        ContactListFragment fragment = new ContactListFragment();
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the fragment (e.g. upon
     * screen orientation changes).
     */
    public ContactListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        mListView = (AbsListView) view.findViewById(android.R.id.list);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);
        mListView.setFastScrollEnabled(true);
        mListView.setSmoothScrollbarEnabled(true);

        GetContactTask task = new GetContactTask();
        task.execute();

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (onContactListFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);

//        MenuItem item = menu.add("Search");
//        item.setIcon(android.R.drawable.ic_menu_search);
//        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
//        SearchView sv = new SearchView(getActivity());

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView sv = (SearchView) menu.findItem(R.id.search).getActionView();

        sv.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

        SearchView.OnQueryTextListener textChangeListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                // this is your adapter that will be filtered
                mAdapter.getFilter().filter(newText);
                Log.v(TAG, "on text chnge text: " + newText);
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                // this is your adapter that will be filtered
                mAdapter.getFilter().filter(query);
                Log.v(TAG, "on query submit: " + query);
                return true;
            }
        };
        sv.setOnQueryTextListener(textChangeListener);
        super.onCreateOptionsMenu(menu, inflater);
//        item.setActionView(sv);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            String employeeString = mEmployees.employees.get(position).toString();
            mListener.onEmployeeClick(employeeString);
        }
    }


    public void showContacts(List<Employee> data) {
        if (mAdapter == null) {
            mAdapter = new ContactAdapter(getActivity(), data);
            mListView.setAdapter(mAdapter);
        } else {
            mAdapter.setData(data);
            mAdapter.notifyDataSetChanged();
        }
    }


    public interface onContactListFragmentListener {

        public void onEmployeeClick(String id);
    }

    private class GetContactTask extends AsyncTask<String, String, Employees> {

        @Override
        protected Employees doInBackground(String... params) {
            HttpClient client = new HttpClient();
            return client.getEmployees();
        }

        @Override
        protected void onPostExecute(Employees employees) {
            super.onPostExecute(employees);

            if (employees != null) {
                mEmployees = employees;
                showContacts(employees.employees);
            }
        }
    }

}
