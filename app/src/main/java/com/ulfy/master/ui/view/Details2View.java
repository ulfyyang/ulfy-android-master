package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.Details2VM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_details2)
public class Details2View extends BaseView {
    private Details2VM vm;

    public Details2View(Context context) {
        super(context);
        init(context, null);
    }

    public Details2View(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /**
     * 初始化页面相关设置
     *      1. 该方法在构造方法中执行，这时候还没有初始化好数据
     *      2. 这里适合做一些一次性绑定的工作，如设置列表的适配器、设置监听等
     */
    private void init(Context context, AttributeSet attrs) {

    }

    /**
     * 绑定数据模型
     *      1. 该方法被调用的时候数据模型已经初始化完毕
     *      2. 该方法中适合做一些控件赋值渲染的操作
     */
    @Override public void bind(IViewModel model) {
        vm = (Details2VM) model;
    }
}