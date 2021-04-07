package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.task_transponder_smart.SmartConfig;
import com.ulfy.android.task_transponder_smart.SmartRefreshSimpleGifView;
import com.ulfy.android.task_transponder_smart.SmartRefreshSimpleTextView;
import com.ulfy.android.task_transponder_smart.SmartRefresher;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.SmartCustomHeaderVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_smart_custom_header)
public class SmartCustomHeaderView extends BaseView {
    @ViewById(id = R.id.defaultBT) private Button defaultBT;
    @ViewById(id = R.id.simpleTextBT) private Button simpleTextBT;
    @ViewById(id = R.id.simpleGifBT) private Button simpleGifBT;
    @ViewById(id = R.id.smartSRL) private SmartRefreshLayout smartSRL;
    private SmartRefresher refresher;
    private SmartCustomHeaderVM vm;

    public SmartCustomHeaderView(Context context) {
        super(context);
        init(context, null);
    }

    public SmartCustomHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        refresher = new SmartRefresher(smartSRL, new SmartRefresher.OnRefreshSuccessListener() {
            @Override public void onRefreshSuccess(SmartRefresher smartRefresher) {
                bind(vm);
            }
        });
    }

    @Override public void bind(IViewModel model) {
        vm = (SmartCustomHeaderVM) model;
        refresher.updateExecuteBody(vm.loadDataOnExe());
    }

    @ViewClick(ids = {R.id.defaultBT, R.id.simpleTextBT, R.id.simpleGifBT})
    private void changeHeader(View v) {
        switch (v.getId()) {
            case R.id.defaultBT:            // 使用全局配置的选项，如果要修改全局配置建议在 MainApplication 的静态块中配置
                refresher.setSmartRefreshConfig(SmartConfig.smartRefreshConfig);
                break;
            case R.id.simpleTextBT:         // 修改当前刷新器的配置，可以直接覆盖默认配置来部分定制
                refresher.setSmartRefreshConfig(new SmartConfig.DefaultSmartRefreshConfig() {
                    @Override public RefreshHeader getRefreshHeaderView(Context context) {
                        return new SmartRefreshSimpleTextView(context);
                    }
                });
                break;
            case R.id.simpleGifBT:          // 修改当前刷新器的配置，完全重新实现接口来定制
                refresher.setSmartRefreshConfig(new SmartConfig.DefaultSmartRefreshConfig() {
                    @Override public RefreshHeader getRefreshHeaderView(Context context) {
                        return new SmartRefreshSimpleGifView(context);
                    }
                });
                break;
        }
    }
}