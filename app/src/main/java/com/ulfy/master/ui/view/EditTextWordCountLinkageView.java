package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_linkage.EditTextWordCountLinkage;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.EditTextWordCountLinkageVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_edit_text_word_count_linkage)
public class EditTextWordCountLinkageView extends BaseView {
    @ViewById(id = R.id.contentET) private EditText contentET;
    @ViewById(id = R.id.countTV) private TextView countTV;
    private EditTextWordCountLinkageVM vm;

    public EditTextWordCountLinkageView(Context context) {
        super(context);
        init(context, null);
    }

    public EditTextWordCountLinkageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        new EditTextWordCountLinkage(new EditTextWordCountLinkage.OnWordCountChageListener() {
            @Override public void onTextWordCountChange(int wordCount) {
                countTV.setText(String.format("%d/100", wordCount));
            }
        }, contentET);
    }

    @Override public void bind(IViewModel model) {
        vm = (EditTextWordCountLinkageVM) model;
    }
}