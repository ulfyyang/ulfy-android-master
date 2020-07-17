package com.ulfy.master.ui.cell;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.List2CM;
import com.ulfy.master.ui.base.BaseCell;

@Layout(id = R.layout.cell_list2)
public class List2Cell extends BaseCell {
    @ViewById(id = R.id.contentTV) private TextView contentTV;
    private List2CM cm;

    public List2Cell(Context context) {
        super(context);
        init(context, null);
    }

    public List2Cell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        cm = (List2CM) model;
        contentTV.setText(String.format("当前位置：%d", cm.index));
    }
}