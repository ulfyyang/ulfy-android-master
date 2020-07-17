package com.ulfy.master.application.cm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.domain.entity.PeopleGroup;
import com.ulfy.master.ui.cell.GroupContactBookCell;

import java.util.ArrayList;
import java.util.List;

public class GroupContactBookCM extends BaseCM {
    public PeopleGroup peopleGroup;
    public List<ContactBookCM> contactBookCMList = new ArrayList<>();

    public GroupContactBookCM(PeopleGroup peopleGroup) {
        this.peopleGroup = peopleGroup;
        for (String peopleName : peopleGroup.peopleList) {
            this.contactBookCMList.add(new ContactBookCM(peopleName));
        }
    }

    @Override public List getChildViewModelList() {
        return contactBookCMList;
    }

    @Override public Class<? extends IView> getViewClass() {
        return GroupContactBookCell.class;
    }
}