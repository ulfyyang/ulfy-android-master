package com.ulfy.master.ui.cell;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.RecyclerViewLoaderCM;
import com.ulfy.master.ui.base.BaseCell;

@Layout(id = R.layout.cell_recycler_view_loader)
public class RecyclerViewLoaderCell extends BaseCell {
    @ViewById(id = R.id.contentTV) private TextView contentTV;
    private RecyclerViewLoaderCM cm;

    public RecyclerViewLoaderCell(Context context) {
        super(context);
        init(context, null);
    }

    public RecyclerViewLoaderCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        cm = (RecyclerViewLoaderCM) model;
        contentTV.setText(cm.content);
    }
}