package com.demo.hmyu.companydirectoryforconfide.ui;

import com.demo.hmyu.companydirectoryforconfide.R;
import com.demo.hmyu.companydirectoryforconfide.model.Employee;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Hsiang-Min on 12/17/14.
 */
public class InfoCategoryView extends LinearLayout {

    private LinearLayout mContainer;

    private TextView mCategory;


    public InfoCategoryView(Context context, String categoryType) {
        super(context);
        View.inflate(context, R.layout.item_category, this);

        mCategory = (TextView) findViewById(R.id.txt_category);
        mCategory.setText(categoryType);
        mContainer = (LinearLayout) findViewById(R.id.layout_container);
    }

    public void addView(View view) {
        mContainer.addView(view);
    }
}
