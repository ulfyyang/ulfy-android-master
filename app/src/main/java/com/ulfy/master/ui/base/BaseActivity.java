package com.ulfy.master.ui.base;

import com.ulfy.android.system.base.UlfyBaseActivity;
import com.umeng.analytics.MobclickAgent;

public abstract class BaseActivity extends UlfyBaseActivity {

    @Override protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

}

