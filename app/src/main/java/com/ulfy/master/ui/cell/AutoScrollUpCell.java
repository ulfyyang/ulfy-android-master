package com.ulfy.master.ui.cell;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.AutoScrollUpCM;
import com.ulfy.master.ui.base.BaseCell;

@Layout(id = R.layout.cell_auto_scroll_up)
public class AutoScrollUpCell extends BaseCell {
    @ViewById(id = R.id.contentTV) private TextView contentTV;
    private AutoScrollUpCM cm;

    public AutoScrollUpCell(Context context) {
        super(context);
        init(context, null);
    }

    public AutoScrollUpCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        cm = (AutoScrollUpCM) model;
        contentTV.setText(cm.content);
    }
}