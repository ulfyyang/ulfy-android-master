package com.ulfy.master.ui.cell;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.GroupContactBookCM;
import com.ulfy.master.ui.base.BaseCell;

@Layout(id = R.layout.cell_group_contact_book)
public class GroupContactBookCell extends BaseCell {
    @ViewById(id = R.id.nameTV) private TextView nameTV;
    private GroupContactBookCM cm;

    public GroupContactBookCell(Context context) {
        super(context);
        init(context, null);
    }

    public GroupContactBookCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        cm = (GroupContactBookCM) model;
        nameTV.setText(cm.peopleGroup.groupName);
    }
}