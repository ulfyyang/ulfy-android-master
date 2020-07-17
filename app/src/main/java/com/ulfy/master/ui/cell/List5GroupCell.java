package com.ulfy.master.ui.cell;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.List5GroupCM;
import com.ulfy.master.ui.base.BaseCell;

@Layout(id = R.layout.cell_list5_group)
public class List5GroupCell extends BaseCell {
    @ViewById(id = R.id.nameTV) private TextView nameTV;
    private List5GroupCM cm;

    public List5GroupCell(Context context) {
        super(context);
        init(context, null);
    }

    public List5GroupCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        cm = (List5GroupCM) model;
        nameTV.setText(cm.name);
    }
}