package com.demo.hmyu.companydirectoryforconfide.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.hmyu.companydirectoryforconfide.R;
import com.demo.hmyu.companydirectoryforconfide.model.Employee;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.demo.hmyu.companydirectoryforconfide.fragments.ContactDetailFragment.onContactDetailListener} interface
 * to handle interaction events.
 * Use the {@link ContactDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactDetailFragment extends Fragment {

    private static final String ARG_EMPLOYEE = "EMPLOYEE";
    private Employee mEmployee;
    private onContactDetailListener mListener;

    private LinearLayout mContainer;
    private ImageView mImgPhoto;
    private TextView mTxtTitle;
    private TextView mTxtName;


    public static ContactDetailFragment newInstance(String jsonString) {
        ContactDetailFragment fragment = new ContactDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_EMPLOYEE, jsonString);
        fragment.setArguments(args);
        return fragment;
    }

    public ContactDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String jsonString = null;
        if (getArguments() != null) {
            jsonString = getArguments().getString(ARG_EMPLOYEE);
        }

        if (jsonString != null) {
            mEmployee = Employee.parse(jsonString);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contact_detail, container, false);
        mContainer = (LinearLayout) view.findViewById(R.id.layout_container);

        mImgPhoto = (ImageView) view.findViewById(R.id.img_profile);
        mTxtName = (TextView) view.findViewById(R.id.txt_name);
        mTxtTitle = (TextView) view.findViewById(R.id.txt_title);

        if (mEmployee != null) {
            mTxtTitle.setText(mEmployee.getTitle());
            mTxtName.setText(mEmployee.getFirst_name());
            Picasso.with(getActivity()).load(mEmployee.getPhoto_url()).fit().centerCrop().into(mImgPhoto);
        }

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (onContactDetailListener) activity;
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
    public interface onContactDetailListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
