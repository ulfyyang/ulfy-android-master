package com.ulfy.master.ui.cell;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.ContactBookCM;
import com.ulfy.master.ui.base.BaseCell;

@Layout(id = R.layout.cell_contact_book)
public class ContactBookCell extends BaseCell {
    @ViewById(id = R.id.nameTV) private TextView nameTV;
    private ContactBookCM cm;

    public ContactBookCell(Context context) {
        super(context);
        init(context, null);
    }

    public ContactBookCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        cm = (ContactBookCM) model;
        nameTV.setText(cm.peopleName);
    }
}