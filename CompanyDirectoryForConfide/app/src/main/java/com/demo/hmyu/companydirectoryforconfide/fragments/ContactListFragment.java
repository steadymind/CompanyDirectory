package com.demo.hmyu.companydirectoryforconfide.fragments;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import com.demo.hmyu.companydirectoryforconfide.R;
import com.demo.hmyu.companydirectoryforconfide.adapter.ContactAdapter;
import com.demo.hmyu.companydirectoryforconfide.http.HttpClient;
import com.demo.hmyu.companydirectoryforconfide.model.Employees;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link com.demo.hmyu.companydirectoryforconfide.fragments.ContactListFragment.onContactListFragmentListener}
 * interface.
 */
public class ContactListFragment extends Fragment implements AbsListView.OnItemClickListener {


    private onContactListFragmentListener mListener;
    private String TAG = this.getClass().getSimpleName();

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;
    private Employees mEmployees;

    // TODO: Rename and change types of parameters
    public static ContactListFragment newInstance() {
        ContactListFragment fragment = new ContactListFragment();
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ContactListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: Change Adapter to display your content

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        mListView = (AbsListView) view.findViewById(android.R.id.list);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
           String employeeString = mEmployees.employees.get(position).toString();
           Log.v(TAG,"onItemclick " + employeeString);
           mListener.onEmployeeClick(employeeString);
        }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {


//        View emptyView = mListView.getEmptyView();
//
//        if (emptyView instanceof TextView) {
//            ((TextView) emptyView).setText(emptyText);
//        }
    }

    public void showContacts(Employees data){
        /*
      The Adapter which will be used to populate the ListView/GridView with
      Views.
     */
        ListAdapter mAdapter = new ContactAdapter(getActivity(), data.employees);
        mListView.setAdapter(mAdapter);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface onContactListFragmentListener {
        // TODO: Update argument type and name
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
                showContacts(employees);
            }
        }
    }

}
