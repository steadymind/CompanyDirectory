package com.demo.hmyu.companydirectoryforconfide.ui;

import com.demo.hmyu.companydirectoryforconfide.R;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Hsiang-Min on 12/17/14.
 */
public class InfoItemView extends RelativeLayout  implements View.OnClickListener {

    private String TAG = this.getClass().getSimpleName();

    public static final int PHONE = 0;

    public static final int EMAIL = 1;

    public static final int WEBSITE = 2;


    private TextView mTxtData;

    private TextView mTxtDataType;

    private ImageButton mBtnLeft;

    private ImageButton mBtnRight;

    private int mInfoCategory;

    private String mDataType;

    private String mData;


    public InfoItemView(Context context, int infoCategory, String dataType, String data){
        super(context);
        View.inflate(context, R.layout.item_information, this);

        mInfoCategory = infoCategory;
        mDataType = dataType;
        mData = data;

        mTxtData = (TextView) findViewById(R.id.txt_data);
        mTxtDataType = (TextView) findViewById(R.id.txt_type);
        mBtnLeft = (ImageButton) findViewById(R.id.btn_left);
        mBtnLeft.setOnClickListener(this);
        mBtnRight = (ImageButton) findViewById(R.id.btn_right);
        mBtnRight.setOnClickListener(this);

        init();
    }

    @Override
    public void onClick(View v) {
        Log.v(TAG, "type " + mDataType + " " + mData);
    }

    private void init() {
        mTxtData.setText(mData);
        mTxtDataType.setText(mDataType);

        if (mInfoCategory == PHONE) {
            mBtnLeft.setImageResource(R.drawable.ic_action_chat);
            mBtnRight.setImageResource(R.drawable.ic_action_call);
        }

        else if (mInfoCategory == EMAIL) {
            mBtnLeft.setVisibility(View.GONE);
            mBtnRight.setImageResource(R.drawable.ic_action_email);
        }
    }


}
