package com.demo.hmyu.companydirectoryforconfide.activity;

import com.demo.hmyu.companydirectoryforconfide.R;
import com.demo.hmyu.companydirectoryforconfide.fragments.ContactDetailFragment;
import com.demo.hmyu.companydirectoryforconfide.fragments.ContactListFragment;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


/**
 * Created by Hsiang-Min on 12/16/14.
 */
public class MainActivity extends ActionBarActivity
        implements ContactListFragment.onContactListFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {

            ContactListFragment f = ContactListFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, f)
                    .commit();
        }
    }


    @Override
    public void onEmployeeClick(String employSring) {
        ContactDetailFragment f = ContactDetailFragment.newInstance(employSring);
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
        String tag = String.valueOf(f.hashCode());
        ft.addToBackStack(tag);
        ft.add(R.id.container, f).commit();

    }

}
