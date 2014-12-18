package com.demo.hmyu.companydirectoryforconfide.fragments;

import com.demo.hmyu.companydirectoryforconfide.R;
import com.demo.hmyu.companydirectoryforconfide.model.Employee;
import com.demo.hmyu.companydirectoryforconfide.ui.CircleTransformation;
import com.demo.hmyu.companydirectoryforconfide.ui.InfoCategoryView;
import com.demo.hmyu.companydirectoryforconfide.ui.InfoItemView;
import com.squareup.picasso.Picasso;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ContactDetailFragment extends Fragment
        implements View.OnClickListener, InfoItemView.OnInfoItemViewListener {

    private static final String ARG_EMPLOYEE = "EMPLOYEE";

    private Employee mEmployee;

    //private onContactDetailListener mListener;


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
        LinearLayout infoContainer = (LinearLayout) view.findViewById(R.id.layout_container);

        ImageView mImgPhoto = (ImageView) view.findViewById(R.id.img_profile);
        TextView txtName = (TextView) view.findViewById(R.id.txt_name);
        TextView txtTitle = (TextView) view.findViewById(R.id.txt_title);
        ImageButton btnFB = (ImageButton) view.findViewById(R.id.btn_fb);
        ImageButton btnTwitter = (ImageButton) view.findViewById(R.id.btn_twitter);
        TextView txtBday = (TextView) view.findViewById(R.id.txt_bday);

        // basic information setting
        if (mEmployee != null) {
            txtName.setText(String.format(getResources().getString(R.string.detail_name),
                    mEmployee.getFirst_name(), mEmployee.getLast_name()));
            Picasso.with(getActivity()).load(mEmployee.getPhoto_url()).fit().centerCrop()
                    .transform(new CircleTransformation()).into(mImgPhoto);

            if (mEmployee.getTitle() != null) {
                txtTitle.setText(mEmployee.getTitle());
            }

            if (mEmployee.getBirthday() != null) {
                txtBday.setText(mEmployee.getBirthday());
                txtBday.setOnClickListener(this);
            } else {
                txtBday.setVisibility(View.GONE);
            }

            if (mEmployee.getSocial() != null && mEmployee.getSocial().getFacebook() != null) {
                btnFB.setOnClickListener(this);
            } else {
                btnFB.setVisibility(View.GONE);
            }

            if (mEmployee.getSocial() != null && mEmployee.getSocial().getTwitter() != null) {
                btnTwitter.setOnClickListener(this);
            } else {
                btnTwitter.setVisibility(View.GONE);
            }

            ArrayList<InfoCategoryView> containers = new ArrayList<>();

            // set emails
            if (mEmployee.getEmails() != null) {
                InfoCategoryView emailContainer = new InfoCategoryView(getActivity(),
                        getResources().getString(R.string.email));
                addItems(emailContainer, InfoItemView.EMAIL);
                containers.add(emailContainer);
            }

            // set phones
            if (mEmployee.getPhones() != null && (mEmployee.getPhones().getPersonal() != null ||
                    mEmployee.getPhones().getWork() != null)) {
                InfoCategoryView phoneContainer = new InfoCategoryView(getActivity(),
                        getResources().getString(R.string.phone));
                addItems(phoneContainer, InfoItemView.PHONE);
                containers.add(phoneContainer);
            }
            // set website
            if (mEmployee.getWebsite() != null) {
                InfoCategoryView personalInfoContainer = new InfoCategoryView(getActivity(),
                        getResources().getString(R.string.website));
                addItems(personalInfoContainer, InfoItemView.WEBSITE);
            }

            for (InfoCategoryView info : containers) {
                infoContainer.addView(info);
            }
        }
        return view;
    }

    private void addItems(InfoCategoryView container, int type) {
        if (type == InfoItemView.PHONE) {
            if (mEmployee.getPhones().getWork() != null) {
                InfoItemView phoneWork = new InfoItemView(getActivity(), this, type,
                        getResources().getString(R.string.work), mEmployee.getPhones().getWork());
                container.addView(phoneWork);
            }

            if (mEmployee.getPhones().getPersonal() != null) {
                InfoItemView phonePersonal = new InfoItemView(getActivity(), this, type,
                        getResources().getString(R.string.personal),
                        mEmployee.getPhones().getPersonal());
                container.addView(phonePersonal);
            }
        } else if (type == InfoItemView.EMAIL) {
            if (mEmployee.getEmails().getWork() != null) {
                InfoItemView emailWork = new InfoItemView(getActivity(), this, type,
                        getResources().getString(R.string.work), mEmployee.getEmails().getWork());
                container.addView(emailWork);
            }

            if (mEmployee.getEmails().getPersonal() != null) {
                InfoItemView emailPersonal = new InfoItemView(getActivity(), this, type,
                        getResources().getString(R.string.personal),
                        mEmployee.getEmails().getPersonal());
                container.addView(emailPersonal);
            }
        } else if (type == InfoItemView.WEBSITE) {
            InfoItemView website = new InfoItemView(getActivity(), this, type,
                    getResources().getString(R.string.website), mEmployee.getWebsite());
            container.addView(website);

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_fb:
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("fb://profile/" + mEmployee.getSocial().getFacebook()));
                    startActivity(intent);
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://www.facebook.com/appetizerandroid")));
                }
                break;

            case R.id.btn_twitter:
                Intent intent;
                String url = "twitter://user?user_id=" + mEmployee.getSocial().getTwitter();
                String browserUrl = "https://twitter.com/" + mEmployee.getSocial().getTwitter();
                try {
                    // get the Twitter app if possible
                    getActivity().getPackageManager().getPackageInfo("com.twitter.android", 0);
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } catch (Exception e) {
                    // no Twitter app, revert to browser
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse(browserUrl));
                }
                this.startActivity(intent);

                break;

            case R.id.txt_bday:
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date convertedDate = new Date();
                try {
                    convertedDate = dateFormat.parse(mEmployee.getBirthday());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Calendar cal = Calendar.getInstance();
                cal.setTime(convertedDate);

                Intent calIntent = new Intent(Intent.ACTION_INSERT);
                calIntent.setType("vnd.android.cursor.item/event");
                calIntent.putExtra(CalendarContract.Events.TITLE,
                        mEmployee.getFirst_name() + "'s Birthday!!");
                calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Confide's Office");
                calIntent.putExtra(CalendarContract.Events.DESCRIPTION,
                        "Get a cake for " + mEmployee.getFirst_name());

                GregorianCalendar calDate = new GregorianCalendar(cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH));
                calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
                calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                        calDate.getTimeInMillis());
                calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                        calDate.getTimeInMillis());

                startActivity(calIntent);

                break;
        }
    }

    @Override
    public void onEmailClick(boolean isWork) {
        String email;
        if (isWork) {
            email = mEmployee.getEmails().getWork();
        } else {
            email = mEmployee.getEmails().getPersonal();
        }
        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{email});
        emailIntent
                .putExtra(android.content.Intent.EXTRA_SUBJECT, "Hi " + mEmployee.getFirst_name());
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "yeah!");
        getActivity().startActivity(Intent.createChooser(emailIntent, "Send mail"));
    }

    @Override
    public void onDialClick(boolean isWork) {
        String number = "tel:";

        if (isWork) {
            number = number + mEmployee.getPhones().getWork();
        } else {
            number = number + mEmployee.getPhones().getPersonal();
        }
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(number));
        getActivity().startActivity(intent);
    }

    @Override
    public void onWebsiteClick() {
        String url = mEmployee.getWebsite();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    public void onTextClick(boolean isWork) {
        String number;
        if (isWork) {
            number = mEmployee.getPhones().getWork();
        } else {
            number = mEmployee.getPhones().getPersonal();
        }

        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address",number);
        smsIntent.putExtra("sms_body", "Hello " + mEmployee.getFirst_name());
        startActivity(smsIntent);
    }

}
