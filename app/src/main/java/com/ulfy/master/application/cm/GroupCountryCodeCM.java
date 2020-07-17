package com.ulfy.master.application.cm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.domain.entity.CountryCode;
import com.ulfy.master.domain.entity.CountryGroup;
import com.ulfy.master.ui.cell.GroupCountryCodeCell;

import java.util.ArrayList;
import java.util.List;

public class GroupCountryCodeCM extends BaseCM {
    public CountryGroup countryGroup;
    public List<CountryCodeCM> countryCodeCMList = new ArrayList<>();

    public GroupCountryCodeCM(CountryGroup countryGroup) {
        this.countryGroup = countryGroup;
        for (CountryCode countryCode : countryGroup.countryCodeList) {
            countryCodeCMList.add(new CountryCodeCM(countryCode));
        }
    }

    @Override public List<CountryCodeCM> getChildViewModelList() {
        return countryCodeCMList;
    }

    @Override public Class<? extends IView> getViewClass() {
        return GroupCountryCodeCell.class;
    }
}