package com.ulfy.master.application.cm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.domain.entity.CountryCode;
import com.ulfy.master.ui.cell.CountryCodeCell;

public class CountryCodeCM extends BaseCM {
    public CountryCode countryCode;

    public CountryCodeCM(CountryCode countryCode) {
        this.countryCode = countryCode;
    }

    @Override public Class<? extends IView> getViewClass() {
        return CountryCodeCell.class;
    }
}