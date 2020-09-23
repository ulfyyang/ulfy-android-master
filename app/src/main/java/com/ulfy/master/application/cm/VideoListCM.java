package com.ulfy.master.application.cm;

import com.ulfy.android.mvvm.IView;
import com.ulfy.master.application.base.BaseCM;
import com.ulfy.master.infrastructure.AppConfig;
import com.ulfy.master.ui.cell.VideoListCell;

import java.util.Random;

public class VideoListCM extends BaseCM {
    public String cover;
    public String title;
    public int count;
    public String time;
    public String avatar;
    public boolean isAuthor;
    public String name;
    public int fanCount;
    public int commentCount;

    public VideoListCM() {
        cover = AppConfig.getPictureUrlRandom();
        title = new String(new char[2 + new Random().nextInt(10)]).replace("\0", "视频标题");
        count = new Random().nextInt(10000);
        time = String.format("%d:%02d", new Random().nextInt(3), new Random().nextInt(60));
        avatar = AppConfig.getPictureUrlRandom();
        isAuthor = new Random().nextBoolean();
        name = "用户名" + new Random().nextInt(10);
        fanCount = new Random().nextInt(200);
        commentCount = new Random().nextInt(500);
    }

    @Override public Class<? extends IView> getViewClass() {
        return VideoListCell.class;
    }
}