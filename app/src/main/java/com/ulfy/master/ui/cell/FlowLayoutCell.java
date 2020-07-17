package com.ulfy.master.ui.cell;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.FlowLayoutCM;
import com.ulfy.master.ui.base.BaseCell;

@Layout(id = R.layout.cell_flow_layout)
public class FlowLayoutCell extends BaseCell {
    @ViewById(id = R.id.contentTV) private TextView contentTV;
    private FlowLayoutCM cm;

    public FlowLayoutCell(Context context) {
        super(context);
        init(context, null);
    }

    public FlowLayoutCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        cm = (FlowLayoutCM) model;
        contentTV.setText(cm.content);
    }
}