package com.ulfy.master.application.cm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.ui.cell.VideoCell;

public class VideoCM extends BaseCM {
    public String title = "超级小白";
    public String cover = "https://uploadfile.huiyi8.com/2014/0611/20140611043850728.jpg.270.jpg";
    public String playUrl = "http://feifei.feifeizuida.com/20191013/20015_a3bffdd7/index.m3u8";

    @Override public Class<? extends IView> getViewClass() {
        return VideoCell.class;
    }
}