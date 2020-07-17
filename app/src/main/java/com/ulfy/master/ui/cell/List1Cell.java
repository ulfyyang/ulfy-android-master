package com.ulfy.master.ui.cell;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.List1CM;
import com.ulfy.master.ui.base.BaseCell;

@Layout(id = R.layout.cell_list1)
public class List1Cell extends BaseCell {
    @ViewById(id = R.id.contentTV) private TextView contentTV;
    private List1CM cm;

    public List1Cell(Context context) {
        super(context);
        init(context, null);
    }

    public List1Cell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        cm = (List1CM) model;
        contentTV.setText(String.format("编号：%d", cm.index));
    }
}