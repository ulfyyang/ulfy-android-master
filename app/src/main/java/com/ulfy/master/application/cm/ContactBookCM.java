package com.ulfy.master.application.cm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.ui.cell.ContactBookCell;

public class ContactBookCM extends BaseCM {
    public String peopleName;

    public ContactBookCM(String peopleName) {
        this.peopleName = peopleName;
    }

    @Override public Class<? extends IView> getViewClass() {
        return ContactBookCell.class;
    }
}