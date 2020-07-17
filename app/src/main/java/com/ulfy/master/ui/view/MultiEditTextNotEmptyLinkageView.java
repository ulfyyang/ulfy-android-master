package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_linkage.MultiEditTextNotEmptyLinkage;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.MultiEditTextNotEmptyLinkageVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_multi_edit_text_not_empty_linkage)
public class MultiEditTextNotEmptyLinkageView extends BaseView {
    @ViewById(id = R.id.content1ET) private EditText content1ET;
    @ViewById(id = R.id.content2ET) private EditText content2ET;
    @ViewById(id = R.id.scanCodeTV) private TextView resultTV;
    private MultiEditTextNotEmptyLinkageVM vm;

    public MultiEditTextNotEmptyLinkageView(Context context) {
        super(context);
        init(context, null);
    }

    public MultiEditTextNotEmptyLinkageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        new MultiEditTextNotEmptyLinkage(new MultiEditTextNotEmptyLinkage.OnEditChangeListener() {
            @Override public void onAllEditChange(boolean isAllNotEmpty) {
                resultTV.setText(String.format("是否都存在内容：%s", isAllNotEmpty ? "是" : "否"));
            }
        }, content1ET, content2ET);
    }

    @Override public void bind(IViewModel model) {
        vm = (MultiEditTextNotEmptyLinkageVM) model;
    }
}