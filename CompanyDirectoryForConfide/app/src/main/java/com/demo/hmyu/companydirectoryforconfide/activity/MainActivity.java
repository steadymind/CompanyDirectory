package com.demo.hmyu.companydirectoryforconfide.activity;

import com.demo.hmyu.companydirectoryforconfide.R;
import com.demo.hmyu.companydirectoryforconfide.fragments.ContactDetailFragment;
import com.demo.hmyu.companydirectoryforconfide.fragments.ContactListFragment;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity implements ContactListFragment.onContactListFragmentListener {

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contact_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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

//    @Override
//    public void onFragmentInteraction(Uri uri) {
//
//    }
}
