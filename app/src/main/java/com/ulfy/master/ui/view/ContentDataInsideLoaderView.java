package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.task_transponder.ContentDataInsideLoader;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.ContentDataInsideLoaderVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_content_data_inside_loader)
public class ContentDataInsideLoaderView extends BaseView implements ContentDataInsideLoader.InsideLoaderView {
    @ViewById(id = R.id.containerFL) private FrameLayout containerFL;
    @ViewById(id = R.id.insideTV) private TextView insideTV;
    private ContentDataInsideLoaderVM vm;

    public ContentDataInsideLoaderView(Context context) {
        super(context);
        init(context, null);
    }

    public ContentDataInsideLoaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (ContentDataInsideLoaderVM) model;
        insideTV.setText(vm.content);
    }

    /*
    需要实现ContentDataInsideLoader.InsideLoaderView接口，并提供相应的信息
     */

    @Override public ContentDataInsideLoader.InsideLoader proviceInsideLoader() {
        return new ContentDataInsideLoader.InsideLoader(containerFL, insideTV);
    }
}