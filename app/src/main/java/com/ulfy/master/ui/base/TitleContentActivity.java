package com.ulfy.master.ui.base;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.android.utils.StatusBarUtils;
import com.ulfy.master.R;

@Layout(id = R.layout.activity_title_content)
public class TitleContentActivity extends BaseActivity {
    /*
    组件全部定义为子类可见，方便操控
     */
    @ViewById(id = R.id.titleFL) protected FrameLayout titleFL;
    @ViewById(id = R.id.left1IV) protected ImageView left1IV;
    @ViewById(id = R.id.left1TV) protected TextView left1TV;
    @ViewById(id = R.id.left2IV) protected ImageView left2IV;
    @ViewById(id = R.id.left2TV) protected TextView left2TV;
    @ViewById(id = R.id.titleTV) protected TextView titleTV;
    @ViewById(id = R.id.right1IV) protected ImageView right1IV;
    @ViewById(id = R.id.right1TV) protected TextView right1TV;
    @ViewById(id = R.id.right2IV) protected ImageView right2IV;
    @ViewById(id = R.id.right2TV) protected TextView right2TV;
    @ViewById(id = R.id.contentFL) protected FrameLayout contentFL;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.translucent(this);
        StatusBarUtils.darkMode(this);
        StatusBarUtils.offsetForStatusBar(getContext(), titleFL);
    }

    /*
    点击左侧第一个图片，返回上一页面
    子类通过重新定义组件的单机事件可复写父组件的单机事件
     */
    @ViewClick(ids = R.id.left1IV) private void left1IV(View v) {
        onBackPressed();
    }

}
