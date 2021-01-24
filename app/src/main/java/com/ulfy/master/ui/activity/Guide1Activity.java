package com.ulfy.master.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.ulfy.android.adapter.PagerViewAdapter;
import com.ulfy.android.system.ActivityUtils;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.android.utils.StatusBarUtils;
import com.ulfy.master.R;
import com.ulfy.master.ui.base.BaseActivity;

@Layout(id = R.layout.activity_guide1)
public class Guide1Activity extends BaseActivity {
    @ViewById(id = R.id.guideVP) private ViewPager guideVP;
    @ViewById(id = R.id.guide1FL) private FrameLayout guide1FL;
    @ViewById(id = R.id.guide1IV) private ImageView guide1IV;
    @ViewById(id = R.id.guide1TV) private TextView guide1TV;
    @ViewById(id = R.id.guide2FL) private FrameLayout guide2FL;
    @ViewById(id = R.id.guide2IV) private ImageView guide2IV;
    @ViewById(id = R.id.guide2TV) private TextView guide2TV;
    @ViewById(id = R.id.guide3FL) private FrameLayout guide3FL;
    @ViewById(id = R.id.guide3IV) private ImageView guide3IV;
    @ViewById(id = R.id.guide3TV) private TextView guide3TV;
    @ViewById(id = R.id.enterTV) private TextView enterTV;

    public static void startActivity() {
        ActivityUtils.startActivity(Guide1Activity.class);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.visiable(this, false);
        initViewContent();
    }

    /**
     * 初始化 ViewPager 显示
     */
    public void initViewContent() {
        guideVP.setAdapter(new PagerViewAdapter(guide1FL, guide2FL, guide3FL));
    }

    /**
     * 点击跳过和进入按钮跳转到主页
     */
    @ViewClick(ids = {R.id.guide1TV, R.id.guide2TV, R.id.guide3TV, R.id.enterTV})
    private void gotoMainActivity(View v) {
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