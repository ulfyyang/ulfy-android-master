package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;

import com.ulfy.android.adapter.ListAdapter;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.views.AutoScrollUpLayout;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.AutoScrollUpCM;
import com.ulfy.master.application.vm.AutoScrollUpVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_auto_scroll_up)
public class AutoScrollUpView extends BaseView {
    @ViewById(id = R.id.contentJumpASUL) private AutoScrollUpLayout contentJumpASUL;
    @ViewById(id = R.id.upJumpASUL) private AutoScrollUpLayout upJumpASUL;
    @ViewById(id = R.id.downJumpASUL) private AutoScrollUpLayout downJumpASUL;
    @ViewById(id = R.id.upBiggerJumpASUL) private AutoScrollUpLayout upBiggerJumpASUL;
    @ViewById(id = R.id.downBiggerJumpASUL) private AutoScrollUpLayout downBiggerJumpASUL;
    @ViewById(id = R.id.contentScrollASUL) private AutoScrollUpLayout contentScrollASUL;
    @ViewById(id = R.id.upScrollASUL) private AutoScrollUpLayout upScrollASUL;
    @ViewById(id = R.id.downScrollASUL) private AutoScrollUpLayout downScrollASUL;
    @ViewById(id = R.id.upBiggerScrollASUL) private AutoScrollUpLayout upBiggerScrollASUL;
    @ViewById(id = R.id.downBiggerScrollASUL) private AutoScrollUpLayout downBiggerScrollASUL;
    private ListAdapter<AutoScrollUpCM> adapter = new ListAdapter<>();
    private AutoScrollUpVM vm;

    public AutoScrollUpView(Context context) {
        super(context);
        init(context, null);
    }

    public AutoScrollUpView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        /*
        跳跃滚动设置点击事件
         */
        contentJumpASUL.setAdapter(adapter);
        adapter.setOnItemClickListener(contentJumpASUL, new ListAdapter.OnItemClickListener<AutoScrollUpCM>() {
            @Override public void onItemClick(AdapterView<?> parent, View view, int position, AutoScrollUpCM model) {
                UiUtils.show("点击了：" + model.content);
            }
        });
        upJumpASUL.setAdapter(adapter);
        adapter.setOnItemClickListener(upJumpASUL, new ListAdapter.OnItemClickListener<AutoScrollUpCM>() {
            @Override public void onItemClick(AdapterView<?> parent, View view, int position, AutoScrollUpCM model) {
                UiUtils.show("点击了：" + model.content);
            }
        });
        downJumpASUL.setAdapter(adapter);
        adapter.setOnItemClickListener(downJumpASUL, new ListAdapter.OnItemClickListener<AutoScrollUpCM>() {
            @Override public void onItemClick(AdapterView<?> parent, View view, int position, AutoScrollUpCM model) {
                UiUtils.show("点击了：" + model.content);
            }
        });
        upBiggerJumpASUL.setAdapter(adapter);
        adapter.setOnItemClickListener(upBiggerJumpASUL, new ListAdapter.OnItemClickListener<AutoScrollUpCM>() {
            @Override public void onItemClick(AdapterView<?> parent, View view, int position, AutoScrollUpCM model) {
                UiUtils.show("点击了：" + model.content);
            }
        });
        downBiggerJumpASUL.setAdapter(adapter);
        adapter.setOnItemClickListener(downBiggerJumpASUL, new ListAdapter.OnItemClickListener<AutoScrollUpCM>() {
            @Override public void onItemClick(AdapterView<?> parent, View view, int position, AutoScrollUpCM model) {
                UiUtils.show("点击了：" + model.content);
            }
        });

        /*
        持续滚动的时候是无法点中的
         */
        contentScrollASUL.setAdapter(adapter);
        adapter.setOnItemClickListener(contentScrollASUL, new ListAdapter.OnItemClickListener<AutoScrollUpCM>() {
            @Override public void onItemClick(AdapterView<?> parent, View view, int position, AutoScrollUpCM model) {
                UiUtils.show("点击了：" + model.content);
            }
        });
        upScrollASUL.setAdapter(adapter);
        adapter.setOnItemClickListener(upScrollASUL, new ListAdapter.OnItemClickListener<AutoScrollUpCM>() {
            @Override public void onItemClick(AdapterView<?> parent, View view, int position, AutoScrollUpCM model) {
                UiUtils.show("点击了：" + model.content);
            }
        });
        downScrollASUL.setAdapter(adapter);
        adapter.setOnItemClickListener(downScrollASUL, new ListAdapter.OnItemClickListener<AutoScrollUpCM>() {
            @Override public void onItemClick(AdapterView<?> parent, View view, int position, AutoScrollUpCM model) {
                UiUtils.show("点击了：" + model.content);
            }
        });
        upBiggerScrollASUL.setAdapter(adapter);
        adapter.setOnItemClickListener(upBiggerScrollASUL, new ListAdapter.OnItemClickListener<AutoScrollUpCM>() {
            @Override public void onItemClick(AdapterView<?> parent, View view, int position, AutoScrollUpCM model) {
                UiUtils.show("点击了：" + model.content);
            }
        });
        downBiggerScrollASUL.setAdapter(adapter);
        adapter.setOnItemClickListener(downBiggerScrollASUL, new ListAdapter.OnItemClickListener<AutoScrollUpCM>() {
            @Override public void onItemClick(AdapterView<?> parent, View view, int position, AutoScrollUpCM model) {
                UiUtils.show("点击了：" + model.content);
            }
        });
    }

    @Override public void bind(IViewModel model) {
        vm = (AutoScrollUpVM) model;
        adapter.setData(vm.dataCMList);
        adapter.notifyDataSetChanged();
    }

    @Override protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        contentJumpASUL.start();
        upJumpASUL.start();
        downJumpASUL.start();
        upBiggerJumpASUL.start();
        downBiggerJumpASUL.start();
        contentScrollASUL.start();
        upScrollASUL.start();
        downScrollASUL.start();
        upBiggerScrollASUL.start();
        downBiggerScrollASUL.start();
    }

    @Override protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        contentJumpASUL.stop();
        upJumpASUL.stop();
        downJumpASUL.stop();
        upBiggerJumpASUL.stop();
        downBiggerJumpASUL.stop();
        contentScrollASUL.stop();
        upScrollASUL.stop();
        downScrollASUL.stop();
        upBiggerScrollASUL.stop();
        downBiggerScrollASUL.stop();
    }
}