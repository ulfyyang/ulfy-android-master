package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.task_transponder.ContentDataInsideLoader;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.Details1VM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_details1)
public class Details1View extends BaseView implements ContentDataInsideLoader.InsideLoaderView {
    @ViewById(id = R.id.containerFL) private FrameLayout containerFL;
    @ViewById(id = R.id.insideFL) private FrameLayout insideFL;
    private Details1VM vm;

    public Details1View(Context context) {
        super(context);
        init(context, null);
    }

    public Details1View(Context context, AttributeSet attrs) {
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
        vm = (Details1VM) model;
    }

    /**
     * 需要实现 ContentDataInsideLoader.InsideLoaderView 接口来实现内部加载的效果
     */
    @Override public ContentDataInsideLoader.InsideLoader proviceInsideLoader() {
        return new ContentDataInsideLoader.InsideLoader(containerFL, insideFL);
    }
}