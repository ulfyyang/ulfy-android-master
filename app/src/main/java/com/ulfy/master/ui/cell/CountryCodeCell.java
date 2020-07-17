package com.ulfy.master.ui.cell;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.CountryCodeCM;
import com.ulfy.master.ui.base.BaseCell;

@Layout(id = R.layout.cell_country_code)
public class CountryCodeCell extends BaseCell {
    @ViewById(id = R.id.countryNameTV) private TextView countryNameTV;
    @ViewById(id = R.id.countryCodeTV) private TextView countryCodeTV;
    private CountryCodeCM cm;

    public CountryCodeCell(Context context) {
        super(context);
        init(context, null);
    }

    public CountryCodeCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        cm = (CountryCodeCM) model;
        countryNameTV.setText(cm.countryCode.countryName);
        countryCodeTV.setText(cm.countryCode.countryCode);
    }
}