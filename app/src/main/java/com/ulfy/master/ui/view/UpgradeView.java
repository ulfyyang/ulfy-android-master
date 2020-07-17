package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.system.AppUtils;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.UpgradeVM;
import com.ulfy.master.domain.cache.UpgradeInfo;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_upgrade)
public class UpgradeView extends BaseView {
    @ViewById(id = R.id.descriptionTV) private TextView descriptionTV;
    @ViewById(id = R.id.confirmOnlineTV) private TextView confirmOnlineTV;
    @ViewById(id = R.id.confirmWebsiteTV) private TextView confirmWebsiteTV;
    private UpgradeVM vm;

    public UpgradeView(Context context) {
        super(context);
        init(context, null);
    }

    public UpgradeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (UpgradeVM) model;
        descriptionTV.setText(UpgradeInfo.getInstance().description);
    }

    /**
     * 在线更新
     */
    @ViewClick(ids = R.id.confirmOnlineTV) private void confirmOnlineTV(View v) {
        AppUtils.upgrade(UpgradeInfo.getInstance().downloadUrl);
    }

    /**
     * 网页更新
     */
    @ViewClick(ids = R.id.confirmWebsiteTV) private void confirmWebsiteTV(View v) {
        AppUtils.launchSystemBrowser(UpgradeInfo.getInstance().downloadPage);
    }
}