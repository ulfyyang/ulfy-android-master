package com.ulfy.master.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.viewpager.widget.ViewPager;

import com.stx.xhb.androidx.XBanner;
import com.stx.xhb.androidx.entity.LocalImageInfo;
import com.ulfy.android.system.ActivityUtils;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.android.utils.StatusBarUtils;
import com.ulfy.master.R;
import com.ulfy.master.ui.base.BaseActivity;

import java.util.Arrays;

@Layout(id = R.layout.activity_guide2)
public class Guide2Activity extends BaseActivity {
    @ViewById(id = R.id.guideXB) private XBanner guideXB;
    @ViewById(id = R.id.enterBT) private Button enterBT;

    public static void startActivity() {
        ActivityUtils.startActivity(Guide2Activity.class);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.visiable(this, false);
        initViewContent();
    }

    /**
     * 初始化 XBanner 加载方式、设置显示数据、设置最后一页按钮可见性
     */
    public void initViewContent() {
        guideXB.loadImage(new XBanner.XBannerAdapter() {
            @Override public void loadBanner(XBanner banner, Object model, View view, int position) {
                ((ImageView) view).setImageResource(((LocalImageInfo) model).getXBannerUrl());
            }
        });
        guideXB.setBannerData(Arrays.asList(new LocalImageInfo(R.drawable.ic_guide1)
                , new LocalImageInfo(R.drawable.ic_guide2)
                , new LocalImageInfo(R.drawable.ic_guide3)
        ));
        guideXB.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                enterBT.setVisibility(guideXB.getRealCount() - 1 == position ? View.VISIBLE : View.GONE);
            }
        });
    }

    /**
     * click: enterBT
     * 点击进入按钮跳转到主页
     */
    @ViewClick(ids = R.id.enterBT) private void enterBT(View v) {
        gotoMainActivity();
    }

    /**
     * 跳转到主页方法
     */
    private void gotoMainActivity() {
        startActivity(new Intent(getContext(), MainActivity.class));
        finish();
    }
}