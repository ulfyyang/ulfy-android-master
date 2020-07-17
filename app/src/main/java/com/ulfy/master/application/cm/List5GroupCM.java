package com.ulfy.master.application.cm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.ui.cell.List5GroupCell;

import java.util.ArrayList;
import java.util.List;

public class List5GroupCM extends BaseCM {
    public String name;
    public List<List5ChildCM> childCMList = new ArrayList<>();

    public List5GroupCM(String name) {
        this.name = name;
    }

    @Override public List<List5ChildCM> getChildViewModelList() {
        return childCMList;
    }

    @Override public Class<? extends IView> getViewClass() {
        return List5GroupCell.class;
    }
}