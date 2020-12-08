package com.ulfy.master.application.cm;


import com.ulfy.android.bus.BusUtils;
import com.ulfy.android.mvvm.IView;
import com.ulfy.android.system.ActivityUtils;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.ui.cell.PickImgCell;
import com.ulfy.master.ui.view.ImagePickView;

import java.io.Serializable;

public class PickImgCM extends BaseCM implements Serializable {
    public int fm_id;
    public String filePath;
    @Override public Class<? extends IView> getViewClass() {
        return PickImgCell.class;
    }

    public void addImg(){
        BusUtils.post(ActivityUtils.getTopActivity(), new ImagePickView.OnItemClickEvent(1,this));
    }

    public void removeImg(){
        BusUtils.post(ActivityUtils.getTopActivity(), new ImagePickView.OnItemClickEvent(2,this));
    }


}