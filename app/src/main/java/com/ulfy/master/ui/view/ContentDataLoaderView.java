package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.ContentDataLoaderVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_content_data_loader)
public class ContentDataLoaderView extends BaseView {
    @ViewById(id = R.id.contentTV) private TextView contentTV;
    private ContentDataLoaderVM vm;

    public ContentDataLoaderView(Context context) {
        super(context);
        init(context, null);
    }

    public ContentDataLoaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (ContentDataLoaderVM) model;
        contentTV.setText(vm.content);
    }
}